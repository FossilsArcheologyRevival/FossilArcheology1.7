package fossilsarcheology.server.block.entity;

import fossilsarcheology.server.item.FAItemRegistry;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;

public class TileEntityAncientChest extends TileEntity implements ITickable {
	public float lidAngle;
	public float prevLidAngle;
	public int numPlayersUsing;
	public int chestState = 0;
	public int chestLidCounter;
	public int chestLidCounter2;
	private int ticksSinceSync;

	@Override
	public void update() {
		++this.ticksSinceSync;
		float f;
		if (this.chestState != 3) {
			if (chestLidCounter != 0 && chestLidCounter < 91) {
				chestLidCounter++;
			}
			if (chestLidCounter == 91) {
				chestState = 3;
				EntityItem item = new EntityItem(this.world, this.pos.getX() + 0.5, this.pos.getY() + 1, this.pos.getZ() + 0.5, new ItemStack(FAItemRegistry.ANCIENT_CLOCK));
				if (!world.isRemote) {
					this.world.spawnEntity(item);
					item.motionX = 0D;
					item.motionZ = 0D;
					item.motionY += 0.1D;
				}
				world.playSound(null, this.pos, SoundEvents.BLOCK_CHEST_CLOSE, SoundCategory.BLOCKS, 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
			}
		} else {
			if (chestLidCounter != 0 && chestLidCounter > 0) {
				chestLidCounter--;
			}
			if (chestLidCounter == 0) {
				chestState = 0;
			}
		}

		this.prevLidAngle = this.lidAngle;
		f = 0.1F;
		double d2;

		if (this.numPlayersUsing > 0 && this.lidAngle == 0.0F) {
			world.playSound(null, this.pos, SoundEvents.BLOCK_CHEST_OPEN, SoundCategory.BLOCKS, 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
		}

		if (this.numPlayersUsing == 0 && this.lidAngle > 0.0F || this.numPlayersUsing > 0 && this.lidAngle < 1.0F) {
			float f1 = this.lidAngle;

			if (this.numPlayersUsing > 0) {
				this.lidAngle += f;
			} else {
				this.lidAngle -= f;
			}

			if (this.lidAngle > 1.0F) {
				this.lidAngle = 1.0F;
			}

			float f2 = 0.5F;

			if (this.lidAngle < f2 && f1 >= f2) {
				world.playSound(null, this.pos, SoundEvents.BLOCK_CHEST_CLOSE, SoundCategory.BLOCKS, 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
			}

			if (this.lidAngle < 0.0F) {
				this.lidAngle = 0.0F;
			}
		}
	}
}
