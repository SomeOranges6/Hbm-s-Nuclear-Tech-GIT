package com.hbm.entity.logic;

import java.util.ArrayList;
import java.util.List;

import com.hbm.entity.logic.IChunkLoader;
import com.hbm.main.MainRegistry;

import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.ForgeChunkManager.Type;
import net.minecraft.util.math.ChunkPos;

import com.hbm.interfaces.IEnergyHandler;
import com.hbm.packet.PacketDispatcher;
import com.hbm.packet.ParticleBurstPacket;



import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;

public class EntityEMP extends Entity implements IChunkLoader {

	List<BlockPos> machines;
	int life = 10 * 60 * 20;
	private Ticket loaderTicket;

	public EntityEMP(World p_i1582_1_) {
		super(p_i1582_1_);
	}
	
	@Override
	public void onUpdate() {
		
		if(!world.isRemote) {
			if(machines == null) {
				allocate();
			} else {
				shock();
			}
			
			if(this.ticksExisted > life)
				this.setDead();
		}
	}
	
	private void allocate() {
		
		machines = new ArrayList<BlockPos>();
		
		int radius = 100;
		
		for(int x = -radius; x <= radius; x++) {
			
			int x2 = (int) Math.pow(x, 2);
			
			for(int y = -radius; y <= radius; y++) {
				
				int y2 = (int) Math.pow(y, 2);
				
				for(int z = -radius; z <= radius; z++) {
					
					int z2 = (int) Math.pow(z, 2);
					
					if(Math.sqrt(x2 + y2 + z2) <= radius) {
						add(new BlockPos((int)posX + x, (int)posY + y, (int)posZ + z));
					}
				}
			}
		}
	}
	
	private void shock() {
		
		for(BlockPos pos : machines) {
			emp(pos);
		}
	}
	
	private void add(BlockPos pos) {
		TileEntity te = world.getTileEntity(pos);
	
		 if(te.hasCapability(CapabilityEnergy.ENERGY, null)){
			machines.add(pos);
		}
	}
	
	private void emp(BlockPos pos) {
		
		TileEntity te = world.getTileEntity(pos);
		if(te == null)
			return;
		boolean flag = false;
		if(te != null && te.hasCapability(CapabilityEnergy.ENERGY, null)){
			IEnergyStorage handle = te.getCapability(CapabilityEnergy.ENERGY, null);
			handle.extractEnergy(handle.getEnergyStored(), false);
			flag = true;
		}
		
		if(flag && rand.nextInt(20) == 0) {
			
			PacketDispatcher.wrapper.sendToAll(new ParticleBurstPacket(pos.getX(), pos.getY(), pos.getZ(), Block.getIdFromBlock(Blocks.STAINED_GLASS), 3));
	        
		}
		
	}

	@Override
	protected void entityInit() {
		init(ForgeChunkManager.requestTicket(MainRegistry.instance, world, Type.ENTITY));
	}

	@Override
	public void init(Ticket ticket) {
		if(!world.isRemote) {
			
            if(ticket != null) {
            	
                if(loaderTicket == null) {
                	
                	loaderTicket = ticket;
                	loaderTicket.bindEntity(this);
                	loaderTicket.getModData();
                }

                ForgeChunkManager.forceChunk(loaderTicket, new ChunkPos(chunkCoordX, chunkCoordZ));
            }
        }
	}

	List<ChunkPos> loadedChunks = new ArrayList<ChunkPos>();
	@Override
	public void loadNeighboringChunks(int newChunkX, int newChunkZ) {
		if(!world.isRemote && loaderTicket != null)
        {
            for(ChunkPos chunk : loadedChunks)
            {
                ForgeChunkManager.unforceChunk(loaderTicket, chunk);
            }

            loadedChunks.clear();
            loadedChunks.add(new ChunkPos(newChunkX, newChunkZ));
            loadedChunks.add(new ChunkPos(newChunkX + 1, newChunkZ + 1));
            loadedChunks.add(new ChunkPos(newChunkX - 1, newChunkZ - 1));
            loadedChunks.add(new ChunkPos(newChunkX + 1, newChunkZ - 1));
            loadedChunks.add(new ChunkPos(newChunkX - 1, newChunkZ + 1));
            loadedChunks.add(new ChunkPos(newChunkX + 1, newChunkZ));
            loadedChunks.add(new ChunkPos(newChunkX, newChunkZ + 1));
            loadedChunks.add(new ChunkPos(newChunkX - 1, newChunkZ));
            loadedChunks.add(new ChunkPos(newChunkX, newChunkZ - 1));

            for(ChunkPos chunk : loadedChunks)
            {
                ForgeChunkManager.forceChunk(loaderTicket, chunk);
            }
        }
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) { }

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) { }
}
