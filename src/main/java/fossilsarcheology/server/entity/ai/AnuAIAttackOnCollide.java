package fossilsarcheology.server.entity.ai;

import fossilsarcheology.server.entity.monster.EntityAnu;
import net.minecraft.entity.ai.EntityAIAttackMelee;

public class AnuAIAttackOnCollide extends EntityAIAttackMelee {
	private EntityAnu theEntity;

	public AnuAIAttackOnCollide(EntityAnu anu, Class clazz, double d, boolean foref) {
		super(anu, d, foref);
		theEntity = anu;
	}

	@Override
	public boolean shouldExecute() {
		return theEntity.getAttackMode() == 0 && super.shouldExecute();
	}

}
