package fossilsarcheology.server.entity.prehistoric;

import fossilsarcheology.server.entity.EntityFishBase;
import net.minecraft.world.World;

public class EntityAlligatorGar extends EntityFishBase {
	public EntityAlligatorGar(World par1World) {
		super(par1World, PrehistoricEntityType.ALLIGATOR_GAR);
		this.setSize(1.9F, 1.2F);
	}

	@Override
	public String getTexture() {
		return "fossil:textures/model/fish/alligator_gar.png";
	}

	@Override
	protected double getSwimSpeed() {
		return 0.45D;
	}
}
