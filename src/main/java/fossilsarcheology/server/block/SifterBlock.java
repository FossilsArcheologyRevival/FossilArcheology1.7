package fossilsarcheology.server.block;

import fossilsarcheology.Revival;
import fossilsarcheology.server.ServerProxy;
import fossilsarcheology.server.api.BlockEntity;
import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.block.entity.TileEntitySifter;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class SifterBlock extends BlockContainer implements DefaultRenderedItem, BlockEntity {
	private static boolean keepInventory = false;
	private final boolean isActive;

	public SifterBlock(boolean isActive) {
		super(Material.WOOD);
		this.setHarvestLevel("axe", 0);
		this.setHardness(2.5F);
		this.setSoundType(SoundType.METAL);
		this.isActive = isActive;
		if (isActive) {
			setUnlocalizedName("sifterActive");
		} else {
			setUnlocalizedName("sifter");
			setCreativeTab(FATabRegistry.BLOCKS);
		}
	}

	public static void setState(boolean isActive, World world, BlockPos pos) {
		TileEntity tile = world.getTileEntity(pos);
		keepInventory = true;
		if (isActive) {
			world.setBlockState(pos, FABlockRegistry.SIFTER_ACTIVE.getDefaultState());
		} else {
			world.setBlockState(pos, FABlockRegistry.SIFTER_IDLE.getDefaultState());
		}
		keepInventory = false;
		if (tile != null) {
			tile.validate();
			world.setTileEntity(pos, tile);
		}
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random random, int fortune) {
		return Item.getItemFromBlock(FABlockRegistry.SIFTER_IDLE);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			player.openGui(Revival.INSTANCE, ServerProxy.GUI_SIFTER, world, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		if (!keepInventory) {
			TileEntitySifter tile = (TileEntitySifter) world.getTileEntity(pos);
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
		}
		super.breakBlock(world, pos, state);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntitySifter();
	}

	@Override
	public boolean hasComparatorInputOverride(IBlockState state) {
		return true;
	}

	@Override
	public int getComparatorInputOverride(IBlockState state, World world, BlockPos pos) {
		return Container.calcRedstoneFromInventory((IInventory) world.getTileEntity(pos));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(FABlockRegistry.SIFTER_IDLE);
	}

	@Override
	public Class<? extends TileEntity> getEntity() {
		return TileEntitySifter.class;
	}
}
