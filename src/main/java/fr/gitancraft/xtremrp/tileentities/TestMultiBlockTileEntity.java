package fr.gitancraft.xtremrp.tileentities;

import fr.gitancraft.xtremrp.ModKnowledge;
import fr.gitancraft.xtremrp.blocks.TestMultiBlock;
import fr.gitancraft.xtremrp.capabilities.Process;
import fr.gitancraft.xtremrp.knowledges.ForgeKnowledge;
import fr.gitancraft.xtremrp.knowledges.ICraftKnowledge;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestMultiBlockTileEntity extends TileEntity implements ITickable {

    public static final int SIZE = 5;
    public static final int BURN_TIME = 200;

    private Process burnProcess;
    private Process cookProcess;

    private ForgeKnowledge curentlyForgingKnowledge;

    private List<ForgeKnowledge> knowledgesList = ModKnowledge.REGISTRY.getValuesCollection() //
            .stream().filter(k -> k instanceof ForgeKnowledge) //
            .map(k -> (ForgeKnowledge) k).collect(Collectors.toList());

    private ItemStackHandler itemStackHandler = new ItemStackHandler(SIZE) {
        @Override
        protected void onContentsChanged(int slot) {
            // We need to tell the tile entity that something has changed so
            // that the chest contents is persisted
            TestMultiBlockTileEntity.this.markDirty();
        }
    };

    public TestMultiBlockTileEntity() {
        burnProcess = new Process(0, this::doBurn, this::burnDone);
        cookProcess = new Process(0, this::doCook, this::cookDone);
    }

    private void doBurn() {
        if (!this.world.isRemote) {
            if (cookProcess.getMaxCooldown() > 0)
                cookProcess.doProcess();
        }
    }

    private void burnDone() {
        if (!this.world.isRemote) {
            burnProcess.setMaxCooldown(0);
            world.setBlockState(pos, world.getBlockState(pos).withProperty(TestMultiBlock.ACTIVATED, false), 3);
            cookProcess.setMaxCooldown(0);
        }
    }

    private void doCook() {
        if (!this.world.isRemote) {
            if(!isCraftValide())
                cookProcess.setReversed(true);
            else
                cookProcess.setReversed(false);
        }
    }

    private void cookDone() {
        if (!this.world.isRemote) {
            cookProcess.setMaxCooldown(0);
            finishCook();
            curentlyForgingKnowledge = null;
        }
    }

    private void finishCook() {
        for (ItemStack recipeStack : curentlyForgingKnowledge.getRecipe()) {
            for (int slot = 0; slot < 3; slot++) {
                ItemStack slotItemStack = itemStackHandler.getStackInSlot(slot);
                if (recipeStack.getItem().getRegistryName().equals(slotItemStack.getItem().getRegistryName()) && //
                        recipeStack.getCount() == slotItemStack.getCount()) {
                    itemStackHandler.extractItem(slot, recipeStack.getCount(), false);
                }
            }
        }
        itemStackHandler.insertItem(4, curentlyForgingKnowledge.getResult().copy(), false);
    }

    @Override
    public void update() {
        if (!this.world.isRemote) {
            if(burnProcess.getMaxCooldown() > 0)
                burnProcess.doProcess();
            if(isCraftValide()) {
                if(cookProcess.getMaxCooldown() == 0 && burnProcess.getMaxCooldown() != 0) {
                    cookProcess.setMaxCooldown(BURN_TIME);
                    cookProcess.setReversed(false);
                }
                if(TileEntityFurnace.isItemFuel(itemStackHandler.getStackInSlot(3)) && burnProcess.getMaxCooldown() == 0) {
                    world.setBlockState(pos, world.getBlockState(pos).withProperty(TestMultiBlock.ACTIVATED, true), 3);
                    burnProcess.setMaxCooldown(TileEntityFurnace.getItemBurnTime(itemStackHandler.getStackInSlot(3)));
                    itemStackHandler.extractItem(3, 1, false);
                }
            }
        }
    }

    private boolean isCraftValide() {
        if(curentlyForgingKnowledge != null) {
            return true;
        }
        for (ForgeKnowledge knowledge : knowledgesList) {
            if(canCraft(knowledge)) {
                return true;
            }
        }
        return false;
    }

    private boolean canCraft(ForgeKnowledge knowledge) {
        List<ItemStack> recipe = new ArrayList<>(knowledge.getRecipe());
        int i = 0;
        int lastUsedSlot = -1;
        for (int slot = 0; slot < 3; slot++) {
            ItemStack slotItemStack = itemStackHandler.getStackInSlot(slot);
            for (ItemStack recipeStack : recipe) {
                if (recipeStack.getItem().getRegistryName().equals(slotItemStack.getItem().getRegistryName()) && //
                        recipeStack.getCount() == slotItemStack.getCount() && lastUsedSlot != slot) {
                    lastUsedSlot = slot;
                    recipe.remove(recipeStack);
                    i++;
                    break;
                }
            }
            if (i >= knowledge.getRecipe().size()) {
                curentlyForgingKnowledge = knowledge;
                return true;
            }
        }
        return false;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        burnProcess.deserializeNBT(compound.getCompoundTag("burnProcess"));
        cookProcess.deserializeNBT(compound.getCompoundTag("cookProcess"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("burnProcess", burnProcess.serializeNBT());
        compound.setTag("cookProcess", cookProcess.serializeNBT());
        return super.writeToNBT(compound);
    }

    public boolean canInteractWith(EntityPlayer playerIn) {
        // If we are too far away from this tile entity you cannot use it
        return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(itemStackHandler);
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return oldState.getBlock() != newSate.getBlock();
    }
}
