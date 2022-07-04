package com.hbm.handler.guncfg;

import net.minecraft.client.Minecraft;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import com.hbm.entity.effect.EntityCloudFleijaRainbow;
import com.hbm.entity.logic.EntityDeathBlast;
import com.hbm.entity.logic.EntityEulerLaser;
import com.hbm.entity.logic.EntityNukeExplosionMK3;
import com.hbm.entity.projectile.EntityBulletBase;
import com.hbm.handler.BulletConfigSyncingUtil;
import com.hbm.handler.BulletConfiguration;
import com.hbm.interfaces.IBulletImpactBehavior;
import com.hbm.interfaces.IBulletUpdateBehavior;
import com.hbm.items.ModItems;

public class GunCannonFactory {

	public static BulletConfiguration getShellConfig() {
		
		BulletConfiguration bullet = BulletConfigFactory.standardShellConfig();
		
		bullet.ammo = ModItems.ammo_shell;
		bullet.dmgMin = 25;
		bullet.dmgMax = 35;
		bullet.explosive = 4F;
		bullet.blockDamage = false;
		
		return bullet;
	}

	public static BulletConfiguration getShellExplosiveConfig() {
		
		BulletConfiguration bullet = BulletConfigFactory.standardShellConfig();
		
		bullet.ammo = ModItems.ammo_shell_explosive;
		bullet.dmgMin = 35;
		bullet.dmgMax = 45;
		bullet.explosive = 4F;
		bullet.blockDamage = true;
		
		return bullet;
	}

	public static BulletConfiguration getShellAPConfig() {
		
		BulletConfiguration bullet = BulletConfigFactory.standardShellConfig();
		
		bullet.ammo = ModItems.ammo_shell_apfsds_t;
		bullet.dmgMin = 50;
		bullet.dmgMax = 55;
		bullet.doesPenetrate = true;
		bullet.style = BulletConfiguration.STYLE_APDS;
		
		return bullet;
	}

	public static BulletConfiguration getShellDUConfig() {
		
		BulletConfiguration bullet = BulletConfigFactory.standardShellConfig();
		
		bullet.ammo = ModItems.ammo_shell_apfsds_du;
		bullet.dmgMin = 70;
		bullet.dmgMax = 80;
		bullet.doesPenetrate = true;
		bullet.style = BulletConfiguration.STYLE_APDS;
		
		return bullet;
	}

	public static BulletConfiguration getShellW9Config() {
		
		BulletConfiguration bullet = BulletConfigFactory.standardShellConfig();
		
		bullet.ammo = ModItems.ammo_shell_w9;
		bullet.dmgMin = 100;
		bullet.dmgMax = 150;
		
		bullet.bImpact = new IBulletImpactBehavior() {

			@Override
			public void behaveBlockHit(EntityBulletBase bullet, int x, int y, int z) {
				BulletConfigFactory.nuclearExplosion(bullet, x, y, z, 1);
			}
		};
		
		return bullet;
	}
	
    public static BulletConfiguration getShellFollyStarConfig() {
		
		BulletConfiguration bullet = BulletConfigFactory.standardShellConfig();
		
		bullet.ammo = ModItems.ammo_folly_tandem;
		bullet.dmgMin = 250;
		bullet.dmgMax = 360;
		
		bullet.bImpact = new IBulletImpactBehavior() {

			@Override
			public void behaveBlockHit (EntityBulletBase bullet, int x, int y, int z) {
				BulletConfigFactory.follyStar(bullet, x, y, z);
				EntityBulletBase bolt = new EntityBulletBase(bullet.worldObj, BulletConfigSyncingUtil.SHELL_FOLLY_NUKE);
				bolt.setPosition(bullet.posX, bullet.posY, bullet.posZ);
				bolt.setThrowableHeading(bullet.motionX, bullet.motionY, bullet.motionZ, 0.1F, 0.1F);
				bullet.worldObj.spawnEntityInWorld(bolt);
			
			}
		};
		
		return bullet;
	}
    
    public static BulletConfiguration getShellFollyNukeConfig() {
		
		BulletConfiguration bullet = BulletConfigFactory.standardShellConfig();
		
		bullet.ammo = ModItems.ammo_folly_nuclear;
		bullet.dmgMin = 300;
		bullet.dmgMax = 330;
		
		bullet.bImpact = new IBulletImpactBehavior() {

			@Override
			public void behaveBlockHit (EntityBulletBase bullet, int x, int y, int z) {
				BulletConfigFactory.nuclearExplosion(bullet, x, y, z, 4);
				
				
			
			}
		};
		
		return bullet;
	}
    
    public static BulletConfiguration getShellFollySleekConfig() {
		
  		BulletConfiguration bullet = BulletConfigFactory.standardShellConfig();
  		
  		bullet.ammo = ModItems.ammo_folly_sleek;
  		bullet.dmgMin = 50;
  		bullet.dmgMax = 60;
  		
  		bullet.bImpact = new IBulletImpactBehavior() {

  			@Override
  			public void behaveBlockHit (EntityBulletBase bullet, int x, int y, int z) {
  				World world = bullet.worldObj;
  				
  				EntityEulerLaser blast = new EntityEulerLaser(world);
  	    		blast.posX = x;
  	    		blast.posY = y;
  	    		blast.posZ = z;
  	    		
  	    		world.spawnEntityInWorld(blast);
  			
  			}
  		};
  		
  		return bullet;
  	}
    
    public static BulletConfiguration getShellFollyDuConfig() {
		
		BulletConfiguration bullet = BulletConfigFactory.standardShellConfig();
		
		bullet.ammo = ModItems.ammo_folly_du;
		bullet.dmgMin = 100;
		bullet.dmgMax = 150;
		bullet.doesPenetrate = true;
		bullet.style = BulletConfiguration.STYLE_APDS;
		
		return bullet;
	} 
    
    public static BulletConfiguration getShellFollyOuchConfig() {
		
		BulletConfiguration bullet = BulletConfigFactory.standardShellConfig();
		
		bullet.ammo = ModItems.ammo_folly_ouch;
		bullet.dmgMin = 80;
		bullet.dmgMax = 90;
		bullet.trail = 0;
		
		bullet.bUpdate = new IBulletUpdateBehavior() {

			@Override
			public void behaveUpdate(EntityBulletBase bullet) {
				
				if(!bullet.worldObj.isRemote) {
					
					if(bullet.ticksExisted > 8) {
						bullet.setDead();
						
						for(int i = 0; i < 80; i++) {
							
							EntityBulletBase bolt = new EntityBulletBase(bullet.worldObj, BulletConfigSyncingUtil.M44_STAR);
							bolt.setPosition(bullet.posX, bullet.posY, bullet.posZ);
							bolt.setThrowableHeading(bullet.motionX, bullet.motionY, bullet.motionZ, 0.25F, 0.1F);
							bullet.worldObj.spawnEntityInWorld(bolt);
						}
						
					}
				}
			}
		};
		return bullet;
     }
 
    public static BulletConfiguration getShellFollyConfig() {
		
		BulletConfiguration bullet = BulletConfigFactory.standardShellConfig();
		
		bullet.ammo = ModItems.ammo_folly;
		bullet.dmgMin = 500;
		bullet.dmgMax = 600;
		bullet.velocity = 10F;
		bullet.trail = 0;
		bullet.HBRC = 0;
		bullet.LBRC = 0;
		bullet.doesPenetrate = true;
		bullet.liveAfterImpact = true;
		
		bullet.bUpdate = new IBulletUpdateBehavior() {
  
			@Override
			public void behaveUpdate(EntityBulletBase bullet) {
				
				World world = Minecraft.getMinecraft().theWorld;
				
				if(!bullet.worldObj.isRemote) {
					
		        int yes = bullet.ticksExisted;
				
				for(int i = 0; i < yes; i++) {
					
					bullet.worldObj.spawnEntityInWorld(EntityNukeExplosionMK3.statFacFleija(bullet.worldObj,bullet.posX ,bullet.posY, bullet.posZ,5+i*2));
							 
					EntityCloudFleijaRainbow cloud = new EntityCloudFleijaRainbow(bullet.worldObj,5+i*2);
					cloud.posX = bullet.posX;
					cloud.posY = bullet.posY;
					cloud.posZ = bullet.posZ;
							
					bullet.worldObj.spawnEntityInWorld(cloud);
					}
				if(bullet.ticksExisted > 15) 
					bullet.setDead();
				
			}
			}
		};
		return bullet;
     }

}
	
