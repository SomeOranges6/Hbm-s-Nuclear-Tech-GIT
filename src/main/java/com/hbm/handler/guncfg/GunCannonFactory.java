package com.hbm.handler.guncfg;


import com.hbm.config.BombConfig;
import com.hbm.entity.effect.EntityNukeCloudSmall;
import com.hbm.entity.logic.EntityNukeExplosionMK4;
import com.hbm.handler.BulletConfiguration;
import com.hbm.inventory.RecipesCommon.ComparableStack;

import com.hbm.items.ModItems;

public class GunCannonFactory {
	static final int stockPen = 10000;
	static byte i = 0;
	public static BulletConfiguration getShellConfig() {
		
		final BulletConfiguration bullet = BulletConfigFactory.standardShellConfig();
		
		bullet.ammo = new ComparableStack(ModItems.ammo_shell, 1, i++);
		bullet.dmgMin = 225;
		bullet.dmgMax = 235;
		bullet.penetration = stockPen;
		bullet.explosive = 40F;
		bullet.blockDamage = false;
		
		return bullet;
	}

	public static BulletConfiguration getShellExplosiveConfig() {
		
		final BulletConfiguration bullet = BulletConfigFactory.standardShellConfig();
		
		bullet.ammo = new ComparableStack(ModItems.ammo_shell, 1, i++);
		bullet.dmgMin = 235;
		bullet.dmgMax = 245;
		bullet.penetration = stockPen;
		bullet.explosive = 40F;
		bullet.blockDamage = true;
		
		return bullet;
	}

	public static BulletConfiguration getShellAPConfig() {
		
		final BulletConfiguration bullet = BulletConfigFactory.standardShellConfig();
		
		bullet.ammo = new ComparableStack(ModItems.ammo_shell, 1, i++);
		bullet.dmgMin = 250;
		bullet.dmgMax = 255;
		bullet.penetration = (int) (stockPen * 1.5);
		bullet.doesPenetrate = true;
		bullet.style = BulletConfiguration.STYLE_APDS;
		
		return bullet;
	}

	public static BulletConfiguration getShellDUConfig() {
		
		final BulletConfiguration bullet = BulletConfigFactory.standardShellConfig();
		
		bullet.ammo = new ComparableStack(ModItems.ammo_shell, 1, i++);
		bullet.dmgMin = 270;
		bullet.dmgMax = 280;
		bullet.penetration = stockPen * 2;
		bullet.doesPenetrate = true;
		bullet.style = BulletConfiguration.STYLE_APDS;
		
		return bullet;
	}

	public static BulletConfiguration getShellW9Config() {
		
		final BulletConfiguration bullet = BulletConfigFactory.standardShellConfig();
		
		bullet.ammo = new ComparableStack(ModItems.ammo_shell, 1, i++);
		bullet.dmgMin = 1200;
		bullet.dmgMax = 1250;
		bullet.penetration = stockPen;
		
		bullet.bImpact = (projectile, x, y, z) -> {

			BulletConfigFactory.nuclearExplosion(projectile, x, y, z, 1);
		};
		
		return bullet;
	}
	
	public static BulletConfiguration getShellW9FullConfig()
	{
		final BulletConfiguration bullet = getShellW9Config().clone();
		
		bullet.ammo = new ComparableStack(ModItems.ammo_shell, 1, i++);
		
		bullet.bImpact = (projectile, x, y, z) ->
		{
			projectile.worldObj.playSoundEffect(x, y, z, "random.explode", 1.0f, projectile.worldObj.rand.nextFloat() * 0.1F + 0.9F);

			projectile.worldObj.spawnEntityInWorld(EntityNukeExplosionMK4.statFac(projectile.worldObj, BombConfig.boyRadius, x + 0.5, y + 0.5, z + 0.5));
			projectile.worldObj.spawnEntityInWorld(EntityNukeCloudSmall.statFac(projectile.worldObj, x, y, z, BombConfig.boyRadius));
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
    public static BulletConfiguration getEffectConfig() {
		
		BulletConfiguration bullet = BulletConfigFactory.standardShellConfig();
		
		bullet.ammo = ModItems.ammo_folly;
		bullet.dmgMin = 100;
		bullet.dmgMax = 150;
		bullet.doesPenetrate = true;
		bullet.style = BulletConfiguration.STYLE_FOLLY;
		bullet.maxAge = 25;
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
		bullet.HBRC = 0; //i have no idea how or why but having these ricochet variables in changes stuff so avoid touchy
		bullet.LBRC = 0;
		bullet.doesPenetrate = true;
		bullet.gravity = 0D;
		bullet.liveAfterImpact = true;
		bullet.style = -1;
		bullet.bUpdate = new IBulletUpdateBehavior() {
  
			@Override
			public void behaveUpdate(EntityBulletBase bullet) {
				
				if(!bullet.worldObj.isRemote) {
				
		        int yes = bullet.ticksExisted;
				
				for(int i = 0; i < yes; i++) {
					bullet.worldObj.spawnEntityInWorld(EntityNukeExplosionMK3.statFacFleija(bullet.worldObj,bullet.posX ,bullet.posY, bullet.posZ,5+yes*2));	
				}
				if(bullet.ticksExisted > 15) 
					bullet.setDead();
				
			}
			}
		};
		return bullet;
     }

}
	
