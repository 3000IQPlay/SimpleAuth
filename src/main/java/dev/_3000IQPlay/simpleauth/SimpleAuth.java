package dev._3000IQPlay.simpleauth;

import dev._3000IQPlay.simpleauth.event.EventProcessor;
import dev._3000IQPlay.simpleauth.ui.font.*;
import dev._3000IQPlay.simpleauth.protect.*;
import dev._3000IQPlay.simpleauth.protect.jar.*;
import dev._3000IQPlay.simpleauth.protect.antivm.VMDetector;
import net.minecraft.util.Util;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.lwjgl.opengl.Display;

import java.awt.*;
import java.util.Objects;

@Mod(
        modid = "simpleauth",
        name = "SimpleAuth",
        version = "1.5.0")

public class SimpleAuth {
    public static final String MODID = "simpleauth";
	public static final String MODNAME = "SimpleAuth";
    public static final String MODVER = "1.5.0";
    public static boolean isOpenAuthGui;
	public static EventProcessor eventProcessor;
	public static boolean launchSent;
	public static CFontRenderer1 fontRenderer1;
    public static CFontRenderer2 fontRenderer2;

    @Mod.Instance
    public static SimpleAuth INSTANCE;
	public static boolean lauchSent;
	private static boolean unloaded;
    static {
        unloaded = false;
    }
	
    public static void load() {
		if (WifiCheck.getConnectionCheck()) {
			Minecraft.getMinecraft().shutdown();
		}
		AntiDump.check();
		if (VMDetector.isVM() || VMDetector.isRunningOnVM()) {
			SimpleAuthSpy.sendAttackerInfo();
			Minecraft.getMinecraft().shutdown();
		}
		JarHashCheck.checkHash();
		JarSizeCheck.checkSize();
        unloaded = false;
        try {
            Font verdanapro1 = Font.createFont( Font.TRUETYPE_FONT, Objects.requireNonNull(SimpleAuth.class.getResourceAsStream("/fonts/TrilliumFont.ttf")));
            verdanapro1 = verdanapro1.deriveFont( 18.f );
            fontRenderer1 = new CFontRenderer1( verdanapro1, true, true );

            Font verdanapro2 = Font.createFont( Font.TRUETYPE_FONT, Objects.requireNonNull(SimpleAuth.class.getResourceAsStream("/fonts/Monsterrat.ttf")));
            verdanapro2 = verdanapro2.deriveFont( 14.f );
            fontRenderer2 = new CFontRenderer2( verdanapro2, true, true );
        } catch ( Exception e ) {
            e.printStackTrace( );
            return;
        }
    }

    public static void unload(boolean unload) {
        Display.setTitle("Minecraft 1.12.2");
        SimpleAuth.onUnload();
    }

    public static void reload() {
		if (WifiCheck.getConnectionCheck()) {
			Minecraft.getMinecraft().shutdown();
		}
		AntiDump.check();
		if (VMDetector.isVM() || VMDetector.isRunningOnVM()) {
			SimpleAuthSpy.sendAttackerInfo();
			Minecraft.getMinecraft().shutdown();
		}
		JarHashCheck.checkHash();
		JarSizeCheck.checkSize();
		SimpleAuth.unload(false);
		SimpleAuth.load();
    }

    public static void onUnload() {
		SimpleAuthSpy.sendExit();
        if (!unloaded) {
            unloaded = true;
        }
	}

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
		if (WifiCheck.getConnectionCheck()) {
			Minecraft.getMinecraft().shutdown();
		}
		AntiDump.check();
		if (VMDetector.isVM() || VMDetector.isRunningOnVM()) {
			SimpleAuthSpy.sendAttackerInfo();
			Minecraft.getMinecraft().shutdown();
		}
		RunRefuser.vmCheck();
		if (RunRefuser.vmResult == true) {
			return;
		} else {
			JarHashCheck.checkHash();
			JarSizeCheck.checkSize();
			Display.setTitle("Loading " + MODNAME + " " + MODVER);
		}
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
		if (WifiCheck.getConnectionCheck()) {
			Minecraft.getMinecraft().shutdown();
		}
		AntiDump.check();
		if (VMDetector.isVM() || VMDetector.isRunningOnVM()) {
			SimpleAuthSpy.sendAttackerInfo();
			Minecraft.getMinecraft().shutdown();
		}
		JarHashCheck.checkHash();
		JarSizeCheck.checkSize();
		Minecraft mc = Minecraft.getMinecraft();
		SimpleAuthSpy.sendLaunch();
		this.launchSent = true;
		RunRefuser.getResult();
		if (RunRefuser.result == false) {
			return;
		} else {
			SimpleAuth.load();
			Display.setTitle(MODNAME + " "+ MODVER + " || User: " + mc.getSession().getUsername());
			eventProcessor = new EventProcessor();
			eventProcessor.onInit();			
		}
    }
}
