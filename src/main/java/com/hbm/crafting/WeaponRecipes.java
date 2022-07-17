package com.hbm.crafting;

import com.hbm.blocks.ModBlocks;
import com.hbm.inventory.OreDictManager;
import com.hbm.inventory.fluid.Fluids;

import static com.hbm.inventory.OreDictManager.*;

import com.hbm.items.ItemAmmoEnums.*;
import com.hbm.items.ModItems;
import com.hbm.items.weapon.GunB92Cell;
import com.hbm.main.CraftingManager;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * For guns, ammo and the like
 * @author hbm
 */
public class WeaponRecipes {
	
	public static void register() {
		
		//Missiles
		CraftingManager.addShapelessAuto(new ItemStack(ModItems.missile_taint, 1), new Object[] { ModItems.missile_assembly, ModItems.bucket_mud, ModItems.powder_spark_mix, ModItems.powder_magic });
		CraftingManager.addShapelessAuto(new ItemStack(ModItems.missile_micro, 1), new Object[] { ModItems.missile_assembly, ModItems.ducttape, ModItems.ammo_nuke.stackFromEnum(AmmoFatman.HIGH)});
		CraftingManager.addShapelessAuto(new ItemStack(ModItems.missile_bhole, 1), new Object[] { ModItems.missile_assembly, ModItems.ducttape, ModItems.grenade.stackFromEnum(AmmoHandGrenade.BLACK_HOLE)});
		CraftingManager.addShapelessAuto(new ItemStack(ModItems.missile_schrabidium, 1), new Object[] { ModItems.missile_assembly, ModItems.ducttape, ModItems.grenade.stackFromEnum(AmmoHandGrenade.ASCHRAB) });
		CraftingManager.addShapelessAuto(new ItemStack(ModItems.missile_schrabidium, 1), new Object[] { ModItems.missile_assembly, ModItems.ducttape, ModItems.cell_sas3, ModItems.circuit_targeting_tier4 });
		CraftingManager.addShapelessAuto(new ItemStack(ModItems.missile_emp, 1), new Object[] { ModItems.missile_assembly, ModItems.ducttape, ModBlocks.emp_bomb, ModItems.circuit_targeting_tier3 });
		CraftingManager.addShapelessAuto(new ItemStack(ModItems.missile_anti_ballistic, 1), new Object[] { ModItems.missile_generic, ModItems.circuit_targeting_tier3 });
		
		//Missile fins
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.mp_stability_10_flat, 1), new Object[] { "PSP", "P P", 'P', STEEL.plate(), 'S', ModBlocks.steel_scaffold });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.mp_stability_10_cruise, 1), new Object[] { "ASA", " S ", "PSP", 'A', TI.plate(), 'P', STEEL.plate(), 'S', ModBlocks.steel_scaffold });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.mp_stability_10_space, 1), new Object[] { "ASA", "PSP", 'A', AL.plate(), 'P', STEEL.ingot(), 'S', ModBlocks.steel_scaffold });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.mp_stability_15_flat, 1), new Object[] { "ASA", "PSP", 'A', AL.plate(), 'P', STEEL.plate(), 'S', ModBlocks.steel_scaffold });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.mp_stability_15_thin, 1), new Object[] { "A A", "PSP", "PSP", 'A', AL.plate(), 'P', STEEL.plate(), 'S', ModBlocks.steel_scaffold });

		//Missile thrusters
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.mp_thruster_15_balefire_large_rad, 1), new Object[] { "CCC", "CTC", "CCC", 'C', ModItems.board_copper, 'T', ModItems.mp_thruster_15_balefire_large });
		
		//Missile fuselages
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.mp_fuselage_10_kerosene_insulation, 1), new Object[] { "CCC", "CTC", "CCC", 'C', ModItems.plate_polymer, 'T', ModItems.mp_fuselage_10_kerosene });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.mp_fuselage_10_long_kerosene_insulation, 1), new Object[] { "CCC", "CTC", "CCC", 'C', ModItems.plate_polymer, 'T', ModItems.mp_fuselage_10_long_kerosene });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.mp_fuselage_15_kerosene_insulation, 1), new Object[] { "CCC", "CTC", "CCC", 'C', ModItems.plate_polymer, 'T', ModItems.mp_fuselage_15_kerosene });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.mp_fuselage_10_solid_insulation, 1), new Object[] { "CCC", "CTC", "CCC", 'C', ModItems.plate_polymer, 'T', ModItems.mp_fuselage_10_solid });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.mp_fuselage_10_long_solid_insulation, 1), new Object[] { "CCC", "CTC", "CCC", 'C', ModItems.plate_polymer, 'T', ModItems.mp_fuselage_10_long_solid });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.mp_fuselage_15_solid_insulation, 1), new Object[] { "CCC", "CTC", "CCC", 'C', ModItems.plate_polymer, 'T', ModItems.mp_fuselage_15_solid });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.mp_fuselage_15_solid_desh, 1), new Object[] { "CCC", "CTC", "CCC", 'C', DESH.ingot(), 'T', ModItems.mp_fuselage_15_solid });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.mp_fuselage_10_kerosene_metal, 1), new Object[] { "ICI", "CTC", "ICI", 'C', STEEL.plate(), 'I', FE.plate(), 'T', ModItems.mp_fuselage_10_kerosene });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.mp_fuselage_10_long_kerosene_metal, 1), new Object[] { "ICI", "CTC", "ICI", 'C', STEEL.plate(), 'I', FE.plate(), 'T', ModItems.mp_fuselage_10_long_kerosene });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.mp_fuselage_15_kerosene_metal, 1), new Object[] { "ICI", "CTC", "ICI", 'C', STEEL.plate(), 'I', FE.plate(), 'T', ModItems.mp_fuselage_15_kerosene });
		
		//Missile warheads
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.mp_warhead_15_boxcar, 1), new Object[] { "SNS", "CBC", "SFS", 'S', STAR.ingot(), 'N', ModBlocks.det_nuke, 'C', ModItems.circuit_targeting_tier4, 'B', ModBlocks.boxcar, 'F', ModItems.tritium_deuterium_cake });
		CraftingManager.addShapelessAuto(new ItemStack(ModItems.mp_warhead_15_schrab_aus, 1), new Object[] { ModItems.mp_warhead_15_schrab, AUSTRALIUM.dust(), AUSTRALIUM.dust(), AUSTRALIUM.dust(),});
		
		//Missile chips
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.mp_chip_1, 1), new Object[] { "P", "C", "S", 'P', ModItems.plate_polymer, 'C', ModItems.circuit_targeting_tier1, 'S', ModBlocks.steel_scaffold });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.mp_chip_2, 1), new Object[] { "P", "C", "S", 'P', ModItems.plate_polymer, 'C', ModItems.circuit_targeting_tier2, 'S', ModBlocks.steel_scaffold });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.mp_chip_3, 1), new Object[] { "P", "C", "S", 'P', ModItems.plate_polymer, 'C', ModItems.circuit_targeting_tier3, 'S', ModBlocks.steel_scaffold });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.mp_chip_4, 1), new Object[] { "P", "C", "S", 'P', ModItems.plate_polymer, 'C', ModItems.circuit_targeting_tier4, 'S', ModBlocks.steel_scaffold });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.mp_chip_5, 1), new Object[] { "P", "C", "S", 'P', ModItems.plate_polymer, 'C', ModItems.circuit_targeting_tier5, 'S', ModBlocks.steel_scaffold });
		
		//Guns
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_rpg, 1), new Object[] { "SSW", " MW", 'S', ModItems.hull_small_steel, 'W', FE.plate(), 'M', ModItems.mechanism_launcher_1 });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_panzerschreck, 1), new Object[] { "SSS", " MW", 'S', ModItems.hull_small_steel, 'W', CU.plate(), 'M', ModItems.mechanism_launcher_1 });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_karl, 1), new Object[] { "SSW", " MW", 'S', ModItems.hull_small_steel, 'W', ALLOY.plate(), 'M', ModItems.mechanism_launcher_2 });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_quadro, 1), new Object[] { "SSS", "SSS", "CM ", 'S', ModItems.hull_small_steel, 'C', ModItems.circuit_targeting_tier3, 'M', ModItems.mechanism_launcher_2 });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_hk69, 1), new Object[] { "SSI", " MB", 'S', ModItems.hull_small_steel, 'I', FE.ingot(), 'M', ModItems.mechanism_launcher_1, 'B', ModItems.bolt_tungsten });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_stinger, 1), new Object[] { "SSW", "CMW", 'S', STEEL.plate(), 'W', TI.plate(), 'C', ModItems.circuit_red_copper, 'M', ModItems.mechanism_launcher_2 });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.ammo_stinger_rocket, 4), new Object[] { "SS ", "STI", " IR", 'S', STEEL.plate(), 'T', Item.getItemFromBlock(Blocks.tnt), 'I', AL.plate(), 'R', REDSTONE.dust() });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_revolver, 1), new Object[] { "SSM", " RW", 'S', STEEL.plate(), 'W', KEY_PLANKS, 'R', ModItems.wire_aluminium, 'M', ModItems.mechanism_revolver_1 });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_revolver_saturnite, 1), new Object[] { "SSM", " RW", 'S', BIGMT.plate(), 'W', KEY_PLANKS, 'R', ModItems.wire_tungsten, 'M', ModItems.mechanism_revolver_2 });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_revolver_iron, 1), new Object[] { "SSM", " RW", 'S', FE.plate(), 'W', KEY_PLANKS, 'R', ModItems.wire_aluminium, 'M', ModItems.mechanism_revolver_1 });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_revolver_gold, 1), new Object[] { "SSM", " RW", 'S', AU.plate(), 'W', W.ingot(), 'R', ModItems.wire_gold, 'M', ModItems.mechanism_revolver_1 });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_revolver_lead, 1), new Object[] { "SSM", " RW", 'S', PB.plate(), 'W', W.ingot(), 'R', ModItems.wire_tungsten, 'M', ModItems.mechanism_revolver_2 });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_revolver_schrabidium, 1), new Object[] { "SSM", " RW", 'S', SA326.block(), 'W', W.ingot(), 'R', ModItems.wire_schrabidium, 'M', ModItems.mechanism_special });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_revolver_pip, 1), new Object[] { " G ", "SSP", " TI", 'G', KEY_ANYPANE, 'S', STEEL.plate(), 'P', ModItems.mechanism_revolver_2, 'T', ModItems.wire_tungsten, 'I', ANY_PLASTIC.ingot() });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_revolver_nopip, 1), new Object[] { "SSP", " TI", 'S', STEEL.plate(), 'P', ModItems.mechanism_revolver_2, 'T', ModItems.wire_tungsten, 'I', ANY_PLASTIC.ingot() });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_revolver_blackjack, 1), new Object[] { "SSP", " TI", 'S', STEEL.plate(), 'P', ModItems.mechanism_revolver_2, 'T', ModItems.wire_tungsten, 'I', KEY_PLANKS });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_revolver_silver, 1), new Object[] { "SSP", " TI", 'S', AL.plate(), 'P', ModItems.mechanism_revolver_2, 'T', ModItems.wire_tungsten, 'I', KEY_PLANKS });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_revolver_red, 1), new Object[] { "R ", " B", 'R', ModItems.key_red, 'B', ModItems.gun_revolver_blackjack });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_deagle, 1), new Object[] { "PPM", " BI", 'P', STEEL.plate(), 'B', ModItems.bolt_tungsten, 'I', ANY_PLASTIC.ingot(), 'M', ModItems.mechanism_rifle_1 });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_revolver_cursed, 1), new Object[] { "TTM", "SRI", 'S', STEEL.plate(), 'I', STEEL.ingot(), 'R', ModItems.wire_red_copper, 'T', TI.plate(), 'M', ModItems.mechanism_revolver_2 });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_revolver_nightmare, 1), new Object[] { "SEM", " RW", 'S', STEEL.plate(), 'W', KEY_PLANKS, 'R', ModItems.wire_aluminium, 'E', ModItems.powder_power, 'M', ModItems.mechanism_revolver_2 });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_revolver_nightmare2, 1), new Object[] { "SSM", "RRW", 'S', OreDictManager.getReflector(), 'W', W.ingot(), 'R', ModItems.wire_gold, 'M', ModItems.mechanism_special });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_bio_revolver, 1), new Object[] { "SSM", "BTW", 'S', STEEL.plate(), 'M', ModItems.mechanism_revolver_2, 'B', B.ingot(), 'T', ModItems.bolt_tungsten, 'W', KEY_LOG });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_fatman, 1), new Object[] { "SSI", "IIM", "WPH", 'S', STEEL.plate(), 'I', STEEL.ingot(), 'W', ModItems.wire_aluminium, 'H', ModItems.hull_small_steel, 'P', Item.getItemFromBlock(Blocks.piston), 'M', ModItems.mechanism_launcher_2 });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_mirv, 1), new Object[] { "LLL", "WFW", "SSS", 'S', STEEL.plate(), 'L', PB.plate(), 'W', ModItems.wire_gold, 'F', ModItems.gun_fatman });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_proto, 1), new Object[] { "LLL", "WFW", "SSS", 'S', ModItems.plate_polymer, 'L', ModItems.plate_desh, 'W', ModItems.wire_tungsten, 'F', ModItems.gun_fatman });
		CraftingManager.addRecipeAuto(ModItems.ammo_nuke.stackFromEnum(AmmoFatman.BALEFIRE), new Object[] { " S ", "EBE", " S ", 'S', ModItems.hull_small_steel, 'E', ModItems.powder_power, 'B', ModItems.egg_balefire_shard });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_mp40, 1), new Object[] { "IIM", " SW", " S ", 'S', STEEL.plate(), 'I', STEEL.ingot(), 'W', KEY_PLANKS, 'M', ModItems.mechanism_rifle_2 });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_thompson, 1), new Object[] { "IIM", " SW", " S ", 'S', FE.plate(), 'I', STEEL.plate(), 'W', KEY_PLANKS, 'M', ModItems.mechanism_rifle_2 });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_flechette, 1), new Object[] { "PPM", "TIS", "G  ", 'P', STEEL.plate(), 'M', ModItems.mechanism_rifle_2, 'T', ModItems.hull_small_steel, 'I', STEEL.ingot(), 'S', POLYMER.ingot(), 'G', ModItems.mechanism_launcher_1 });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_uboinik, 1), new Object[] { "IIM", "SPW", 'P', STEEL.plate(), 'I', STEEL.ingot(), 'W', KEY_PLANKS, 'S', KEY_STICK, 'M', ModItems.mechanism_revolver_2 });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_supershotgun, 1), new Object[] { "BBM", "BBM", "AWL", 'B', ModItems.hull_small_steel, 'M', ModItems.mechanism_rifle_2, 'A', ModItems.plate_dalekanium, 'W', ModItems.wire_gold, 'L', KEY_LOG });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_ks23, 1), new Object[] { "PPM", "SWL", 'P', STEEL.plate(), 'M', ModItems.mechanism_rifle_1, 'S', KEY_STICK, 'W', ModItems.wire_tungsten, 'L', KEY_LOG });
		CraftingManager.addShapelessAuto(new ItemStack(ModItems.gun_sauer, 1), new Object[] { ModItems.ducttape, ModItems.gun_ks23, Blocks.lever, ModItems.gun_ks23 });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_xvl1456, 1), new Object[] { "PBB", "ACC", "PRY", 'P', STEEL.plate(), 'R', ModItems.redcoil_capacitor, 'A', ModItems.coil_advanced_alloy, 'B', ModItems.battery_generic, 'C', ModItems.coil_advanced_torus, 'Y', ModItems.mechanism_special });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_xvl1456_ammo, 16), new Object[] { " S ", "SRS", " S ", 'S', STEEL.plate(), 'R', ModItems.waste_natural_uranium });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_xvl1456_ammo, 16), new Object[] { " S ", "SRS", " S ", 'S', STEEL.plate(), 'R', ModItems.waste_uranium });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_xvl1456_ammo, 20), new Object[] { " S ", " R ", " S ", 'S', STEEL.plate(), 'R', ModItems.waste_plate_u235 });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_xvl1456_ammo, 16), new Object[] { "SRS", 'S', STEEL.plate(), 'R', ModItems.waste_u235 });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_xvl1456_ammo, 16), new Object[] { " S ", " R ", " S ", 'S', STEEL.plate(), 'R', U238.ingot() });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_xvl1456_ammo, 16), new Object[] { "SRS", 'S', STEEL.plate(), 'R', U238.ingot() });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_osipr, 1), new Object[] { "CCT", "WWI", "MCC", 'C', CMB.plate(), 'T', W.ingot(), 'W', ModItems.wire_magnetized_tungsten, 'I', ModItems.mechanism_rifle_2, 'M', ModItems.coil_magnetized_tungsten });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_immolator, 1), new Object[] { "WCC", "PMT", "WAA", 'W', ModItems.wire_gold, 'C', CU.plate(), 'P', ALLOY.plate(), 'M', ModItems.mechanism_launcher_1, 'T', ModItems.tank_steel, 'A', STEEL.plate() });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_immolator_ammo, 16), new Object[] { "SPS", "PCP", "SPS", 'S', STEEL.plate(), 'C', COAL.dust(), 'P', P_RED.dust() });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_immolator_ammo, 16), new Object[] { " F ", "SFS", " F ", 'S', STEEL.plate(), 'F', Fluids.DIESEL.getDict(1000) });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_immolator_ammo, 24), new Object[] { " F ", "SFS", " F ", 'S', STEEL.plate(), 'F', ModItems.canister_napalm });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_flamer, 1), new Object[] { "WPP", "SCT", "WMI", 'W', ModItems.wire_gold, 'P', ModItems.pipes_steel, 'S', ModItems.hull_small_steel, 'C', ModItems.coil_tungsten, 'T', ModItems.tank_steel, 'M', ModItems.mechanism_launcher_1, 'I', STEEL.ingot() });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_cryolator, 1), new Object[] { "SSS", "IWL", "LMI", 'S', STEEL.plate(), 'I', FE.plate(), 'L', Items.leather, 'M', ModItems.mechanism_launcher_1, 'W', ModItems.wire_aluminium });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_cryolator_ammo, 16), new Object[] { "SPS", "PCP", "SPS", 'S', STEEL.plate(), 'C', KNO.dust(), 'P', Items.snowball });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_cryolator_ammo, 16), new Object[] { " F ", "SFS", " F ", 'S', STEEL.plate(), 'F', ModItems.powder_ice });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_mp, 1), new Object[] { "EEE", "SSM", "III", 'E', EUPH.ingot(), 'S', STEEL.plate(), 'I', STEEL.ingot(), 'M', ModItems.mechanism_rifle_2 });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_emp, 1), new Object[] { "CPG", "CMF", "CPI", 'C', ModItems.coil_copper, 'P', PB.plate(), 'G', ModItems.circuit_gold, 'M', ModItems.magnetron, 'I', W.ingot(), 'F', ModItems.mechanism_special });

		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_emp_ammo, 8), new Object[] { "IGI", "IPI", "IPI", 'G', AU.plate(), 'I', FE.plate(), 'P', ModItems.powder_power });
		CraftingManager.addShapelessAuto(new ItemStack(ModItems.ammo_4gauge_quad, 1), new Object[] { ModItems.ammo_4gauge, ModItems.ammo_4gauge, ModItems.ammo_4gauge, ModItems.ammo_4gauge });

		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_emp_ammo, 8), new Object[] { "IGI", "IPI", "IPI", 'G', AU.plate(), 'I', FE.plate(), 'P', ModItems.powder_power });
		//CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_jack, 1), new Object[] { "WW ", "TSD", " TT", 'W', "ingotWeidanium", 'T', ModItems.toothpicks, 'S', ModItems.gun_uboinik, 'D', ModItems.ducttape });
		
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_euthanasia, 1), new Object[] { "TDT", "AAS", " T ", 'A', AUSTRALIUM.ingot(), 'T', ModItems.toothpicks, 'S', ModItems.gun_mp40, 'D', ModItems.ducttape });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_euthanasia_ammo, 12), new Object[] { "P", "S", "N", 'P', ModItems.powder_poison, 'N', KNO.dust(), 'S', ModItems.syringe_metal_empty });
		//CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_spark, 1), new Object[] { "TTD", "AAS", "  T", 'A', "ingotDaffergon", 'T', ModItems.toothpicks, 'S', ModItems.gun_rpg, 'D', ModItems.ducttape });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_spark_ammo, 4), new Object[] { "PCP", "DDD", "PCP", 'P', PB.plate(), 'C', ModItems.coil_gold, 'D', ModItems.powder_power });
		//CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_skystinger, 1), new Object[] { "TTT", "AAS", " D ", 'A', "ingotUnobtainium", 'T', ModItems.toothpicks, 'S', ModItems.gun_stinger, 'D', ModItems.ducttape });
		//CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_hp, 1), new Object[] { "TDT", "ASA", " T ", 'A', "ingotReiium", 'T', ModItems.toothpicks, 'S', ModItems.gun_xvl1456, 'D', ModItems.ducttape });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_hp_ammo, 8), new Object[] { " R ", "BSK", " Y ", 'S', STEEL.plate(), 'K', KEY_BLACK, 'R', KEY_RED, 'B', KEY_BLUE, 'Y', KEY_YELLOW });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_defabricator_ammo, 16), new Object[] { "PCP", "DDD", "PCP", 'P', STEEL.plate(), 'C', ModItems.coil_copper, 'D', LI.dust() });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_lever_action, 1), new Object[] { "PPI", "SWD", 'P', FE.plate(), 'I', ModItems.mechanism_rifle_1, 'S', KEY_STICK, 'D', KEY_PLANKS, 'W', ModItems.wire_aluminium });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_lever_action_dark, 1), new Object[] { "PPI", "SWD", 'P', STEEL.plate(), 'I', ModItems.mechanism_rifle_1, 'S', KEY_STICK, 'D', KEY_PLANKS, 'W', ModItems.wire_aluminium });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_bolt_action, 1), new Object[] { "PPI", "SWD", 'P', STEEL.plate(), 'I', ModItems.mechanism_rifle_1, 'S', KEY_STICK, 'D', KEY_PLANKS, 'W', ModItems.wire_copper });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_bolt_action_green, 1), new Object[] { "PPI", "SWD", 'P', FE.plate(), 'I', ModItems.mechanism_rifle_1, 'S', KEY_STICK, 'D', KEY_PLANKS, 'W', ModItems.wire_copper });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_bolt_action_saturnite, 1), new Object[] { "PPI", "SWD", 'P', BIGMT.plate(), 'I', ModItems.mechanism_rifle_1, 'S', KEY_STICK, 'D', KEY_PLANKS, 'W', ModItems.wire_tungsten });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_b92), new Object[] { "DDD", "SSC", "  R", 'D', ModItems.plate_dineutronium, 'S', STAR.ingot(), 'C', ModItems.circuit_targeting_tier6, 'R', ModItems.gun_revolver_schrabidium });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_b93), new Object[] { "PCE", "SEB", "PCE", 'P', ModItems.plate_dineutronium, 'C', ModItems.weaponized_starblaster_cell, 'E', ModItems.component_emitter, 'B', ModItems.gun_b92, 'S', ModItems.singularity_spark });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_b92_ammo, 1), new Object[] { "PSP", "ESE", "PSP", 'P', STEEL.plate(), 'S', STAR.ingot(), 'E', ModItems.powder_spark_mix });
		CraftingManager.addShapelessAuto(new ItemStack(ModItems.weaponized_starblaster_cell, 1), new Object[] { new ItemStack(ModItems.fluid_tank_full, 1, Fluids.ACID.getID()), GunB92Cell.getFullCell(), ModItems.wire_copper });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_uzi, 1), new Object[] { "SMS", " PB", " P ", 'S', STEEL.ingot(), 'M', ModItems.mechanism_rifle_2, 'P', STEEL.plate(), 'B', ModItems.bolt_dura_steel });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_uzi_silencer, 1), new Object[] { "P  ", " P ", "  U", 'P', ANY_PLASTIC.ingot(), 'U', ModItems.gun_uzi });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_uzi_saturnite, 1), new Object[] { "SMS", " PB", " P ", 'S', BIGMT.ingot(), 'M', ModItems.mechanism_rifle_2, 'P', BIGMT.plate(), 'B', ModItems.bolt_tungsten });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_uzi_saturnite_silencer, 1), new Object[] { "P  ", " P ", "  U", 'P', ANY_PLASTIC.ingot(), 'U', ModItems.gun_uzi_saturnite });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_bolter, 1), new Object[] { "SSM", "PIP", " I ", 'S', BIGMT.plate(), 'I', BIGMT.ingot(), 'M', ModItems.mechanism_special, 'P', ANY_PLASTIC.ingot() });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_calamity, 1), new Object[] { " PI", "BBM", " PI", 'P', FE.plate(), 'B', ModItems.pipes_steel, 'M', ModItems.mechanism_rifle_1, 'I', STEEL.ingot() });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_calamity_dual, 1), new Object[] { "BBM", " PI", "BBM", 'P', FE.plate(), 'B', ModItems.pipes_steel, 'M', ModItems.mechanism_rifle_1, 'I', STEEL.ingot() });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_minigun, 1), new Object[] { "PIB", "PCM", "PIB", 'P', ModItems.pipes_steel, 'B', STEEL.block(), 'I', ANY_PLASTIC.ingot(), 'C', ModItems.mechanism_rifle_2, 'M', ModItems.motor });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_avenger, 1), new Object[] { "PIB", "PCM", "PIB", 'P', ModItems.pipes_steel, 'B', BE.block(), 'I', DESH.ingot(), 'C', ModItems.mechanism_rifle_2, 'M', ModItems.motor });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_lacunae, 1), new Object[] { "TIT", "ILI", "PRP", 'T', ModItems.syringe_taint, 'I', STAR.ingot(), 'L', ModItems.gun_minigun, 'P', ModItems.pellet_rtg, 'R', ModBlocks.machine_rtg_grey });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_mymy, 1), new Object[] { "PP ", " WP", 'P', ModItems.plate_polymer, 'W', ModItems.wire_aluminium });
		//CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_darter, 1), new Object[] { "SST", "  P", 'S', STEEL.plate(), 'T', ModItems.gas_empty, 'P', ANY_PLASTIC.ingot() });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_fireext, 1), new Object[] { "HB", " T", 'H', ModItems.hull_small_steel, 'B', ModItems.bolt_tungsten, 'T', ModItems.tank_steel });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_benelli), new Object[] { "HHP", "SSM", "AAP", 'H', ModItems.ingot_dura_steel, 'S', ModItems.hull_small_steel, 'A', ModItems.hull_small_aluminium, 'P', ModItems.ingot_polymer, 'M', ModItems.mechanism_rifle_2 });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gun_spas12, 1), new Object[] { "TPS", "HHR", "  L", 'T', ModItems.bolt_tungsten, 'P', STEEL.plate(), 'S', STEEL.ingot(), 'H', ModItems.hull_small_steel, 'R', ModItems.mechanism_rifle_1, 'L', ANY_PLASTIC.ingot()});
		
		//Ammo assemblies
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.pellet_flechette, 1), new Object[] { " L ", " L ", "LLL", 'L', PB.nugget() });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.pellet_claws, 1), new Object[] { " X ", "X X", " XX", 'X', STEEL.plate() });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.assembly_iron, 24), new Object[] { " I", "GC", " P", 'I', FE.ingot(), 'G', ModItems.cordite, 'C', ModItems.casing_357, 'P', ModItems.primer_357 });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.assembly_iron, 24), new Object[] { " I", "GC", " P", 'I', FE.ingot(), 'G', ModItems.ballistite, 'C', ModItems.casing_357, 'P', ModItems.primer_357 });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.assembly_steel, 24), new Object[] { " I", "GC", " P", 'I', PB.ingot(), 'G', ModItems.cordite, 'C', ModItems.casing_357, 'P', ModItems.primer_357 });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.assembly_steel, 24), new Object[] { " I", "GC", " P", 'I', PB.ingot(), 'G', ModItems.ballistite, 'C', ModItems.casing_357, 'P', ModItems.primer_357 });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.assembly_lead, 24), new Object[] { " I", "GC", " P", 'I', U235.ingot(), 'G', ModItems.cordite, 'C', KEY_CLEARGLASS, 'P', ModItems.primer_357 });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.assembly_lead, 24), new Object[] { " I", "GC", " P", 'I', PU239.ingot(), 'G', ModItems.cordite, 'C', KEY_CLEARGLASS, 'P', ModItems.primer_357 });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.assembly_lead, 24), new Object[] { " I", "GC", " P", 'I', ModItems.trinitite, 'G', ModItems.cordite, 'C', KEY_CLEARGLASS, 'P', ModItems.primer_357 });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.assembly_lead, 24), new Object[] { " I", "GC", " P", 'I', ModItems.nuclear_waste_tiny, 'G', ModItems.cordite, 'C', KEY_CLEARGLASS, 'P', ModItems.primer_357 });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.assembly_gold, 24), new Object[] { " I", "GC", " P", 'I', AU.ingot(), 'G', ModItems.cordite, 'C', ModItems.casing_357, 'P', ModItems.primer_357 });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.assembly_schrabidium, 6), new Object[] { " I ", "GCN", " P ", 'I', SA326.ingot(), 'G', ModItems.cordite, 'C', ModItems.casing_357, 'P', ModItems.primer_357, 'N', ModItems.billet_yharonite });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.assembly_nightmare, 24), new Object[] { " I", "GC", " P", 'I', W.ingot(), 'G', ModItems.cordite, 'C', ModItems.casing_357, 'P', ModItems.primer_357 });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.assembly_desh, 24), new Object[] { " I", "GC", " P", 'I', DESH.ingot(), 'G', ModItems.cordite, 'C', ModItems.casing_357, 'P', ModItems.primer_357 });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.assembly_smg, 32), new Object[] { " I", "GC", " P", 'I', PB.ingot(), 'G', ANY_SMOKELESS.dust(), 'C', ModItems.casing_9, 'P', ModItems.primer_9 });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.assembly_45, 32), " I", "GC", " P", 'I', CU.ingot(), 'G', ANY_SMOKELESS.dust(), 'C', ModItems.casing_44, 'P', ModItems.primer_44);
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.assembly_556, 32), new Object[] { " I", "GC", " P", 'I', STEEL.ingot(), 'G', ModItems.cordite, 'C', ModItems.casing_9, 'P', ModItems.primer_9 });
		CraftingManager.addRecipeAuto(ModItems.ammo_556.stackFromEnum(30, Ammo556mm.K), new Object[] { "G", "C", "P", 'G', ANY_GUNPOWDER.dust(), 'C', ModItems.casing_9, 'P', ModItems.primer_9 });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.assembly_308, 32), " I", "GC", " P", 'I', CU.ingot(), 'G', ANY_SMOKELESS.dust(), 'C', ModItems.casing_50, 'P', ModItems.primer_9);
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.assembly_uzi, 32), new Object[] { " I", "GC", " P", 'I', FE.ingot(), 'G', ANY_SMOKELESS.dust(), 'C', ModItems.casing_9, 'P', ModItems.primer_9 });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.assembly_lacunae, 32), new Object[] { " I", "GC", " P", 'I', CU.ingot(), 'G', ModItems.cordite, 'C', ModItems.casing_9, 'P', ModItems.primer_9 });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.assembly_nopip, 24), new Object[] { " I", "GC", " P", 'I', PB.ingot(), 'G', ANY_SMOKELESS.dust(), 'C', ModItems.casing_44, 'P', ModItems.primer_44 });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.ammo_12gauge, 12), new Object[] { " I ", "GCL", " P ", 'I', ModItems.pellet_buckshot, 'G', ANY_SMOKELESS.dust(), 'C', ModItems.casing_buckshot, 'P', ModItems.primer_buckshot, 'L', ModItems.plate_polymer });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.ammo_4gauge, 12), new Object[] { " I ", "GCL", " P ", 'I', ModItems.pellet_buckshot, 'G', ANY_SMOKELESS.dust(), 'C', ModItems.casing_50, 'P', ModItems.primer_50, 'L', ModItems.plate_polymer });
		CraftingManager.addRecipeAuto(ModItems.ammo_4gauge.stackFromEnum(12, Ammo4Gauge.SLUG), new Object[] { " I ", "GCL", " P ", 'I', PB.ingot(), 'G', ANY_SMOKELESS.dust(), 'C', ModItems.casing_50, 'P', ModItems.primer_50, 'L', ModItems.plate_polymer });
		CraftingManager.addRecipeAuto(ModItems.ammo_4gauge.stackFromEnum(12, Ammo4Gauge.FLECHETTE), new Object[] { " I ", "GCL", " P ", 'I', ModItems.pellet_flechette, 'G', ANY_SMOKELESS.dust(), 'C', ModItems.casing_50, 'P', ModItems.primer_50, 'L', ModItems.plate_polymer });
		CraftingManager.addRecipeAuto(ModItems.ammo_4gauge.stackFromEnum(4, Ammo4Gauge.EXPLOSIVE), new Object[] { " I ", "GCL", " P ", 'I', ModBlocks.tnt, 'G', ANY_SMOKELESS.dust(), 'C', ModItems.casing_50, 'P', ModItems.primer_50, 'L', ModItems.plate_polymer });
		CraftingManager.addRecipeAuto(ModItems.ammo_4gauge.stackFromEnum(6, Ammo4Gauge.EXPLOSIVE), new Object[] { " I ", "GCL", " P ", 'I', ANY_PLASTICEXPLOSIVE.ingot(), 'G', ANY_SMOKELESS.dust(), 'C', ModItems.casing_50, 'P', ModItems.primer_50, 'L', ModItems.plate_polymer });
		CraftingManager.addRecipeAuto(ModItems.ammo_4gauge.stackFromEnum(4, Ammo4Gauge.MINING), new Object[] { " I ", "GCL", " P ", 'I', ModBlocks.det_miner, 'G', ANY_SMOKELESS.dust(), 'C', ModItems.casing_50, 'P', ModItems.primer_50, 'L', ModItems.plate_polymer });
		CraftingManager.addShapelessAuto(ModItems.ammo_4gauge.stackFromEnum(Ammo4Gauge.QUACK), new Object[] { ModItems.ammo_4gauge, ModItems.nugget_bismuth, ModItems.nugget_tantalium, ModItems.ball_dynamite });
		CraftingManager.addRecipeAuto(ModItems.ammo_20gauge.stackFromEnum(12, Ammo20Gauge.STOCK), new Object[] { " I ", "GCL", " P ", 'I', ModItems.pellet_buckshot, 'G', ANY_SMOKELESS.dust(), 'C', ModItems.casing_buckshot, 'P', ModItems.primer_buckshot, 'L', CU.plate() });
		CraftingManager.addRecipeAuto(ModItems.ammo_20gauge.stackFromEnum(12, Ammo20Gauge.SLUG), new Object[] { " I ", "GCL", " P ", 'I', PB.ingot(), 'G', ANY_SMOKELESS.dust(), 'C', ModItems.casing_buckshot, 'P', ModItems.primer_buckshot, 'L', CU.plate() });
		CraftingManager.addRecipeAuto(ModItems.ammo_20gauge.stackFromEnum(12, Ammo20Gauge.EXPLOSIVE), new Object[] { " I ", "GCL", " P ", 'I', ModItems.pellet_cluster, 'G', ANY_SMOKELESS.dust(), 'C', ModItems.casing_buckshot, 'P', ModItems.primer_buckshot, 'L', CU.plate() });
		CraftingManager.addRecipeAuto(ModItems.ammo_20gauge.stackFromEnum(20, Ammo20Gauge.FLECHETTE), new Object[] { " I ", "GCL", " P ", 'I', ModItems.pellet_flechette, 'G', ANY_SMOKELESS.dust(), 'C', ModItems.casing_buckshot, 'P', ModItems.primer_buckshot, 'L', CU.plate() });
		CraftingManager.addRecipeAuto(ModItems.ammo_357.stackFromEnum(6, Ammo357Magnum.NIGHTMARE2), new Object[] { "I", "C", "P", 'I', ModItems.powder_power, 'C', ModItems.casing_buckshot, 'P', ModItems.primer_buckshot });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.assembly_calamity, 12), new Object[] { " I ", "GCG", " P ", 'I', PB.ingot(), 'G', ModItems.cordite, 'C', ModItems.casing_50, 'P', ModItems.primer_50 });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.assembly_actionexpress, 12), new Object[] { " I", "GC", " P", 'I', PB.ingot(), 'G', ModItems.cordite, 'C', ModItems.casing_50, 'P', ModItems.primer_50 });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.assembly_nuke, 1), new Object[] { " WP", "SEP", " WP", 'W', ModItems.wire_aluminium, 'P', STEEL.plate(), 'S', ModItems.hull_small_steel, 'E', ModItems.ingot_semtex });
		CraftingManager.addRecipeAuto(ModItems.ammo_dart.stackFromEnum(16, AmmoDart.GPS), new Object[] { "IPI", "ICI", "IPI", 'I', ModItems.plate_polymer, 'P', FE.plate(), 'C', new ItemStack(ModItems.fluid_tank_lead_full, 1, Fluids.WATZ.getID()) });
		CraftingManager.addRecipeAuto(ModItems.ammo_dart.stackFromEnum(16, AmmoDart.NERF), new Object[] { "I", "I", 'I', ModItems.plate_polymer });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.assembly_luna_sniper, 4), new Object[] { " B ", "GCG", "GPG", 'B', ModItems.bolt_staballoy, 'G', ModItems.powder_nitan_mix, 'C', ModItems.casing_50, 'P', ModItems.powder_power});

		//Folly shells

		CraftingManager.addRecipeAuto(new ItemStack(ModItems.folly_bullet_nuclear, 1), new Object[] { " N ", "UTU", "UTU", 'N', ModItems.ammo_nuke, 'U', DESH.ingot(), 'T', W.ingot() });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.folly_bullet_du, 1), new Object[] { " U ", "UDU", "UTU", 'U', U238.ingot(), 'D', DURA.ingot(), 'T', W.ingot() });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.folly_shell, 1), new Object[] { "IPI", "IPI", "IMI", 'I', STEEL.ingot(), 'P', FE.plate(), 'M', ModItems.cordite });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.ammo_folly, 1), new Object[] { " B ", "MEM", " S ", 'B', ModItems.folly_bullet, 'M', ModItems.powder_power, 'E', Fluids.NITAN.getDict(1000), 'S', ModItems.folly_shell });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.ammo_folly_nuclear, 2), new Object[] { " B ", "EEE", " S ", 'B', ModItems.folly_bullet_nuclear, 'E', ModBlocks.det_charge, 'S', ModItems.folly_shell });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.ammo_folly_tandem, 2), new Object[] { " B ", "EEE", " S ", 'B', ModItems.folly_bullet_tandem, 'E', ModBlocks.det_charge, 'S', ModItems.folly_shell });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.ammo_folly_du, 4), new Object[] { " B ", " S ", 'B', ModItems.folly_bullet_du, 'S', ModItems.folly_shell });



		//Rockets
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.ammo_rocket, 1), new Object[] { " T ", "GCG", " P ", 'T', Blocks.tnt, 'G', ModItems.rocket_fuel, 'C', ModItems.casing_50, 'P', ModItems.primer_50 });// I got tired of changing *all* of them, the stock one is always the first one anyway
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.ammo_rocket, 2), new Object[] { " T ", "GCG", " P ", 'T', ANY_PLASTICEXPLOSIVE.ingot(), 'G', ModItems.rocket_fuel, 'C', ModItems.casing_50, 'P', ModItems.primer_50 });
		CraftingManager.addRecipeAuto(ModItems.ammo_rocket.stackFromEnum(AmmoRocket.HE), new Object[] { "G", "R", 'G', ANY_PLASTICEXPLOSIVE.ingot(), 'R', ModItems.ammo_rocket });
		CraftingManager.addRecipeAuto(ModItems.ammo_rocket.stackFromEnum(AmmoRocket.INCENDIARY), new Object[] { "G", "R", 'G', P_RED.dust(), 'R', ModItems.ammo_rocket });
		CraftingManager.addRecipeAuto(ModItems.ammo_rocket.stackFromEnum(AmmoRocket.PHOSPHORUS), new Object[] { "G", "R", 'G', P_WHITE.ingot(), 'R', ModItems.ammo_rocket });
		CraftingManager.addRecipeAuto(ModItems.ammo_rocket.stackFromEnum(AmmoRocket.EMP), new Object[] { "G", "R", 'G', "dustDiamond", 'R', ModItems.ammo_rocket });
		CraftingManager.addRecipeAuto(ModItems.ammo_rocket.stackFromEnum(AmmoRocket.SHRAPNEL), new Object[] { "G", "R", 'G', ModItems.pellet_buckshot, 'R', ModItems.ammo_rocket });
		CraftingManager.addRecipeAuto(ModItems.ammo_rocket.stackFromEnum(AmmoRocket.GLARE), new Object[] { "GGG", "GRG", "GGG", 'G', REDSTONE.dust(), 'R', ModItems.ammo_rocket });
		CraftingManager.addRecipeAuto(ModItems.ammo_rocket.stackFromEnum(AmmoRocket.CHLORINE), new Object[] { "G", "R", 'G', ModItems.pellet_gas, 'R', ModItems.ammo_rocket });
		CraftingManager.addRecipeAuto(ModItems.ammo_rocket.stackFromEnum(AmmoRocket.CANISTER), new Object[] { "G", "R", 'G', ModItems.pellet_canister, 'R', ModItems.ammo_rocket });
		CraftingManager.addRecipeAuto(ModItems.ammo_rocket.stackFromEnum(AmmoRocket.NUCLEAR), new Object[] { " P ", "NRN", " P ", 'P', PU239.nugget(), 'N', OreDictManager.getReflector(), 'R', ModItems.ammo_rocket });
		CraftingManager.addRecipeAuto(ModItems.ammo_rocket.stackFromEnum(2, AmmoRocket.RPC), new Object[] { "BP ", "CBH", " DR", 'B', ModItems.blades_steel, 'P', STEEL.plate(), 'C', ModItems.canister_fuel, 'H', ModItems.hull_small_steel, 'D', ModItems.piston_selenium, 'R', ModItems.ammo_rocket });
		CraftingManager.addRecipeAuto(ModItems.ammo_rocket.stackFromEnum(2, AmmoRocket.RPC), new Object[] { "BP ", "CBH", " DR", 'B', ModItems.blades_steel, 'P', STEEL.plate(), 'C', ModItems.canister_petroil, 'H', ModItems.hull_small_steel, 'D', ModItems.piston_selenium, 'R', ModItems.ammo_rocket });
		CraftingManager.addRecipeAuto(ModItems.ammo_rocket.stackFromEnum(2, AmmoRocket.RPC), new Object[] { "BP ", "CBH", " DR", 'B', ModItems.blades_steel, 'P', STEEL.plate(), 'C', ModItems.canister_biofuel, 'H', ModItems.hull_small_steel, 'D', ModItems.piston_selenium, 'R', ModItems.ammo_rocket });

		//Stinger Rockets
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.ammo_stinger_rocket, 2), "CE ", "FSF", " P ", 'C', ModItems.circuit_aluminium, 'E', ANY_PLASTICEXPLOSIVE.ingot(), 'F', ModItems.rocket_fuel, 'S', ModItems.hull_small_aluminium, 'P', ModItems.primer_50);
		CraftingManager.addRecipeAuto(ModItems.ammo_stinger_rocket.stackFromEnum(AmmoStinger.HE), new Object[] { "S", "R", 'S', ANY_PLASTICEXPLOSIVE.ingot(), 'R', ModItems.ammo_stinger_rocket });
		CraftingManager.addRecipeAuto(ModItems.ammo_stinger_rocket.stackFromEnum(AmmoStinger.INCENDIARY), new Object[] { "S", "R", 'S', P_RED.dust(), 'R', ModItems.ammo_stinger_rocket });
		CraftingManager.addRecipeAuto(ModItems.ammo_stinger_rocket.stackFromEnum(AmmoStinger.NUCLEAR), new Object[] { "RPR", "PSP", "RPR", 'R', ModItems.neutron_reflector, 'P', PU239.nugget(), 'S', ModItems.ammo_stinger_rocket.stackFromEnum(AmmoStinger.HE) });
		CraftingManager.addRecipeAuto(ModItems.ammo_stinger_rocket.stackFromEnum(AmmoStinger.BONES), new Object[] { " C ", "SKR", " P ", 'C', ModItems.fallout, 'S', SR90.dust(), 'K', ModItems.ammo_stinger_rocket, 'R', RA226.dust(), 'P', PU.dust() });
		
		//40mm grenades
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.ammo_grenade, 2), new Object[] { " T ", "GCI", " P ", 'T', ANY_HIGHEXPLOSIVE.dust(), 'G', ANY_SMOKELESS.dust(), 'C', ModItems.casing_50, 'P', ModItems.primer_50, 'I', FE.plate() });
		CraftingManager.addRecipeAuto(ModItems.ammo_grenade.stackFromEnum(2, AmmoGrenade.TRACER), new Object[] { " T ", "GCI", " P ", 'T', LAPIS.dust(), 'G', ANY_SMOKELESS.dust(), 'C', ModItems.casing_50, 'P', ModItems.primer_50, 'I', FE.plate() });
		CraftingManager.addRecipeAuto(ModItems.ammo_grenade.stackFromEnum(2, AmmoGrenade.HE), new Object[] { "GIG", 'G', ModItems.ammo_grenade, 'I', ANY_PLASTICEXPLOSIVE.ingot() });
		CraftingManager.addRecipeAuto(ModItems.ammo_grenade.stackFromEnum(2, AmmoGrenade.INCENDIARY), new Object[] { "GIG", 'G', ModItems.ammo_grenade, 'I', P_RED.dust() });
		CraftingManager.addRecipeAuto(ModItems.ammo_grenade.stackFromEnum(2, AmmoGrenade.PHOSPHORUS), new Object[] { "GIG", 'G', ModItems.ammo_grenade, 'I', P_WHITE.ingot() });
		CraftingManager.addShapelessAuto(ModItems.ammo_grenade.stackFromEnum(AmmoGrenade.SMOKE), ModItems.ammo_grenade, P_WHITE.ingot(), ANY_GUNPOWDER.dust());
		CraftingManager.addRecipeAuto(ModItems.ammo_grenade.stackFromEnum(2, AmmoGrenade.CHLORINE), new Object[] { "GIG", 'G', ModItems.ammo_grenade, 'I', ModItems.powder_poison });
		CraftingManager.addRecipeAuto(ModItems.ammo_grenade.stackFromEnum(2, AmmoGrenade.CONCUSSION), new Object[] { "GIG", 'G', ModItems.ammo_grenade, 'I', Items.glowstone_dust });
		CraftingManager.addRecipeAuto(ModItems.ammo_grenade.stackFromEnum(2, AmmoGrenade.NUCLEAR), new Object[] { " P ", "GIG", " P ", 'G', ModItems.ammo_grenade.stackFromEnum(AmmoGrenade.HE), 'I', ModItems.neutron_reflector, 'P', PU239.nugget() });
		CraftingManager.addRecipeAuto(ModItems.ammo_grenade.stackFromEnum(AmmoGrenade.FINNED), new Object[] { "G", "R", 'G', Items.feather, 'R', ModItems.ammo_grenade });
		CraftingManager.addRecipeAuto(ModItems.ammo_grenade.stackFromEnum(AmmoGrenade.KAMPF), new Object[] { "G", "R", 'G', ModItems.ammo_rocket, 'R', ModItems.ammo_grenade });

		//240mm Shells
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.ammo_shell, 4), new Object[] { " T ", "GHG", "CCC", 'T', ModBlocks.tnt, 'G', Items.gunpowder, 'H', ModItems.hull_small_steel, 'C', CU.ingot() });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.ammo_shell, 4), new Object[] { " T ", "GHG", "CCC", 'T', ModBlocks.tnt, 'G', ModItems.ballistite, 'H', ModItems.hull_small_steel, 'C', CU.ingot() });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.ammo_shell, 6), new Object[] { " T ", "GHG", "CCC", 'T', ModBlocks.tnt, 'G', ModItems.cordite, 'H', ModItems.hull_small_steel, 'C', CU.ingot() });
		CraftingManager.addRecipeAuto(ModItems.ammo_shell.stackFromEnum(4, Ammo240Shell.EXPLOSIVE), new Object[] { " T ", "GHG", "CCC", 'T', ANY_PLASTICEXPLOSIVE.ingot(), 'G', Items.gunpowder, 'H', ModItems.hull_small_steel, 'C', CU.ingot() });
		CraftingManager.addRecipeAuto(ModItems.ammo_shell.stackFromEnum(4, Ammo240Shell.EXPLOSIVE), new Object[] { " T ", "GHG", "CCC", 'T', ANY_PLASTICEXPLOSIVE.ingot(), 'G', ModItems.ballistite, 'H', ModItems.hull_small_steel, 'C', CU.ingot() });
		CraftingManager.addRecipeAuto(ModItems.ammo_shell.stackFromEnum(6, Ammo240Shell.EXPLOSIVE), new Object[] { " T ", "GHG", "CCC", 'T', ANY_PLASTICEXPLOSIVE.ingot(), 'G', ModItems.cordite, 'H', ModItems.hull_small_steel, 'C', CU.ingot() });
		CraftingManager.addRecipeAuto(ModItems.ammo_shell.stackFromEnum(4, Ammo240Shell.APFSDS_T), new Object[] { " I ", "GIG", "CCC", 'I', W.ingot(), 'G', Items.gunpowder, 'C', CU.ingot() });
		CraftingManager.addRecipeAuto(ModItems.ammo_shell.stackFromEnum(4, Ammo240Shell.APFSDS_T), new Object[] { " I ", "GIG", "CCC", 'I', W.ingot(), 'G', ModItems.ballistite, 'C', CU.ingot() });
		CraftingManager.addRecipeAuto(ModItems.ammo_shell.stackFromEnum(6, Ammo240Shell.APFSDS_T), new Object[] { " I ", "GIG", "CCC", 'I', W.ingot(), 'G', ModItems.cordite, 'C', CU.ingot() });
		CraftingManager.addRecipeAuto(ModItems.ammo_shell.stackFromEnum(4, Ammo240Shell.APFSDS_DU), new Object[] { " I ", "GIG", "CCC", 'I', U238.ingot(), 'G', Items.gunpowder, 'C', CU.ingot() });
		CraftingManager.addRecipeAuto(ModItems.ammo_shell.stackFromEnum(4, Ammo240Shell.APFSDS_DU), new Object[] { " I ", "GIG", "CCC", 'I', U238.ingot(), 'G', ModItems.ballistite, 'C', CU.ingot() });
		CraftingManager.addRecipeAuto(ModItems.ammo_shell.stackFromEnum(6, Ammo240Shell.APFSDS_DU), new Object[] { " I ", "GIG", "CCC", 'I', U238.ingot(), 'G', ModItems.cordite, 'C', CU.ingot() });
		CraftingManager.addRecipeAuto(ModItems.ammo_shell.stackFromEnum(Ammo240Shell.W9), new Object[] { " P ", "NSN", " P ", 'P', PU239.nugget(), 'N', OreDictManager.getReflector(), 'S', ModItems.ammo_shell.stackFromEnum(Ammo240Shell.EXPLOSIVE) });
		CraftingManager.addRecipeAuto(ModItems.ammo_shell.stackFromEnum(Ammo240Shell.W9_TRUE), "PCP", "PWP", "PSP", 'P', TI.plate(), 'C', ModItems.circuit_targeting_tier3, 'S', ModItems.ammo_shell.stackFromEnum(Ammo240Shell.EXPLOSIVE), 'W', ModItems.warhead_nuclear);
		
		//DGK Belts
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.ammo_dgk, 1), new Object[] { "LLL", "GGG", "CCC", 'L', PB.plate(), 'G', ANY_SMOKELESS.dust(), 'C', CU.ingot() });
		
		//Mini Nuke
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.ammo_nuke, 1), new Object[] { "P", "S", "P", 'P', PU239.nugget(), 'S', ModItems.assembly_nuke });
		CraftingManager.addRecipeAuto(ModItems.ammo_nuke.stackFromEnum(AmmoFatman.LOW), new Object[] { "P", "S", 'P', PU239.nugget(), 'S', ModItems.assembly_nuke });
		CraftingManager.addRecipeAuto(ModItems.ammo_nuke.stackFromEnum(AmmoFatman.HIGH), new Object[] { "PPP", "PSP", "PPP", 'P', PU239.nugget(), 'S', ModItems.assembly_nuke });
		CraftingManager.addRecipeAuto(ModItems.ammo_nuke.stackFromEnum(AmmoFatman.TOTS), new Object[] { "PPP", "PIP", "PPP", 'P', ModItems.pellet_cluster, 'I', PU239.ingot() });
		CraftingManager.addRecipeAuto(ModItems.ammo_nuke.stackFromEnum(AmmoFatman.SAFE), new Object[] { "G", "N", 'G', Items.glowstone_dust, 'N', ModItems.ammo_nuke.stackFromEnum(AmmoFatman.LOW) });
		CraftingManager.addRecipeAuto(ModItems.ammo_nuke.stackFromEnum(AmmoFatman.PUMPKIN), new Object[] { " T ", "TST", " T ", 'T', ModBlocks.tnt, 'S', ModItems.assembly_nuke });
		
		//MIRV recycling
		CraftingManager.addShapelessAuto(new ItemStack(ModItems.ammo_nuke, 6), new Object[] { ModItems.ammo_nuke.stackFromEnum(AmmoFatman.MIRV) });
		CraftingManager.addShapelessAuto(ModItems.ammo_nuke.stackFromEnum(6, AmmoFatman.LOW), new Object[] { ModItems.ammo_nuke.stackFromEnum(AmmoFatman.MIRV_LOW) });
		CraftingManager.addShapelessAuto(ModItems.ammo_nuke.stackFromEnum(6, AmmoFatman.HIGH), new Object[] { ModItems.ammo_nuke.stackFromEnum(AmmoFatman.MIRV_HIGH) });
		CraftingManager.addShapelessAuto(ModItems.ammo_nuke.stackFromEnum(6, AmmoFatman.SAFE), new Object[] { ModItems.ammo_nuke.stackFromEnum(AmmoFatman.MIRV_SAFE) });
		
		//MIRV
		CraftingManager.addRecipeAuto(ModItems.ammo_nuke.stackFromEnum(AmmoFatman.MIRV), new Object[] { "NNN", "CDS", "NNN", 'N', ModItems.ammo_nuke, 'C', ModItems.cap_aluminium, 'D', ModBlocks.det_cord, 'S', ModItems.hull_small_steel });
		CraftingManager.addRecipeAuto(ModItems.ammo_nuke.stackFromEnum(AmmoFatman.MIRV_LOW), new Object[] { "NNN", "CDS", "NNN", 'N', ModItems.ammo_nuke.stackFromEnum(AmmoFatman.LOW), 'C', ModItems.cap_aluminium, 'D', ModBlocks.det_cord, 'S', ModItems.hull_small_steel });
		CraftingManager.addRecipeAuto(ModItems.ammo_nuke.stackFromEnum(AmmoFatman.MIRV_HIGH), new Object[] { "NNN", "CDS", "NNN", 'N', ModItems.ammo_nuke.stackFromEnum(AmmoFatman.HIGH), 'C', ModItems.cap_aluminium, 'D', ModBlocks.det_cord, 'S', ModItems.hull_small_steel });
		CraftingManager.addRecipeAuto(ModItems.ammo_nuke.stackFromEnum(AmmoFatman.MIRV_SAFE), new Object[] { "NNN", "CDS", "NNN", 'N', ModItems.ammo_nuke.stackFromEnum(AmmoFatman.SAFE), 'C', ModItems.cap_aluminium, 'D', ModBlocks.det_cord, 'S', ModItems.hull_small_steel });
		//since the milk part of the recipe isn't realy present in the MIRV's effect, it might as well be replaced with something more sensible, i.e. duct tape
		CraftingManager.addRecipeAuto(ModItems.ammo_nuke.stackFromEnum(AmmoFatman.MIRV_SPECIAL), new Object[] { "CBC", "MCM", "CBC", 'C', ModItems.canned_jizz, 'B', ModItems.ammo_nuke.stackFromEnum(AmmoFatman.BALEFIRE), 'M', ModItems.ammo_nuke.stackFromEnum(AmmoFatman.MIRV) });
		
		//Flamer fuel
		CraftingManager.addRecipeAuto(ModItems.ammo_fuel.stackFromEnum(AmmoFlamethrower.DIESEL), new Object[] { " P ", "BDB", " P ", 'P', STEEL.plate(), 'B', ModItems.bolt_tungsten, 'D', ModItems.canister_fuel });
		CraftingManager.addRecipeAuto(ModItems.ammo_fuel.stackFromEnum(AmmoFlamethrower.NAPALM), new Object[] { " P ", "BDB", " P ", 'P', STEEL.plate(), 'B', ModItems.bolt_tungsten, 'D', ModItems.canister_napalm });
		CraftingManager.addRecipeAuto(ModItems.ammo_fuel.stackFromEnum(AmmoFlamethrower.PHOSPHORUS), new Object[] { "CPC", "CDC", "CPC", 'C', COAL.dust(), 'P', P_WHITE.ingot(), 'D', ModItems.ammo_fuel });
		CraftingManager.addRecipeAuto(ModItems.ammo_fuel.stackFromEnum(AmmoFlamethrower.CHLORINE), new Object[] { "PDP", "BDB", "PDP", 'P', STEEL.plate(), 'B', ModItems.bolt_tungsten, 'D', ModItems.pellet_gas });
		CraftingManager.addRecipeAuto(ModItems.ammo_fuel.stackFromEnum(AmmoFlamethrower.VAPORIZER), new Object[] { "PSP", "SNS", "PSP", 'P', P_WHITE.ingot(), 'S', ModItems.crystal_sulfur, 'N', ModItems.ammo_fuel.stackFromEnum(AmmoFlamethrower.NAPALM) });
		
		//Fire Extingusisher Tanks
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.ammo_fireext, 1), new Object[] { " P ", "BDB", " P ", 'P', STEEL.plate(), 'B', ModItems.bolt_tungsten, 'D', new ItemStack(ModItems.fluid_tank_full, 1, Fluids.WATER.getID()) });
		CraftingManager.addRecipeAuto(ModItems.ammo_fireext.stackFromEnum(AmmoFireExt.FOAM), new Object[] { " N ", "NFN", " N ", 'N', KNO.dust(), 'F', ModItems.ammo_fireext });
		CraftingManager.addRecipeAuto(ModItems.ammo_fireext.stackFromEnum(AmmoFireExt.SAND), new Object[] { "NNN", "NFN", "NNN", 'N', ModBlocks.sand_boron, 'F', ModItems.ammo_fireext });

		//Turrets
		//CraftingManager.addRecipeAuto(new ItemStack(ModItems.turret_light_ammo, 1), new Object[] { " L ", "IGI", "ICI", 'L', PB.plate(), 'I', IRON.plate(), 'C', CU.plate(), 'G', Items.gunpowder });
		//CraftingManager.addRecipeAuto(new ItemStack(ModItems.turret_heavy_ammo, 1), new Object[] { "LGC", "LGC", "LGC", 'L', PB.plate(), 'C', CU.plate(), 'G', Items.gunpowder });
		//CraftingManager.addRecipeAuto(new ItemStack(ModItems.turret_rocket_ammo, 1), new Object[] { "TS ", "SGS", " SR", 'T', Blocks.tnt, 'S', STEEL.plate(), 'G', Items.gunpowder, 'R', REDSTONE.dust() });
		//CraftingManager.addRecipeAuto(new ItemStack(ModItems.turret_flamer_ammo, 1), new Object[] { "FSF", "FPF", "FPF", 'F', ModItems.gun_immolator_ammo, 'S', ModItems.pipes_steel, 'P', CU.plate() });
		//CraftingManager.addRecipeAuto(new ItemStack(ModItems.turret_tau_ammo, 1), new Object[] { "AAA", "AUA", "AAA", 'A', ModItems.gun_xvl1456_ammo, 'U', U238.block() });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.turret_spitfire_ammo, 1), new Object[] { "CP ", "PTP", " PR", 'P', STEEL.plate(), 'C', ModItems.circuit_copper, 'T', Blocks.tnt, 'R', REDSTONE.dust() });
		//CraftingManager.addRecipeAuto(new ItemStack(ModItems.turret_cwis_ammo, 1), new Object[] { "LLL", "GGG", "IGI", 'L', PB.plate(), 'I', IRON.plate(), 'G', Items.gunpowder });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.turret_cheapo_ammo, 1), new Object[] { "ILI", "IGI", "ICI", 'L', PB.plate(), 'I', STEEL.plate(), 'C', CU.plate(), 'G', Items.gunpowder });

		//Grenades
		final ItemStack genericGrenade = ModItems.grenade.stackFromEnum(AmmoHandGrenade.GENERIC);
//		CraftingManager.addRecipeAuto(new ItemStack(ModItems.grenade_generic, 4), new Object[] { "RS ", "ITI", " I ", 'I', FE.plate(), 'R', ModItems.wire_red_copper, 'S', STEEL.plate(), 'T', ANY_HIGHEXPLOSIVE.ingot() });
		CraftingManager.addRecipeAuto(ModItems.grenade.stackFromEnum(4, AmmoHandGrenade.GENERIC), "RS ", "AEA", " A ", 'A', AL.plate(), 'R', ModItems.wire_aluminium, 'E', ANY_HIGHEXPLOSIVE.ingot(), 'S', STEEL.plate());
//		CraftingManager.addShapelessAuto(new ItemStack(ModItems.grenade_smoke), ModItems.grenade_generic, P_WHITE.ingot(), ANY_GUNPOWDER.dust());
		CraftingManager.addRecipeAuto(ModItems.grenade.stackFromEnum(2, AmmoHandGrenade.STRONG), new Object[] { " G ", "SGS", " A ", 'G', genericGrenade, 'S', ANY_HIGHEXPLOSIVE.ingot(), 'A', AL.dust() });
		CraftingManager.addRecipeAuto(ModItems.grenade.stackFromEnum(2, AmmoHandGrenade.FRAG), new Object[] { " G ", "WGW", " K ", 'G', genericGrenade, 'W', KEY_PLANKS, 'K', Item.getItemFromBlock(Blocks.gravel) });
		CraftingManager.addRecipeAuto(ModItems.grenade.stackFromEnum(2, AmmoHandGrenade.POISON), new Object[] { " G ", "PGP", " P ", 'G', genericGrenade, 'P', ModItems.powder_poison });
		CraftingManager.addRecipeAuto(ModItems.grenade.stackFromEnum(2, AmmoHandGrenade.GAS), new Object[] { " G ", "CGC", " C ", 'G', genericGrenade, 'C', ModItems.pellet_gas });
		CraftingManager.addRecipeAuto(ModItems.grenade.stackFromEnum(AmmoHandGrenade.ASCHRAB), new Object[] {"RS ", "ITI", " S ", 'I', KEY_CLEARGLASS, 'R', ModItems.wire_red_copper, 'S', STEEL.plate(), 'T', ModItems.cell_anti_schrabidium });
//		CraftingManager.addRecipeAuto(new ItemStack(ModItems.grenade_mk2, 2), new Object[] { " G ", "SGS", " S ", 'G', ModItems.grenade_strong, 'S', Items.gunpowder });
		final ItemStack gascanGrenade = ModItems.grenade.stackFromEnum(AmmoHandGrenade.GASCAN);
		CraftingManager.addShapelessAuto(gascanGrenade, new Object[] { Fluids.DIESEL.getDict(1000), Items.flint });
		CraftingManager.addShapelessAuto(gascanGrenade, new Object[] { Fluids.DIESEL_CRACK.getDict(1000), Items.flint });
		CraftingManager.addShapelessAuto(gascanGrenade, new Object[] { Fluids.PETROIL.getDict(1000), Items.flint });
		CraftingManager.addShapelessAuto(gascanGrenade, new Object[] { Fluids.PETROIL_LEADED.getDict(1000), Items.flint });
		CraftingManager.addShapelessAuto(gascanGrenade, new Object[] { Fluids.GASOLINE.getDict(1000), Items.flint });
		CraftingManager.addShapelessAuto(gascanGrenade, new Object[] { Fluids.GASOLINE_LEADED.getDict(1000), Items.flint });
		CraftingManager.addShapelessAuto(gascanGrenade, new Object[] { Fluids.BIOFUEL.getDict(1000), Items.flint });
		CraftingManager.addShapelessAuto(ModItems.grenade.stackFromEnum(AmmoHandGrenade.LEMON), new Object[] { ModItems.lemon, ModItems.grenade.stackFromEnum(AmmoHandGrenade.STRONG) });
		CraftingManager.addShapelessAuto(new ItemStack(ModItems.gun_moist_nugget, 12), new Object[] { Items.bread, Items.wheat, Items.cooked_chicken, Items.egg });
		CraftingManager.addRecipeAuto(ModItems.grenade.stackFromEnum(4, AmmoHandGrenade.SMART), new Object[] { " A ", "ACA", " A ", 'A', ModItems.grenade.stackFromEnum(AmmoHandGrenade.STRONG), 'C', ModItems.circuit_aluminium });
		CraftingManager.addRecipeAuto(ModItems.grenade.stackFromEnum(AmmoHandGrenade.MIRV), new Object[] { "GGG", "GCG", "GGG", 'G', ModItems.grenade.stackFromEnum(AmmoHandGrenade.SMART), 'C', genericGrenade });
		CraftingManager.addRecipeAuto(ModItems.grenade.stackFromEnum(AmmoHandGrenade.BREACH), new Object[] { "G", "G", "P", 'G', ModItems.grenade.stackFromEnum(AmmoHandGrenade.SMART), 'P', BIGMT.plate() });
//		CraftingManager.addRecipeAuto(ModItems.grenade.stackFromEnum(AmmoHandGrenade.BURST), new Object[] { "GGG", "GCG", "GGG", 'G', ModItems.grenade_breach, 'C', ModItems.grenade_generic });
		CraftingManager.addRecipeAuto(ModItems.grenade.stackFromEnum(AmmoHandGrenade.CLOUD), new Object[] { "SPS", "CAC", "SPS", 'S', S.dust(), 'P', ModItems.powder_poison, 'C', CU.dust(), 'A', new ItemStack(ModItems.fluid_tank_full, 1, Fluids.ACID.getID()) });
		CraftingManager.addRecipeAuto(ModItems.grenade.stackFromEnum(AmmoHandGrenade.PINK_CLOUD), new Object[] { " S ", "ECE", " E ", 'S', ModItems.powder_spark_mix, 'E', ModItems.powder_magic, 'C', ModItems.grenade.stackFromEnum(AmmoHandGrenade.CLOUD) });
		CraftingManager.addRecipeAuto(ModItems.grenade.stackFromEnum(AmmoHandGrenade.WASTE_PEARL), new Object[] { "WWW", "WFW", "WWW", 'W', ModItems.nuclear_waste_tiny, 'F', ModBlocks.block_fallout });
		CraftingManager.addShapelessAuto(ModItems.grenade.stackFromEnum(AmmoHandGrenade.KYIV), new Object[] { ModItems.canister_napalm, ModItems.bottle2_empty, ModItems.rag });
		
		//Sticks of explosives
		CraftingManager.addRecipeAuto(ModItems.grenade.stackFromEnum(4, AmmoHandGrenade.DYNAMITE), new Object[] { " S ", "PDP", "PDP", 'S', ModItems.safety_fuse, 'P', Items.paper, 'D', ModItems.ball_dynamite });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.stick_tnt, 4), new Object[] { " S ", "PDP", "PDP", 'S', ModBlocks.det_cord, 'P', Items.paper, 'D', ModItems.ball_tnt });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.stick_semtex, 4), new Object[] { " S ", "PDP", "PDP", 'S', ModBlocks.det_cord, 'P', Items.paper, 'D', ModItems.ingot_semtex });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.stick_c4, 4), new Object[] { " S ", "PDP", "PDP", 'S', ModBlocks.det_cord, 'P', Items.paper, 'D', ModItems.ingot_c4 });
		
		//Blocks of explosives
		CraftingManager.addRecipeAuto(new ItemStack(ModBlocks.dynamite, 1), new Object[] { "DDD", "DSD", "DDD", 'D', ModItems.grenade.stackFromEnum(AmmoHandGrenade.DYNAMITE), 'S', ModItems.safety_fuse });
		CraftingManager.addRecipeAuto(new ItemStack(ModBlocks.tnt, 1), new Object[] { "DDD", "DSD", "DDD", 'D', ModItems.stick_tnt, 'S', ModItems.safety_fuse });
		CraftingManager.addRecipeAuto(new ItemStack(ModBlocks.semtex, 1), new Object[] { "DDD", "DSD", "DDD", 'D', ModItems.stick_semtex, 'S', ModItems.safety_fuse });
		CraftingManager.addRecipeAuto(new ItemStack(ModBlocks.c4, 1), new Object[] { "DDD", "DSD", "DDD", 'D', ModItems.stick_c4, 'S', ModItems.safety_fuse });

		
		//IF Grenades
		final ItemStack ifGenericGrenade = ModItems.grenade.stackFromEnum(AmmoHandGrenade.IF_GENERIC);
		CraftingManager.addRecipeAuto(ifGenericGrenade, new Object[] { "CA ", "PTP", " P ", 'C', ModItems.wire_tungsten, 'P', STEEL.plate(), 'T', ANY_HIGHEXPLOSIVE.ingot(), 'A', AL.dust() });
		CraftingManager.addRecipeAuto(ModItems.grenade.stackFromEnum(AmmoHandGrenade.IF_HE), new Object[] { "AGP", 'G', ifGenericGrenade, 'A', ANY_HIGHEXPLOSIVE.ingot(), 'P', AL.dust() });
		CraftingManager.addRecipeAuto(ModItems.grenade.stackFromEnum(AmmoHandGrenade.IF_BOUNCY), new Object[] { "G", "A", 'G', ifGenericGrenade, 'A', ModItems.plate_polymer });
		CraftingManager.addRecipeAuto(ModItems.grenade.stackFromEnum(AmmoHandGrenade.IF_STICKY), new Object[] { "G", "A", 'G', ifGenericGrenade, 'A', KEY_SLIME });
		CraftingManager.addRecipeAuto(ModItems.grenade.stackFromEnum(AmmoHandGrenade.IF_IMPACT), new Object[] { "G", "A", 'G', ifGenericGrenade, 'A', REDSTONE.dust() });
		CraftingManager.addRecipeAuto(ModItems.grenade.stackFromEnum(AmmoHandGrenade.IF_CONCUSSION), new Object[] { "G", "A", 'G', ifGenericGrenade, 'A', Items.glowstone_dust });
		CraftingManager.addRecipeAuto(ModItems.grenade.stackFromEnum(AmmoHandGrenade.IF_TOXIC), new Object[] { "G", "A", 'G', ifGenericGrenade, 'A', ModItems.powder_poison });
		CraftingManager.addRecipeAuto(ModItems.grenade.stackFromEnum(AmmoHandGrenade.IF_INCENDIARY), new Object[] { "G", "A", 'G', ifGenericGrenade, 'A', P_RED.dust() });
		CraftingManager.addRecipeAuto(ModItems.grenade.stackFromEnum(AmmoHandGrenade.IF_BRIMSTONE), new Object[] { "R", "G", "A", 'G', ifGenericGrenade, 'R', REDSTONE.dust(), 'A', S.dust() });
		CraftingManager.addRecipeAuto(ModItems.grenade.stackFromEnum(AmmoHandGrenade.IF_MYSTERY), new Object[] { "A", "G", "A", 'G', ifGenericGrenade, 'A', ModItems.powder_magic });
		CraftingManager.addRecipeAuto(ModItems.grenade.stackFromEnum(AmmoHandGrenade.IF_SPARK), new Object[] { " A ", "AGA", " A ", 'G', ifGenericGrenade, 'A', ModItems.powder_spark_mix });
		CraftingManager.addRecipeAuto(ModItems.grenade.stackFromEnum(AmmoHandGrenade.IF_HOPWIRE), new Object[] { " A ", "AGA", " A ", 'G', ifGenericGrenade, 'A', ModItems.powder_power });
		CraftingManager.addRecipeAuto(ModItems.grenade.stackFromEnum(AmmoHandGrenade.IF_NULL), new Object[] { "BAB", "AGA", "BAB", 'G', ifGenericGrenade, 'A', Blocks.obsidian, 'B', BIGMT.ingot() });

		//Mines
		CraftingManager.addRecipeAuto(new ItemStack(ModBlocks.mine_ap, 4), new Object[] { "C", "P", "T", 'C', ModItems.circuit_targeting_tier2, 'P', FE.plate(), 'T', ModItems.ingot_semtex });
		CraftingManager.addRecipeAuto(new ItemStack(ModBlocks.mine_he, 1), new Object[] { " C ", "PTP", 'C', ModItems.circuit_targeting_tier2, 'P', STEEL.plate(), 'T', ModItems.ingot_semtex });
		CraftingManager.addRecipeAuto(new ItemStack(ModBlocks.mine_shrap, 2), new Object[] { "LLL", " C ", "PTP", 'C', ModItems.circuit_targeting_tier2, 'P', STEEL.plate(), 'T', ModBlocks.det_cord, 'L', ModItems.pellet_buckshot });
		CraftingManager.addRecipeAuto(new ItemStack(ModBlocks.mine_fat, 1), new Object[] { "CDN", 'C', ModItems.circuit_targeting_tier2, 'D', ModItems.ducttape, 'N', ModItems.ammo_nuke });
		
		//Nuke parts
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.n2_charge, 1), new Object[] { " D ", "ERE", " D ", 'D', ModItems.ducttape, 'E', ModBlocks.det_charge, 'R', REDSTONE.block() });

		//Custom nuke rods
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.custom_tnt, 1), new Object[] { " C ", "TIT", "TIT", 'C', CU.plate(), 'I', FE.plate(), 'T', Blocks.tnt });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.custom_nuke, 1), new Object[] { " C ", "LUL", "LUL", 'C', CU.plate(), 'L', PB.plate(), 'U', U235.ingot() });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.custom_hydro, 1), new Object[] { " C ", "LTL", "LIL", 'C', CU.plate(), 'L', PB.plate(), 'I', FE.plate(), 'T', ModItems.cell_tritium });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.custom_amat, 1), new Object[] { " C ", "MMM", "AAA", 'C', CU.plate(), 'A', AL.plate(), 'M', ModItems.cell_antimatter });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.custom_dirty, 1), new Object[] { " C ", "WLW", "WLW", 'C', CU.plate(), 'L', PB.plate(), 'W', ModItems.nuclear_waste });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.custom_schrab, 1), new Object[] { " C ", "LUL", "LUL", 'C', CU.plate(), 'L', PB.plate(), 'U', SA326.ingot() });

		CraftingManager.addRecipeAuto(new ItemStack(ModBlocks.lamp_demon, 1), new Object[] { " D ", "S S", 'D', ModItems.demon_core_closed, 'S', STEEL.ingot() });

		CraftingManager.addRecipeAuto(new ItemStack(ModItems.crucible, 1, 3), new Object[] { "MEM", "YDY", "YCY", 'M', ModItems.ingot_meteorite_forged, 'E', EUPH.ingot(), 'Y', ModItems.billet_yharonite, 'D', ModItems.demon_core_closed, 'C', ModItems.ingot_chainsteel });
	}
}