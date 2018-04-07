package fossilsarcheology.server.event;


import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class FossilPickupItemEvent {
	@SubscribeEvent
	public void notifyPickup(PlayerEvent.ItemPickupEvent event) {
	}

	@SubscribeEvent
	public void notifyPickup(PlayerEvent.ItemCraftedEvent event) {
	}

  /*  @SubscribeEvent
    public void notifyPickup(PlayerEvent.ItemSmeltedEvent event) {

        // Analyzer Achievements
        if (PrehistoricEntityType.isDNA(event.smelting.getItem())) {
            event.player.addStat(FossilAchievements.DINO_DNA, 1);
        }
        if (event.smelting.getItem() == FAItemRegistry.STONE_TABLET) {
            event.player.addStat(FossilAchievements.TABLET, 1);
        }
        if (event.smelting.getItem() == FAItemRegistry.FOSSIL_SEED || event.smelting.getItem() == FAItemRegistry.FOSSIL_SEED) {
            event.player.addStat(FossilAchievements.FOSSIL_SEEDS, 1);
        }
        if (event.smelting.getItem() == FAItemRegistry.FAILURESAURUS_FLESH) {
            event.player.addStat(FossilAchievements.FAILURESAURUS, 1);
        }

        // Cultivator Achievements
        if (PrehistoricEntityType.isDinoEgg(event.smelting.getItem())) {
            event.player.addStat(FossilAchievements.DINO_EGG, 1);
        }
        if (PrehistoricEntityType.isEmbryo(event.smelting.getItem())) {
            event.player.addStat(FossilAchievements.MAMMAL_EMBRYO, 1);
        }

        if (PrehistoricEntityType.isBestBirdEgg(event.smelting.getItem())) {
            event.player.addStat(FossilAchievements.BIRD_EGG, 1);
        }

        // Workbench Achievements
        if (event.smelting.getItem() == FAItemRegistry.ANCIENT_SWORD) {
            event.player.addStat(FossilAchievements.FIXED_SWORD, 1);
        }
        if (event.smelting.getItem() == FAItemRegistry.ANCIENT_HELMET) {
            event.player.addStat(FossilAchievements.FIXED_HELMET, 1);
        }
        if (event.smelting.getItem() == Item.getItemFromBlock(FABlockRegistry.AMPHORA_VASE) || event.smelting.getItem() == Item.getItemFromBlock(FABlockRegistry.KYLIX_VASE) || event.smelting.getItem() == Item.getItemFromBlock(FABlockRegistry.VOLUTE_VASE)) {
            event.player.addStat(FossilAchievements.FIXED_VASE, 1);
        }
    }*/
}