package com.hbm.tileentity.machine;

import com.hbm.blocks.machine.MachineDiFurnace;
import com.hbm.inventory.MachineRecipes;
import com.hbm.items.ModItems;
import com.hbm.tileentity.TileEntityMachineBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class TileEntityDiFurnace extends TileEntityMachineBase implements ITickable, ICapabilityProvider {


    public static final int maxPower = 12800;
    public static final int processingSpeed = 400;
    private static final int[] slots_top = new int[]{0};
    private static final int[] slots_bottom = new int[]{3};
    private static final int[] slots_side = new int[]{1};
    public int dualCookTime;
    public int dualPower;
    private int detectDualCookTime;
    private int detectDualPower;

    public TileEntityDiFurnace() {
        super(4);
    }

    private static int getItemPower(ItemStack itemStack) {
        if (itemStack == null) {
            return 0;
        } else {
            Item item = itemStack.getItem();

            if (item == Items.COAL) return 200;
            if (item == Item.getItemFromBlock(Blocks.COAL_BLOCK)) return 2000;
            if (item == Items.LAVA_BUCKET) return 12800;
            if (item == Items.BLAZE_ROD) return 1000;
            if (item == Items.BLAZE_POWDER) return 300;
            if (item == ModItems.lignite) return 150;
            if (item == ModItems.powder_lignite) return 150;
            if (item == ModItems.powder_coal) return 200;
            if (item == ModItems.briquette_lignite) return 200;
            if (MachineRecipes.mODE(itemStack, "fuelCoke")) return 400;
            if (item == ModItems.solid_fuel) return 400;

            return 0;
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        this.dualPower = compound.getInteger("dualPower");
        this.dualCookTime = compound.getInteger("cookTime");
        this.detectDualCookTime = this.dualCookTime;
        this.detectDualPower = this.dualPower;
        if (compound.hasKey("inventory"))
            inventory.deserializeNBT((NBTTagCompound) compound.getTag("inventory"));
        super.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger("dualPower", this.dualPower);
        compound.setInteger("cookTime", this.dualCookTime);
        compound.setTag("inventory", inventory.serializeNBT());
        return super.writeToNBT(compound);
    }

    @Override
    public void update() {
        boolean flag = this.hasPower();

        if (flag && isProcessing()) {
            this.dualPower = this.dualPower - 1;
            if (this.dualPower < 0) {
                this.dualPower = 0;
            }
        }
        if (this.hasItemPower(inventory.getStackInSlot(2))
                && this.dualPower <= (TileEntityDiFurnace.maxPower - TileEntityDiFurnace.getItemPower(inventory.getStackInSlot(2)))) {
            this.dualPower += getItemPower(inventory.getStackInSlot(2));
            if (!inventory.getStackInSlot(2).isEmpty()) {
                ItemStack copy = inventory.getStackInSlot(2).copy();
                inventory.getStackInSlot(2).shrink(1);
                if (inventory.getStackInSlot(2).isEmpty()) {
                    inventory.setStackInSlot(2, copy.getItem().getContainerItem(copy));
                }
            }
        }
        if (flag && canProcess()) {
            dualCookTime++;
            if (this.dualCookTime == TileEntityDiFurnace.processingSpeed) {
                this.dualCookTime = 0;
                this.processItem();
            }
        } else {
            dualCookTime = 0;
        }

        if (!world.isRemote) {
            boolean trigger = !flag || !canProcess() || this.dualCookTime != 0;

            if (!inventory.getStackInSlot(2).isEmpty() && inventory.getStackInSlot(2).getItem() == ModItems.pellet_rtg) {
                if (this.dualPower != maxPower) {
                    this.dualPower = maxPower;
                }
            }

            if (trigger) {
                MachineDiFurnace.updateBlockState(this.dualCookTime > 0, this.world, pos);
            }
        }

        this.detectAndSendChanges();
    }

    public boolean hasItemPower(ItemStack itemStack) {
        return getItemPower(itemStack) > 0;
    }

    @Override
    public String getName() {
        return "container.diFurnace";
    }

    @Override
    public int[] getAccessibleSlotsFromSide(EnumFacing e) {
        int i = e.ordinal();
        return i == 0 ? slots_bottom : (i == 1 ? slots_top : slots_side);
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack stack) {
        return i != 3;
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack itemStack, int amount) {
        return isItemValidForSlot(slot, itemStack);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack itemStack, int amount) {
        return true;
    }

    public boolean isUsableByPlayer(EntityPlayer player) {
        if (world.getTileEntity(pos) != this) {
            return false;
        } else {
            return player.getDistanceSq(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D) <= 64;
        }
    }

    public int getDiFurnaceProgressScaled(int i) {
        return (dualCookTime * i) / processingSpeed;
    }

    public int getPowerRemainingScaled(int i) {
        return (dualPower * i) / maxPower;
    }

    public boolean canProcess() {
        if (inventory.getStackInSlot(0) == null || inventory.getStackInSlot(1) == null) {
            return false;
        }
        ItemStack itemStack = MachineRecipes.getFurnaceProcessingResult(inventory.getStackInSlot(0), inventory.getStackInSlot(1));
        if (itemStack == null) {
            return false;
        }

        if (inventory.getStackInSlot(3) == ItemStack.EMPTY) {
            return true;
        }
        if (inventory.getStackInSlot(3).getItem() != ItemStack.EMPTY.getItem() && !inventory.getStackInSlot(3).isItemEqual(itemStack)) {
            System.out.println(inventory.getStackInSlot(3).getItem());
            return false;
        }

        if (inventory.getStackInSlot(3).getCount() < inventory.getSlotLimit(3) && inventory.getStackInSlot(3).getCount() < inventory.getStackInSlot(3).getMaxStackSize()) {
            return true;
        } else {
            return inventory.getStackInSlot(3).getCount() < itemStack.getMaxStackSize();
        }
    }

    private void processItem() {
        if (canProcess()) {
            ItemStack itemStack = MachineRecipes.getFurnaceProcessingResult(inventory.getStackInSlot(0), inventory.getStackInSlot(1));

            if (inventory.getStackInSlot(3).isEmpty()) {
                inventory.setStackInSlot(3, itemStack.copy());
            } else if (inventory.getStackInSlot(3).isItemEqual(itemStack)) {
                inventory.getStackInSlot(3).grow(itemStack.getCount());
            }

            for (int i = 0; i < 2; i++) {
                if (inventory.getStackInSlot(i).getCount() <= 0) {
                    inventory.setStackInSlot(i, new ItemStack(inventory.getStackInSlot(i).getItem().setFull3D()));
                } else {
                    inventory.getStackInSlot(i).shrink(1);
                }
                if (inventory.getStackInSlot(i).getCount() <= 0) {
                    inventory.setStackInSlot(i, ItemStack.EMPTY);
                }
            }
        }
    }

    public boolean hasPower() {
        return dualPower > 0;
    }

    public boolean isProcessing() {
        return this.dualCookTime > 0;
    }

    private void detectAndSendChanges() {
        boolean mark = false;
        if (detectDualCookTime != dualCookTime) {
            mark = true;
            detectDualCookTime = dualCookTime;
        }
        if (detectDualPower != dualPower) {
            mark = true;
            detectDualPower = dualPower;
        }
        if (mark)
            markDirty();
    }

}
