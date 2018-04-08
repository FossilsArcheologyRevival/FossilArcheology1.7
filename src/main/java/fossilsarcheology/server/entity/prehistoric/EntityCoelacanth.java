package fossilsarcheology.server.entity.prehistoric;

import java.util.Collections;
import java.util.List;

import fossilsarcheology.server.entity.ai.FishAIWaterFindTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import fossilsarcheology.Revival;
import fossilsarcheology.server.entity.EntityFishBase;
import fossilsarcheology.server.item.FAItemRegistry;

public class EntityCoelacanth extends EntityFishBase {
    public EntityCoelacanth(World par1World) {
        super(par1World, PrehistoricEntityType.COELACANTH);
        this.setSize(1.9F, 1F);
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.posY < 35.0D && super.getCanSpawnHere();
    }

    @Override
    public String getTexture() {
        return "fossil:textures/model/fish/coelacanth.png";
    }

    @Override
    protected double getSwimSpeed() {
        return 0.35D;
    }
}
