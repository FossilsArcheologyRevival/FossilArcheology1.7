package fossilsarcheology.server.entity.ai;

import fossilsarcheology.server.entity.monster.EntityAnu;
import net.minecraft.entity.ai.EntityAIAvoidEntity;

public class AnuAIAvoidEntity extends EntityAIAvoidEntity {
	private EntityAnu theEntity;

	public AnuAIAvoidEntity(EntityAnu anu, Class clazz, float f, double d0, double d1) {
		super(anu, clazz, f, d0, d1);
		theEntity = anu;
	}

	@Override
	public boolean shouldExecute() {
		return theEntity.getAttackMode() == 2 && super.shouldExecute();
	}

}
