package com.LPC1.Lmod.injection.clicker.listeners;


import com.LPC1.Lmod.injection.clicker.calcs.GenerateSequence;
import com.LPC1.Lmod.injection.setup.Setup;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommandListener implements ICommand {


    private static final Random r = new Random();

    private final Setup setup;


    public CommandListener(Setup setup) {
        this.setup = setup;
    }


    @Override
    public String getCommandName() {
        return "mod";
    }

    @Override
    public String getCommandUsage(ICommandSender iCommandSender) {
        return "Change mod settings";
    }

    @Override
    public List<String> getCommandAliases() {
        List<String> Aliases = new ArrayList<String>();
        Aliases.add("mod");
        Aliases.add("clicker");
        Aliases.add("ac");
        return Aliases;
    }

    @Override
    public void processCommand(ICommandSender iCommandSender, String[] strings) {
        if (strings.length == 0) {
            sendMessage("");
            sendMessage("mod (on/off)");
            sendMessage("mod maxcps (1-20)");
            sendMessage("mod mincps (1-20)");
            sendMessage("mod cps (on / off)");
            sendMessage("");
        }
        if (strings[0].equalsIgnoreCase("on")) {
            setup.getClickerOn().setOn(true);
            setup.getFirstList().setOn(true);
            sendMessage("Clicker turned on");
            new GenerateSequence(setup.getMaxSpeed().getValue(), setup.getMinSpeed().getValue(), 20, setup);
        }

        if (strings[0].equalsIgnoreCase("off")) {
            setup.getClickerOn().setOn(false);
            sendMessage("Clicker turned off");
        }
        if (strings[0].equalsIgnoreCase("cps") && strings[1].equalsIgnoreCase("off")) {
            setup.getCpsOn().setOn(false);
            sendMessage("CPS turned off");
        }
        if (strings[0].equalsIgnoreCase("cps") && strings[1].equalsIgnoreCase("on")) {
            setup.getCpsOn().setOn(true);
            sendMessage("CPS turned on");
        }
        if (strings[0].equalsIgnoreCase("maxcps") && strings[1].matches("[0-9]+")) {

            if (Integer.parseInt(strings[1]) <= 20 && Integer.parseInt(strings[1]) >= setup.getMinSpeed().getValue()) {
                setup.getMaxSpeedSet().setOn(true);
                setup.getMaxSpeed().setValue(Integer.parseInt(strings[1]));
                sendMessage("MaxCPS set to " + Integer.parseInt(strings[1]));
            }

        }
        if (strings[0].equalsIgnoreCase("mincps") && strings[1].matches("[0-9]+")) {

            if (Integer.parseInt(strings[1]) <= 20 && Integer.parseInt(strings[1]) <= setup.getMaxSpeed().getValue()) {
                setup.getMinSpeedSet().setOn(true);
                setup.getMinSpeed().setValue(Integer.parseInt(strings[1]));
                sendMessage("MinCPS set to " + Integer.parseInt(strings[1]));
            }
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender iCommandSender) {
        return true;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender iCommandSender, String[] strings, BlockPos blockPos) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] strings, int i) {
        return false;
    }

    @Override
    public int compareTo(ICommand o) {
        return 0;
    }

    public static void sendMessage(String message) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
    }
}
