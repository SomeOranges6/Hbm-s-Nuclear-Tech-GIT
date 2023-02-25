package com.hbm.inventory.gui;

import org.lwjgl.opengl.GL11;
import com.hbm.inventory.BreederRecipes;
import com.hbm.inventory.container.ContainerReactor;
import com.hbm.lib.RefStrings;
import com.hbm.tileentity.machine.TileEntityMachineReactor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GUIMachineReactor extends GuiInfoContainer {

	public static ResourceLocation texture = new ResourceLocation(RefStrings.MODID + ":textures/gui/processing/gui_breeder.png");
	private final TileEntityMachineReactor breeder;

	public GUIMachineReactor(InventoryPlayer invPlayer, TileEntityMachineReactor tedf) {
		super(new ContainerReactor(invPlayer, tedf));
		breeder = tedf;
		
		this.xSize = 176;
		this.ySize = 166;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);

		String tooltip = BreederRecipes.getHEATString(breeder.heat + " HEAT", breeder.heat);

		this.drawCustomInfoStat(mouseX, mouseY, guiLeft + 47, guiTop + 34, 6, 18, mouseX, mouseY, new String[] { tooltip });
		this.drawCustomInfoStat(mouseX, mouseY, guiLeft + 55, guiTop + 34, 18, 18, mouseX, mouseY, new String[] { breeder.charge + " operation(s) left" });
		super.renderHoveredToolTip(mouseX, mouseY);
		
		
		/*GL11.glPushMatrix();
		GlStateManager.matrixMode(GL11.GL_PROJECTION);
		GL11.glPushMatrix();
		GL11.glLoadIdentity();
		GLU.gluPerspective(90F, Minecraft.getMinecraft().displayWidth/Minecraft.getMinecraft().displayHeight, 0.05F, 100F);
		GlStateManager.matrixMode(GL11.GL_MODELVIEW);
		GlStateManager.disableDepth();
		GlStateManager.disableCull();
		GL11.glLoadIdentity();
		GL11.glTranslated(0, 0, -0.05);
		GL11.glScaled(0.01, 0.01, 0.01);
		
		GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, HbmShaderManager2.AUX_GL_BUFFER);
		HbmShaderManager2.AUX_GL_BUFFER.rewind();
		Matrix4f mvMatrix = new Matrix4f();
		mvMatrix.load(HbmShaderManager2.AUX_GL_BUFFER);
		HbmShaderManager2.AUX_GL_BUFFER.rewind();
		
		GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, HbmShaderManager2.AUX_GL_BUFFER);
		HbmShaderManager2.AUX_GL_BUFFER.rewind();
		Matrix4f pMatrix = new Matrix4f();
		pMatrix.load(HbmShaderManager2.AUX_GL_BUFFER);
		HbmShaderManager2.AUX_GL_BUFFER.rewind();
		
		Matrix4f.mul(pMatrix, mvMatrix, mvMatrix);
		
		Vector4f bruh = new Vector4f(0, 0, 0, 1);
		Matrix4f.transform(mvMatrix, bruh, bruh);
		Vector3f bruh2 = new Vector3f(bruh.x/bruh.w, bruh.y/bruh.w, bruh.z/bruh.w);
		System.out.println(bruh2);
		
		
		ResourceManager.sphere_iuv.renderAll();
		GlStateManager.enableDepth();
		GlStateManager.enableCull();
		GlStateManager.matrixMode(GL11.GL_PROJECTION);
		GL11.glPopMatrix();
		GlStateManager.matrixMode(GL11.GL_MODELVIEW);
		GL11.glPopMatrix();*/
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int i, int j) {
		String name = this.breeder.hasCustomInventoryName() ? this.breeder.getInventoryName() : I18n.format(this.breeder.getInventoryName());
		
		this.fontRenderer.drawString(name, this.xSize / 2 - this.fontRenderer.getStringWidth(name) / 2, 6, 4210752);
		this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		super.drawDefaultBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		/*
		 * A dud is a tile entity which did not survive a block state change (i.e. a furnace becoming lit) on the client.
		 * Usually, most functionality is preserved since vanilla interacts with the open GUI screen rather than the TE
		 * itself, though this does not apply to NTM packets. The client will think the TE bound to the GUI is invalid,
		 * and therefore miss out on NTM status packets, but it will still require the old TE for slot changes. The refreshed
		 * "dud" is only used for status bars, it will not replace the actual invalid TE instance in the GUI screen.
		 */
		TileEntityMachineReactor dud = breeder;

		if(breeder.isInvalid() && breeder.getWorld().getTileEntity(breeder.getPos()) instanceof TileEntityMachineReactor)
			dud = (TileEntityMachineReactor) breeder.getWorld().getTileEntity(breeder.getPos());

		if(dud.hasPower())
		{
			drawTexturedModalRect(guiLeft + 55, guiTop + 35, 176, 0, 18, 16);
		}
		
		int i = dud.getProgressScaled(23);
		drawTexturedModalRect(guiLeft + 80, guiTop + 34, 176, 16, i, 16);

		int j = dud.getHeatScaled(16);
		drawTexturedModalRect(guiLeft + 48, guiTop + 51 - j, 194, 16 - j, 4, j);
	}
}
