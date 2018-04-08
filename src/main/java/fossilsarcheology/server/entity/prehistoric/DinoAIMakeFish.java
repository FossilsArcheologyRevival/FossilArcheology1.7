package fossilsarcheology.server.entity.prehistoric;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class DinoAIMakeFish extends EntityAIBase {

	private EntityPrehistoricSwimming dinosaur;
	private World world;

	public DinoAIMakeFish(EntityPrehistoricSwimming dinosaur) {
		this.dinosaur = dinosaur;
		this.world = dinosaur.world;
	}

	@Override
	public boolean shouldExecute() {
		if (!this.dinosaur.isHungry()) {
			return false;
		}
		if (this.dinosaur.getRNG().nextInt(205) != 0) {
			return false;
		}
		if (!this.dinosaur.isInWater()) {
			return false;
		}
		if (this.dinosaur.FISH_ANIMATION == null) {
			return false;
		}
		return true;
	}

	public boolean continueExecuting() {
		if (!this.dinosaur.isHungry()) {
			return false;
		}
		if (!this.dinosaur.isInWater()) {
			return false;
		}
		return true;
	}

	public void startExecuting() {
		if (this.dinosaur.isInWater()) {
			if (this.dinosaur.getAnimation() != this.dinosaur.FISH_ANIMATION) {
				this.dinosaur.setAnimation(this.dinosaur.FISH_ANIMATION);
			}
		}
	}

	public void updateTask() {
		if (this.dinosaur.getAnimation() == this.dinosaur.FISH_ANIMATION) {
			if (this.dinosaur.getAnimationTick() == 20) {
				ItemStack stack2 = new ItemStack(Items.FISH, 1 + this.dinosaur.getRNG().nextInt(2), this.dinosaur.getRNG().nextInt(2));
				this.dinosaur.entityDropItem(stack2, 1);
				resetTask();
				return;
			}
		}
	}
}
