package com.github.mc_nagatuki.realminecraft.commands;

import com.github.mc_nagatuki.realminecraft.RealMinecraft;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CmdHelp extends CommandAbstract {
    private RealMinecraft plugin;
    private String cmdStr = "help";

    final static String[] HELP_MESSAGE = {
            "--------------[" + ChatColor.BLUE + "This is \"Real\" Minecraft!" + ChatColor.RESET + "]--------------",
            "/real help: ヘルプ表示",
            "/real < on | off >: プラグインを有効化/無効化",
            "/real set < pos1 | pos2 | power | damage | probability >: 種々のパラメータを設定",
            "   pos1: ランダム設置の始点, pos2: ランダム設置の終点  power: 爆発の大きさ",
            "   damage: 与ダメージ量, probability: ランダム設置の頻度",
            "-------------------------------------------------",
    };

    public CmdHelp(RealMinecraft plugin) {
        super();
        this.plugin = plugin;
    }

    public boolean executeCommand(CommandSender sender, Command command, String label, String[] args){
        if(args.length == 1 && args[0].equalsIgnoreCase(this.cmdStr)){
            Stream.of(HELP_MESSAGE).forEach(sender::sendMessage);
            return true;
        }

        return false;
    }

    public List<String> tabComplete(CommandSender sender, Command command, String alias, String[] args){
        if(args.length == 1 && this.cmdStr.startsWith(args[0])){
            return new ArrayList<>(Arrays.asList(this.cmdStr));
        }

        return null;
    }
}
