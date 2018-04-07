package fossilsarcheology.server.entity;

import com.google.common.collect.Lists;
import fossilsarcheology.server.item.FAItemRegistry;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class StoneTabletEntity extends EntityHanging implements IEntityAdditionalSpawnData {
	public StoneTabletEntity.Variant variant;

	public StoneTabletEntity(World world) {
		super(world);
	}

	public StoneTabletEntity(World world, BlockPos pos, EnumFacing facing) {
		super(world, pos);
		List<StoneTabletEntity.Variant> validVariants = Lists.newArrayList();
		for (StoneTabletEntity.Variant variant : StoneTabletEntity.Variant.values()) {
			this.variant = variant;
			this.updateFacingWithBoundingBox(facing);
			if (this.onValidSurface()) {
				validVariants.add(variant);
			}
		}
		if (!validVariants.isEmpty()) {
			this.variant = validVariants.get(this.rand.nextInt(validVariants.size()));
		}
		this.updateFacingWithBoundingBox(facing);
	}

	@SideOnly(Side.CLIENT)
	public StoneTabletEntity(World world, BlockPos pos, EnumFacing facing, String title) {
		this(world, pos, facing);
		for (StoneTabletEntity.Variant variant : StoneTabletEntity.Variant.values()) {
			if (variant.title.equals(title)) {
				this.variant = variant;
				break;
			}
		}
		this.updateFacingWithBoundingBox(facing);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		compound.setString("Variant", this.variant.title);
		super.writeEntityToNBT(compound);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		String variantName = compound.getString("Variant");
		for (StoneTabletEntity.Variant variant : StoneTabletEntity.Variant.values()) {
			if (variant.title.equals(variantName)) {
				this.variant = variant;
			}
		}
		if (this.variant == null) {
			this.variant = Variant.FLAT_CREEP;
		}
		super.readEntityFromNBT(compound);
	}

	@Override
	public int getWidthPixels() {
		return this.variant.sizeX;
	}

	@Override
	public int getHeightPixels() {
		return this.variant.sizeY;
	}

	@Override
	public void onBroken(Entity brokenEntity) {
		if (this.world.getGameRules().getBoolean("doEntityDrops")) {
			this.playSound(SoundEvents.BLOCK_STONE_PLACE, 1.0F, 1.0F);
			if (brokenEntity instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) brokenEntity;
				if (player.capabilities.isCreativeMode) {
					return;
				}
			}
			this.entityDropItem(new ItemStack(FAItemRegistry.STONE_TABLET), 0.0F);
		}
	}

	@Override
	public void playPlaceSound() {
		this.playSound(SoundEvents.BLOCK_STONE_PLACE, 1.0F, 1.0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
	}

	@Override
	public void setLocationAndAngles(double x, double y, double z, float yaw, float pitch) {
		BlockPos positionOffset = new BlockPos(x - this.posX, y - this.posY, z - this.posZ);
		BlockPos newPosition = this.hangingPosition.add(positionOffset);
		this.setPosition(newPosition.getX(), newPosition.getY(), newPosition.getZ());
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		buffer.writeByte(this.variant.ordinal());
		buffer.writeLong(this.hangingPosition.toLong());
		buffer.writeByte(this.facingDirection.getHorizontalIndex());
	}

	@Override
	public void readSpawnData(ByteBuf buffer) {
		this.variant = Variant.values()[buffer.readByte()];
		this.hangingPosition = BlockPos.fromLong(buffer.readLong());
		this.updateFacingWithBoundingBox(EnumFacing.getHorizontal(buffer.readByte()));
	}

	@Override
	public ItemStack getPickedResult(RayTraceResult target) {
		return new ItemStack(FAItemRegistry.STONE_TABLET, 1);
	}

	public enum Variant {
		LIGHTNING("Lightning", 32, 16, 0, 0),
		SOCIAL("Social", 16, 16, 32, 0),
		GREAT_WAR("Greatwar", 32, 32, 0, 16),
		CLOCK("clock", 32, 16, 0, 48),
		PORTAL("Portal", 32, 32, 0, 64),
		HEROBRINE("Herobrine", 32, 32, 32, 32),
		FLAT_CREEP("FlatCreep", 16, 16, 48, 0),
		ANGRY("annoyangry", 16, 16, 48, 16),
		REX_1("Rex1", 32, 32, 64, 0),
		REX_2("Rex2", 32, 16, 64, 32),
		REX_3("Rex3", 32, 16, 64, 48),
		REX_4("Rex4", 32, 32, 64, 64),
		PUZZLE("Puzzle", 32, 32, 32, 64),
		GUN_FIGHT("GunFight", 64, 32, 32, 96),
		PRINCESS("Princess", 32, 32, 0, 96),
		MOSAURUS("Mosa", 32, 16, 64, 128),
		HOLY_MOSAURUS("HolyMosasaurus", 64, 32, 0, 128),
		ANCI_TM("AnciTM", 32, 32, 96, 0),
		MOD_TM("ModTM", 16, 32, 128, 0),
		VIG_TM("VigTM", 32, 32, 144, 0),
		SABER_HUNT("SaberHunt", 32, 16, 96, 32),
		ANU_PORTAL("AnuPortal", 32, 32, 96, 48),
		ANUBITE_1("Anubite1", 16, 16, 128, 32),
		ANUBITE_2("Anubite2", 16, 16, 144, 32),
		ANUBITE_3("Anubite3", 16, 16, 160, 32),
		ANUBITE_4("Anubite4", 16, 16, 176, 32),
		SARCOPHAGUS_OPEN("sarcophagus_open", 32, 32, 128, 48),
		SARCOPHAGUS_KILL("sarcophagus_kill", 32, 32, 96, 80),
		DEAD_ANU("deadAnu", 32, 32, 128, 80);

		public final String title;
		public final int sizeX;
		public final int sizeY;
		public final int offsetX;
		public final int offsetY;

		Variant(String title, int xSize, int ySize, int textureX, int textureY) {
			this.title = title;
			this.sizeX = xSize;
			this.sizeY = ySize;
			this.offsetX = textureX;
			this.offsetY = textureY;
		}
	}
}