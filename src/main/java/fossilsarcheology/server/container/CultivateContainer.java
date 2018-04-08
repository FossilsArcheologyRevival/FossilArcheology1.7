package fossilsarcheology.server.container;

import fossilsarcheology.server.block.entity.TileEntityCultivate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;

public class CultivateContainer extends SyncedFieldContainer {
	private TileEntityCultivate furnace;

	public CultivateContainer(InventoryPlayer playerInventory, TileEntityCultivate var2) {
		super(var2);
		this.furnace = (TileEntityCultivate) var2;
		this.addSlotToContainer(new Slot(this.furnace, 0, 49, 20));
		this.addSlotToContainer(new Slot(this.furnace, 1, 81, 54));
		this.addSlotToContainer(new SlotFurnaceOutput(playerInventory.player, this.furnace, 2, 116, 21));
		int var3;

		// inventory
		for (var3 = 0; var3 < 3; ++var3) {
			for (int var4 = 0; var4 < 9; ++var4) {
				this.addSlotToContainer(new Slot(playerInventory, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
			}
		}

		// hotbar
		for (var3 = 0; var3 < 9; ++var3) {
			this.addSlotToContainer(new Slot(playerInventory, var3, 8 + var3 * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return this.furnace.isUsableByPlayer(player);
	}

}
