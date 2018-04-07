package fossilsarcheology.server.block;

import fossilsarcheology.client.sound.FASoundRegistry;
import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class DrumBlock extends Block implements DefaultRenderedItem {

	public static final PropertyInteger DRUMS = PropertyInteger.create("drums", 0, 2);

	public DrumBlock() {
		super(Material.WOOD);
		this.setDefaultState(this.blockState.getBaseState().withProperty(DRUMS, Integer.valueOf(0)));
		this.setTickRandomly(true);
		this.setUnlocalizedName("drum");
		this.setSoundType(SoundType.WOOD);
		this.setCreativeTab(FATabRegistry.BLOCKS);
	}

	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		int i = ((Integer) state.getValue(DRUMS)).intValue();
		if (i < 2) {
			worldIn.setBlockState(pos, state.withProperty(DRUMS, Integer.valueOf(i + 1)), 3);
		} else {
			worldIn.setBlockState(pos, state.withProperty(DRUMS, Integer.valueOf(0)), 3);
		}
		worldIn.playSound(player, pos, FASoundRegistry.DRUM_SINGLE, SoundCategory.BLOCKS, 1, 1);
		return true;
	}

	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(DRUMS, Integer.valueOf(meta));
	}

	public int getMetaFromState(IBlockState state) {
		return ((Integer) state.getValue(DRUMS)).intValue();
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[]{DRUMS});
	}

}
