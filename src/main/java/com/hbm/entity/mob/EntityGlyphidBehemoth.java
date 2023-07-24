package com.hbm.entity.mob;

import com.hbm.entity.effect.EntityMist;
import com.hbm.entity.projectile.EntityChemical;
import com.hbm.inventory.FluidContainerRegistry;
import com.hbm.inventory.fluid.FluidType;
import com.hbm.inventory.fluid.Fluids;
import com.hbm.items.ModItems;
import com.hbm.main.ResourceManager;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;

public class EntityGlyphidBehemoth extends EntityGlyphid {

	public EntityGlyphidBehemoth(World world) {
		super(world);
		this.setSize(2.5F, 1.5F);
	}
	
	@Override
	public ResourceLocation getSkin() {
		return ResourceManager.glyphid_behemoth_tex;
	}

	@Override
	public double getScale() {
		return 1.5D;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(25D);
	}
	public int timer = 120;
	int breathTime = 0;
	@Override
	public int getArmorBreakChance(float amount) {
		return amount < 20 ? 10 : amount < 100 ? 5 : amount > 200 ? 1 : 3;
	}
	@Override
	public void onUpdate(){
		super.onUpdate();
		Entity e = this.getEntityToAttack();
		if (e == null) {
			timer = 120;
			breathTime = 0;
		} else {
			if (breathTime > 0) {
				if(!isSwingInProgress){
					this.swingItem();
				}
				acidAttack();
				breathTime--;
			} else if (--timer <= 0) {
				breathTime = 120;
				timer = 120;
			}
		}

	}
	@Override
	public boolean attackEntityAsMob(Entity victum) {
		return super.attackEntityAsMob(victum);
	}

	@Override
	public void onDeath(DamageSource source) {
		super.onDeath(source);
		if (!worldObj.isRemote) {
			EntityMist mist = new EntityMist(worldObj);
			mist.setType(Fluids.ACID);
			mist.setPosition(posX, posY, posZ);
			mist.setArea(10, 4);
			mist.setDuration(120);
			worldObj.spawnEntityInWorld(mist);
		}
	}



	public void acidAttack(){
		if (!worldObj.isRemote) {
			this.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 2 * 20, 6));
			EntityChemical chem = new EntityChemical(worldObj, this);
			chem.setFluid(Fluids.ACID);
			worldObj.spawnEntityInWorld(chem);
		}
	}

	@Override
	protected void dropFewItems(boolean byPlayer, int looting) {
		if(rand.nextInt(2) == 0) this.entityDropItem(new ItemStack(ModItems.glyphid_gland, 1, Fluids.SULFURIC_ACID.getID()), 1);
		super.dropFewItems(byPlayer, looting);
	}

	@Override
	public int swingDuration() {
		return 100;
	}
	@Override
	public float calculateDamage(float amount) {

		byte armor = this.dataWatcher.getWatchableObjectByte(17);
		int divisor = 1;
		
		for(int i = 0; i < 5; i++) {
			if((armor & (1 << i)) > 0) {
				divisor += 3;
			}
		}
		
		amount /= divisor;
		
		return amount;
	}

	@Override
	public float getDamageThreshold() {
		return 2.5F;
	}
}
