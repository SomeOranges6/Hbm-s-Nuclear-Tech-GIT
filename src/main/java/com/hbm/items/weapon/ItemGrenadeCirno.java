package com.hbm.items.weapon;

import com.hbm.explosion.ExplosionThermo;

import net.minecraft.world.World;

public class ItemGrenadeCirno extends ItemGenericGrenade {

	public ItemGrenadeCirno(int fuse) {
		super(fuse);
	}

	public void explode(World world, double x, double y, double z) {
		ExplosionThermo.freeze(world,(int) x,(int) y,(int) z, 100);
		ExplosionThermo.freezer(world,(int) x,(int) y,(int) z, 100);
	}
}
