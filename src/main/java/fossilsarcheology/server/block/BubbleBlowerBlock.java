package fossilsarcheology.server.block;

import fossilsarcheology.Revival;
import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BubbleBlowerBlock extends Block implements DefaultRenderedItem {
	public static final PropertyDirection FACING = BlockHorizontal.FACING;

	public BubbleBlowerBlock() {
		super(Material.IRON);
		this.setHardness(3.0F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		setSoundType(SoundType.METAL);
		this.setUnlocalizedName("bubble_blower");
		setCreativeTab(FATabRegistry.BLOCKS);
	}

	public static void setState(boolean active, World worldIn, BlockPos pos) {
		IBlockState iblockstate = worldIn.getBlockState(pos);
		worldIn.setBlockState(pos, Blocks.FURNACE.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
		worldIn.setBlockState(pos, Blocks.FURNACE.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
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

		return this.getDefaultState().withProperty(FACING, enumfacing);
	}

	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing) state.getValue(FACING)).getIndex();
	}

	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(FACING, rot.rotate((EnumFacing) state.getValue(FACING)));
	}

	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation((EnumFacing) state.getValue(FACING)));
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[]{FACING});
	}

	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World world, BlockPos pos, Random rand) {

		super.randomDisplayTick(stateIn, world, pos, rand);
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		if (world.isBlockIndirectlyGettingPowered(pos) > 0) {
			world.playSound(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.NEUTRAL, 0.5F, rand.nextFloat() * 0.7F + 0.4F, false);
			EnumFacing enumfacing = (EnumFacing) stateIn.getValue(FACING);

			switch (enumfacing) {
				case NORTH:
					Revival.PROXY.spawnBubbleParticles(world, x + rand.nextFloat(), y + rand.nextFloat(), z, 0, 0.1, 0);
					Revival.PROXY.spawnBubbleParticles(world, x + rand.nextFloat(), y + rand.nextFloat(), z, 0, 0.1, 0);
					Revival.PROXY.spawnBubbleParticles(world, x + rand.nextFloat(), y + rand.nextFloat(), z, 0, 0.1, 0);
					Revival.PROXY.spawnBubbleParticles(world, x + rand.nextFloat(), y + rand.nextFloat(), z, 0, 0.1, 0);
					break;
				case EAST:
					Revival.PROXY.spawnBubbleParticles(world, x + 1.1F, y + rand.nextFloat(), z + rand.nextFloat(), 0, 0.1, 0);
					Revival.PROXY.spawnBubbleParticles(world, x + 1.1F, y + rand.nextFloat(), z + rand.nextFloat(), 0, 0.1, 0);
					Revival.PROXY.spawnBubbleParticles(world, x + 1.1F, y + rand.nextFloat(), z + rand.nextFloat(), 0, 0.1, 0);
					Revival.PROXY.spawnBubbleParticles(world, x + 1.1F, y + rand.nextFloat(), z + rand.nextFloat(), 0, 0.1, 0);
					break;
				case SOUTH:
					Revival.PROXY.spawnBubbleParticles(world, x + rand.nextFloat(), y + rand.nextFloat(), z + 1.1F, 0, 0.1, 0);
					Revival.PROXY.spawnBubbleParticles(world, x + rand.nextFloat(), y + rand.nextFloat(), z + 1.1F, 0, 0.1, 0);
					Revival.PROXY.spawnBubbleParticles(world, x + rand.nextFloat(), y + rand.nextFloat(), z + 1.1F, 0, 0.1, 0);
					Revival.PROXY.spawnBubbleParticles(world, x + rand.nextFloat(), y + rand.nextFloat(), z + 1.1F, 0, 0.1, 0);
					break;
				case WEST:
					Revival.PROXY.spawnBubbleParticles(world, x, y + rand.nextFloat(), z + rand.nextFloat(), 0, 0.1, 0);
					Revival.PROXY.spawnBubbleParticles(world, x, y + rand.nextFloat(), z + rand.nextFloat(), 0, 0.1, 0);
					Revival.PROXY.spawnBubbleParticles(world, x, y + rand.nextFloat(), z + rand.nextFloat(), 0, 0.1, 0);
					Revival.PROXY.spawnBubbleParticles(world, x, y + rand.nextFloat(), z + rand.nextFloat(), 0, 0.1, 0);


					break;
			}
		}
	}

}
