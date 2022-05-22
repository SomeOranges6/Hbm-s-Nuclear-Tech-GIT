package com.hbm.render.item.weapon;

import org.lwjgl.opengl.GL11;

import com.hbm.main.ResourceManager;

import net.minecraft.client.Minecraft;
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
		
		GL11.glPushMatrix();
		
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		
		switch(type) {
		
		case EQUIPPED_FIRST_PERSON:
			
			double s0 = 0.26D;
			GL11.glTranslated(1.7, 1.1, 0);
			GL11.glScaled(s0, s0, s0);
			GL11.glRotated(255, 0, 0.8, -0.3);
			GL11.glRotated(-25, 0, 0, 0.1);
			
			break;
			
		case EQUIPPED:

			double scale = 0.3D;
			GL11.glRotated(190, 0, 1, 0);
			GL11.glRotated(-10, 1, 0, 0);
			GL11.glRotated(0, 0, 0, 0);
			GL11.glTranslated(-0.55, 0.5, -1.3);
			GL11.glScaled(scale, scale, scale);
			
			break;
			
		case ENTITY:

			double s1 = 0.4D;
			GL11.glScaled(s1, s1, s1);
			GL11.glRotated(0, 0, 1, 0);
			
			break;
			
		case INVENTORY:

			GL11.glEnable(GL11.GL_LIGHTING);
			
			double s = 1.7D;
			GL11.glTranslated(8, 6, 1);
			GL11.glRotated(-140, 0, 0, 1);
			GL11.glRotated(90, 0, 1, 0);
			GL11.glScaled(s, s, -s);
			
			break;
			
		default: break;
		}
		
		Minecraft.getMinecraft().renderEngine.bindTexture(ResourceManager.folly_tex);
		ResourceManager.folly.renderAll();

		GL11.glShadeModel(GL11.GL_FLAT);
		GL11.glPopMatrix();
	}
}
