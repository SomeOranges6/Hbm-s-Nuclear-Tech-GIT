package com.hbm.tileentity.machine;

import api.hbm.energy.IBatteryItem;
import com.hbm.config.VersatileConfig;
import com.hbm.interfaces.IConsumer;
import com.hbm.inventory.MachineRecipes;
import com.hbm.items.ModItems;
import com.hbm.items.machine.ItemCapacitor;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.lib.Library;
import com.hbm.main.MainRegistry;
import com.hbm.sound.AudioWrapper;
import com.hbm.tileentity.TileEntityMachineBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;

public class TileEntityMachineSchrabidiumTransmutator extends TileEntityMachineBase implements ITickable, IConsumer {

    public static final long maxPower = 5000000;
    public static final int processSpeed = 600;
    private static final int[] slots_top = new int[]{0};
    private static final int[] slots_bottom = new int[]{1, 2};
    private static final int[] slots_side = new int[]{3, 2};
    public long power = 0;
    public int process = 0;
    private AudioWrapper audio;
    private long detectPower;

    public TileEntityMachineSchrabidiumTransmutator() {
        super(4);
    }

    @Override
    public String getName() {
        return "container.machine_schrabidium_transmutator";
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack stack) {
        switch (i) {
            case 0:
                if (MachineRecipes.mODE(stack, "ingotUranium"))
                    return true;
                break;
            case 2:
                if (stack.getItem() == ModItems.redcoil_capacitor)
                    return true;
                break;
            case 3:
                if (stack.getItem() instanceof IBatteryItem)
                    return true;
                break;
        }
        return false;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(EnumFacing e) {
        int i = e.ordinal();
        return i == 0 ? slots_bottom : (i == 1 ? slots_top : slots_side);
    }

    @Override
    public boolean canExtractItem(int i, ItemStack stack, int amount) {
        if (i == 2 && stack.getItem() != null && stack.getItem() == ModItems.redcoil_capacitor && ItemCapacitor.getDura(stack) <= 0) {
            return true;
        }
        if (i == 1) {
            return true;
        }

        if (i == 3) {
            return stack.getItem() instanceof IBatteryItem && ((IBatteryItem) stack.getItem()).getCharge(stack) == 0;
        }

        return false;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        power = compound.getLong("power");
        process = compound.getInteger("process");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setLong("power", power);
        compound.setInteger("process", process);
        return super.writeToNBT(compound);
    }

    @Override
    public void update() {
        if (!world.isRemote) {

            power = Library.chargeTEFromItems(inventory, 3, power, maxPower);

            if (canProcess()) {
                process();
            } else {
                process = 0;
            }

            NBTTagCompound data = new NBTTagCompound();
            data.setLong("power", power);
            data.setInteger("progress", process);
            this.networkPack(data, 50);

            detectAndSendChanges();
        } else {
            if (process > 0) {

                if (audio == null) {
                    audio = MainRegistry.proxy.getLoopedSound(HBMSoundHandler.tauChargeLoop, SoundCategory.BLOCKS, pos.getX(), pos.getY(), pos.getZ(), 1.0F, 1.0F);
                    audio.startSound();
                }
            } else {

                if (audio != null) {
                    audio.stopSound();
                    audio = null;
                }
            }
        }
    }

    @Override
    public void onChunkUnload() {
        if (audio != null) {
            audio.stopSound();
            audio = null;
        }
    }

    @Override
    public void invalidate() {
        super.invalidate();
        if (audio != null) {
            audio.stopSound();
            audio = null;
        }
    }

    @Override
    public void networkUnpack(NBTTagCompound data) {
        this.power = data.getLong("power");
        this.process = data.getInteger("progress");
    }

    private void detectAndSendChanges() {
        boolean mark = false;
        if (detectPower != power) {
            mark = true;
            detectPower = power;
        }
        if (mark)
            markDirty();
    }

    public long getPowerScaled(long i) {
        return (power * i) / maxPower;
    }

    public int getProgressScaled(int i) {
        return (process * i) / processSpeed;
    }

    public boolean canProcess() {
        return power >= 4990000 && MachineRecipes.mODE(inventory.getStackInSlot(0), "ingotUranium")
                && inventory.getStackInSlot(2).getItem() == ModItems.redcoil_capacitor
                && ItemCapacitor.getDura(inventory.getStackInSlot(2)) > 0
                && (inventory.getStackInSlot(1).isEmpty() || (inventory.getStackInSlot(1).getItem() == VersatileConfig.getTransmutatorItem()
                && inventory.getStackInSlot(1).getCount() < inventory.getStackInSlot(1).getMaxStackSize()));
    }

    public boolean isProcessing() {
        return process > 0;
    }

    public void process() {
        process++;

        if (process >= processSpeed) {

            power = 0;
            process = 0;

            inventory.getStackInSlot(0).shrink(1);
            if (inventory.getStackInSlot(0).isEmpty()) {
                inventory.setStackInSlot(0, ItemStack.EMPTY);
            }

            if (inventory.getStackInSlot(1).isEmpty()) {
                inventory.setStackInSlot(1, new ItemStack(VersatileConfig.getTransmutatorItem()));
            } else {
                inventory.getStackInSlot(1).grow(1);
            }
            if (!inventory.getStackInSlot(2).isEmpty()) {
                ItemCapacitor.setDura(inventory.getStackInSlot(2), ItemCapacitor.getDura(inventory.getStackInSlot(2)) - 1);
            }

            this.world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_LIGHTNING_THUNDER, SoundCategory.BLOCKS, 10000.0F, 0.8F + world.rand.nextFloat() * 0.2F);
        }
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

}
