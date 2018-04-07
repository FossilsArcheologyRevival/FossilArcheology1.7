package fossilsarcheology.server.item;

import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.block.FABlockRegistry;
import fossilsarcheology.server.block.FernsBlock;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class FernSeedItem extends Item implements DefaultRenderedItem {

	public FernSeedItem() {
		super();
		this.setHasSubtypes(true);
		this.setCreativeTab(FATabRegistry.ITEMS);
		this.setUnlocalizedName("fern_seed");
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItem(hand);
		if (player.canPlayerEdit(pos, facing, stack) && player.canPlayerEdit(pos.up(), facing, stack)) {
			if (canPlant(world.getBlockState(pos)) && world.isAirBlock(pos.up()) && FernsBlock.checkUnderTree(world, pos)) {
				if (this.placePlantBlock(stack, world, pos.getX(), pos.getY(), pos.getZ(), new Random())) {
					world.playSound(player, pos, FABlockRegistry.DILLHOFFIA_FLOWER.getSoundType().getBreakSound(), SoundCategory.BLOCKS, 1F, new Random().nextFloat() * 0.1F + 0.8F);
				}
				stack.shrink(1);
				return EnumActionResult.SUCCESS;
			} else {
				return EnumActionResult.PASS;
			}
		} else {
			return EnumActionResult.PASS;
		}
	}

	private boolean placePlantBlock(ItemStack stack, World world, int x, int y, int z, Random rand) {
		world.setBlockState(new BlockPos(x, y + 1, z), FABlockRegistry.FERNS.getDefaultState());
		return true;
	}

	public boolean canPlant(IBlockState state) {
		return state.getBlock() == net.minecraft.init.Blocks.GRASS || state.getBlock() == net.minecraft.init.Blocks.DIRT || state.getBlock() == net.minecraft.init.Blocks.FARMLAND;
	}

}
