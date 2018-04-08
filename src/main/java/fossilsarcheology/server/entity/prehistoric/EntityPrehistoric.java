package fossilsarcheology.server.entity.prehistoric;


import fossilsarcheology.Revival;
import fossilsarcheology.server.block.FABlockRegistry;
import fossilsarcheology.server.block.IDinoUnbreakable;
import fossilsarcheology.server.block.entity.TileEntityFeeder;
import fossilsarcheology.server.entity.EntityDinosaurEgg;
import fossilsarcheology.server.entity.utility.EntityToyBase;
import fossilsarcheology.server.item.FAItemRegistry;
import fossilsarcheology.server.item.variant.DinosaurBoneType;
import fossilsarcheology.server.localization.Localizations;
import fossilsarcheology.server.message.MessageFoodParticles;
import fossilsarcheology.server.message.MessageHappyParticles;
import fossilsarcheology.server.message.MessageUpdateEgg;
import fossilsarcheology.server.util.FoodMappings;
import net.ilexiconn.llibrary.client.model.tools.ChainBuffer;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationHandler;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class EntityPrehistoric extends EntityTameable implements IPrehistoricAI, IAnimatedEntity {

    public Animation SPEAK_ANIMATION;
    public Animation ATTACK_ANIMATION;
    public float minSize;
    public float maxSize;
    public int teenAge;
    public PrehistoricEntityType type;
    public ItemStack ItemInMouth = null;
    public OrderType currentOrder;
    public boolean hasFeatherToggle = false;
    public boolean featherToggle;
    public boolean hasTeenTexture = false;
    public boolean hasBabyTexture;
    public float weakProgress;
    public float sitProgress;
    public int ticksSitted;
    protected boolean isSitting;
    public float sleepProgress;
    public float climbProgress;
    public int ticksSlept;
    protected boolean isSleeping;
    protected boolean developsResistance;
    protected boolean breaksBlocks;
    private Animation currentAnimation;
    private int animTick;
    @SideOnly(Side.CLIENT)
    public ChainBuffer chainBuffer;
    public float pediaScale;
    public boolean mood_nospace;
    public boolean mood_noplants;
    protected int nearByMobsAllowed;
    public int ticksTillPlay;
    public int ticksTillMate;
    public int prevAge;
    public boolean isDaytime;
    public Flock flockObj;
    public double baseDamage;
    public double maxDamage;
    public double baseHealth;
    public double maxHealth;
    public double baseSpeed;
    public double maxSpeed;
    public float ridingXZ;
    public float ridingY;
    public float actualWidth;
    private static final DataParameter<Integer> AGETICK = EntityDataManager.<Integer>createKey(EntityPrehistoric.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> HUNGER = EntityDataManager.<Integer>createKey(EntityPrehistoric.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> MODELIZED = EntityDataManager.<Boolean>createKey(EntityPrehistoric.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> ANGRY = EntityDataManager.<Boolean>createKey(EntityPrehistoric.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> SUBSPECIES = EntityDataManager.<Integer>createKey(EntityPrehistoric.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> GENDER = EntityDataManager.<Integer>createKey(EntityPrehistoric.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> SLEEPING = EntityDataManager.<Boolean>createKey(EntityPrehistoric.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> MOOD = EntityDataManager.<Integer>createKey(EntityPrehistoric.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> SITTING = EntityDataManager.<Boolean>createKey(EntityPrehistoric.class, DataSerializers.BOOLEAN);
    private static final DataParameter<String> OWNERDISPLAYNAME = EntityDataManager.<String>createKey(EntityPrehistoric.class, DataSerializers.STRING);
    private static final DataParameter<Byte> CLIMBING = EntityDataManager.<Byte>createKey(EntityPrehistoric.class, DataSerializers.BYTE);

    public EntityPrehistoric(World world, PrehistoricEntityType type, double baseDamage, double maxDamage, double baseHealth, double maxHealth, double baseSpeed, double maxSpeed) {
        super(world);
        this.aiSit = new EntityAISit(this);
        this.setHunger(this.getMaxHunger() / 2);
        this.setScale(this.getAgeScale());
        SPEAK_ANIMATION = Animation.create(this.getSpeakLength());
        ATTACK_ANIMATION = Animation.create(this.getAttackLength());
        this.hasBabyTexture = true;
        this.type = type;
        this.pediaScale = 1.0F;
        this.nearByMobsAllowed = 15;
        this.currentOrder = OrderType.WANDER;
        if (ticksTillMate == 0) {
            ticksTillMate = this.rand.nextInt(6000) + 6000;
        }
        if (FMLCommonHandler.instance().getSide().isClient()) {
            this.chainBuffer = new ChainBuffer();
        }
        this.baseDamage = baseDamage;
        this.maxDamage = maxDamage;
        this.baseHealth = baseHealth;
        this.maxHealth = maxHealth;
        this.baseSpeed = baseSpeed;
        this.maxSpeed = maxSpeed;
        this.updateAbilities();
    }

    public static boolean isCannibalistic() {
        return false;
    }

    public int getSpeakLength() {
        return 20;
    }

    public int getAttackLength() {
        return 20;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(AGETICK, 0);
        this.dataManager.register(HUNGER, 0);
        this.dataManager.register(MODELIZED, false);
        this.dataManager.register(ANGRY, false);
        this.dataManager.register(SUBSPECIES, 0);
        this.dataManager.register(GENDER, 0);
        this.dataManager.register(SLEEPING, false);
        this.dataManager.register(CLIMBING, (byte)0);
        this.dataManager.register(MOOD, 0);
        this.dataManager.register(SITTING, false);
        this.dataManager.register(OWNERDISPLAYNAME, "");
    }


    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("AgeTick", this.getAgeInTicks());
        compound.setInteger("Hunger", this.getHunger());
        compound.setBoolean("isModelized", this.isSkeleton());
        compound.setBoolean("Angry", this.isAngry());
        compound.setInteger("SubSpecies", this.getSubSpecies());
        compound.setInteger("Gender", this.getGender());
        compound.setBoolean("Sleeping", this.isSleeping);
        compound.setInteger("Mood", this.getMood());
        compound.setBoolean("Sitting", this.isSitting);
        compound.setBoolean("MoodNoSpace", this.mood_nospace);
        compound.setBoolean("MoodNoPlants", this.mood_noplants);
        compound.setInteger("TicksSincePlay", this.ticksTillPlay);
        compound.setInteger("TicksSinceMate", this.ticksTillMate);
        compound.setByte("currentOrder", (byte) this.currentOrder.ordinal());
        compound.setString("OwnerDisplayName", this.getOwnerDisplayName());
    }

    public String getOwnerDisplayName() {
        return this.dataManager.get(OWNERDISPLAYNAME);
    }

    public void setOwnerDisplayName(String displayName) {
        this.dataManager.set(OWNERDISPLAYNAME, displayName);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1D);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setAgeinTicks(compound.getInteger("AgeTick"));
        this.setHunger(compound.getInteger("Hunger"));
        this.setSkeleton(compound.getBoolean("isModelized"));
        this.setAngry(compound.getBoolean("Angry"));
        this.setSubSpecies(compound.getInteger("SubSpecies"));
        this.setGender(compound.getInteger("Gender"));
        this.setSleeping(compound.getBoolean("Sleeping"));
        this.setSitting(compound.getBoolean("Sitting"));
        this.setMood(compound.getInteger("Mood"));
        if (compound.hasKey("currentOrder")) {
            this.setOrder(OrderType.values()[compound.getByte("currentOrder")]);
        }
        this.mood_nospace = compound.getBoolean("MoodNoSpace");
        this.mood_noplants = compound.getBoolean("MoodNoPlants");
        this.ticksTillPlay = compound.getInteger("TicksSincePlay");
        this.ticksTillMate = compound.getInteger("TicksSinceMate");
        String s = "";
        if (compound.hasKey("Owner", 8)) {
            s = compound.getString("Owner");
            this.setOwnerDisplayName(s);
        } else {
            this.setOwnerDisplayName(compound.getString("OwnerDisplayName"));
        }

    }

    public AxisAlignedBB getAttackBounds() {
        return this.getEntityBoundingBox().expand(3.0F, 3.0F, 3.0F);
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata){
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        Random random = new Random();
        this.setAgeInDays(this.getAdultAge());
        this.setSpawnValues();
        this.setGender(random.nextInt(2));
        this.updateAbilities();
        ticksTillPlay = 0;
        ticksTillMate = 24000;
        this.heal(this.getMaxHealth());
        return livingdata;
    }

    public void setActualSize(float width, float height) {
        this.actualWidth = width;
        this.setSize(width, height);
    }

    public boolean isAIDisabled() {
        return this.isSkeleton();
    }

    public void doPlayBonus(int playBonus) {
        ticksTillPlay = this.rand.nextInt(600) + 600;
        this.setMood(this.getMood() + playBonus);
        Revival.NETWORK_WRAPPER.sendToAll(new MessageHappyParticles(this.getEntityId()));
    }

    public abstract void setSpawnValues();

    public OrderType getOrderType() {
        return this.currentOrder;
    }

    @Override
    public boolean isMovementBlocked() {
        return this.getHealth() <= 0.0F || isSitting() || isSleeping() || this.isSkeleton() || this.isActuallyWeak();
    }

    @Override
    public boolean isSitting() {
        if (world.isRemote) {
            boolean isSitting = this.dataManager.get(SITTING);

            if ((isSitting != this.isSitting)) {
                ticksSitted = 0;
            }

            this.isSitting = isSitting;

            return isSitting;
        }

        return isSitting;
    }

    public boolean isSleeping() {
        if (world.isRemote) {
            boolean isSleeping = this.dataManager.get(SLEEPING);

            if ((isSleeping != this.isSleeping)) {
                ticksSlept = 0;
            }

            this.isSleeping = isSleeping;
            return isSleeping;
        }

        return isSleeping;
    }

    public BlockPos getBlockToEat(int range) {
        BlockPos pos;

        for (int r = 1; r <= range; r++) {
            for (int ds = -r; ds <= r; ds++) {
                for (int dy = 4; dy > -5; dy--) {
                    int x = MathHelper.floor(this.posX + ds);
                    int y = MathHelper.floor(this.posY + dy);
                    int z = MathHelper.floor(this.posZ - r);
                    if (this.posY + dy >= 0 && this.posY + dy <= this.world.getHeight() && FoodMappings.INSTANCE.getBlockFoodAmount(this.world.getBlockState(new BlockPos(x, y, z)).getBlock(), type.diet) != 0) {
                        pos = new BlockPos(x, y, z);
                        return pos;
                    }

                    if (this.posY + dy >= 0 && this.posY + dy <= this.world.getHeight() && FoodMappings.INSTANCE.getBlockFoodAmount(this.world.getBlockState(new BlockPos(x, y, z)).getBlock(), type.diet) != 0) {
                        pos = new BlockPos(x, y, z);
                        return pos;
                    }
                }
            }

            for (int ds = -r + 1; ds <= r - 1; ds++) {
                for (int dy = 4; dy > -5; dy--) {
                    int x = MathHelper.floor(this.posX + ds);
                    int y = MathHelper.floor(this.posY + dy);
                    int z = MathHelper.floor(this.posZ - r);

                    if (this.posY + dy >= 0 && this.posY + dy <= this.world.getHeight() && FoodMappings.INSTANCE.getBlockFoodAmount(this.world.getBlockState(new BlockPos(x, y, z)).getBlock(), type.diet) != 0) {
                        pos = new BlockPos(x, y, z);
                        return pos;
                    }

                    if (this.posY + dy >= 0 && this.posY + dy <= this.world.getHeight() && FoodMappings.INSTANCE.getBlockFoodAmount(this.world.getBlockState(new BlockPos(x, y, z)).getBlock(), type.diet) != 0) {
                        pos = new BlockPos(x, y, z);
                        return pos;
                    }
                }
            }
        }

        return null;
    }

    public void setOrder(OrderType var1) {
        this.currentOrder = var1;
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

    public TileEntityFeeder getNearestFeeder(int feederRange) {
        for (int dx = -2; dx != -(feederRange + 1); dx += (dx < 0) ? (dx * -2) : (-(2 * dx + 1))) {
            for (int dy = -5; dy < 4; dy++) {
                for (int dz = -2; dz != -(feederRange + 1); dz += (dz < 0) ? (dz * -2) : (-(2 * dz + 1))) {
                    if (this.posY + dy >= 0 && this.posY + dy <= this.world.getHeight()) {
                        TileEntity feeder = this.world.getTileEntity(new BlockPos(MathHelper.floor(this.posX + dx), MathHelper.floor(this.posY + dy), MathHelper.floor(this.posZ + dz)));

                        if (feeder != null && feeder instanceof TileEntityFeeder && !((TileEntityFeeder) feeder).isEmpty(type)) {
                            return (TileEntityFeeder) feeder;
                        }
                    }
                }
            }
        }

        return null;
    }

    public float getActualWidth() {
        return this.actualWidth * this.getAgeScale();
    }

    public boolean arePlantsNearby(int range) {
        for (int r = 1; r <= range; r++) {
            for (int ds = -r; ds <= r; ds++) {
                for (int dy = 4; dy > -5; dy--) {
                    int x = MathHelper.floor(this.posX + ds);
                    int y = MathHelper.floor(this.posY + dy);
                    int z = MathHelper.floor(this.posZ - r);
                    if (this.posY + dy >= 0 && this.posY + dy <= this.world.getHeight() && isPlantBlock(this.world.getBlockState(new BlockPos(x, y, z)))) {
                        return true;
                    }

                    if (this.posY + dy >= 0 && this.posY + dy <= this.world.getHeight() && isPlantBlock(this.world.getBlockState(new BlockPos(x, y, z)))) {
                        return true;
                    }
                }
            }
            for (int ds = -r + 1; ds <= r - 1; ds++) {
                for (int dy = 4; dy > -5; dy--) {
                    int x = MathHelper.floor(this.posX + ds);
                    int y = MathHelper.floor(this.posY + dy);
                    int z = MathHelper.floor(this.posZ - r);

                    if (this.posY + dy >= 0 && this.posY + dy <= this.world.getHeight() && isPlantBlock(this.world.getBlockState(new BlockPos(x, y, z)))) {
                        return true;
                    }

                    if (this.posY + dy >= 0 && this.posY + dy <= this.world.getHeight() && isPlantBlock(this.world.getBlockState(new BlockPos(x, y, z)))) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public boolean canBePushed() {
        return !this.isSkeleton() && super.canBePushed();
    }

    public int getNearestBubbleBlock(int range, int type) {
        for (int r = 1; r <= range; r++) {
            for (int ds = -r; ds <= r; ds++) {
                for (int dy = 4; dy > -5; dy--) {
                    int x = MathHelper.floor(this.posX + ds);
                    int y = MathHelper.floor(this.posY + dy);
                    int z = MathHelper.floor(this.posZ - r);
                    if (this.posY + dy >= 0 && this.posY + dy <= this.world.getHeight() && this.world.getBlockState(new BlockPos(x, y, z)).getBlock() == FABlockRegistry.BUBBLE_MACHINE && this.world.isBlockIndirectlyGettingPowered(new BlockPos(x, y, z)) > 0) {
                        switch (type) {
                            case 0:
                                return x;
                            case 1:
                                return y;
                            case 2:
                                return z;
                        }
                    }

                    if (this.posY + dy >= 0 && this.posY + dy <= this.world.getHeight() && this.world.getBlockState(new BlockPos(x, y, z)).getBlock() == FABlockRegistry.BUBBLE_MACHINE && this.world.isBlockIndirectlyGettingPowered(new BlockPos(x, y, z)) > 0) {
                        switch (type) {
                            case 0:
                                return x;
                            case 1:
                                return y;
                            case 2:
                                return z;
                        }
                    }
                }
            }

        }
        return 0;
    }

    public boolean isPlantBlock(IBlockState block) {
        return block.getMaterial() == Material.GRASS || block.getMaterial() == Material.PLANTS || block.getMaterial() == Material.LEAVES;
    }

    public boolean canSleep() {
        return !(!this.onGround && ticksExisted % 20 != 0) && !this.isInWater() && (this.aiActivityType() == PrehistoricEntityTypeAI.Activity.DIURINAL && !this.isDaytime() || this.aiActivityType() == PrehistoricEntityTypeAI.Activity.NOCTURNAL && this.isDaytime() && !this.world.canSeeSky(new BlockPos(MathHelper.floor(this.posX), (int) this.getEntityBoundingBox().minY, MathHelper.floor(this.posZ))) || this.aiActivityType() == PrehistoricEntityTypeAI.Activity.BOTH && this.getRNG().nextInt(600) == 0);
    }

    public boolean isDaytime() {
        return this.world.isDaytime();
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.isSkeleton()) {
            this.motionX *= 0;
            this.motionY *= 0;
            this.motionZ *= 0;
        }
        if (this.getOwner() != null && this.getOwnerDisplayName().equals("")) {
            this.setOwnerDisplayName(this.getOwner().getDisplayName().toString());
        }
        if (this.getHunger() > this.getMaxHunger()) {
            this.setHunger(this.getMaxHunger());
        }
        if (this.getMood() > 100) {
            this.setMood(100);
        }
        if (this.getMood() < -100) {
            this.setMood(-100);
        }
        if (this.ticksTillPlay > 0) {
            this.ticksTillPlay--;
        }
        if (this.ticksTillMate > 0) {
            this.ticksTillMate--;
        }
        if (this.getRidingPlayer() != null) {
            this.stepHeight = 1;
        }
        int blockX = MathHelper.floor(this.posX);
        int blockY = MathHelper.floor(this.getEntityBoundingBox().minY) - 1;
        int blockZ = MathHelper.floor(this.posZ);
        if (this.getBlockUnder() == FABlockRegistry.BUBBLE_MACHINE && this.world.isBlockIndirectlyGettingPowered(new BlockPos(blockX, blockY, blockZ)) > 0 && this.ticksTillPlay == 0) {
            this.jump();
            for (int i = 0; i < 1; ++i) {
                double dd = this.getRNG().nextGaussian() * 0.02D;
                double dd1 = this.getRNG().nextGaussian() * 0.02D;
                double dd2 = this.getRNG().nextGaussian() * 0.02D;
                Revival.PROXY.spawnPacketHeartParticles(this.world, (float) (this.posX + (this.getRNG().nextFloat() * this.width * 2.0F) - this.width), (float) (this.posY + 0.5D + (this.getRNG().nextFloat() * this.height)), (float) (this.posZ + (this.getRNG().nextFloat() * this.width * 2.0F) - this.width), dd, dd1, dd2);
            }
            this.doPlayBonus(15);
        }
        if (ticksTillMate == 0 && this.getGender() == 1) {
            this.mate();
        }
        if (!this.arePlantsNearby(16) && !mood_noplants) {
            boolean inital_mood_noplants = mood_noplants;
            this.mood_noplants = true;
            if (mood_noplants != inital_mood_noplants) {
                this.setMood(this.getMood() - 50);
            }
        }
        if (this.arePlantsNearby(16)) {
            boolean inital_mood_noplants = mood_noplants;
            this.mood_noplants = false;
            if (mood_noplants != inital_mood_noplants) {
                this.setMood(this.getMood() + 50);
            }
        }

        if (this.isThereNearbyTypes() && !mood_nospace) {
            boolean inital_mood_nospace = mood_nospace;
            this.mood_nospace = true;
            if (mood_nospace != inital_mood_nospace) {
                this.setMood(this.getMood() - 50);
            }
        }
        if (!this.isThereNearbyTypes()) {
            boolean inital_mood_nospace = mood_nospace;
            this.mood_nospace = false;
            if (mood_nospace != inital_mood_nospace) {
                this.setMood(this.getMood() + 50);
            }
        }

        if (this.isSitting()) {
            ticksSitted++;
        }
        if (this.isSleeping()) {
            ticksSlept++;
        }

        if (!world.isRemote && !this.isInWater() && !this.isBeingRidden() && !this.isSitting() && this.getRNG().nextInt(100) == 1 && !this.isRiding() && (this.getAnimation() == NO_ANIMATION || this.getAnimation() == SPEAK_ANIMATION) && !this.isSleeping()) {
            this.setSitting(true);
            ticksSitted = 0;
        }

        if (!world.isRemote && !this.isInWater() && (this.isSitting() && ticksSitted > 100 && this.getRNG().nextInt(100) == 1 || this.getAttackTarget() != null) && !this.isSleeping()) {
            this.setSitting(false);
            ticksSitted = 0;
        }
        if (!world.isRemote && !this.isInWater() && !this.isBeingRidden() && !this.isActuallyWeak() && this.canSleep() && this.getRNG().nextInt(100) == 1 && this.getAttackTarget() == null && (this.getAnimation() == NO_ANIMATION || this.getAnimation() == SPEAK_ANIMATION)) {
            this.setSitting(false);
            this.setSleeping(true);
            ticksSlept = 0;
        }

        if (!world.isRemote && (!this.canSleep() || this.isActuallyWeak() || (this.isSleeping() && ticksSlept > 200 && this.getRNG().nextInt(1000) == 1 || this.getAttackTarget() != null || this.isInWater()))) {
            this.setSitting(false);
            this.setSleeping(false);
            ticksSlept = 0;
        }

        if (this.currentOrder == OrderType.STAY && !this.isSitting() && !this.isActuallyWeak()) {
            this.setSitting(true);
            this.setSleeping(false);
        }

        if (breaksBlocks) {
            this.breakBlock(5);
        }

        if (this.doesFlock() && flockObj == null) {
            if (this.getNearbyFlock() != null) {
                this.getNearbyFlock().flockMembers.add(this);
            } else {
                flockObj = new Flock();
                flockObj.createFlock(this);
            }
        }
        if (this.flockObj != null) {
            if (this == flockObj.flockLeader) {
                this.flockObj.onUpdate();
            }
        }
    }

    public EntityLivingBase getOwner() {
        return this.world.getPlayerEntityByName(this.getOwnerDisplayName());
    }

    public boolean isBreedingItem(ItemStack stack) {
        return false;
    }

    public Block getBlockUnder() {
        int blockX = MathHelper.floor(this.posX);
        int blockY = MathHelper.floor(this.getEntityBoundingBox().minY) - 1;
        int blockZ = MathHelper.floor(this.posZ);
        return this.world.getBlockState(new BlockPos(blockX, blockY, blockZ)).getBlock();
    }

    public EntityPrehistoric findFlockLeader(List<EntityPrehistoric> flock) {
        int index = new Random().nextInt(flock.size());
        return flock.get(index);
    }

    public EntityPlayer getRidingPlayer() {
        if (this.getControllingPassenger() instanceof EntityPlayer) {
            return (EntityPlayer) getControllingPassenger();
        } else {
            return null;
        }
    }

    public void setRidingPlayer(EntityPlayer player) {
        player.rotationYaw = this.rotationYaw;
        player.rotationPitch = this.rotationPitch;
        player.startRiding(this);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        this.setAgeinTicks(this.getAgeInTicks() + 1);
        if (this.getAgeInTicks() % 24000 == 0) {
            this.updateAbilities();
        }

        if (this.getAgeInTicks() % 1200 == 0 && this.getHunger() > 0 && Revival.CONFIG.starvingDinos) {
            this.setHunger(this.getHunger() - 1);
        }

        boolean sitting = isSitting();
        if (sitting && sitProgress < 20.0F) {
            sitProgress += 0.5F;
            if (sleepProgress != 0) {
                sleepProgress = 0F;
            }
        } else if (!sitting && sitProgress > 0.0F) {
            sitProgress -= 0.5F;
            if (sleepProgress != 0) {
                sleepProgress = 0F;
            }
        }
        boolean sleeping = isSleeping();
        if (sleeping && sleepProgress < 20.0F) {
            sleepProgress += 0.5F;
            if (sitProgress != 0) {
                sitProgress = 0F;
            }
        } else if (!sleeping && sleepProgress > 0.0F) {
            sleepProgress -= 0.5F;
            if (sitProgress != 0) {
                sitProgress = 0F;
            }
        }
        boolean climbing = this.aiClimbType() == PrehistoricEntityTypeAI.Climbing.ARTHROPOD && (this.isBesideClimbableBlock() && !this.onGround);
        if (climbing && climbProgress < 20.0F) {
            climbProgress += 1F;
            if (sitProgress != 0) {
                sitProgress = 0F;
            }
        } else if (!climbing && climbProgress > 0.0F) {
            climbProgress -= 1F;
            if (sitProgress != 0) {
                sitProgress = 0F;
            }
        }
        boolean weak = this.isActuallyWeak();
        if (weak && weakProgress < 20.0F) {
            weakProgress += 0.5F;
            sitProgress = 0F;
            sleepProgress = 0F;
        } else if (!weak && weakProgress > 0.0F) {
            weakProgress -= 0.5F;
            sitProgress = 0F;
            sleepProgress = 0F;
        }
        if (!this.world.isRemote) {
            if (this.aiClimbType() == PrehistoricEntityTypeAI.Climbing.ARTHROPOD) {
                this.setBesideClimbableBlock(this.collidedHorizontally);
            } else {
                this.setBesideClimbableBlock(false);
            }
        }
        Revival.PROXY.calculateChainBuffer(this);
        AnimationHandler.INSTANCE.updateAnimations(this);
    }

    public abstract PrehistoricEntityTypeAI.Activity aiActivityType();

    public abstract PrehistoricEntityTypeAI.Attacking aiAttackType();

    public abstract PrehistoricEntityTypeAI.Climbing aiClimbType();

    public abstract PrehistoricEntityTypeAI.Following aiFollowType();

    public abstract PrehistoricEntityTypeAI.Jumping aiJumpType();

    public abstract PrehistoricEntityTypeAI.Response aiResponseType();

    public abstract PrehistoricEntityTypeAI.Stalking aiStalkType();

    public abstract PrehistoricEntityTypeAI.Taming aiTameType();

    public abstract PrehistoricEntityTypeAI.Untaming aiUntameType();

    public abstract PrehistoricEntityTypeAI.Moving aiMovingType();

    public abstract PrehistoricEntityTypeAI.WaterAbility aiWaterAbilityType();

    public abstract int getAdultAge();

    public abstract boolean doesFlock();

    @Override
    public boolean canAttackClass(Class clazz) {
        return this.getClass() != clazz && clazz != EntityDinosaurEgg.class;
    }

    public float getAgeScale() {
        float step = (this.maxSize - this.minSize) / ((this.getAdultAge() * 24000) + 1);
        if (this.getAgeInTicks() > this.getAdultAge() * 24000) {
            return this.minSize + ((step) * this.getAdultAge() * 24000);
        }
        return this.minSize + ((step * this.getAgeInTicks()));
    }

    @Override
    protected int getExperiencePoints(EntityPlayer par1EntityPlayer) {
        return MathHelper.floor(this.type.experience + (float) this.getAgeInDays() * this.type.experienceIncrement);
    }

    public void updateAbilities() {
        double healthStep = (maxHealth - baseHealth) / (this.getAdultAge());
        double attackStep = (maxDamage - baseDamage) / (this.getAdultAge());
        double speedStep = (maxSpeed - baseSpeed) / (this.getAdultAge());
        if (this.getAgeInDays() <= this.getAdultAge()) {
            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(Math.round(baseHealth + (healthStep * this.getAgeInDays())));
            this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(Math.round(baseDamage + (attackStep * this.getAgeInDays())));
            this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(baseSpeed + (speedStep * this.getAgeInDays()));
            if (this.developsResistance) {
                if (this.isTeen()) {
                    this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.5D);
                } else if (this.isAdult()) {
                    this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(2.0D);
                } else {
                    this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.0D);
                }
            }
        }
    }

    public void breakBlock(float hardness) {
        if (Revival.CONFIG.dinoBlockBreaking) {
            if (!isSkeleton() && this.isAdult() && this.isHungry()) {
                for (int a = (int) Math.round(this.getEntityBoundingBox().minX) - 1; a <= (int) Math.round(this.getEntityBoundingBox().maxX) + 1; a++) {
                    for (int b = (int) Math.round(this.getEntityBoundingBox().minY) + 1; (b <= (int) Math.round(this.getEntityBoundingBox().maxY) + 3) && (b <= 127); b++) {
                        for (int c = (int) Math.round(this.getEntityBoundingBox().minZ) - 1; c <= (int) Math.round(this.getEntityBoundingBox().maxZ) + 1; c++) {
                            IBlockState state = world.getBlockState(new BlockPos(a, b, c));
                            Block block = state.getBlock();
                            if (!(block instanceof BlockBush) && !(block instanceof BlockLiquid) && block != Blocks.BEDROCK && !(block instanceof IDinoUnbreakable) && state.getBlockHardness(world, new BlockPos(a, b, c)) < hardness) {
                                this.motionX *= 0.6D;
                                this.motionZ *= 0.6D;
                                if (block != Blocks.AIR) {
                                    if (!world.isRemote) {
                                        world.destroyBlock(new BlockPos(a, b, c), true);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void setScaleForAge(boolean child) {
        if (ticksExisted % 20 == 0) {
            this.setScale(this.getAgeScale());
        }
    }

    public Entity createEgg(EntityAgeable entity) {
        Entity baby = null;
        if (this.type.mobType == MobType.MAMMAL) {
            baby = this.type.invokeClass(this.world);
        }
        if (this.type.mobType == MobType.BIRD) {
            baby = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack(this.type.birdEggItem));
        }
        if (this.type.mobType == MobType.DINOSAUR) {
            if (Revival.CONFIG.eggsLikeChickens) {
                baby = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack(this.type.eggItem));
            } else {
                baby = new EntityDinosaurEgg(this.world, this.type);
                ((EntityDinosaurEgg) baby).selfType = this.type;
            }
        }
        return baby;
    }

    public boolean isAdult() {
        return this.getAgeInDays() >= getAdultAge();
    }

    public boolean isTeen() {
        return this.getAgeInDays() >= teenAge && this.getAgeInDays() < getAdultAge();
    }

    @Override
    public boolean isChild() {
        return this.getAgeInDays() < teenAge && !this.isSkeleton();
    }

    public abstract int getMaxHunger();

    public boolean isSkeleton() {
        return this.dataManager.get(MODELIZED);
    }

    public void setSkeleton(boolean skeleton) {
        this.dataManager.set(MODELIZED, skeleton);
    }

    public int getAgeInDays() {
        return this.dataManager.get(AGETICK) / 24000;
    }

    public void setAgeInDays(int days) {
        this.dataManager.set(AGETICK, days * 24000);
        this.updateAbilities();
    }

    public int getAgeInTicks() {
        return  this.dataManager.get(AGETICK);
    }

    public void setAgeinTicks(int ticks) {
        this.dataManager.set(AGETICK, ticks);
    }

    public int getHunger() {
        return this.dataManager.get(HUNGER);
    }

    public void setHunger(int hunger) {
        if (this.getHunger() > this.getMaxHunger()) {
            this.dataManager.set(HUNGER, this.getMaxHunger());
        } else {
            this.dataManager.set(HUNGER, hunger);
        }
    }

    public boolean increaseHunger(int hunger) {
        if (this.getHunger() >= this.getMaxHunger()) {
            return false;
        }
        this.setHunger(this.getHunger() + hunger);
        if (this.getHunger() > this.getMaxHunger()) {
            this.setHunger(this.getMaxHunger());
        }
        this.world.playSound(null, this.getPosition(), SoundEvents.ENTITY_GENERIC_EAT, SoundCategory.NEUTRAL, this.getSoundVolume(), this.getSoundPitch());
        return true;
    }

    @Override
    public void onKillEntity(EntityLivingBase var1) {
        super.onKillEntity(var1);
        this.increaseHunger(FoodMappings.INSTANCE.getEntityFoodAmount(var1.getClass(), this.type.diet));
        this.heal(FoodMappings.INSTANCE.getEntityFoodAmount(var1.getClass(), this.type.diet) / 3);
        this.setMood(this.getMood() + 25);
    }

    public boolean isHungry() {
        return this.getHunger() < this.getMaxHunger() * 0.75F;
    }

    public boolean IsDeadlyHungry() {
        return this.getHunger() < this.getMaxHunger() * 0.25F;
    }

    public void sendStatusMessage(SituationType var1) {
        if (this.getOwner() != null && this.getDistance(this.getOwner()) < 50.0F) {
            String Status1 = I18n.format(("status." + var1.toString() + ".head"));
            String Dino = this.type.toString();
            String Status2 = I18n.format("status." + var1.toString());
            ((EntityPlayer) this.getOwner()).sendStatusMessage(new TextComponentString(Status1 + Dino + Status2), false);
        }
    }

    @Override
    public boolean isOnLadder() {
        if (this.aiMovingType() == PrehistoricEntityTypeAI.Moving.AQUATIC || this.aiMovingType() == PrehistoricEntityTypeAI.Moving.SEMIAQUATIC) {
            return false;
        } else {
            return this.aiClimbType() == PrehistoricEntityTypeAI.Climbing.ARTHROPOD && this.isBesideClimbableBlock();
        }
    }

    public boolean isAngry() {
        return this.dataManager.get(ANGRY);
    }

    public void setAngry(boolean angry) {
       this.dataManager.set(ANGRY, angry);
    }

    public int getSubSpecies() {
        return this.dataManager.get(SUBSPECIES);
    }

    public void setSubSpecies(int subspecies) {
        this.dataManager.set(SUBSPECIES, subspecies);
    }

    public int getGender() {
        return this.dataManager.get(GENDER);
    }

    public void setGender(int gender) {
        this.dataManager.set(GENDER, gender);
    }

    public void setSleeping(boolean sleeping) {
        this.dataManager.set(SLEEPING, sleeping);
        if (!world.isRemote) {
            this.isSleeping = sleeping;
        }
    }

    public int getMood() {
        return this.dataManager.get(MOOD);
    }

    public void setMood(int mood) {
        this.dataManager.set(MOOD, mood);
    }

    public PrehistoricMoodType getMoodFace() {
        if (this.getMood() == 100) {
            return PrehistoricMoodType.HAPPY;
        } else if (this.getMood() >= 50) {
            return PrehistoricMoodType.CONTENT;
        } else if (this.getMood() == -100) {
            return PrehistoricMoodType.ANGRY;
        } else if (this.getMood() <= -50) {
            return PrehistoricMoodType.SAD;
        } else {
            return PrehistoricMoodType.CALM;
        }
    }

    public int getScaledMood() {
        return (int) (71 * -(this.getMood() * 0.01));
    }

    @Override
    public void setSitting(boolean sitting) {
        super.setSitting(sitting);

        if (!world.isRemote) {
            this.isSitting = sitting;
        }
    }

    @Override
    public boolean shouldDismountInWater(Entity rider) {
        return false;
    }

    @Override
    public boolean attackEntityFrom(DamageSource dmg, float i) {

        if (i > 0 && this.isSkeleton()) {
            if (dmg == DamageSource.IN_WALL) {
                return false;
            } else {
                this.world.playSound(null, this.getPosition(), SoundEvents.ENTITY_SKELETON_HURT, SoundCategory.NEUTRAL, this.getSoundVolume(), this.getSoundPitch());
                if (!world.isRemote) {
                    if (this.type.timePeriod == TimePeriod.CENOZOIC) {
                        this.dropItem(FAItemRegistry.TAR_FOSSIL, 1);
                    } else {
                        this.dropItem(FAItemRegistry.BIOFOSSIL, 1);
                    }
                    this.entityDropItem(new ItemStack(Items.BONE, Math.min(this.getAgeInDays(), this.getAdultAge()), 1), 1);
                }
                this.setDead();
                return false;
            }
        }
        if (this.getLastAttackedEntity() instanceof EntityPlayer) {
            if (this.getOwner() == this.getLastAttackedEntity()) {
                this.setTamed(false);
                this.setMood(this.getMood() - 15);
                this.sendStatusMessage(SituationType.Betrayed);
            }
        }

        if (i > 0) {
            this.setSitting(false);
            this.setSleeping(false);
        }
        if (dmg.getTrueSource() != null) {
            this.setMood(this.getMood() - 5);
        }
        if (this.getHurtSound(DamageSource.GENERIC) != null) {
            if (this.getAnimation() != null) {
                if (this.getAnimation() == NO_ANIMATION && world.isRemote) {
                    this.setAnimation(SPEAK_ANIMATION);
                }
            }
        }
        super.attackEntityFrom(dmg, i);
        return super.attackEntityFrom(dmg, i);
    }

    public static boolean isEntitySmallerThan(Entity entity, float size) {
        if (entity instanceof EntityPrehistoric) {
            return ((EntityPrehistoric) entity).getActualWidth() <= size;
        } else {
            return entity.width <= size;
        }
    }

    public boolean isBesideClimbableBlock()
    {
        return (((Byte)this.dataManager.get(CLIMBING)).byteValue() & 1) != 0;
    }

    public void setBesideClimbableBlock(boolean climbing) {
        byte b0 = ((Byte)this.dataManager.get(CLIMBING)).byteValue();

        if (climbing) {
            b0 = (byte)(b0 | 1);
        }
        else {
            b0 = (byte)(b0 & -2);
        }

        this.dataManager.set(CLIMBING, Byte.valueOf(b0));
    }

    @Override
    public void fall(float distance, float damageMultiplier) {
        if (this.aiClimbType() == PrehistoricEntityTypeAI.Climbing.ARTHROPOD || this.aiMovingType() == PrehistoricEntityTypeAI.Moving.WALKANDGLIDE || this.aiMovingType() == PrehistoricEntityTypeAI.Moving.FLIGHT) {

        } else {
            super.fall(distance, damageMultiplier);
        }
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        ItemStack itemstack = player.inventory.getCurrentItem();

        if (this.isSkeleton()) {
            if (itemstack == ItemStack.EMPTY) {
                if (player.isSneaking()) {
                    this.nudgeEntity(player);
                } else {
                    this.faceEntity(player, 360.0F, 360.0F);
                }
            } else {
                if (itemstack.getItem() == Items.BONE && this.getAgeInDays() < this.getAdultAge()) {
                    this.world.playSound(null, this.getPosition(), SoundEvents.ENTITY_SKELETON_AMBIENT, SoundCategory.NEUTRAL, 0.8F, 1);
                    this.setAgeInDays(this.getAgeInDays() + 1);
                    if (!player.capabilities.isCreativeMode) {
                        itemstack.shrink(1);
                    }
                    return true;
                }
            }

        } else {

            if (itemstack != null) {
                if (itemstack.getItem() != null) {
                    if ((this.aiTameType() == PrehistoricEntityTypeAI.Taming.GEM && itemstack.getItem() == FAItemRegistry.SCARAB_GEM) || (this.aiTameType() == PrehistoricEntityTypeAI.Taming.BLUEGEM && itemstack.getItem() == FAItemRegistry.AQUATIC_SCARAB_GEM)) {
                        if (!this.isTamed() && !this.func_152114_e(player) && this.isActuallyWeak()) {
                            this.triggerTamingAcheivement(player);
                            this.heal(200);
                            this.setMood(100);
                            this.increaseHunger(500);
                            this.setTamed(true);
                            this.getNavigator().clearPath();
                            setAttackTarget(null);
                            this.func_152115_b(player.getDisplayName().toString());
                            itemstack.shrink(1);
                            return true;
                        }
                    }
                }
            }

            if (itemstack != null) {
                if (itemstack.getItem() == FAItemRegistry.CHICKEN_ESSENCE && !player.world.isRemote) {
                    if (this.getAgeInDays() < this.getAdultAge() && this.getHunger() > 0) {
                        if (this.getHunger() > 0) {
                            itemstack.shrink(1);
                            if (!player.capabilities.isCreativeMode) {
                                player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE, 1));
                            }
                            Revival.NETWORK_WRAPPER.sendToAll(new MessageFoodParticles(getEntityId(), Item.getIdFromItem(FAItemRegistry.CHICKEN_ESSENCE)));
                            this.setAgeInDays(this.getAgeInDays() + 1);
                            this.setHunger(1 + (new Random()).nextInt(this.getHunger()));
                            this.func_152115_b(player.getDisplayName().toString());
                            return true;
                        }
                    }

                    if (!this.world.isRemote) {
                        player.sendStatusMessage(new TextComponentString(I18n.format(Localizations.STATUS_ESSENCE_FAIL)), false);
                    }

                    return false;
                }

                if (FoodMappings.INSTANCE.getItemFoodAmount(itemstack, this.type.diet) != 0) {
                    if (!player.world.isRemote) {
                        if (this.getMaxHunger() > this.getHunger() || this.getHealth() > this.getMaxHealth() && Revival.CONFIG.healingDinos) {

                            this.setHunger(this.getHunger() + FoodMappings.INSTANCE.getItemFoodAmount(itemstack, this.type.diet));
                            if (!world.isRemote) {
                                this.eatItem(itemstack);
                            }
                            if (Revival.CONFIG.healingDinos) {
                                this.heal(3);
                            }
                            if (this.getHunger() >= this.getMaxHunger()) {
                                if (this.isTamed()) {
                                    this.sendStatusMessage(SituationType.Full);
                                }

                            }

                            itemstack.shrink(1);
                            if (this.aiTameType() == PrehistoricEntityTypeAI.Taming.FEEDING) {
                                if (!this.isTamed() && this.type.isTameable() && (new Random()).nextInt(10) == 1) {
                                    this.setTamed(true);
                                    this.func_152115_b(player.getDisplayName().toString());
                                    this.world.setEntityState(this, (byte) 35);
                                }
                            }

                            return true;
                        } else {
                            if (this.ItemInMouth == null) {
                                return true;
                            }
                        }
                    }

                    return false;
                } else {

                    if (itemstack.getItem() == Items.LEAD && this.isTamed()) {
                        if (func_152114_e(player)) {
                            this.setLeashHolder(player, true);
                            itemstack.shrink(1);
                            return true;
                        }
                    }

                    if (FMLCommonHandler.instance().getSide().isClient() && itemstack.getItem() == FAItemRegistry.DINOPEDIA) {
                        this.setPedia();
                        player.openGui(Revival.INSTANCE, 6, this.world, (int) this.posX, (int) this.posY, (int) this.posZ);
                        return true;
                    }

                    if (itemstack.getItem() == FAItemRegistry.WHIP && this.aiTameType() != PrehistoricEntityTypeAI.Taming.NONE && this.isAdult() && !this.world.isRemote) {
                        if (this.isTamed() && func_152114_e(player) && this.canBeRidden()) {
                            if (this.getRidingPlayer() == null) {
                                Revival.NETWORK_WRAPPER.sendToAll(new MessageFoodParticles(getEntityId(), FABlockRegistry.VOLCANIC_ROCK));
                                this.setOrder(OrderType.WANDER);
                                setRidingPlayer(player);
                                this.setSitting(false);
                                this.setSleeping(false);
                            } else if (this.getRidingPlayer() == player) {
                                this.setSprinting(true);
                                Revival.NETWORK_WRAPPER.sendToAll(new MessageFoodParticles(getEntityId(), FABlockRegistry.VOLCANIC_ROCK));
                                this.setMood(this.getMood() - 1);
                            }
                        } else if (!this.isTamed() && this.aiTameType() != PrehistoricEntityTypeAI.Taming.BLUEGEM && this.aiTameType() != PrehistoricEntityTypeAI.Taming.GEM) {
                            this.setMood(this.getMood() - 1);
                            Revival.NETWORK_WRAPPER.sendToAll(new MessageFoodParticles(getEntityId(), FABlockRegistry.VOLCANIC_ROCK));
                            if (getRNG().nextInt(5) == 0) {
                                player.sendStatusMessage(new TextComponentString(I18n.format("prehistoric.autotame") + this.getDisplayName().toString() + I18n.format("prehistoric.period")), false);
                                this.setMood(this.getMood() - 25);
                                this.setTamed(true);
                                Revival.NETWORK_WRAPPER.sendToAll(new MessageFoodParticles(getEntityId(), Item.getIdFromItem(Items.GOLD_INGOT)));
                                this.func_152115_b(player.getDisplayName().toString());
                            }
                        }
                        this.setSitting(false);
                        // this.setOrder(OrderType.WANDER);

                        // this.currentOrder = OrderType.FreeMove;
                        // setRidingPlayer(player);
                    }
                    if (this.getOrderItem() != null && itemstack.getItem() == this.getOrderItem() && this.isTamed() && this.getOwnerDisplayName().equals(player.getDisplayName().toString()) && !player.isRiding()) {
                        if (!this.world.isRemote) {
                            this.isJumping = false;
                            this.getNavigator().clearPath();
                            this.currentOrder = OrderType.values()[(this.currentOrder.ordinal() + 1) % 3];
                            this.sendOrderMessage(this.currentOrder);
                        }
                        return true;
                    }
                }
            }
        }
        return super.processInteract(player, hand);
    }

    public abstract Item getOrderItem();

    private void triggerTamingAcheivement(EntityPlayer player) {
        // player.triggerAchievement(FossilAchievementHandler.theKing);

    }

    public boolean isWeak() {
        return (this.getHealth() < 8) && (this.getAgeInDays() >= this.getAdultAge()) && !this.isTamed();
    }

    protected void setPedia() {
        Revival.PEDIA_OBJECT = this;
    }

    private void sendOrderMessage(OrderType var1) {
        String s = I18n.format("order.head") + I18n.format("order." + var1.toString().toLowerCase());
        this.world.getPlayerEntityByName(this.getOwnerDisplayName()).sendStatusMessage(new TextComponentString(s), false);
    }

    public void nudgeEntity(EntityPlayer player) {
        this.setPositionAndUpdate(this.posX + (player.posX - this.posX) * 0.01F, this.posY, this.posZ + (player.posZ - this.posZ) * 0.01F);
    }

    public ArrayList<Class<? extends Entity>> preyList() {
        return new ArrayList<Class<? extends Entity>>();
    }

    public ArrayList<Class<? extends Entity>> preyBlacklist() {
        return new ArrayList<Class<? extends Entity>>();
    }

    public void playerRoar(EntityPlayer player) {
    }

    public void playerAttack(EntityPlayer player) {
    }

    public void playerJump(EntityPlayer player) {
    }

    public void playerFlyUp(EntityPlayer player) {
    }

    public void playerFlyDown(EntityPlayer player) {
    }

    public String getTexture() {
        if (this.isSkeleton()) {
            return "fossil:textures/model/" + type.toString().toLowerCase() + "_0/" + type.toString().toLowerCase() + "_skeleton.png";
        }
        if (this.hasBabyTexture) {
            String toggle = this.hasFeatherToggle ? this.featherToggle ? "feathered/" : "scaled/" : "";
            boolean isBaby = this.isChild() && this.hasBabyTexture;
            String gender = this.hasTeenTexture ? this.isTeen() ? "_teen" : isBaby ? "_baby" : this.getGender() == 0 ? "_female" : "_male" : this.isChild() ? "_baby" : this.getGender() == 0 ? "_female" : "_male";
            String sleeping = !this.isSleeping() ? this.isActuallyWeak() ? "_sleeping" : "" : "_sleeping";
            String toggleList = this.hasFeatherToggle ? this.featherToggle ? "_feathered" : "_scaled" : "";
            return "fossil:textures/model/" + type.toString().toLowerCase() + "_0/" + toggle + type.toString().toLowerCase() + gender + toggleList + sleeping + ".png";
        } else {
            String toggle = this.hasFeatherToggle ? this.featherToggle ? "feathered/" : "scaled/" : "";
            String gender = this.getGender() == 0 ? "_female" : "_male";
            String sleeping = !this.isSleeping() ? this.isActuallyWeak() ? "_sleeping" : "" : "_sleeping";
            String toggleList = this.hasFeatherToggle ? this.featherToggle ? "_feathered" : "_scaled" : "";
            return "fossil:textures/model/" + type.toString().toLowerCase() + "_0/" + toggle + type.toString().toLowerCase() + gender + toggleList + sleeping + ".png";
        }
    }

    public boolean func_152114_e(EntityLivingBase entity) {
        if (entity != null) {
            String s = entity.getDisplayName().toString();
            return s != null && this.getOwnerDisplayName() != null && this.getOwnerDisplayName().equals(s);
        }
        return false;
    }

    public boolean isActuallyWeak() {
        return (this.aiTameType() == PrehistoricEntityTypeAI.Taming.BLUEGEM || this.aiTameType() == PrehistoricEntityTypeAI.Taming.GEM) ? this.isWeak() : false;
    }

    public int getTailSegments() {
        return 3;
    }


    private double getSpeed() {
        return 0.4D;
    }

    public float getMaleSize() {
        return 1.0F;
    }

    public String getOverlayTexture() {
        return "fossil:textures/blank.png";
    }

    @Override
    public int getAnimationTick() {
        return animTick;
    }

    @Override
    public void setAnimationTick(int tick) {
        animTick = tick;
    }

    @Override
    public Animation getAnimation() {
        return currentAnimation == null ? NO_ANIMATION : currentAnimation;
    }

    @Override
    public void setAnimation(Animation animation) {
        currentAnimation = animation;
    }

    @Override
    public Animation[] getAnimations() {
        return new Animation[]{SPEAK_ANIMATION, ATTACK_ANIMATION};
    }

    @Override
    public void playLivingSound() {
        if (!this.isSleeping() && !this.isSkeleton()) {
            super.playLivingSound();
            if (this.getAnimation() != null) {
                if (this.getAnimation() == NO_ANIMATION && !world.isRemote) {
                    this.setAnimation(SPEAK_ANIMATION);
                }
            }
        }
    }

    public void knockbackEntity(Entity knockBackMob, float knockbackStrength, float knockbackStrengthUp) {
        if (!(knockBackMob instanceof EntityToyBase)) {
            knockBackMob.motionY += 0.4000000059604645D;
            knockBackMob(knockBackMob, 0.25D, 0.2D, 0.25D);
        }
    }

    public static void knockBackMob(Entity entity, double xMotion, double yMotion, double zMotion) {
        entity.isAirBorne = true;
        float f1 = MathHelper.sqrt(xMotion * xMotion + zMotion * zMotion);
        entity.motionX /= 2.0D;
        entity.motionY /= 2.0D;
        entity.motionZ /= 2.0D;
        entity.motionX -= xMotion / (double) f1;
        entity.motionY += yMotion;
        entity.motionZ -= zMotion / (double) f1;

    }

    public void func_152115_b(String name) {
        this.setOwnerDisplayName(name);
    }

    @Override
    public void knockBack(Entity entity, float f, double x, double z) {
        if (entity != null && entity instanceof EntityPrehistoric) {
            if (this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).getAttributeValue() <= 0 && this.onGround) {
                this.velocityChanged = false;
                knockBackMob(entity, 1, 0.4D, 1);
            }
        } else {
            super.knockBack(entity, f, x, z);
        }
    }

    public boolean canDinoHunt(Entity target, boolean hunger) {
        boolean isAnotherDino = target instanceof EntityPrehistoric;

        if (this.type.diet != Diet.HERBIVORE && this.type.diet != Diet.NONE && canAttackClass(target.getClass())) {
            if (isAnotherDino ? this.getActualWidth() >= ((EntityPrehistoric) target).getActualWidth() : this.getActualWidth() >= target.width) {
                if (hunger) {
                    return isHungry() || target instanceof EntityToyBase && this.ticksTillPlay == 0;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isMad() {
        return this.getMoodFace() == PrehistoricMoodType.SAD;
    }

    public Flock getNearbyFlock() {
        EntityAINearestAttackableTarget.Sorter theNearestAttackableTargetSorter = new EntityAINearestAttackableTarget.Sorter(this);
        double d0 = 64;
        List<EntityPrehistoric> list = world.getEntitiesWithinAABB(EntityPrehistoric.class, this.getEntityBoundingBox().expand(d0, 4.0D, d0), null);
        Collections.sort(list, theNearestAttackableTargetSorter);
        if (!list.isEmpty()) {
            for (EntityPrehistoric mob : list) {
                if (mob != this && mob.type == this.type && mob.flockObj != null && mob.flockObj.flockLeader == mob) {
                    return mob.flockObj;
                }
            }
        }
        return null;
    }

    public void mate() {
        Entity targetEntity;
        EntityAINearestAttackableTarget.Sorter theNearestAttackableTargetSorter = new EntityAINearestAttackableTarget.Sorter(this);
        double d0 = 64;
        List<EntityPrehistoric> list = world.getEntitiesWithinAABB(EntityPrehistoric.class, this.getEntityBoundingBox().expand(d0, 4.0D, d0), null);
        Collections.sort(list, theNearestAttackableTargetSorter);
        List<EntityPrehistoric> listOfFemales = new ArrayList<EntityPrehistoric>();
        if (!list.isEmpty()) {
            for (EntityPrehistoric mob : list) {
                if (mob != this && mob.type == this.type && mob.isAdult() && mob.getGender() == 0 && mob.ticksTillMate == 0) {
                    listOfFemales.add(mob);
                }
            }
        }
        if (!listOfFemales.isEmpty() && this.ticksTillMate == 0) {
            EntityPrehistoric prehistoric = listOfFemales.get(0);
            if (prehistoric.ticksTillMate == 0) {
                this.getNavigator().tryMoveToEntityLiving(prehistoric, 1);
                double distance = (double) (this.width * 8.0F * this.width * 8.0F + prehistoric.width);
                if (this.getDistanceSq(prehistoric.posX, prehistoric.getEntityBoundingBox().minY, prehistoric.posZ) <= distance && prehistoric.onGround && this.onGround && this.isAdult() && prehistoric.isAdult()) {
                    prehistoric.procreate(this);
                    this.ticksTillMate = this.rand.nextInt(6000) + 6000;
                    prehistoric.ticksTillMate = this.rand.nextInt(12000) + 24000;
                }
            }
        }
    }

    public abstract boolean canBeRidden();

    public boolean canBeSteered() {
        return canBeRidden() && (this.getRidingPlayer() != null && this.func_152114_e(this.getRidingPlayer()));
    }

    public void procreate(EntityPrehistoric mob) {
        for (int i = 0; i < 7; ++i) {
            double dd = this.rand.nextGaussian() * 0.02D;
            double dd1 = this.rand.nextGaussian() * 0.02D;
            double dd2 = this.rand.nextGaussian() * 0.02D;
            Revival.PROXY.spawnPacketHeartParticles(this.world, (float) (this.posX + (this.rand.nextFloat() * this.width * 2.0F) - this.width), (float) (this.posY + 0.5D + (this.rand.nextFloat() * this.height)), (float) (this.posZ + (this.rand.nextFloat() * this.width * 2.0F) - this.width), dd, dd1, dd2);
            Revival.PROXY.spawnPacketHeartParticles(mob.world, (float) (mob.posX + (mob.rand.nextFloat() * mob.width * 2.0F) - mob.width), (float) (mob.posY + 0.5D + (mob.rand.nextFloat() * mob.height)), (float) (mob.posZ + (mob.rand.nextFloat() * mob.width * 2.0F) - mob.width), dd, dd1, dd2);

        }
        if (this.rand.nextInt(15) == 0) {
            //this.world.playSound(null, this.getPosition(), SoundEvents.ENTITY_SKELETON_HURT, SoundCategory.NEUTRAL, this.getSoundVolume(), this.getSoundPitch());
            //TODO
            //this.playSound("fossil:music.mating", 1, 1);
        }
        Entity hatchling = this.createEgg(mob);
        if (hatchling != null && !world.isRemote) {
            this.setAttackTarget(null);
            mob.setAttackTarget(null);
            hatchling.setPositionAndRotation(mob.posX, mob.posY + 1, mob.posZ, mob.rotationYaw, 0);
            if (hatchling instanceof EntityDinosaurEgg) {
                Revival.NETWORK_WRAPPER.sendToAll(new MessageUpdateEgg(hatchling.getEntityId(), this.type.ordinal()));
            } else {
                if (hatchling instanceof EntityPrehistoric) {
                    ((EntityPrehistoric) hatchling).onInitialSpawn(null, null);
                    ((EntityPrehistoric) hatchling).setAgeInDays(1);
                    ((EntityPrehistoric) hatchling).updateAbilities();
                    ((EntityPrehistoric) hatchling).setHealth((float) this.baseHealth);

                }
            }
            this.world.spawnEntity(hatchling);
        }
    }

    public boolean isThereNearbyTypes() {
        Entity targetEntity;
        EntityAINearestAttackableTarget.Sorter theNearestAttackableTargetSorter = new EntityAINearestAttackableTarget.Sorter(this);
        double d0 = 64;
        List<EntityPrehistoric> list = world.getEntitiesWithinAABB(EntityPrehistoric.class, this.getEntityBoundingBox().expand(d0, 4.0D, d0), null);
        Collections.sort(list, theNearestAttackableTargetSorter);

        if (list.isEmpty() || this.doesFlock()) {
            return false;
        } else {
            List<EntityPrehistoric> listOfType = new ArrayList<EntityPrehistoric>();
            for (EntityPrehistoric mob : list) {
                if (mob != this && mob.type == this.type && mob.isAdult()) {
                    listOfType.add(mob);
                }
            }
            return listOfType.size() > this.nearByMobsAllowed;
        }
    }

    public void doFoodEffect(Item item) {
        this.world.playSound(null, this.getPosition(), SoundEvents.ENTITY_GENERIC_EAT, SoundCategory.NEUTRAL, this.getSoundVolume(), this.getSoundPitch());
        if (item != null) {
            if (item instanceof ItemBlock) {
                spawnItemParticle(item, true);
            } else {
                spawnItemParticle(item, false);
            }
        }
    }

    public void doFoodEffect() {
        this.world.playSound(null, this.getPosition(), SoundEvents.ENTITY_GENERIC_EAT, SoundCategory.NEUTRAL, this.getSoundVolume(), this.getSoundPitch());
        switch (this.type.diet) {
            case HERBIVORE:
                spawnItemParticle(Items.WHEAT_SEEDS, false);
                break;
            case OMNIVORE:
                spawnItemParticle(Items.BREAD, false);
                break;
            case PISCIVORE:
                spawnItemParticle(Items.FISH, false);
                break;
            default:
                spawnItemParticle(Items.BEEF, false);
                break;
        }
    }

    public void spawnItemParticle(Item item, boolean itemBlock) {
        if (!world.isRemote) {
            double motionX = rand.nextGaussian() * 0.07D;
            double motionY = rand.nextGaussian() * 0.07D;
            double motionZ = rand.nextGaussian() * 0.07D;
            float f = (float) (getRNG().nextFloat() * (this.getEntityBoundingBox().maxX - this.getEntityBoundingBox().minX) + this.getEntityBoundingBox().minX);
            float f1 = (float) (getRNG().nextFloat() * (this.getEntityBoundingBox().maxY - this.getEntityBoundingBox().minY) + this.getEntityBoundingBox().minY);
            float f2 = (float) (getRNG().nextFloat() * (this.getEntityBoundingBox().maxZ - this.getEntityBoundingBox().minZ) + this.getEntityBoundingBox().minZ);
            if (itemBlock && item instanceof ItemBlock) {
                Revival.NETWORK_WRAPPER.sendToAll(new MessageFoodParticles(this.getEntityId(), Block.getIdFromBlock(((ItemBlock) item).getBlock())));
            } else {
                Revival.NETWORK_WRAPPER.sendToAll(new MessageFoodParticles(this.getEntityId(), Item.getIdFromItem(item)));
            }
        }
    }

    public boolean isInWaterMaterial() {
        double d0 = this.posY;
        int i = MathHelper.floor(this.posX);
        int j = MathHelper.floor((float) MathHelper.floor(d0));
        int k = MathHelper.floor(this.posZ);
        IBlockState blockState = this.world.getBlockState(new BlockPos(i, j, k));
        if (blockState.getMaterial() == Material.WATER) {
            double filled = 1.0f;
            if (blockState.getBlock() instanceof IFluidBlock) {
                filled = ((IFluidBlock) blockState.getBlock()).getFilledPercentage(world, new BlockPos(i, j, k));
            }
            if (filled < 0) {
                filled *= -1;
                return d0 > (double) (j + (1 - filled));
            } else {
                return d0 < (double) (j + filled);
            }
        } else {
            return false;
        }
    }

    public void eatItem(ItemStack stack) {
        if (stack != null && stack.getItem() != null) {
            if (FoodMappings.INSTANCE.getItemFoodAmount(stack, type.diet) != 0) {
                this.setMood(this.getMood() + 5);
                doFoodEffect(stack.getItem());
                Revival.NETWORK_WRAPPER.sendToAll(new MessageFoodParticles(getEntityId(), Item.getIdFromItem(stack.getItem())));
                this.setHunger(this.getHunger() + FoodMappings.INSTANCE.getItemFoodAmount(stack, type.diet));
                stack.shrink(1);
            }
        }
    }

    public String getTempermentString() {
        String s = null;
        if (this.aiResponseType() == PrehistoricEntityTypeAI.Response.AGRESSIVE || this.aiResponseType() == PrehistoricEntityTypeAI.Response.WATERAGRESSIVE) {
            s = "agressive";
        } else if (this.aiResponseType() == PrehistoricEntityTypeAI.Response.SCARED) {
            s = "scared";
        } else if (this.aiResponseType() == PrehistoricEntityTypeAI.Response.NONE || this.aiResponseType() == PrehistoricEntityTypeAI.Response.WATERCALM) {
            s = "none";
        } else if (this.aiResponseType() == PrehistoricEntityTypeAI.Response.TERITORIAL) {
            s = "territorial";
        }
        return "pedia.temperament." + s;
    }

    public boolean canRunFrom(Entity entity) {
        if (width <= entity.width) {
            if (entity instanceof EntityPrehistoric) {
                EntityPrehistoric mob = (EntityPrehistoric) entity;
                if (mob.type.diet != Diet.HERBIVORE) {
                    return true;
                }
            } else {
                if (entity instanceof EntityPlayer) {
                    EntityPlayer player = (EntityPlayer) entity;
                    if (this.getOwner() == player) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    protected void dropFewItems(boolean bool, int rand) {
        int j = this.rand.nextInt(3) + this.rand.nextInt(1 + rand);
        if (this.type.mobType == MobType.BIRD || this.type.mobType == MobType.TERRORBIRD) {
            for (int k = 0; k < j; ++k) {
                this.dropItem(Items.FEATHER, 1 + this.rand.nextInt(3));
            }
        }
        if (this.isBurning() && this.type.cookedFoodItem != null) {
            this.dropItem(this.type.cookedFoodItem, Math.min(this.getAgeInDays(), this.getAdultAge()));
        } else if (this.type.foodItem != null) {
            this.dropItem(this.type.foodItem, Math.min(this.getAgeInDays(), this.getAdultAge()));
        }
        if (DinosaurBoneType.get(this.type) != null) {
            this.entityDropItem(new ItemStack(FAItemRegistry.SKULL, this.rand.nextInt(1), DinosaurBoneType.get(this.type).ordinal()), 0);
            this.entityDropItem(new ItemStack(FAItemRegistry.ARM_BONE, this.rand.nextInt(2), DinosaurBoneType.get(this.type).ordinal()), 0);
            this.entityDropItem(new ItemStack(FAItemRegistry.RIBCAGE, this.rand.nextInt(1), DinosaurBoneType.get(this.type).ordinal()), 0);
            this.entityDropItem(new ItemStack(FAItemRegistry.VERTEBRAE, this.rand.nextInt(5), DinosaurBoneType.get(this.type).ordinal()), 0);
            this.entityDropItem(new ItemStack(FAItemRegistry.FOOT, this.rand.nextInt(2), DinosaurBoneType.get(this.type).ordinal()), 0);
            this.entityDropItem(new ItemStack(FAItemRegistry.UNIQUE_ITEM, this.rand.nextInt(2), DinosaurBoneType.get(this.type).ordinal()), 0);
        }
    }

    public double getMountedYOffset() {
        return 0;
    }

    @Override
    public void updateRidden() {
        if (this.getControllingPassenger() != null) {
            if (this.getControllingPassenger() instanceof EntityPlayer) {
                this.setPosition(posX, posY - ((EntityPlayer) this.getControllingPassenger()).getYOffset(), posZ);
            }
        }
        if (this.func_152114_e(this.getRidingPlayer()) && this.getAttackTarget() != this.getRidingPlayer()) {
            rotationYaw = renderYawOffset;
            rotationYaw = this.getRidingPlayer().rotationYaw;
            float radius = ridingXZ * (0.7F * getAgeScale()) * -3;
            float angle = (0.01745329251F * this.renderYawOffset);
            double extraX = (double) (radius * MathHelper.sin((float) (Math.PI + angle)));
            double extraZ = (double) (radius * MathHelper.cos(angle));
            double extraY = ridingY * (getAgeScale());
            float spinosaurusAddition = 0;
            //TODO
           /* if (this instanceof EntitySpinosaurus) {
                spinosaurusAddition = -(((EntitySpinosaurus) this).swimProgress * 0.1F);
            }*/
            this.getRidingPlayer().setPosition(this.posX + extraX, this.posY + extraY + spinosaurusAddition, this.posZ + extraZ);
            return;
        }
        super.updateRidden();
    }

    @Override
    public EntityAgeable createChild(EntityAgeable entity) {
        Entity baby = this.type.invokeClass(this.world);
        if (entity instanceof EntityPrehistoric) {
            ((EntityPrehistoric) baby).onInitialSpawn(null, null);
            ((EntityPrehistoric) baby).setAgeInDays(0);
            ((EntityPrehistoric) baby).updateAbilities();
            ((EntityPrehistoric) baby).setHealth((float) this.baseHealth);
            return ((EntityPrehistoric) baby);
        }
        return null;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return null;//"fossil:" + this.type.name().toLowerCase() + "_living";
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return null; //"fossil:" + this.type.name().toLowerCase() + "_hurt";
    }

    @Override
    protected SoundEvent getDeathSound() {
        return null; //"fossil:" + this.type.name().toLowerCase() + "_death";
    }

    public boolean isAquatic() {
        return this instanceof EntityPrehistoricSwimming;
    }

    public void onWhipRightClick(){

    }
}
