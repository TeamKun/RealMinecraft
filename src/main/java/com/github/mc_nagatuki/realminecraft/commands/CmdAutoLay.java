package com.github.mc_nagatuki.realminecraft.commands;

import com.github.mc_nagatuki.realminecraft.RealMinecraft;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CmdAutoLay extends CommandAbstract {
    private RealMinecraft plugin;
    private String cmdStr = "autolay";

    public CmdAutoLay(RealMinecraft plugin) {
        super();
        this.plugin = plugin;
    }

    public boolean executeCommand(CommandSender sender, Command command, String label, String[] args){
        if(args.length == 1 && args[0].equalsIgnoreCase(this.cmdStr)){
            int x1, x2, z1, z2;
            double prob;
            x1 = this.plugin.getX1();
            x2 = this.plugin.getX2();
            z1 = this.plugin.getZ1();
            z2 = this.plugin.getZ2();
            prob = this.plugin.getProbability();

            this.plugin.getMineManager().layAutomatically(x1, z1, x2, z2, prob);
            sender.sendMessage("[RealMinecraft] set mines automatically");
            return true;
        }

        if(args.length == 5 && args[0].equalsIgnoreCase(this.cmdStr)){
            int x1, x2, z1, z2;
            double prob;

            Location loc = ((Player) sender).getLocation();

            if(CommandArgsParser.isInteger(args[1])){
                x1 = Integer.parseInt(args[1]);
            } else if(CommandArgsParser.isDouble(args[1])){
                x1 = CommandArgsParser.positionToBlockPosition(Double.parseDouble(args[1]));
            } else if(CommandArgsParser.isTilde(args[1])){
                x1 = loc.getBlockX();
            } else{
                return false;
            }

            if(CommandArgsParser.isInteger(args[2])){
                z1 = Integer.parseInt(args[2]);
            } else if(CommandArgsParser.isDouble(args[2])){
                z1 = CommandArgsParser.positionToBlockPosition(Double.parseDouble(args[2]));
            } else if(CommandArgsParser.isTilde(args[2])){
                z1 = loc.getBlockZ();
            } else{
                return false;
            }

            if(CommandArgsParser.isInteger(args[3])){
                x2 = Integer.parseInt(args[3]);
            } else if(CommandArgsParser.isDouble(args[3])){
                x2 = CommandArgsParser.positionToBlockPosition(Double.parseDouble(args[3]));
            } else if(CommandArgsParser.isTilde(args[3])){
                x2 = loc.getBlockX();
            } else{
                return false;
            }

            if(CommandArgsParser.isInteger(args[4])){
                z2 = Integer.parseInt(args[4]);
            } else if(CommandArgsParser.isDouble(args[4])){
                z2 = CommandArgsParser.positionToBlockPosition(Double.parseDouble(args[4]));
            } else if(CommandArgsParser.isTilde(args[4])){
                z2 = loc.getBlockZ();
            } else{
                return false;
            }

            prob = this.plugin.getProbability();

            this.plugin.getMineManager().layAutomatically(x1, z1, x2, z2, prob);
            sender.sendMessage("[RealMinecraft] set mines automatically");
            return true;
        }

        return false;
    }

    public List<String> tabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1 && this.cmdStr.startsWith(args[0])) {
            return new ArrayList<>(Arrays.asList(this.cmdStr));
        }

        if(args[0].equalsIgnoreCase(this.cmdStr)){
            switch (args.length){
                case 2:
                    return new ArrayList<>(Arrays.asList("~ ~ ~ ~"));
                case 3:
                    return new ArrayList<>(Arrays.asList("~ ~ ~"));
                case 4:
                    return new ArrayList<>(Arrays.asList("~ ~"));
                case 5:
                    return new ArrayList<>(Arrays.asList("~"));
            }
        }

        return null;
    }
}
