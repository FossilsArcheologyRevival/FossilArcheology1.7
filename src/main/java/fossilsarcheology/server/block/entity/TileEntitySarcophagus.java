package fossilsarcheology.server.block.entity;

import fossilsarcheology.server.entity.monster.EntityAnu;
import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;

public class TileEntitySarcophagus extends TileEntity implements ITickable {
    public int chestState = 0;
    public int chestLidCounter;

    @Override
    public void update() {
        if (this.chestState != 3) {
            if (chestLidCounter != 0 && chestLidCounter < 91) {
                chestLidCounter++;
            }
            if (chestLidCounter == 91) {
                chestState = 3;
                EntityAnu newMob = new EntityAnu(world);
                if (!world.isRemote) {
                    newMob.setLocationAndAngles(this.pos.getX() + 0.5, this.pos.getY(), this.pos.getZ() + 0.5, 0, 0);

                    world.spawnEntity(newMob);
                }
                newMob.initializeMob();
                world.playSound(null, this.pos.getX(), (double) this.pos.getY() + 0.5D, this.pos.getZ(), SoundEvents.BLOCK_CHEST_CLOSE, SoundCategory.BLOCKS, 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
            }
        } else {
            if (chestLidCounter != 0 && chestLidCounter > 0) {
                chestLidCounter--;
            }
            if (chestLidCounter == 0) {
               chestState = 0;
            }
        }
    }
}
