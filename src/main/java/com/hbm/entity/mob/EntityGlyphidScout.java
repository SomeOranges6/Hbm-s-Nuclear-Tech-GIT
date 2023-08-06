package com.hbm.entity.mob;

import com.hbm.blocks.ModBlocks;
import com.hbm.entity.logic.EntityWaypoint;
import com.hbm.main.ResourceManager;
import com.hbm.world.feature.GlyphidHive;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class EntityGlyphidScout extends EntityGlyphid {

	public EntityGlyphidScout(World world) {
		super(world);
		this.setSize(1.25F, 0.75F);
	}

	@Override
	public float getDamageThreshold() {
		return 0.0F;
	}

	@Override
	public ResourceLocation getSkin() {
		return ResourceManager.glyphid_scout_tex;
	}

	@Override
	public double getScale() {
		return 0.75D;
	}

	@Override
	public int getArmorBreakChance(float amount) {
		return 1;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(16D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.5D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2D);
	}

	@Override
	protected boolean canDespawn() {
		return true;
	}
	boolean hasTarget = false;
	int timer;
	@Override
	public void onUpdate() {

		super.onUpdate();

		if(this.getCurrentTask() == 0) {
				setCurrentTask(2, this.taskWaypoint);
		}

		if(getCurrentTask() == 2) {

			if(!worldObj.isRemote && !hasTarget) {
				if (expandHive(null)){
					hasTarget = true;
				}
			}

			if (isAtDestination() && ticksExisted % 20 == 0) {
				timer++;
				if (!worldObj.isRemote) {
					if(timer == 1) {
						EntityWaypoint additional = new EntityWaypoint(worldObj);
						additional.setLocationAndAngles(posX, posY, posZ, 0, 0);
						additional.setWaypointType(0);

						//First, go home and get reinforcements
						EntityWaypoint home = new EntityWaypoint(worldObj);
						home.setWaypointType(1);
						home.setAdditionalWaypoint(additional);
						home.setHighPriority();
						home.setLocationAndAngles(homeX, homeY, homeZ, 0, 0);
						worldObj.spawnEntityInWorld(home);

						communicate(4, home);
					} else if(timer == 10) {
						worldObj.newExplosion(this, posX, posY, posZ, 5F, false, false);
						GlyphidHive.generateBigGround(worldObj, (int) Math.floor(posX), (int) Math.floor(posY), (int) Math.floor(posZ), rand);
						this.setDead();
					}
				}
			}
		}

	}

	@Override
	public boolean expandHive(@Nullable EntityWaypoint waypoint) {

	   int nestX = rand.nextInt((homeX + 30) - (homeX - 30)) + (homeX - 30);
	   int nestZ = rand.nextInt((homeZ + 30) - (homeZ - 30)) + (homeZ - 30);
	   int nestY = worldObj.getHeightValue(nestX, nestZ);

	   Block b = worldObj.getBlock(nestX, nestY - 1, nestZ);

	   if(b.getMaterial() != Material.air && b.isNormalCube() && b != ModBlocks.glyphid_base) {
		   if(!worldObj.isRemote) {
			   EntityWaypoint nest = new EntityWaypoint(worldObj);
			   nest.setWaypointType(2);
			   nest.setLocationAndAngles(nestX, nestY, nestZ, 0, 0);
			   worldObj.spawnEntityInWorld(nest);

			   taskWaypoint = nest;
			   communicate(2, taskWaypoint);
		   }
		   return true;
	   }

		   return false;
	}

	@Override
	public boolean isAtDestination() {
		return super.isAtDestination() && this.getCurrentTask() == 2;
	}

	@Override
	public void carryOutTask() {
		if (!worldObj.isRemote && taskWaypoint == null) {
			if(getCurrentTask() == 3) {
				this.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 20 * 20, 3));

				//then, come back later
				EntityWaypoint additional = new EntityWaypoint(worldObj);
				additional.setLocationAndAngles(posX, posY, posZ, 0, 0);
				additional.setWaypointType(0);

				//First, go home and get reinforcements
				EntityWaypoint home = new EntityWaypoint(worldObj);
				home.setWaypointType(4);
				home.setAdditionalWaypoint(additional);
				home.setHighPriority();
				home.radius = 6;
				home.setLocationAndAngles(homeX, homeY, homeZ, 0, 0);
				worldObj.spawnEntityInWorld(home);

				communicate(4, home);
			}
		}
		super.carryOutTask();

	}

	@Override
	public void communicate(int task, EntityWaypoint waypoint) {
		int radius = waypoint.radius;
		AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(
				this.posX - radius,
				this.posY - radius,
				this.posZ - radius,
				this.posX + radius,
				this.posY + radius,
				this.posZ + radius);

		List<Entity> bugs = worldObj.getEntitiesWithinAABBExcludingEntity(this, bb);
		for (Entity e: bugs){
			if(e instanceof EntityGlyphid){
				if(((EntityGlyphid) e).getCurrentTask() == 0){
					((EntityGlyphid) e).setCurrentTask(task, waypoint);
				}
			}
		}
		super.communicate(task, waypoint);
	}
	/*
	@Override
	protected void updateWanderPath() {
		this.worldObj.theProfiler.startSection("stroll");
		boolean flag = false;
		int pathX = -1;
		int pathY = -1;
		int pathZ = -1;
		float maxWeight = -99999.0F;

		for(int l = 0; l < 5; ++l) {
			int x = MathHelper.floor_double(this.posX + (double) this.rand.nextInt(25) - 12.0D);
			int y = MathHelper.floor_double(this.posY + (double) this.rand.nextInt(11) - 5.0D);
			int z = MathHelper.floor_double(this.posZ + (double) this.rand.nextInt(25) - 12.0D);
			float weight = this.getBlockPathWeight(x, y, z);

			if(weight > maxWeight) {
				maxWeight = weight;
				pathX = x;
				pathY = y;
				pathZ = z;
				flag = true;
			}
		}

		if(flag) {
			this.setPathToEntity(this.worldObj.getEntityPathToXYZ(this, pathX, pathY, pathZ, 10.0F, true, false, false, true));
		}

		this.worldObj.theProfiler.endSection();
	}*/

}
