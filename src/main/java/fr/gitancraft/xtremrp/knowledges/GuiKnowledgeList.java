package fr.gitancraft.xtremrp.knowledges;

import fr.gitancraft.xtremrp.ModCapabilities;
import fr.gitancraft.xtremrp.ModKnowledge;
import fr.gitancraft.xtremrp.XtremModForge;
import fr.gitancraft.xtremrp.capabilities.IKnowledges;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.client.GuiScrollingList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class GuiKnowledgeList extends GuiScrollingList {

    protected static final ResourceLocation KNOWLEDGE_GUI = new ResourceLocation(XtremModForge.MOD_ID, "textures/gui/knowledge.png");

    private Minecraft mc;
    private Gui parent;
    private int width;
    private int height;
    private int guiLeft;
    private int guiTop;
    private Consumer<AKnowledge> doubleClickAction = aKnowledge -> {
    };

    private Container playerInventory;
    private Class<? extends AKnowledge> knowledgeType;
    private AKnowledge selectedKnowledge;

    private List<? extends AKnowledge> knowledgesList;

    public GuiKnowledgeList(Minecraft client, int width, int height, int top, int bottom, int left, int entryHeight, int screenWidth, int screenHeight, //
                            EntityPlayer player, Class<? extends AKnowledge> knowledgeType, Gui parent) {
        super(client, width, height, top, bottom, left, entryHeight, screenWidth, screenHeight);
        this.mc = client;
        this.width = screenWidth;
        this.height = screenHeight;
        this.guiLeft = top;
        this.guiTop = left;
        this.playerInventory = player.inventoryContainer;
        this.knowledgeType = knowledgeType;
        this.parent = parent;

        IKnowledges playerKnowledgeCapability = player.getCapability(ModCapabilities.CAPABILITY_KNOWLEDGES, null);
        System.err.println(playerKnowledgeCapability.getKnowledges());
        playerKnowledgeCapability.addKnowledge(ModKnowledge.knowledge1);
        System.err.println(playerKnowledgeCapability.getKnowledges());

        this.knowledgesList = playerKnowledgeCapability.getKnowledges().stream() //
                .filter(k -> k.getClass().isAssignableFrom(knowledgeType)) //
                .collect(Collectors.toList());

//        this.knowledgesList = ModKnowledge.REGISTRY.getValuesCollection() //
//                .stream().filter(k -> k.getClass().isAssignableFrom(knowledgeType)) //
//                .map(k -> knowledgeType.cast(k)).collect(Collectors.toList());
    }

    public void onDoubleClickAction(Consumer<AKnowledge> doubleClickAction) {
        this.doubleClickAction = doubleClickAction;
    }

    @Override
    protected int getSize() {
        return knowledgesList.size();
    }

    @Override
    protected void elementClicked(int index, boolean doubleClick) {
        if (canSelectKnowledge(knowledgesList.get(index))) {
            if (doubleClick) {
                doubleClickAction.accept(selectedKnowledge);
            } else {
                this.selectedKnowledge = knowledgesList.get(index);
            }
        }
    }

    @Override
    protected boolean isSelected(int index) {
        return Objects.isNull(selectedKnowledge) ? false : this.selectedKnowledge.equals(knowledgesList.get(index));
    }

    private int xOffset = 86;

    @Override
    protected void drawBackground() {
        RenderHelper.enableGUIStandardItemLighting();
        GlStateManager.disableLighting();
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0F, 0.0F, 100.0F);
        this.mc.getTextureManager().bindTexture(KNOWLEDGE_GUI);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        int i = (this.width - 144) / 2 - this.xOffset;
        int j = (this.height - 166) / 2;
        parent.drawTexturedModalRect(i, j, 1, 1, 147, 166);
        RenderHelper.disableStandardItemLighting();
        GlStateManager.popMatrix();
    }

    @Override
    protected void drawSlot(int slotIdx, int entryRight, int slotTop, int slotBuffer, Tessellator tess) {
        RenderHelper.enableGUIStandardItemLighting();
        GlStateManager.disableLighting();
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0F, 0.0F, 100.0F);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        int x = (this.width - 112) / 2 - this.xOffset;
        int y = slotTop - 1;

        drawSlotBackground(slotIdx, x, y);

        if (knowledgeType.equals(ForgeKnowledge.class)) { /* ###### Forge Knowledge ###### */
            ForgeKnowledge knowledge = (ForgeKnowledge) knowledgesList.get(slotIdx);
            ItemStack item = knowledge.getResult();
            mc.getRenderItem().renderItemIntoGUI(item, x + 4, y + 4);
        } else { /* ###### *** Knowledge ###### */
        }

        RenderHelper.disableStandardItemLighting();
        GlStateManager.popMatrix();
    }

    private void drawSlotBackground(int slotIdx, int x, int y) {
        this.mc.getTextureManager().bindTexture(KNOWLEDGE_GUI);

        if (canSelectKnowledge(knowledgesList.get(slotIdx))) {
            if (isSelected(slotIdx)) {
                parent.drawTexturedModalRect(x, y, 29, 231, 107, 24);
            } else {
                parent.drawTexturedModalRect(x, y, 29, 206, 107, 24);
            }
        } else {
            parent.drawTexturedModalRect(x, y, 137, 206, 107, 24);
        }
    }

    private boolean canSelectKnowledge(AKnowledge knowledge) {
        if (knowledge instanceof ICraftKnowledge) {
            ICraftKnowledge craftKnowledge = (ICraftKnowledge) knowledge;
            return canCraftKnowledge(craftKnowledge);
        }
        return false;
    }

    private boolean canCraftKnowledge(ICraftKnowledge knowledge) {
        List<ItemStack> recipe = new ArrayList<>(knowledge.getRecipe());
        List<Slot> inventory = playerInventory.inventorySlots.stream().filter(s -> !s.getStack().equals(ItemStack.EMPTY)).collect(Collectors.toList());
        int i = 0;
        int lastUsedSlot = -1;
        for (Slot slot : inventory) {
            ItemStack slotItemStack = slot.getStack();
            for (ItemStack recipeStack : recipe) {
                if (recipeStack.getItem().getRegistryName().equals(slotItemStack.getItem().getRegistryName()) && //
                        recipeStack.getCount() <= slotItemStack.getCount() && lastUsedSlot != slot.slotNumber) {
                    lastUsedSlot = slot.slotNumber;
                    recipe.remove(recipeStack);
                    i++;
                    break;
                }
            }
            if (i >= knowledge.getRecipe().size()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void handleMouseInput(int mouseX, int mouseY) throws IOException {
        super.handleMouseInput(mouseX, mouseY);
    }
}
