package fossilsarcheology.server.entity.prehistoric;


import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.world.World;

public class EntityQuagga extends EntityHorse {

	public EntityQuagga(World par1World) {
		super(par1World);

	}

	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		return null;
	}

	@Override
	protected void entityInit() {

	}

}