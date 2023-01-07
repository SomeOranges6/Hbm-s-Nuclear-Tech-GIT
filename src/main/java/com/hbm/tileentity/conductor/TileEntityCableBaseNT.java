package com.hbm.tileentity.conductor;

import api.hbm.energy.IEnergyConductor;
import api.hbm.energy.IPowerNet;
import api.hbm.energy.PowerNet;

import com.hbm.lib.ForgeDirection;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityCableBaseNT extends TileEntity implements ITickable, IEnergyConductor {


	
	protected IPowerNet network;
	
	@Override
	public void update() {
       if(!world.isRemote) {
			
			//we got here either because the net doesn't exist or because it's not valid, so that's safe to assume
			this.setPowerNet(null);
			
			this.connect();
			
			if(this.getPowerNet() == null) {
				this.setPowerNet(new PowerNet().joinLink(this));
			}
		}
	}
	
    protected void connect() {
		
		for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			
			
			TileEntity te = world.getTileEntity(new BlockPos(getPos().getX() + dir.offsetX, getPos().getY() + dir.offsetY, getPos().getZ() + dir.offsetZ));
			
			if(te instanceof IEnergyConductor) {
				
				IEnergyConductor conductor = (IEnergyConductor) te;
				
				if(!conductor.canConnect(dir.getOpposite()))
					continue;
				
				if(this.getPowerNet() == null && conductor.getPowerNet() != null) {
					conductor.getPowerNet().joinLink(this);
				}
				
				if(this.getPowerNet() != null && conductor.getPowerNet() != null && this.getPowerNet() != conductor.getPowerNet()) {
					conductor.getPowerNet().joinNetworks(this.getPowerNet());
				}
			}
		}
	}
    @Override
	public void invalidate() {
		super.invalidate();
		
		if(!world.isRemote) {
			if(this.network != null) {
				this.network.reevaluate();
				this.network = null;
			}
		}
	}
    public boolean canUpdate() {
		return (this.network == null || !this.network.isValid()) && !this.isInvalid();
	}

	@Override
	public boolean canConnect(ForgeDirection dir) {
		return true;
	}

	@Override
	public long getPower() {
		return 0;
	}

	@Override
	public long getMaxPower() {
		return 0;
	}

	@Override
	public void setPowerNet(IPowerNet network) {
		this.network = network;
	}

	@Override
	public long transferPower(long power) {
		
		if(this.network == null)
			return power;
		
		return this.network.transferPower(power);
	}

	@Override
	public IPowerNet getPowerNet() {
		return this.network;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public double getMaxRenderDistanceSquared()
	{
		return 65536.0D;
	}

}
