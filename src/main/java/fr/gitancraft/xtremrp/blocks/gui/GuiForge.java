package fr.gitancraft.xtremrp.blocks.gui;

import fr.gitancraft.xtremrp.XtremModForge;
import fr.gitancraft.xtremrp.blocks.container.ForgeContainer;
import fr.gitancraft.xtremrp.knowledges.ForgeKnowledge;
import fr.gitancraft.xtremrp.knowledges.GuiKnowledgeList;
import fr.gitancraft.xtremrp.knowledges.ICraftKnowledge;
import fr.gitancraft.xtremrp.tileentities.TestMultiBlockTileEntity;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import org.lwjgl.input.Mouse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@SideOnly(Side.CLIENT)
public class GuiForge extends GuiContainer {

    private static final ResourceLocation FORGE_GUI_TEXTURES = new ResourceLocation(XtremModForge.MOD_ID, "textures/gui/forge.png");
    private GuiKnowledgeList guiKnowledgeList;
    private IItemHandler itemHandler;
    private TestMultiBlockTileEntity te;
    private EntityPlayer player;

    public GuiForge(TestMultiBlockTileEntity tileEntity, ForgeContainer container, EntityPlayer player) {
        super(container);
        te = tileEntity;
        itemHandler = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        this.player = player;
    }

    @Override
    public void initGui() {
        super.initGui();
        guiKnowledgeList = new GuiKnowledgeList(mc, 130, height / 4, guiTop + 8, guiTop + 158, guiLeft - 60, 26, width, height, player, ForgeKnowledge.class, this);
        guiKnowledgeList.onDoubleClickAction(knowledge -> moveKnowledgeRecipeInSlots((ICraftKnowledge) knowledge));
        this.guiLeft = 177 + (width - xSize - 200) / 2;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(FORGE_GUI_TEXTURES);
        int i = this.guiLeft;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, 176, 192);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        this.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
        guiKnowledgeList.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        int i = Mouse.getEventX() * this.width / this.mc.displayWidth;
        int j = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1;
        guiKnowledgeList.handleMouseInput(i, j);
    }

    private void moveKnowledgeRecipeInSlots(ICraftKnowledge knowledge) {
        List<ItemStack> recipe = knowledge.getRecipe();
        List<Slot> inventory = inventorySlots.inventorySlots.stream().filter(s -> !s.getStack().equals(ItemStack.EMPTY) //
                || s.slotNumber < itemHandler.getSlots()).collect(Collectors.toList());
        for (Slot inventorySlot : inventory) {
            for (int i = 0; i < recipe.size(); i++) {
                if (inventorySlot.getStack().getItem().getRegistryName().equals(recipe.get(i).getItem().getRegistryName())) {
                    // TODO CustomPacket MaJ slots
                    //        inventorySlots.putStackInSlot(inventorySlot.slotNumber, inventorySlot.decrStackSize(recipe.get(i).getCount()));
                    //        inventorySlots.putStackInSlot(i, recipe.get(i));
                    //        inventorySlots.detectAndSendChanges();
                }
            }
        }

    }

}
