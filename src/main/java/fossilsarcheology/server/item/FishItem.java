package fossilsarcheology.server.item;

import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.entity.EntityFishBase;
import fossilsarcheology.server.entity.prehistoric.PrehistoricEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FishItem extends PrehistoricEntityItem implements DefaultRenderedItem {
	public boolean isEggs;

	public FishItem(PrehistoricEntityType type, boolean isEggs) {
		super(isEggs ? "egg" : "fish", type);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.maxStackSize = 1;
		this.isEggs = isEggs;
	}

	public boolean spawnFish(World world, double x, double y, double z) {
		Entity egg = type.invokeClass(world);
		if (egg != null) {
			egg.setLocationAndAngles(x, y + 1, z, world.rand.nextFloat() * 360.0F, 0.0F);
			if (egg instanceof EntityFishBase) {
				EntityFishBase prehistoric = (EntityFishBase) egg;
				if (this.isEggs) {
					prehistoric.setGrowingAge(-24000);
				} else {
					prehistoric.setGrowingAge(12000);
				}
				if (!world.isRemote) {
					world.spawnEntity(egg);
				}
			}
		}
		return egg != null;
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		boolean success = this.spawnFish(world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
		if (success && !player.capabilities.isCreativeMode) {
			player.getHeldItem(hand).shrink(1);
		}
		return success ? EnumActionResult.SUCCESS : EnumActionResult.FAIL;
	}
}
