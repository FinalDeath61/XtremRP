package fr.gitancraft.xtremrp.proxy;


import fr.gitancraft.xtremrp.ModBlocks;
import fr.gitancraft.xtremrp.ModCapabilities;
import fr.gitancraft.xtremrp.ModKnowledge;
import fr.gitancraft.xtremrp.Util.RegistryHandler;
import fr.gitancraft.xtremrp.XtremModForge;
import fr.gitancraft.xtremrp.capabilities.IKnowledges;
import fr.gitancraft.xtremrp.capabilities.Knowledges;
import fr.gitancraft.xtremrp.knowledges.AKnowledge;
import fr.gitancraft.xtremrp.network.ModPacketHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import java.io.File;

@Mod.EventBusSubscriber(modid = XtremModForge.MOD_ID)
public class XtremCommon {

    public void preInit(File configFile) {
        RegistryHandler.preInitRegistries();
        ModCapabilities.registerCapabilities();
        ModPacketHandler.registerPackets();
    }

    public void init() {
        NetworkRegistry.INSTANCE.registerGuiHandler(XtremModForge.instance, new XtremGui());
    }

    public void postInit() {

    }


    @SubscribeEvent
    public static void createRegistries(RegistryEvent.NewRegistry event) {
        ModKnowledge.createRegistry(event);

    }

    @SubscribeEvent
    public void onPlayerCloned(PlayerEvent.Clone event) {
        IKnowledges originalKnowledge = event.getOriginal().getCapability(ModCapabilities.CAPABILITY_KNOWLEDGES, null);
        IKnowledges newKnowledge = event.getEntityPlayer().getCapability(ModCapabilities.CAPABILITY_KNOWLEDGES, null);

        System.err.println(originalKnowledge.getKnowledges() + " / " + newKnowledge.getKnowledges());
        newKnowledge.setKnowledges(originalKnowledge.getKnowledges());
    }

    @SubscribeEvent
    public static void onAttachCapability(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof EntityPlayer) {
            if (event.getObject().getCapability(ModCapabilities.CAPABILITY_KNOWLEDGES, null) != null) {
                event.getObject().getCapability(ModCapabilities.CAPABILITY_KNOWLEDGES, null).sync();
            }
            System.err.println("addCapa");
            event.addCapability(new ResourceLocation(XtremModForge.MOD_ID, "knowledges"), new IKnowledges.Provider((EntityPlayer) event.getObject()));
        }
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        ModBlocks.registerBlocks(event);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        ModBlocks.registerItems(event);
    }

    @SubscribeEvent
    public static void registerKnowledges(RegistryEvent.Register<AKnowledge> event) {
        ModKnowledge.registerKnowledges(event);
    }

    @SubscribeEvent
    public static void registerSoundEvents(RegistryEvent.Register<SoundEvent> event) {
        //ModSounds.RegistrationHandler.registerSoundEvents(event);
    }

}
