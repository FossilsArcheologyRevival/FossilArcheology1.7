package fossilsarcheology.server.entity.ai;

import fossilsarcheology.server.entity.prehistoric.EntityPrehistoric;
import net.minecraft.entity.ai.EntityAIBase;

public class DinoAILookIdle extends EntityAIBase {
	private EntityPrehistoric prehistoric;
	private double lookX;
	private double lookZ;
	private int idleTime;

	public DinoAILookIdle(EntityPrehistoric prehistoric) {
		this.prehistoric = prehistoric;
		this.setMutexBits(3);
	}

	public boolean shouldExecute() {
		if (prehistoric.isSleeping()) {
			return false;
		}
		return this.prehistoric.getRNG().nextFloat() < 0.02F;
	}

	public boolean continueExecuting() {
		return this.idleTime >= 0;
	}

	public void startExecuting() {
		double d0 = (Math.PI * 2D) * this.prehistoric.getRNG().nextDouble();
		this.lookX = Math.cos(d0);
		this.lookZ = Math.sin(d0);
		this.idleTime = 20 + this.prehistoric.getRNG().nextInt(20);
	}

	public void updateTask() {
		--this.idleTime;
		this.prehistoric.getLookHelper().setLookPosition(this.prehistoric.posX + this.lookX, this.prehistoric.posY + (double) this.prehistoric.getEyeHeight(), this.prehistoric.posZ + this.lookZ, 10.0F, (float) this.prehistoric.getVerticalFaceSpeed());
	}
}