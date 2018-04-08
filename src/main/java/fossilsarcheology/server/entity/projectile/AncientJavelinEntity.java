package fossilsarcheology.server.entity.projectile;

import fossilsarcheology.server.item.FAItemRegistry;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class AncientJavelinEntity extends JavelinEntity {
	private boolean lightning = false;

	public AncientJavelinEntity(World world) {
		super(world);
	}

	public AncientJavelinEntity(World world, int damage, double x, double y, double z) {
		super(world, Item.ToolMaterial.WOOD, damage, x, y, z);
	}

	public AncientJavelinEntity(World world, int damage, EntityLivingBase shooter) {
		super(world, Item.ToolMaterial.WOOD, damage, shooter);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setBoolean("LightningShot", this.lightning);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		this.lightning = compound.getBoolean("LightningShot");
	}

	@Override
	public void onUpdate() {
		if (this.inGround && !this.lightning) {
			if (this.rand.nextInt(100) < 30) {
				this.world.addWeatherEffect(new EntityLightningBolt(this.world, this.posX, this.posY, this.posZ, false));
			}
			this.lightning = true;
		}
		super.onUpdate();
	}

	@Override
	protected ItemStack getArrowStack() {
		return new ItemStack(FAItemRegistry.ANCIENT_JAVELIN, 1, FAItemRegistry.ANCIENT_JAVELIN.getMaxDamage() - this.damage);
	}
}
