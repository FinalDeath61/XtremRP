package fr.gitancraft.xtremrp.Util;


import fr.gitancraft.xtremrp.Pnj.init.EntityInit;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class RegistryHandler {

	public static void preInitRegistries() {
		EntityInit.registerEntities();
		RenderHandler.registerEntityRenderers();
	}
	
	public static void postInitRegistries() {
		
	}
}
