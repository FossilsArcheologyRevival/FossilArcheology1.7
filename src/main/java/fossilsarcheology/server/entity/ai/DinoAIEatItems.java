package fossilsarcheology.server.entity.ai;

import fossilsarcheology.server.entity.prehistoric.EntityPrehistoric;
import fossilsarcheology.server.util.FoodMappings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class DinoAIEatItems extends EntityAIBase {
	private EntityItem targetItem;
	private EntityPrehistoric prehistoric;
	private double speed;
	private ItemsSorter targetSorter;
	private int feedingTicks;

	public DinoAIEatItems(EntityPrehistoric prehistoric, double speed) {
		super();
		this.prehistoric = prehistoric;
		this.speed = speed;
		this.targetSorter = new ItemsSorter(this, prehistoric);
		this.setMutexBits(1);
	}

	@Override
	public boolean shouldExecute() {

		if (prehistoric.getHunger() >= prehistoric.getMaxHunger()) {
			return false;
		}
		if (prehistoric.isMovementBlocked()) {
			return false;
		}
		if (prehistoric.getRNG().nextInt(1) != 0) {
			return false;
		}
		this.targetItem = this.getNearestItem(16);
		if (this.targetItem != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean shouldContinueExecuting() {
		if (targetItem == null || !this.targetItem.isEntityAlive()) {
			return false;
		}
		if (prehistoric.getHunger() >= prehistoric.getMaxHunger()) {
			return false;
		}

		if (prehistoric.isMovementBlocked()) {
			return false;
		}
		return false;
	}

	@Override
	public void updateTask() {
		double distance = Math.sqrt(Math.pow(this.prehistoric.posX - this.targetItem.posX, 2.0D) + Math.pow(this.prehistoric.posZ - this.targetItem.posZ, 2.0D));
		if (distance < 16) {
			this.prehistoric.getNavigator().tryMoveToXYZ(this.targetItem.posX, this.targetItem.posY, this.targetItem.posZ, 1D);
			if (distance < 2.5D) {
				if (this.targetItem != null) {
					this.prehistoric.eatItem(this.targetItem.getItem());
					this.targetItem.setDead();
				}
			}
		}
	}

	private EntityItem getNearestItem(int range) {
		List nearbyItems = this.prehistoric.world.getEntitiesWithinAABB(EntityItem.class, this.prehistoric.getEntityBoundingBox().expand(range, range, range));
		Collections.sort(nearbyItems, this.targetSorter);
		Iterator iterateNearbyItems = nearbyItems.iterator();
		EntityItem entityItem = null;

		while (iterateNearbyItems.hasNext()) {

			EntityItem entityItem1 = (EntityItem) iterateNearbyItems.next();
			if (entityItem1.getItem() != null && entityItem1.getItem().getItem() != null)
				if ((FoodMappings.INSTANCE.getItemFoodAmount(entityItem1.getItem(), prehistoric.type.diet) != 0) && this.prehistoric.getDistanceSq(entityItem1) < range) {
					entityItem = entityItem1;
				}
		}
		return entityItem;
	}

	public class ItemsSorter implements Comparator {
		final EntityAIBase ai;
		private Entity entity;

		public ItemsSorter(EntityAIBase var1, Entity var2) {
			this.ai = var1;
			this.entity = var2;
		}

		public int func_48469_a(Entity var1, Entity var2) {
			double var3 = this.entity.getDistanceSq(var1);
			double var5 = this.entity.getDistanceSq(var2);
			return var3 < var5 ? -1 : (var3 > var5 ? 1 : 0);
		}

		@Override
		public int compare(Object var1, Object var2) {
			return this.func_48469_a((Entity) var1, (Entity) var2);
		}
	}
}
