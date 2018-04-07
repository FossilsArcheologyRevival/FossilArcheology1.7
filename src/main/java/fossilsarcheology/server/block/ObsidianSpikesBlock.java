package fossilsarcheology.server.block;

import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.entity.monster.EntityAnu;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ObsidianSpikesBlock extends Block implements DefaultRenderedItem {

	protected static final AxisAlignedBB SPIKES_AABB = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);


	public ObsidianSpikesBlock() {
		super(Material.ROCK);
		this.setHarvestLevel("pickaxe", 3);
		this.setCreativeTab(FATabRegistry.BLOCKS);
		this.setHardness(50.0F);
		this.setResistance(2000.0F);
		this.setSoundType(SoundType.STONE);
		this.setUnlocalizedName("obsidian_spikes");
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return SPIKES_AABB;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return super.canPlaceBlockAt(worldIn, pos) ? this.canBlockStay(worldIn, pos) : false;
	}

	private boolean canBlockStay(World worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos.down()).getMaterial().isSolid();
	}

	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
		if (!this.canBlockStay(worldIn, pos)) {
			worldIn.setBlockToAir(pos);
		}
	}

	/**
	 * Triggered whenever an entity collides with this block (enters into the
	 * block). Args: world, x, y, z, entity
	 */
	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		if (!(entity instanceof EntityAnu)) {
			if (entity instanceof EntityLivingBase) {
				double var4 = pos.getX() - entity.posX;
				double var5 = pos.getZ() - entity.posZ;

				entity.attackEntityFrom(DamageSource.CACTUS, 1.0F);
				if ((entity instanceof EntityPlayer) && !((EntityPlayer) entity).capabilities.isCreativeMode) {
					((EntityLivingBase) entity).knockBack(entity, 0, var4 * 5.0D, var5 * 5.0D);
				}
				if (!world.isRemote && !(entity instanceof EntityPlayer)) {
					((EntityLivingBase) entity).knockBack(entity, 0, var4 * 5.0D, var5 * 5.0D);
				}
			}
		}
	}

}
