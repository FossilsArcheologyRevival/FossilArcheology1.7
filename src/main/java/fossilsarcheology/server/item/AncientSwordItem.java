package fossilsarcheology.server.item;

import fossilsarcheology.server.entity.EntityAnuLightning;
import fossilsarcheology.server.entity.mob.EntityFriendlyPigZombie;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.EnumDifficulty;

import java.util.Random;

public class AncientSwordItem extends ItemSword {
    public AncientSwordItem(ToolMaterial var2) {
        super(var2);
        this.maxStackSize = 1;
        this.setMaxDamage(250);
    }

    public AncientSwordItem() {
        this(ToolMaterial.IRON);
    }

    /**
     * Current implementations of this method in child classes do not use the
     * entry argument beside ev. They just raise the damage on the stack.
     */
    @Override
    public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase targetentity, EntityLivingBase player) {
        if (player instanceof EntityPlayer) {
            if (player != null && this.checkHelmet((EntityPlayer) player)) {
                if (targetentity != null && (targetentity instanceof EntityPig || targetentity instanceof EntityPigZombie)) {
                    EntityFriendlyPigZombie fpz = new EntityFriendlyPigZombie(targetentity.worldObj);
                    fpz.setLocationAndAngles(targetentity.posX, targetentity.posY, targetentity.posZ, targetentity.rotationYaw, targetentity.rotationPitch);
                    if (!targetentity.worldObj.isRemote) {
                        targetentity.worldObj.spawnEntityInWorld(fpz);
                    }
                    fpz.setTamed(true);
                    if (player instanceof EntityPlayer) {
                        EntityPlayer playerUUID = (EntityPlayer) player;
                        fpz.func_152115_b(playerUUID.getUniqueID().toString());
                        fpz.sendMessageToOwner("pigman.summon");
                    }
                    targetentity.worldObj.spawnEntityInWorld(fpz);
                    targetentity.setDead();
                    targetentity.worldObj.spawnEntityInWorld(new EntityLightningBolt(targetentity.worldObj, targetentity.posX, targetentity.posY, targetentity.posZ));

                } else {
                    if (targetentity != null && (new Random()).nextInt(5) == 0) {
                        targetentity.worldObj.addWeatherEffect(new EntityAnuLightning(targetentity.worldObj, targetentity.posX, targetentity.posY, targetentity.posZ));
                    }

                }
            }

            par1ItemStack.damageItem(1, player);
        }
        return true;
    }

    private boolean checkHelmet(EntityPlayer player) {
        ItemStack item = player.inventory.armorInventory[3];
        if (item != null && item.getItem() != null) {
            if (item.getItem() == FAItemRegistry.INSTANCE.ancienthelmet) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon("fossil:Ancient_Sword");
    }
}
