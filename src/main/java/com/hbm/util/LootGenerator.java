package com.hbm.util;

import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.generic.BlockLoot.TileEntityLoot;
import com.hbm.items.ModItems;
import com.hbm.items.special.ItemBookLore;
import com.hbm.items.ItemAmmoEnums.AmmoFatman;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LootGenerator {

	public static void setBlock(World world, int x, int y, int z) {
		world.setBlock(x, y, z, ModBlocks.deco_loot);
	}
	
	public static void addItemWithDeviation(TileEntityLoot loot, Random rand, ItemStack stack, double  x, double y, double z) {
		loot.addItem(stack, x + rand.nextGaussian() * 0.02, y, z + rand.nextGaussian() * 0.02);
	}
	
	public static void lootBooklet(World world, int x, int y, int z) {
		
		TileEntityLoot loot = (TileEntityLoot) world.getTileEntity(x, y, z);
		
		if(loot != null && loot.items.isEmpty()) {
			loot.addItem(ItemBookLore.createBook("beacon", 12, 0x404040, 0xD637B3), 0, 0, 0);;
		}
	}
	
	public static void lootCapNuke(World world, int x, int y, int z) {
		
		TileEntityLoot loot = (TileEntityLoot) world.getTileEntity(x, y, z);
		
		if(loot != null && loot.items.isEmpty()) {
			
			if(world.rand.nextInt(5) == 0)
				loot.addItem(ModItems.ammo_nuke.stackFromEnum(AmmoFatman.LOW), -0.25, 0, -0.125);
			else
				loot.addItem(new ItemStack(ModItems.ammo_rocket), -0.25, 0, -0.25);

			for(int i = 0; i < 4; i++)
				addItemWithDeviation(loot, world.rand, new ItemStack(ModItems.cap_nuka, 2), 0.125, i * 0.03125, 0.25);
			for(int i = 0; i < 2; i++)
				addItemWithDeviation(loot, world.rand, new ItemStack(ModItems.syringe_metal_stimpak, 1), -0.25, i * 0.03125, 0.25);
			for(int i = 0; i < 6; i++)
				addItemWithDeviation(loot, world.rand, new ItemStack(ModItems.cap_nuka, 2), 0.125, i * 0.03125, -0.25);
		}
	}
	
	public static void lootMedicine(World world, int x, int y, int z) {
		
		TileEntityLoot loot = (TileEntityLoot) world.getTileEntity(x, y, z);
		
		if(loot != null && loot.items.isEmpty()) {

			for(int i = 0; i < 4; i++) {
				int type = world.rand.nextInt(4);
				Item syringe = type < 2 ? ModItems.syringe_metal_stimpak : type == 2 ? ModItems.syringe_metal_medx : ModItems.syringe_metal_psycho;
				addItemWithDeviation(loot, world.rand, new ItemStack(syringe), 0.125, i * 0.03125, 0.25);
			}
			
			int type = world.rand.nextInt(8);
			Item syringe = type < 2 ? ModItems.radaway : type < 4 ? ModItems.radx : type < 7 ? ModItems.iv_blood : ModItems.siox;
			addItemWithDeviation(loot, world.rand, new ItemStack(syringe), -0.25, 0, -0.125);
		}
	}
	
	public static void lootCapStash(World world, int x, int y, int z) {
		
		TileEntityLoot loot = (TileEntityLoot) world.getTileEntity(x, y, z);
		
		if(loot != null && loot.items.isEmpty()) {
			
			for(int i = -1; i <= 1; i++) {
				for(int j = -1; j <= 1; j++) {
					
					int r = world.rand.nextInt(3);
					Item cap = r < 2 ? ModItems.cap_nuka : ModItems.cap_sunset;
					
					int count = world.rand.nextInt(5) + 3;
					for(int k = 0; k < count; k++) {
						
						if(cap == ModItems.cap_sunset && world.rand.nextInt(10) == 0)
							addItemWithDeviation(loot, world.rand, new ItemStack(ModItems.cap_star, 1), i * 0.3125, k * 0.03125, j * 0.3125);
						else
							addItemWithDeviation(loot, world.rand, new ItemStack(cap, 4), i * 0.3125, k * 0.03125, j * 0.3125);
					}
				}
			}
		}
	}
	public static void lootScrapMetal(World world, int x, int y, int z) {

		TileEntityLoot loot = (TileEntityLoot) world.getTileEntity(x, y, z);
		Item scrap;

		if(loot != null && loot.items.isEmpty()) {
			int scrapType = world.rand.nextInt(3);
			int type = world.rand.nextInt(4);
            switch(scrapType) {
				case 0:
					for (int i = 0; i < 8; i++) {
						scrap = type < 2 ? ModItems.plate_steel : type == 2 ? ModItems.plate_copper : ModItems.plate_aluminium;
						loot.addItem(new ItemStack(scrap), world.rand.nextGaussian() * 0.06, i * 0.01125, world.rand.nextGaussian() * 0.06);
					}
				break;

				case 1:
					for (int i = 0; i < 16; i++) {

						scrap = type < 2 ? ModItems.bolt_tungsten : type == 2 ? ModItems.wire_tungsten : ModItems.bolt_dura_steel;
						loot.addItem(new ItemStack(scrap), world.rand.nextGaussian() * 0.06, i * 0.01125,  world.rand.nextGaussian() * 0.06);
					}
				break;

				default:
					int type2 = world.rand.nextInt(8);
					scrap = type < 2 ? ModItems.hull_big_steel : type2 < 4 ? ModItems.hull_big_titanium : type2 < 7 ? ModItems.pipes_steel : ModItems.tank_steel;
					loot.addItem(new ItemStack(scrap), world.rand.nextGaussian() * 0.06, 0, world.rand.nextGaussian() * 0.06);
			}
		}
	}
	public static void lootMakeshiftGun(World world, int x, int y, int z) {

		TileEntityLoot loot = (TileEntityLoot) world.getTileEntity(x, y, z);
		
		if(loot != null && loot.items.isEmpty()) {
			
			if(world.rand.nextInt(2) == 0)
				addItemWithDeviation(loot, world.rand, new ItemStack(ModItems.gun_lever_action), 0, 0, 0.125);
			
			int count = world.rand.nextInt(2) + 1;
			for(int i = 0; i < count; i++) {
				int type = world.rand.nextInt(2);
				Item parts = type < 1 ? ModItems.plate_steel : ModItems.mechanism_rifle_1;
				addItemWithDeviation(loot, world.rand, new ItemStack(parts), -0.3125, i * 0.03125, 0.3125);
			}
			
			count = world.rand.nextInt(2) + 2;
			for(int i = 0; i < count; i++)
				addItemWithDeviation(loot, world.rand, new ItemStack(ModItems.wire_aluminium), 0.3125, i * 0.03125, -0.125);
			
			int type = world.rand.nextInt(4);
			Item tool = type > 2 ? ModItems.wrench : ModItems.screwdriver;
			addItemWithDeviation(loot, world.rand, new ItemStack(tool), 0.005, 0, -0.3125);
		}
	}
	
	public static void lootNukeStorage(World world, int x, int y, int z) {
		
		TileEntityLoot loot = (TileEntityLoot) world.getTileEntity(x, y, z);
		
		if(loot != null && loot.items.isEmpty()) {
			
			boolean memes = world.rand.nextInt(10) == 0;
			
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 4; j++) {
					
					if(world.rand.nextBoolean() || memes) {
						int type = world.rand.nextInt(11);
						AmmoFatman nuke = memes ? AmmoFatman.PUMPKIN : type == 0 ? AmmoFatman.STOCK : type <= 5 ? AmmoFatman.LOW : AmmoFatman.SAFE;
						loot.addItem(ModItems.ammo_nuke.stackFromEnum(nuke), -0.375 + i * 0.25, 0, -0.375 + j * 0.25);
					}
				}
			}
		}
	}
	
	public static void lootBones(World world, int x, int y, int z) {
		
		TileEntityLoot loot = (TileEntityLoot) world.getTileEntity(x, y, z);
		
		if(loot != null && loot.items.isEmpty()) {
			
			int limit = world.rand.nextInt(3) + 3;
			for(int i = 0; i < limit; i++) {
				addItemWithDeviation(loot, world.rand, new ItemStack(Items.bone), world.rand.nextDouble() - 0.5, i * 0.03125, world.rand.nextDouble() - 0.5);
			}
		}
	}
	
	public static void lootGlyphidHive(World world, int x, int y, int z) {
		
		TileEntityLoot loot = (TileEntityLoot) world.getTileEntity(x, y, z);
		
		if(loot != null && loot.items.isEmpty()) {
			
			int limit = world.rand.nextInt(3) + 3;
			for(int i = 0; i < limit; i++) {
				
				ItemStack stack = new ItemStack(ModItems.ammo_12gauge, 4);
				
				switch(world.rand.nextInt(11)) {
				case 0: stack = new ItemStack(ModItems.steel_plate); break;
				case 1: stack = new ItemStack(ModItems.gun_lever_action); break;
				case 2: stack = new ItemStack(ModItems.grenade_if_generic); break;
				case 3:
				case 4: stack = new ItemStack(ModItems.bottle_nuka, 1 + world.rand.nextInt(2)); break;
				case 5:
				case 6: stack = new ItemStack(ModItems.ingot_steel, 3 + world.rand.nextInt(10)); break;
				case 7: stack = new ItemStack(ModItems.steel_pickaxe); break;
				case 8: stack = new ItemStack(ModItems.gas_mask_m65); break;
				case 9: stack = new ItemStack(ModItems.ammo_20gauge, 8); break;
				}
				
				addItemWithDeviation(loot, world.rand, stack, world.rand.nextDouble() - 0.5, i * 0.03125, world.rand.nextDouble() - 0.5);
			}
		}
	}
}
