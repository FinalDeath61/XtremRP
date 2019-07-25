package fr.gitancraft.xtremrp.gui;

import java.util.HashMap;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public abstract class GuiNPCFacade extends GuiScreen {

//	   public EntityClientPlayerMP player;
	public boolean drawDefaultBackground;
//	public EntityNPCInterface npc;
	private HashMap buttons;
	private HashMap topbuttons;
	private HashMap sidebuttons;
	private HashMap textfields;
	private HashMap labels;
	private HashMap scrolls;
	private HashMap sliders;
	private HashMap extra;
	public String title;
	private ResourceLocation background;
	public boolean closeOnEsc;
	public int guiLeft;
	public int guiTop;
	public int xSize;
	public int ySize;
//	private SubGuiInterface subgui;
	public int mouseX;
	public int mouseY;

	public GuiNPCFacade() {
		this.drawDefaultBackground = true;
		this.buttons = new HashMap();
		this.topbuttons = new HashMap();
		this.sidebuttons = new HashMap();
		this.textfields = new HashMap();
		this.labels = new HashMap();
		this.scrolls = new HashMap();
		this.sliders = new HashMap();
		this.extra = new HashMap();
		this.background = null;
		this.closeOnEsc = false;
//	      this.player = Minecraft.getMinecraft().thePlayer;
//		this.npc = npc;
		this.title = "";
		this.xSize = 200;
		this.ySize = 222;
	}
	/**
	 * Permet de mettre en place la background du gui 
	 * @param texture
	 */
	 public void setBackground(String texture) {
	      this.background = new ResourceLocation("customnpcs", "textures/gui/" + texture);
	   }

	 /**
	  * Getter de la texture en backgroundS
	  * @param texture
	  * @return
	  */
	   public ResourceLocation getResource(String texture) {
	      return new ResourceLocation("customnpcs", "textures/gui/" + texture);
	   }
	   
	   /**
	    * Méthode d'initialisation du gui
	    */
	   @Override
	   public void initGui() {
		super.initGui();
//	      GuiNpcTextField.unfocus();
//	      if(this.subgui != null) {
//	         this.subgui.setWorldAndResolution(super.mc, super.width, super.height);
//	         this.subgui.initGui();
//	      }

	      this.guiLeft = (super.width - this.xSize) / 2;
	      this.guiTop = (super.height - this.ySize) / 2;
	      super.buttonList.clear();
	      this.labels.clear();
	      this.textfields.clear();
	      this.buttons.clear();
	      this.sidebuttons.clear();
	      this.topbuttons.clear();
	      this.scrolls.clear();
	      this.sliders.clear();
	      Keyboard.enableRepeatEvents(true);
	   }
	   
	   
}
