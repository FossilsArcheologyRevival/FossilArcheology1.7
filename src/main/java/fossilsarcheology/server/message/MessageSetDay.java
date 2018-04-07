package fossilsarcheology.server.message;

import fossilsarcheology.server.entity.prehistoric.EntityPrehistoric;
import io.netty.buffer.ByteBuf;
import net.ilexiconn.llibrary.server.network.AbstractMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MessageSetDay extends AbstractMessage<MessageSetDay> {

	public int dinosaurID;
	public boolean isDay;

	public MessageSetDay(int dinosaurID, boolean isDay) {
		this.dinosaurID = dinosaurID;
		this.isDay = isDay;
	}

	public MessageSetDay() {
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onClientReceived(Minecraft client, MessageSetDay message, EntityPlayer player, MessageContext messageContext) {
		Entity entity = player.world.getEntityByID(message.dinosaurID);

		if (entity instanceof EntityPrehistoric) {
			EntityPrehistoric prehistoric = (EntityPrehistoric) entity;
			prehistoric.isDaytime = message.isDay;
		}
	}

	@Override
	public void onServerReceived(MinecraftServer server, MessageSetDay message, EntityPlayer player, MessageContext messageContext) {
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		dinosaurID = buf.readInt();
		isDay = buf.readBoolean();

	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(dinosaurID);
		buf.writeBoolean(isDay);
	}
}