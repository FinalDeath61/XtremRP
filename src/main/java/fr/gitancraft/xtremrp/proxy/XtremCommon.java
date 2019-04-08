package fr.gitancraft.xtremrp.proxy;


import fr.gitancraft.xtremrp.ModBlocks;
import fr.gitancraft.xtremrp.Util.RegistryHandler;
import fr.gitancraft.xtremrp.XtremModForge;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import java.io.File;

@Mod.EventBusSubscriber
public class XtremCommon {

	public void preInit(File configFile) {
		RegistryHandler.preInitRegistries();
	}

	public void init() {
		NetworkRegistry.INSTANCE.registerGuiHandler(XtremModForge.instance, new XtremGui());
	}

	public void postInit() {

	}


	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		ModBlocks.registerBlocks(event);
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		ModBlocks.registerItems(event);
		//ModItems.registerItems(event);
	}

	@SubscribeEvent
	public static void registerSoundEvents(RegistryEvent.Register<SoundEvent> event) {
		//ModSounds.RegistrationHandler.registerSoundEvents(event);
	}

}
