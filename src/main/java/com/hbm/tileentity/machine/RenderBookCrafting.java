package com.hbm.tileentity.machine;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class RenderBookCrafting extends TileEntitySpecialRenderer<TileEntityBlackBook> {

    @Override
    public boolean isGlobalRenderer(TileEntityBlackBook te) {
        return true;
    }

    @Override
    public void render(TileEntityBlackBook te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
    }
}
