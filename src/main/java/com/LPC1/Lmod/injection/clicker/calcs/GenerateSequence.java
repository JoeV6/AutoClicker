package com.LPC1.Lmod.injection.clicker.calcs;

import com.LPC1.Lmod.injection.setup.Setup;
import net.minecraft.client.Minecraft;

import java.util.Random;

import static com.LPC1.Lmod.injection.clicker.listeners.EventListener.ClickList;

public class GenerateSequence {

    private static final Random r = new Random();

    private final Setup setup;


    public GenerateSequence(int MAX, int MIN, int T, Setup setup) {

        this.setup = setup;

        Minecraft mc = Minecraft.getMinecraft();


        if (setup.getMaxSpeedSet().isOn() && setup.getMinSpeedSet().isOn() && mc.thePlayer != null && mc.theWorld != null) {

            setup.getListGenerated().setOn(false);

            ClickList.clear();

            for (int i = 0; i < T; i++) {

                int percentage = r.nextInt(100);

                if (setup.getFirstList().isOn()) {
                    if (percentage > (20 - (MIN + MAX) / 2) / 0.20 && setup.getClickCount().getValue() < setup.getMaxSpeed().getValue()) {

                        ClickList.add(1);
                        setup.getClickCount().setValue(setup.getClickCount().getValue() + 1);

                    } else {

                        ClickList.add(0);

                    }
                } else {


                    if ((percentage > ((20 - setup.getTemp().getValue()) / 0.20)) && setup.getClickCount().getValue() < setup.getMaxSpeed().getValue()) {

                        ClickList.add(1);
                        setup.getClickCount().setValue(setup.getClickCount().getValue() + 1);

                    } else {

                        ClickList.add(0);
                    }
                }
            }

            if (!setup.getFirstList().isOn()) {
                if ((setup.getTemp().getValue() == setup.getClickCount().getValue() || setup.getTemp().getValue() == (setup.getClickCount().getValue() + 1) || setup.getTemp().getValue() == (setup.getClickCount().getValue() - 1)) && MIN < setup.getClickCount().getValue() && setup.getClickCount().getValue() < MAX) {

                    setup.getListGenerated().setOn(true);
                    System.out.println(ClickList);
                    setup.getTemp().setValue(setup.getClickCount().getValue());
                    setup.getClickCount().setValue(0);

                } else {

                    System.out.println("List Failed");

                    System.out.println(ClickList);

                    setup.getClickCount().setValue(0);

                    new GenerateSequence(setup.getMaxSpeed().getValue(), setup.getMinSpeed().getValue(), 20, setup);

                }
            } else {

                if (MIN <= setup.getClickCount().getValue() && setup.getClickCount().getValue() < MAX) {

                    setup.getTemp().setValue(setup.getClickCount().getValue());
                    setup.getClickCount().setValue(0);

                    System.out.println(ClickList);


                    setup.getFirstList().setOn(false);
                    setup.getListGenerated().setOn(true);

                } else {

                    new GenerateSequence(setup.getMaxSpeed().getValue(), setup.getMinSpeed().getValue(), 20, setup);

                }
            }
        }
    }
}
