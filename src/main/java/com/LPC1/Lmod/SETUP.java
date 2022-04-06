package com.LPC1.Lmod;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

@Mod(modid = SETUP.MODID, version = SETUP.VERSION)

public class SETUP
{
    public static final String MODID = "LiamMod";
    public static final String VERSION = "1.0";
    
    @Mod.EventHandler
    public void PostInit(FMLPostInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new AUTOCLICKER());
        ClientCommandHandler.instance.registerCommand(new COMMANDS());
    }
}
