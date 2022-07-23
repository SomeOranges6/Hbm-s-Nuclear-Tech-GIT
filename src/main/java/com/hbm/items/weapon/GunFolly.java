package com.hbm.items.weapon;

import java.util.List;

import com.hbm.entity.effect.EntityCloudFleijaRainbow;
import com.hbm.entity.logic.EntityNukeExplosionMK3;
import com.hbm.entity.particle.EntitySSmokeFX;
import com.hbm.entity.projectile.EntityBulletBase;
import com.hbm.handler.BulletConfigSyncingUtil;
import com.hbm.handler.BulletConfiguration;
import com.hbm.handler.GunConfiguration;
import com.hbm.handler.guncfg.GunCannonFactory;
import com.hbm.interfaces.IHoldableWeapon;
import com.hbm.inventory.RecipesCommon.ComparableStack;
import com.hbm.items.ModItems;
import com.hbm.main.MainRegistry;
import com.hbm.potion.HbmPotion;
import com.hbm.render.util.RenderScreenOverlay.Crosshair;
import com.hbm.util.InventoryUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class GunFolly extends Item implements IHoldableWeapon {
	
	GunConfiguration folly = GunCannonFactory.getFollyConfig();
	
	public GunFolly()
    {
        this.maxStackSize = 1;
    }
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
		final BulletConfiguration cfg = BulletConfigSyncingUtil.pullConfig(folly.config.get(getType(stack)));
		final ComparableStack ammo = cfg.ammo.copy();
		switch (state) {
		
		case 0:
		
			world.playSoundAtEntity(player, "hbm:weapon.follyOpen", 1.0F, 1.0F);
			setState(stack, 1);
			break;
			
		case 1:
			
			for(Integer config : folly.config) {
				
				BulletConfiguration cfg1 = BulletConfigSyncingUtil.pullConfig(config);
				
				if(InventoryUtil.doesPlayerHaveAStack(player, cfg1.ammo, true)) {
					setType(stack, folly.config.indexOf(config));
					world.playSoundAtEntity(player, "hbm:weapon.follyReload", 1.0F, 1.0F);
					setState(stack, 2);
					
				} else if(player.inventory.hasItem(ModItems.folly_shell)) {
					
					world.playSoundAtEntity(player, "hbm:weapon.follyReload", 1.0F, 1.0F);
					player.inventory.consumeInventoryItem(ModItems.folly_shell);
					
					setState(stack, 2);
					setType(stack, 7);	
					
			    } else {
					
					world.playSoundAtEntity(player, "hbm:weapon.follyClose", 1.0F, 1.0F);
					setState(stack, 0);
					setType(stack,0);
					
				}
				
              /*}else if(player.inventory.hasItem(ModItems.coin_maskman)) {
                 	
                 	if(verified == false) {
                 	player.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "[EV-1101] Access granted, Fire when ready."));
                 	stack.stackTagCompound.setBoolean("verified", true);
                 	setState(stack, 1);
                 	}
            	} else {
                	player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "[EV-1101] Access Denied, Verify using a IF-R&D M.A.S.K token"));
            	}
            
             */ 
             
			
			}
			break;
			
		case 2:

			world.playSoundAtEntity(player, "hbm:weapon.follyClose", 1.0F, 1.0F);
			setState(stack, 3);
			switch (bulletType) {
			
			case 7:
			setTimer(stack, 25);
			break;
			
			default:setTimer(stack, cfg.firerate);
			};
		case 3:
			
			if(getTimer(stack) == 0) {
				
				setState(stack, 0);
				world.playSoundAtEntity(player, "hbm:weapon.follyFire", 1.0F, 1.0F);
				
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

				if (!world.isRemote) {
					final int config;
					switch (bulletType)
					{
					  
					  case 0: config = BulletConfigSyncingUtil.SHELL_FOLLY;
					  
					  world.spawnEntityInWorld(new EntityBulletBase(world, BulletConfigSyncingUtil.SHELL_FOLLY_EFFECT, player));
					  
					  player.addPotionEffect(new PotionEffect(HbmPotion.taint.id, 30 * 20, 0));  
					  player.addPotionEffect(new PotionEffect(HbmPotion.radiation.id, 20 * 20, 0));  
					  break;
				
					  default:config = getType(stack); break;
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
			
			list.add(EnumChatFormatting.BLUE + "  Besides the name it is completly Unrelated to" + EnumChatFormatting.OBFUSCATED + " The Digamma Particle");
			
		} else if (MainRegistry.polaroidID == 11) {
			
			list.add(EnumChatFormatting.YELLOW + "" + EnumChatFormatting.ITALIC + "  A Handheld 128cm cannon, made to destroy Project Horizons using a Starmetal-Moonstone reaction");
			list.add(EnumChatFormatting.YELLOW + "" + EnumChatFormatting.ITALIC + "  Does it work? ");
			list.add(EnumChatFormatting.RED + "" + EnumChatFormatting.ITALIC + "  Nope, found that out the hard way");
		} else {
	
		
		list.add(EnumChatFormatting.YELLOW + "" + EnumChatFormatting.ITALIC + "  A Handheld 128cm cannon, made to destroy Project Horizons using a Starmetal-Moonstone reaction");
		list.add(EnumChatFormatting.YELLOW + "" + EnumChatFormatting.ITALIC + "  Does it work?");
		list.add(EnumChatFormatting.YELLOW + "" + EnumChatFormatting.ITALIC + "  Well, thats your job to find out.");
		
		
		}
		list.add("Name: Project Starfall");
		list.add("Manufacturer: Horizons Labs");
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
