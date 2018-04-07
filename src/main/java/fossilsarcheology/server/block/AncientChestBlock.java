package fossilsarcheology.server.block;

import fossilsarcheology.server.api.BlockEntity;
import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.block.entity.TileEntityAncientChest;
import fossilsarcheology.server.item.FAItemRegistry;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class AncientChestBlock extends BlockContainer implements DefaultRenderedItem, BlockEntity {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	protected static final AxisAlignedBB NORTH_CHEST_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0D, 0.9375D, 0.875D, 0.9375D);
	protected static final AxisAlignedBB SOUTH_CHEST_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.875D, 1.0D);
	protected static final AxisAlignedBB WEST_CHEST_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0625D, 0.9375D, 0.875D, 0.9375D);
	protected static final AxisAlignedBB EAST_CHEST_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 1.0D, 0.875D, 0.9375D);
	protected static final AxisAlignedBB NOT_CONNECTED_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.875D, 0.9375D);

	protected AncientChestBlock() {
		super(Material.WOOD);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.setCreativeTab(FATabRegistry.BLOCKS);
		this.setUnlocalizedName("ancient_chest");
	}

	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	public boolean isFullCube(IBlockState state) {
		return false;
	}

	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return source.getBlockState(pos.north()).getBlock() == this ? NORTH_CHEST_AABB : (source.getBlockState(pos.south()).getBlock() == this ? SOUTH_CHEST_AABB : (source.getBlockState(pos.west()).getBlock() == this ? WEST_CHEST_AABB : (source.getBlockState(pos.east()).getBlock() == this ? EAST_CHEST_AABB : NOT_CONNECTED_AABB)));
	}

	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
	}

	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing) state.getValue(FACING)).getIndex();
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntityAncientChest tile = (TileEntityAncientChest) world.getTileEntity(pos);
		if (tile.chestState == 0) {
			if (player.getHeldItem(hand) != null) {
				if (player.getHeldItem(hand).getItem() != null) {
					if (player.getHeldItem(hand).getItem() == FAItemRegistry.ANCIENT_KEY) {
						tile.chestState = 1;
						if (!player.capabilities.isCreativeMode) {
							player.getHeldItem(hand).shrink(1);
						}

						if (player.getHeldItem(hand).getCount() <= 0) {
							player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
						}

					}
				}
			}
		} else if (tile.chestState == 1) {
			tile.chestState = 2;
			tile.chestLidCounter = 1;
			world.playSound(player, pos, SoundEvents.BLOCK_CHEST_OPEN, SoundCategory.BLOCKS, 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);

		}
		return true;
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

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityAncientChest();
	}

	@Override
	public Class<? extends TileEntity> getEntity() {
		return TileEntityAncientChest.class;
	}
}
