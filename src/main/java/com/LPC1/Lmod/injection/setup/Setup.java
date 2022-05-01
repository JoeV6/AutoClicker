package com.LPC1.Lmod.injection.setup;

import com.LPC1.Lmod.injection.clicker.ConstructorSaver;
import com.LPC1.Lmod.injection.clicker.gui.customfont.FontUtil;
import com.LPC1.Lmod.injection.clicker.listeners.CommandListener;
import com.LPC1.Lmod.injection.clicker.listeners.EventListener;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import java.awt.*;

@Mod(modid = Setup.MODID, version = Setup.VERSION)
public class Setup {

    public static final String MODID = "LPC1";
    public static final String VERSION = "1.0";

    private final ConstructorSaver listGenerated = new ConstructorSaver();
    private final ConstructorSaver maxSpeedSet = new ConstructorSaver();
    private final ConstructorSaver minSpeedSet = new ConstructorSaver();
    private final ConstructorSaver clickerOn = new ConstructorSaver();
    private final ConstructorSaver cpsOn = new ConstructorSaver();
    private final ConstructorSaver maxSpeed = new ConstructorSaver();
    private final ConstructorSaver minSpeed = new ConstructorSaver();
    private final ConstructorSaver firstList = new ConstructorSaver();
    private final ConstructorSaver buttonStateClicker = new ConstructorSaver();
    private final ConstructorSaver clickCount = new ConstructorSaver();
    private final ConstructorSaver temp = new ConstructorSaver();
    private final ConstructorSaver fontHeight = new ConstructorSaver();


    public Setup() throws AWTException {

        ClientCommandHandler.instance.registerCommand(new CommandListener(this));
        MinecraftForge.EVENT_BUS.register(new EventListener(this));

    }

    @Mod.EventHandler
    public void onPostInit(FMLPostInitializationEvent event) {
        FontUtil.bootstrap();
        System.out.println("Initialized font ---------------------------------------------------------------------");
    }

    public ConstructorSaver getListGenerated() {
        return listGenerated;
    }

    public ConstructorSaver getMaxSpeedSet() {
        return maxSpeedSet;
    }

    public ConstructorSaver getMinSpeedSet() {
        return minSpeedSet;
    }

    public ConstructorSaver getClickerOn() {
        return clickerOn;
    }

    public ConstructorSaver getCpsOn() {
        return cpsOn;
    }

    public ConstructorSaver getMaxSpeed() {
        return maxSpeed;
    }

    public ConstructorSaver getMinSpeed() {
        return minSpeed;
    }

    public ConstructorSaver getFirstList() {
        return firstList;
    }

    public ConstructorSaver getButtonStateClicker() {
        return buttonStateClicker;
    }

    public ConstructorSaver getTemp() {
        return temp;
    }

    public ConstructorSaver getClickCount() {
        return clickCount;
    }

    public ConstructorSaver getFontHeight() {
        return fontHeight;
    }
}
