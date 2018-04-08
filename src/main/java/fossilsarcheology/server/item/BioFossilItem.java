package fossilsarcheology.server.item;

import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.entity.prehistoric.EntityPrehistoric;
import fossilsarcheology.server.entity.prehistoric.PrehistoricEntityType;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.Random;

public class BioFossilItem extends Item implements DefaultRenderedItem {
    private boolean isTarFossil;

    public BioFossilItem(boolean isTarFossil) {
        this.maxStackSize = 64;
        this.setMaxDamage(0);
        this.isTarFossil = isTarFossil;
        this.setUnlocalizedName(isTarFossil ? "tar_fossil" : "biofossil");
        this.setCreativeTab(FATabRegistry.ITEMS);
    }

    public boolean tryPlaceIntoWorld(ItemStack stack, EntityPlayer player, World world, BlockPos pos) {
            EntityPrehistoric entity = (EntityPrehistoric) PrehistoricEntityType.getRandomBioFossil(new Random(), this.isTarFossil).invokeClass(world);
            entity.prevRotationYaw = 0;
            entity.setLocationAndAngles((double) pos.getX() + 0.5, (double)pos.getY(), (double) pos.getZ() + 0.5D, player.rotationYaw, player.rotationPitch);
            entity.setSkeleton(true);
            if (world.checkNoEntityCollision(entity.getEntityBoundingBox())) {
                if(!world.isRemote){
                    world.spawnEntity(entity);
                }
                stack.shrink(1);
                return true;
            } else {
                return false;
            }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        RayTraceResult result = this.rayTrace(world, player, true);
        ItemStack stack = player.getHeldItem(hand);
        if (result != null && result.typeOfHit == RayTraceResult.Type.BLOCK) {
            BlockPos pos = result.getBlockPos();
            if (world.getBlockState(pos).isSideSolid(world, pos, result.sideHit)) {
                this.tryPlaceIntoWorld(stack, player, world, pos.offset(result.sideHit));
            }
        }
        return ActionResult.newResult(EnumActionResult.PASS, stack);
    }
}
