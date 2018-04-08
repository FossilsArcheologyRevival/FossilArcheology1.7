package fossilsarcheology.server.entity.utility;

import fossilsarcheology.server.item.FAItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityToyScratchingPost extends EntityToyBase {

	public EntityToyScratchingPost(World world) {
		super(world, 20);
	}

	@Override
	protected ItemStack getItem() {
		return new ItemStack(FAItemRegistry.TOY_SCRATCHING_POST);
	}

	@Override
	protected SoundEvent getAttackNoise() {
		return SoundEvents.BLOCK_WOOD_HIT;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		this.motionX *= 0;
		this.motionZ *= 0;
		if (!isOnBlock()) {
			if (!this.world.isRemote)
				this.world.spawnEntity(new EntityItem(this.world, this.posX, this.posY, this.posZ, this.getItem()));
			this.setDead();
			this.playSound(getAttackNoise(), 1, 1);
		}
	}

	public boolean isOnBlock() {
		int blockX = MathHelper.floor(this.posX);
		int blockY = MathHelper.floor(this.posY) - 1;
		int blockZ = MathHelper.floor(this.posZ);
		return !this.world.isAirBlock(new BlockPos(blockX, blockY, blockZ));
	}

	public AxisAlignedBB getCollisionBox(Entity entity) {
		return this.getEntityBoundingBox();
	}

	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	public boolean canBeCollidedWith() {
		return !this.isDead;
	}

	protected float getSoundPitch() {
		return super.getSoundPitch() * 0.2F;
	}
}
