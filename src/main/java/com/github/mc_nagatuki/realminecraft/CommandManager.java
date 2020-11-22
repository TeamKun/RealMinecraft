package com.github.mc_nagatuki.realminecraft;

import com.github.mc_nagatuki.realminecraft.commands.*;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class CommandManager implements CommandExecutor, TabCompleter {
    private RealMinecraft plugin;
    private CommandAbstract[] commands;

    public CommandManager(RealMinecraft plugin){
        this.plugin = plugin;

        this.commands = new CommandAbstract[]{
                new CmdOn(plugin),
                new CmdOff(plugin),
                new CmdHelp(plugin),
                new CmdSet(plugin),
                new CmdAutoLay(plugin),
        };
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if(args.length == 0) return false;

        for(CommandAbstract e : this.commands){
            boolean ret = e.executeCommand(sender, command, label, args);
            if(ret) return true;
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
        List<String> result = new ArrayList<>();

        for(CommandAbstract e : this.commands){
            List<String> ret = e.tabComplete(sender, command, alias, args);

            if(ret == null) continue;

            result.addAll(ret);
        }

        return result;
    }


//        if (args.length > 0) {
//            switch (args[0]) {
//                case "help":
//
//                    Stream.of(HELP_MESSAGE).forEach(sender::sendMessage);
//                    return true;
//                case "type":
//                    if (args.length <= 1)
//                        return false;
//                    // /pwb type <type>
//                    PushWorldBorder.behaviour = BorderBehaviour.from(args[1]);
//                    sender.sendMessage("[" + ChatColor.DARK_PURPLE + "PushWorldBorder" + ChatColor.RESET + "]種類を" + PushWorldBorder.behaviour + "に設定しました。");
//                    return true;
//                case "leader":
//                    if (args.length <= 1)
//                        return false;
//                    Player player = Bukkit.getPlayer(args[1]);
//                    if (player == null) {
//                        sender.sendMessage("プレイヤーが見つかりません");
//                        return true;
//                    }
//                    PushWorldBorder.leader = player;
//                    sender.sendMessage("[" + ChatColor.DARK_PURPLE + "PushWorldBorder" + ChatColor.RESET + "]リーダーを" + PushWorldBorder.leader.getName() + "に設定しました。");
//                    return true;
//            }
//        }

//        switch (args.length) {
//            case 1:
//                return Stream.of("help", "type", "leader")
//                        .filter(e -> e.startsWith(args[0]))
//                        .collect(Collectors.toList());
//            case 2:
//                switch (args[0]) {
//                    case "type":
//                        return Arrays.stream(BorderBehaviour.values())
//                                .map(e -> e.name().toLowerCase())
//                                .filter(e -> e.startsWith(args[1]))
//                                .collect(Collectors.toList());
//                    case "leader":
//                        return Bukkit.getOnlinePlayers().stream()
//                                .map(Player::getName)
//                                .filter(e -> e.startsWith(args[1]))
//                                .collect(Collectors.toList());
//                }
//        }
//        return Collections.emptyList();
}
