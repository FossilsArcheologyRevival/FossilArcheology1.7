package fossilsarcheology.server.block;

import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class StrongGlassBlock extends BlockBreakable implements DefaultRenderedItem {

    public StrongGlassBlock() {
        super(Material.GLASS, true);
        this.setLightOpacity(1);
        this.setUnlocalizedName("strongGlass");
        this.setHardness(3F);
        this.setResistance(25F);
        this.setCreativeTab(FATabRegistry.BLOCKS);
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

<<<<<<< HEAD
	@SuppressWarnings("deprecation")
	public boolean isFullCube(IBlockState state) {
		return false;
	}
=======
    public boolean isFullCube(IBlockState state) {
        return false;
    }
>>>>>>> parent of 9902da5f... why are there two build.gradle files?

}
