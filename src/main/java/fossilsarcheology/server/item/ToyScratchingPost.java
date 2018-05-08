package fossilsarcheology.server.item;

import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.entity.utility.EntityToyScratchingPost;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ToyScratchingPost extends Item implements DefaultRenderedItem {

    public ToyScratchingPost() {
        this.setUnlocalizedName("toy_scratching_post");
        this.setCreativeTab(FATabRegistry.ITEMS);
    }
        public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
            if (facing == EnumFacing.UP && worldIn.isAirBlock(pos.up()) && worldIn.isAirBlock(pos.up(2))) {
                EntityToyScratchingPost ball = new EntityToyScratchingPost(worldIn);
                ball.setLocationAndAngles(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, 0, 0);
                if (!worldIn.isRemote)
                    worldIn.spawnEntity(ball);
                ball.rotationYaw = playerIn.rotationYawHead;
                if (!playerIn.capabilities.isCreativeMode)
                    playerIn.getHeldItem(hand).shrink(1);
            }
        return EnumActionResult.SUCCESS;
    }
}
