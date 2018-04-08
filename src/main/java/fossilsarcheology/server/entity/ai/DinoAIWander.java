package fossilsarcheology.server.entity.ai;


import fossilsarcheology.server.entity.prehistoric.EntityPrehistoric;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.Vec3d;

public class DinoAIWander extends EntityAIBase {
	private EntityPrehistoric prehistoric;
	private double xPosition;
	private double yPosition;
	private double zPosition;
	private double speed;

	public DinoAIWander(EntityPrehistoric prehistoric, double speed) {
		this.prehistoric = prehistoric;
		this.speed = speed;
		this.setMutexBits(1);
	}

	public boolean shouldExecute() {
		if (this.prehistoric.isMovementBlocked()) {
			return false;
		}
		if (this.prehistoric.getAgeInTicks() <= 100) {
			return false;
		} else if (this.prehistoric.getRNG().nextInt(120) != 0) {
			return false;
		} else {
			Vec3d vec3 = RandomPositionGenerator.findRandomTarget(
					this.prehistoric, 10, 7);

			if (vec3 == null) {
				return false;
			} else {
				this.xPosition = vec3.x;
				this.yPosition = vec3.y;
				this.zPosition = vec3.z;
				return true;
			}
		}
	}

	public boolean continueExecuting() {
		return !this.prehistoric.getNavigator().noPath();
	}

	public void startExecuting() {
		this.prehistoric.getNavigator().tryMoveToXYZ(this.xPosition,
				this.yPosition, this.zPosition, this.speed);
	}
}