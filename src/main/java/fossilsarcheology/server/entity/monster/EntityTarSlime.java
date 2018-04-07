package fossilsarcheology.server.entity.monster;

import fossilsarcheology.server.block.FABlockRegistry;
import fossilsarcheology.server.item.FAItemRegistry;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityTarSlime extends EntitySlime {

	private int newSlimeJumpDelay;

	public EntityTarSlime(World world) {
		super(world);
		newSlimeJumpDelay = this.rand.nextInt(20) + 10;

	}

	protected EnumParticleTypes getParticleType() {
		return EnumParticleTypes.SUSPENDED_DEPTH;
	}


	@Override
	protected EntitySlime createInstance() {
		return new EntityTarSlime(this.world);
	}

	@Override
	protected float getSoundPitch() {
		return 0.5F;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		int j = MathHelper.floor(this.posX);
		int k = MathHelper.floor(this.posY);
		int l = MathHelper.floor(this.posZ);
		if (this.world.getBlockState(new BlockPos(j, k, l)).getBlock() == FABlockRegistry.TAR) {
			this.motionY *= 1.3;
		}
		boolean flag = onGround;
		if (onGround && !flag || this.isJumping) {
			this.playSound(this.getJumpSound(), this.getSoundVolume(), getSoundPitch() * ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);
		}

	}

	protected void alterSquishAmount() {
		this.squishAmount *= 1F;
	}

	@Override
	public void updateRidden() {
		super.updateRidden();
		if (this.getRidingEntity() != null) {
			this.rotationYaw = getRidingEntity().rotationYaw;
			if (this.getRidingEntity() instanceof EntityPlayer) {
				this.rotationYaw = ((EntityPlayer) getRidingEntity()).rotationYawHead;
				this.setPosition(posX, posY - 1.6F, posZ);
			}
			if (getRidingEntity() instanceof EntityLivingBase) {
				((EntityLivingBase) getRidingEntity()).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 100, 1));
			}
			if (this.ticksExisted % 20 == 0) {
				this.getRidingEntity().attackEntityFrom(DamageSource.causeMobDamage(this), 0.5F * this.getSlimeSize());
				this.playSound(this.getJumpSound(), this.getSoundVolume(), getSoundPitch() * ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);
				squishFactor = 0.7F;
			} else {
				squishFactor = 0.0F;

			}
			// alterSquishAmount();
		}
	}

	@Override
	protected Item getDropItem() {
		return FAItemRegistry.TARDROP;
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer player) {
		super.onCollideWithPlayer(player);
		if (this.rand.nextInt(6) == 0 && player.getPassengers().isEmpty()) {
			if (!player.capabilities.isCreativeMode) {
				this.startRiding(player);
			}
		}
	}


	@Override
	protected boolean makesSoundOnJump() {
		return false;
	}

	@Override
	public boolean isEntityInsideOpaqueBlock() {
		for (int i = 0; i < 8; ++i) {
			float f = ((float) ((i) % 2) - 0.5F) * this.width * 0.8F;
			float f1 = ((float) ((i >> 1) % 2) - 0.5F) * 0.1F;
			float f2 = ((float) ((i >> 2) % 2) - 0.5F) * this.width * 0.8F;
			int j = MathHelper.floor(this.posX + (double) f);
			int k = MathHelper.floor(this.posY + (double) this.getEyeHeight() + (double) f1);
			int l = MathHelper.floor(this.posZ + (double) f2);

			if (this.world.getBlockState(new BlockPos(j, k, l)).isNormalCube() && this.world.getBlockState(new BlockPos(j, k, l)).getBlock() != FABlockRegistry.TAR) {
				return true;
			}
		}

		return false;
	}
}
