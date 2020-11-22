package com.github.mc_nagatuki.realminecraft.commands;

import com.github.mc_nagatuki.realminecraft.RealMinecraft;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CmdSet extends CommandAbstract {
    private RealMinecraft plugin;
    private String cmdStr = "set";
    private CommandAbstract[] subCommands;

    public CmdSet(RealMinecraft plugin) {
        super();
        this.plugin = plugin;

        this.subCommands = new CommandAbstract[]{
            new Pos1(plugin), new Pos2(plugin), new Power(plugin), new Damage(plugin), new Probability(plugin),
        };
    }

    public boolean executeCommand(CommandSender sender, Command command, String label, String[] args){
        if(args.length >= 2){
            for(CommandAbstract e : this.subCommands){
                boolean ret = e.executeCommand(sender, command, label, args);
                if(ret) return true;
            }
        }

        return false;
    }

    public List<String> tabComplete(CommandSender sender, Command command, String alias, String[] args){
        if(args.length == 0){
            return new ArrayList<>(Arrays.asList(this.cmdStr));
        }

        if(args.length == 1){
            if(this.cmdStr.startsWith(args[0])){
                return new ArrayList<>(Arrays.asList(this.cmdStr));
            }
            return null;
        }

        // args.length >= 2
        if(args[0].equalsIgnoreCase(this.cmdStr)) {
            List<String> result = new ArrayList<>();

            for(CommandAbstract e : this.subCommands){
                List<String> ret = e.tabComplete(sender, command, alias, args);

                if(ret == null) continue;

                result.addAll(ret);
            }

            if(result.size() > 0){
                return result;
            }
        }

        return null;
    }


    // set pos1 x, z
    class Pos1 extends CommandAbstract{
        private RealMinecraft plugin;
        private String cmdStr = "pos1";

        public Pos1(RealMinecraft plugin){
            super();
            this.plugin = plugin;
        }

        public boolean executeCommand(CommandSender sender, Command command, String label, String[] args){
            if(args.length == 4 && args[1].equalsIgnoreCase(this.cmdStr)){
                int x, z;
                Location loc = ((Player) sender).getLocation();

                if(CommandArgsParser.isInteger(args[2])){
                    x = Integer.parseInt(args[2]);
                } else if(CommandArgsParser.isDouble(args[2])){
                    x = CommandArgsParser.positionToBlockPosition(Double.parseDouble(args[2]));
                } else if(CommandArgsParser.isTilde(args[2])){
                    x = loc.getBlockX();
                } else{
                    return false;
                }

                if(CommandArgsParser.isInteger(args[3])){
                    z = Integer.parseInt(args[3]);
                } else if(CommandArgsParser.isDouble(args[3])){
                    z = CommandArgsParser.positionToBlockPosition(Double.parseDouble(args[3]));
                } else if(CommandArgsParser.isTilde(args[3])){
                    z = loc.getBlockZ();
                } else{
                    return false;
                }

                this.plugin.setPos1(x, z);
                sender.sendMessage("[RealMinecraft] set (x1, z1) = (" + x + ", " + z + ")");
                return true;
            }

            return false;
        }

        public List<String> tabComplete(CommandSender sender, Command command, String alias, String[] args){
            if(args.length == 2 && this.cmdStr.startsWith(args[1])) {
                return new ArrayList<>(Arrays.asList(this.cmdStr));
            }

            if(args[1].equalsIgnoreCase(this.cmdStr)){
                switch (args.length){
                    case 3:
                        return new ArrayList<>(Arrays.asList("~ ~"));
                    case 4:
                        return new ArrayList<>(Arrays.asList("~"));
                }
            }

            return null;
        }
    }

    // set pos2 x, z
    class Pos2 extends CommandAbstract{
        private RealMinecraft plugin;
        private String cmdStr = "pos2";

        public Pos2(RealMinecraft plugin){
            super();
            this.plugin = plugin;
        }

        public boolean executeCommand(CommandSender sender, Command command, String label, String[] args){
            if(args.length == 4 && args[1].equalsIgnoreCase(this.cmdStr)){
                int x, z;
                Location loc = ((Player) sender).getLocation();

                if(CommandArgsParser.isInteger(args[2])){
                    x = Integer.parseInt(args[2]);
                } else if(CommandArgsParser.isDouble(args[2])){
                    x = CommandArgsParser.positionToBlockPosition(Double.parseDouble(args[2]));
                } else if(CommandArgsParser.isTilde(args[2])){
                    x = loc.getBlockX();
                } else{
                    return false;
                }

                if(CommandArgsParser.isInteger(args[3])){
                    z = Integer.parseInt(args[3]);
                } else if(CommandArgsParser.isDouble(args[3])){
                    z = CommandArgsParser.positionToBlockPosition(Double.parseDouble(args[3]));
                } else if(CommandArgsParser.isTilde(args[3])){
                    z = loc.getBlockZ();
                } else{
                    return false;
                }

                this.plugin.setPos2(x, z);
                sender.sendMessage("[RealMinecraft] set (x2, z2) = (" + x + ", " + z + ")");
                return true;
            }

            return false;
        }

        public List<String> tabComplete(CommandSender sender, Command command, String alias, String[] args){
            if(args.length == 2 && this.cmdStr.startsWith(args[1])) {
                return new ArrayList<>(Arrays.asList(this.cmdStr));
            }

            if(args[1].equalsIgnoreCase(this.cmdStr)){
                switch (args.length){
                    case 3:
                        return new ArrayList<>(Arrays.asList("~ ~"));
                    case 4:
                        return new ArrayList<>(Arrays.asList("~"));
                }
            }

            return null;
        }
    }

    // set power value
    class Power extends CommandAbstract{
        private RealMinecraft plugin;
        private String cmdStr = "power";

        public Power(RealMinecraft plugin){
            super();
            this.plugin = plugin;
        }

        public boolean executeCommand(CommandSender sender, Command command, String label, String[] args){
            if(args.length == 3 && args[1].equalsIgnoreCase(this.cmdStr)){
                if(CommandArgsParser.isInteger(args[2])){
                    int value = Integer.parseInt(args[2]);

                    if(value > 0){
                        this.plugin.setPower(value);
                        sender.sendMessage("[RealMinecraft] set \"Power\" to " + value);
                        return true;
                    }
                }
            }

            return false;
        }

        public List<String> tabComplete(CommandSender sender, Command command, String alias, String[] args){
            if(args.length == 2 && this.cmdStr.startsWith(args[1])) {
                return new ArrayList<>(Arrays.asList(this.cmdStr));
            }

            if(args.length == 3 && args[1].equalsIgnoreCase(this.cmdStr)){
                return new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5"));
            }

            return null;
        }
    }

    // set damage value
    class Damage extends CommandAbstract{
        private RealMinecraft plugin;
        private String cmdStr = "damage";

        public Damage(RealMinecraft plugin){
            super();
            this.plugin = plugin;
        }

        public boolean executeCommand(CommandSender sender, Command command, String label, String[] args){
            if(args.length == 3 && args[1].equalsIgnoreCase(this.cmdStr)){
                if(CommandArgsParser.isInteger(args[2]) || CommandArgsParser.isDouble(args[2])){
                    double value = Double.parseDouble(args[2]);

                    if(value > 0){
                        this.plugin.setDamage(value);
                        sender.sendMessage("[RealMinecraft] set \"Damage\" to " + value);
                        return true;
                    }
                }
            }

            return false;
        }

        public List<String> tabComplete(CommandSender sender, Command command, String alias, String[] args){
            if(args.length == 2 && this.cmdStr.startsWith(args[1])) {
                return new ArrayList<>(Arrays.asList(this.cmdStr));
            }

            return null;
        }
    }

    // set probability value
    class Probability extends CommandAbstract{
        private RealMinecraft plugin;
        private String cmdStr = "probability";

        public Probability(RealMinecraft plugin){
            super();
            this.plugin = plugin;
        }

        public boolean executeCommand(CommandSender sender, Command command, String label, String[] args){
            if(args.length == 3 && args[1].equalsIgnoreCase(this.cmdStr)){
                if(CommandArgsParser.isInteger(args[2]) || CommandArgsParser.isDouble(args[2])){
                    double value = Double.parseDouble(args[2]);

                    if(value > 0){
                        this.plugin.setProbability(value);
                        sender.sendMessage("[RealMinecraft] set \"Probability\" to " + value);
                        return true;
                    }
                }
            }

            return false;
        }

        public List<String> tabComplete(CommandSender sender, Command command, String alias, String[] args){
            if(args.length == 2 && this.cmdStr.startsWith(args[1])) {
                return new ArrayList<>(Arrays.asList(this.cmdStr));
            }

            return null;
        }
    }
}
