package com.LPC1.Lmod;

import net.minecraft.client.Minecraft;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;

import static com.LPC1.Lmod.COMMANDS.*;
import static java.lang.Math.random;


public class AUTOCLICKER {

    Minecraft minecraft = Minecraft.getMinecraft();

    public static boolean ClickerON = false;
    public static boolean ListGenerated = false;
    public static boolean FirstList = true;
    public static boolean CPS = false;
    public static boolean CPSCMD = false;

    private static int Ticks = 0;
    private static int Seconds = 0;

    public static int ClickCount = 0;
    public static int TEMP = 0;
    public static int MAXSpeed = 0;
    public static int MINSpeed = 0;

    public static ArrayList<Integer> ClickList = new ArrayList<Integer>();




    @SubscribeEvent
    public void ClientTickEvent(TickEvent.ClientTickEvent event) {

        if (Ticks == 20) {
            Ticks = 0;
            Seconds++;
            System.out.println(Seconds);
        }

        if (Seconds == 2 ) {
            Seconds = 0;
            if (AUTOCLICKER.ClickerON) {
                GenerateSequence(AUTOCLICKER.MAXSpeed, AUTOCLICKER.MINSpeed, 20);
            }
        }

        if (ClickerON && ListGenerated && ClickList.get(Ticks) == 1) {

            MouseClick();

            }
        Ticks++;
        }


    private void MouseClick() {
        if (minecraft.gameSettings.keyBindAttack.isKeyDown()) {
            minecraft.thePlayer.swingItem();
            CPS = true;
            switch (minecraft.objectMouseOver.typeOfHit) {
                case ENTITY:
                    minecraft.playerController.attackEntity(minecraft.thePlayer, minecraft.objectMouseOver.entityHit);
                    break;
                case BLOCK:
                    minecraft.playerController.clickBlock(minecraft.objectMouseOver.getBlockPos(), minecraft.objectMouseOver.sideHit);
                    break;
            }
        } else { CPS = false; }
    }
}
