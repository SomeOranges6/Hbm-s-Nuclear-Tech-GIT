package com.hbm.tileentity.machine;

import com.hbm.blocks.machine.Radiobox;
import com.hbm.interfaces.IConsumer;
import com.hbm.lib.ModDamageSource;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityRadiobox extends TileEntity implements ITickable, IConsumer {

    public static long maxPower = 500000;
    public boolean infinite = false;
    long power;

    @Override
    public void update() {
        if (!world.isRemote && world.getBlockState(pos).getValue(Radiobox.STATE) && (power >= 25000 || infinite)) {
            if (!infinite) {
                power -= 25000;
                this.markDirty();
            }
            int range = 15;

            world.getEntitiesWithinAABB(EntityMob.class, new AxisAlignedBB(pos.getX() - range, pos.getY() - range, pos.getZ() - range, pos.getX() + range, pos.getY() + range, pos.getZ() + range)).forEach(e -> e.attackEntityFrom(ModDamageSource.enervation, 20.0F));
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        power = compound.getLong("power");
        infinite = compound.getBoolean("infinite");
        super.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setLong("power", power);
        compound.setBoolean("infinite", infinite);
        return super.writeToNBT(compound);
    }

    @Override
    public long getPower() {
        return power;
    }

    @Override
    public void setPower(long i) {
        power = i;
    }

    @Override
    public long getMaxPower() {
        return maxPower;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public double getMaxRenderDistanceSquared() {
        return 65536.0D;
    }

}
