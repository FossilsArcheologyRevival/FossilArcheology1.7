package fossilsarcheology.server.block;

import fossilsarcheology.Revival;
import fossilsarcheology.server.ServerProxy;
import fossilsarcheology.server.api.BlockEntity;
import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.block.entity.TileEntityCultivate;
import fossilsarcheology.server.entity.monster.EntityFailuresaurus;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class CultivateBlock extends BlockContainer implements DefaultRenderedItem, BlockEntity {

	private static boolean keepInventory = false;

	public CultivateBlock(boolean isActive) {
		super(Material.IRON);
		this.setLightLevel(0.9375F);
		this.setHardness(0.3F);
		this.setSoundType(SoundType.GLASS);
		if (isActive) {
			this.setUnlocalizedName("cultivate_active");
		} else {
			this.setUnlocalizedName("cultivate");
			this.setCreativeTab(FATabRegistry.BLOCKS);
		}
	}

	public static void setState(boolean isActive, World world, BlockPos pos) {
		TileEntity tile = world.getTileEntity(pos);
		keepInventory = true;
		if (isActive) {
			world.setBlockState(pos, FABlockRegistry.CULTIVATE_ACTIVEE.getDefaultState());
		} else {
			world.setBlockState(pos, FABlockRegistry.CULTIVATE_IDLE.getDefaultState());
		}
		keepInventory = false;
		if (tile != null) {
			tile.validate();
			world.setTileEntity(pos, tile);
		}
	}

	public void onBlockRemovalLost(World world, BlockPos pos, boolean isActive) {
		keepInventory = false;
		String var6 = I18n.format("cultivate.outBreak");

		for (int var7 = 0; var7 < world.playerEntities.size(); ++var7) {
			EntityPlayer P = (EntityPlayer) world.playerEntities.get(var7);

			if (Math.pow(pos.getX() - P.posX, 2D) + Math.pow(pos.getY() - P.posY, 2D) + Math.pow(pos.getZ() - P.posZ, 2D) < 10000) {
				// P.addStat(FossilAchievements.FAILURESAURUS, 1);
				P.sendStatusMessage(new TextComponentString(var6), false);
			}
		}

		this.returnIron(world, pos);
		this.returnDNA(world, pos);
		if (!world.isRemote) {
			if (isActive) {
				TileEntityCultivate tileentity = (TileEntityCultivate) world.getTileEntity(pos);
				if (tileentity != null) {

					if (tileentity.getDNAType() == 2 || tileentity.getDNAType() == 3) {
						world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS, 1, 1, false);
						world.setBlockState(pos.up(), FABlockRegistry.MUTANT_FLOWER.getDefaultState());
						world.setBlockState(pos.up(2), FABlockRegistry.MUTANT_FLOWER.getDefaultState());//TODO:set it to upper part w/ meta
						world.setBlockState(pos, Blocks.DIRT.getDefaultState());

					} else {
						Object creature = null;
						world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS, 1, 1, false);
						world.setBlockState(pos, Blocks.WATER.getDefaultState());

						if (world.isRemote) {
							return;
						}

						int rand = world.rand.nextInt(100);

						if (rand <= 5) {
							creature = new EntityCreeper(world);
						}

						if (rand > 5 && rand < 10) {
							creature = new EntityPigZombie(world);
						}

						if (rand >= 10) {
							creature = new EntityFailuresaurus(world);
							((EntityFailuresaurus) creature).setSkin(new Random().nextInt(3));
						}

						((EntityLiving) creature).setLocationAndAngles((double) pos.getX() + 0.5, (double) pos.getY() + 0.5, (double) pos.getZ() + 0.5, world.rand.nextFloat() * 360.0F, 0.0F);
						world.spawnEntity((Entity) creature);
					}
					world.removeTileEntity(pos);
				}

			}
		}
	}

	private void returnIron(World world, BlockPos pos) {
		Random rand = new Random();
		ItemStack stack = new ItemStack(Items.IRON_INGOT, 3);
		float offsetX = rand.nextFloat() * 0.8F + 0.1F;
		float offsetY = rand.nextFloat() * 0.8F + 0.1F;
		float offsetZ = rand.nextFloat() * 0.8F + 0.1F;

		while (stack.getCount() > 0) {
			int stackDecay = rand.nextInt(21) + 10;

			if (stackDecay > stack.getCount()) {
				stackDecay = stack.getCount();
			}

			stack.shrink(stackDecay);
			EntityItem item = new EntityItem(world, (double) ((float) pos.getX() + offsetX), (double) ((float) pos.getY() + offsetY), (double) ((float) pos.getZ() + offsetZ), new ItemStack(stack.getItem(), stackDecay, stack.getItemDamage()));
			float motionMutlipler = 0.05F;
			item.motionX = (double) ((float) rand.nextGaussian() * motionMutlipler);
			item.motionY = (double) ((float) rand.nextGaussian() * motionMutlipler + 0.2F);
			item.motionZ = (double) ((float) rand.nextGaussian() * motionMutlipler);
			world.spawnEntity(item);
		}
	}

	private void returnDNA(World world, BlockPos pos) {
		Random rand = new Random();
		if (!keepInventory) {
			TileEntityCultivate tile = (TileEntityCultivate) world.getTileEntity(pos);
			if (tile != null) {
				ItemStack stack = tile.getStackInSlot(0);
				float offsetX = rand.nextFloat() * 0.8F + 0.1F;
				float offsetY = rand.nextFloat() * 0.8F + 0.1F;
				float offsetZ = rand.nextFloat() * 0.8F + 0.1F;

				while (stack.getCount() > 0) {
					int stackDecay = rand.nextInt(21) + 10;

					if (stackDecay > stack.getCount()) {
						stackDecay = stack.getCount();
					}

					stack.shrink(stackDecay);
					EntityItem item = new EntityItem(world, (double) ((float) pos.getX() + offsetX), (double) ((float) pos.getY() + offsetY), (double) ((float) pos.getZ() + offsetZ), new ItemStack(stack.getItem(), stackDecay, stack.getItemDamage()));
					float motionMutlipler = 0.05F;
					item.motionX = (double) ((float) rand.nextGaussian() * motionMutlipler);
					item.motionY = (double) ((float) rand.nextGaussian() * motionMutlipler + 0.2F);
					item.motionZ = (double) ((float) rand.nextGaussian() * motionMutlipler);
					world.spawnEntity(item);
				}
			}
		}
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random random, int fortune) {
		return Item.getItemFromBlock(FABlockRegistry.CULTIVATE_IDLE);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			player.openGui(Revival.INSTANCE, ServerProxy.GUI_CULTIVATE, world, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		if (stack.hasDisplayName()) {
			TileEntity tile = world.getTileEntity(pos);
			if (tile instanceof TileEntityCultivate) {
				((TileEntityCultivate) tile).setCustomName(stack.getDisplayName());
			}
		}
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		if (!keepInventory) {
			TileEntityCultivate tile = (TileEntityCultivate) world.getTileEntity(pos);
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
		return new TileEntityCultivate();
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
		return new ItemStack(FABlockRegistry.CULTIVATE_IDLE);
	}

	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public Class<? extends TileEntity> getEntity() {
		return TileEntityCultivate.class;
	}

}
