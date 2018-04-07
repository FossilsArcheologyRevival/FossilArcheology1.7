package fossilsarcheology.server.block;

import fossilsarcheology.Revival;
import fossilsarcheology.server.ServerProxy;
import fossilsarcheology.server.api.BlockEntity;
import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.block.entity.TileEntityTimeMachine;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TimeMachineBlock extends BlockContainer implements DefaultRenderedItem, BlockEntity {
	public static final AxisAlignedBB QUARTER_BLOCK_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D);

	public TimeMachineBlock() {
		super(Material.GLASS);
		this.setLightOpacity(0);
		this.setLightLevel(0.9375F);
		this.setHardness(0.3F);
		this.setSoundType(SoundType.GLASS);
		this.setUnlocalizedName("time_machine");
		this.setCreativeTab(FATabRegistry.BLOCKS);
	}

	public boolean isFullCube(IBlockState state) {
		return false;
	}

	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}


	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return QUARTER_BLOCK_AABB;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			player.openGui(Revival.INSTANCE, ServerProxy.GUI_TIME_MACHINE, world, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntityTimeMachine tile = (TileEntityTimeMachine) world.getTileEntity(pos);
		if (tile != null) {
			for (int i = 0; i < tile.getSizeInventory(); ++i) {
				ItemStack stack = tile.getStackInSlot(i);
				if (stack != null) {
					float xOffset = world.rand.nextFloat() * 0.8F + 0.1F;
					float yOffset = world.rand.nextFloat() * 0.8F + 0.1F;
					float zOffset = world.rand.nextFloat() * 0.8F + 0.1F;
					while (stack.getCount() > 0) {
						int rand = world.rand.nextInt(21) + 10;
						if (rand > stack.getCount()) {
							rand = stack.getCount();
						}
						stack.shrink(rand);
						EntityItem entity = new EntityItem(world, pos.getX() + xOffset, pos.getY() + yOffset, pos.getZ() + zOffset, new ItemStack(stack.getItem(), rand, stack.getItemDamage()));
						if (stack.hasTagCompound()) {
							entity.getItem().setTagCompound(stack.getTagCompound().copy());
						}
						float offset = 0.05F;
						entity.motionX = world.rand.nextGaussian() * offset;
						entity.motionY = world.rand.nextGaussian() * offset + 0.2F;
						entity.motionZ = world.rand.nextGaussian() * offset;
						world.spawnEntity(entity);
					}
				}
			}
		}
		super.breakBlock(world, pos, state);
	}

	@Override
	public Class<? extends TileEntity> getEntity() {
		return TileEntityTimeMachine.class;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityTimeMachine();
	}
}
