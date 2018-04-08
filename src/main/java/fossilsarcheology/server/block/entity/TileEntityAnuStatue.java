package fossilsarcheology.server.block.entity;

import fossilsarcheology.server.achievement.FossilAchievements;
import fossilsarcheology.server.block.FABlockRegistry;
import fossilsarcheology.server.entity.utility.EntityAnuEffect;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class TileEntityAnuStatue extends TileEntity implements ITickable {

    @Override
    public void update() {
        for (int var7 = 0; var7 < world.playerEntities.size(); ++var7) {
            EntityPlayer P = (EntityPlayer) world.playerEntities.get(var7);

            if (Math.pow(this.pos.getX() - P.posX, 2D) + Math.pow(this.pos.getY() - P.posY, 2D) + Math.pow(this.pos.getZ() - P.posZ, 2D) < 40) {
                //P.addStat(FossilAchievements.FIND_ANU_TOTEM, 1);
            }
        }

        if (world.getBlockState(new BlockPos(this.pos.getX() - 1, pos.getY(), pos.getZ() - 1)).getBlock() == FABlockRegistry.FIGURINE && world.getBlockState(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() - 1)).getBlock() == FABlockRegistry.FIGURINE && world.getBlockState(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() + 1)).getBlock() == FABlockRegistry.FIGURINE && world.getBlockState(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() + 1)).getBlock() == FABlockRegistry.FIGURINE && world.getBlockState(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ())).getBlock() == Blocks.REDSTONE_WIRE && world.getBlockState(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ())).getBlock() == Blocks.REDSTONE_WIRE && world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1)).getBlock() == Blocks.REDSTONE_WIRE && world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1)).getBlock() == Blocks.REDSTONE_WIRE) {
            for (int var7 = 0; var7 < world.playerEntities.size(); ++var7) {
                EntityPlayer P = (EntityPlayer) world.playerEntities.get(var7);

                if (Math.pow(this.pos.getX() - P.posX, 2D) + Math.pow(this.pos.getY() - P.posY, 2D) + Math.pow(this.pos.getZ() - P.posZ, 2D) < 90) {
                    //P.addStat(FossilAchievements.ANU_PORTAL, 1);
                }
            }
            world.newExplosion(null, pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5, 5F, true, true);
            EntityAnuEffect newMob = new EntityAnuEffect(world);
            if (!world.isRemote) {
                newMob.setLocationAndAngles(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0, 0);

                world.spawnEntity(newMob);
            }
            newMob.setAnuRotation(FABlockRegistry.ANU_STATUE.getMetaFromState(world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()))));
            newMob.playSummonSong();
            newMob.setHealth(0);
            world.setBlockToAir(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ()));
            world.setBlockToAir(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ()));
            world.setBlockToAir(new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1));
            world.setBlockToAir(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1));
            world.setBlockToAir(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() - 1));
            world.setBlockToAir(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() + 1));
            world.setBlockToAir(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() + 1));
            world.setBlockToAir(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() - 1));
            world.setBlockToAir(new BlockPos(pos.getX(), pos.getY(), pos.getZ()));
            world.setBlockToAir(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()));

        }
    }
}
