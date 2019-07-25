package fr.gitancraft.xtremrp.proxy;


import java.io.File;

import fr.gitancraft.xtremrp.ModBlocks;
import fr.gitancraft.xtremrp.ModCapabilities;
import fr.gitancraft.xtremrp.ModPackets;
import fr.gitancraft.xtremrp.XtremModForge;
import fr.gitancraft.xtremrp.Util.RegistryHandler;
import fr.gitancraft.xtremrp.entity.capabilities.ListQuestProvider;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod.EventBusSubscriber
public class XtremCommon {

	public void preInit(File configFile) {
		RegistryHandler.preInitRegistries();
        ModCapabilities.registerCapabilities();
	}

	public void init() {
		NetworkRegistry.INSTANCE.registerGuiHandler(XtremModForge.instance, new XtremGui());
		ModPackets.registerMessage();
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
	public static void addCapabilityToPlayer(AttachCapabilitiesEvent<Entity> event) {
		if(event.getObject() instanceof EntityPlayer) {
			if(!event.getCapabilities().containsKey(new ResourceLocation(XtremModForge.MOD_ID, "PlayerQuests"))) {
				event.addCapability(new ResourceLocation(XtremModForge.MOD_ID, "PlayerQuests"), new ListQuestProvider());
			}
		}
	}
	
	@SubscribeEvent
	public static void registerSoundEvents(RegistryEvent.Register<SoundEvent> event) {
		//ModSounds.RegistrationHandler.registerSoundEvents(event);
	}

}
