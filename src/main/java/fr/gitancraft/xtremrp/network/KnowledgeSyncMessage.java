package fr.gitancraft.xtremrp.network;

import fr.gitancraft.xtremrp.ModCapabilities;
import fr.gitancraft.xtremrp.ModKnowledge;
import fr.gitancraft.xtremrp.capabilities.IKnowledges;
import fr.gitancraft.xtremrp.knowledges.AKnowledge;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class KnowledgeSyncMessage implements IMessage {

    public KnowledgeSyncMessage() {}

    private Set<AKnowledge> knowledges;

    public KnowledgeSyncMessage(Set<AKnowledge> knowledges) {
        this.knowledges = knowledges;
    }

    @Override
    public void toBytes(ByteBuf buf) {

        buf.writeInt(knowledges.size());
        for (AKnowledge knowledge : knowledges) {
            buf.writeShort(knowledge.getName().length());
            buf.writeCharSequence(knowledge.getName(), Charset.forName("UTF-8"));
        }
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        knowledges = new HashSet<>();
        int listSize = buf.readInt();
        for (int i = 1; i < listSize; i++) {
            short nameSize = buf.readShort();
            String knowledgeName = buf.readCharSequence(nameSize, Charset.forName("UTF-8")).toString();
            AKnowledge k = ModKnowledge.getKnowledgeFromName(knowledgeName);
            if (Objects.nonNull(k)) {
                knowledges.add(k);
            }
        }
    }

    public static class ServerHandler implements IMessageHandler<KnowledgeSyncMessage, IMessage> {

        @Override
        public IMessage onMessage(KnowledgeSyncMessage message, MessageContext ctx) {

            EntityPlayerMP serverPlayer = ctx.getServerHandler().player;
            serverPlayer.getServerWorld().addScheduledTask(() -> updateKnowledge(serverPlayer, message.knowledges));
            return null;
        }
    }

    public static class ClientHandler implements IMessageHandler<KnowledgeSyncMessage, IMessage> {

        @Override
        public IMessage onMessage(KnowledgeSyncMessage message, MessageContext ctx) {

            EntityPlayer player = getPlayer();
            Minecraft.getMinecraft().addScheduledTask(() -> updateKnowledge(player, message.knowledges));
            return null;
        }

        @SideOnly(Side.CLIENT)
        private EntityPlayer getPlayer()
        {
            return Minecraft.getMinecraft().player;
        }
    }

    private static void updateKnowledge(EntityPlayer player, Set<AKnowledge> knowledges) {
        System.err.println("Updating knowledge");
        IKnowledges capabilityKnowledge = player.getCapability(ModCapabilities.CAPABILITY_KNOWLEDGES, null);
        capabilityKnowledge.setKnowledges(knowledges);
    }
}
