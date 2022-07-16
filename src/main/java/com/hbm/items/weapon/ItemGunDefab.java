package com.hbm.items.weapon;

import com.hbm.handler.GunConfiguration;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemGunDefab extends ItemGunBase {
	
	public ItemGunDefab(GunConfiguration config) {
		super(config);
	}
	
	@Override
	public void startAction(ItemStack stack, World world, EntityPlayer player, boolean main) {
		
		if(main) {
			setDelay(stack, 20);
			world.playSoundAtEntity(player, "hbm:weapon.defabSpinup", 2.0F, 1.0F);
		}
	}
}