package fossilsarcheology.server.entity.utility;

import fossilsarcheology.server.entity.monster.EntityFriendlyPigZombie;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.List;

public class EntityAncientLightning extends EntityLightningBolt {

	public long boltVertex;
	private int lightningState = 2;
	private int boltLivingTime;

	public EntityAncientLightning(World var1, double var2, double var4, double var6) {
		super(var1, var2, var4, var6, false);
		this.boltLivingTime = this.rand.nextInt(3) + 1;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (this.lightningState == 2) {
			this.world.playSound((EntityPlayer) null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_LIGHTNING_THUNDER, SoundCategory.WEATHER, 10000.0F, 0.8F + this.rand.nextFloat() * 0.2F);
			this.world.playSound((EntityPlayer) null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_LIGHTNING_IMPACT, SoundCategory.WEATHER, 2.0F, 0.5F + this.rand.nextFloat() * 0.2F);
		}

		--this.lightningState;

		if (this.lightningState < 0) {
			if (this.boltLivingTime == 0) {
				this.setDead();
			} else if (this.lightningState < -this.rand.nextInt(10)) {
				--this.boltLivingTime;
				this.lightningState = 1;
				this.boltVertex = this.rand.nextLong();

				if (this.world.isAreaLoaded(new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.posY), MathHelper.floor(this.posZ)), 10)) {
					int var1 = MathHelper.floor(this.posX);
					int var2 = MathHelper.floor(this.posY);
					int var3 = MathHelper.floor(this.posZ);
					BlockPos pos = new BlockPos(var1, var2, var3);
					if (this.world.isAirBlock(pos) && Blocks.FIRE.canPlaceBlockAt(this.world, pos)) {
						this.world.setBlockState(pos, Blocks.FIRE.getDefaultState());
					}
				}
			}
		}

		if (this.lightningState >= 0) {
			double var6 = 3.0D;
			List var7 = this.world.getEntitiesWithinAABBExcludingEntity(this, new AxisAlignedBB(this.posX - var6, this.posY - var6, this.posZ - var6, this.posX + var6, this.posY + 6.0D + var6, this.posZ + var6));

			for (int var4 = 0; var4 < var7.size(); ++var4) {
				Entity var5 = (Entity) var7.get(var4);

				if (!(var5 instanceof EntityPlayer) && !(var5 instanceof EntityFriendlyPigZombie) && !(var5 instanceof EntityPig)) {
					var5.onStruckByLightning(new EntityLightningBolt(this.world, this.posX, this.posY, this.posZ, false));
				}
			}

			this.world.setLastLightningBolt(2);
		}
	}
}
