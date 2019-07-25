package fr.gitancraft.xtremrp;

import fr.gitancraft.xtremrp.entity.packets.QuestMessage;
import fr.gitancraft.xtremrp.entity.packets.QuestMessage.QuestMessageHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class ModPackets {

	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(XtremModForge.MOD_ID);
	
	public static void registerMessage() {
		INSTANCE.registerMessage(QuestMessageHandler.class, QuestMessage.class, 0, Side.SERVER);
    }
}
