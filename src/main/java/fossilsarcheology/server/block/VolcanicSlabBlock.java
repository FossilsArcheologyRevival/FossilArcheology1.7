package fossilsarcheology.server.block;

import net.minecraft.block.SoundType;
import net.minecraft.item.ItemBlock;

public abstract class VolcanicSlabBlock extends FossilSlabBlock {
	public VolcanicSlabBlock(String name, float hardness, float resistance, SoundType soundType) {
		super(name, hardness, resistance, soundType, FABlockRegistry.VOLCANIC_SINGLESLAB);
	}

	@Override
	public ItemBlock getItemBlock() {
		return new FossilSlabBlockItem(this, FABlockRegistry.VOLCANIC_SINGLESLAB, FABlockRegistry.VOLCANIC_DOUBLESLAB);
	}

	public static class Double extends FossilSlabBlock {
		public Double(String name, float hardness, float resistance, SoundType soundType) {
			super(name, hardness, resistance, soundType, FABlockRegistry.VOLCANIC_SINGLESLAB);
		}

		@Override
		public ItemBlock getItemBlock() {
			return new FossilSlabBlockItem(this, FABlockRegistry.VOLCANIC_SINGLESLAB, FABlockRegistry.VOLCANIC_DOUBLESLAB);
		}

		public boolean isDouble() {
			return true;
		}
	}

	public static class Half extends FossilSlabBlock {
		public Half(String name, float hardness, float resistance, SoundType soundType) {
			super(name, hardness, resistance, soundType, FABlockRegistry.VOLCANIC_SINGLESLAB);
		}

		@Override
		public ItemBlock getItemBlock() {
			return new FossilSlabBlockItem(this, FABlockRegistry.VOLCANIC_SINGLESLAB, FABlockRegistry.VOLCANIC_DOUBLESLAB);
		}

		public boolean isDouble() {
			return false;
		}
	}
}
