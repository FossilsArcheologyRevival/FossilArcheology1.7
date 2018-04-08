package fossilsarcheology.server.container;

import fossilsarcheology.server.block.entity.TileEntityFeeder;
import fossilsarcheology.server.entity.prehistoric.Diet;
import fossilsarcheology.server.util.FoodMappings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class FeederContainer extends SyncedFieldContainer{
    public static final int CARN_INPUT = 0, HERB_INPUT = 1;
    int lastVegValue = 0;
    int lastMeatValue = 0;
    private TileEntityFeeder feeder;

    public FeederContainer(IInventory inventory, TileEntity var2) {
        super(inventory);
        this.feeder = (TileEntityFeeder) var2;
        this.addSlotToContainer(new Slot(this.feeder, 0, 60, 62));
        this.addSlotToContainer(new Slot(this.feeder, 1, 104, 62));
        int var3;

        for (var3 = 0; var3 < 3; ++var3) {
            for (int var4 = 0; var4 < 9; ++var4) {
                this.addSlotToContainer(new Slot(inventory, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 9; ++var3) {
            this.addSlotToContainer(new Slot(inventory, var3, 8 + var3 * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return this.feeder.isUsableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int getSlot) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(getSlot);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            // itemstack is in player inventory, try to place in appropriate
            // furnace slot
            if (getSlot > HERB_INPUT) // if it's not in the INPUT and in player
            // inventory
            {
                // if it can be smelted, place in the input slots
                if (FoodMappings.INSTANCE.getItemFoodAmount(itemstack1, Diet.CARNIVORE_EGG) != 0) {
                    // try to place in either Input slot; add 1 to final input
                    // slot because mergeItemStack uses < index
                    if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
                        return null;
                    }
                }
                if (FoodMappings.INSTANCE.getItemFoodAmount(itemstack1, Diet.HERBIVORE) != 0) {
                    // try to place in either Input slot; add 1 to final input
                    // slot because mergeItemStack uses < index
                    if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
                        return null;
                    }
                }
            }
            // item in player's inventory, but not in action bar
            else if (getSlot >= HERB_INPUT + 1 && getSlot < HERB_INPUT + 28) {
                // place in action bar
                if (!this.mergeItemStack(itemstack1, HERB_INPUT + 28, HERB_INPUT + 37, false)) {
                    return null;
                }
            }
            // item in action bar - place in player inventory
            else if (getSlot >= HERB_INPUT + 28 && getSlot < HERB_INPUT + 37 && !this.mergeItemStack(itemstack1, HERB_INPUT + 1, HERB_INPUT + 28, false)) {
                return null;
            }

            if (itemstack1.getCount() == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return null;
            }

            slot.onTake(player, itemstack1);
        }

        return itemstack;
    }
}
