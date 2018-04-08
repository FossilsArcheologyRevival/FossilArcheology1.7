package fossilsarcheology.server.message;

import fossilsarcheology.Revival;
import fossilsarcheology.server.entity.prehistoric.EntityPrehistoric;
import io.netty.buffer.ByteBuf;
import net.ilexiconn.llibrary.server.network.AbstractMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class MessageHappyParticles extends AbstractMessage<MessageHappyParticles> {

	public int dinosaurID;

	public MessageHappyParticles(int dinosaurID) {
		this.dinosaurID = dinosaurID;
	}

	public MessageHappyParticles() {
	}

	public static final void spawnParticle(Entity entity, Item item) {
		Random rand = new Random();
		double motionX = rand.nextGaussian() * 0.07D;
		double motionY = rand.nextGaussian() * 0.07D;
		double motionZ = rand.nextGaussian() * 0.07D;
		float f = (float) (rand.nextFloat() * (entity.getEntityBoundingBox().maxX - entity.getEntityBoundingBox().minX) + entity.getEntityBoundingBox().minX);
		float f1 = (float) (rand.nextFloat() * (entity.getEntityBoundingBox().maxY - entity.getEntityBoundingBox().minY) + entity.getEntityBoundingBox().minY);
		float f2 = (float) (rand.nextFloat() * (entity.getEntityBoundingBox().maxZ - entity.getEntityBoundingBox().minZ) + entity.getEntityBoundingBox().minZ);
		Revival.PROXY.spawnPacketHeartParticles(entity.world, f, f1, f2, motionX, motionY, motionZ);

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onClientReceived(Minecraft client, MessageHappyParticles message, EntityPlayer player, MessageContext messageContext) {
		Entity entity = player.world.getEntityByID(message.dinosaurID);

		if (entity instanceof EntityPrehistoric) {
			EntityPrehistoric prehistoric = (EntityPrehistoric) entity;

		}
	}

	@Override
	public void onServerReceived(MinecraftServer server, MessageHappyParticles message, EntityPlayer player, MessageContext messageContext) {

	}

	@Override
	public void fromBytes(ByteBuf buf) {
		dinosaurID = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(dinosaurID);
	}
}