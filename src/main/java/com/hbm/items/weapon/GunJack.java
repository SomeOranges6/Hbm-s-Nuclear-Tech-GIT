/* package com.hbm.items.weapon;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Multimap;
import com.hbm.entity.projectile.EntityBullet;
import com.hbm.items.ModItems;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class GunJack extends Item {
	Random rand = new Random();
	
	public int dmgMin = 12;
	public int dmgMax = 24;

	public GunJack() {
		
		this.maxStackSize = 1;
	}

	
	@Override
	public void onPlayerStoppedUsing(ItemStack p_77615_1_, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_) {
		int j = this.getMaxItemUseDuration(p_77615_1_) - p_77615_4_;

		ArrowLooseEvent event = new ArrowLooseEvent(p_77615_3_, p_77615_1_, j);
		MinecraftForge.EVENT_BUS.post(event);
		j = event.charge;

		boolean flag = p_77615_3_.capabilities.isCreativeMode
				|| EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, p_77615_1_) > 0;

		if (flag || p_77615_3_.inventory.hasItem(ModItems.ammo_4gauge_quad)) {
			float f = j / 20.0F;
			f = (f * f + f * 2.0F) / 3.0F;

			if (j < 10.0D) {
				return;
			}

			if (j > 10.0F) {
				f = 10.0F;
			}

			p_77615_1_.damageItem(1, p_77615_3_);

			p_77615_2_.playSoundAtEntity(p_77615_3_, "hbm:weapon.shotgunShoot", 1.0F, 1.0F);

			if (flag) { } else {
				p_77615_3_.inventory.consumeInventoryItem(ModItems.ammo_4gauge_quad);
			}

			int k = rand.nextInt(25) + 24;
			
			for(int i = 0; i < k; i++) {

				EntityBullet entityarrow1 = new EntityBullet(p_77615_2_, p_77615_3_, 3.0F);
				entityarrow1.setDamage(dmgMin + rand.nextInt(dmgMax - dmgMin));
				
				if(!p_77615_2_.isRemote)
					p_77615_2_.spawnEntityInWorld(entityarrow1);
			}
		}
	}

	@Override
	public ItemStack onEaten(ItemStack p_77654_1_, World p_77654_2_, EntityPlayer p_77654_3_) {
		return p_77654_1_;
	}

	
	@Override
	public int getMaxItemUseDuration(ItemStack p_77626_1_) {
		return 72000;
	}

	
	@Override
	public EnumAction getItemUseAction(ItemStack p_77661_1_) {
		return EnumAction.bow;
	}

	
	@Override
	public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
		ArrowNockEvent event = new ArrowNockEvent(p_77659_3_, p_77659_1_);
		MinecraftForge.EVENT_BUS.post(event);

		if (p_77659_3_.capabilities.isCreativeMode || p_77659_3_.inventory.hasItem(ModItems.ammo_4gauge_quad)) {
			p_77659_3_.setItemInUse(p_77659_1_, this.getMaxItemUseDuration(p_77659_1_));
		}

		return p_77659_1_;
	}


	@Override
	public int getItemEnchantability() {
		return 1;
	}

	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean bool) {

		list.add("One barrel? Boring.");
		list.add("Two barrels? Nah.");
		list.add("Four barrels? Heck yes!");
		list.add("");
		list.add("Ammo: Quadruple Shotgun Shells");
		list.add("Damage: 12 - 24");
		list.add("Projectiles: 24 - 48");
		list.add("");
		list.add("[LEGENDARY WEAPON]");
	}

	@Override
	public Multimap getItemAttributeModifiers() {
		Multimap multimap = super.getItemAttributeModifiers();
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(),
				new AttributeModifier(field_111210_e, "Weapon modifier", 4.5, 0));
		return multimap;
	}
} */
