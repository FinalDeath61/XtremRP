package fr.gitancraft.xtremrp.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class GuiPNJMaster extends GuiContainer {

	public GuiPNJMaster() {
		super(new Container() {
			
			@Override
			public boolean canInteractWith(EntityPlayer playerIn) {
				return false;
			}
		});
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
	}
}
