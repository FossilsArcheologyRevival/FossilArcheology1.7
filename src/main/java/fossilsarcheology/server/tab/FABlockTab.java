package fossilsarcheology.server.tab;

import fossilsarcheology.server.block.FABlockRegistry;
import fossilsarcheology.server.localization.Localizations;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FABlockTab extends CreativeTabs {
	public FABlockTab() {
		super("Fossil Blocks");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getTabIconItem() {
		return new ItemStack(FABlockRegistry.WORKTABLE_ACTIVE);
	}

	@Override
	public String getTranslatedTabLabel() {
		return Localizations.TAB_BLOCKS;
	}
}
