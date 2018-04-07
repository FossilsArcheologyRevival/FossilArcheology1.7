package fossilsarcheology.server.block;

import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class TempskyaBlock extends BlockBush implements DefaultRenderedItem {

	public static final PropertyInteger LAYER = PropertyInteger.create("layer", 0, 3);

	public TempskyaBlock() {
		super(Material.PLANTS);
		this.setDefaultState(this.blockState.getBaseState().withProperty(LAYER, Integer.valueOf(0)));
		this.setTickRandomly(true);
		this.setUnlocalizedName("tempskya");
		this.setSoundType(SoundType.PLANT);
		this.setCreativeTab(FATabRegistry.BLOCKS);
		this.setHardness(0.5F);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.30000001192092896D, 0.0D, 0.30000001192092896D, 0.699999988079071D, 1D, 0.699999988079071D);
	}

	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
		if (state.getBlock() == this && state.getValue(LAYER) == 0) {
			IBlockState soil = worldIn.getBlockState(pos.down());
			return soil.getBlock().canSustainPlant(soil, worldIn, pos.down(), net.minecraft.util.EnumFacing.UP, this);
		}
		if (state.getBlock() == this && state.getValue(LAYER) != 0) {
			IBlockState below = worldIn.getBlockState(pos.down());
			return below.getBlock() == this;
		}
		return this.canSustainBush(worldIn.getBlockState(pos.down()));
	}


	@Override
	protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state) {
		if (!this.canBlockStay(worldIn, pos, state)) {
			if (state.getValue(LAYER) == 0) {
				this.dropBlockAsItem(worldIn, pos, state, 0);
			}
			worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
		}
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return super.canPlaceBlockAt(worldIn, pos) && worldIn.isAirBlock(pos.up()) && worldIn.isAirBlock(pos.up(2)) && worldIn.isAirBlock(pos.up(3));
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		worldIn.setBlockState(pos, this.getDefaultState().withProperty(LAYER, 0), 2);
		worldIn.setBlockState(pos.up(), this.getDefaultState().withProperty(LAYER, 1), 2);
		worldIn.setBlockState(pos.up(2), this.getDefaultState().withProperty(LAYER, 2), 2);
		worldIn.setBlockState(pos.up(3), this.getDefaultState().withProperty(LAYER, 3), 2);
	}

	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
		if (state.getValue(LAYER) == 0) {
			worldIn.destroyBlock(pos, true);
			for (int i = 1; i < 3; i++) {
				if (worldIn.getBlockState(pos.up(i)).getBlock() == this) {
					worldIn.destroyBlock(pos.up(i), false);
				}
			}
		}
		super.onBlockHarvested(worldIn, pos, state, player);
	}


	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(LAYER, Integer.valueOf(meta));
	}

	public int getMetaFromState(IBlockState state) {
		return ((Integer) state.getValue(LAYER)).intValue();
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[]{LAYER});
	}

}
