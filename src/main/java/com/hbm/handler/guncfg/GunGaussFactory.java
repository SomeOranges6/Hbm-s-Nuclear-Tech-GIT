package com.hbm.handler.guncfg;

import java.util.ArrayList;

import com.hbm.handler.BulletConfigSyncingUtil;
import com.hbm.handler.BulletConfiguration;
import com.hbm.handler.GunConfiguration;
import com.hbm.inventory.RecipesCommon.ComparableStack;
import com.hbm.items.ModItems;

import com.hbm.main.MainRegistry;

import com.hbm.lib.HbmCollection.EnumGunManufacturer;

import com.hbm.render.util.RenderScreenOverlay.Crosshair;

public class GunGaussFactory {
	
	public static GunConfiguration getXVLConfig() {
		
		GunConfiguration config = new GunConfiguration();
		
		config.rateOfFire = 4;
		config.roundsPerCycle = 1;
		config.gunMode = GunConfiguration.MODE_NORMAL;
		config.firingMode = GunConfiguration.FIRE_AUTO;
		config.reloadDuration = 20;
		config.firingDuration = 0;
		config.ammoCap = 0;
		config.reloadType = GunConfiguration.RELOAD_NONE;
		config.allowsInfinity = true;
		config.crosshair = Crosshair.L_RAD;
		config.durability = 6000;
		config.firingSound = "hbm:weapon.tauShoot";
		
		config.name = "tau";
		config.manufacturer = EnumGunManufacturer.BLACK_MESA;
		
		config.config = new ArrayList<Integer>();
		config.config.add(BulletConfigSyncingUtil.SPECIAL_GAUSS);
		
		return config;
	}
	
	public static GunConfiguration getChargedConfig() {
		
		GunConfiguration config = new GunConfiguration();
		
		config.rateOfFire = 10;
		config.roundsPerCycle = 1;
		config.gunMode = GunConfiguration.MODE_NORMAL;
		config.reloadDuration = 1;
		config.firingDuration = 0;
		config.ammoCap = 0;
		config.reloadType = GunConfiguration.RELOAD_NONE;
		config.allowsInfinity = true;
		config.crosshair = Crosshair.L_ARROWS;
		
		config.config = new ArrayList<Integer>();
		config.config.add(BulletConfigSyncingUtil.SPECIAL_GAUSS_CHARGED);
		
		return config;
	}
    public static GunConfiguration getDefabConfig() {
		
		GunConfiguration config = new GunConfiguration();
		
		config.rateOfFire = 2;
		config.roundsPerCycle = 1;
		config.gunMode = GunConfiguration.MODE_NORMAL;
		config.firingMode = GunConfiguration.FIRE_AUTO;
		config.reloadDuration = 20;
		config.firingDuration = 0;
		config.ammoCap = 120;
		config.reloadType = GunConfiguration.RELOAD_FULL;
		config.allowsInfinity = true;
		config.crosshair = Crosshair.L_CIRCLE;
		config.durability = 10000;
		config.firingSound = "hbm:weapon.defabShoot";
		
		
		config.name = "Defabricator";
		config.manufacturer = "BADWOLF";
		
		if(MainRegistry.polaroidID == 11){
			config.comment.add("Thats a Compact Laser Deluxe, Where were you hiding that?");
			config.comment.add("You really dont wanna know");
		}
		
		else{
		config.comment.add("Did you set your alarm for volcano day?");
        }
		
		config.config = new ArrayList<Integer>();
		config.config.add(BulletConfigSyncingUtil.SPECIAL_DEFAB);
		
		return config;
	}
	

	public static BulletConfiguration getGaussConfig() {
		
		BulletConfiguration bullet = BulletConfigFactory.standardBulletConfig();
		
		bullet.ammo = new ComparableStack(ModItems.gun_xvl1456_ammo);
		bullet.dmgMin = 6;
		bullet.dmgMax = 9;
		bullet.trail = 1;
		bullet.vPFX = "fireworksSpark";
		bullet.LBRC = 80;
		bullet.HBRC = 5;
		
		return bullet;
	}
    public static BulletConfiguration getEnergyConfig() {
		
		BulletConfiguration bullet = BulletConfigFactory.standardBulletConfig();
		bullet.ammoCount = 3;
		bullet.ammo = ModItems.gun_defabricator_ammo;
		bullet.dmgMin = 40;
		bullet.dmgMax = 120;
		bullet.trail = 1;
		bullet.style = BulletConfiguration.BOLT_LACUNAE;
		bullet.LBRC = 80;
		bullet.HBRC = 5;
		bullet.doesPenetrate = true;
		bullet.liveAfterImpact = true;
		
		return bullet;
	}
	
	public static BulletConfiguration getAltConfig() {
		
		BulletConfiguration bullet = getGaussConfig();
		
		bullet.vPFX = "reddust";
		
		return bullet;
	}
}