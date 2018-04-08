package fossilsarcheology.server.container;

import fossilsarcheology.server.block.entity.TileEntityWorktable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;

public class WorktableContainer extends SyncedFieldContainer {

	private TileEntityWorktable furnace;

	public WorktableContainer(InventoryPlayer var1, TileEntityWorktable var2) {
		super(var2);
		this.furnace = (TileEntityWorktable) var2;
		this.addSlotToContainer(new Slot(this.furnace, 0, 49, 20));
		this.addSlotToContainer(new Slot(this.furnace, 1, 81, 54));
		this.addSlotToContainer(new SlotFurnaceOutput(var1.player, this.furnace, 2, 116, 21));
		int var3;

		for (var3 = 0; var3 < 3; ++var3) {
			for (int var4 = 0; var4 < 9; ++var4) {
				this.addSlotToContainer(new Slot(var1, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
			}
		}

		for (var3 = 0; var3 < 9; ++var3) {
			this.addSlotToContainer(new Slot(var1, var3, 8 + var3 * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return this.furnace.isUsableByPlayer(player);
	}
}
