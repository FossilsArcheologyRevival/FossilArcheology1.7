package fossilsarcheology.server.structure;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class SpikesBlockWorldGen extends WorldGenerator {

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		return false;
	}
}
