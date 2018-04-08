package fossilsarcheology.server.block;

import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.item.FAItemRegistry;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

import java.util.Random;

public class PermafrostBlock extends BlockBreakable implements DefaultRenderedItem {
	public PermafrostBlock() {
		super(Material.ICE, false);
		this.setTickRandomly(true);
		this.setHarvestLevel("shovel", 2);
		this.setHardness(0.5F);
		this.setLightOpacity(3);
		this.setSoundType(SoundType.GROUND);
		this.setUnlocalizedName("permafrost");
		this.setCreativeTab(FATabRegistry.BLOCKS);
	}

	@Override
	public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity entity, ItemStack stack) {
		super.harvestBlock(world, player, pos, state, entity, stack);
		//player.addStat(FossilAchievements.FIRST_FOSSIL);
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		this.spread(world, pos);
	}

	private void spread(World world, BlockPos pos) {
		if (world.getLightFor(EnumSkyBlock.BLOCK, pos) <= 11 - this.lightOpacity && (!world.canBlockSeeSky(pos.up()) || !world.isDaytime())) {
			Random random = new Random();
			int runs = 0;
			while (runs < 20) {
				int offsetX = random.nextInt(3) - 1;
				int offsetY = random.nextInt(3) - 1;
				int offsetZ = random.nextInt(3) - 1;
				BlockPos offsetPos = pos.add(offsetX, offsetY, offsetZ);
				IBlockState offsetState = world.getBlockState(offsetPos);
				Block offsetBlock = offsetState.getBlock();
				if (offsetBlock != Blocks.FLOWING_WATER && offsetBlock != Blocks.WATER) {
					if (offsetBlock != Blocks.FLOWING_LAVA && offsetBlock != Blocks.LAVA && offsetBlock != Blocks.FIRE) {
						++runs;
						continue;
					}
					world.setBlockState(pos, Blocks.STONE.getDefaultState());
					return;
				}
				world.setBlockState(offsetPos, Blocks.ICE.getDefaultState());
				return;
			}
		} else {
			world.setBlockState(pos, Blocks.DIRT.getDefaultState());
		}
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		int selection = rand.nextInt(20000);
		if (selection >= 0 && selection < 4000) {
			return FAItemRegistry.FERN_SEED;
		} else {
			if (selection >= 4000 && selection < 8000) {
				return Item.getItemFromBlock(FABlockRegistry.SKULL_BLOCK);
			} else {
				if (selection >= 8000 && selection < 12000) {
					return FAItemRegistry.ICED_MEAT;
				} else {
					if (selection >= 12000 && selection < 16000) {
						return Items.BONE;
					} else {
						if (selection >= 16000 && selection < 20000) {
							return Items.BOOK;
						} else {
							return Item.getItemFromBlock(Blocks.DIRT);
						}
					}
				}
			}
		}
	}
}