package com.hbm.dim.duna;

import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.*;

import com.hbm.blocks.ModBlocks;
import com.hbm.dim.ChunkProviderCelestial;
import com.hbm.dim.ExperimentalCaveGenerator;
import com.hbm.dim.duna.biome.BiomeGenBaseDuna;

import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.TerrainGen;
public class ChunkProviderDuna extends ChunkProviderCelestial {

	private ExperimentalCaveGenerator caveGenV2 = new ExperimentalCaveGenerator();

    public ChunkProviderDuna(World world, long seed, boolean hasMapFeatures) {
        super(world, seed, hasMapFeatures);
		stoneBlock = ModBlocks.duna_rock;
        
		caveGenV2 = (ExperimentalCaveGenerator) TerrainGen.getModdedMapGen(caveGenV2, CUSTOM);
        caveGenV2.lavaBlock = ModBlocks.basalt;
    }

    @Override
	public BlockMetaBuffer getChunkPrimer(int x, int z) {
		BlockMetaBuffer buffer = super.getChunkPrimer(x, z);

        if(biomesForGeneration[0] == BiomeGenBaseDuna.dunaLowlands) {
            // BEEG CAVES UNDER THE CANYONS
            this.caveGenV2.func_151539_a(this, worldObj, x, z, buffer.blocks);
        }
		
		return buffer;
	}

}