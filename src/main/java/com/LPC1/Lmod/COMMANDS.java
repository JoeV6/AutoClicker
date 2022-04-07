 package com.LPC1.Lmod;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

 public class COMMANDS implements ICommand {

    @Override
    public String getCommandName() {
        return "Clicker";
    }

    @Override
    public String getCommandUsage(ICommandSender iCommandSender) {
        return "Change autoclicker settings";
    }

    @Override
    public List<String> getCommandAliases() {
        List<String> Aliases = new ArrayList<String>();
        Aliases.add("autoclicker");
        Aliases.add("clicker");
        Aliases.add("ac");
        return Aliases;
    }

    @Override
    public void processCommand(ICommandSender iCommandSender, String[] strings) throws CommandException {
        if (strings.length == 0) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(""));
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("ac (on/off)"));
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("ac speed (1-20)"));
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(""));
        }
        if (strings[0].equalsIgnoreCase("on")) {
            AUTOCLICKER.ClickerON = true;
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Autoclicker turned on"));
        }
        if (strings[0].equalsIgnoreCase("off")) {
            AUTOCLICKER.ClickerON = false;
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Autoclicker turned off"));
        }
        if (strings[0].equalsIgnoreCase("speed") && strings[1].matches("[0-9]+")) {

            if (Integer.parseInt(strings[1]) < 21) {
                AUTOCLICKER.ClickSpeed = Integer.parseInt(strings[1]);
                System.out.println(AUTOCLICKER.ClickSpeed);
                GenerateSequence(AUTOCLICKER.ClickSpeed, 20);
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Autoclicker speed set to " + AUTOCLICKER.ClickSpeed));
            } else {
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("ac speed (1-20)"));
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

    public void GenerateSequence(int C, int T) {


        int ClickCount = 0;
        AUTOCLICKER.ListGenerated = false;
        AUTOCLICKER.ClickList.clear();

        for (int i = 0; i < T; i++) {

            Random r = new Random();
            int percentage = r.nextInt(100);

            if ((percentage > ((20 - C) / 0.20)) && (ClickCount < AUTOCLICKER.ClickSpeed)) {
                AUTOCLICKER.ClickList.add(1);
                ClickCount++;
            } else {
                AUTOCLICKER.ClickList.add(0);
            }
        }
        System.out.println(AUTOCLICKER.ClickList);

        if (ClickCount != C) {
            GenerateSequence(AUTOCLICKER.ClickSpeed, 20);

        } else {
            AUTOCLICKER.ListGenerated = true;
            System.out.println("Good List");
        }
    }
}
