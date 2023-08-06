package com.hbm.items.tool;

import java.util.List;
import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.entity.logic.EntityWaypoint;
import com.hbm.entity.mob.EntityGlyphid;
import com.hbm.entity.mob.EntityGlyphidNuclear;
import com.hbm.entity.mob.EntityGlyphidScout;
import com.hbm.handler.pollution.PollutionHandler;
import com.hbm.lib.Library;

import com.hbm.util.ChatBuilder;
import com.hbm.world.feature.GlyphidHive;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemWandD extends Item {

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		
		if(world.isRemote)
			return stack;
		
		MovingObjectPosition pos = Library.rayTrace(player, 500, 1, false, true, false);
		
		if(pos != null) {
			
			/*ExplosionVNT vnt = new ExplosionVNT(world, pos.hitVec.xCoord, pos.hitVec.yCoord, pos.hitVec.zCoord, 7);
			vnt.setBlockAllocator(new BlockAllocatorBulkie(60));
			vnt.setBlockProcessor(new BlockProcessorStandard().withBlockEffect(new BlockMutatorBulkie(ModBlocks.block_slag)).setNoDrop());
			vnt.setEntityProcessor(new EntityProcessorStandard());
			vnt.setPlayerProcessor(new PlayerProcessorStandard());
			vnt.setSFX(new ExplosionEffectStandard());
			vnt.explode();*/
			PollutionHandler.incrementPollution(world, pos.blockX, pos.blockY, pos.blockZ, PollutionHandler.PollutionType.SOOT, 30);
			/*
			int nestX = world.rand.nextInt((pos.blockX + 50) - (pos.blockX - 50)) + (pos.blockX - 50);
			int nestZ = world.rand.nextInt((pos.blockZ + 50) - (pos.blockZ - 50)) + (pos.blockZ - 50);
			int nestY = world.getHeightValue(nestX, nestZ);

			if (!(nestY > pos.blockY + 20) && !(nestY < pos.blockY - 20)) {

				Block b = world.getBlock(nestX, nestY - 1, nestZ);

				if (b.isNormalCube() && b != ModBlocks.glyphid_base) {

					if(!world.isRemote) {
						player.addChatMessage(ChatBuilder.start("[").color(EnumChatFormatting.DARK_AQUA)
								.nextTranslation(this.getUnlocalizedName() + ".name").color(EnumChatFormatting.DARK_AQUA)
								.next("] ").color(EnumChatFormatting.DARK_AQUA)
								.next(nestX + ","+ nestY + "," + nestZ).color(EnumChatFormatting.GREEN).flush());
					}

					EntityWaypoint nest = new EntityWaypoint(world);
					nest.setWaypointType(2);
					//nest.setHighPriority();
					nest.setLocationAndAngles(nestX, nestY, nestZ, 0, 0);
					world.spawnEntityInWorld(nest);


					int radius = 8;
					AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(
							pos.hitVec.xCoord - radius,
							pos.hitVec.yCoord - radius,
							pos.hitVec.zCoord - radius,
							pos.hitVec.xCoord + radius,
							pos.hitVec.yCoord + radius,
							pos.hitVec.zCoord + radius);

					List<Entity> bugs = world.getEntitiesWithinAABB(EntityGlyphid.class, bb);
					for (Entity e : bugs) {
						if (e instanceof EntityGlyphid) {
							if(e instanceof EntityGlyphidNuclear){
								((EntityGlyphid) e).setCurrentTask(2, nest);
								assert ((EntityGlyphid) e).getCurrentTask() == 2;
							} else {
								((EntityGlyphid) e).setCurrentTask(4, nest);
							}
						}
					}
				} else {
					if(!world.isRemote) {
						player.addChatMessage(ChatBuilder.start("[").color(EnumChatFormatting.DARK_AQUA)
								.nextTranslation(this.getUnlocalizedName() + ".name").color(EnumChatFormatting.DARK_AQUA)
								.next("] ").color(EnumChatFormatting.DARK_AQUA)
								.next("Normal cube check failed :(").color(EnumChatFormatting.GREEN)
								.next("The culprit:" + b.getUnlocalizedName()).color(EnumChatFormatting.GREEN).flush());

					}
				}
			} else {
				if(!world.isRemote) {
					player.addChatMessage(ChatBuilder.start("[").color(EnumChatFormatting.DARK_AQUA)
							.nextTranslation(this.getUnlocalizedName() + ".name").color(EnumChatFormatting.DARK_AQUA)
							.next("] ").color(EnumChatFormatting.DARK_AQUA)
							.next("Coordinates beyond Y limit").color(EnumChatFormatting.GREEN).flush());
				}
			}

			 */
			//world.newExplosion(null, pos.blockX, pos.blockY, pos.blockZ, 5F, false, false);
			//GlyphidHive.generateBigOrb(world, pos.blockX, pos.blockY, pos.blockZ, new Random());
			/*TimeAnalyzer.startCount("setBlock");
			world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.dirt);
			TimeAnalyzer.startEndCount("getBlock");
			world.getBlock(pos.blockX, pos.blockY, pos.blockZ);
			TimeAnalyzer.endCount();
			TimeAnalyzer.dump();*/
			
			/*TomSaveData data = TomSaveData.forWorld(world);
			data.impact = false;
			data.fire = 0F;
			data.dust = 0F;
			data.markDirty();*/
			
			/*EntityTomBlast tom = new EntityTomBlast(world);
			tom.posX = pos.blockX;
			tom.posY = pos.blockY;
			tom.posZ = pos.blockZ;
			tom.destructionRange = 600;
			world.spawnEntityInWorld(tom);*/
			
			/*EntityNukeTorex torex = new EntityNukeTorex(world);
			torex.setPositionAndRotation(pos.blockX, pos.blockY + 1, pos.blockZ, 0, 0);
			torex.getDataWatcher().updateObject(10, 1.5F);
			world.spawnEntityInWorld(torex);
			EntityTracker entitytracker = ((WorldServer) world).getEntityTracker();
			IntHashMap map = ReflectionHelper.getPrivateValue(EntityTracker.class, entitytracker, "trackedEntityIDs", "field_72794_c");
			EntityTrackerEntry entry = (EntityTrackerEntry) map.lookup(torex.getEntityId());
			entry.blocksDistanceThreshold = 1000;
			world.spawnEntityInWorld(EntityNukeExplosionMK5.statFacNoRad(world, 150, pos.blockX, pos.blockY + 1, pos.blockZ));*/
			
			//DungeonToolbox.generateBedrockOreWithChance(world, world.world.rand, pos.blockX, pos.blockZ, EnumBedrockOre.TITANIUM,	new FluidStack(Fluids.SULFURIC_ACID, 500), 2, 1);
			
			/*EntitySiegeTunneler tunneler = new EntitySiegeTunneler(world);
			tunneler.setPosition(pos.blockX, pos.blockY + 1, pos.blockZ);
			tunneler.onSpawnWithEgg(null);
			world.spawnEntityInWorld(tunneler);*/
			
			//CellularDungeonFactory.meteor.generate(world, x, y, z, world.world.rand);
			
			/*int r = 5;
			
			int x = pos.blockX;
			int y = pos.blockY;
			int z = pos.blockZ;
			for(int i = x - r; i <= x + r; i++) {
				for(int j = y - r; j <= y + r; j++) {
					for(int k = z - r; k <= z + r; k++) {
						if(world.getBlock(i, j, k) == ModBlocks.concrete_super)
							world.getBlock(i, j, k).updateTick(world, i, j, k, world.world.rand);
					}
				}
			}*/
			
			//new Bunker().generate(world, world.world.rand, x, y, z);
			
			/*EntityBlockSpider spider = new EntityBlockSpider(world);
			spider.setPosition(x + 0.5, y, z + 0.5);
			spider.makeBlock(world.getBlock(x, y, z), world.getBlockMetadata(x, y, z));
			world.setBlockToAir(x, y, z);
			world.spawnEntityInWorld(spider);*/
			
			
    		/*NBTTagCompound data = new NBTTagCompound();
    		data.setString("type", "rift");
    		data.setDouble("posX", x);
    		data.setDouble("posY", y + 1);
    		data.setDouble("posZ", z);
    		
    		MainRegistry.proxy.effectNT(data);*/
			
			//new Spaceship().generate_r0(world, world.world.rand, x - 4, y, z - 8);

			//new Ruin001().generate_r0(world, world.world.rand, x, y - 8, z);

			//CellularDungeonFactory.jungle.generate(world, x, y, z, world.world.rand);
			//CellularDungeonFactory.jungle.generate(world, x, y + 4, z, world.world.rand);
			//CellularDungeonFactory.jungle.generate(world, x, y + 8, z, world.world.rand);
			
			//new AncientTomb().build(world, world.world.rand, x, y + 10, z);
			
			//new ArcticVault().trySpawn(world, x, y, z);
			
			/*for(int ix = x - 10; ix <= x + 10; ix++) {
				for(int iz = z - 10; iz <= z + 10; iz++) {

					if(ix % 2 == 0 && iz % 2 == 0) {
						for(int iy = y; iy < y + 4; iy++)
							world.setBlock(ix, iy, iz, ModBlocks.brick_dungeon_flat);
						world.setBlock(ix, y + 4, iz, ModBlocks.brick_dungeon_tile);
					} else if(ix % 2 == 1 && iz % 2 == 1) {
						world.setBlock(ix, y, iz, ModBlocks.reinforced_stone);
						world.setBlock(ix, y + 1, iz, ModBlocks.spikes);
					} else if(world.world.rand.nextInt(3) == 0) {
						for(int iy = y; iy < y + 4; iy++)
							world.setBlock(ix, iy, iz, ModBlocks.brick_dungeon_flat);
						world.setBlock(ix, y + 4, iz, ModBlocks.brick_dungeon_tile);
					} else {
						world.setBlock(ix, y, iz, ModBlocks.reinforced_stone);
						world.setBlock(ix, y + 1, iz, ModBlocks.spikes);
					}
				}
			}*/
		}
		
		return stack;
	}

	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean bool)
	{
		list.add("Used for debugging purposes.");
	}
}
