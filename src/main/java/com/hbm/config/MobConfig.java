package com.hbm.config;

import net.minecraftforge.common.config.Configuration;

public class MobConfig {

	public static boolean enableMaskman = true;
	public static int maskmanDelay = 60 * 60 * 60;
	public static int maskmanChance = 3;
	public static int maskmanMinRad = 50;
	public static boolean maskmanUnderground = true;
	
	public static boolean enableRaids = false;
	public static int raidDelay = 30 * 60 * 60;
	public static int raidChance = 3;
	public static int raidAmount = 15;
	public static int raidDrones = 5;
	public static int raidAttackDelay = 40;
	public static int raidAttackReach = 2;
	public static int raidAttackDistance = 32;

	public static boolean enableElementals = true;
	public static int elementalDelay = 30 * 60 * 60;
	public static int elementalChance = 2;
	public static int elementalAmount = 10;
	public static int elementalDistance = 32;

	public static boolean enableDucks = true;
	public static boolean enableMobGear = true;
	
	public static boolean enableHives = true;
	public static int hiveSpawn = 256;
	public static double scoutThreshold = 0.1;

	public static int baseSwarmSize = 5;
    public static double swarmScalingMult = 1.5;
	public static int sootStep = 50;

	public static double advancedThreshold = 20;
	public static double bossThreshold = 100;
	public static int[] glyphidChance = {50, -40};
	public static int[] brawlerChance = {20, 40};
	public static int[] bombardierChance = {20, -15};
	public static int[] blasterChance = {10, 40};
	public static int[] behemothChance = {6, 40};
	public static int[] brendaChance = {-3, 20};
	public static int[] johnsonChance = {-3, 15};

	public static double spawnMax = 50;
	public static boolean enableInfestation = true;
	public static double baseInfestChance = 5;
	public static double targetingThreshold = 1;
	
	
	public static void loadFromConfig(Configuration config) {

		final String CATEGORY = CommonConfig.CATEGORY_MOBS;

		enableMaskman = CommonConfig.createConfigBool(config, CATEGORY, "12.M00_enableMaskman", "Whether mask man should spawn", true);
		maskmanDelay = CommonConfig.createConfigInt(config, CATEGORY, "12.M01_maskmanDelay", "How many world ticks need to pass for a check to be performed", 60 * 60 * 60);
		maskmanChance = CommonConfig.createConfigInt(config, CATEGORY, "12.M02_maskmanChance", "1:x chance to spawn mask man, must be at least 1", 3);
		maskmanMinRad = CommonConfig.createConfigInt(config, CATEGORY, "12.M03_maskmanMinRad", "The amount of radiation needed for mask man to spawn", 50);
		maskmanUnderground = CommonConfig.createConfigBool(config, CATEGORY, "12.M04_maskmanUnderound", "Whether players need to be underground for mask man to spawn", true);

		enableRaids = CommonConfig.createConfigBool(config, CATEGORY, "12.F00_enableFBIRaids", "Whether there should be FBI raids", false);
		raidDelay = CommonConfig.createConfigInt(config, CATEGORY, "12.F01_raidDelay", "How many world ticks need to pass for a check to be performed", 30 * 60 * 60);
		raidChance = CommonConfig.createConfigInt(config, CATEGORY, "12.F02_raidChance", "1:x chance to spawn a raid, must be at least 1", 3);
		raidAmount = CommonConfig.createConfigInt(config, CATEGORY, "12.F03_raidAmount", "How many FBI agents are spawned each raid", 15);
		raidAttackDelay = CommonConfig.createConfigInt(config, CATEGORY, "12.F04_raidAttackDelay", "Time between individual attempts to break machines", 40);
		raidAttackReach = CommonConfig.createConfigInt(config, CATEGORY, "12.F05_raidAttackReach", "How far away machines can be broken", 2);
		raidAttackDistance = CommonConfig.createConfigInt(config, CATEGORY, "12.F06_raidAttackDistance", "How far away agents will spawn from the targeted player", 32);
		raidDrones = CommonConfig.createConfigInt(config, CATEGORY, "12.F07_raidDrones", "How many quadcopter drones are spawned each raid", 5);

		enableElementals = CommonConfig.createConfigBool(config, CATEGORY, "12.E00_enableMeltdownElementals", "Whether there should be radiation elementals", true);
		elementalDelay = CommonConfig.createConfigInt(config, CATEGORY, "12.E01_elementalDelay", "How many world ticks need to pass for a check to be performed", 30 * 60 * 60);
		elementalChance = CommonConfig.createConfigInt(config, CATEGORY, "12.E02_elementalChance", "1:x chance to spawn elementals, must be at least 1", 2);
		elementalAmount = CommonConfig.createConfigInt(config, CATEGORY, "12.E03_elementalAmount", "How many elementals are spawned each raid", 10);
		elementalDistance = CommonConfig.createConfigInt(config, CATEGORY, "12.E04_elementalAttackDistance", "How far away elementals will spawn from the targeted player", 32);
		
		enableDucks = CommonConfig.createConfigBool(config, CATEGORY, "12.D00_enableDucks", "Whether pressing O should allow the player to duck", true);
		enableMobGear = CommonConfig.createConfigBool(config, CATEGORY, "12.D01_enableMobGear", "Whether zombies and skeletons should have additional gear when spawning", true);

		enableHives = CommonConfig.createConfigBool(config, CATEGORY, "12.G00_enableHives", "Whether glyphid hives should spawn", true);
		hiveSpawn = CommonConfig.createConfigInt(config, CATEGORY, "12.G01_hiveSpawn", "The average amount of chunks per hive", 256);
		scoutThreshold = CommonConfig.createConfigDouble(config, CATEGORY, "12.G02_scoutThreshold", "Minimum amount of soot for scouts to spawn", 0.1);
		spawnMax = CommonConfig.createConfigDouble(config, CATEGORY, "12.G07_spawnMax", "Maximum amount of glyphids being able to exist at once through natural spawning", 50);
		targetingThreshold = CommonConfig.createConfigDouble(config, CATEGORY, "12.G08_targetingThreshold", "Minimum amount of soot required for glyphids' extended targeting range to activate", 1D);

		//Infested structures
		enableInfestation= CommonConfig.createConfigBool(config, CATEGORY, "12.I01_enableInfestation", "Whether structures infested with glyphids should spawn", true);
		baseInfestChance = CommonConfig.createConfigDouble(config, CATEGORY, "12.I02_baseInfestChance", "The base chance for infested structures to spawn", 5);

		//Glyphid spawn stuff
		config.addCustomCategoryComment(CATEGORY,
				"General Glyphid spawn logic configuration\n"
						+ "\n"
						+ "The chances work in a simple way:\n"
						+ "The base chance is the stock chance of the bug to spawn within a swarm, unaffected by soot\n"
						+ "As soot increases, the spawn rate of the bug increases until it reaches a limit determined by the modifier\n"
						+ "If the default chance is negative, the mob will not spawn by default, and the lower it is,\n"
		                + "The longer it takes for the modifier to make it positive\n"
		                + "If the Modifier is negative, the bug will spawn less often in swarms,\n"
						+ "And its place will be taken over by another one.\n"
		                + "\n"
						+ "The formula for glyphid spawning is: (chance + (modifier - modifier / (soot+1)))"
						+ "The formula for glyphid swarm scaling is: (baseSwarmSize * Math.max(swarmScalingMult * soot/sootStep, 1))");


		baseSwarmSize =  CommonConfig.createConfigInt(config, CATEGORY, "12.GS01_baseSwarmSize", "The basic, soot-less swarm size", 5);
		swarmScalingMult =  CommonConfig.createConfigDouble(config, CATEGORY, "12.GS02_swarmScalingMult", "By how much should swarm size scale by per soot amount determined below", 1.5);
		sootStep =  CommonConfig.createConfigInt(config, CATEGORY, "12.GS03_sootStep", "The soot amount the above multiplier applies to the swarm size", 50);
		

		
		glyphidChance = CommonConfig.createConfigIntList(config, CATEGORY, "12.GC01_glyphidChance", "Base Spawn chance and soot modifier for a glyphid grunt", new int[]{50, -40});
		brawlerChance = CommonConfig.createConfigIntList(config, CATEGORY, "12.GC02_brawlerChance", "Base Spawn chance and soot modifier for a glyphid brawler", new int[]{5, 40});
		bombardierChance = CommonConfig.createConfigIntList(config, CATEGORY, "12.GC03_bombardierChance", "Base Spawn chance and soot modifier for a glyphid bombardier", new int[]{20, -15});
		blasterChance = CommonConfig.createConfigIntList(config, CATEGORY, "12.GC04_blasterChance", "Base Spawn chance and soot modifier for a glyphid blaster", new int[]{-15, 40});
		behemothChance = CommonConfig.createConfigIntList(config, CATEGORY, "12.GC05_behemothChance", "Base Spawn chance and soot modifier for a glyphid behemoth", new int[]{-30, 60});
		brendaChance = CommonConfig.createConfigIntList(config, CATEGORY, "12.GC06_brendaChance", "Base Spawn chance and soot modifier for a glyphid brenda", new int[]{-30, 10});
		johnsonChance = CommonConfig.createConfigIntList(config, CATEGORY, "12.GC07_johnsonChance", "Base Spawn chance and soot modifier for Big Man Johnson", new int[]{-30, 10});

	}
}
