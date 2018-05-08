package fossilsarcheology.server.item;

import fossilsarcheology.Revival;
import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class AncientHelmetItem extends ItemArmor implements DefaultRenderedItem {
    public AncientHelmetItem() {
        super(FAItemRegistry.ANCIENT_HELMET_MATERIAL, 0, EntityEquipmentSlot.HEAD);
        this.setCreativeTab(FATabRegistry.ITEMS);
        this.setUnlocalizedName("ancient_helmet");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public net.minecraft.client.model.ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, net.minecraft.client.model.ModelBiped _default){
        net.minecraft.client.model.ModelBiped armorModel = new net.minecraft.client.model.ModelBiped();

        if (itemStack != null) {
            if (itemStack.getItem() instanceof AncientHelmetItem) {

                EntityEquipmentSlot type = ((ItemArmor) itemStack.getItem()).armorType;
                if (type == EntityEquipmentSlot.CHEST || type == EntityEquipmentSlot.HEAD || type == EntityEquipmentSlot.FEET) {
                    armorModel = Revival.PROXY.getArmorModel(0);
                } else {
                    armorModel = Revival.PROXY.getArmorModel(1);
                }
            }
            if (armorModel != null) {
                armorModel.bipedHead.showModel = armorSlot == EntityEquipmentSlot.HEAD;
                armorModel.bipedHeadwear.showModel = armorSlot == EntityEquipmentSlot.HEAD;
                armorModel.bipedBody.showModel = armorSlot == EntityEquipmentSlot.CHEST || armorSlot == EntityEquipmentSlot.LEGS;
                armorModel.bipedRightArm.showModel = armorSlot == EntityEquipmentSlot.CHEST;
                armorModel.bipedLeftArm.showModel = armorSlot == EntityEquipmentSlot.CHEST;
                armorModel.bipedRightLeg.showModel = armorSlot == EntityEquipmentSlot.LEGS || armorSlot == EntityEquipmentSlot.FEET;
                armorModel.bipedLeftLeg.showModel = armorSlot == EntityEquipmentSlot.LEGS || armorSlot == EntityEquipmentSlot.FEET;
                armorModel.isSneak = entityLiving.isSneaking();
                armorModel.isRiding = entityLiving.isRiding();
                armorModel.isChild = entityLiving.isChild();
                return armorModel;
            }
        }
        return null;
    }
}
