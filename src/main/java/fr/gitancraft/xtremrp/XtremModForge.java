package fr.gitancraft.xtremrp;


import org.apache.logging.log4j.Logger;

import fr.gitancraft.xtremrp.proxy.XtremCommon;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid=XtremModForge.MOD_ID, name= "Xtrem Mod", version="0.1")
public class XtremModForge {
	
	public static final int ENTITY_QUESTNPC = 120;
	public static final String MOD_ID="xtremrp";
	
	@Instance(XtremModForge.MOD_ID)
	public static XtremModForge instance;
	
	@SidedProxy(clientSide="fr.gitancraft.xtremrp.proxy.XtremClient", serverSide="fr.gitancraft.xtremrp.proxy.XtremServer")
	public static XtremCommon proxy;
	
	
	public static Logger log;
	/**
	 * Au chargement du mod, check de toutes les m�thodes annot�es eventhandler. Execution en fonction de l'�tape correspondant a la 
	 * classe FML appel�e.
	 * @param event
	 */
	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		log = event.getModLog();
		proxy.preInit(event.getSuggestedConfigurationFile());
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init();
	}
}