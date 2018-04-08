package fossilsarcheology.server.item;

import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.entity.prehistoric.PrehistoricEntityType;
import fossilsarcheology.server.entity.utility.FossilsMammalProperties;
import net.ilexiconn.llibrary.server.entity.EntityPropertiesHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;

public class MammalEmbryoItem extends PrehistoricEntityItem implements DefaultRenderedItem {
	public MammalEmbryoItem(PrehistoricEntityType type) {
		super("syringe", type);
		this.setMaxDamage(0);
		this.type = type;
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase entity, EnumHand hand) {
		if (entity instanceof EntityAnimal && !((EntityAnimal) entity).isChild()) {
			FossilsMammalProperties properties = EntityPropertiesHandler.INSTANCE.getProperties(entity, FossilsMammalProperties.class);
			if (properties != null) {
				if (properties.embryo != null || properties.isPregnant) {
					return false;
				}
				if (this.type != null) {
					properties.embryo = this.type;
					properties.isPregnant = true;
					if (!player.capabilities.isCreativeMode) {
						stack.shrink(1);
					}
				} else {
					return false;
				}
				for (int i = 0; i < 7; i++) {
					entity.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, entity.posX + entity.getRNG().nextFloat() * entity.width * 2.0F - entity.width, entity.posY + 0.5D + (entity.getRNG().nextFloat() * entity.height), entity.posZ + (entity.getRNG().nextFloat() * entity.width * 2.0F) - entity.width, entity.getRNG().nextGaussian() * 0.02D, entity.getRNG().nextGaussian() * 0.02D, entity.getRNG().nextGaussian() * 0.02D);
				}
				return true;
			}
		}
		return false;
	}
}
