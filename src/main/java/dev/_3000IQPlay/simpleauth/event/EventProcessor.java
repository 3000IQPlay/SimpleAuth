package dev._3000IQPlay.simpleauth.event;

import dev._3000IQPlay.simpleauth.SimpleAuth;
import dev._3000IQPlay.simpleauth.ui.AuthGui;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventProcessor {
    private final Minecraft mc = Minecraft.getMinecraft();
	
	public EventProcessor() {
		MinecraftForge.EVENT_BUS.register(this);
	}
	
    @SubscribeEvent
	public void onGuiOpen(GuiOpenEvent event) {
		if (!(event.getGui() instanceof AuthGui) && SimpleAuth.isOpenAuthGui) {
			event.setCanceled(true);
		}
	}

    public void onInit() {
        mc.displayGuiScreen(new AuthGui());
        SimpleAuth.isOpenAuthGui = true;
    }
}