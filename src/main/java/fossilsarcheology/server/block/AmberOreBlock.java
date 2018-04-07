package fossilsarcheology.server.block;

import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.item.FAItemRegistry;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class AmberOreBlock extends Block implements DefaultRenderedItem {
	public AmberOreBlock() {
		super(Material.ROCK);
		this.setCreativeTab(FATabRegistry.BLOCKS);
		this.setHardness(3.0F);
		this.setUnlocalizedName("amber_ore");
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return this == FABlockRegistry.AMBER_ORE ? FAItemRegistry.AMBER : Item.getItemFromBlock(this);
	}

	@Override
	public int quantityDropped(Random rand) {
		return this == FABlockRegistry.AMBER_ORE ? 2 + rand.nextInt(2) : 1;
	}

	@Override
	public int quantityDroppedWithBonus(int bonus, Random rand) {
		if (bonus > 0 && Item.getItemFromBlock(this) != this.getItemDropped(this.getDefaultState(), rand, bonus)) {
			int quantityMultiplier = rand.nextInt(bonus + 1) - 1;
			if (quantityMultiplier < 0) {
				quantityMultiplier = 0;
			}
			return this.quantityDropped(rand) * (quantityMultiplier + 1);
		} else {
			return this.quantityDropped(rand);
		}
	}

	@Override
	public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float chance, int fortune) {
		super.dropBlockAsItemWithChance(world, pos, state, chance, fortune);
		if (this.getItemDropped(state, world.rand, fortune) != Item.getItemFromBlock(this)) {
			int experience = 0;
			if (this == FABlockRegistry.AMBER_ORE) {
				experience = 1;
			}
			this.dropXpOnBlockBreak(world, pos, experience);
		}
	}
}
