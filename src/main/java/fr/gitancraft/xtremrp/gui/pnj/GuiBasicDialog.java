package fr.gitancraft.xtremrp.gui.pnj;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import fr.gitancraft.xtremrp.ModCapabilities;
import fr.gitancraft.xtremrp.ModPackets;
import fr.gitancraft.xtremrp.XtremModForge;
import fr.gitancraft.xtremrp.Util.formattage.formattageUtils;
import fr.gitancraft.xtremrp.elements.quest.QuestStatus;
import fr.gitancraft.xtremrp.entity.capabilities.Quest;
import fr.gitancraft.xtremrp.entity.packets.QuestMessage;
import fr.gitancraft.xtremrp.gui.GuiPNJMaster;
import fr.gitancraft.xtremrp.refs.ButtonNPCDialogID;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiButtonImage;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiButtonExt;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBasicDialog extends GuiPNJMaster {

	private final static int MAX_WIDTH = 25;
	private List<String> messages = new ArrayList<>();
	private int firstX;
	private int firstY;
	private int firstLine;
	private int originalLastLine;
	private EntityPlayer player;
	private static final ResourceLocation background = new ResourceLocation(XtremModForge.MOD_ID,
			"textures/gui/backgroundbasicguidialog.png");
	private String firstMessage = "";

	public GuiBasicDialog(EntityPlayer player) {
		this.player = player;
	}

	@Override
	public void initGui() {
		messages.add(
				"ceci est un test, cousin n'a pas du tout une grosse bite, contrairement a richard, qui lui, en a une FANTAAAAASQUE on  test si y'en a plus que 5  tata atatzzzzzzza on continue d'afficher trop de texte pour savoir comment faire pour affichier plusieurs page, car oui c'est cool les pages lol t'a vu y'a des pages en plus et pluys apres y'a plus rien, ceci dit je suis sur que ca dépasse de texte maitenantn");
		firstLine = 0;
		buttonList.add(new GuiButtonExt(ButtonNPCDialogID.ADMIN_PANEL.getIdButton(), super.guiLeft + 5,
				super.guiTop + 50, 50, 10, "Admin Panel"));
		originalLastLine = 10;
		// TODO messages = jsonLecture
		firstMessage = messages.get(0);
		drawFleche();
		super.initGui();
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

		mc.getTextureManager().bindTexture(background);
		drawTexturedModalRect(guiLeft - 120, height / 2 - 120, 0, 0, 684 / 4, 856 / 4);

	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		// TODO Auto-generated method stub
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		RenderHelper.enableGUIStandardItemLighting();
		GlStateManager.disableLighting();
		GlStateManager.pushMatrix();

		firstX = guiLeft - 85;
		firstY = height / 2 - 80;

		
		for (GuiButton guiButton : buttonList) {
			if (guiButton.id == ButtonNPCDialogID.PREVIOUS_PAGE.getIdButton()) {
				guiButton.x = super.guiLeft - 85;
				guiButton.y = super.guiTop + 160;
			}
			if (guiButton.id == ButtonNPCDialogID.NEXT_PAGE.getIdButton()) {
				guiButton.x = super.guiLeft - 50;
				guiButton.y = super.guiTop + 160;
			}
			if (guiButton.id == ButtonNPCDialogID.ADMIN_PANEL.getIdButton()) {
				guiButton.x = super.guiLeft;
				guiButton.y = super.guiTop - 26;
			}
		}

		displayMessages(formattageUtils.wrapText(firstMessage, MAX_WIDTH));
		GlStateManager.popMatrix();

	}

	/**
	 * J permet le décalage des lignes, mais aussi le compte de row actif sur la
	 * page
	 * 
	 * @param message
	 */
	public void displayMessages(String[] message) {
		int j = 0;
		for (int i = firstLine; i < originalLastLine; i++) {
			if (i < message.length && i > 0) {
				fontRenderer.drawString(message[i], firstX, firstY + j * 8, 0x606060);
				j++;
			}
		}
	}

	/**
	 * new GuiButtonImage(IDButton, posX , PosY, width, height, atXRessource,
	 * atYressourceds, mooveRessourceOnHover only Y, Ressource ));
	 */
	private void drawFleche() {
		mc.getTextureManager().bindTexture(background);
		buttonList.add(new GuiButtonImage(ButtonNPCDialogID.PREVIOUS_PAGE.getIdButton(), super.guiLeft - 85,
				super.guiTop + 160, 16 / 4, 28 / 4, 42 / 4, 912 / 4, 31 / 4, background));
		buttonList.add(new GuiButtonImage(ButtonNPCDialogID.NEXT_PAGE.getIdButton(), super.guiLeft - 50,
				super.guiTop + 160, 16 / 4, 28 / 4, 17 / 4, 918 / 4, 31 / 4, background));
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if (button.enabled) {
			switch (button.id) {
			case 0:
				ModPackets.INSTANCE.sendToServer(new QuestMessage(UUID.randomUUID()));
				button.enabled = false;
				System.out.println("mabite");
				break;
			case 1:
				firstLine += 10;
				originalLastLine += 10;
				break;
			case 2:
				firstLine -= 10;
				originalLastLine -= 10;
				System.out.println(firstLine);
				break;
			default:
				break;
			}
		}
	}

}
