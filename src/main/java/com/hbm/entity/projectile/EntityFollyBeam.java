package com.hbm.entity.projectile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityFollyBeam extends EntityBeamBase {
	
	public int maxAge = 100;
	public int age;
    public float scale = 0;
    
    public EntityFollyBeam(World world) {
		super(world);
		this.setSize(1, 4);
		this.ignoreFrustumCheck = true;
		this.isImmuneToFire = true;
		this.age = 0;
    	scale = 0;
	}
   
     public EntityFollyBeam(World world, EntityPlayer player) {
		super(world);
		
		this.ignoreFrustumCheck = true;
		this.dataWatcher.updateObject(20, player.getDisplayName());
		
		Vec3 vec = player.getLookVec();
		vec.rotateAroundY(-90F);
		float l = 0.075F;
		vec.xCoord *= l;
		vec.yCoord *= l;
		vec.zCoord *= l;
		
		Vec3 vec0 = player.getLookVec();
		float d = 0.1F;
		vec0.xCoord *= d;
		vec0.yCoord *= d;
		vec0.zCoord *= d;
		
		this.setPosition(player.posX + vec.xCoord + vec0.xCoord, player.posY + player.getEyeHeight() + vec0.yCoord, player.posZ + vec.zCoord + vec0.zCoord);
	}
     
	@Override
	protected void entityInit() {
		this.dataWatcher.addObject(16, Integer.valueOf(0));
	}

    @Override
	@SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float p_70070_1_)
    {
        return 15728880;
    }

    @Override
	public float getBrightness(float p_70013_1_)
    {
        return 1.0F;
    }

	public EntityFollyBeam(World p_i1582_1_, int maxAge) {
		super(p_i1582_1_);
		this.setSize(20, 40);
		this.isImmuneToFire = true;
		this.setMaxAge(maxAge);
	}

    @Override
	public void onUpdate() {
        this.age++;
        this.worldObj.spawnEntityInWorld(new EntityLightningBolt(this.worldObj, this.posX, this.posY + 200, this.posZ));
        
        if(this.age >= this.getMaxAge())
        {
    		this.age = 0;
        	this.setDead();
        }
        
        this.scale++;
    }

	@Override
	protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {
		age = p_70037_1_.getShort("age");
		scale = p_70037_1_.getShort("scale");
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {
		p_70014_1_.setShort("age", (short)age);
		p_70014_1_.setShort("scale", (short)scale);
		
	}
	
	public void setMaxAge(int i) {
		this.dataWatcher.updateObject(16, Integer.valueOf(i));
	}
	
	public int getMaxAge() {
		return this.dataWatcher.getWatchableObjectInt(16);
	}
	
    @Override
	@SideOnly(Side.CLIENT)
    public boolean isInRangeToRenderDist(double distance)
    {
        return distance < 25000;
    }
}
