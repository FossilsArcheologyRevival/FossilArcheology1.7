package fossilsarcheology.server.block;

import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public class ShortFlowerBlock extends BlockBush implements DefaultRenderedItem {

	public ShortFlowerBlock(String name) {
		super(Material.PLANTS);
		this.setTickRandomly(true);
		this.setUnlocalizedName(name);
		this.setSoundType(SoundType.PLANT);
		this.setCreativeTab(FATabRegistry.BLOCKS);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Block.EnumOffsetType getOffsetType() {
		return Block.EnumOffsetType.XZ;
	}

	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (this == FABlockRegistry.BENNETTITALES_SMALL_FLOWER) {
			this.grow(FABlockRegistry.BENNETTITALES_LARGE_FLOWER, heldItem, world, pos, playerIn);
			return true;
		} else if (this == FABlockRegistry.HORSETAIL_SMALL_FLOWER) {
			this.grow(FABlockRegistry.HORSETAIL_LARGE_FLOWER, heldItem, world, pos, playerIn);
			return true;
		}
		return false;
	}

	public void grow(TallFlowerBlock plantBlock, ItemStack itemstack, World world, BlockPos pos, EntityPlayer player) {
		if (itemstack != null && world.isAirBlock(pos.up())) {
			if (itemstack.getItem() != null) {
				if (itemstack.getItem() == Items.DYE) {
					if (itemstack.getItemDamage() == 15) {
						Random rand = new Random();
						world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, pos.getX() + (rand.nextDouble() - 0.5D), pos.getY() + rand.nextDouble(), pos.getZ() + (rand.nextDouble() - 0.5D), 0.0D, 0.0D, 0.0D, new int[0]);
						world.setBlockState(pos, plantBlock.getDefaultState().withProperty(TallFlowerBlock.HALF, TallFlowerBlock.EnumBlockHalf.LOWER));
						world.setBlockState(pos.up(), plantBlock.getDefaultState().withProperty(TallFlowerBlock.HALF, TallFlowerBlock.EnumBlockHalf.UPPER));
						if (!player.capabilities.isCreativeMode) {
							itemstack.shrink(1);
						}
						if (itemstack.getCount() <= 0) {
							player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
						}
					}
				}
			}
		}
	}
}
