package com.hbm.entity.logic;

import com.hbm.entity.mob.EntityGlyphid;
import com.hbm.entity.mob.EntityGlyphidNuclear;
import com.hbm.entity.mob.EntityGlyphidScout;
import com.hbm.main.MainRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.List;


public class EntityWaypoint extends Entity {
    public EntityWaypoint(World world) {
        super(world);
        this.isImmuneToFire = true;
        this.noClip = true;
    }
    @Override
    protected void entityInit() {
        this.dataWatcher.addObject(10, 0);
        //this.dataWatcher.addObject(11, 0);

    }
    int maxAge = 2400;
    public int radius = 3;
    public boolean highPriority = false;
    public EntityWaypoint additional;
    public void setHighPriority(){
        highPriority = true;
    }
    public int getWaypointType(){
        return this.dataWatcher.getWatchableObjectInt(10);
    }

    public void setAdditionalWaypoint(EntityWaypoint waypoint){
        additional = waypoint;
    }

    public void setWaypointType(int waypointType) {
        this.dataWatcher.updateObject(10, waypointType);
    }

    public int getColor(){
        switch(getWaypointType()){

            case 1: return 0x5FA6E8;

            case 2:
            case 3:
                return 0x127766;

            default: return 0x566573;
        }
    }
    AxisAlignedBB bb;
    @Override
    public void onEntityUpdate() {
        if (ticksExisted >= maxAge) {
            this.setDead();
        }
        if (!worldObj.isRemote) {

            if (ticksExisted == 1) {
                         bb = AxisAlignedBB.getBoundingBox(
                        this.posX - radius,
                        this.posY - radius,
                        this.posZ - radius,
                        this.posX + radius,
                        this.posY + radius,
                        this.posZ + radius);
            }

            if (ticksExisted % 100 == 0) {

                List<Entity> targets = worldObj.getEntitiesWithinAABBExcludingEntity(this, bb);

                for (Entity e : targets) {
                    if (e instanceof EntityGlyphid) {

                        if (additional != null) {
                            worldObj.spawnEntityInWorld(additional);
                        }

                        ((EntityGlyphid) e).setCurrentTask(getWaypointType(), additional);

                        if (getWaypointType() == 2) {
                            if (e instanceof EntityGlyphidNuclear || e instanceof EntityGlyphidScout)
                                setDead();
                        } else {
                            setDead();
                        }
                    }
                }
            }
        } else {
            AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(
                    this.posX - radius,
                    this.posY - radius,
                    this.posZ - radius,
                    this.posX + radius,
                    this.posY + radius,
                    this.posZ + radius);


            double x = bb.minX + (rand.nextDouble() - 0.5) * (bb.maxX - bb.minX);
            double y = bb.minY + rand.nextDouble() * (bb.maxY - bb.minY);
            double z = bb.minZ + (rand.nextDouble() - 0.5) * (bb.maxZ - bb.minZ);

            NBTTagCompound fx = new NBTTagCompound();
            fx.setString("type", "tower");
            fx.setFloat("lift", 0.5F);
            fx.setFloat("base", 0.75F);
            fx.setFloat("max", 2F);
            fx.setInteger("life", 50 + worldObj.rand.nextInt(10));
            fx.setInteger("color", getColor());
            fx.setDouble("posX", x);
            fx.setDouble("posY", y);
            fx.setDouble("posZ", z);
            MainRegistry.proxy.effectNT(fx);
        }

    }


    @Override
    protected void readEntityFromNBT(NBTTagCompound nbt) {
        this.setWaypointType(nbt.getInteger("type"));
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbt) {
       nbt.setInteger("type", getWaypointType());
    }
}
