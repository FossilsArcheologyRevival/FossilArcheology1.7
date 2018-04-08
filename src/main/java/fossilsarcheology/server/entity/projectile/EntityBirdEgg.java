package fossilsarcheology.server.entity.projectile;

import fossilsarcheology.server.entity.prehistoric.EntityPrehistoric;
import fossilsarcheology.server.entity.prehistoric.PrehistoricEntityType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import java.util.Random;

public class EntityBirdEgg extends EntityThrowable {
    public Item item;
    PrehistoricEntityType type;
    boolean cultivated;

    public EntityBirdEgg(PrehistoricEntityType type, boolean cultivated, World par1World, Item item) {
        super(par1World);
        this.type = type;
        this.cultivated = cultivated;
        this.item = item;
    }

    public EntityBirdEgg(World par1World, EntityLivingBase par2EntityLivingBase, PrehistoricEntityType type, boolean cultivated, Item item) {
        super(par1World, par2EntityLivingBase);
        this.type = type;
        this.cultivated = cultivated;
        this.item = item;

    }

    public String getTexture() {

        return cultivated ? "fossil/items/prehistoric/birdEggs/Egg_" + type.toString() + ".png" : "fossil/items/prehistoric/birdEggs/Egg_Cultivated" + type.toString() + ".png";
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    @Override
    protected void onImpact(RayTraceResult par1MovingObjectPosition) {
        if (par1MovingObjectPosition.entityHit != null) {
            par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0);
        }
        if (this.cultivated) {
            this.spawnAnimal();

        } else {
            if (!this.world.isRemote && this.rand.nextInt(8) == 0) {
                byte b0 = 1;

                if (this.rand.nextInt(32) == 0) {
                    b0 = 4;
                }

                for (int i = 0; i < b0; ++i) {
                    this.spawnAnimal();

                }
                for (int j = 0; j < 8; ++j) {
                    this.world.spawnParticle(EnumParticleTypes.SNOWBALL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
                }
            }
        }

        if (!this.world.isRemote) {
            this.setDead();
        }

    }

    private void spawnAnimal() {
        if (type != PrehistoricEntityType.CHICKEN) {
            EntityPrehistoric mob = (EntityPrehistoric) type.invokeClass(world);
            if (!world.isRemote && mob != null) {
                mob.setAgeInDays(0);
                mob.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
                this.world.spawnEntity(mob);
                mob.setTamed(true);
                if(world.getClosestPlayerToEntity(mob, 5) != null){
                    mob.func_152115_b(world.getClosestPlayerToEntity(mob, 5).getName().toString());
                }
                mob.setGender(new Random().nextInt(1));
            }
        } else {
            EntityChicken mob = new EntityChicken(world);
            if (!world.isRemote && mob != null) {
                mob.setGrowingAge(-24000);
                mob.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
                this.world.spawnEntity(mob);
            }
        }
    }

}
