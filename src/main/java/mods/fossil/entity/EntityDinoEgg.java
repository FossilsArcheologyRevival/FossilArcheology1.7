package mods.fossil.entity;

import io.netty.buffer.ByteBuf;

import java.util.List;

import mods.fossil.Fossil;
import mods.fossil.client.LocalizationStrings;
import mods.fossil.client.gui.GuiPedia;
import mods.fossil.entity.mob.EntityAllosaurus;
import mods.fossil.entity.mob.EntityAnkylosaurus;
import mods.fossil.entity.mob.EntityBrachiosaurus;
import mods.fossil.entity.mob.EntityCeratosaurus;
import mods.fossil.entity.mob.EntityCompsognathus;
import mods.fossil.entity.mob.EntityDeinonychus;
import mods.fossil.entity.mob.EntityDilophosaurus;
import mods.fossil.entity.mob.EntityDinosaur;
import mods.fossil.entity.mob.EntityGallimimus;
import mods.fossil.entity.mob.EntityLiopleurodon;
import mods.fossil.entity.mob.EntityMosasaurus;
import mods.fossil.entity.mob.EntityPachycephalosaurus;
import mods.fossil.entity.mob.EntityPlesiosaur;
import mods.fossil.entity.mob.EntityPterosaur;
import mods.fossil.entity.mob.EntitySarcosuchus;
import mods.fossil.entity.mob.EntitySpinosaurus;
import mods.fossil.entity.mob.EntityStegosaurus;
import mods.fossil.entity.mob.EntityTRex;
import mods.fossil.entity.mob.EntityTriceratops;
import mods.fossil.entity.mob.EntityVelociraptor;
import mods.fossil.fossilEnums.EnumDinoType;
import mods.fossil.fossilEnums.EnumOrderType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityDinoEgg extends Entity implements IEntityAdditionalSpawnData
{
	private static final String HEAD = "Dinoegg.";
	private static final String MSGHEAD = "Dinoegg.msghead";
	private static final String MSGTAIL = ".msgtail";
	private static final String COLD = "cold";
	private static final String DRY = "dry";
	private static final String PEDIA = "PediaText.egg.";
	public int damageTaken;
	public int timeSinceHit;
	public EnumDinoType DinoInside;
	//public int BirthTick;
	public String ParentOwner;
	private int HatchTime;
	public final int HatchingNeedTime;
	private static int lastBirthTick;
	public static final int HATCHING_INDEX = 18;

	public EntityDinoEgg(World var1, EnumDinoType var2)
	{
		super(var1);
		//this.BirthTick = 0;
		this.ParentOwner = "";
		this.HatchingNeedTime = this.HatchTime;
		this.damageTaken = 0;
		this.timeSinceHit = 0;
		this.preventEntitySpawning = true;
		this.setSize(0.5F, 1.5F);
		this.yOffset = this.height;
		this.DinoInside = var2;
		this.lastBirthTick = 0;
	}

	/**
	 * Returns the texture's file path as a String.
	 */
	public String getTexture()
	{
		int var1 = this.DinoInside.ordinal();
		return var1 < 4 ? "fossil:textures/mob/DinosaurEggs/eggTexture" + (var1 + 1) + ".png" : "fossil:textures/mob/DinosaurEggs/eggTexture" + var1 + ".png";
		
	}

	public EntityDinoEgg(World var1)
	{
		this(var1, (EnumDinoType)null);
	}
	private void setPedia()
	{
		Fossil.ToPedia = (Object)this;
	}

	public EntityDinoEgg(World var1, EnumDinoType var2, EntityDinosaur var3)
	{
		this(var1, var2);
		this.ParentOwner = var3.getCommandSenderName();
	}

	/**
	 * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
	 * prevent them from trampling crops
	 */
	protected boolean canTriggerWalking()
	{
		return false;
	}

	protected void entityInit()
	{
		if (Fossil.DebugMode())
		{
			this.HatchTime = 100;
		}
		else
		{
			this.HatchTime = 3000;
		}

		this.dataWatcher.addObject(HATCHING_INDEX, new Integer(0));
	}
	public int getBirthTick()
	{
		return this.dataWatcher.getWatchableObjectInt(HATCHING_INDEX);
	}

	public void setBirthTick(int var1)
	{
		this.dataWatcher.updateObject(HATCHING_INDEX, Integer.valueOf(var1));
	}

	/**
	 * Returns a boundingBox used to collide the entity with other entities and blocks. This enables the entity to be
	 * pushable on contact, like boats or minecarts.
	 */
	public AxisAlignedBB getCollisionBox(Entity var1)
	{
		return var1.boundingBox;
	}

	/**
	 * returns the bounding box for this entity
	 */
	public AxisAlignedBB getBoundingBox()
	{
		return this.boundingBox;
	}

	/**
	 * Returns true if this entity should push and be pushed by other entities when colliding.
	 */
	public boolean canBePushed()
	{
		return true;
	}

	public EntityDinoEgg(World var1, double var2, double var4, double var6, EnumDinoType var8)
	{
		this(var1, var8);
		this.setPosition(var2, var4 + (double)this.yOffset, var6);
		this.motionX = 0.0D;
		this.motionY = 0.0D;
		this.motionZ = 0.0D;
		this.prevPosX = var2;
		this.prevPosY = var4;
		this.prevPosZ = var6;
	}

	/**
	 * Returns the Y offset from the entity's position for any entity riding this one.
	 */
	public double getMountedYOffset()
	{
		return (double)this.height * 0.0D - 0.30000001192092896D;
	}

	/**
	 * Called when the entity is attacked.
	 */
	public boolean attackEntityFrom(DamageSource var1, int var2)
	{
		if (!this.worldObj.isRemote && !this.isDead)
		{
			this.timeSinceHit = 10;
			this.damageTaken += var2 * 10;
			this.setBeenAttacked();

			if (this.damageTaken > 40)
			{
				if (this.riddenByEntity != null)
				{
					this.riddenByEntity.mountEntity(this);
				}

				this.setDead();
			}

			return true;
		}
		else
		{
			return true;
		}
	}

	/**
	 * Setups the entity to do the hurt animation. Only used by packets in multiplayer.
	 */
	public void performHurtAnimation()
	{
		this.timeSinceHit = 10;
		this.damageTaken += this.damageTaken * 10;
	}

	/**
	 * Returns true if other Entities should be prevented from moving through this Entity.
	 */
	public boolean canBeCollidedWith()
	{
		return !this.isDead;
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate()
	{
		super.onUpdate();
		//if(!this.worldObj.isRemote)
		this.HandleHatching();
		//super.onUpdate();

		if (this.timeSinceHit > 0)
		{
			--this.timeSinceHit;
		}

		if (this.damageTaken > 0)
		{
			--this.damageTaken;
		}

		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		byte var1 = 5;
		double var2 = 0.0D;

		for (int var4 = 0; var4 < var1; ++var4)
		{
			double var5 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (double)(var4 + 0) / (double)var1 - 0.125D;
			double var7 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (double)(var4 + 1) / (double)var1 - 0.125D;
			AxisAlignedBB.getBoundingBox(this.boundingBox.minX, var5, this.boundingBox.minZ, this.boundingBox.maxX, var7, this.boundingBox.maxZ);
		}

		double var21;

		if (var2 < 1.0D)
		{
			var21 = var2 * 2.0D - 1.0D;
			this.motionY += 0.03999999910593033D * var21;
		}
		else
		{
			if (this.motionY < 0.0D)
			{
				this.motionY /= 2.0D;
			}

			this.motionY += 0.007000000216066837D;
		}

		if (this.riddenByEntity != null)
		{
			this.motionX += this.riddenByEntity.motionX * 0.2D;
			this.motionZ += this.riddenByEntity.motionZ * 0.2D;
		}

		var21 = 0.4D;

		if (this.motionX < -var21)
		{
			this.motionX = -var21;
		}

		if (this.motionX > var21)
		{
			this.motionX = var21;
		}

		if (this.motionZ < -var21)
		{
			this.motionZ = -var21;
		}

		if (this.motionZ > var21)
		{
			this.motionZ = var21;
		}

		if (this.onGround)
		{
			this.motionX *= 0.5D;
			this.motionY *= 0.5D;
			this.motionZ *= 0.5D;
		}

		this.moveEntity(this.motionX, this.motionY, this.motionZ);
		double var6 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
		double var8;
		double var10;

		if (var6 > 0.15D)
		{
			var8 = Math.cos((double)this.rotationYaw * Math.PI / 180.0D);
			var10 = Math.sin((double)this.rotationYaw * Math.PI / 180.0D);

			for (int var12 = 0; (double)var12 < 1.0D + var6 * 60.0D; ++var12)
			{
				double var13 = (double)(this.rand.nextFloat() * 2.0F - 1.0F);
				double var15 = (double)(this.rand.nextInt(2) * 2 - 1) * 0.7D;
				double var17;
				double var19;

				if (this.rand.nextBoolean())
				{
					var17 = this.posX - var8 * var13 * 0.8D + var10 * var15;
					var19 = this.posZ - var10 * var13 * 0.8D - var8 * var15;
				}
				else
				{
					var17 = this.posX + var8 + var10 * var13 * 0.7D;
					var19 = this.posZ + var10 - var8 * var13 * 0.7D;
				}
			}
		}

		if (this.isCollidedHorizontally && var6 > 0.15D)
		{
			if (!this.worldObj.isRemote)
			{
				this.setDead();
			}
		}
		else
		{
			this.motionX *= 0.9900000095367432D;
			this.motionY *= 0.949999988079071D;
			this.motionZ *= 0.9900000095367432D;
		}

		this.rotationPitch = 0.0F;
		var8 = (double)this.rotationYaw;
		var10 = this.prevPosX - this.posX;
		double var22 = this.prevPosZ - this.posZ;

		if (var10 * var10 + var22 * var22 > 0.001D)
		{
			var8 = (double)((float)(Math.atan2(var22, var10) * 180.0D / Math.PI));
		}

		double var14;

		for (var14 = var8 - (double)this.rotationYaw; var14 >= 180.0D; var14 -= 360.0D)
		{
			;
		}

		while (var14 < -180.0D)
		{
			var14 += 360.0D;
		}

		if (var14 > 20.0D)
		{
			var14 = 20.0D;
		}

		if (var14 < -20.0D)
		{
			var14 = -20.0D;
		}

		this.rotationYaw = (float)((double)this.rotationYaw + var14);
		this.setRotation(this.rotationYaw, this.rotationPitch);
		List var16 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(0.0D, 0.0D, 0.0D));
		int var23;

		if (var16 != null && var16.size() > 0)
		{
			for (var23 = 0; var23 < var16.size(); ++var23)
			{
				Entity var18 = (Entity)var16.get(var23);

				if (var18 != this.riddenByEntity && var18.canBePushed() && var18 instanceof EntityBoat)
				{
					var18.applyEntityCollision(this);
				}
			}
		}

		for (var23 = 0; var23 < 4; ++var23)
		{
			int var24 = MathHelper.floor_double(this.posX + ((double)(var23 % 2) - 0.5D) * 0.8D);
			int var25 = MathHelper.floor_double(this.posY);
			int var20 = MathHelper.floor_double(this.posZ + ((double)(var23 / 2) - 0.5D) * 0.8D);

			if (this.worldObj.getBlock(var24, var25, var20) == Blocks.snow)
			{
				this.worldObj.setBlock(var24, var25, var20, Blocks.air, 0, 2);
			}
		}

		if (this.riddenByEntity != null && this.riddenByEntity.isDead)
		{
			this.riddenByEntity = null;
		}
	}

	public void updateRiderPosition()
	{
		if (this.riddenByEntity != null)
		{
			double var1 = Math.cos((double)this.rotationYaw * Math.PI / 180.0D) * 0.4D;
			double var3 = Math.sin((double)this.rotationYaw * Math.PI / 180.0D) * 0.4D;
			this.riddenByEntity.setPosition(this.posX + var1, this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset(), this.posZ + var3);
		}
	}
	private void HandleHatching()
	{
		//this.getClass();//needed to set which is the actual instance using this function
		float brightness = this.getBrightness(1.0F);
		EntityPlayer player = null;

		if ((this.ParentOwner == "" || this.worldObj.getPlayerEntityByName(this.ParentOwner) == null) && this.worldObj.getClosestPlayerToEntity(this, 16.0D) != null)
		{
			player = this.worldObj.getClosestPlayerToEntity(this, 16.0D);
		}

		if (this.DinoInside == EnumDinoType.Mosasaurus || this.DinoInside == EnumDinoType.Liopleurodon)
		{
			if (this.inWater)
			{
				this.lastBirthTick = this.getBirthTick();
				this.setBirthTick(this.getBirthTick() + 1);

			}
			else
			{
				this.setBirthTick(this.getBirthTick() - 1);
			}

		}
		else if ((double)brightness >= 0.5D && !this.inWater)
		{
			this.lastBirthTick = this.getBirthTick();
			this.setBirthTick(this.getBirthTick() + 1);

		}
		else
		{
			BiomeGenBase var5 = this.worldObj.getBiomeGenForCoords((int)this.posX, (int)this.posZ);
			float var6 = var5.temperature;
			//if (!this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)))
			if ((var6 <= 0.15F && brightness < 0.5) || this.inWater)
			{
				this.setBirthTick(this.getBirthTick() - 1);
			}
		}

		if (this.getBirthTick() <= -this.HatchingNeedTime)
		{
			if (player != null)
			{
				String var6;

				if (this.DinoInside == EnumDinoType.Mosasaurus || this.DinoInside == EnumDinoType.Liopleurodon)
				{
					var6 = StatCollector.translateToLocal(LocalizationStrings.DINOEGG_DRY);
				}
				else
				{
					var6 = StatCollector.translateToLocal(LocalizationStrings.DINOEGG_WET);
				}

				String var1 = StatCollector.translateToLocal(LocalizationStrings.DINOEGG_HEAD);

				if (FMLCommonHandler.instance().getSide().isServer())
				{
					Fossil.ShowMessage(var1 + StatCollector.translateToLocal("entity.fossil." + this.DinoInside.toString())/*EntityDinosaur.GetNameByEnum(this.DinoInside, false)*/ + var6, player);
				}
			}

			this.setDead();
		}
		else
		{
			if (this.getBirthTick() >= this.HatchingNeedTime)
			{
				if (this.worldObj.isRemote)
				{
					return;
				}

				BiomeGenBase var3 = this.worldObj.provider.worldChunkMgr.getBiomeGenAt((int)Math.floor(this.posX), (int)Math.floor(this.posZ));
				Object var5 = null;

				switch (this.DinoInside)
				{
				case Triceratops:
					var5 = new EntityTriceratops(this.worldObj);

					if (BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.SAVANNA)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.SANDY)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.MESA))
					{
						((EntityTriceratops)var5).setSubSpecies(1);
					}
					else if (BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.COLD)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.MOUNTAIN))
					{
						((EntityTriceratops)var5).setSubSpecies(2);
					}
					else 
					{
						((EntityTriceratops)var5).setSubSpecies(0);
					}
					break;

				case Velociraptor:
					var5 = new EntityVelociraptor(this.worldObj);

					if (BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.FOREST)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.SWAMP))
					{
						((EntityVelociraptor)var5).setSubSpecies(2);
					}
					else if (BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.COLD)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.MOUNTAIN))
					{
						((EntityVelociraptor)var5).setSubSpecies(1);
					}
					else if (BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.WATER)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.BEACH))
					{
						((EntityVelociraptor)var5).setSubSpecies(3);
					}
					else
					{
						((EntityVelociraptor)var5).setSubSpecies(0);
					}

					break;

				case TRex:
					var5 = new EntityTRex(this.worldObj);

					if (BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.FOREST)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.SWAMP)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.JUNGLE))
					{
						((EntityTRex)var5).setSubSpecies(1);
					}
					else
					{
						((EntityTRex)var5).setSubSpecies(0);
					}
					break;

				case Pterosaur:
					var5 = new EntityPterosaur(this.worldObj);

					if (BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.WASTELAND)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.NETHER)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.DEAD)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.JUNGLE)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.HOT))
					{
						((EntityPterosaur)var5).setSubSpecies(2); //nether
					}
					else if (BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.COLD)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.END)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.FOREST)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.MUSHROOM)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.CONIFEROUS)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.OCEAN)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.RIVER)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.WATER))
					{
						((EntityPterosaur)var5).setSubSpecies(1); //cold
					}else{
						((EntityPterosaur)var5).setSubSpecies(0); 

					}
					break;

				case Plesiosaur:
					var5 = new EntityPlesiosaur(this.worldObj);
					break;

				case Mosasaurus:
					var5 = new EntityMosasaurus(this.worldObj);

					if (BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.COLD)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.MOUNTAIN)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.WATER))
					{
						((EntityMosasaurus)var5).setSubSpecies(1);
					}
					else
					{
						((EntityMosasaurus)var5).setSubSpecies(2);
					}

					break;

				case Liopleurodon:
					var5 = new EntityLiopleurodon(this.worldObj);
					break;

				case Stegosaurus:
					var5 = new EntityStegosaurus(this.worldObj);

					if (BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.SAVANNA)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.SANDY)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.MESA))
					{
						((EntityStegosaurus)var5).setSubSpecies(2);
					}
					else if (BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.COLD)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.MOUNTAIN))
					{
						((EntityStegosaurus)var5).setSubSpecies(1);
					}
					else 
					{
						((EntityStegosaurus)var5).setSubSpecies(0);
					}
					break;

				case Dilophosaurus:
					var5 = new EntityDilophosaurus(this.worldObj);

					if (BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.NETHER)||BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.JUNGLE)
							||BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.LUSH)||BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.MESA)
							||BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.WASTELAND)||BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.SPOOKY))
					{
						((EntityDilophosaurus)var5).setSubSpecies(1);
					}
					else
					{
						((EntityDilophosaurus)var5).setSubSpecies(0);
					}
					break;

				case Allosaurus:
					var5 = new EntityAllosaurus(this.worldObj);

					if (BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.HOT)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.DRY))
					{
						((EntityAllosaurus)var5).setSubSpecies(2);
					}
					else if (BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.JUNGLE)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.SWAMP)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.FOREST))
					{
						((EntityAllosaurus)var5).setSubSpecies(3);
					}
					else
					{
						((EntityAllosaurus)var5).setSubSpecies(1);
					}

					break;

				case Brachiosaurus:
					var5 = new EntityBrachiosaurus(this.worldObj);
					break;

				case Spinosaurus:
					var5 = new EntitySpinosaurus(this.worldObj);
					break;

				case Pachycephalosaurus:
					var5 = new EntityPachycephalosaurus(this.worldObj);

					if (BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.FOREST))
					{
						((EntityPachycephalosaurus)var5).setSubSpecies(1);
					}
					else if (BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.COLD)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.MOUNTAIN))
					{
						((EntityPachycephalosaurus)var5).setSubSpecies(2);
					}
					else
					{
						((EntityPachycephalosaurus)var5).setSubSpecies(3);
					}

					break;

				case Compsognathus:
					var5 = new EntityCompsognathus(this.worldObj);

					if (BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.COLD)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.MOUNTAIN)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.HOT))
					{
						((EntityCompsognathus)var5).setSubSpecies(1);
					}
					else
					{
						((EntityCompsognathus)var5).setSubSpecies(2);
					}

					break;

				case Ankylosaurus:
					var5 = new EntityAnkylosaurus(this.worldObj);
					break;

				case Deinonychus:
					var5 = new EntityDeinonychus(this.worldObj);
					if (BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.COLD)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.MOUNTAIN)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.HILLS))
					{
						((EntityDeinonychus)var5).setSubSpecies(1); //Grey
					}
					else if (BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.JUNGLE)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.SWAMP))
					{
						((EntityDeinonychus)var5).setSubSpecies(2); //Black
					}
					else
					{
						((EntityDeinonychus)var5).setSubSpecies(3); //Brown
					}
					break;

				case Gallimimus:
					var5 = new EntityGallimimus(this.worldObj);

					if (BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.PLAINS)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.HILLS)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.FOREST))
					{
						((EntityGallimimus)var5).setSubSpecies(1); //Green
					}
					else if (BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.COLD)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.BEACH)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.WATER))
					{
						((EntityGallimimus)var5).setSubSpecies(2); //Blue
					}
					else if (BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.HOT)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.WASTELAND))
					{
						((EntityGallimimus)var5).setSubSpecies(3); //Orange
					}
					else
					{
						((EntityGallimimus)var5).setSubSpecies(4); //Brown
					}
					break;
				case Sarcosuchus:
					var5 = new EntitySarcosuchus(this.worldObj);

					if (BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.SANDY)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.DEAD)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.BEACH)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.DRY)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.WASTELAND)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.SPOOKY))
					{
						((EntitySarcosuchus)var5).setSubSpecies(2); //desert
					}
					else if (BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.JUNGLE)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.MAGICAL)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.SWAMP)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.DENSE)
							|| BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.LUSH))
					{
						((EntitySarcosuchus)var5).setSubSpecies(1); //swamp
					}else{
						((EntitySarcosuchus)var5).setSubSpecies(0); 

					}
					break;
				case Ceratosaurus:
					var5 = new EntityCeratosaurus(this.worldObj);

					if (BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.NETHER)||BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.JUNGLE)
							||BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.LUSH)||BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.MESA)
							||BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.WASTELAND)||BiomeDictionary.isBiomeOfType(var3, BiomeDictionary.Type.SPOOKY))
					{
						((EntityCeratosaurus)var5).setSubSpecies(1);
					}
					else
					{
						((EntityCeratosaurus)var5).setSubSpecies(0);
					}
					break;

				default:
					Fossil.ShowMessage("Bug: Impossible result.", player);
					//System.err.println("EGGERROR2"+String.valueOf(i));
					this.setDead();
					return;
				}

				if(var5 instanceof EntityDinosaur){
					if (((EntityDinosaur)var5).SelfType.isTameable() && player != null)
					{
						if(((EntityDinosaur)var5).SelfType != EnumDinoType.TRex && ((EntityDinosaur)var5).SelfType != EnumDinoType.Allosaurus){
							// Tameable and player next to it
							((EntityDinosaur)var5).setTamed(true);
							((EntityDinosaur)var5).setOwner(player.getUniqueID().toString());
							((EntityDinosaur)var5).setOwnerDisplayName(player.getCommandSenderName());
							// Locked to follow for first day
							((EntityDinosaur)var5).OrderStatus = EnumOrderType.Follow;
						}
					}
				}

				((EntityLiving)var5).setLocationAndAngles((double)((int)Math.floor(this.posX)), (double)((int)Math.floor(this.posY) + 1), (double)((int)Math.floor(this.posZ)), this.worldObj.rand.nextFloat() * 360.0F, 0.0F);

				if (this.worldObj.checkNoEntityCollision(((EntityLiving)var5).boundingBox) 
						&& this.worldObj.getCollidingBoundingBoxes((Entity)var5, ((EntityLiving)var5).boundingBox).size() == 0 
						&& (!this.worldObj.isAnyLiquid(((EntityLiving)var5).boundingBox) 
								|| this.DinoInside == EnumDinoType.Mosasaurus || this.DinoInside == EnumDinoType.Liopleurodon))
				{
					//if (!this.worldObj.isRemote)
					{
						this.worldObj.spawnEntityInWorld((Entity)var5);

						if (player != null)
						{
							Fossil.ShowMessage(StatCollector.translateToLocal(LocalizationStrings.DINOEGG_HATCHED), player);
						}
					}
					this.setDead();
				}
				else
				{
					//System.err.println("EGGERROR-NOPLACE");
					Fossil.ShowMessage(StatCollector.translateToLocal(LocalizationStrings.DINOEGG_NOSPACE), player);
					this.setBirthTick(this.getBirthTick() - 500);
					//System.err.println("EGGERROR3"+String.valueOf(i));
				}
			}
		}
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	protected void writeEntityToNBT(NBTTagCompound var1)
	{
		var1.setInteger("BirthTick", this.getBirthTick());
		var1.setInteger("DinoType", this.EnumToInt(this.DinoInside));
		var1.setString("ParentOwner", this.ParentOwner);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	protected void readEntityFromNBT(NBTTagCompound var1)
	{
		EnumDinoType[] var2 = EnumDinoType.values();
		this.setBirthTick(var1.getInteger("BirthTick"));
		this.DinoInside = var2[var1.getInteger("DinoType")];
		this.ParentOwner = var1.getString("ParentOwner");
	}

	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
	 */
	@Override
	public boolean interactFirst(EntityPlayer player)
	{
		ItemStack itemstack = player.inventory.getCurrentItem();

		if (itemstack == null)
		{
			Item i0 = this.DinoInside.EggItem;
			ItemStack var3 = new ItemStack(i0/*this.DinoInside.EggItem/*var7*/, 1, 1);

			if (player.inventory.addItemStackToInventory(var3))
			{
				this.worldObj.playSoundAtEntity(player, "random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
				this.setDead();
			}

			return true;
		}
		else if (FMLCommonHandler.instance().getSide().isClient() && itemstack.getItem() == Fossil.dinoPedia)
		{
			this.setPedia();
			player.openGui(Fossil.instance, 4, worldObj, (int)posX, (int)posY, (int)posZ);
			return true;
		}

		return false;
	}

	private int EnumToInt(EnumDinoType var1)
	{
		return this.DinoInside.ordinal();
	}

	@SideOnly(Side.CLIENT)
	public void ShowPedia(GuiPedia p0)
	{
		Item it0 = this.DinoInside.EggItem;
		/*switch (this.DinoInside)
        {
            case Triceratops:it0=Fossil.eggTriceratops;break;
            case Velociraptor:it0=Fossil.eggVelociraptor;break;
            case TRex:it0=Fossil.eggTRex;break;
            case Pterosaur:it0=Fossil.eggPterosaur;break;
            case Plesiosaur:it0=Fossil.eggPlesiosaur;break;
            case Mosasaurus:it0=Fossil.eggMosasaurus;break;
            case Stegosaurus:it0=Fossil.eggStegosaurus;break;
            case Dilophosaurus:it0=Fossil.eggDilophosaurus;break;
            case Brachiosaurus:it0=Fossil.eggBrachiosaurus;break;
            case Spinosaurus:it0=Fossil.eggSpinosaurus;break;

            default:it0=Fossil.eggTriceratops;
        }*/
		p0.reset();
		p0.PrintItemXY(it0, 185, 7);
		p0.PrintStringLR(StatCollector.translateToLocal("entity.fossil." + this.DinoInside.toString() + ".name"), false, 1, 40, 90, 245);
		int quot = (int)Math.floor(((float)this.getBirthTick() / (float)this.HatchingNeedTime * 100.0F));
		String stat;

		if (this.DinoInside == EnumDinoType.Mosasaurus || this.DinoInside == EnumDinoType.Liopleurodon)
		{
			if (this.getBirthTick() >= 0)
			{
				stat = StatCollector.translateToLocal(LocalizationStrings.PEDIA_EGG_WET);
			}
			else
			{
				stat = StatCollector.translateToLocal(LocalizationStrings.PEDIA_EGG_DRY);
			}
		}
		else
		{
			if ((this.getBirthTick() >= 0 && this.getBirthTick() > this.lastBirthTick) || this.getBirthTick() >= 100)
			{
				stat = StatCollector.translateToLocal(LocalizationStrings.PEDIA_EGG_WARM);
			}
			else
			{
				stat = StatCollector.translateToLocal(LocalizationStrings.PEDIA_EGG_COLD);
			}
		}

		p0.PrintStringLR(StatCollector.translateToLocal(LocalizationStrings.PEDIA_EGG_STATUS), false, 2, 40, 90, 245);
		p0.PrintStringLR(stat, false, 3);

		if (this.getBirthTick() >= 0)
		{
			p0.PrintStringLR(StatCollector.translateToLocal(LocalizationStrings.PEDIA_EGG_PROGRESS), false, 4, 40, 90, 245);
			p0.PrintStringLR(String.valueOf(quot) + "/100", false, 5);
		}
	}

	@Override
	public void writeSpawnData(ByteBuf var1)
	{
		var1.writeInt(this.getBirthTick());
		var1.writeInt(this.EnumToInt(this.DinoInside));
	}

	@Override
	public void readSpawnData(ByteBuf var1)
	{
		this.setBirthTick(var1.readInt());
		this.DinoInside = EnumDinoType.values()[var1.readInt()];
	}
}
