package fossilsarcheology.server.entity.projectile;

import fossilsarcheology.server.item.FAItemRegistry;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class JavelinEntity extends EntityArrow implements IEntityAdditionalSpawnData {
	public Item.ToolMaterial material;
	protected int damage;

	public JavelinEntity(World world) {
		super(world);
	}

	public JavelinEntity(World world, Item.ToolMaterial material, int damage, double x, double y, double z) {
		this(world);
		this.damage = damage;
		this.material = material;
		this.setPosition(x, y, z);
	}

	public JavelinEntity(World world, Item.ToolMaterial material, int damage, EntityLivingBase shooter) {
		this(world, material, damage, shooter.posX, shooter.posY + shooter.getEyeHeight() - 0.1, shooter.posZ);
		this.shootingEntity = shooter;
		if (shooter instanceof EntityPlayer) {
			this.pickupStatus = EntityArrow.PickupStatus.ALLOWED;
		}
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		this.ticksInGround = 0;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setByte("Material", (byte) this.material.ordinal());
		compound.setInteger("Damage", this.damage);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		this.material = Item.ToolMaterial.values()[compound.getByte("Material")];
		this.damage = compound.getInteger("Damage");
	}

	@Override
	protected ItemStack getArrowStack() {
		switch (this.material) {
			case WOOD:
				return new ItemStack(FAItemRegistry.WOODEN_JAVELIN);
			case STONE:
				return new ItemStack(FAItemRegistry.STONE_JAVELIN);
			case IRON:
				return new ItemStack(FAItemRegistry.IRON_JAVELIN);
			case GOLD:
				return new ItemStack(FAItemRegistry.GOLD_JAVELIN);
			case DIAMOND:
				return new ItemStack(FAItemRegistry.DIAMOND_JAVELIN);
		}
		return null;
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		buffer.writeByte(this.material.ordinal());
	}

	@Override
	public void readSpawnData(ByteBuf buffer) {
		this.material = Item.ToolMaterial.values()[buffer.readByte()];
	}
}
