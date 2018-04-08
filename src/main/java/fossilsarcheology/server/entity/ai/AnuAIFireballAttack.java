package fossilsarcheology.server.entity.ai;

import fossilsarcheology.server.entity.monster.EntityAnu;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.MathHelper;

public class AnuAIFireballAttack extends EntityAIBase {
	private final EntityAnu entityHost;
	private final IRangedAttackMob rangedAttackEntityHost;
	private EntityLivingBase attackTarget;
	private int rangedAttackTime;
	private double entityMoveSpeed;
	private int field_75318_f;
	private int field_96561_g;
	private int maxRangedAttackTime;
	private float field_96562_i;
	private float field_82642_h;

	public AnuAIFireballAttack(EntityAnu mob, double d, int i, float f) {
		this(mob, d, i, i, f);
	}

	public AnuAIFireballAttack(EntityAnu anu, double d, int i0, int i1, float f) {
		this.rangedAttackTime = -1;

		if (!(anu instanceof EntityLivingBase)) {
			throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
		} else {
			this.rangedAttackEntityHost = anu;
			this.entityHost = anu;
			this.entityMoveSpeed = d;
			this.field_96561_g = i0;
			this.maxRangedAttackTime = i1;
			this.field_96562_i = f;
			this.field_82642_h = f * f;
			this.setMutexBits(3);
		}
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute() {
		EntityLivingBase entitylivingbase = this.entityHost.getAttackTarget();

		if (entitylivingbase == null) {
			return false;
		} else {
			this.attackTarget = entitylivingbase;
			return entityHost.getAttackMode() == 1;
		}
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	public boolean continueExecuting() {
		return this.shouldExecute() || !this.entityHost.getNavigator().noPath();
	}

	/**
	 * Resets the task
	 */
	public void resetTask() {
		this.attackTarget = null;
		this.field_75318_f = 0;
		this.rangedAttackTime = -1;
	}

	/**
	 * Updates the task
	 */
	public void updateTask() {
		double d0 = this.entityHost.getDistanceSq(this.attackTarget.posX, this.attackTarget.getEntityBoundingBox().minY, this.attackTarget.posZ);
		boolean flag = this.entityHost.getEntitySenses().canSee(this.attackTarget);

		if (flag) {
			++this.field_75318_f;
		} else {
			this.field_75318_f = 0;
		}

		if (d0 <= (double) this.field_82642_h && this.field_75318_f >= 20) {
			this.entityHost.getNavigator().clearPath();
		} else {
			this.entityHost.getNavigator().tryMoveToEntityLiving(this.attackTarget, this.entityMoveSpeed);
		}

		this.entityHost.getLookHelper().setLookPositionWithEntity(this.attackTarget, 30.0F, 30.0F);
		float f;

		if (--this.rangedAttackTime == 0) {
			if (d0 > (double) this.field_82642_h || !flag) {
				return;
			}

			f = MathHelper.sqrt(d0) / this.field_96562_i;
			float f1 = f;

			if (f < 0.1F) {
				f1 = 0.1F;
			}

			if (f1 > 1.0F) {
				f1 = 1.0F;
			}

			this.rangedAttackEntityHost.attackEntityWithRangedAttack(this.attackTarget, f1);
			this.rangedAttackTime = MathHelper.floor(f * (float) (this.maxRangedAttackTime - this.field_96561_g) + (float) this.field_96561_g);
		} else if (this.rangedAttackTime < 0) {
			f = MathHelper.sqrt(d0) / this.field_96562_i;
			this.rangedAttackTime = MathHelper.floor(f * (float) (this.maxRangedAttackTime - this.field_96561_g) + (float) this.field_96561_g);
		}
	}
}