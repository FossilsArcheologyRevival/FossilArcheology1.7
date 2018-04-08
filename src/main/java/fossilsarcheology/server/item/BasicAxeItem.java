package fossilsarcheology.server.item;

import com.google.common.collect.Sets;
import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

import java.util.Set;

public class BasicAxeItem extends ItemTool implements DefaultRenderedItem {
	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(new Block[]{Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK, Blocks.LADDER, Blocks.OAK_DOOR, Blocks.WOODEN_PRESSURE_PLATE});

	public BasicAxeItem(ToolMaterial toolmaterial, String name) {
		super(toolmaterial, EFFECTIVE_ON);
		this.attackDamage = toolmaterial.getAttackDamage();
		this.attackSpeed = -3;
		this.setUnlocalizedName(name);
		this.setCreativeTab(FATabRegistry.ITEMS);
	}

	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state) {
		Material material = state.getMaterial();
		return material != Material.WOOD && material != Material.PLANTS && material != Material.VINE ? super.getDestroySpeed(stack, state) : this.efficiency;
	}
}
