package fr.gitancraft.xtremrp.network;

import fr.gitancraft.xtremrp.XtremModForge;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class ModPacketHandler {

    public static final SimpleNetworkWrapper INSTANCE = new SimpleNetworkWrapper(XtremModForge.MOD_ID);
    private static int id = 0;

    public static void registerPackets() {
        INSTANCE.registerMessage(KnowledgeSyncMessage.ClientHandler.class, KnowledgeSyncMessage.class, id, Side.CLIENT);
        INSTANCE.registerMessage(KnowledgeSyncMessage.ServerHandler.class, KnowledgeSyncMessage.class, id++, Side.SERVER);
    }
}
