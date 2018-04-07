package fossilsarcheology.server.entity.prehistoric;

import com.google.common.base.Predicate;
import fossilsarcheology.client.sound.FASoundRegistry;
import fossilsarcheology.server.entity.ai.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class EntityMammoth extends EntityPrehistoric implements IShearable {

	private static final DataParameter<Boolean> SHEARED = EntityDataManager.<Boolean>createKey(EntityMammoth.class, DataSerializers.BOOLEAN);
	private static PotionEffect BIOME_EFFECT = new PotionEffect(MobEffects.WEAKNESS, 60, 1);
	protected boolean isSheared;
	private int eatGrassTimes = 0;

	public EntityMammoth(World world) {
		super(world, PrehistoricEntityType.MAMMOTH, 2, 12, 10, 66, 0.2, 0.3);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, this.aiSit);
		this.tasks.addTask(3, new DinoAIRiding(this, 1.0F));
		this.tasks.addTask(4, new DinoAIAttackOnCollide(this, 1.5D, false));
		this.tasks.addTask(5, new DinoAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
		this.tasks.addTask(6, new DinoAIEatBlocks(this, 1));
		this.tasks.addTask(6, new DinoAIEatFeeders(this, 1));
		this.tasks.addTask(6, new DinoAIEatItems(this, 1));
		this.tasks.addTask(7, new DinoAIWander(this, 1.0D));
		this.tasks.addTask(8, new DinoAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(8, new DinoAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
		this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(4, new DinoAIHunt(this, EntityLivingBase.class, false, new Predicate<Entity>() {
			@Override
			public boolean apply(@Nullable Entity entity) {
				return entity instanceof EntityLivingBase;
			}
		}));
		this.setActualSize(1.2F, 0.7F);
		this.pediaScale = 60F;
		minSize = 1.3F;
		maxSize = 5F;
		teenAge = 7;
		developsResistance = true;
		breaksBlocks = true;
		this.ridingY = 1.25F;
		this.eatGrassTimes = 0;
		this.setSheared(false);
	}

	@Override
	public int getAttackLength() {
		return 35;
	}

	@Override
	public int getSpeakLength() {
		return 35;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(SHEARED, false);
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
		return !this.isSheared() && !this.isChild() && !this.isSkeleton();
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		ArrayList var7 = new ArrayList();
		int var8 = 1 + this.rand.nextInt(20);
		for (int var9 = 0; var9 < var8; ++var9) {
			var7.add(new ItemStack(Blocks.WOOL, 1, 12));
		}
		this.setSheared(true);
		this.playSound(SoundEvents.ENTITY_SHEEP_SHEAR, 1.0F, 1.0F);
		return var7;
	}

	public String getTexture() {
		if (this.isSkeleton()) {
			return "fossil:textures/model/" + type.toString().toLowerCase() + "_0/" + type.toString().toLowerCase() + "_skeleton.png";
		}
		String toggle = this.hasFeatherToggle ? !this.featherToggle ? "feathered/" : "scaled/" : "";
		String gender = this.isChild() ? "_baby" : this.getGender() == 0 ? "_female" : "_male";
		String sleeping = !this.isSleeping() ? this.isActuallyWeak() ? "_sleeping" : "" : "_sleeping";
		String toggleList = this.hasFeatherToggle ? !this.featherToggle ? "_feathered" : "_scaled" : "";
		return "fossil:textures/model/" + type.toString().toLowerCase() + "_0/" + toggle + type.toString().toLowerCase() + gender + toggleList + sleeping + (this.isSheared() ? "_sheared" : "") + ".png";
	}

	@Override
	public void setSpawnValues() {
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound var1) {
		super.writeEntityToNBT(var1);
		var1.setInteger("GrassTicks", this.eatGrassTimes);
		var1.setBoolean("Sheared", this.isSheared());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound var1) {
		super.readEntityFromNBT(var1);
		this.setSheared(var1.getBoolean("Sheared"));
		this.eatGrassTimes = var1.getInteger("GrassTicks");
	}

	@Override
	public void doFoodEffect(Item item) {
		super.doFoodEffect(item);
		if (this.isSheared()) {
			++this.eatGrassTimes;
			if (this.eatGrassTimes >= 5) {
				this.setSheared(false);
				this.eatGrassTimes = 0;
			}
		}
	}

	@Override
	public void onLivingUpdate() {
		int i = MathHelper.floor(this.posX);
		int j = MathHelper.floor(this.posY);
		int k = MathHelper.floor(this.posZ);
		if (!this.isPotionActive(MobEffects.WEAKNESS) && this.world.getBiome(new BlockPos(i, 0, k)).getTemperature(new BlockPos(i, j, k)) > 1.0 && !this.isSheared()) {
			this.addPotionEffect(BIOME_EFFECT);
		}
		if (this.getAnimation() == ATTACK_ANIMATION && this.getAnimationTick() == 17 && this.getAttackTarget() != null) {
			this.attackEntityAsMob(this.getAttackTarget());
		}
		super.onLivingUpdate();
	}

	@Override
	public PrehistoricEntityTypeAI.Activity aiActivityType() {
		return PrehistoricEntityTypeAI.Activity.DIURINAL;
	}

	@Override
	public PrehistoricEntityTypeAI.Attacking aiAttackType() {
		return PrehistoricEntityTypeAI.Attacking.KNOCKUP;
	}

	@Override
	public PrehistoricEntityTypeAI.Climbing aiClimbType() {
		return PrehistoricEntityTypeAI.Climbing.NONE;
	}

	@Override
	public PrehistoricEntityTypeAI.Following aiFollowType() {
		return PrehistoricEntityTypeAI.Following.NONE;
	}

	@Override
	public PrehistoricEntityTypeAI.Jumping aiJumpType() {
		return PrehistoricEntityTypeAI.Jumping.BASIC;
	}

	@Override
	public PrehistoricEntityTypeAI.Response aiResponseType() {
		return PrehistoricEntityTypeAI.Response.TERITORIAL;
	}

	@Override
	public PrehistoricEntityTypeAI.Stalking aiStalkType() {
		return PrehistoricEntityTypeAI.Stalking.NONE;
	}

	@Override
	public PrehistoricEntityTypeAI.Taming aiTameType() {
		return PrehistoricEntityTypeAI.Taming.IMPRINTING;
	}

	@Override
	public PrehistoricEntityTypeAI.Untaming aiUntameType() {
		return PrehistoricEntityTypeAI.Untaming.ATTACK;
	}

	@Override
	public PrehistoricEntityTypeAI.Moving aiMovingType() {
		return PrehistoricEntityTypeAI.Moving.WALK;
	}

	@Override
	public PrehistoricEntityTypeAI.WaterAbility aiWaterAbilityType() {
		return PrehistoricEntityTypeAI.WaterAbility.NONE;
	}

	@Override
	public boolean doesFlock() {
		return true;
	}

	@Override
	public Item getOrderItem() {
		return Items.STICK;
	}

	@Override
	public int getAdultAge() {
		return 14;
	}

	@Override
	public float getMaleSize() {
		return 1.2F;
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (this.getAttackBounds().expand(3.0F, 3.0F, 3.0F).intersects(entity.getEntityBoundingBox())) {
			if (this.getAnimation() == NO_ANIMATION) {
				this.setAnimation(ATTACK_ANIMATION);
				return false;
			}
			if (this.getAnimation() == ATTACK_ANIMATION && this.getAnimationTick() == 17) {
				IAttributeInstance iattributeinstance = this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
				boolean flag = entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float) iattributeinstance.getAttributeValue());
				if (entity.getRidingEntity() != null) {
					if (entity.getRidingEntity() == this) {
						entity.startRiding(null);
					}
				}
				entity.motionY += 0.4000000059604645D;
				double d0 = this.getAttackTarget().posX - this.posX;
				double d1 = this.getAttackTarget().posZ - this.posZ;
				float f = MathHelper.sqrt(d0 * d0 + d1 * d1);
				entity.addVelocity((double) (-MathHelper.sin((this.rotationYaw - 180) * (float) Math.PI / 180.0F) * 2 * 0.5F), 0.1D, (double) (MathHelper.cos((this.rotationYaw - 180) * (float) Math.PI / 180.0F) * 2 * 0.5F));
				return flag;
			}
		}
		return false;
	}

	public int getMaxHunger() {
		return 150;
	}

	public void setSheared(boolean sheared) {
		this.dataManager.set(SHEARED, sheared);
		if (!world.isRemote) {
			this.isSheared = sheared;
		}
	}

	public boolean isSheared() {
		if (world.isRemote) {
			boolean isStanding = this.dataManager.get(SHEARED);
			this.isSheared = isStanding;
			return isStanding;
		}

		return isSheared;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return FASoundRegistry.MAMMOTH_LIVING;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return FASoundRegistry.MAMMOTH_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return FASoundRegistry.MAMMOTH_DEATH;
	}

	@Override
	public boolean canBeRidden() {
		return true;
	}
}
