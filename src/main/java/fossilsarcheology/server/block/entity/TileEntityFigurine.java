package fossilsarcheology.server.block.entity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.tileentity.TileEntity;

public class TileEntityFigurine extends TileEntity {

	private int figurineType;
	private int figurineRotation;

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setByte("FigurineType", (byte) (this.figurineType & 255));
		par1NBTTagCompound.setByte("Rot", (byte) (this.figurineRotation & 255));
		return par1NBTTagCompound;
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		this.figurineType = par1NBTTagCompound.getByte("FigurineType");
		this.figurineRotation = par1NBTTagCompound.getByte("Rot");
	}

	@Override
	public void onDataPacket(NetworkManager netManager, net.minecraft.network.play.server.SPacketUpdateTileEntity packet) {
		readFromNBT(packet.getNbtCompound());
	}

	public int getFigurineType() {
		return this.figurineType;
	}

	public void setFigurineType(int par1) {
		this.figurineType = par1;
	}

	public void setFigurineRotation(int par1) {
		this.figurineRotation = par1;
	}

	public int getFigurineRotation() {
		return this.figurineRotation;
	}


}
