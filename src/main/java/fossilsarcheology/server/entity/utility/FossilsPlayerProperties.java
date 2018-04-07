package fossilsarcheology.server.entity.utility;

import net.ilexiconn.llibrary.server.entity.EntityProperties;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class FossilsPlayerProperties extends EntityProperties<EntityPlayer> {

	public boolean killedAnu;

	@Override
	public void saveNBTData(NBTTagCompound compound) {
		compound.setBoolean("KilledAnu", killedAnu);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		this.killedAnu = compound.getBoolean("KilledAnu");
	}

	@Override
	public void init() {
		killedAnu = false;
	}

	@Override
	public String getID() {
		return "Fossils - Player Tracker";
	}

	@Override
	public Class getEntityClass() {
		return EntityPlayer.class;
	}
}
