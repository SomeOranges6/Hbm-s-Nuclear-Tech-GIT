package com.hbm.entity.mob;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.hbm.config.MobConfig;
import com.hbm.entity.logic.EntityWaypoint;
import com.hbm.entity.pathfinder.PathFinderUtils;
import com.hbm.handler.pollution.PollutionHandler;
import com.hbm.handler.pollution.PollutionHandler.PollutionType;
import com.hbm.items.ModItems;
import com.hbm.lib.ModDamageSource;
import com.hbm.main.ResourceManager;

import com.hbm.potion.HbmPotion;
import com.hbm.util.BobMathUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityGlyphid extends EntityMob {
	public boolean hasHome = false;
	public int homeX;
	public int homeY;
	public int homeZ;
	protected int currentTask = 0;
	public int taskX;
	public int taskY;
	public int taskZ;

	EntityWaypoint taskWaypoint = null;
	public EntityGlyphid(World world) {
		super(world);
		/*this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
		this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
		this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));*/
		this.setSize(1.75F, 1F);
	}

	public ResourceLocation getSkin() {
		return ResourceManager.glyphid_tex;
	}

	public double getScale() {
		return 1.0D;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(16, new Byte((byte) 0)); //wall climbing
		this.dataWatcher.addObject(17, new Byte((byte) 0b11111)); //armor
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5D);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if(!worldObj.isRemote) {
			if(!hasHome) {
				homeX = (int) posX;
				homeY = (int) posY;
				homeZ = (int) posZ;
				hasHome = true;
			}


            if(getCurrentTask() == 4 && isAtDestination()){
				setCurrentTask(0, null);
			}

			this.setBesideClimbableBlock(climbCheck());

			if(ticksExisted % 100 == 0) {
				this.swingItem();
			}
		}
	}

	@Override
	protected void dropFewItems(boolean byPlayer, int looting) {
		super.dropFewItems(byPlayer, looting);
		if(rand.nextInt(2) == 0) this.entityDropItem(new ItemStack(ModItems.glyphid_meat, ((int)getScale()*2)  + looting), 0F);
	}

	@Override
	protected Entity findPlayerToAttack() {
		EntityPlayer entityplayer = this.worldObj.getClosestVulnerablePlayerToEntity(this, useExtendedTargeting() ? 128D : 16D);
		return entityplayer != null && this.canEntityBeSeen(entityplayer) ? entityplayer : null;
	}

	@Override
	protected void updateWanderPath() {
		if(getCurrentTask() == 0) {
			super.updateWanderPath();
		}
	}

	@Override
	protected void updateEntityActionState() {
		super.updateEntityActionState();

		if(!this.hasPath()) {

			// hell yeah!!
			if (useExtendedTargeting() && this.entityToAttack != null) {
				this.setPathToEntity(PathFinderUtils.getPathEntityToEntityPartial(worldObj, this, this.entityToAttack, 16F, true, false, false, true));
			} else if(getCurrentTask() != 0) {
				this.worldObj.theProfiler.startSection("stroll");

				if (!isAtDestination()) {

					if (taskWaypoint != null) {

						taskX = (int) taskWaypoint.posX;
						taskY = (int) taskWaypoint.posY;
						taskZ = (int) taskWaypoint.posZ;

						if (taskWaypoint.highPriority) {
							setTarget(taskWaypoint);
						}

					}
					if(taskX != 0) {
						Vec3 vec = Vec3.createVectorHelper(posX, posY, posZ);
						int maxDist = (int) (Math.sqrt(vec.squareDistanceTo(taskX, taskY, taskZ)) * 1.2);
						this.setPathToEntity(PathFinderUtils.getPathEntityToCoordPartial(worldObj, this, taskX, taskY, taskZ, maxDist, true, false, true, true));
					}
				}
				this.worldObj.theProfiler.endSection();
			}
		}
	}

	public boolean climbCheck(){
        return this.hasPath() && isCollidedHorizontally;
	}
	public boolean useExtendedTargeting() {
		return PollutionHandler.getPollution(worldObj, (int) Math.floor(posX), (int) Math.floor(posY), (int) Math.floor(posZ), PollutionType.SOOT) >= MobConfig.targetingThreshold;
	}

	@Override
	protected boolean canDespawn() {
		return entityToAttack == null;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {

		if(!source.isDamageAbsolute() && !source.isUnblockable() && !worldObj.isRemote && !source.isFireDamage() && !source.getDamageType().equals(ModDamageSource.s_cryolator)) {
			byte armor = this.dataWatcher.getWatchableObjectByte(17);

			if(armor != 0) { //if at least one bit of armor is present

				if(amount < getDamageThreshold()) return false;

				 //chances of armor being broken off
				if(amount > 1 && isArmorBroken(amount)) {
					breakOffArmor();
					amount *= 0.25F;
				}

				amount -= getDamageThreshold();
				if(amount < 0) return true;
			}

			amount = this.calculateDamage(amount);
		}

		if(source.isFireDamage()) amount *= 0.6F;

		if(this.isPotionActive(HbmPotion.phosphorus.getId())){
			amount *= 1.5F;
		}

		if(source == ModDamageSource.acid || source.equals(new DamageSource(ModDamageSource.s_acid))) amount = 0;

		return super.attackEntityFrom(source, amount);
	}

	public boolean isArmorBroken(float amount) {
		return this.rand.nextInt(100) <= Math.min(Math.pow(amount * 0.5, 2), 100);
	}

	public float calculateDamage(float amount) {

		byte armor = this.dataWatcher.getWatchableObjectByte(17);
		int divisor = 1;

		for(int i = 0; i < 5; i++) {
			if((armor & (1 << i)) > 0) {
				divisor++;
			}
		}

		amount /= divisor;

		return amount;
	}

	public float getDamageThreshold() {
		return 0.5F;
	}

	public void breakOffArmor() {
		byte armor = this.dataWatcher.getWatchableObjectByte(17);
		List<Integer> indices = Arrays.asList(0, 1, 2, 3, 4);
		Collections.shuffle(indices);

		for(Integer i : indices) {
			byte bit = (byte) (1 << i);
			if((armor & bit) > 0) {
				armor &= ~bit;
				armor = (byte) (armor & 0b11111);
				this.dataWatcher.updateObject(17, armor);
				worldObj.playSoundAtEntity(this, "mob.zombie.woodbreak", 1.0F, 1.25F);
				break;
			}
		}
	}

	@Override
	protected void updateArmSwingProgress() {
		int i = this.swingDuration();

		if(this.isSwingInProgress) {
			++this.swingProgressInt;

			if(this.swingProgressInt >= i) {
				this.swingProgressInt = 0;
				this.isSwingInProgress = false;
			}
		} else {
			this.swingProgressInt = 0;
		}

		this.swingProgress = (float) this.swingProgressInt / (float) i;
	}

	public int swingDuration() {
		return 15;
	}

	@Override
	public void setInWeb() { }

	@Override
	public boolean isOnLadder() {
		return this.isBesideClimbableBlock();
	}

	public boolean isBesideClimbableBlock() {
		return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
	}

	public void setBesideClimbableBlock(boolean climbable) {
		byte watchable = this.dataWatcher.getWatchableObjectByte(16);

		if(climbable) {
			watchable = (byte) (watchable | 1);
		} else {
			watchable &= -2;
		}

		this.dataWatcher.updateObject(16, Byte.valueOf(watchable));
	}

	@Override
	public boolean attackEntityAsMob(Entity victum) {
		if(this.isSwingInProgress) return false;
		this.swingItem();
		return super.attackEntityAsMob(victum);
	}


	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	/// TASK SYSTEM START ///
	public int getCurrentTask(){
		return currentTask;
	}

	public EntityWaypoint getWaypoint(){
		return taskWaypoint;
	}

	public void setCurrentTask(int task, @Nullable EntityWaypoint waypoint){
		currentTask =  task;
		taskWaypoint = waypoint;
		if (taskWaypoint != null) {

			taskX = (int) taskWaypoint.posX;
			taskY = (int) taskWaypoint.posY;
			taskZ = (int) taskWaypoint.posZ;

			if (taskWaypoint.highPriority) {
				setTarget(taskWaypoint);
			}

		}
		carryOutTask();
	}

	public void carryOutTask(){
		int task = getCurrentTask();

		switch(task){

			//call for reinforcements
			case 1: if(taskWaypoint != null){
				communicate(4, taskWaypoint);
				setCurrentTask(4, taskWaypoint);
			}  break;
			//expand the hive
			//case 2: expandHive(null);

			//retreat
			case 3:

				if (!worldObj.isRemote && taskWaypoint == null) {

					//Then, Come back later
					EntityWaypoint additional =  new EntityWaypoint(worldObj);
					additional.setLocationAndAngles(posX, posY, posZ, 0 , 0);

					//First, go home and get reinforcements
					EntityWaypoint home = new EntityWaypoint(worldObj);
					home.setWaypointType(1);
 					home.setAdditionalWaypoint(additional);
					home.setHighPriority();
					home.setLocationAndAngles(homeX, homeY, homeZ, 0, 0);
					worldObj.spawnEntityInWorld(home);

					this.taskWaypoint = home;
					communicate(4, home);
					setCurrentTask(4, taskWaypoint);

					break;
				}

				//the fourth task (case 4) is to just follow the waypoint path
			break;

			default: break;
			
		}

	}

    public void communicate(int task, @Nullable EntityWaypoint waypoint) {
		int radius = waypoint != null ? waypoint.radius : 4;

		AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(
				this.posX - radius,
				this.posY - radius,
				this.posZ - radius,
				this.posX + radius,
				this.posY + radius,
				this.posZ + radius);

		List<Entity> bugs = worldObj.getEntitiesWithinAABBExcludingEntity(this, bb);
		for (Entity e: bugs){
			if(e instanceof EntityGlyphid && !(e instanceof EntityGlyphidScout)){
				if(((EntityGlyphid) e).getCurrentTask() != task){
					((EntityGlyphid) e).setCurrentTask(task, waypoint);
				}
			}
		}
	}

    /** What each type of glyphid does when it is time to expand the hive.
	 * Args: Whether there is a specific coordinate to expand to, can be null.
	 * Returns: whether it has expanded successfully or not **/
	public boolean expandHive(@Nullable EntityWaypoint waypoint){
		return false;
	}

	public boolean isAtDestination() {
		int destinationRadius = taskWaypoint != null ? (int) Math.pow(taskWaypoint.radius-1, 2) : 25;

		return this.getDistanceSq(taskX, taskY, taskZ) <= destinationRadius;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setByte("armor", this.dataWatcher.getWatchableObjectByte(17));

		nbt.setBoolean("hasHome", hasHome);
		nbt.setInteger("homeX", homeX);
		nbt.setInteger("homeY", homeY);
		nbt.setInteger("homeZ", homeZ);

		nbt.setInteger("taskX", taskX);
		nbt.setInteger("taskY", taskY);
		nbt.setInteger("taskZ", taskZ);

		nbt.setInteger("task", currentTask);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		this.dataWatcher.updateObject(17, nbt.getByte("armor"));

		this.hasHome = nbt.getBoolean("hasHome");
		this.homeX = nbt.getInteger("homeX");
		this.homeY = nbt.getInteger("homeY");
		this.homeZ = nbt.getInteger("homeZ");

		this.taskX = nbt.getInteger("taskX");
		this.taskY = nbt.getInteger("taskY");
		this.taskZ = nbt.getInteger("taskZ");

		this.currentTask = nbt.getInteger("task");
	}
}
