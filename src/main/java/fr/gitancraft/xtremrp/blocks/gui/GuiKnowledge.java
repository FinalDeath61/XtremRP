package fr.gitancraft.xtremrp.blocks.gui;

import fr.gitancraft.xtremrp.XtremModForge;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButtonImage;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SideOnly(Side.CLIENT)
public class GuiKnowledge extends Gui {

    protected static final ResourceLocation KNOWLEDGE_GUI = new ResourceLocation(XtremModForge.MOD_ID ,"textures/gui/knowledge.png");
    private int xOffset = 86;
    private int width;
    private int height;
    private int guiLeft;
    private int guiTop;
    private Minecraft mc;
    private List<GuiButtonImage> knowledgeButton = new ArrayList<>();
    private List<Item> knowledge = Arrays.asList(
            Items.APPLE, Items.ARROW, Items.BEEF, Items.BED,
            Items.BLAZE_ROD, Items.CHICKEN, Items.BLAZE_ROD, Items.CHICKEN,
            Items.DIAMOND_HELMET, Items.FLINT, Items.FURNACE_MINECART, Items.FLINT,
            Items.CHEST_MINECART, Items.WHEAT_SEEDS, Items.IRON_INGOT, Items.APPLE
    );

    public GuiKnowledge() {
    }

    public void initGui(int width, int height, int guiLeft, int guiTop, Minecraft mc) {
        this.width = width;
        this.height = height;
        this.guiLeft = guiLeft;
        this.guiTop = guiTop;
        this.mc = mc;

        int x = this.guiLeft - 62 - this.xOffset + 15;
        int y = this.guiTop + 15;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                knowledgeButton.add(new GuiButtonImage(i + j*4, x + i*31, y + j*31, 24, 24,
                        29, 206, 25, KNOWLEDGE_GUI));
            }
        }
    }

    public void updateScreen(int width, int height, int guiLeft, int guiTop) {
        this.width = width;
        this.height = height;
        this.guiLeft = guiLeft;
        this.guiTop = guiTop;

        int x = this.guiLeft - 62 - this.xOffset + 15;
        int y = this.guiTop + 15;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                knowledgeButton.get(i*4 + j).setPosition(x + i*31, y + j*31);
            }
        }
    }

    public int updateScreenPosition(int width, int xSize)
    {
        int i = 177 + (width - xSize - 200) / 2;

        return i;
    }

    public void render(int mouseX, int mouseY, float partialTicks) {
        renderBackground();
        renderKnowledge(mouseX, mouseY, partialTicks);
    }

    private void renderBackground() {
        RenderHelper.enableGUIStandardItemLighting();
        GlStateManager.disableLighting();
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0F, 0.0F, 100.0F);
        this.mc.getTextureManager().bindTexture(KNOWLEDGE_GUI);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        int i = (this.width - 147) / 2 - this.xOffset;
        int j = (this.height - 166) / 2;
        this.drawTexturedModalRect(i, j, 1, 1, 147, 166);
        RenderHelper.disableStandardItemLighting();
        GlStateManager.popMatrix();
    }

    private void renderKnowledge(int mouseX, int mouseY, float partialTicks) {
        RenderHelper.enableGUIStandardItemLighting();
        GlStateManager.disableLighting();
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0F, 0.0F, 100.0F);
        this.mc.getTextureManager().bindTexture(KNOWLEDGE_GUI);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        int x = (this.width - 147) / 2 - this.xOffset + 15;
        int y = (this.height - 166) / 2 + 15;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                knowledgeButton.get(i*4 + j).drawButton(mc, mouseX, mouseY, partialTicks);
                ItemStack item = new ItemStack(knowledge.get(i*4 + j));
                mc.getRenderItem().renderItemIntoGUI(item, x + i*31 + 4 , y + j*31 + 4);
            }
        }

        RenderHelper.disableStandardItemLighting();
        GlStateManager.popMatrix();
    }
}
