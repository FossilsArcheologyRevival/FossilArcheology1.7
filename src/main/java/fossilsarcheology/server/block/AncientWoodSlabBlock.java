package fossilsarcheology.server.block;

import net.minecraft.block.SoundType;
import net.minecraft.item.ItemBlock;

public abstract class AncientWoodSlabBlock extends FossilSlabBlock {
	public AncientWoodSlabBlock(String name, float hardness, float resistance, SoundType soundType) {
		super(name, hardness, resistance, soundType, FABlockRegistry.ANCIENT_WOOD_SINGLESLAB);
	}

	@Override
	public ItemBlock getItemBlock() {
		return new FossilSlabBlockItem(this, FABlockRegistry.ANCIENT_WOOD_SINGLESLAB, FABlockRegistry.ANCIENT_WOOD_DOUBLESLAB);
	}

	public static class Double extends FossilSlabBlock {
		public Double(String name, float hardness, float resistance, SoundType soundType) {
			super(name, hardness, resistance, soundType, FABlockRegistry.ANCIENT_WOOD_SINGLESLAB);
		}

		public boolean isDouble() {
			return true;
		}

		@Override
		public ItemBlock getItemBlock() {
			return new FossilSlabBlockItem(this, FABlockRegistry.ANCIENT_WOOD_SINGLESLAB, FABlockRegistry.ANCIENT_WOOD_DOUBLESLAB);
		}
	}

	public static class Half extends FossilSlabBlock {
		public Half(String name, float hardness, float resistance, SoundType soundType) {
			super(name, hardness, resistance, soundType, FABlockRegistry.ANCIENT_WOOD_SINGLESLAB);
		}

		public boolean isDouble() {
			return false;
		}

		@Override
		public ItemBlock getItemBlock() {
			return new FossilSlabBlockItem(this, FABlockRegistry.ANCIENT_WOOD_SINGLESLAB, FABlockRegistry.ANCIENT_WOOD_DOUBLESLAB);
		}
	}
}
