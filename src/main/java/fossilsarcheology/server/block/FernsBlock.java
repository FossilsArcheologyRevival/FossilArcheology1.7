package fossilsarcheology.server.block;

import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.item.FAItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class FernsBlock extends BlockBush implements DefaultRenderedItem {

<<<<<<< HEAD
	public static final PropertyInteger GROWTH = PropertyInteger.create("growth", 0, 7);
	public static final PropertyInteger TYPE = PropertyInteger.create("type", 0, 1);
	protected static final AxisAlignedBB FERN_AABB = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);

	public FernsBlock() {
		super(Material.PLANTS);
		this.setTickRandomly(true);
		this.disableStats();
		this.setUnlocalizedName("fernBlock");
		this.setHardness(0.0F);
		this.setSoundType(SoundType.PLANT);
		this.setCreativeTab(null);
		this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, Integer.valueOf(0)).withProperty(GROWTH, Integer.valueOf(0)));

	}

	public static boolean checkUnderTree(World var0, BlockPos pos) {
		for (int var4 = pos.getY(); var4 <= 128; ++var4) {
			if (var0.getBlockState(pos.up(var4)).getMaterial() == Material.LEAVES) {
				return true;
			}
		}
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return FERN_AABB;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	public boolean isFullCube(IBlockState state) {
		return false;
	}

	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(world, pos, state, rand);
		IBlockState currentState = world.getBlockState(pos);
		int var6 = currentState.getBlock().getMetaFromState(currentState);

		if (checkUnderTree(world, pos) && this.canGrow(var6)) {
			if (rand.nextInt(10) > 1) {
				++var6;
				if (this.getLv2(var6)) {
					if (!world.isAirBlock(pos.up())) {
						--var6;
					} else {
						world.setBlockState(pos.up(), FABlockRegistry.FERNS.getStateFromMeta(this.checkSubType(var6) == 0 ? var6 + 2 : var6 + 1));
					}
				} else if (this.hasLv2(var6)) {
					if (world.getBlockState(pos.up()).getBlock() == FABlockRegistry.FERNS) {
						world.setBlockState(pos.up(), FABlockRegistry.FERNS.getStateFromMeta(this.checkSubType(var6) == 0 ? var6 + 2 : var6 + 1));
					} else {
						var6 = this.checkSubType(var6) == 0 ? 3 : 10;
					}
				}
				world.setBlockState(pos, currentState.getBlock().getStateFromMeta(var6));
			}

		}

		if (var6 % 7 >= 3) {
			for (int var8 = -1; var8 <= 1; ++var8) {
				for (int var9 = -1; var9 <= 1; ++var9) {
					for (int var10 = -1; var10 <= 1; ++var10) {
						if ((var8 != 0 || var10 != 0 || var9 != 0) && world.getBlockState(pos.add(var8, var9 - 1, var10)).getBlock() == Blocks.GRASS && (world.isAirBlock(pos.add(var8, var9, var10)) || world.getBlockState(pos.add(var8, var9, var10)) == Blocks.TALLGRASS) && checkUnderTree(world, pos.add(var8, var9, var10)) && (new Random()).nextInt(10) <= 9) {
							world.setBlockState(pos.add(var8, var9, var10), FABlockRegistry.FERNS.getStateFromMeta(8 * this.checkSubType(var6)));
						}
					}
				}
			}
		}
	}

	public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
		boolean var5 = true;
		IBlockState currentState = world.getBlockState(pos);
		int meta = currentState.getBlock().getMetaFromState(currentState);

		if (this.checkLevel(meta)) {
			return world.getBlockState(pos.down()).getBlock() == FABlockRegistry.FERNS;
		} else {
			var5 = world.getBlockState(pos.down()).getBlock() == Blocks.GRASS && checkUnderTree(world, pos);
			if (this.hasLv2(meta)) {
				var5 &= world.getBlockState(pos.down()).getBlock() == FABlockRegistry.FERNS;
			}

			return var5;
		}
	}

	public boolean checkLevel(int meta) {
		return (meta >= 6 && meta <= 7) || meta == 12;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		if (rand.nextInt(4) == 0) {
			return FAItemRegistry.FERN_SEED;
		}
		return null;
	}

	public int checkSubType(int meta) {
		return meta > 7 ? 1 : 0;
	}

	protected boolean canPlaceBlockOn(Block block) {
		return block == Blocks.GRASS || block == Blocks.DIRT;
	}

	public boolean canGrow(int meta) {
		return (meta >= 0 && meta < 5) || (meta >= 8 && meta < 11);
	}

	public boolean hasLv2(int meta) {
		return (meta >= 4 && meta <= 5) || (meta >= 11 && meta <= 11);
	}

	public boolean getLv2(int meta) {
		return (meta == 4) || (meta == 11);
	}

	@SuppressWarnings("deprecation")
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(TYPE, Integer.valueOf(meta / 8)).withProperty(GROWTH, Integer.valueOf((meta < 8 ? meta : meta - 8)));
	}

	public int getMetaFromState(IBlockState state) {
		return ((Integer) state.getValue(TYPE)).intValue() == 0 ? ((Integer) state.getValue(GROWTH)).intValue() : ((Integer) state.getValue(GROWTH)).intValue() + 7;
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[]{TYPE, GROWTH});
	}
=======
    protected static final AxisAlignedBB FERN_AABB = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
    public static final PropertyInteger GROWTH = PropertyInteger.create("growth", 0, 7);
    public static final PropertyInteger TYPE = PropertyInteger.create("type", 0, 1);

    public FernsBlock() {
        super(Material.PLANTS);
        this.setTickRandomly(true);
        this.disableStats();
        this.setUnlocalizedName("fernBlock");
        this.setHardness(0.0F);
        this.setSoundType(SoundType.PLANT);
        this.setCreativeTab(null);
        this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, Integer.valueOf(0)).withProperty(GROWTH, Integer.valueOf(0)));

    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return FERN_AABB;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }

    public static boolean checkUnderTree(World var0, BlockPos pos) {
        for (int var4 = pos.getY(); var4 <= 128; ++var4) {
            if (var0.getBlockState(pos.up(var4)).getMaterial() == Material.LEAVES) {
                return true;
            }
        }
        return false;
    }

    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
        super.updateTick(world, pos, state, rand);
        IBlockState currentState = world.getBlockState(pos);
        int var6 = currentState.getBlock().getMetaFromState(currentState);

        if (checkUnderTree(world, pos) && this.canGrow(var6)) {
            if (rand.nextInt(10) > 1) {
                ++var6;
                if (this.getLv2(var6)) {
                    if (!world.isAirBlock(pos.up())) {
                        --var6;
                    } else {
                        world.setBlockState(pos.up(), FABlockRegistry.FERNS.getStateFromMeta(this.checkSubType(var6) == 0 ? var6 + 2 : var6 + 1));
                    }
                } else if (this.hasLv2(var6)) {
                    if (world.getBlockState(pos.up()).getBlock() == FABlockRegistry.FERNS) {
                        world.setBlockState(pos.up(), FABlockRegistry.FERNS.getStateFromMeta(this.checkSubType(var6) == 0 ? var6 + 2 : var6 + 1));
                    } else {
                        var6 = this.checkSubType(var6) == 0 ? 3 : 10;
                    }
                }
                world.setBlockState(pos, currentState.getBlock().getStateFromMeta(var6));
            }

        }

        if (var6 % 7 >= 3) {
            for (int var8 = -1; var8 <= 1; ++var8) {
                for (int var9 = -1; var9 <= 1; ++var9) {
                    for (int var10 = -1; var10 <= 1; ++var10) {
                        if ((var8 != 0 || var10 != 0 || var9 != 0) && world.getBlockState(pos.add(var8, var9 - 1, var10)).getBlock() == Blocks.GRASS && (world.isAirBlock(pos.add(var8, var9, var10)) || world.getBlockState(pos.add(var8, var9, var10)) == Blocks.TALLGRASS) && checkUnderTree(world, pos.add(var8, var9, var10)) && (new Random()).nextInt(10) <= 9) {
                            world.setBlockState(pos.add(var8, var9, var10), FABlockRegistry.FERNS.getStateFromMeta(8 * this.checkSubType(var6)));
                        }
                    }
                }
            }
        }
    }

    public boolean canBlockStay(World world, BlockPos pos, IBlockState state){
        boolean var5 = true;
        IBlockState currentState = world.getBlockState(pos);
        int meta = currentState.getBlock().getMetaFromState(currentState);

        if (this.checkLevel(meta)) {
            return world.getBlockState(pos.down()).getBlock() == FABlockRegistry.FERNS;
        } else {
            var5 = world.getBlockState(pos.down()).getBlock() == Blocks.GRASS && checkUnderTree(world, pos);
            if (this.hasLv2(meta)) {
                var5 &= world.getBlockState(pos.down()).getBlock() == FABlockRegistry.FERNS;
            }

            return var5;
        }
    }

    public boolean checkLevel(int meta){
        return (meta >= 6 && meta <= 7) || meta == 12;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        if (rand.nextInt(4) == 0) {
            return FAItemRegistry.FERN_SEED;
        }
        return null;
    }

    public int checkSubType(int meta) {
        return meta > 7 ? 1 : 0;
    }

    protected boolean canPlaceBlockOn(Block block) {
        return block == Blocks.GRASS || block == Blocks.DIRT;
    }

    public boolean canGrow(int meta){
        return (meta >= 0 && meta < 5) || (meta >= 8 && meta < 11);
    }

    public boolean hasLv2(int meta) {
        return (meta >= 4 && meta <= 5) || (meta >= 11 && meta <= 11);
    }

    public boolean getLv2(int meta) {
        return (meta == 4) || (meta == 11);
    }

    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(TYPE, Integer.valueOf(meta / 8)).withProperty(GROWTH, Integer.valueOf((meta < 8 ? meta : meta - 8)));
    }

    public int getMetaFromState(IBlockState state) {
        return ((Integer)state.getValue(TYPE)).intValue() == 0 ? ((Integer)state.getValue(GROWTH)).intValue() : ((Integer)state.getValue(GROWTH)).intValue() + 7;
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {TYPE, GROWTH});
    }
>>>>>>> parent of 9902da5f... why are there two build.gradle files?
}
