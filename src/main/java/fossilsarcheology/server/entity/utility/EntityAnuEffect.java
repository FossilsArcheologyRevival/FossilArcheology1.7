package fossilsarcheology.server.entity.utility;

import fossilsarcheology.client.sound.FASoundRegistry;
import fossilsarcheology.server.block.FABlockRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityAnuEffect extends EntityLiving {

	private static final DataParameter<Integer> ROTATION = EntityDataManager.<Integer>createKey(EntityAnuEffect.class, DataSerializers.VARINT);
	public boolean slowed;
	public int deathTicks;
	private Entity target;

	public EntityAnuEffect(World world) {
		super(world);
		this.setSize(1.0F, 1.9F);
		this.isImmuneToFire = true;
		this.ignoreFrustumCheck = true;

	}

	public int getAnuRotation() {
		return this.dataManager.get(ROTATION);
	}

	public void setAnuRotation(int rotation) {
		this.dataManager.set(ROTATION, rotation);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setInteger("AnuRotation", this.getAnuRotation());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);
		this.setAnuRotation(par1NBTTagCompound.getInteger("AnuRotation"));
	}

	@Override
	public void travel(float par1, float par2, float vertical) {
		this.motionX *= 0.0D;
		this.motionY *= 0.0D;
		this.motionZ *= 0.0D;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(ROTATION, 0);
	}

	public void playSummonSong() {
		this.playSound(FASoundRegistry.ANU_TOTEM, 0.15F, 1F);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
	}

	/**
	 * handles entity death timer, experience orb and particle creation
	 */
	@Override
	protected void onDeathUpdate() {
		++this.deathTicks;

		if (this.deathTicks >= 160 && this.deathTicks <= 180) {
			float f = (this.rand.nextFloat() - 0.5F) * 8.0F;
			float f1 = (this.rand.nextFloat() - 0.5F) * 4.0F;
			float f2 = (this.rand.nextFloat() - 0.5F) * 8.0F;
			world.newExplosion(this, posX, posY, posZ, 3F, true, true);
		}

		int i;
		int j;

		if (!this.world.isRemote) {
			if (this.deathTicks > 150 && this.deathTicks % 5 == 0) {
				i = 1000;

				while (i > 0) {
					j = EntityXPOrb.getXPSplit(i);
					i -= j;
					// this.worldObj.spawnEntityInWorld(new
					// EntityXPOrb(this.worldObj, this.posX, this.posY,
					// this.posZ, j));
				}
			}

			if (this.deathTicks == 1) {
				// this.worldObj.playBroadcastSound(1018, (int)this.posX,
				// (int)this.posY, (int)this.posZ, 0);
			}
		}
		this.renderYawOffset = this.rotationYaw += 20.0F;

		if (this.deathTicks == 200 && !this.world.isRemote) {
			i = 2000;

			while (i > 0) {
				j = EntityXPOrb.getXPSplit(i);
				i -= j;
				this.world.spawnEntity(new EntityXPOrb(this.world, this.posX, this.posY, this.posZ, j));
			}

			this.createEnderPortal(MathHelper.floor(this.posX), MathHelper.floor(this.posY), MathHelper.floor(this.posZ));
			this.setDead();
		}
	}

	/**
	 * Creates the ender portal leading back to the normal world after defeating
	 * the enderdragon.
	 */
	private void createEnderPortal(int x, int y, int z) {
		world.setBlockState(new BlockPos(x, y, z), FABlockRegistry.ANU_PORTAL.getDefaultState());
		world.setBlockState(new BlockPos(x, y + 1, z), FABlockRegistry.ANU_PORTAL.getDefaultState());
		world.setBlockState(new BlockPos(x, y + 2, z), Blocks.OBSIDIAN.getDefaultState());
		world.setBlockState(new BlockPos(x, y - 1, z), Blocks.OBSIDIAN.getDefaultState());
	}

	@Override
	protected void despawnEntity() {
	}

	@Override
	public boolean canBeCollidedWith() {
		return false;
	}

	@Override
	protected float getSoundVolume() {
		return 5.0F;
	}
}