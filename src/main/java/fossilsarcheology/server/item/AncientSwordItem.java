package fossilsarcheology.server.item;

import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.entity.monster.EntityFriendlyPigZombie;
import fossilsarcheology.server.entity.utility.EntityAncientLightning;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

import java.util.Random;

public class AncientSwordItem extends ItemSword implements DefaultRenderedItem {

    public AncientSwordItem() {
        super(ToolMaterial.IRON);
        this.maxStackSize = 1;
        this.setMaxDamage(250);
        this.setCreativeTab(FATabRegistry.ITEMS);
        this.setUnlocalizedName("ancient_sword");
    }

    @Override
    public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase targetentity, EntityLivingBase player) {
        if (player instanceof EntityPlayer) {
            if (player != null && this.checkHelmet((EntityPlayer) player)) {
                if (targetentity != null && (targetentity instanceof EntityPig || targetentity instanceof EntityPigZombie)) {
                    EntityFriendlyPigZombie fpz = new EntityFriendlyPigZombie(targetentity.world);
                    fpz.setLocationAndAngles(targetentity.posX, targetentity.posY, targetentity.posZ, targetentity.rotationYaw, targetentity.rotationPitch);
                    fpz.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.GOLDEN_SWORD));
                    if (!targetentity.world.isRemote) {
                        targetentity.world.spawnEntity(fpz);
                    }
                    fpz.setTamed(true);
                    if (player instanceof EntityPlayer) {
                        EntityPlayer playerUUID = (EntityPlayer) player;
                        fpz.setOwnerId(playerUUID.getUniqueID());
                        fpz.sendMessageToOwner("pigman.summon");
                    }
                    targetentity.setDead();
                    targetentity.world.spawnEntity(new EntityLightningBolt(targetentity.world, targetentity.posX, targetentity.posY, targetentity.posZ, true));

                } else {
                    if (targetentity != null && (new Random()).nextInt(5) == 0) {
                        targetentity.world.addWeatherEffect(new EntityAncientLightning(targetentity.world, targetentity.posX, targetentity.posY, targetentity.posZ));
                    }

                }
            }

            par1ItemStack.damageItem(1, player);
        }
        return true;
    }

    private boolean checkHelmet(EntityPlayer player) {
        ItemStack item = player.inventory.armorInventory.get(3);
        if (item != ItemStack.EMPTY) {
            if (item.getItem() == FAItemRegistry.ANCIENT_HELMET) {
                return true;
            }
        }
        return false;
    }
}
