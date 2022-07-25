package com.hbm.handler.guncfg;


import java.util.ArrayList;

import net.minecraft.world.World;

import com.hbm.config.BombConfig;
import com.hbm.entity.effect.EntityNukeCloudSmall;
import com.hbm.entity.logic.EntityEulerLaser;
import com.hbm.entity.logic.EntityNukeExplosionMK3;
import com.hbm.entity.logic.EntityNukeExplosionMK4;
import com.hbm.entity.projectile.EntityBulletBase;
import com.hbm.handler.BulletConfigSyncingUtil;
import com.hbm.handler.BulletConfiguration;
import com.hbm.handler.GunConfiguration;
import com.hbm.inventory.RecipesCommon.ComparableStack;
import com.hbm.items.ModItems;
import com.hbm.lib.HbmCollection;
import com.hbm.lib.HbmCollection.EnumGunManufacturer;
import com.hbm.render.util.RenderScreenOverlay.Crosshair;

public class GunCannonFactory {
	static final int stockPen = 10000;
	static byte i = 0;
	//used for technical thingmabobs
    public static GunConfiguration getFollyConfig() {
		
		GunConfiguration config = new GunConfiguration();
		
		config.rateOfFire = 20;
		config.roundsPerCycle = 1;
		config.gunMode = GunConfiguration.MODE_NORMAL;
		config.firingMode = GunConfiguration.FIRE_MANUAL;
		config.reloadDuration = 60;
		config.firingDuration = 0;
		config.ammoCap = 1;
		config.reloadType = GunConfiguration.RELOAD_FULL;
		config.allowsInfinity = true;
		config.crosshair = Crosshair.L_CIRCUMFLEX;
		config.firingSound = "hbm:weapon.follyShoot";
		config.reloadSound = "hbm:weapon.follyOpen";
		config.reloadSoundEnd = false;
		
		config.name = "Folly";
		config.manufacturer = EnumGunManufacturer.F_STRONG;
		
		config.config = HbmCollection.silver;
		config.durability = 100000;
		
		return config;
	}
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
	
    
    
public static BulletConfiguration getShellFollyConfig() {
		
		BulletConfiguration bullet = BulletConfigFactory.standardShellConfig();
		
		bullet.ammo = new ComparableStack(ModItems.ammo_folly, 1);
		bullet.dmgMin = 500;
		bullet.dmgMax = 600;
		bullet.firerate = 100;
		bullet.velocity = 10F;
		bullet.HBRC = 0; //i have no idea how or why but having these ricochet variables in changes stuff so avoid touchy
		bullet.LBRC = 0;
		bullet.doesPenetrate = true;
		bullet.gravity = 0D;
		bullet.liveAfterImpact = true;
		bullet.style = -1;
		bullet.bUpdate = (projectile) -> {
			  
				if(!projectile.worldObj.isRemote) {
				
		        int yes = projectile.ticksExisted;
				
				for(int i = 0; i < yes; i++) {
					int r = (int)(5+yes*1.5);
					projectile.worldObj.spawnEntityInWorld(EntityNukeExplosionMK3.statFacFleija(projectile.worldObj,projectile.posX ,projectile.posY, projectile.posZ,r));	
				}
			
				if(projectile.ticksExisted > 15) 
					projectile.setDead();
			
		};
		};
		
		return bullet;
     }
    
    public static BulletConfiguration getShellFollyNukeConfig() {
		
		BulletConfiguration bullet = BulletConfigFactory.standardShellConfig();
		
		bullet.ammo = new ComparableStack(ModItems.ammo_folly, 1, 1);
		bullet.dmgMin = 500;
		bullet.dmgMax = 600;
		bullet.firerate = 50;
		bullet.penetration = stockPen;
		
		bullet.bImpact = (projectile, x, y, z) -> {

			BulletConfigFactory.nuclearExplosion(projectile, x, y, z, 5);
		};
		
		return bullet;
	}
    
    public static BulletConfiguration getShellFollyStarConfig() {
		
    	final BulletConfiguration bullet = BulletConfigFactory.standardShellConfig();
		
		bullet.ammo = new ComparableStack(ModItems.ammo_folly, 1, 2);
		bullet.dmgMin = 250;
		bullet.firerate = 100;
		bullet.dmgMax = 360;
		bullet.rainbow = 5;
			bullet.bImpact = (projectile, x, y, z) ->
			{
				EntityBulletBase bolt = new EntityBulletBase(projectile.worldObj, BulletConfigSyncingUtil.SHELL_FOLLY_NUKE);
				bolt.setPosition(projectile.posX, projectile.posY, projectile.posZ);
				bolt.setThrowableHeading(projectile.motionX, projectile.motionY, projectile.motionZ, 0.1F, 0.1F);
				projectile.worldObj.spawnEntityInWorld(bolt);
			
			};
	
		
		return bullet;
    }	
    
    
    public static BulletConfiguration getShellFollyDuConfig() {
		
	BulletConfiguration bullet = BulletConfigFactory.standardShellConfig();
		
		bullet.ammo = new ComparableStack(ModItems.ammo_folly, 1, 3);
		bullet.dmgMin = 100;
		bullet.dmgMax = 150;
		bullet.firerate = 25;
		bullet.penetration = stockPen;
		bullet.style = BulletConfiguration.STYLE_APDS;
		
		return bullet;
	}
   
    public static BulletConfiguration getShellFollySleekConfig() {
		
  		BulletConfiguration bullet = BulletConfigFactory.standardShellConfig();
  		
  		bullet.ammo = new ComparableStack(ModItems.ammo_folly, 1, 4);
  		bullet.dmgMin = 50;
  		bullet.dmgMax = 60;
  		bullet.firerate = 100;
  		bullet.bImpact = (projectile, x, y, z) -> {

  				World world = projectile.worldObj;
  				
  				EntityEulerLaser blast = new EntityEulerLaser(world);
  	    		blast.posX = x;
  	    		blast.posY = y;
  	    		blast.posZ = z;
  	    		
  	    		world.spawnEntityInWorld(blast);
  			
  			};
  		
  		
  		return bullet;
  	}
    public static BulletConfiguration getShellFollyOuchConfig() {
		
		BulletConfiguration bullet = BulletConfigFactory.standardShellConfig();
		
		bullet.ammo = new ComparableStack(ModItems.ammo_folly, 1, 5);
		bullet.dmgMin = 80;
		bullet.dmgMax = 90;
		bullet.firerate = 25;
		bullet.trail = 0;
		
		bullet.bUpdate = (projectile) -> {
			
				if(!projectile.worldObj.isRemote) {
					
					if(projectile.ticksExisted > 8) {
						projectile.setDead();
						
						for(int i = 0; i < 80; i++) {
							
							EntityBulletBase bolt = new EntityBulletBase(projectile.worldObj, BulletConfigSyncingUtil.M44_STAR);
							bolt.setPosition(projectile.posX, projectile.posY, projectile.posZ);
							bolt.setThrowableHeading(projectile.motionX, projectile.motionY, projectile.motionZ, 0.25F, 0.1F);
							projectile.worldObj.spawnEntityInWorld(bolt);
						}
						
					}
				}
			
		};
		return bullet;
     }
     
      public static BulletConfiguration getEffectConfig() {
		
		BulletConfiguration bullet = BulletConfigFactory.standardShellConfig();
		
		bullet.ammo = new ComparableStack(ModItems.nothing);
		bullet.dmgMin = 100;
		bullet.dmgMax = 150;
		bullet.doesPenetrate = true;
		bullet.style = BulletConfiguration.STYLE_FOLLY;
		bullet.maxAge = 25;
		return bullet;
	} 
    

}
	
