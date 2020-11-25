package com.github.mc_nagatuki.realminecraft.commands;

public class CommandArgsParser {
    public static boolean hasTilde(String ipt) {
        return ipt.equalsIgnoreCase("~");
    }

    public static int positionToBlockPosition(double pos) {
        int i = (int) pos;
        return pos < i ? i - 1 : i;
    }
}