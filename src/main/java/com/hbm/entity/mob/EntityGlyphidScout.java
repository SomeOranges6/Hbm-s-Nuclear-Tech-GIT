package com.hbm.entity.mob;

import com.hbm.blocks.ModBlocks;
import com.hbm.entity.logic.EntityWaypoint;
import com.hbm.handler.pollution.PollutionHandler;
import com.hbm.main.ResourceManager;
import com.hbm.world.feature.GlyphidHive;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class EntityGlyphidScout extends EntityGlyphid {

	boolean hasTarget = false;
	int timer;
	int scoutingRange = 30;
	int minDistanceToHive = 8 ;

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
	public boolean isArmorBroken(float amount) {
		return this.rand.nextInt(100) <= Math.min(Math.pow(amount, 2), 100);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.5D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2D);
	}

	@Override
	protected boolean canDespawn() {
		return true;
	}

	@Override
	public void onUpdate() {

		super.onUpdate();

		if(getCurrentTask() == 0 && taskWaypoint == null) {
				setCurrentTask(2, null);
		}

		if(getCurrentTask() == 2 || getCurrentTask() == 5) {

			if(!worldObj.isRemote && !hasTarget) {
				//Check for whether a big man johnson is nearby, this makes the scout switch into its terraforming task
				if(scoutingRange != 60 && findJohnson()){
					setCurrentTask(5, null);
				}
				if (expandHive(null)){
					this.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 180*20, 1));
					hasTarget = true;
				}
			}

			if (getCurrentTask() == 5 && super.isAtDestination()) {
				communicate(5, null);
			}

			if (ticksExisted % 10 == 0 && isAtDestination()) {
				timer++;
				if (!worldObj.isRemote && doubleCheckHive()) {
					 if(timer == 1) {
						 EntityWaypoint additional = new EntityWaypoint(worldObj);
						 additional.setLocationAndAngles(posX, posY, posZ, 0, 0);
						 additional.setWaypointType(0);

						 //First, go home and get reinforcements
						 EntityWaypoint home = new EntityWaypoint(worldObj);
						 home.setWaypointType(1);
						 home.setAdditionalWaypoint(additional);
						 home.setLocationAndAngles(homeX, homeY, homeZ, 0, 0);
						 home.maxAge = 1200;
						 home.radius = 6;

						 worldObj.spawnEntityInWorld(home);

						 this.taskWaypoint = home;
						 this.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 40 * 20, 10));
						 communicate(1, taskWaypoint);

					 } else if (timer >= 5) {
						 worldObj.newExplosion(this, posX, posY, posZ, 5F, false, false);
						 GlyphidHive.generateBigGround(worldObj, (int) Math.floor(posX), (int) Math.floor(posY), (int) Math.floor(posZ), rand, true);
						 this.setDead();
					 } else {
						 communicate(4, taskWaypoint);
					 }
				}
			}
		}
	}
    public boolean doubleCheckHive(){

		for(int i = 0; i < 8; i++) {
			float angle = (float) Math.toRadians(360D / 16 * i);
			Vec3 rot = Vec3.createVectorHelper(0, 0, 8);
			rot.rotateAroundY(angle);
			Vec3 pos = Vec3.createVectorHelper(this.posX, this.posY + 1, this.posZ);
			Vec3 nextPos = Vec3.createVectorHelper(this.posX + rot.xCoord, this.posY + 1, this.posZ + rot.zCoord);
			MovingObjectPosition mop = this.worldObj.rayTraceBlocks(pos, nextPos);

			if (mop != null && mop.typeOfHit == mop.typeOfHit.BLOCK) {

				Block block = worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ);

				if (block == ModBlocks.glyphid_base) {
					setCurrentTask(0 ,null);
					hasTarget = false;
					return false;
				}

			}
		}
		return true;
	}
	@Override
	public boolean isAtDestination() {
		return this.getCurrentTask() == 2 && super.isAtDestination();
	}

	public boolean findJohnson(){
		int radius = 8;

		AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(
				this.posX - radius,
				this.posY - radius,
				this.posZ - radius,
				this.posX + radius,
				this.posY + radius,
				this.posZ + radius);

		List<Entity> bugs = worldObj.getEntitiesWithinAABBExcludingEntity(this, bb);
		for (Entity e: bugs){
			if(e instanceof EntityGlyphidNuclear){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean expandHive(@Nullable EntityWaypoint waypoint) {

	   int nestX = rand.nextInt((homeX + scoutingRange) - (homeX - scoutingRange)) + (homeX - scoutingRange);
	   int nestZ = rand.nextInt((homeZ + scoutingRange) - (homeZ - scoutingRange)) + (homeZ - scoutingRange);
	   int nestY = worldObj.getHeightValue(nestX, nestZ);

	   Block b = worldObj.getBlock(nestX, nestY - 1, nestZ);
	   boolean distanceCheck = Vec3.createVectorHelper(nestX - homeX, nestY - homeY, nestZ - homeZ).lengthVector() > minDistanceToHive;
	   if(distanceCheck && b.getMaterial() != Material.air && b.isNormalCube() && b != ModBlocks.glyphid_base) {
		   if(!worldObj.isRemote) {
			   EntityWaypoint nest = new EntityWaypoint(worldObj);
			   nest.setWaypointType(getCurrentTask());
			   nest.radius = 3;
			   nest.setLocationAndAngles(nestX, nestY, nestZ, 0, 0);
			   worldObj.spawnEntityInWorld(nest);

			   taskWaypoint = nest;

               //updates the task coordinates
			   setCurrentTask(getCurrentTask(), taskWaypoint);
			   communicate(2, taskWaypoint);
		   }
		   return true;
	   }
		   return false;
	}


	@Override
	public void carryOutTask() {
		if (!worldObj.isRemote && taskWaypoint == null) {
			switch(getCurrentTask()){
				case 3:
					this.clearActivePotions();
					this.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 20 * 20, 4));

					//then, come back later
					EntityWaypoint additional = new EntityWaypoint(worldObj);
					additional.setLocationAndAngles(posX, posY, posZ, 0, 0);
					additional.setWaypointType(0);


					//First, go home and get reinforcements
					EntityWaypoint home = new EntityWaypoint(worldObj);
					home.setWaypointType(2);
					home.setAdditionalWaypoint(additional);
					home.setHighPriority();
					home.radius = 6;
					home.setLocationAndAngles(homeX, homeY, homeZ, 0, 0);
					worldObj.spawnEntityInWorld(home);

					communicate(4, home);
				break;

				//terraforming task, only used if a big man johnson is near the scout
				case 5:
					scoutingRange = 60;
					minDistanceToHive = 20;
			}
		}
		super.carryOutTask();

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
