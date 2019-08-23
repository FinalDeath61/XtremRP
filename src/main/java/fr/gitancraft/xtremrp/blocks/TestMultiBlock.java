package fr.gitancraft.xtremrp.blocks;

import com.google.common.collect.ImmutableMap;
import fr.gitancraft.xtremrp.XtremModForge;
import fr.gitancraft.xtremrp.blocks.gui.GuiForge;
import fr.gitancraft.xtremrp.tileentities.TestMultiBlockTileEntity;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;
import java.util.Map;

public class TestMultiBlock extends AMultiBlockMaster {

    public static final PropertyBool ACTIVATED = PropertyBool.create("activated");

    private static final String[][] multiblockPattern = new String[][]{
            {
                    "AMA",
            },
            {
                    " A ",
            },
            {
                    " A ",
            },
            {
                    " A ",
            },
            {
                    " A ",
            },
            {
                    " A ",
            }
    };

    private static final Map<Character, Class> patternMap = ImmutableMap.<Character, Class>builder()
            .put('A', Blocks.STONE.getClass())
            .put('M', TestMultiBlock.class)
            .build();

    public TestMultiBlock() {
        super(Material.ROCK, multiblockPattern, TestMultiBlock.class, patternMap);
        setUnlocalizedName(XtremModForge.MOD_ID + ".testblock");
        setRegistryName("testblock");

        setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH)
                .withProperty(RENDERED, false).withProperty(ACTIVATED, false));
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TestMultiBlockTileEntity();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, RENDERED, ACTIVATED);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta & 3)) //
                .withProperty(RENDERED, (meta & 4) == 4) //
                .withProperty(ACTIVATED, (meta & 8) == 8);
    }

    @Override
    public int getMetaFromState(IBlockState state) { // RENDERED = 4 | ACTIVATED = 8
        int i = 0;
        i |= (state.getValue(FACING)).getHorizontalIndex();
        i |= state.getValue(RENDERED) ? 4 : 0;
        i |= state.getValue(ACTIVATED) ? 8 : 0;
        return i;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TestMultiBlockTileEntity te = (TestMultiBlockTileEntity) worldIn.getTileEntity(pos);
        IItemHandler handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        for (int slot = 0; slot < handler.getSlots(); slot++) {
            ItemStack stack = handler.getStackInSlot(slot);
            InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), stack);
        }
        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        // playerIn.sendMessage(new TextComponentString("MultiBlock formé ? " + isMultiBlockFormed(worldIn, pos)));
        playerIn.openGui(XtremModForge.instance, 1, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }
}
