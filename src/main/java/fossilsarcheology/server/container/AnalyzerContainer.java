package fossilsarcheology.server.container;

import fossilsarcheology.server.block.entity.AnalyzerBlockEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;

public class AnalyzerContainer extends SyncedFieldContainer {
	private AnalyzerBlockEntity analyzer;

	public AnalyzerContainer(InventoryPlayer playerInventory, AnalyzerBlockEntity tile) {
		super(tile);
		this.analyzer = tile;
		for (int column = 0; column < 3; ++column) {
			for (int row = 0; row < 3; ++row) {
				this.addSlotToContainer(new Slot(this.analyzer, row + column * 3, 20 + row * 18, 17 + column * 18));
			}
		}
		this.addSlotToContainer(new SlotFurnaceOutput(playerInventory.player, this.analyzer, 9, 116, 21));
		for (int slot = 0; slot < 3; ++slot) {
			this.addSlotToContainer(new SlotFurnaceOutput(playerInventory.player, this.analyzer, 10 + slot, 111 + 18 * slot, 53));
		}
		for (int column = 0; column < 3; ++column) {
			for (int row = 0; row < 9; ++row) {
				this.addSlotToContainer(new Slot(playerInventory, row + column * 9 + 9, 8 + row * 18, 84 + column * 18));
			}
		}
		for (int row = 0; row < 9; ++row) {
			this.addSlotToContainer(new Slot(playerInventory, row, 8 + row * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return this.analyzer.isUsableByPlayer(player);
	}
}
