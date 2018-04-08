package fossilsarcheology.server.block.entity;

import fossilsarcheology.server.achievement.FossilAchievements;
import fossilsarcheology.server.entity.monster.EntityAnubite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityAnubiteStatue extends TileEntity implements ITickable {

    @Override
    public void update() {
        EntityPlayer player = this.world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 5, false);
        if (player != null) {
            //player.addStat(FossilAchievements.ANUBITE_ENCOUNTER, 1);
            if (!player.capabilities.isCreativeMode) {
                world.newExplosion(null, pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5, 5F, true, true);
                EntityAnubite newMob = new EntityAnubite(world);
                if (!world.isRemote) {
                    newMob.setLocationAndAngles(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0, 0);
                    world.spawnEntity(newMob);
                    world.removeTileEntity(pos);
                    world.setBlockState(pos, Blocks.AIR.getDefaultState());
                }
            }
        }
    }
}
