package fossilsarcheology.server.message;

import fossilsarcheology.server.entity.EntityDinosaurEgg;
import fossilsarcheology.server.entity.prehistoric.PrehistoricEntityType;
import io.netty.buffer.ByteBuf;
import net.ilexiconn.llibrary.server.network.AbstractMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MessageUpdateEgg extends AbstractMessage<MessageUpdateEgg> {

	public int dinosaurID;
	public int ordinal;

	public MessageUpdateEgg(int dinosaurID, int ordinal) {
		this.dinosaurID = dinosaurID;
		this.ordinal = ordinal;
	}

	public MessageUpdateEgg() {
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onClientReceived(Minecraft client, MessageUpdateEgg message, EntityPlayer player, MessageContext messageContext) {
		Entity entity = player.world.getEntityByID(message.dinosaurID);

		if (entity instanceof EntityDinosaurEgg) {
			EntityDinosaurEgg egg = (EntityDinosaurEgg) entity;
			egg.selfType = PrehistoricEntityType.values()[message.ordinal];
		}
	}

	@Override
	public void onServerReceived(MinecraftServer server, MessageUpdateEgg message, EntityPlayer player, MessageContext messageContext) {
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		dinosaurID = buf.readInt();
		ordinal = buf.readInt();

	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(dinosaurID);
		buf.writeInt(ordinal);
	}
}