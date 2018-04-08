package fossilsarcheology.server.block.entity.block;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.tileentity.TileEntity;

public class TileEntityVolute extends TileEntity {

    private int vaseType;
    private int vaseRotation;

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("VaseType", (byte) (this.vaseType));
        par1NBTTagCompound.setInteger("Rot", (byte) (this.vaseRotation));
        return par1NBTTagCompound;
    }

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        this.vaseType = par1NBTTagCompound.getInteger("VaseType");
        this.vaseRotation = par1NBTTagCompound.getInteger("Rot");
    }
    @Override
    public void onDataPacket(NetworkManager netManager, net.minecraft.network.play.server.SPacketUpdateTileEntity packet) {
        readFromNBT(packet.getNbtCompound());
    }

    public int getVaseType() {
        return this.vaseType;
    }

    public int getVaseRotation() {
        return this.vaseRotation;
    }

    public void setVaseType(int par1) {
        this.vaseType = par1;
    }

    public void setVaseRotation(int par1) {
        this.vaseRotation = par1;
    }



}
