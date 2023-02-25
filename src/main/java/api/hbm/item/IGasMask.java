package api.hbm.item;

import com.hbm.util.ArmorRegistry.HazardClass;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public interface IGasMask {
    /**
     * Returns a list of HazardClasses which can not be protected against by this mask (e.g. chlorine gas for half masks)
     *
     * @param stack
     * @param entity
     * @return an empty list if there's no blacklist
     */
    ArrayList<HazardClass> getBlacklist(ItemStack stack, EntityLivingBase entity);

    /**
     * Returns the loaded filter, if there is any
     *
     * @param stack
     * @param entity
     * @return null if no filter is installed
     */
    ItemStack getFilter(ItemStack stack, EntityLivingBase entity);

    /**
     * Checks whether the provided filter can be screwed into the mask, does not take already applied filters into account (those get ejected)
     *
     * @param stack
     * @param entity
     * @param filter
     * @return
     */
    boolean isFilterApplicable(ItemStack stack, EntityLivingBase entity, ItemStack filter);

    /**
     * This will write the filter to the stack's NBT, it ignores any previously installed filter and won't eject those
     *
     * @param stack
     * @param entity
     * @param filter
     */
    void installFilter(ItemStack stack, EntityLivingBase entity, ItemStack filter);

    /**
     * Damages the installed filter, if there is one
     *
     * @param stack
     * @param entity
     * @param damage
     */
    void damageFilter(ItemStack stack, EntityLivingBase entity, int damage);
}
