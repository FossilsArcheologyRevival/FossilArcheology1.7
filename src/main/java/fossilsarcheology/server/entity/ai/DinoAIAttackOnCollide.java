package fossilsarcheology.server.entity.ai;

import fossilsarcheology.server.entity.prehistoric.EntityPrehistoric;
import fossilsarcheology.server.entity.utility.EntityToyBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class DinoAIAttackOnCollide extends EntityAIBase {
	private final EntityPrehistoric dino;
	private World worldObj;
	private int attackTick;
	private double speed;
	private boolean field_75437_f;
	private Path entityPathEntity;
	private Class classTarget;

	public DinoAIAttackOnCollide(EntityPrehistoric entityNewPrehistoric, double speed, boolean par4) {
		this.dino = entityNewPrehistoric;
		this.worldObj = entityNewPrehistoric.world;
		this.speed = speed;
		this.field_75437_f = par4;
		this.setMutexBits(3);
	}

	@Override
	public boolean shouldExecute() {
		EntityLivingBase entitylivingbase = this.dino.getAttackTarget();

		if (entitylivingbase == null) {
			return false;
		} else if (!entitylivingbase.isEntityAlive()) {
			return false;
		} else if (this.dino.isMovementBlocked()) {
			return false;
		} else if (this.classTarget != null && !this.classTarget.isAssignableFrom(entitylivingbase.getClass())) {
			return false;
		} else if (dino.world.getDifficulty() == EnumDifficulty.PEACEFUL && entitylivingbase instanceof EntityPlayer) {
			return false;
		} else {
			this.entityPathEntity = this.dino.getNavigator().getPathToEntityLiving(entitylivingbase);
			return this.entityPathEntity != null;
		}
	}

	@Override
	public boolean shouldContinueExecuting() {
		EntityLivingBase entitylivingbase = this.dino.getAttackTarget();
		return entitylivingbase != null && (entitylivingbase.isEntityAlive() && (!this.field_75437_f ? !this.dino.getNavigator().noPath() : this.dino.isWithinHomeDistanceFromPosition(new BlockPos(entitylivingbase))));
	}

	@Override
	public void startExecuting() {
		if (this.dino.getControllingPassenger() == null) {
			this.dino.getNavigator().setPath(this.entityPathEntity, speed);
		}
	}

	@Override
	public void resetTask() {
		this.dino.getNavigator().clearPath();
	}

	@Override
	public void updateTask() {
		EntityLivingBase target = this.dino.getAttackTarget();
		if (target instanceof EntityToyBase && dino.ticksTillPlay > 0) {
			dino.setAttackTarget(null);
			return;
		}
		if (this.dino.getControllingPassenger() == null) {
			this.dino.getNavigator().tryMoveToEntityLiving(target, speed);
		}
		this.attackTick = Math.max(this.attackTick - 1, 0);
		if (this.dino.getDistanceSq(target.posX, target.getEntityBoundingBox().minY, target.posZ) < this.dino.width * 2.0F * this.dino.width * 2.0F + target.width) {
			this.dino.swingArm(EnumHand.MAIN_HAND);
			attackTick = 20;
			this.dino.getNavigator().clearPath();
			this.dino.attackEntityAsMob(target);
		} else {
			this.dino.getLookHelper().setLookPositionWithEntity(target, 30.0F, 30.0F);
		}
	}
}
