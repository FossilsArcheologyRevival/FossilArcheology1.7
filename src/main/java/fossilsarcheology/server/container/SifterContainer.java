package fossilsarcheology.server.container;

import fossilsarcheology.server.block.entity.TileEntitySifter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;

public class SifterContainer extends SyncedFieldContainer {
	private TileEntitySifter sifter;

	public SifterContainer(InventoryPlayer var1, TileEntitySifter var2) {
		super(var2);
		this.sifter = (TileEntitySifter) var2;
		int var3;
		int var4;

		// Sifter input
		this.addSlotToContainer(new Slot(this.sifter, 0, 80, 10));

		// Sifter output
		for (var3 = 0; var3 < 5; ++var3) {
			this.addSlotToContainer(new SlotFurnaceOutput(var1.player, this.sifter, 1 + var3, 44 + 18 * var3, 62));
		}

		// player inventory
		for (var3 = 0; var3 < 3; ++var3) {
			for (var4 = 0; var4 < 9; ++var4) {
				this.addSlotToContainer(new Slot(var1, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
			}
		}

		// player hotbar
		for (var3 = 0; var3 < 9; ++var3) {
			this.addSlotToContainer(new Slot(var1, var3, 8 + var3 * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return this.sifter.isUsableByPlayer(player);
	}
}
