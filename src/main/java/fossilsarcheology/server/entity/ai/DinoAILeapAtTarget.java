package fossilsarcheology.server.entity.ai;

import fossilsarcheology.server.entity.prehistoric.EntityPrehistoric;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public class DinoAILeapAtTarget extends EntityAIBase {
	EntityPrehistoric dino;
	EntityLivingBase leapTarget;

	public DinoAILeapAtTarget(EntityPrehistoric dino) {
		this.dino = dino;
		this.setMutexBits(5);
	}

	@Override
	public boolean shouldExecute() {
		this.leapTarget = this.dino.getAttackTarget();

		if (dino.getAnimation() == dino.ATTACK_ANIMATION) {
			return false;
		}
		if (dino.isSitting()) {
			return false;
		}
		if (dino.isRiding()) {
			return false;
		}
		if (this.leapTarget == null) {
			return false;
		} else {
			double d0 = this.dino.getDistanceSq(this.leapTarget);
			return (d0 <= 16.0D) && (this.dino.onGround && this.dino.getRNG().nextInt(5) == 0) && !dino.isMovementBlocked();
		}
	}

	@Override
	public boolean shouldContinueExecuting() {
		return !this.dino.onGround;
	}

	@Override
	public void startExecuting() {
		if (dino.getAnimation() != dino.ATTACK_ANIMATION) {
			dino.setAnimation(dino.ATTACK_ANIMATION);
		}
		dino.faceEntity(this.leapTarget, 100.0F, 100.0F);
	}

}