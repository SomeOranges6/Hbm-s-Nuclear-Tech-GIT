package com.hbm.render.item.weapon;

import org.lwjgl.opengl.GL11;

import com.hbm.items.ModItems;
import com.hbm.items.weapon.GunFolly;
import com.hbm.main.ResourceManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class ItemRenderFolly implements IItemRenderer {

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		switch(type) {
		case EQUIPPED:
		case EQUIPPED_FIRST_PERSON:
		case ENTITY:
		case INVENTORY:
			return true;
		default: return false;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		
		return type == ItemRenderType.ENTITY && (helper == ItemRendererHelper.ENTITY_ROTATION || helper == ItemRendererHelper.ENTITY_BOBBING);
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		
		int state = GunFolly.getState(item);
		int timer = GunFolly.getTimer(item);
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		GL11.glPushMatrix();
		
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		Minecraft.getMinecraft().renderEngine.bindTexture(ResourceManager.folly_tex);
		
		
		switch(type) {
		
		
		case EQUIPPED_FIRST_PERSON:
			double s0 = 0.3D;
			switch (state) {
			
			case 1: 
				//Open but shell'nt
				GL11.glPushMatrix();
				
				    GL11.glTranslated(1.6, 1.1, 0);
				    GL11.glScaled(s0, s0, s0);
				    GL11.glRotated(80, 0, 1, 0);
				    GL11.glRotated(-20, 1, 0, 0);
				    
				    if(player.inventory.hasItem(ModItems.ammo_folly_nuclear )||(player.inventory.hasItem(ModItems.ammo_folly)||(player.inventory.hasItem(ModItems.ammo_folly_du)||(player.inventory.hasItem(ModItems.ammo_folly_sleek)||
			    		  (player.inventory.hasItem(ModItems.ammo_folly_ouch)||(player.inventory.hasItem(ModItems.ammo_folly_tandem))))))){ 
			    	  ResourceManager.folly.renderPart("Shell2");
			        }
				
				GL11.glPopMatrix();
				
				GL11.glPushMatrix();
				
				   GL11.glTranslated(2, 1.0, 0.2);
			       GL11.glScaled(s0, s0, s0);
			       GL11.glRotated(80, 1, 0, 0);
			       GL11.glRotated(-25, 1, 0, 0.1);
			       GL11.glRotated(20, 0, 1, 0);
			       ResourceManager.folly.renderPart("Barrel");
			       ResourceManager.folly.renderPart("Handle");
			       
			    GL11.glPopMatrix();
			    
			      GL11.glTranslated(1.7, 1.8, 0);
			      GL11.glScaled(s0, s0, s0);
			      GL11.glRotated(0, 0, 1, 0);
			      GL11.glRotated(-25, 1, 0, 0.1);
			      GL11.glRotated(20, 0, 1, 0);
			      
			    break;
			case 2: 
				
				//Open with A an shell
				GL11.glPushMatrix();
				
				   GL11.glTranslated(2, 1.0, 0.2);
			       GL11.glScaled(s0, s0, s0);
			       GL11.glRotated(80, 1, 0, 0);
			       GL11.glRotated(-25, 1, 0, 0.1);
			       GL11.glRotated(20, 0, 1, 0);
			       
			       ResourceManager.folly.renderPart("Barrel");
			       ResourceManager.folly.renderPart("Handle");
			       ResourceManager.folly.renderPart("Shell");
			    GL11.glPopMatrix();
			    
			      GL11.glTranslated(1.7, 1.8, 0);
			      GL11.glScaled(s0, s0, s0);
			      GL11.glRotated(-25, 1, 0, 0.1);
			      GL11.glRotated(20, 0, 1, 0);
			   
			    break;
			case 3:
				//closed and has a an shell
				GL11.glTranslated(1.7, 0.9, 0);
			    GL11.glScaled(s0, s0, s0);
			    GL11.glRotated(80, 0, 1, 0);
			    GL11.glRotated(-20, 1, 0, 0);
			    ResourceManager.folly.renderPart("Shell");
			    ResourceManager.folly.renderPart("Barrel");
			    ResourceManager.folly.renderPart("Handle");
			    
			    break;
			   
			default:
	            //Closed and empy
			    GL11.glTranslated(1.7, 0.9, 0);
			    GL11.glScaled(s0, s0, s0);
			    GL11.glRotated(80, 0, 1, 0);
			    GL11.glRotated(-20, 1, 0, 0);
			    ResourceManager.folly.renderPart("Barrel");
			    ResourceManager.folly.renderPart("Handle");
			    break;
			    
			    //Same pattern applies for the second big block of switches
			    
			}
			
			if(state == 3 && timer > -1) {
				GL11.glPushMatrix();
		        GL11.glDisable(GL11.GL_TEXTURE_2D);
		        GL11.glDisable(GL11.GL_LIGHTING);
		        GL11.glRotated(90, 0, 1, 0);
		        GL11.glTranslated(1.8, -0.4, 1);
	            Tessellator tessellator = Tessellator.instance;
	            int color = 0x00FF00;
	            
	            if(timer == 0)
	            	color = 0xFF0000;

	            tessellator.startDrawing(3);
		        tessellator.setColorOpaque_I(color);
	            tessellator.addVertex(-32F / 16F, 0 + 4F / 16F, 0);
	            tessellator.addVertex(-150, timer, 0);
	            tessellator.draw();

	            tessellator.startDrawing(3);
		        tessellator.setColorOpaque_I(color);
	            tessellator.addVertex(-32F / 16F, 0 + 4F / 16F, 0);
	            tessellator.addVertex(-150, -timer, 0);
	            tessellator.draw();

	            tessellator.startDrawing(3);
		        tessellator.setColorOpaque_I(color);
	            tessellator.addVertex(-32F / 16F, 0 + 4F / 16F, 0);
	            tessellator.addVertex(-150, 0, timer);
	            tessellator.draw();

	            tessellator.startDrawing(3);
		        tessellator.setColorOpaque_I(color);
	            tessellator.addVertex(-32F / 16F, 0 + 4F / 16F, 0);
	            tessellator.addVertex(-150, 0, -timer);
	            tessellator.draw();
		        
		        GL11.glEnable(GL11.GL_LIGHTING);
		        GL11.glEnable(GL11.GL_TEXTURE_2D);
				GL11.glPopMatrix();
			}
			
			break;
		case EQUIPPED:

			double scale = 0.4D;
             switch (state) {
			
			case 1: 
				GL11.glPushMatrix();
				
				   GL11.glTranslated(0.7, -1.3, 0.2);
			       GL11.glScaled(scale, scale, scale);
			       GL11.glRotated(10, 0, 1, 0);
				   GL11.glRotated(95, 1, 0, 0);
				
			       
			       ResourceManager.folly.renderPart("Barrel");
			       ResourceManager.folly.renderPart("Handle");
			      
			    GL11.glPopMatrix();
				GL11.glRotated(10, 0, 1, 0);
				GL11.glRotated(15, 1, 0, 0);
				GL11.glTranslated(0.7, 0.1, 1.3);
				GL11.glScaled(scale, scale, scale);
				
			    break;
			case 2: 
				GL11.glPushMatrix();
				
				   GL11.glTranslated(0.7, -1.3, 0.2);
			       GL11.glScaled(scale, scale, scale);
			       GL11.glRotated(10, 0, 1, 0);
				   GL11.glRotated(95, 1, 0, 0);
				
			       
			       ResourceManager.folly.renderPart("Barrel");
			       ResourceManager.folly.renderPart("Handle");
			       ResourceManager.folly.renderPart("Shell");
			    GL11.glPopMatrix();
				GL11.glRotated(10, 0, 1, 0);
				GL11.glRotated(15, 1, 0, 0);
				GL11.glTranslated(0.7, 0.1, 1.3);
				GL11.glScaled(scale, scale, scale);
				
			    break;
			case 3:
				
				GL11.glRotated(10, 0, 1, 0);
				GL11.glRotated(15, 1, 0, 0);
				GL11.glTranslated(0.7, 0.1, 1.3);
				GL11.glScaled(scale, scale, scale);
				 ResourceManager.folly.renderPart("Barrel");
			    ResourceManager.folly.renderPart("Shell");
			    ResourceManager.folly.renderPart("Handle");
			    break;
			default:
				GL11.glRotated(10, 0, 1, 0);
				GL11.glRotated(15, 1, 0, 0);
				GL11.glTranslated(0.7, 0.1, 1.3);
			    GL11.glScaled(scale, scale, scale);
			    ResourceManager.folly.renderPart("Barrel");
			    ResourceManager.folly.renderPart("Handle");
			    break;
             }
             
             
             
          break;
			
		case ENTITY:

			double s1 = 0.5D;
			GL11.glScaled(s1, s1, s1);
			GL11.glRotated(0, 0, 1, 0);
			 ResourceManager.folly.renderPart("Barrel");
			 ResourceManager.folly.renderPart("Handle");
			
			break;
			
		case INVENTORY:

			GL11.glEnable(GL11.GL_LIGHTING);
			
			double s = 2.1D;
			GL11.glTranslated(8, 8, 0);
			GL11.glRotated(-140, 0, 0, 1);
			GL11.glRotated(-90, 0, 1, 0);
			GL11.glScaled(s, s, -s);
			 ResourceManager.folly.renderPart("Barrel");
			
			break;
			
		default: break;
		}
		
		ResourceManager.folly.renderPart("Main");
		
		GL11.glShadeModel(GL11.GL_FLAT);
		GL11.glPopMatrix();
	}
}
