package fossilsarcheology.server.item;

import com.google.common.collect.Multimap;
import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemSword;

public class BasicSwordItem extends ItemSword implements DefaultRenderedItem {

	public BasicSwordItem(ToolMaterial toolmaterial, String name) {
		super(toolmaterial);
		this.setUnlocalizedName(name);
		this.setCreativeTab(FATabRegistry.ITEMS);
		if (toolmaterial == FAItemRegistry.ICED_MEAT_MATERIAL) {
			this.setMaxDamage(4);
		}
	}

	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
		if (this == FAItemRegistry.TOOTH_DAGGER) {
			Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

			if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
				multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double) this.getAttackDamage(), 0));
				multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -10.4000000953674316D, 0));
			}
			return multimap;

		} else {
			return super.getItemAttributeModifiers(equipmentSlot);
		}

	}

}
