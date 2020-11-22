package com.github.mc_nagatuki.realminecraft.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandArgsParser {
    private static Pattern intPattern = Pattern.compile("\\A[-]?[0-9]+\\z");
    private static Pattern doublePattern = Pattern.compile("\\A[-]?[0-9]+\\.[0-9]+\\z");

    public static boolean isInteger(String ipt){
        Matcher m = CommandArgsParser.intPattern.matcher(ipt);
        return m.find();
    }

    public static boolean isDouble(String ipt){
        Matcher m = CommandArgsParser.doublePattern.matcher(ipt);
        return m.find();
    }

    public static boolean isNumber(String ipt){
        return CommandArgsParser.isInteger(ipt) || CommandArgsParser.isDouble(ipt);
    }

    public static boolean isTilde(String ipt){
        return ipt.equalsIgnoreCase("~");
    }

    public static int positionToBlockPosition(double pos){
        if(pos < 0){
            pos = -1 * Math.ceil(-1 * pos);
        }
        return (int) pos;
    }
}