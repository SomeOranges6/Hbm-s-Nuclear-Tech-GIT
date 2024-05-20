package com.hbm.dim.trait;

import java.util.ArrayList;
import java.util.List;

import com.hbm.inventory.fluid.FluidType;
import com.hbm.inventory.fluid.Fluids;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;


public class CBT_Atmosphere extends CelestialBodyTrait {

	public List<FluidEntry> fluids;

	public static class FluidEntry {
		public FluidType fluid;
		public double pressure;

		public FluidEntry(FluidType fluid, double percentage) {
			this.fluid = fluid;
			this.pressure = percentage;
		}
	}

	public CBT_Atmosphere() {
		fluids = new ArrayList<>();
	}

	public CBT_Atmosphere(FluidType fluid, double pressure) {
		fluids = new ArrayList<>();
		fluids.add(new FluidEntry(fluid, pressure));
	}

	public CBT_Atmosphere and(FluidType fluid, double pressure) {
		fluids.add(new FluidEntry(fluid, pressure));
		return this;
	}

	// Fluid must be above at least 1 millbar to be "present"
	public boolean hasFluid(FluidType fluid) {
		return hasFluid(fluid, 0.001);
	}

	public boolean hasFluid(FluidType fluid, double abovePressure) {
		for(FluidEntry entry : fluids) {
			if(entry.fluid == fluid)
				return entry.pressure >= abovePressure;
		}

		return false;
	}

	// Get the highest pressure fluid
	public FluidType getMainFluid() {
		sortDescending();
		FluidEntry first = fluids.get(0);
		return first != null ? first.fluid : Fluids.NONE;
	}

	public void sortDescending() {
		fluids.sort((a, b) -> {
			return Double.compare(b.pressure, a.pressure);
		});
	}

	// FluidEntries store PARTIAL pressure, to get the total atmospheric pressure, use this method
	public double getPressure() {
		double pressure = 0;
		for(FluidEntry entry : fluids) {
			pressure += entry.pressure;
		}

		return pressure;
	}

	public double getPressure(FluidType fluid) {
		for(FluidEntry entry : fluids) {
			if(entry.fluid == fluid) {
				return entry.pressure;
			}
		}
		
		return 0;
	}

	public List<Integer> getFluidColors() {
		List<Integer> colors = new ArrayList<>();
		for (FluidEntry entry : fluids) {
			colors.add(entry.fluid.getColor());
		}
		return colors;
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		NBTTagList fluidList = new NBTTagList();
		for (FluidEntry entry : fluids) {
			NBTTagCompound fluidTag = new NBTTagCompound();
			fluidTag.setInteger("type", entry.fluid.getID());
			fluidTag.setDouble("percentage", entry.pressure);
			fluidList.appendTag(fluidTag);
		}
		nbt.setTag("fluids", fluidList);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		fluids = new ArrayList<>();
		NBTTagList fluidList = nbt.getTagList("fluids", Constants.NBT.TAG_COMPOUND);
		for (int i = 0; i < fluidList.tagCount(); i++) {
			NBTTagCompound fluidTag = fluidList.getCompoundTagAt(i);
			FluidType fluid = Fluids.fromID(fluidTag.getInteger("type"));
			double percentage = fluidTag.getDouble("percentage");
			fluids.add(new FluidEntry(fluid, percentage));
		}
	}

	// These methods are for client syncing, the precision loss is intentional
	@Override
	public void writeToBytes(ByteBuf buf) {
		buf.writeInt(fluids.size());
		for (FluidEntry entry : fluids) {
			buf.writeInt(entry.fluid.getID());
			buf.writeFloat((float)entry.pressure);
		}
	}

	@Override
	public void readFromBytes(ByteBuf buf) {
		int size = buf.readInt();
		fluids = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			FluidType fluid = Fluids.fromID(buf.readInt());
			double percentage = buf.readFloat();
			fluids.add(new FluidEntry(fluid, percentage));
		}
	}
}
