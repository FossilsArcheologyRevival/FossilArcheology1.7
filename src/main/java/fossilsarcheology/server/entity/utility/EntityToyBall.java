package fossilsarcheology.server.entity.utility;

import fossilsarcheology.Revival;
import fossilsarcheology.server.entity.prehistoric.EntityPrehistoric;
import fossilsarcheology.server.entity.projectile.JavelinEntity;
import fossilsarcheology.server.item.FAItemRegistry;
import fossilsarcheology.server.message.MessageRollBall;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityToyBall extends EntityToyBase {

	private static final DataParameter<Integer> COLOR = EntityDataManager.<Integer>createKey(EntityToyBall.class, DataSerializers.VARINT);
	public int rollValue;

	public EntityToyBall(World world) {
		super(world, 15);
		this.setSize(0.5F, 0.5F);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1);
	}

	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(COLOR, 0);
	}

	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setInteger("Color", this.getColor());
	}

	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		this.setColor(compound.getInteger("Color"));
	}

	public void setColor(int color) {
		this.dataManager.set(COLOR, color);
	}

	public int getColor() {
		return this.dataManager.get(COLOR);
	}

	@Override
	public void applyEntityCollision(Entity entity) {
		if (entity != null && !(entity instanceof EntityToyBase)) {
			this.rotationYaw = entity.rotationYaw;
			this.addVelocity((double) (-MathHelper.sin(this.rotationYaw * (float) Math.PI / 180.0F) * 0.5F), 0.1D, (double) (MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F) * 0.5F));
		}
	}

	public void onUpdate() {
		super.onUpdate();
		if (Math.abs(this.motionX) > 0.01 || Math.abs(this.motionZ) > 0.01) {
			rollValue++;
			Revival.NETWORK_WRAPPER.sendToAll(new MessageRollBall(this.getEntityId(), this.rollValue));
		}
	}

	@Override
	protected ItemStack getItem() {
		return new ItemStack(FAItemRegistry.TOY_BALL, 1, this.getColor());
	}

	public boolean attackEntityFrom(DamageSource dmg, float f) {
		if (dmg.getTrueSource() != null) {
			if (dmg.getTrueSource() instanceof EntityPlayer) {
				this.playSound(getAttackNoise(), 1, this.getSoundPitch());
				if (!this.world.isRemote)
					this.world.spawnEntity(new EntityItem(this.world, this.posX, this.posY, this.posZ, this.getItem()));
				this.setDead();
				return true;
			}
			if (dmg.getTrueSource() instanceof JavelinEntity) {
				this.playSound(getAttackNoise(), 1, this.getSoundPitch());
				this.rotationYaw = dmg.getTrueSource().rotationYaw;
				this.addVelocity((double) (-MathHelper.sin(this.rotationYaw * (float) Math.PI / 180.0F) * 0.5F), 0.1D, (double) (MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F) * 0.5F));
				return true;
			}
			if (dmg.getTrueSource() instanceof EntityPrehistoric) {
				((EntityPrehistoric) dmg.getTrueSource()).doPlayBonus(toyBonus);
				if (getAttackNoise() != null) {
					this.playSound(getAttackNoise(), 1, this.getSoundPitch());
					this.rotationYaw = dmg.getTrueSource().rotationYaw;
					this.addVelocity((double) (-MathHelper.sin(this.rotationYaw * (float) Math.PI / 180.0F) * 0.5F), 0.1D, (double) (MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F) * 0.5F));
				}
			}
		}
		return dmg != DamageSource.OUT_OF_WORLD;
	}

	@Override
	protected SoundEvent getAttackNoise() {
		return SoundEvents.ENTITY_SLIME_ATTACK;
	}
}
