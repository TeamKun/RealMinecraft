package com.github.mc_nagatuki.realminecraft.commands;

import com.github.mc_nagatuki.realminecraft.RealMinecraft;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CmdOff extends CommandAbstract {
    private RealMinecraft plugin;
    private String cmdStr = "off";

    public CmdOff(RealMinecraft plugin) {
        super();
        this.plugin = plugin;
    }

    public boolean executeCommand(CommandSender sender, Command command, String label, String[] args){
        if(args.length == 1 && args[0].equalsIgnoreCase(this.cmdStr)){
            this.plugin.setActivated(false);
            sender.sendMessage("[RealMinecraft] 無効化しました。");
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