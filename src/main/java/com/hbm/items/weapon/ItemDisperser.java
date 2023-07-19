package com.hbm.items.weapon;

import com.hbm.entity.grenade.EntityDisperserCanister;
import com.hbm.inventory.fluid.FluidType;
import com.hbm.inventory.fluid.Fluids;
import com.hbm.items.machine.ItemFluidTank;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class ItemDisperser extends ItemFluidTank {
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {

        if (!player.capabilities.isCreativeMode) {
            --stack.stackSize;
        }

        world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!world.isRemote) {
            EntityDisperserCanister canister = new EntityDisperserCanister(world, player);

            canister.setType(Item.getIdFromItem(this));
            canister.setFluid(stack.getItemDamage());
            world.spawnEntityInWorld(canister);

        }

        return stack;
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tabs, List list) {

        FluidType[] order = Fluids.getInNiceOrder();
        for(int i = 1; i < order.length; ++i) {
            FluidType type = order[i];

           if(type.isDispersable()) {
                int id = type.getID();
                list.add(new ItemStack(item, 1, id));
            }

        }
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister p_94581_1_) {
        super.registerIcons(p_94581_1_);
        this.overlayIcon = p_94581_1_.registerIcon("hbm:disperser_canister_overlay");

    }
}
