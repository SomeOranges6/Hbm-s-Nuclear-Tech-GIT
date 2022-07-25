package com.hbm.entity.logic;

import com.hbm.explosion.ExplosionThermo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;

public class EntityEulerLaser extends Entity implements IChunkLoader{
	
	public static final int maxAge = 60;
	private Ticket loaderTicket;

	public EntityEulerLaser(World p_i1582_1_) {
		super(p_i1582_1_);
		this.ignoreFrustumCheck = true;
	}

	@Override
	protected void entityInit() { }

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) { }

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) { }
	
	@Override
	public void onUpdate() {
		
		if(this.ticksExisted <= maxAge && !worldObj.isRemote) {
			this.setDead();

			
			EntityBalefire bf = new EntityBalefire(worldObj);
			bf.posX = posX + 0.5;
			bf.posY = posY + 0.5;
			bf.posZ = posZ + 0.5;
			bf.destructionRange = (int) 80;
			worldObj.spawnEntityInWorld(bf);
			
			ExplosionThermo.balemelt(this.worldObj, (int)this.posX, (int)this.posY, (int)this.posZ, 90);
			ExplosionThermo.setEntitiesOnFire(this.worldObj, (int)this.posX, (int)this.posY, (int)this.posZ, 50);
		
			worldObj.playSoundEffect(posX, posY, posZ, "hbm:weapon.mukeExplosion", 25.0F, 0.9F);
			
		   } 
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
	
    @Override
	@SideOnly(Side.CLIENT)
    public boolean isInRangeToRenderDist(double distance)
    {
        return distance < 25000;
    }
    public void init(Ticket ticket) {
		if(!worldObj.isRemote) {
			
            if(ticket != null) {
            	
                if(loaderTicket == null) {
                	
                	loaderTicket = ticket;
                	loaderTicket.bindEntity(this);
                	loaderTicket.getModData();
                }

                ForgeChunkManager.forceChunk(loaderTicket, new ChunkCoordIntPair(chunkCoordX, chunkCoordZ));
            }
        }
	}
}