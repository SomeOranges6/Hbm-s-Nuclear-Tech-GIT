package com.hbm.items.weapon;

import com.hbm.handler.BulletConfigSyncingUtil;
import com.hbm.handler.BulletConfiguration;
import com.hbm.handler.GunConfiguration;
import com.hbm.items.ModItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemGunFolly extends ItemGunBase {

	public ItemGunFolly(GunConfiguration config) {
		super(config);
		
	}
	
	public void startAction(ItemStack stack, World world, EntityPlayer player, boolean main) {
		BulletConfiguration bulletCfg = BulletConfigSyncingUtil.pullConfig(mainConfig.config.get(getMagType(stack)));

		setTimer(stack, bulletCfg.firerate);
		
		if(main) {
			
			int timer = getTimer(stack);
			
			if(timer > 0) {
				timer--;

				if(timer % 20 == 0 && timer != 0)
					world.playSoundAtEntity(player, "hbm:weapon.follyBuzzer", 1.0F, 1.0F);

				if(timer == 0)
					world.playSoundAtEntity(player, "hbm:weapon.follyAquired", 1.0F, 1.0F);
				
				setTimer(stack, timer);
				
			}
		} else {
			setTimer(stack, bulletCfg.firerate);
		}
		if(getTimer(stack) == 0) {
		super.startAction(stack, world, player, main);
		}
		
	}
	public void endAction(ItemStack stack, World world, EntityPlayer player, boolean main) {
		 
		if(getHasShot(stack)) {
			double mult;
			
			if(player.inventory.hasItem(ModItems.memespoon)) {
		     mult = 5D;
			} else if(player.isSneaking()) {
				mult = 1.2D;	
			} else {
			 mult = 1.75D;
			}
			
			player.motionX -= player.getLookVec().xCoord * mult;
			player.motionY -= player.getLookVec().yCoord * mult;
			player.motionZ -= player.getLookVec().zCoord * mult;
			
			}
			setHasShot(stack, false);
		}
	
	
	protected void reload2(ItemStack stack, World world, EntityPlayer player) {
		super.reload2(stack, world, player);
		if (getReloadCycle(stack)== -1){
			world.playSoundAtEntity(player, "hbm:weapon.follyClose", 1.0F, 1.0F);
		}
	}
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		boolean verified = false;
		
		if (!stack.hasTagCompound()) {
			stack.stackTagCompound = new NBTTagCompound();
			stack.stackTagCompound.setBoolean("verified", false);
		}
		
		if(player.inventory.hasItem(ModItems.coin_maskman)) {
         	
         	if(verified == false) {
         	player.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "[EV-1101] Access granted, Fire when ready."));
         	stack.stackTagCompound.setBoolean("verified", true);
         	}
		}
		return stack;
	}
	
	protected void spawnProjectile(World world, EntityPlayer player, ItemStack stack, int config) {
		
		super.spawnProjectile(world, player, stack, config);
		setHasShot(stack, true);
	}
	
	public static void setHasShot(ItemStack stack, boolean b) {
		writeNBT(stack, "hasShot", b ? 1 : 0);
	}
	
	public static boolean getHasShot(ItemStack stack) {
		return readNBT(stack, "hasShot") == 1;
	}
	
	public static void setTimer(ItemStack stack, int i) {
		writeNBT(stack, "timer", i);
	}
	
	public static int getTimer(ItemStack stack) {
		return readNBT(stack, "timer");
	}
}
	

	