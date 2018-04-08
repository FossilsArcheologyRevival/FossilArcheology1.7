package fossilsarcheology.server.entity;

import fossilsarcheology.Revival;
import fossilsarcheology.server.entity.ai.FishAIWaterFindTarget;
import fossilsarcheology.server.entity.prehistoric.EntityNautilus;
import fossilsarcheology.server.entity.prehistoric.PrehistoricEntityType;
import fossilsarcheology.server.item.FAItemRegistry;
import net.ilexiconn.llibrary.client.model.tools.ChainBuffer;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigateSwimmer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public abstract class EntityFishBase extends EntityTameable {
    public PrehistoricEntityType selfType;
    public BlockPos currentTarget;
    @SideOnly(Side.CLIENT)
    public ChainBuffer chainBuffer;

    public EntityFishBase(World world, PrehistoricEntityType selfType) {
        super(world);
        this.moveHelper = new EntityFishBase.SwimmingMoveHelper();
        this.navigator = new PathNavigateSwimmer(this, world);
        this.tasks.addTask(1, new FishAIWaterFindTarget(this));
        this.tasks.addTask(2, new EntityAILookIdle(this));
        this.selfType = selfType;
        if (FMLCommonHandler.instance().getSide().isClient()) {
            this.chainBuffer = new ChainBuffer();
        }
    }

    public boolean isAIEnabled() {
        return true;
    }

    public abstract String getTexture();@Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    }

    private void setPedia() {
        Revival.PEDIA_OBJECT = this;
    }

    @Override
    protected Item getDropItem() {
        return Items.FISH;
    }

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    public void swimTowardsTarget() {
        if (currentTarget != null && isTargetInWater() && this.inWater) {
            double targetX = currentTarget.getX() + 0.5D - posX;
            double targetY = currentTarget.getY() + 1D - posY;
            double targetZ = currentTarget.getZ() + 0.5D - posZ;
            motionX += (Math.signum(targetX) * 0.5D - motionX) * 0.100000000372529 * getSwimSpeed(); // 0.10000000149011612D
            // /
            // 2;
            motionY += (Math.signum(targetY) * 0.5D - motionY) * 0.100000000372529 * getSwimSpeed();// 0.10000000149011612D
            // /
            // 2;
            motionZ += (Math.signum(targetZ) * 0.5D - motionZ) * 0.100000000372529 * getSwimSpeed(); // 0.10000000149011612D
            // /
            // 2;
            float angle = (float) (Math.atan2(motionZ, motionX) * 180.0D / Math.PI) - 90.0F;
            float rotation = MathHelper.wrapDegrees(angle - rotationYaw);
            moveForward = 0.5F;
            rotationYaw += rotation;
        }
    }

    protected abstract double getSwimSpeed();

    @Override
    public void onUpdate() {
        super.onUpdate();
        if(this.height != 1F){
            this.height = 1F;
        }
        Revival.PROXY.calculateChainBuffer(this);
        if (this.isInWater() && this.getClosestMate() != null && this.getGrowingAge() == 0 && this.getClosestMate().getGrowingAge() == 0 && !this.world.isRemote) {
            this.setGrowingAge(12000);
            this.getClosestMate().setGrowingAge(12000);
            this.world.spawnEntity(new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack(this.selfType.eggItem)));
        }
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata){
        this.setGrowingAge(12000);
        return super.onInitialSpawn(difficulty, livingdata);
    }

    public EntityFishBase getClosestMate() {
        EntityAINearestAttackableTarget.Sorter theNearestAttackableTargetSorter = new EntityAINearestAttackableTarget.Sorter(this);
        List<EntityFishBase> list = world.getEntitiesWithinAABB(EntityFishBase.class, this.getEntityBoundingBox().expand(2.0D, 2.0D, 2.0D), null);
        Collections.sort(list, theNearestAttackableTargetSorter);

        if (list.isEmpty()) {
            return null;
        } else {
            for (EntityFishBase entity : list) {
                if (entity != this)
                    return entity.selfType == this.selfType ? entity : null;
            }
            return null;
        }
    }

    public boolean isInsideNautilusShell() {
        return this instanceof EntityNautilus && ((EntityNautilus) this).isInShell();
    }

    public boolean isInWater(){
        return this.inWater || this.isChild() & this.isInsideOfMaterial(Material.WATER);
    }

    protected boolean isTargetInWater() {
        return currentTarget != null && (world.getBlockState(new BlockPos(currentTarget.getX(), currentTarget.getY(), currentTarget.getZ())).getMaterial() == Material.WATER && world.getBlockState(new BlockPos(currentTarget.getX(), currentTarget.getY() + 1, currentTarget.getZ())).getMaterial() == Material.WATER);
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.inventory.getCurrentItem();
        if (stack != null && FMLCommonHandler.instance().getSide().isClient() && stack.getItem() == FAItemRegistry.DINOPEDIA) {
            this.setPedia();
            player.openGui(Revival.INSTANCE, 6, this.world, (int) this.posX, (int) this.posY, (int) this.posZ);
            return true;
        }
        if (this.isInsideNautilusShell()) {
            if (stack != null && stack.getItem() == Items.FLINT) {
                ((EntityNautilus) this).setInShell(false);
                ((EntityNautilus) this).ticksToShell = 60;
            } else {
                this.playSound(SoundEvents.ENTITY_ITEM_BREAK, 1, this.getRNG().nextFloat() + 0.8F);
                return false;
            }
        }
        if (stack == null && this.getGrowingAge() > 0) {
            ItemStack var3 = new ItemStack(this.selfType.fishItem, 1);

            if (player.inventory.addItemStackToInventory(var3)) {
                if (!this.world.isRemote) {
                    this.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 1, this.getRNG().nextFloat() + 0.8F);
                    this.setDead();
                }

                return true;
            }
        }

        return super.processInteract(player, hand);
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    public boolean getCanSpawnHere() {
        return this.world.checkNoEntityCollision(this.getEntityBoundingBox());
    }

    public int getTalkInterval() {
        return 120;
    }

    protected boolean canDespawn() {
        return true;
    }

    protected int getExperiencePoints(EntityPlayer player) {
        return 1 + this.world.rand.nextInt(3);
    }

    public void onEntityUpdate() {
        int i = this.getAir();
        super.onEntityUpdate();
        if (!this.isInsideNautilusShell()) {
            if (this.isEntityAlive() && !this.isInWater()) {
                --i;
                this.setAir(i);

                if (this.getAir() == -20) {
                    this.setAir(0);
                    this.attackEntityFrom(DamageSource.DROWN, 2.0F);
                }
            } else {
                this.setAir(300);
            }
        }
    }

    @Override
    public boolean isOnLadder() {
        return false;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        this.renderYawOffset = this.rotationYaw;
    }

    public EntityAgeable createChild(EntityAgeable entity) {
        return null;
    }

    public boolean isDirectPathBetweenPoints(Vec3d vec1, Vec3d vec2) {
        RayTraceResult movingobjectposition = this.world.rayTraceBlocks(vec1, new Vec3d(vec2.x, vec2.y + (double) this.height * 0.5D, vec2.z), false, true, false);
        return movingobjectposition == null || movingobjectposition.typeOfHit != RayTraceResult.Type.BLOCK;
    }

    @Override
    public boolean shouldDismountInWater(Entity rider) {
        return false;
    }

    @Override
    public void travel(float strafe, float forward, float vertical) {
        if (this.isServerWorld()) {
            float f4;
            float f5;
            if (this.isInWater()) {
                this.moveRelative(strafe, vertical, forward, 0.1F);
                f4 = 0.8F;
                float d0 = (float) EnchantmentHelper.getDepthStriderModifier(this);
                if (d0 > 3.0F) {
                    d0 = 3.0F;
                }
                if (!this.onGround) {
                    d0 *= 0.5F;
                }
                if (d0 > 0.0F) {
                    f4 += (0.54600006F - f4) * d0 / 3.0F;
                }
                this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
                this.motionX *= (double) f4;
                this.motionX *= 0.900000011920929D;
                this.motionY *= 0.900000011920929D;
                this.motionZ *= 0.900000011920929D;
                this.motionZ *= (double) f4;
            } else {
                float f2 = 0.91F;
                if (this.onGround && this.isInWater()) {
                    this.onGround = false;
                }
                if (this.onGround) {
                    f2 = this.world.getBlockState(new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.getEntityBoundingBox().minY) - 1, MathHelper.floor(this.posZ))).getBlock().slipperiness * 0.91F;
                }
                float f3 = 0.16277136F / (f2 * f2 * f2);
                if (this.onGround) {
                    f4 = this.getAIMoveSpeed() * f3;
                } else {
                    f4 = this.jumpMovementFactor;
                }
                this.move(MoverType.SELF, strafe, forward, f4);
                f2 = 0.91F;
                if (this.onGround) {
                    f2 = this.world.getBlockState(new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.getEntityBoundingBox().minY) - 1, MathHelper.floor(this.posZ))).getBlock().slipperiness * 0.91F;
                }
                if (this.isOnLadder()) {
                        f5 = 0.15F;
                        this.motionX = MathHelper.clamp(this.motionX, (double) (-f5), (double) f5);
                        this.motionZ = MathHelper.clamp(this.motionZ, (double) (-f5), (double) f5);
                        this.fallDistance = 0.0F;
                        if (this.motionY < -0.15D) {
                            this.motionY = -0.15D;
                        }
                    }
                    this.move(MoverType.SELF, this.isInsideNautilusShell() ? 0 : this.motionX, this.isInsideNautilusShell() ? 0 : this.motionY, this.isInsideNautilusShell() ? 0 : this.motionZ);
                    if (this.collidedHorizontally && this.isOnLadder()) {
                        this.motionY = 0.2D;
                    }
                    if (this.world.isRemote && (!this.world.isBlockLoaded(new BlockPos((int) this.posX, 0, (int) this.posZ)) || !this.world.getChunkFromBlockCoords(new BlockPos((int) this.posX, 0, (int) this.posZ)).isLoaded())) {
                        if (this.posY > 0.0D) {
                            this.motionY = -0.1D;
                        } else {
                            this.motionY = 0.0D;
                        }
                    } else {
                        this.motionY -= 0.08D;
                    }
                    this.motionY *= 0.9800000190734863D;
                    this.motionX *= (double) f2;
                    this.motionZ *= (double) f2;
            }
        }
        this.prevLimbSwingAmount = this.limbSwingAmount;
        double deltaX = this.posX - this.prevPosX;
        double deltaZ = this.posZ - this.prevPosZ;
        float delta = MathHelper.sqrt(deltaX * deltaX + deltaZ * deltaZ) * 4.0F;
        if (delta > 1.0F) {
            delta = 1.0F;
        }
        this.limbSwingAmount += (delta - this.limbSwingAmount) * 0.4F;
        this.limbSwing += this.limbSwingAmount;
    }

    class SwimmingMoveHelper extends EntityMoveHelper {
        private EntityFishBase swimmingEntity = EntityFishBase.this;

        public SwimmingMoveHelper() {
            super(EntityFishBase.this);
        }

        @Override
        public void onUpdateMoveHelper() {
            if (this.action == EntityMoveHelper.Action.MOVE_TO && !this.swimmingEntity.getNavigator().noPath() && !swimmingEntity.isInsideNautilusShell()) {
                double distanceX = this.posX - this.swimmingEntity.posX;
                double distanceY = this.posY - this.swimmingEntity.posY;
                double distanceZ = this.posZ - this.swimmingEntity.posZ;
                double distance = Math.abs(distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ);
                distance = (double) MathHelper.sqrt(distance);
                distanceY /= distance;
                float angle = (float) (Math.atan2(distanceZ, distanceX) * 180.0D / Math.PI) - 90.0F;
                this.swimmingEntity.rotationYaw = this.limitAngle(this.swimmingEntity.rotationYaw, angle, 30.0F);
                this.swimmingEntity.setAIMoveSpeed((float)swimmingEntity.getSwimSpeed() * 0.5F);
                this.swimmingEntity.motionY += (double) this.swimmingEntity.getAIMoveSpeed() * distanceY * 0.1D;
            } else {
                this.swimmingEntity.setAIMoveSpeed(0.0F);
            }
        }
    }
}
