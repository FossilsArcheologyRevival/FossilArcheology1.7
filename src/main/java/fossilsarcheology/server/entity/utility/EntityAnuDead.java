package fossilsarcheology.server.entity.utility;

import fossilsarcheology.Revival;
import fossilsarcheology.client.sound.FASoundRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class EntityAnuDead extends EntityLiving {

	public boolean slowed;
	public int deathTicks = 0;
	public int deathTicks_animation = 0;
	public int maxLifespan = 940;
	private Entity target;

	public EntityAnuDead(World world) {
		super(world);
		this.setSize(1.8F, 0.8F);
		this.isImmuneToFire = true;
		this.ignoreFrustumCheck = true;
	}

	@Override
	public void travel(float par1, float par2, float vertical) {
		this.motionX *= 0.0D;
		this.motionY *= 0.0D;
		this.motionZ *= 0.0D;
	}


	public void playSummonSong() {
		this.playSound(FASoundRegistry.ANU_DEATH_EFFECT, 1F, 1F);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(300.0D);
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		if (player instanceof EntityPlayerMP) {
			EntityPlayerMP thePlayer = (EntityPlayerMP) player;

			if (thePlayer.timeUntilPortal > 0) {
				thePlayer.timeUntilPortal = 10;
			} else if (thePlayer.dimension != Revival.CONFIG.dimensionIDTreasure) {
				thePlayer.timeUntilPortal = 10;
				thePlayer.mcServer.getPlayerList().changePlayerDimension(thePlayer, Revival.CONFIG.dimensionIDTreasure);
			} else {
				thePlayer.timeUntilPortal = 10;
				thePlayer.mcServer.getPlayerList().changePlayerDimension(thePlayer, 0);
			}
		}
		return true;
	}

	/**
	 * handles entity death timer, experience orb and particle creation
	 */
	@Override
	protected void onDeathUpdate() {
		if (deathTicks < this.maxLifespan) {
			deathTicks++;
		}
		if (deathTicks == this.maxLifespan) {
			this.setDead();
		}
		if (deathTicks == 40) {
			this.playSummonSong();
		}
		for (int i = 0; i < 2; ++i) {
			if (world.isRemote) {
				Revival.PROXY.spawnAnuParticle(world, posX, posY, posZ);

			}
		}
	}

	@Override
	protected void despawnEntity() {
	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	@Override
	protected float getSoundVolume() {
		return 5.0F;
	}
}