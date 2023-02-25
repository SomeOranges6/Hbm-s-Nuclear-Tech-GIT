package com.hbm.tileentity.bomb;

import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.bomb.NukeCustom;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.inventory.RecipesCommon.ComparableStack;
import com.hbm.inventory.RecipesCommon.NbtComparableStack;
import com.hbm.items.ModItems;
import com.hbm.items.special.ItemCell;
import com.hbm.items.tool.ItemFluidCanister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemStackHandler;

import java.util.HashMap;

public class TileEntityNukeCustom extends TileEntity implements ITickable {

    public static HashMap<ComparableStack, CustomNukeEntry> entries = new HashMap<>();
    public ItemStackHandler inventory;
    public float tnt;
    public float nuke;
    public float hydro;
    public float amat;
    public float dirty;
    public float schrab;
    public float euph;
    private String customName;

    public TileEntityNukeCustom() {
        inventory = new ItemStackHandler(27) {
            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
                super.onContentsChanged(slot);
            }
        };
    }

    public static void registerBombItems() {
        entries.put(new ComparableStack(Items.GUNPOWDER), new CustomNukeEntry(EnumBombType.TNT, 0.8F));
        entries.put(new ComparableStack(Blocks.TNT), new CustomNukeEntry(EnumBombType.TNT, 4F));
        entries.put(new ComparableStack(ModBlocks.det_cord), new CustomNukeEntry(EnumBombType.TNT, 1.5F));
        entries.put(new ComparableStack(ModItems.ingot_semtex), new CustomNukeEntry(EnumBombType.TNT, 8F));
        entries.put(new ComparableStack(ModBlocks.det_charge), new CustomNukeEntry(EnumBombType.TNT, 15F));
        entries.put(new NbtComparableStack(ItemFluidCanister.getFullCanister(ModForgeFluids.diesel)), new CustomNukeEntry(EnumBombType.TNT, 0.5F));
        entries.put(new ComparableStack(ModItems.canister_napalm), new CustomNukeEntry(EnumBombType.TNT, 2.5F));
        entries.put(new NbtComparableStack(ItemFluidCanister.getFullCanister(ModForgeFluids.kerosene)), new CustomNukeEntry(EnumBombType.TNT, 0.8F));
        entries.put(new ComparableStack(ModBlocks.red_barrel), new CustomNukeEntry(EnumBombType.TNT, 2.5F));
        entries.put(new ComparableStack(ModBlocks.pink_barrel), new CustomNukeEntry(EnumBombType.TNT, 4F));
        entries.put(new ComparableStack(ModItems.custom_tnt), new CustomNukeEntry(EnumBombType.TNT, 10F));

        entries.put(new ComparableStack(ModItems.ingot_u235), new CustomNukeEntry(EnumBombType.NUKE, 15F));
        entries.put(new ComparableStack(ModItems.ingot_pu239), new CustomNukeEntry(EnumBombType.NUKE, 25F));
        entries.put(new ComparableStack(ModItems.ingot_neptunium), new CustomNukeEntry(EnumBombType.NUKE, 30F));
        entries.put(new ComparableStack(ModItems.nugget_u235), new CustomNukeEntry(EnumBombType.NUKE, 1.5F));
        entries.put(new ComparableStack(ModItems.nugget_pu239), new CustomNukeEntry(EnumBombType.NUKE, 2.5F));
        entries.put(new ComparableStack(ModItems.nugget_neptunium), new CustomNukeEntry(EnumBombType.NUKE, 3.0F));
        entries.put(new ComparableStack(ModItems.powder_neptunium), new CustomNukeEntry(EnumBombType.NUKE, 30F));
        entries.put(new ComparableStack(ModItems.custom_nuke), new CustomNukeEntry(EnumBombType.NUKE, 30F));

        entries.put(new NbtComparableStack(ItemCell.getFullCell(ModForgeFluids.deuterium)), new CustomNukeEntry(EnumBombType.HYDRO, 20F));
        entries.put(new NbtComparableStack(ItemCell.getFullCell(ModForgeFluids.tritium)), new CustomNukeEntry(EnumBombType.HYDRO, 30F));
        entries.put(new ComparableStack(ModItems.lithium), new CustomNukeEntry(EnumBombType.HYDRO, 20F));
        entries.put(new ComparableStack(ModItems.tritium_deuterium_cake), new CustomNukeEntry(EnumBombType.HYDRO, 200F));
        entries.put(new ComparableStack(ModItems.custom_hydro), new CustomNukeEntry(EnumBombType.HYDRO, 30F));

        entries.put(new NbtComparableStack(ItemCell.getFullCell(ModForgeFluids.amat)), new CustomNukeEntry(EnumBombType.AMAT, 5F));
        entries.put(new ComparableStack(ModItems.custom_amat), new CustomNukeEntry(EnumBombType.AMAT, 15F));
        entries.put(new ComparableStack(ModItems.egg_balefire_shard), new CustomNukeEntry(EnumBombType.AMAT, 15F));
        entries.put(new ComparableStack(ModItems.egg_balefire), new CustomNukeEntry(EnumBombType.AMAT, 150F));

        entries.put(new ComparableStack(ModItems.ingot_tungsten), new CustomNukeEntry(EnumBombType.DIRTY, 1F));
        entries.put(new ComparableStack(ModItems.custom_dirty), new CustomNukeEntry(EnumBombType.DIRTY, 10F));

        entries.put(new ComparableStack(ModItems.ingot_schrabidium), new CustomNukeEntry(EnumBombType.SCHRAB, 5F));
        entries.put(new ComparableStack(ModBlocks.block_schrabidium), new CustomNukeEntry(EnumBombType.SCHRAB, 50F));
        entries.put(new ComparableStack(ModItems.nugget_schrabidium), new CustomNukeEntry(EnumBombType.SCHRAB, 0.5F));
        entries.put(new ComparableStack(ModItems.powder_schrabidium), new CustomNukeEntry(EnumBombType.SCHRAB, 5F));
        entries.put(new NbtComparableStack(ItemCell.getFullCell(ModForgeFluids.sas3)), new CustomNukeEntry(EnumBombType.SCHRAB, 7.5F));
        entries.put(new NbtComparableStack(ItemCell.getFullCell(ModForgeFluids.aschrab)), new CustomNukeEntry(EnumBombType.SCHRAB, 15F));
        entries.put(new ComparableStack(ModItems.custom_schrab), new CustomNukeEntry(EnumBombType.SCHRAB, 15F));

        entries.put(new ComparableStack(ModItems.nugget_euphemium), new CustomNukeEntry(EnumBombType.EUPH, 1F));
        entries.put(new ComparableStack(ModItems.ingot_euphemium), new CustomNukeEntry(EnumBombType.EUPH, 1F));

        entries.put(new ComparableStack(Items.REDSTONE), new CustomNukeEntry(EnumBombType.TNT, 1.05F, EnumEntryType.MULT));
        entries.put(new ComparableStack(Blocks.REDSTONE_BLOCK), new CustomNukeEntry(EnumBombType.TNT, 1.5F, EnumEntryType.MULT));

        entries.put(new ComparableStack(ModItems.ingot_uranium), new CustomNukeEntry(EnumBombType.NUKE, 1.05F, EnumEntryType.MULT));
        entries.put(new ComparableStack(ModItems.ingot_plutonium), new CustomNukeEntry(EnumBombType.NUKE, 1.15F, EnumEntryType.MULT));
        entries.put(new ComparableStack(ModItems.ingot_u238), new CustomNukeEntry(EnumBombType.NUKE, 1.1F, EnumEntryType.MULT));
        entries.put(new ComparableStack(ModItems.ingot_pu238), new CustomNukeEntry(EnumBombType.NUKE, 1.15F, EnumEntryType.MULT));
        entries.put(new ComparableStack(ModItems.nugget_uranium), new CustomNukeEntry(EnumBombType.NUKE, 1.005F, EnumEntryType.MULT));
        entries.put(new ComparableStack(ModItems.nugget_plutonium), new CustomNukeEntry(EnumBombType.NUKE, 1.15F, EnumEntryType.MULT));
        entries.put(new ComparableStack(ModItems.nugget_u238), new CustomNukeEntry(EnumBombType.NUKE, 1.01F, EnumEntryType.MULT));
        entries.put(new ComparableStack(ModItems.nugget_pu238), new CustomNukeEntry(EnumBombType.NUKE, 1.015F, EnumEntryType.MULT));
        entries.put(new ComparableStack(ModItems.powder_uranium), new CustomNukeEntry(EnumBombType.NUKE, 1.05F, EnumEntryType.MULT));
        entries.put(new ComparableStack(ModItems.powder_plutonium), new CustomNukeEntry(EnumBombType.NUKE, 1.15F, EnumEntryType.MULT));

        entries.put(new ComparableStack(ModItems.ingot_pu240), new CustomNukeEntry(EnumBombType.DIRTY, 1.05F, EnumEntryType.MULT));
        entries.put(new ComparableStack(ModItems.nuclear_waste), new CustomNukeEntry(EnumBombType.DIRTY, 1.025F, EnumEntryType.MULT));
        entries.put(new ComparableStack(ModBlocks.block_waste), new CustomNukeEntry(EnumBombType.DIRTY, 1.25F, EnumEntryType.MULT));
        entries.put(new ComparableStack(ModBlocks.yellow_barrel), new CustomNukeEntry(EnumBombType.DIRTY, 1.2F, EnumEntryType.MULT));
    }

    public String getInventoryName() {
        return this.hasCustomInventoryName() ? this.customName : "container.nukeCustom";
    }

    public boolean hasCustomInventoryName() {
        return this.customName != null && this.customName.length() > 0;
    }

    public void setCustomName(String name) {
        this.customName = name;
    }

    public boolean isUseableByPlayer(EntityPlayer player) {
        if (world.getTileEntity(pos) != this) {
            return false;
        } else {
            return player.getDistanceSq(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D) <= 64;
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        if (compound.hasKey("inventory"))
            inventory.deserializeNBT(compound.getCompoundTag("inventory"));
        super.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("inventory", inventory.serializeNBT());
        return super.writeToNBT(compound);
    }

    @SuppressWarnings("incomplete-switch")
    @Override
    public void update() {
        float tnt = 0F, tntMod = 1F;
        float nuke = 0F, nukeMod = 1F;
        float hydro = 0F, hydroMod = 1F;
        float amat = 0F, amatMod = 1F;
        float dirty = 0F, dirtyMod = 1F;
        float schrab = 0F, schrabMod = 1F;
        float euph = 0F;

        for (int i = 0; i < inventory.getSlots(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (stack.isEmpty())
                continue;

            ComparableStack comp = new NbtComparableStack(stack).makeSingular();
            CustomNukeEntry ent = entries.get(comp);

            if (ent == null)
                continue;

            if (ent.entry == EnumEntryType.ADD) {

                switch (ent.type) {
                    case TNT:
                        tnt += ent.value;
                        break;
                    case NUKE:
                        nuke += ent.value;
                        break;
                    case HYDRO:
                        hydro += ent.value;
                        break;
                    case AMAT:
                        amat += ent.value;
                        break;
                    case DIRTY:
                        dirty += ent.value;
                        break;
                    case SCHRAB:
                        schrab += ent.value;
                        break;
                    case EUPH:
                        euph += ent.value;
                        break;
                }

            } else if (ent.entry == EnumEntryType.MULT) {

                switch (ent.type) {
                    case TNT:
                        tntMod *= ent.value;
                        break;
                    case NUKE:
                        nukeMod *= ent.value;
                        break;
                    case HYDRO:
                        hydroMod *= ent.value;
                        break;
                    case AMAT:
                        amatMod *= ent.value;
                        break;
                    case DIRTY:
                        dirtyMod *= ent.value;
                        break;
                    case SCHRAB:
                        schrabMod *= ent.value;
                        break;
                }
            }
        }
        tnt *= tntMod;
        nuke *= nukeMod;
        hydro *= hydroMod;
        amat *= amatMod;
        dirty *= dirtyMod;
        schrab *= schrabMod;

        if (tnt < 16) nuke = 0;
        if (nuke < 100) hydro = 0;
        if (nuke < 50) amat = 0;
        if (nuke < 50) schrab = 0;
        if (schrab == 0) euph = 0;

        this.tnt = tnt;
        this.nuke = nuke;
        this.hydro = hydro;
        this.amat = amat;
        this.dirty = dirty;
        this.schrab = schrab;
        this.euph = euph;
    }

    public float getNukeAdj() {

        if (nuke == 0)
            return 0;

        return Math.min(nuke + tnt / 2, NukeCustom.maxNuke);
    }

    public float getHydroAdj() {

        if (hydro == 0)
            return 0;

        return Math.min(hydro + nuke / 2 + tnt / 4, NukeCustom.maxHydro);
    }

    public float getAmatAdj() {

        if (amat == 0)
            return 0;

        return Math.min(amat + hydro / 2 + nuke / 4 + tnt / 8, NukeCustom.maxAmat);
    }

    public float getSchrabAdj() {

        if (schrab == 0)
            return 0;

        return Math.min(schrab + amat / 2 + hydro / 4 + nuke / 8 + tnt / 16, NukeCustom.maxSchrab);
    }

    public boolean isFalling() {

        for (int i = 0; i < inventory.getSlots(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (stack != null && stack.getItem() == ModItems.custom_fall)
                return true;
        }

        return false;
    }

    public void destruct() {

        clearSlots();
        world.destroyBlock(pos, false);
    }

    public void clearSlots() {
        for (int i = 0; i < inventory.getSlots(); i++) {
            inventory.setStackInSlot(i, ItemStack.EMPTY);
        }
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return TileEntity.INFINITE_EXTENT_AABB;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public double getMaxRenderDistanceSquared() {
        return 65536.0D;
    }

    public enum EnumBombType {
        TNT("TNT"),
        NUKE("Nuclear"),
        HYDRO("Hydrogen"),
        AMAT("Antimatter"),
        DIRTY("Salted"),
        SCHRAB("Schrabidium"),
        EUPH("Anti Mass");

        String name;

        EnumBombType(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public enum EnumEntryType {
        ADD,
        MULT
    }

    public static class CustomNukeEntry {

        public EnumBombType type;
        public EnumEntryType entry;
        public float value;

        public CustomNukeEntry(EnumBombType type, float value) {
            this.type = type;
            this.entry = EnumEntryType.ADD;
            this.value = value;
        }

        public CustomNukeEntry(EnumBombType type, float value, EnumEntryType entry) {
            this(type, value);
            this.entry = entry;
        }
    }
}
