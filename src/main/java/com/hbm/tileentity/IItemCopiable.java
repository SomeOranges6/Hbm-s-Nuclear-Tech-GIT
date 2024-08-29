package com.hbm.tileentity;

import com.hbm.interfaces.ICopiable;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public interface IItemCopiable extends ICopiable {

	/**
	 * Used for the copy tool
	 * @return The start and end (start inclusive, end exclusive) of the slots to copy
	 */
	int[] getCopySlots();

    @Override
	default NBTTagCompound getSettings(World world, int x, int y, int z) {
		IInventory inv = (IInventory) this;
		NBTTagCompound nbt = new NBTTagCompound();
		NBTTagList tags = new NBTTagList();
		int count = 0;
		for (int i = getCopySlots()[0]; i < getCopySlots()[1]; i++) {
			NBTTagCompound slotNBT = new NBTTagCompound();
			if(inv.getStackInSlot(i) != null) {
				slotNBT.setByte("slot", (byte) count);
				inv.getStackInSlot(i).writeToNBT(slotNBT);
				tags.appendTag(slotNBT);
			}
			count++;
		}
		nbt.setTag("itemsToCopy", tags);

		return nbt;
	}

	@Override
	default void pasteSettings(NBTTagCompound nbt, int index, World world, EntityPlayer player, int x, int y, int z) {
		TileEntity tile = (TileEntity) this;
		IInventory inv = (IInventory) this;
		NBTTagList items = nbt.getTagList("itemsToCopy", 10);
		int listSize = items.tagCount();
		if(listSize > 0 && !world.isRemote) {

			int count = 0;
			for (int i = getCopySlots()[0]; i < getCopySlots()[1]; i++) {
				if (count < listSize) {
					NBTTagCompound slotNBT = items.getCompoundTagAt(count);
					byte slot = slotNBT.getByte("slot");
					ItemStack loadedStack = ItemStack.loadItemStackFromNBT(slotNBT);

					if (loadedStack != null && slot < getCopySlots()[1] && player.inventory.hasItemStack(loadedStack)) {
						for (int playerIndex = 0; playerIndex < player.inventory.getSizeInventory(); playerIndex++) {
							ItemStack stackInSlot = player.inventory.getStackInSlot(playerIndex);
							if(stackInSlot != null && loadedStack.isItemEqual(stackInSlot)) {
								player.inventory.decrStackSize(playerIndex, 1);
								ItemStack prevItem= inv.getStackInSlot(i);
								if(prevItem != null){
								  if(!player.inventory.addItemStackToInventory(prevItem)){
									  EntityItem item = new EntityItem(world, x + 0.5, y + 2, z + 0.5, prevItem);
									  world.spawnEntityInWorld(item);
								  }
								}
								inv.setInventorySlotContents(slot + getCopySlots()[0], ItemStack.loadItemStackFromNBT(slotNBT));
								break;
							}
						}
					}
				}
				count++;
			}
			tile.getWorldObj().markTileEntityChunkModified(tile.xCoord, tile.yCoord, tile.zCoord, tile);

		}
	}

	@Override
	default String[] infoForDisplay(World world, int x, int y, int z) {
		return new String[] { "copytool.filter" };
	}
}
