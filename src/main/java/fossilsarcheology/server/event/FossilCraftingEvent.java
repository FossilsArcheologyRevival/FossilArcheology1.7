package fossilsarcheology.server.event;

public class FossilCraftingEvent {

   /* @SubscribeEvent
    public void onCraftEvent(PlayerEvent.ItemCraftedEvent event) {
        EntityPlayer player = event.player;
        if (event.crafting.getItem() == Item.getItemFromBlock(FABlockRegistry.ANALYZER)) {
            player.addStat(FossilAchievements.ANALYZER, 1);
        } else if (event.crafting.getItem() == Item.getItemFromBlock(FABlockRegistry.CULTIVATE_IDLE)) {
            player.addStat(FossilAchievements.CULTIVATE, 1);
        } else if (event.crafting.getItem() == Item.getItemFromBlock(FABlockRegistry.SIFTER_IDLE)) {
            player.addStat(FossilAchievements.SIFTER, 1);
        } else if (event.crafting.getItem() == Item.getItemFromBlock(FABlockRegistry.WORKTABLE_IDLE)) {
            player.addStat(FossilAchievements.ARC_WORKBENCH, 1);
        } else if (event.crafting.getItem() == FAItemRegistry.AQUATIC_SCARAB_GEM) {
            player.addStat(FossilAchievements.BLUE_SCARAB, 1);
        } else if (event.crafting.getItem() == FAItemRegistry.DINOPEDIA) {
            player.addStat(FossilAchievements.DINOPEDIA, 1);
        } else if (event.crafting.getItem() == FAItemRegistry.SCARAB_AXE || event.crafting.getItem() == FAItemRegistry.SCARAB_HOE || event.crafting.getItem() == FAItemRegistry.SCARAB_SWORD || event.crafting.getItem() == FAItemRegistry.SCARAB_PICKAXE || event.crafting.getItem() == FAItemRegistry.SCARAB_SHOVEL) {
            player.addStat(FossilAchievements.SCARAB_TOOLS, 1);
        }
    }

    @SubscribeEvent
    public void onItemEvent(PlayerEvent.ItemPickupEvent event) {
        if (event.pickedUp.getEntityItem().getItem() == FAItemRegistry.SCARAB_GEM) {
            event.player.addStat(FossilAchievements.SCARAB, 1);
        }
        if (event.pickedUp.getEntityItem().getItem() == FAItemRegistry.ANCIENT_KEY) {
            event.player.addStat(FossilAchievements.KEY, 1);
        }
        if (event.pickedUp.getEntityItem().getItem() == FAItemRegistry.ANCIENT_CLOCK) {
            event.player.addStat(FossilAchievements.CLOCK, 1);
        }
    }*/
}
