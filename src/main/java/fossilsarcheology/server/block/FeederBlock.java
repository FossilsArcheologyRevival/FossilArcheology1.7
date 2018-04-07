package fossilsarcheology.server.block;


import fossilsarcheology.Revival;
import fossilsarcheology.server.ServerProxy;
import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.block.entity.TileEntityFeeder;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FeederBlock extends BlockContainer implements DefaultRenderedItem {
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public static final PropertyInteger FOOD = PropertyInteger.create("food", 0, 3);
	private static final int NO_BIT = 0;
	private static final int HERB_BIT = 4;
	private static final int CARN_BIT = 8;
	private static final int BOTH_BITS = 12;
	private static final int DIRECTION_BITS = 3;

	protected FeederBlock() {
		super(Material.IRON);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(FOOD, 0));
		this.setSoundType(SoundType.METAL);
		this.setUnlocalizedName("feeder");
		this.setCreativeTab(FATabRegistry.BLOCKS);
		this.setHardness(3);

	}

	public static void updateFeederBlockState(boolean herb, boolean carn, World world, BlockPos pos) {
		int meta = world.getBlockState(pos).getBlock().getMetaFromState(world.getBlockState(pos));
		TileEntity tileentity = world.getTileEntity(pos);
		// world.setBlock(x, y, z, FABlockRegistry.INSTANCE.feederActive);
		if (herb) { // If there's VEGGIES{
			meta |= HERB_BIT;
		} else {
			meta &= ~HERB_BIT;
		}
		if (carn) {
			meta |= CARN_BIT;
		} else {
			meta &= ~CARN_BIT;
		}

		world.setBlockState(pos, world.getBlockState(pos).getBlock().getStateFromMeta(meta));

		if (tileentity != null) {
			world.setTileEntity(pos, tileentity);
		}
	}

	public static void setState(boolean active, World worldIn, BlockPos pos) {
		IBlockState iblockstate = worldIn.getBlockState(pos);
		worldIn.setBlockState(pos, FABlockRegistry.FEEDER.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)).withProperty(FOOD, iblockstate.getValue(FOOD)), 3);
		worldIn.setBlockState(pos, FABlockRegistry.FEEDER.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)).withProperty(FOOD, iblockstate.getValue(FOOD)), 3);
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			player.openGui(Revival.INSTANCE, ServerProxy.GUI_FEEDER, world, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}

	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		this.setDefaultFacing(worldIn, pos, state);
	}

	private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state) {
		if (!worldIn.isRemote) {
			IBlockState iblockstate = worldIn.getBlockState(pos.north());
			IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
			IBlockState iblockstate2 = worldIn.getBlockState(pos.west());
			IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
			EnumFacing enumfacing = (EnumFacing) state.getValue(FACING);

			if (enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock() && !iblockstate1.isFullBlock()) {
				enumfacing = EnumFacing.SOUTH;
			} else if (enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock() && !iblockstate.isFullBlock()) {
				enumfacing = EnumFacing.NORTH;
			} else if (enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock() && !iblockstate3.isFullBlock()) {
				enumfacing = EnumFacing.EAST;
			} else if (enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock() && !iblockstate2.isFullBlock()) {
				enumfacing = EnumFacing.WEST;
			}

			worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
		}
	}

	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
	}

	public IBlockState getStateFromMeta(int meta) {
		EnumFacing enumfacing = EnumFacing.getFront(meta);

		if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
			enumfacing = EnumFacing.NORTH;
		}

		return this.getDefaultState().withProperty(FACING, enumfacing).withProperty(FOOD, meta % 6);
	}

	public int getMetaFromState(IBlockState state) {
		int i = ((EnumFacing) state.getValue(FACING)).getIndex();
		return i |= 3;
	}

	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(FACING, rot.rotate((EnumFacing) state.getValue(FACING)));
	}

	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation((EnumFacing) state.getValue(FACING)));
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[]{FACING, FOOD});
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityFeeder();
	}
}
