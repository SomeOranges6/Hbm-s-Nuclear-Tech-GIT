package com.hbm.entity.grenade;

import com.hbm.explosion.ExplosionChaos;
import com.hbm.items.ModItems;
import com.hbm.items.ItemAmmoEnums.AmmoHandGrenade;
import com.hbm.items.weapon.ItemGrenade;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityGrenadeIFMystery extends EntityGrenadeBouncyBase {

    public EntityGrenadeIFMystery(World p_i1773_1_)
    {
        super(p_i1773_1_);
    }

    public EntityGrenadeIFMystery(World p_i1774_1_, EntityLivingBase p_i1774_2_)
    {
        super(p_i1774_1_, p_i1774_2_);
    }

    public EntityGrenadeIFMystery(World p_i1775_1_, double p_i1775_2_, double p_i1775_4_, double p_i1775_6_)
    {
        super(p_i1775_1_, p_i1775_2_, p_i1775_4_, p_i1775_6_);
    }

    @Override
    public void explode() {
    	
        if (!this.worldObj.isRemote)
        {
            this.setDead();
    		
    		worldObj.newExplosion(this, posX, posY, posZ, 10, false, false);
    		
    		ExplosionChaos.spawnVolley(worldObj, posX, posY, posZ, 100, 1.0D);
        }
    }

	@Override
	protected int getMaxTimer() {
		return AmmoHandGrenade.IF_MYSTERY.fuse * 20;
	}

	@Override
	protected double getBounceMod() {
		return 0.25D;
	}
}
