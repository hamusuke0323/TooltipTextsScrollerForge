package com.hamusuke.tooltipscroller;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.lwjgl.glfw.GLFW;

import java.util.concurrent.atomic.AtomicInteger;

@Mod(TooltipScroller.MOD_ID)
public class TooltipScroller {
    public static final String MOD_ID = "tooltipscroller";
    public static final KeyMapping UP = new KeyMapping(MOD_ID + ".up", GLFW.GLFW_KEY_PAGE_UP, KeyMapping.CATEGORY_INVENTORY);
    public static final KeyMapping DOWN = new KeyMapping(MOD_ID + ".down", GLFW.GLFW_KEY_PAGE_DOWN, KeyMapping.CATEGORY_INVENTORY);
    public static final KeyMapping RESET = new KeyMapping(MOD_ID + ".reset", GLFW.GLFW_KEY_HOME, KeyMapping.CATEGORY_INVENTORY);
    public static final AtomicInteger AMOUNT = new AtomicInteger();

    public TooltipScroller() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
    }

    private void onClientSetup(final RegisterKeyMappingsEvent event) {
        event.register(UP);
        event.register(DOWN);
        event.register(RESET);
    }
}
