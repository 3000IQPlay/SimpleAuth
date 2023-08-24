package dev._3000IQPlay.simpleauth.ui.font;

import dev._3000IQPlay.simpleauth.SimpleAuth;
import dev._3000IQPlay.simpleauth.util.Util;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class FontRender{
    public static boolean isCustomFontEnabled() {
        return true;
    }
	
	public void init() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static void prepare() {
        GlStateManager.pushMatrix();
        GlStateManager.disableDepth();
        GlStateManager.disableLighting();
        GlStateManager.depthMask((boolean) false);
        GlStateManager.disableAlpha();
        GlStateManager.disableCull();
        GlStateManager.enableBlend();
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
    }
	
    public static float drawCentString1(String text, float x, float y, int color) {
        return SimpleAuth.fontRenderer1.drawString(text, x - getStringWidth1(text) / 2f, y - getFontHeight1() / 2f , color);
    }

    public static float drawCentString2(String text, float x, float y, int color) {
        return SimpleAuth.fontRenderer2.drawString(text, x - getStringWidth2(text) / 2f, y, color);
    }
	
    public static int getStringWidth2(String str) {
        return SimpleAuth.fontRenderer2.getStringWidth(str);
    }

    public static int getStringWidth1(String str) {
        return SimpleAuth.fontRenderer1.getStringWidth(str);
    }

    public static int getFontHeight1() {
        if (isCustomFontEnabled())
            return SimpleAuth.fontRenderer1.getHeight();

        return Util.mc.fontRenderer.FONT_HEIGHT;
    }
}