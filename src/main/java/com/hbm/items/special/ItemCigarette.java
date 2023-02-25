/**
 * package com.hbm.items.special;
 * <p>
 * import java.util.List;
 * <p>
 * import com.hbm.capability.HbmLivingProps;
 * import com.hbm.packet.AuxParticlePacketNT;
 * import com.hbm.packet.PacketDispatcher;
 * <p>
 * import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
 * import net.minecraftforge.fml.relauncher.Side;
 * import net.minecraftforge.fml.relauncher.SideOnly;
 * import net.minecraft.entity.player.EntityPlayer;
 * import net.minecraft.item.EnumAction;
 * import net.minecraft.item.Item;
 * import net.minecraft.item.ItemStack;
 * import net.minecraft.nbt.NBTTagCompound;
 * import net.minecraft.util.text.TextFormatting;
 * import net.minecraft.world.World;
 * import net.minecraftforge.event.entity.player.ArrowNockEvent;
 * <p>
 * public class ItemCigarette extends Item  {
 *
 * @Override public EnumAction getItemUseAction(ItemStack stack) {
 * return EnumAction.BOW;
 * }
 * @Override public int getMaxItemUseDuration(ItemStack stack) {
 * return 30;
 * }
 * @Override public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
 * player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
 * return stack;
 * }
 * @Override public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
 * stack.stackSize--;
 * <p>
 * if(!world.isRemote) {
 * HbmLivingProps.incrementBlackLung(player, 2000);
 * HbmLivingProps.incrementAsbestos(player, 2000);
 * HbmLivingProps.incrementRadiation(player, 100F);
 * <p>
 * world.playSound(player.posX, player.posY, player.posZ, "hbm:player.cough", 1.0F, 1.0F);
 * <p>
 * NBTTagCompound nbt = new NBTTagCompound();
 * nbt.setString("type", "vomit");
 * nbt.setString("mode", "smoke");
 * nbt.setInteger("count", 30);
 * nbt.setInteger("entity", player.getEntityId());
 * PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(nbt, 0, 0, 0),  new TargetPoint(player.dimension, player.posX, player.posY, player.posZ, 25));
 * }
 * <p>
 * return stack;
 * }
 * @Override
 * @SideOnly(Side.CLIENT) public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean ext) {
 * list.add(TextFormatting.RED + "✓ Asbestos filter");
 * list.add(TextFormatting.RED + "✓ High in tar");
 * list.add(TextFormatting.RED + "✓ Tobacco contains 100% Polonium-210");
 * list.add(TextFormatting.RED + "✓ Yum");
 * }
 * }
 */