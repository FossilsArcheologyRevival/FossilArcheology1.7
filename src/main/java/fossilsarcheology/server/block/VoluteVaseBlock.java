package fossilsarcheology.server.block;

import fossilsarcheology.server.api.BlockEntity;
import fossilsarcheology.server.block.entity.block.TileEntityVolute;
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

public class VoluteVaseBlock extends BlockContainer implements BlockEntity, IBlockItem {

	public static final PropertyEnum<VoluteVaseBlock.EnumType> VARIANT = PropertyEnum.<VoluteVaseBlock.EnumType>create("variant", VoluteVaseBlock.EnumType.class);

	protected VoluteVaseBlock() {
		super(Material.ROCK);
		this.setCreativeTab(FATabRegistry.BLOCKS);
		this.setUnlocalizedName("vaseVolute");
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.DAMAGED_VOLUTE));
	}

	public int damageDropped(IBlockState state) {
		return ((VoluteVaseBlock.EnumType) state.getValue(VARIANT)).getMetadata();
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@SuppressWarnings("deprecation")
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		int l = MathHelper.floor((double) (placer.rotationYaw * 4.0F / 360.0F) + 1.5D) & 3;
		TileEntity tileentity = worldIn.getTileEntity(pos);
		((TileEntityVolute) tileentity).setVaseType(stack.getItemDamage());
		((TileEntityVolute) tileentity).setVaseRotation(l);
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
		for (VoluteVaseBlock.EnumType type : VoluteVaseBlock.EnumType.values()) {
			list.add(new ItemStack(itemIn, 1, type.getMetadata()));
		}
	}

	@SuppressWarnings("deprecation")
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(VARIANT, VoluteVaseBlock.EnumType.byMetadata(meta));
	}

	public int getMetaFromState(IBlockState state) {
		return ((VoluteVaseBlock.EnumType) state.getValue(VARIANT)).getMetadata();
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[]{VARIANT});
	}

	@Override
	public Class<? extends ItemBlock> getItemBlockClass() {
		return VoluteBlockItem.class;
	}

	@Override
	public Class<? extends TileEntity> getEntity() {
		return TileEntityVolute.class;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityVolute();
	}

	public static enum EnumType implements IStringSerializable {
		DAMAGED_VOLUTE(0, "damaged_volute"),
		RESTORED_VOLUTE(1, "restored_volute"),
		REDFIGURE_VOLUTE(2, "redfigure_volute"),
		BLACKFIGURE_VOLUTE(3, "blackfigure_volute"),
		PORCELAIN_VOLUTE(4, "porcelain_volute");

		private static final VoluteVaseBlock.EnumType[] META_LOOKUP = new VoluteVaseBlock.EnumType[values().length];

		static {
			for (VoluteVaseBlock.EnumType blockplanks$enumtype : values()) {
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

		public static VoluteVaseBlock.EnumType byMetadata(int meta) {
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

	class VoluteBlockItem extends ItemBlock {
		public VoluteBlockItem(Block block) {
			super(block);
		}

		@Override
		public String getUnlocalizedName(ItemStack itemstack) {
			return getUnlocalizedName() + "." + EnumType.byMetadata(itemstack.getItemDamage()).getName();
		}
	}
}
