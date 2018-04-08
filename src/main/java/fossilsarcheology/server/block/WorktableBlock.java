package fossilsarcheology.server.block;

import fossilsarcheology.Revival;
import fossilsarcheology.server.ServerProxy;
import fossilsarcheology.server.api.BlockEntity;
import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.block.entity.TileEntityWorktable;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class WorktableBlock extends BlockContainer implements DefaultRenderedItem, BlockEntity {

<<<<<<< HEAD
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	private static boolean keepInventory = false;
	private final boolean isActive;

	public WorktableBlock(boolean isActive) {
		super(Material.WOOD);
		this.setHarvestLevel("axe", 0);
		this.setHardness(2.5F);
		this.setSoundType(SoundType.WOOD);
		this.isActive = isActive;
		if (isActive) {
			setUnlocalizedName("worktableActive");
		} else {
			setUnlocalizedName("worktable");
			setCreativeTab(FATabRegistry.BLOCKS);
		}
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}

	public static void setState(boolean isActive, World world, BlockPos pos) {
		TileEntity tile = world.getTileEntity(pos);
		keepInventory = true;
		if (isActive) {
			world.setBlockState(pos, FABlockRegistry.WORKTABLE_ACTIVE.getDefaultState());
		} else {
			world.setBlockState(pos, FABlockRegistry.WORKTABLE_IDLE.getDefaultState());
		}
		keepInventory = false;
		if (tile != null) {
			tile.validate();
			world.setTileEntity(pos, tile);
		}
	}

	@Override
	public Item getItemDropped(IBlockState state, Random random, int fortune) {
		return Item.getItemFromBlock(FABlockRegistry.WORKTABLE_IDLE);
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		super.onBlockAdded(world, pos, state);
		this.setDefaultFacing(world, pos, state);
	}

	private void setDefaultFacing(World world, BlockPos pos, IBlockState state) {
		if (!world.isRemote) {
			IBlockState north = world.getBlockState(pos.north());
			IBlockState south = world.getBlockState(pos.south());
			IBlockState west = world.getBlockState(pos.west());
			IBlockState east = world.getBlockState(pos.east());
			EnumFacing facing = state.getValue(FACING);
			if (facing == EnumFacing.NORTH && north.isFullBlock() && !south.isFullBlock()) {
				facing = EnumFacing.SOUTH;
			} else if (facing == EnumFacing.SOUTH && south.isFullBlock() && !north.isFullBlock()) {
				facing = EnumFacing.NORTH;
			} else if (facing == EnumFacing.WEST && west.isFullBlock() && !east.isFullBlock()) {
				facing = EnumFacing.EAST;
			} else if (facing == EnumFacing.EAST && east.isFullBlock() && !west.isFullBlock()) {
				facing = EnumFacing.WEST;
			}
			world.setBlockState(pos, state.withProperty(FACING, facing), 2);
		}
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			player.openGui(Revival.INSTANCE, ServerProxy.GUI_WORKTABLE, world, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}


	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		world.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		if (!keepInventory) {
			TileEntityWorktable tile = (TileEntityWorktable) world.getTileEntity(pos);
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
		return new TileEntityWorktable();
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean hasComparatorInputOverride(IBlockState state) {
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public int getComparatorInputOverride(IBlockState state, World world, BlockPos pos) {
		return Container.calcRedstoneFromInventory((IInventory) world.getTileEntity(pos));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(FABlockRegistry.WORKTABLE_IDLE);
	}

	@SuppressWarnings("deprecation")
	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing facing = EnumFacing.getFront(meta);
		if (facing.getAxis() == EnumFacing.Axis.Y) {
			facing = EnumFacing.NORTH;
		}
		return this.getDefaultState().withProperty(FACING, facing);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getIndex();
	}

	@SuppressWarnings("deprecation")
	@Override
	public IBlockState withRotation(IBlockState state, Rotation rotation) {
		return state.withProperty(FACING, rotation.rotate(state.getValue(FACING)));
	}

	@SuppressWarnings("deprecation")
	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirror) {
		return state.withRotation(mirror.toRotation(state.getValue(FACING)));
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING);
	}

	@Override
	public Class<? extends TileEntity> getEntity() {
		return TileEntityWorktable.class;
	}
=======
    private static boolean keepInventory = false;
    private final boolean isActive;
    public static final PropertyDirection FACING = BlockHorizontal.FACING;

    public WorktableBlock(boolean isActive) {
        super(Material.WOOD);
        this.setHarvestLevel("axe", 0);
        this.setHardness(2.5F);
        this.setSoundType(SoundType.WOOD);
        this.isActive = isActive;
        if (isActive) {
            setUnlocalizedName("worktableActive");
        } else {
            setUnlocalizedName("worktable");
            setCreativeTab(FATabRegistry.BLOCKS);
        }
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
    }

    public static void setState(boolean isActive, World world, BlockPos pos) {
        TileEntity tile = world.getTileEntity(pos);
        keepInventory = true;
        if (isActive) {
            world.setBlockState(pos, FABlockRegistry.WORKTABLE_ACTIVE.getDefaultState());
        } else {
            world.setBlockState(pos, FABlockRegistry.WORKTABLE_IDLE.getDefaultState());
        }
        keepInventory = false;
        if (tile != null) {
            tile.validate();
            world.setTileEntity(pos, tile);
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random random, int fortune) {
        return Item.getItemFromBlock(FABlockRegistry.WORKTABLE_IDLE);
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
        super.onBlockAdded(world, pos, state);
        this.setDefaultFacing(world, pos, state);
    }

    private void setDefaultFacing(World world, BlockPos pos, IBlockState state) {
        if (!world.isRemote) {
            IBlockState north = world.getBlockState(pos.north());
            IBlockState south = world.getBlockState(pos.south());
            IBlockState west = world.getBlockState(pos.west());
            IBlockState east = world.getBlockState(pos.east());
            EnumFacing facing = state.getValue(FACING);
            if (facing == EnumFacing.NORTH && north.isFullBlock() && !south.isFullBlock()) {
                facing = EnumFacing.SOUTH;
            } else if (facing == EnumFacing.SOUTH && south.isFullBlock() && !north.isFullBlock()) {
                facing = EnumFacing.NORTH;
            } else if (facing == EnumFacing.WEST && west.isFullBlock() && !east.isFullBlock()) {
                facing = EnumFacing.EAST;
            } else if (facing == EnumFacing.EAST && east.isFullBlock() && !west.isFullBlock()) {
                facing = EnumFacing.WEST;
            }
            world.setBlockState(pos, state.withProperty(FACING, facing), 2);
        }
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            player.openGui(Revival.INSTANCE, ServerProxy.GUI_WORKTABLE, world, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }


    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        world.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        if (!keepInventory) {
            TileEntityWorktable tile = (TileEntityWorktable) world.getTileEntity(pos);
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
        return new TileEntityWorktable();
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
        return new ItemStack(FABlockRegistry.WORKTABLE_IDLE);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing facing = EnumFacing.getFront(meta);
        if (facing.getAxis() == EnumFacing.Axis.Y) {
            facing = EnumFacing.NORTH;
        }
        return this.getDefaultState().withProperty(FACING, facing);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rotation) {
        return state.withProperty(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirror) {
        return state.withRotation(mirror.toRotation(state.getValue(FACING)));
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    @Override
    public Class<? extends TileEntity> getEntity() {
        return TileEntityWorktable.class;
    }
>>>>>>> parent of 9902da5f... why are there two build.gradle files?
}
