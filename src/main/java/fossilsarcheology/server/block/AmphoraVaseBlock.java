package fossilsarcheology.server.block;

import fossilsarcheology.server.api.BlockEntity;
import fossilsarcheology.server.block.entity.TileEntityAmphora;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class AmphoraVaseBlock extends BlockContainer implements BlockEntity, IBlockItem {
	public static final PropertyEnum<AmphoraVaseBlock.EnumType> VARIANT = PropertyEnum.<AmphoraVaseBlock.EnumType>create("variant", AmphoraVaseBlock.EnumType.class);

	protected AmphoraVaseBlock() {
		super(Material.ROCK);
		this.setCreativeTab(FATabRegistry.BLOCKS);
		this.setUnlocalizedName("vaseAmphora");
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.DAMAGED_AMPHORA));
	}

	public int damageDropped(IBlockState state) {
		return ((AmphoraVaseBlock.EnumType) state.getValue(VARIANT)).getMetadata();
	}

	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		int l = MathHelper.floor((double) (placer.rotationYaw * 4.0F / 360.0F) + 1.5D) & 3;
		TileEntity tileentity = worldIn.getTileEntity(pos);
		((TileEntityAmphora) tileentity).setVaseType(stack.getItemDamage());
		((TileEntityAmphora) tileentity).setVaseRotation(l);
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
		for (AmphoraVaseBlock.EnumType type : AmphoraVaseBlock.EnumType.values()) {
			list.add(new ItemStack(itemIn, 1, type.getMetadata()));
		}
	}

	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(VARIANT, AmphoraVaseBlock.EnumType.byMetadata(meta));
	}

	public int getMetaFromState(IBlockState state) {
		return ((AmphoraVaseBlock.EnumType) state.getValue(VARIANT)).getMetadata();
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[]{VARIANT});
	}

	@Override
	public Class<? extends ItemBlock> getItemBlockClass() {
		return AmphoraBlockItem.class;
	}

	@Override
	public Class<? extends TileEntity> getEntity() {
		return TileEntityAmphora.class;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityAmphora();
	}

	public static enum EnumType implements IStringSerializable {
		DAMAGED_AMPHORA(0, "damaged_amphora"),
		RESTORED_AMPHORA(1, "restored_amphora"),
		REDFIGURE_AMPHORA(2, "redfigure_amphora"),
		BLACKFIGURE_AMPHORA(3, "blackfigure_amphora"),

		PORCELAIN_AMPHORA(4, "porcelain_amphora");
		private static final AmphoraVaseBlock.EnumType[] META_LOOKUP = new AmphoraVaseBlock.EnumType[values().length];

		static {
			for (AmphoraVaseBlock.EnumType blockplanks$enumtype : values()) {
				META_LOOKUP[blockplanks$enumtype.getMetadata()] = blockplanks$enumtype;
			}
		}

		private final int meta;
		private final String name;
		private final String unlocalizedName;

		private EnumType(int metaIn, String nameIn) {
			this(metaIn, nameIn, nameIn);
		}

		private EnumType(int metaIn, String nameIn, String unlocalizedNameIn) {
			this.meta = metaIn;
			this.name = nameIn;
			this.unlocalizedName = unlocalizedNameIn;
		}

		public static AmphoraVaseBlock.EnumType byMetadata(int meta) {
			if (meta < 0 || meta >= META_LOOKUP.length) {
				meta = 0;
			}

			return META_LOOKUP[meta];
		}

		public int getMetadata() {
			return this.meta;
		}

		public String toString() {
			return this.name;
		}

		public String getName() {
			return this.name;
		}

		public String getUnlocalizedName() {
			return this.unlocalizedName;
		}
	}

	class AmphoraBlockItem extends ItemBlock {
		public AmphoraBlockItem(Block block) {
			super(block);
		}

		@Override
		public String getUnlocalizedName(ItemStack itemstack) {
			return getUnlocalizedName() + "." + EnumType.byMetadata(itemstack.getItemDamage()).getName();
		}
	}
}
