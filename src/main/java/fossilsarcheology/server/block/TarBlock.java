package fossilsarcheology.server.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class TarBlock extends BlockFluidClassic {
	public TarBlock() {
		super(FAFluidRegistry.TAR_FLUID, FAFluidRegistry.TAR_MATERIAL);
		this.setLightOpacity(1);
		this.setUnlocalizedName("tar");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		IBlockState neighbor = blockAccess.getBlockState(pos.offset(side));
		return !(neighbor.getMaterial() == blockState.getMaterial() || (!neighbor.isOpaqueCube() && neighbor.getBlock() != Blocks.AIR)) && (side == EnumFacing.UP || neighbor.getBlock() instanceof BlockChest || super.shouldSideBeRendered(blockState, blockAccess, pos, side));
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	@Override
	public boolean displaceIfPossible(World world, BlockPos pos) {
		if (world.isAirBlock(pos)) {
			return true;
		}
		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();
		if (block == this) {
			return false;
		}
		if (this.displacements.containsKey(block)) {
			if (this.displacements.get(block)) {
				block.dropBlockAsItem(world, pos, state, 0);
				return true;
			}
			return false;
		}
		Material material = state.getMaterial();
		if (material.blocksMovement() || material == Material.WATER || material == Material.PORTAL) {
			return false;
		}
		int density = getDensity(world, pos);
		if (density == Integer.MAX_VALUE) {
			block.dropBlockAsItem(world, pos, state, 0);
			return true;
		}
		return this.density > density;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		if ((world.getBlockState(new BlockPos(entity).down()).getMaterial() == Material.WATER || world.getBlockState(new BlockPos(entity).down()).getMaterial() == Material.CORAL) && world.getBlockState(pos.down()).getMaterial() == Material.WATER && entity.getRidingEntity() == null) {
			if (entity instanceof EntityLivingBase) {
				EntityLivingBase living = (EntityLivingBase) entity;
				living.motionX *= 0.8;
				living.motionZ *= 0.8;
			}
		}
	}


}
