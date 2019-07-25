package fr.gitancraft.xtremrp.entity.packets;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.UUID;

import fr.gitancraft.xtremrp.Util.ToByteUtil;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class QuestMessage implements IMessage {

	private UUID idQuest;
	public QuestMessage() {
	}

	public QuestMessage(UUID idQuest) {
		this.idQuest = idQuest;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		short size = buf.readShort();
		idQuest =  UUID.fromString(buf.readCharSequence(size, Charset.forName("UTF-8")).toString());
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeShort(idQuest.toString().length());
		buf.writeCharSequence(idQuest.toString(), Charset.forName("UTF-8"));
	}

	 public static class QuestMessageHandler implements  IMessageHandler<QuestMessage, IMessage>  {
		

		@Override
		public IMessage onMessage(QuestMessage message, MessageContext ctx) {
			System.out.println("add Quest" + message.idQuest);
			return null;
		}

	}
}
