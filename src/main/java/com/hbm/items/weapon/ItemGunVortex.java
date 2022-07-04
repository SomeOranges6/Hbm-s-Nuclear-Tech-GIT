package com.hbm.items.weapon;

import java.util.List;

import org.lwjgl.opengl.GL11;

import com.hbm.entity.projectile.EntityBeamVortex;
import com.hbm.entity.projectile.EntityBulletBase;
import com.hbm.entity.projectile.EntityLaser;
import com.hbm.handler.BulletConfigSyncingUtil;
import com.hbm.handler.GunConfiguration;
import com.hbm.items.ModItems;
import com.hbm.lib.Library;
import com.hbm.lib.ModDamageSource;
import com.hbm.main.MainRegistry;
import com.hbm.packet.GunAnimationPacket;
import com.hbm.packet.PacketDispatcher;
import com.hbm.particle.ParticleVortexCircle;
import com.hbm.particle.ParticleVortexGlow;
import com.hbm.particle.ParticleVortexParticle;
import com.hbm.render.anim.HbmAnimations.AnimType;
import com.hbm.sound.AudioWrapper;
import com.hbm.util.BobMathUtil;
import com.hbm.util.EntityDamageUtil;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemGunVortex extends ItemGunBase {
	
	@SideOnly(Side.CLIENT)
	private long lastFireTime;
	private AudioWrapper chargeLoop;
	public ItemGunVortex(GunConfiguration config, GunConfiguration alt) {
		super(config, alt);
	}
		public void endAction(ItemStack stack, World world, EntityPlayer player, boolean main) {

			if(getHasShot(stack)) {

				EntityBeamVortex laser = new EntityBeamVortex(world, player);
				if (!world.isRemote) {
					world.spawnEntityInWorld(laser);
				}
				setHasShot(stack, false);
			}
			
			if(!main && getStored(stack) > 1) {
				
				EntityBulletBase bullet = new EntityBulletBase(world, altConfig.config.get(0), player);
				bullet.overrideDamage = getStored(stack)*6 + 120;
				world.spawnEntityInWorld(bullet);
				world.playSoundAtEntity(player, "hbm:weapon.tauShoot", 1.0F, 0.75F);
				setItemWear(stack, getItemWear(stack) + (getCharge(stack)) * 2);
				setCharge(stack, 0);
				EntityBeamVortex laser = new EntityBeamVortex(world, player);
				if (!world.isRemote) {
					world.spawnEntityInWorld(laser);
				}
			}
		}
		
		public void endActionClient(ItemStack stack, World world, EntityPlayer player, boolean main) {

			if(chargeLoop != null) {
				chargeLoop.stopSound();
				chargeLoop = null;
			}
		}
		
		protected void altFire(ItemStack stack, World world, EntityPlayer player) {
			setCharge(stack, 10);
		}
		
		@Override
		public void startActionClient(ItemStack stack, World world, EntityPlayer player, boolean main) {

			if(!main && getItemWear(stack) < mainConfig.durability && player.inventory.hasItem(ModItems.ammo_cell)) {
				chargeLoop = MainRegistry.proxy.getLoopedSound("hbm:weapon.tauChargeLoop2", (float)player.posX, (float)player.posY, (float)player.posZ, 1.0F, 0.75F);
				world.playSoundAtEntity(player, "hbm:weapon.tauChargeLoop2", 1.0F, 0.75F);
				
				if(chargeLoop != null) {
					chargeLoop.startSound();
				}
			}
		}
		
		protected void updateServer(ItemStack stack, World world, EntityPlayer player, int slot, boolean isCurrentItem) {
			
			super.updateServer(stack, world, player, slot, isCurrentItem);
			
			if(getIsAltDown(stack) && getItemWear(stack) < mainConfig.durability) {
				
				int c = getCharge(stack);
				
				if(c > 200) {
					setCharge(stack, 0);
					setItemWear(stack, mainConfig.durability/4);
					
					int config = BulletConfigSyncingUtil.VORTEX_OUCH;
					
					EntityBulletBase bullet = new EntityBulletBase(world, config, player);
					bullet.overrideDamage = getStored(stack)* 7F + 150;
					
					world.spawnEntityInWorld(bullet);
					
					world.playSoundAtEntity(player, "hbm:weapon.tauShoot", 1.0F, 0.75F);
					setItemWear(stack, getItemWear(stack) + (getCharge(stack)) * 2);
					setCharge(stack, 0);
					
					
					EntityBeamVortex laser = new EntityBeamVortex(world, player);
					if (!world.isRemote) {
						world.spawnEntityInWorld(laser);
					}
					return;
				}
				
				if(c > 0) {
					setCharge(stack, c + 1);
					
					if(c % 10 == 1 && c < 140 && c > 2) {
						
						if(player.inventory.hasItem(ModItems.ammo_cell)) {
							player.inventory.consumeInventoryItem(ModItems.ammo_cell);
							setStored(stack, getStored(stack) + 1);
						} else {
							setCharge(stack, 0);
							setStored(stack, 0);
						}
					}
				} else {
					setStored(stack, 0);
				}
			} else {
				setCharge(stack, 0);
				setStored(stack, 0);
			}
		}
		
		protected void updateClient(ItemStack stack, World world, EntityPlayer player, int slot, boolean isCurrentItem) {
			super.updateClient(stack, world, player, slot, isCurrentItem);

			if(chargeLoop != null) {
				if(!chargeLoop.isPlaying()) {
					chargeLoop = rebootAudio(chargeLoop, player);
				}
				chargeLoop.updatePosition((float)player.posX, (float)player.posY, (float)player.posZ);
				chargeLoop.updatePitch(chargeLoop.getPitch() + 0.01F);
			}
		}
		
		public AudioWrapper rebootAudio(AudioWrapper wrapper, EntityPlayer player) {
			wrapper.stopSound();
			AudioWrapper audio = MainRegistry.proxy.getLoopedSound("hbm:weapon.tauChargeLoop2", (float)player.posX, (float)player.posY, (float)player.posZ, wrapper.getVolume(), wrapper.getPitch());
			audio.startSound();
			return audio;
		}
		
		protected void spawnProjectile(World world, EntityPlayer player, ItemStack stack, int config) {
			
			super.spawnProjectile(world, player, stack, config);
			setHasShot(stack, true);
		}
		public static void setHasShot(ItemStack stack, boolean b) {
			writeNBT(stack, "hasShot", b ? 1 : 0);
		}
		
		public static boolean getHasShot(ItemStack stack) {
			return readNBT(stack, "hasShot") == 1;
		}
		
		/// gauss charge state ///
		public static void setCharge(ItemStack stack, int i) {
			writeNBT(stack, "charge", i);
		}
		
		public static int getCharge(ItemStack stack) {
			return readNBT(stack, "charge");
		}
		
		public static void setStored(ItemStack stack, int i) {
			writeNBT(stack, "stored", i);
		}
		
		public static int getStored(ItemStack stack) {
			return readNBT(stack, "stored");
		}
		
	}
    
	/*@Override
	protected void spawnProjectile(World world, EntityPlayer player, ItemStack stack, int config) {
		//EntityBeamVortex beam = new EntityBeamVortex(world, player);
		//world.spawnEntity(beam);
		//100 blocks is its current max range, but I'm sure that could be increased if necessary.
		List<Entity> entsOnBeam = Library.rayTraceEntitiesOnLine(player, 100, 1).getRight();
		
		for(Entity e : entsOnBeam){
			
			if(!(e instanceof EntityLivingBase))
				continue;
			
			float dmg = 30;
			EntityDamageUtil.attackEntityFromIgnoreIFrame(e, ModDamageSource.radiation, dmg);
		}
		
		if(this.mainConfig.animations.containsKey(AnimType.CYCLE) && player instanceof EntityPlayerMP)
			PacketDispatcher.wrapper.sendTo(new GunAnimationPacket(AnimType.CYCLE.ordinal()), (EntityPlayerMP) player);
		PacketDispatcher.wrapper.sendToAllAround(new GunFXPacket(player, FXType.FIRE), new TargetPoint(world.provider.dimensionId, player.posX, player.posY, player.posZ, 1));
	}
	
	//This method should also solve the supershotgun issue where it doesn't fire some of the time (maybe?)
	@Override
	@SideOnly(Side.CLIENT)
	public void onFireClient(ItemStack stack, EntityPlayer player, boolean shouldDoThirdPerson) {
		//If I'm going to do more particle systems like this maybe I should write some kind of abstraction around it to make it less messy.
		NBTTagCompound tag = new NBTTagCompound();
		Vec3d pos = null;
		if(stack == player.getHeldItemMainhand()){
			pos = new Vec3d(-0.16, -0.20, 1).rotatePitch(-(float) Math.toRadians(player.rotationPitch)).rotateYaw(-(float) Math.toRadians(player.rotationYawHead));
		} else {
			pos = new Vec3d(0.16, -0.20, 1).rotatePitch(-(float) Math.toRadians(player.rotationPitch)).rotateYaw(-(float) Math.toRadians(player.rotationYawHead));
		}
		pos = pos.add(player.getPositionEyes(1F));
		Vec3d view = BobMathUtil.getVectorFromAngle(BobMathUtil.getEulerAngles(player.getLookVec()).addVector(0, 3, 0));
		Vec3d hitPos = null;
		Vec3d hitNormal = null;
		RayTraceResult r = Library.rayTraceIncludeEntities(player, 100, MainRegistry.proxy.partialTicks());
		if(r == null || r.typeOfHit == Type.MISS){
			hitPos = player.getLook(MainRegistry.proxy.partialTicks()).scale(100).add(pos);
		} else {
			hitPos = r.hitVec;
			hitNormal = new Vec3d(r.sideHit.getFrontOffsetX(), r.sideHit.getFrontOffsetY(), r.sideHit.getFrontOffsetZ());
		}
		
		tag.setString("type", "spark");
		tag.setString("mode", "coneBurst");
		tag.setDouble("posX", pos.x-player.motionX);
		tag.setDouble("posY", pos.y-player.motionY);
		tag.setDouble("posZ", pos.z-player.motionZ);
		tag.setDouble("dirX", view.x);
		tag.setDouble("dirY", view.y);
		tag.setDouble("dirZ", view.z);
		tag.setFloat("r", 0.2F);
		tag.setFloat("g", 0.8F);
		tag.setFloat("b", 0.9F);
		tag.setFloat("a", 1.5F);
		tag.setInteger("lifetime", 1);
		tag.setFloat("width", 0.01F);
		tag.setFloat("length", 2F);
		tag.setFloat("gravity", 0);
		tag.setFloat("angle", 15F);
		tag.setInteger("count", 12);
		MainRegistry.proxy.effectNT(tag);
		
		ParticleVortexBeam beam = new ParticleVortexBeam(player.world, pos.x, pos.y, pos.z, hitPos.x, hitPos.y, hitPos.z, shouldDoThirdPerson);
		beam.color(0.5F, 0.8F, 0.9F, 2.0F);
		beam.width(0.125F);
		Minecraft.getMinecraft().effectRenderer.addEffect(beam);
		
		ParticleVortexFireFlash flash = new ParticleVortexFireFlash(player.world, pos.x, pos.y, pos.z, hitPos.x, hitPos.y, hitPos.z);
		flash.color(0.5F, 0.8F, 0.9F, 1F);
		flash.width(0.5F);
		Minecraft.getMinecraft().effectRenderer.addEffect(flash);
		
		Vec3 line = hitPos.subtract(pos);
		int circleParticles = (int) line.lengthVector();
		for(int i = 0; i < circleParticles; i ++){
			Vec3 circlePos = line.scale(i/(float)circleParticles).add(pos);
			ParticleVortexCircle c = new ParticleVortexCircle(player.worldObj, circlePos.x, circlePos.y, circlePos.z, 0.5F+player.worldObj.rand.nextFloat()*0.3F);
			c.color(0.5F, 0.8F, 0.9F, 0.15F);
			c.lifetime((int) (15+(i/(float)circleParticles)*10));
			Minecraft.getMinecraft().effectRenderer.addEffect(c);
		}
		
		int extraParticles = (int) line.lengthVector();
		for(int i = 0; i < extraParticles; i ++){
			Vec3d circlePos = line.scale((i/(float)circleParticles)*0.25).add(pos);
			float randX = (float) (player.worldObj.rand.nextGaussian()-0.5) * 0.01F;
			float randY = (float) (player.worldObj.rand.nextGaussian()-0.5) * 0.01F;
			float randZ = (float) (player.worldObj.rand.nextGaussian()-0.5) * 0.01F;
			ParticleVortexParticle c = new ParticleVortexParticle(player.worldObj, circlePos.x+randX, circlePos.y+randY, circlePos.z+randZ, 0.5F);
			c.color(0.5F, 0.8F, 0.9F, 0.15F);
			c.lifetime(30);
			Minecraft.getMinecraft().effectRenderer.addEffect(c);
		}
		
		ParticleVortexGlow glow = new ParticleVortexGlow(player.worldObj, pos.x, pos.y, pos.z, 2F);
		glow.color(0.3F, 0.7F, 1F, 0.5F);
		glow.lifetime(15);
		Minecraft.getMinecraft().effectRenderer.addEffect(glow);
		
		if(hitNormal != null){
			Vec3d sparkAxis = line.normalize().scale(0.25);
			switch(r.sideHit.getAxis()){
			case X:
				sparkAxis = new Vec3d(-sparkAxis.x, sparkAxis.y, sparkAxis.z);
				break;
			case Y:
				sparkAxis = new Vec3d(sparkAxis.x, -sparkAxis.y, sparkAxis.z);
				break;
			case Z:
				sparkAxis = new Vec3d(sparkAxis.x, sparkAxis.y, -sparkAxis.z);
				break;
			}
			tag = new NBTTagCompound();
			tag.setString("type", "spark");
			tag.setString("mode", "coneBurst");
			tag.setDouble("posX", hitPos.x);
			tag.setDouble("posY", hitPos.y);
			tag.setDouble("posZ", hitPos.z);
			tag.setDouble("dirX", sparkAxis.x);
			tag.setDouble("dirY", sparkAxis.y+0.1);
			tag.setDouble("dirZ", sparkAxis.z);
			tag.setFloat("r", 0.2F);
			tag.setFloat("g", 0.8F);
			tag.setFloat("b", 0.9F);
			tag.setFloat("a", 1.5F);
			tag.setInteger("lifetime", 20);
			tag.setInteger("randLifetime", 30);
			tag.setFloat("width", 0.015F);
			tag.setFloat("length", 0.5F);
			tag.setFloat("gravity", 0.05F);
			tag.setFloat("angle", 70F);
			tag.setInteger("count", 15);
			tag.setFloat("randomVelocity", 0.1F);
			MainRegistry.proxy.effectNT(tag);
			
			ParticleVortexHit hit = new ParticleVortexHit(player.world, hitPos.x, hitPos.y, hitPos.z, 2.5F+player.world.rand.nextFloat()*0.5F, 90);
			hit.color(0.4F, 0.8F, 1F, 0.25F);
			hit.lifetime(20);
			ParticleVortexHit hit2 = new ParticleVortexHit(player.world, hitPos.x, hitPos.y, hitPos.z, 2.5F+player.world.rand.nextFloat()*0.5F, -90);
			hit2.color(0.4F, 0.8F, 1F, 0.25F);
			hit2.lifetime(20);
			Minecraft.getMinecraft().effectRenderer.addEffect(hit);
			Minecraft.getMinecraft().effectRenderer.addEffect(hit2);
		}
		
		MainRegistry.proxy.setRecoil(3);
		lastFireTime = System.currentTimeMillis();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasCustomHudElement() {
		return true;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void renderHud(ScaledResolution res, GuiIngame gui, ItemStack stack, float partialTicks) {
		float x = res.getScaledWidth()/2;
		float y = res.getScaledHeight()/2;
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(ResourceManager.vortex_hud_reticle);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GL11.glColor4f(0.4F, 0.9F, 0.9F, 1.0F);
		GL11.glEnable(GL11.GL_BLEND);
		GlStateManager.tryBlendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE, SourceFactor.ONE, DestFactor.ZERO);
		RenderHelper.drawGuiRect(x - 11F, y - 11F, 0, 0, 22, 22, 1, 1);
		Minecraft.getMinecraft().getTextureManager().bindTexture(ResourceManager.vortex_hud_circle);
		
		//Running off of system time gives less wonky results than relying on server updating the nbt tag.
		long time = System.currentTimeMillis();

		//float cooldown = (this.mainConfig.rateOfFire-getDelay(stack)+partialTicks)/(float)this.mainConfig.rateOfFire;
		//Adding 0.05 so it doesn't start at nothing makes it look better in my opinion. 
		//It's 55 instead of 50 (50 ms in one tick) because xon lets you fire slightly before the cooldown is over. This extends the cooldown slightly beyond the real one.
		float cooldown = MathHelper.clamp((time-lastFireTime)/(float)(mainConfig.rateOfFire*55), 0, 1)+0.05F;
		final int SUBDIVISIONS = 64;
		Tessellator tes = Tessellator.instance;
		tes.startDrawing(GL11.GL_TRIANGLE_FAN);
		
		tes.setColorRGBA_F(0.4F, 0.9F, 0.9F, 0.4F);
		tes.addVertexWithUV(x, y, 0, 0.5, 0.5);
		
		for(int i = 0; i < SUBDIVISIONS+1; i ++){
			//Should be quite fast because MathHelper uses a sin table... right?
			float ratio = i/(float)SUBDIVISIONS;
			float x2 = MathHelper.sin((float) (ratio*Math.PI*2+0.5*Math.PI));
			float y2 = MathHelper.cos((float) (ratio*Math.PI*2+0.5*Math.PI));
			float alphaMult = 1-ratio < cooldown ? 1 : 0;
			buf.pos(x+x2*11, y+y2*11, 0).tex(BobMathUtil.remap01(x2, -1, 1), BobMathUtil.remap01(y2, -1, 1)).color(0.4F, 0.9F, 0.9F, 0.4F*alphaMult).endVertex();
		}
		tes.draw();
		
		GlStateManager.tryBlendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ZERO);
		GlStateManager.disableBlend();
	}*/

