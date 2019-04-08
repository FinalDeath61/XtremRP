package fr.gitancraft.xtremrp.blocks.gui;

import fr.gitancraft.xtremrp.XtremModForge;
import fr.gitancraft.xtremrp.blocks.container.ForgeContainer;
import fr.gitancraft.xtremrp.tileentities.TestMultiBlockTileEntity;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiForge extends GuiContainer {

    private static final ResourceLocation FORGE_GUI_TEXTURES = new ResourceLocation(XtremModForge.MOD_ID , "textures/gui/forge.png");
    private final GuiKnowledge knowledgeGUI;

    public GuiForge(TestMultiBlockTileEntity tileEntity, ForgeContainer container)
    {
        super(container);
        this.knowledgeGUI = new GuiKnowledge();
    }

    @Override
    public void initGui() {
        super.initGui();
        knowledgeGUI.initGui(width, height, guiLeft, guiTop, mc);
        this.guiLeft = this.knowledgeGUI.updateScreenPosition(this.width, this.xSize);
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
        knowledgeGUI.updateScreen(width, height, guiLeft, guiTop);
        knowledgeGUI.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
    }
}
