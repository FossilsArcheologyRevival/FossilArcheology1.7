package fossilsarcheology.server.item;


import fossilsarcheology.client.sound.FASoundRegistry;
import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.entity.prehistoric.EntityPrehistoric;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemCarrotOnAStick;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WhipItem extends ItemCarrotOnAStick implements DefaultRenderedItem {
    public WhipItem() {
        super();
        this.setMaxDamage(100);
        this.setMaxStackSize(1);
        this.setUnlocalizedName("whip");
        this.setCreativeTab(FATabRegistry.ITEMS);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isFull3D() {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldRotateAroundWhenRendering() {
        return true;
    }


    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand){
        if (player.isRiding() && player.getRidingEntity() instanceof EntityPrehistoric) {
            EntityPrehistoric dinosaur = (EntityPrehistoric) player.getRidingEntity();
             dinosaur.onWhipRightClick();
            player.getHeldItem(hand).damageItem(1, player);
            player.swingArm(hand);
            player.getRidingEntity().playSound(FASoundRegistry.WHIP, 1.0F, 1.0F);
        } else {
            /*
			 * if (!W.isRemote) { W.spawnEntityInWorld(new
			 * EntityWhipAttachment(W, P)); }
			 */
            player.swingArm(hand);
            player.getRidingEntity().playSound(FASoundRegistry.WHIP, 1.0F, 1.0F);
        }

        return ActionResult.newResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));

    }

}