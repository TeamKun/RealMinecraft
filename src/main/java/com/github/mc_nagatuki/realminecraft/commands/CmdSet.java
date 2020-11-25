package com.github.mc_nagatuki.realminecraft.commands;

import com.github.mc_nagatuki.realminecraft.BlockPosition;
import com.github.mc_nagatuki.realminecraft.RealMinecraft;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.function.Consumer;

public class CmdSet extends CommandAbstract {
    private String cmdStr = "set";
    private CommandAbstract[] subCommands = new CommandAbstract[]{
            new Pos("pos1", RealMinecraft.plugin.getMineConfig()::setPos1),
            new Pos("pos2", RealMinecraft.plugin.getMineConfig()::setPos2),
            new Power(),
            new Damage(),
            new Probability(),
    };

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
            return Collections.singletonList(this.cmdStr);
        }

        if(args.length == 1){
            if(this.cmdStr.startsWith(args[0])){
                return Collections.singletonList(this.cmdStr);
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


    // set pos? x, z
    private static class Pos extends CommandAbstract{
        private final String cmdStr;
        private final Consumer<BlockPosition> blockPosConsumer;

        public Pos(String cmdStr, Consumer<BlockPosition> blockPosConsumer) {
            this.cmdStr = cmdStr;
            this.blockPosConsumer = blockPosConsumer;
        }

        private Optional<Integer> parsePosArg(String arg, int base) {
            try {
                return Optional.of(CommandArgsParser.positionToBlockPosition(
                        (CommandArgsParser.hasTilde(arg) ? base : 0) + Double.parseDouble(arg.substring(1))
                ));
            } catch (NumberFormatException ignored) {
            }
            return Optional.empty();
        }

        public boolean executeCommand(CommandSender sender, Command command, String label, String[] args){
            if(args.length == 4 && args[1].equalsIgnoreCase(this.cmdStr)){
                Location loc = ((Player) sender).getLocation();

                Optional<Integer> xOptional = parsePosArg(args[2], loc.getBlockX());
                Optional<Integer> zOptional = parsePosArg(args[3], loc.getBlockZ());
                if (!xOptional.isPresent() || !zOptional.isPresent())
                    return false;

                BlockPosition pos = new BlockPosition(xOptional.get(), zOptional.get());
                blockPosConsumer.accept(pos);

                sender.sendMessage("[RealMinecraft] set " + cmdStr + " = " + pos);

                return true;
            }

            return false;
        }

        public List<String> tabComplete(CommandSender sender, Command command, String alias, String[] args){
            if(args.length == 2 && this.cmdStr.startsWith(args[1])) {
                return Collections.singletonList(this.cmdStr);
            }

            if(args[1].equalsIgnoreCase(this.cmdStr)){
                switch (args.length){
                    case 3:
                        return Collections.singletonList("~ ~");
                    case 4:
                        return Collections.singletonList("~");
                }
            }

            return null;
        }
    }

    // set power value
    private static class Power extends CommandAbstract{
        private String cmdStr = "power";

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
                return Arrays.asList(this.cmdStr);
            }

            if(args.length == 3 && args[1].equalsIgnoreCase(this.cmdStr)){
                return Arrays.asList("1", "2", "3", "4", "5");
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
                return Arrays.asList(this.cmdStr);
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
                return Arrays.asList(this.cmdStr);
            }

            return null;
        }
    }
}
