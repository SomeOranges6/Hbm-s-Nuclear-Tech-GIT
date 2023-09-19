package com.hbm.items.weapon;

import com.hbm.entity.projectile.EntityBulletBaseNT;
import com.hbm.handler.GunConfiguration;
import com.hbm.packet.GunAnimationPacket;
import com.hbm.packet.PacketDispatcher;
import com.hbm.render.anim.BusAnimation;
import com.hbm.render.anim.BusAnimationKeyframe;
import com.hbm.render.anim.BusAnimationSequence;
import com.hbm.render.anim.HbmAnimations.AnimType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemGunAkimboBase extends ItemGunBase {

	public ItemGunAkimboBase(GunConfiguration config) {
		super(config);
		config.ammoCap *= 2;
		config.rateOfFire *= 0.5;
	}

	@Override
	protected void spawnProjectile(World world, EntityPlayer player, ItemStack stack, int config) {
		if(getMag(stack) % 2 != 0) {
			if(!player.isSneaking())
				spawnProjectileAlt(world, player, stack, config);
			return;
		}
		super.spawnProjectile(world, player, stack, config);
	}

	@Override
	protected void spawnProjectileAlt(World world, EntityPlayer player, ItemStack stack, int config) {
		EntityBulletBaseNT bullet = new EntityBulletBaseNT(world, config, player);
		bullet.offsetOverride = 0.84;
		world.spawnEntityInWorld(bullet);

		if(player instanceof EntityPlayerMP)
			PacketDispatcher.wrapper.sendTo(new GunAnimationPacket(AnimType.ALT_CYCLE.ordinal()), (EntityPlayerMP) player);
	}
}
