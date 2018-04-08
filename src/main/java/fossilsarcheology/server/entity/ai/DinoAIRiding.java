package fossilsarcheology.server.entity.ai;

import fossilsarcheology.server.entity.prehistoric.EntityPrehistoric;
import fossilsarcheology.server.entity.prehistoric.EntityPrehistoricSwimming;
import fossilsarcheology.server.item.FAItemRegistry;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class DinoAIRiding extends EntityAIBase {
    private static final float PLAYER_SPEED = 0.98f;
    private final double speed;
    public int FollowTimeWithoutWhip = 120;
    private int lastTimeSeenWhip = -1;
    private EntityPrehistoric prehistoric;
    private EntityPlayer rider;

    public DinoAIRiding(EntityPrehistoric dinosaur, double speed) {
        super();
        this.prehistoric = dinosaur;
        this.speed = speed;
        setMutexBits(-1);

    }

    public static boolean hasEquipped(EntityPlayer player, Item item) {
        if (player == null) {
            return false;
        }
        ItemStack itemStack = player.getActiveItemStack();

        return itemStack != null && itemStack.getItem() == item;

    }

    @Override
    public boolean shouldExecute() {
        if (prehistoric.getControllingPassenger() != null && prehistoric.getControllingPassenger() instanceof EntityPlayer) {

            rider = prehistoric.getRidingPlayer();
            if (rider == null) {
                return false;
            }
            if (hasEquipped(rider, FAItemRegistry.WHIP)) {
                this.lastTimeSeenWhip = 0;
            }

            return this.lastTimeSeenWhip != -1 && this.prehistoric.getControllingPassenger() != null && this.prehistoric.getRidingPlayer() != null;
        }
        return false;
    }

    @Override
    public void startExecuting() {
        if (prehistoric.getControllingPassenger() != null && prehistoric.getControllingPassenger() instanceof EntityPlayer) {
            prehistoric.getNavigator().clearPath();
            this.lastTimeSeenWhip = -1;
        }
    }

    @Override
    public void resetTask() {
        if (prehistoric.getControllingPassenger() != null && prehistoric.getControllingPassenger() instanceof EntityPlayer) {
            this.lastTimeSeenWhip = -1;
        }
    }

    @Override
    public void updateTask() {
        super.updateTask();
        if (prehistoric.getControllingPassenger() != null && prehistoric.getControllingPassenger() instanceof EntityPlayer) {
            if (rider != null) {
                float speedX = rider.moveForward / PLAYER_SPEED * (prehistoric.getRidingPlayer().isSprinting() ? 4 : 1);
                float speedZ = rider.moveStrafing / PLAYER_SPEED * (prehistoric.getRidingPlayer().isSprinting() ? 4 : 1);

                if (hasEquipped(rider, FAItemRegistry.WHIP) || (this.lastTimeSeenWhip < FollowTimeWithoutWhip && this.lastTimeSeenWhip != -1)) {
                    float speedPlayer = Math.max(Math.abs(speedX), Math.abs(speedZ));
                    Vec3d look = rider.getLookVec();
                    float dir = Math.min(speedX, 0) * -1;
                    dir += speedZ / (speedX * 2 + (speedX < 0 ? -2 : 2));
                    if (dir != 0) {
                        look.rotateYaw((float) Math.PI * dir);
                    }
                    double waterAddition = 0;
                    if (speedPlayer > 0) {
                        if (!prehistoric.shouldDismountInWater(rider) && prehistoric.isInWater()) {
                            prehistoric.motionX = (double) (-MathHelper.sin(rider.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rider.rotationPitch / 180.0F * (float) Math.PI));
                            prehistoric.motionZ = (double) (MathHelper.cos(rider.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rider.rotationPitch / 180.0F * (float) Math.PI));
                        } else {
                            prehistoric.getMoveHelper().setMoveTo(prehistoric.posX + look.x, prehistoric.posY, prehistoric.posZ + look.z, speed);
                        }
                    }
                    if (!prehistoric.shouldDismountInWater(rider) && prehistoric.isInWater()) {
                        if (Math.abs(look.y) > 0.4 && prehistoric instanceof EntityPrehistoricSwimming) {
                            waterAddition = look.y > 0 ? Math.min(0.15, Math.abs(look.y)) : -Math.min(0.15, Math.abs(look.y));
                        }
                        prehistoric.motionY = waterAddition;
                    }
                    this.lastTimeSeenWhip++;
                }
            }
        }
    }
}
