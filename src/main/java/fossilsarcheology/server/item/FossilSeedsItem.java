package fossilsarcheology.server.item;

import fossilsarcheology.server.block.FABlockRegistry;
import fossilsarcheology.server.block.TempskyaBlock;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class FossilSeedsItem extends Item {
	public static final String[] fossilSeeds = new String[]{"dillhoffia", "sarracina", "cephalotaxus", "licopodiophyta", "paleopanax", "zamites", "bennettitales", "welwitschia", "horsetail", "tempskya", "vaccinium", "osmunda", "crataegus", "florissantia", "ephedra"};
	public boolean isFossil;

	public FossilSeedsItem(boolean isFossil) {
		super();
		this.setHasSubtypes(true);
		this.isFossil = isFossil;
		this.setCreativeTab(FATabRegistry.ITEMS);
		this.setUnlocalizedName(isFossil ? "fossilseed" : "seed");
	}

	@Override
	public void getSubItems(CreativeTabs creativeTabs, NonNullList<ItemStack> list) {
		if (creativeTabs == FATabRegistry.ITEMS) {
			for (int i = 0; i < fossilSeeds.length; ++i) {
				list.add(new ItemStack(this, 1, i));
			}
		}
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItem(hand);
		if (!isFossil && player.canPlayerEdit(pos, facing, stack) && player.canPlayerEdit(pos.up(), facing, stack)) {
			if (canPlant(world.getBlockState(pos)) && world.isAirBlock(pos.up()) && (world.getBlockState(pos).getBlock() != FABlockRegistry.WELWITSCHIA_FLOWER)) {
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

	public boolean canPlant(IBlockState state) {
		return state.getBlock() == net.minecraft.init.Blocks.GRASS || state.getBlock() == net.minecraft.init.Blocks.DIRT || state.getBlock() == net.minecraft.init.Blocks.FARMLAND;
	}

	private boolean placePlantBlock(ItemStack stack, World world, int x, int y, int z, Random rand) {
		switch (stack.getItemDamage()) {
			case 0:
				world.setBlockState(new BlockPos(x, y + 1, z), FABlockRegistry.DILLHOFFIA_FLOWER.getDefaultState());
				return true;
			case 1:
				world.setBlockState(new BlockPos(x, y + 1, z), FABlockRegistry.SARRACENIA_FLOWER.getDefaultState());
				world.setBlockState(new BlockPos(x, y + 2, z), FABlockRegistry.SARRACENIA_FLOWER.getDefaultState());
				return true;
			case 2:
				world.setBlockState(new BlockPos(x, y + 1, z), FABlockRegistry.CEPHALOTAXUS_FLOWER.getDefaultState());
				return true;
			case 3:
				world.setBlockState(new BlockPos(x, y + 1, z), FABlockRegistry.LICOPODIOPHYTA_FLOWER.getDefaultState());
				return true;
			case 4:
				world.setBlockState(new BlockPos(x, y + 1, z), FABlockRegistry.PALEOPANAX_FLOWER.getDefaultState());
				world.setBlockState(new BlockPos(x, y + 2, z), FABlockRegistry.PALEOPANAX_FLOWER.getDefaultState());
				return true;
			case 5:
				world.setBlockState(new BlockPos(x, y + 1, z), FABlockRegistry.ZAMITES_FLOWER.getDefaultState());
				return true;
			case 6:
				world.setBlockState(new BlockPos(x, y + 1, z), FABlockRegistry.BENNETTITALES_SMALL_FLOWER.getDefaultState());
				return true;
			case 7:
				world.setBlockState(new BlockPos(x, y + 1, z), FABlockRegistry.WELWITSCHIA_FLOWER.getDefaultState());
				return true;
			case 8:
				world.setBlockState(new BlockPos(x, y + 1, z), FABlockRegistry.HORSETAIL_SMALL_FLOWER.getDefaultState());
				return true;
			case 9:
				if (((TempskyaBlock) FABlockRegistry.TEMPSKYA_FLOWER).canPlaceBlockAt(world, new BlockPos(x, y + 1, z))) {
					world.setBlockState(new BlockPos(x, y + 1, z), FABlockRegistry.TEMPSKYA_FLOWER.getDefaultState().withProperty(TempskyaBlock.LAYER, 0), 2);
					world.setBlockState(new BlockPos(x, y + 2, z), FABlockRegistry.TEMPSKYA_FLOWER.getDefaultState().withProperty(TempskyaBlock.LAYER, 1), 2);
					world.setBlockState(new BlockPos(x, y + 3, z), FABlockRegistry.TEMPSKYA_FLOWER.getDefaultState().withProperty(TempskyaBlock.LAYER, 2), 2);
					world.setBlockState(new BlockPos(x, y + 4, z), FABlockRegistry.TEMPSKYA_FLOWER.getDefaultState().withProperty(TempskyaBlock.LAYER, 3), 2);
					return true;
				}
			case 10:
				world.setBlockState(new BlockPos(x, y + 1, z), FABlockRegistry.VACCINIUM_FLOWER.getDefaultState());
				return true;
			case 11:
				world.setBlockState(new BlockPos(x, y + 1, z), FABlockRegistry.OSMUNDA_FLOWER.getDefaultState());
				return true;
			case 12:
				world.setBlockState(new BlockPos(x, y + 1, z), FABlockRegistry.CRATAEGUS_FLOWER.getDefaultState());
				world.setBlockState(new BlockPos(x, y + 2, z), FABlockRegistry.CRATAEGUS_FLOWER.getDefaultState());
				return true;
			case 13:
				world.setBlockState(new BlockPos(x, y + 1, z), FABlockRegistry.FLORISSANTIA_FLOWER.getDefaultState());
				return true;
			case 14:
				world.setBlockState(new BlockPos(x, y + 1, z), FABlockRegistry.EPENDRA_FLOWER.getDefaultState());
				return true;
		}
		return false;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		int meta = itemStack.getItemDamage();
		if (meta < 0 || meta >= fossilSeeds.length) {
			meta = 0;
		}

		return super.getUnlocalizedName() + "_" + fossilSeeds[meta];
	}
}
