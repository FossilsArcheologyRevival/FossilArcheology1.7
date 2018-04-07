package fossilsarcheology.server.entity.utility;

import fossilsarcheology.server.entity.prehistoric.PrehistoricEntityType;
import net.ilexiconn.llibrary.server.entity.EntityProperties;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.nbt.NBTTagCompound;

public class FossilsMammalProperties extends EntityProperties<EntityAnimal> {

	public boolean isPregnant;
	public int embryoProgress;
	public PrehistoricEntityType embryo;

	@Override
	public void saveNBTData(NBTTagCompound compound) {
		compound.setBoolean("Pregnant", isPregnant);
		compound.setInteger("Embryo", embryo == null ? 0 : embryo.ordinal());
		compound.setInteger("EmbryoProgress", embryoProgress);

	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		this.isPregnant = compound.getBoolean("Pregnant");
		this.embryoProgress = compound.getInteger("EmbryoProgress");
		this.embryo = PrehistoricEntityType.values()[compound.getInteger("Embryo")];

	}

	@Override
	public void init() {
		isPregnant = false;
	}

	@Override
	public String getID() {
		return "Fossils - Mammal Tracker";
	}

	@Override
	public Class getEntityClass() {
		return EntityAnimal.class;
	}
}
