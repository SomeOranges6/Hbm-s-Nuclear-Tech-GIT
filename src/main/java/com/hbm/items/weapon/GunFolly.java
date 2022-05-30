package com.hbm.items.weapon;

import java.util.List;

import com.hbm.entity.effect.EntityCloudFleijaRainbow;
import com.hbm.entity.logic.EntityNukeExplosionMK3;
import com.hbm.entity.particle.EntitySSmokeFX;
import com.hbm.entity.projectile.EntityBulletBase;
import com.hbm.handler.BulletConfigSyncingUtil;
import com.hbm.interfaces.IHoldableWeapon;
import com.hbm.items.ModItems;
import com.hbm.main.MainRegistry;
import com.hbm.render.util.RenderScreenOverlay.Crosshair;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class GunFolly extends Item implements IHoldableWeapon {

	@Override
	public Crosshair getCrosshair() {
		return Crosshair.L_SPLIT;
	}
	
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		//man I wish old zomg was a thing:
		if (!stack.hasTagCompound()) {
			stack.stackTagCompound = new NBTTagCompound();
			stack.stackTagCompound.setBoolean("verified", false);
		}
		
		boolean verified = stack.stackTagCompound.getBoolean("verified");
		
		int state = getState(stack);
		
		int bulletType = getType(stack);
		
		switch (state) {
		
		case 0:
		
			world.playSoundAtEntity(player, "hbm:weapon.follyOpen", 1.0F, 1.0F);
			setState(stack, 1);
			break;
			
		case 1:
			
			if(player.inventory.hasItem(ModItems.ammo_folly_nuclear)) {

				world.playSoundAtEntity(player, "hbm:weapon.follyReload", 1.0F, 1.0F);
				player.inventory.consumeInventoryItem(ModItems.ammo_folly_nuclear);
				
				setState(stack, 2);
				setType(stack,1);
				
			} else if(player.inventory.hasItem(ModItems.ammo_folly)) {
				
    			world.playSoundAtEntity(player, "hbm:weapon.follyReload", 1.0F, 1.0F);
    			player.inventory.consumeInventoryItem(ModItems.ammo_folly);
    				
    			setState(stack, 2);
    			setType(stack,5);
    			
			} else if(player.inventory.hasItem(ModItems.ammo_folly_du)) {
				
				world.playSoundAtEntity(player, "hbm:weapon.follyReload", 1.0F, 1.0F);
				player.inventory.consumeInventoryItem(ModItems.ammo_folly_du);
				
				setState(stack, 2);
				setType(stack,2);

           } else if(player.inventory.hasItem(ModItems.ammo_folly_tandem)) {
				
				world.playSoundAtEntity(player, "hbm:weapon.follyReload", 1.0F, 1.0F);
				player.inventory.consumeInventoryItem(ModItems.ammo_folly_tandem);
				
				setState(stack, 2);
				setType(stack,3);
           } else if(player.inventory.hasItem(ModItems.ammo_folly_ouch)) {
				
				world.playSoundAtEntity(player, "hbm:weapon.follyReload", 1.0F, 1.0F);
				player.inventory.consumeInventoryItem(ModItems.ammo_folly_ouch);
				
				setState(stack, 2);
				setType(stack,6);
				
            } else if(player.inventory.hasItem(ModItems.coin_maskman)) {
            	
            	player.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "[IF Systems] Access granted, Fire when ready."));
            	stack.stackTagCompound.setBoolean("verified", true);
            	
            } else if(player.inventory.hasItem(ModItems.ammo_folly_sleek)) {
            	
            	if(verified == true) {
				
				world.playSoundAtEntity(player, "hbm:weapon.follyReload", 1.0F, 1.0F);
				player.inventory.consumeInventoryItem(ModItems.ammo_folly_sleek);
				setState(stack, 2);
				setType(stack,4);
				
            	} else {
                	player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "[IF Systems] Access to Silos Denied, Verify using a IF-R&D M.A.S.K token"));
            	}
             } else if(player.inventory.hasItem(ModItems.folly_shell)) {
				
				world.playSoundAtEntity(player, "hbm:weapon.follyReload", 1.0F, 1.0F);
				player.inventory.consumeInventoryItem(ModItems.folly_shell);
				
				setState(stack, 2);
				setType(stack,0);	
       
			} else {
				
				world.playSoundAtEntity(player, "hbm:weapon.follyClose", 1.0F, 1.0F);
				setState(stack, 0);
				setType(stack,0);
				
			}
			break;
			
		case 2:

			world.playSoundAtEntity(player, "hbm:weapon.follyClose", 1.0F, 1.0F);
			setState(stack, 3);
			switch (bulletType) {
			
			case 1:
			setTimer(stack, 50);
			break;
			
			case 2:
			setTimer(stack, 25);
			break;
			
			default:setTimer(stack, 100);
			};
		case 3:
			
			if(getTimer(stack) == 0) {
				
				setState(stack, 0);
				world.playSoundAtEntity(player, "hbm:weapon.follyFire", 1.0F, 1.0F);
				
				double mult;
				
				if(player.inventory.hasItem(ModItems.memespoon)) {
			     mult = 5D;
				} else {
				 mult = 1.75D;
				}
				
				player.motionX -= player.getLookVec().xCoord * mult;
				player.motionY -= player.getLookVec().yCoord * mult;
				player.motionZ -= player.getLookVec().zCoord * mult;

				if (!world.isRemote) {
					final int config;
					switch (bulletType)
					{
					  case 1: config = BulletConfigSyncingUtil.SHELL_FOLLY_NUKE; break;
					  case 2: config = BulletConfigSyncingUtil.SHELL_FOLLY_DU; break;
					  case 3: config = BulletConfigSyncingUtil.SHELL_FOLLY_STAR; break;
					  case 4: config = BulletConfigSyncingUtil.SHELL_FOLLY_SLEEK; break;
					  case 6: config = BulletConfigSyncingUtil.SHELL_FOLLY_OUCH; break;
					  
					  case 5: config = BulletConfigSyncingUtil.TEST_CONFIG;
					  
					  for(int i = 0; i < 10; i++) {
						  
						  world.spawnEntityInWorld(EntityNukeExplosionMK3.statFacFleija(world,player.posX + player.getLookVec().xCoord *i*5 ,player.posY+player.getLookVec().yCoord + player.eyeHeight,player.posZ +player.getLookVec().zCoord *i*5 , i*2));
							 
							EntityCloudFleijaRainbow cloud = new EntityCloudFleijaRainbow(world, i*2);
							//these mafs are the same ones used for the blast 
							cloud.posX = player.posX + player.getLookVec().xCoord *i*5;
							cloud.posY = player.posY + player.getLookVec().yCoord + player.eyeHeight;
							cloud.posZ = player.posZ + player.getLookVec().zCoord *i*5;
							
							world.spawnEntityInWorld(cloud);
							
					  } 
					       
					  break;
				
					  default:config = BulletConfigSyncingUtil.TEST_CONFIG; break;
					}
					world.spawnEntityInWorld(new EntityBulletBase(world, config, player));
					
					
						
					for(int i = 0; i < 25; i++) {
						EntitySSmokeFX flame = new EntitySSmokeFX(world);
						
						flame.motionX = player.getLookVec().xCoord;
						flame.motionY = player.getLookVec().yCoord;
						flame.motionZ = player.getLookVec().zCoord;
						
						flame.posX = player.posX + flame.motionX + world.rand.nextGaussian() * 0.35;
						flame.posY = player.posY + flame.motionY + world.rand.nextGaussian() * 0.35 + player.eyeHeight;
						flame.posZ = player.posZ + flame.motionZ + world.rand.nextGaussian() * 0.35;
						
						world.spawnEntityInWorld(flame);
					}
				}
			 
			}
		}
		
		return stack;
	}

	@Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isCurrentItem) {
		
		if(getState(stack) == 3) {
			
			if(isCurrentItem) {
				int timer = getTimer(stack);
				
				if(timer > 0) {
					timer--;
	
					if(timer % 20 == 0 && timer != 0)
						world.playSoundAtEntity(entity, "hbm:weapon.follyBuzzer", 1.0F, 1.0F);
					
					if(timer == 0)
						world.playSoundAtEntity(entity, "hbm:weapon.follyAquired", 1.0F, 1.0F);
					
					setTimer(stack, timer);
				}
			} else {
				setTimer(stack, 100);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean bool) {
		
		if(MainRegistry.polaroidID == 3) {
			
			list.add(EnumChatFormatting.BLUE + "  Besides the name it is completly Unrelated to" + EnumChatFormatting.OBFUSCATED + "The Digamma Particle");
			
		} else if (MainRegistry.polaroidID == 11) {
			
			list.add(EnumChatFormatting.RED + "    AHAHAH OOO THEY'RE GONNA HAVE TO GLUE YOU BACK TOGETHER");
			list.add(EnumChatFormatting.RED + ""+ EnumChatFormatting.BOLD + "    IN HELL");
			
		} else {
	
		
		list.add(EnumChatFormatting.YELLOW + "" + EnumChatFormatting.ITALIC + "  A Handheld 128mm Cannon made deep inside the IF-RD skunkworks");
		list.add(EnumChatFormatting.YELLOW + "" + EnumChatFormatting.ITALIC + "  However, it never made out of the drawing board.");
		list.add(EnumChatFormatting.YELLOW + "" + EnumChatFormatting.ITALIC + "  The blueprint was recovered at a later date by an unknown Ironshod Firearms Employee");
		
		
		
		}
		list.add("Ammo: Silver Bullets");
	}
	public EnumRarity getRarity(ItemStack p_77613_1_)
	{ 
		return EnumRarity.epic;
	}

	//0: closed, empty,
	//1: open, empty
	//2: open, full
	//3: closed, full
	public static void setState(ItemStack stack, int i) {
		writeNBT(stack, "state", i);
	}
	
	public static int getState(ItemStack stack) {
		return readNBT(stack, "state");
	}
	
	public static void setType(ItemStack stack, int i) {
		writeNBT(stack, "type", i);
	}
	
	public static int getType(ItemStack stack) {
		return readNBT(stack, "type");
	}
	
	public static void setTimer(ItemStack stack, int i) {
		writeNBT(stack, "timer", i);
	}
	
	public static int getTimer(ItemStack stack) {
		return readNBT(stack, "timer");
	}
	
	private static void writeNBT(ItemStack stack, String key, int value) {
		
		if(!stack.hasTagCompound())
			stack.stackTagCompound = new NBTTagCompound();
		
		stack.stackTagCompound.setInteger(key, value);
	}
	
	private static int readNBT(ItemStack stack, String key) {
		
		if(!stack.hasTagCompound())
			return 0;
		
		return stack.stackTagCompound.getInteger(key);
	}

}
