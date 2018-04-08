package fossilsarcheology.server.block;

import net.minecraft.block.SoundType;
import net.minecraft.item.ItemBlock;

public abstract class AncientStoneSlabBlock extends FossilSlabBlock {
	public AncientStoneSlabBlock(String name, float hardness, float resistance, SoundType soundType) {
		super(name, hardness, resistance, soundType, FABlockRegistry.ANCIENT_STONE_SINGLESLAB);
	}

	@Override
	public ItemBlock getItemBlock() {
		return new FossilSlabBlockItem(this, FABlockRegistry.ANCIENT_STONE_SINGLESLAB, FABlockRegistry.ANCIENT_STONE_DOUBLESLAB);
	}


	public static class Double extends FossilSlabBlock {
		public Double(String name, float hardness, float resistance, SoundType soundType) {
			super(name, hardness, resistance, soundType, FABlockRegistry.ANCIENT_STONE_DOUBLESLAB);
		}

		public boolean isDouble() {
			return true;
		}

		@Override
		public ItemBlock getItemBlock() {
			return new FossilSlabBlockItem(this, FABlockRegistry.ANCIENT_STONE_SINGLESLAB, FABlockRegistry.ANCIENT_STONE_DOUBLESLAB);
		}
	}

	public static class Half extends FossilSlabBlock {
		public Half(String name, float hardness, float resistance, SoundType soundType) {
			super(name, hardness, resistance, soundType, FABlockRegistry.ANCIENT_STONE_SINGLESLAB);
		}

		public boolean isDouble() {
			return false;
		}

		@Override
		public ItemBlock getItemBlock() {
			return new FossilSlabBlockItem(this, FABlockRegistry.ANCIENT_STONE_SINGLESLAB, FABlockRegistry.ANCIENT_STONE_DOUBLESLAB);
		}
	}
}
