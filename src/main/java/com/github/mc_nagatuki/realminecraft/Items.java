package com.github.mc_nagatuki.realminecraft;

import org.bukkit.Material;

public class Items {
    public static class Detector{
        public static Material material = Material.BLAZE_ROD;
        public static String id = "minecraft:blaze_rod";
        public static String name = "地雷探知機";
        public static String color = "green";
    }

    public static class Installer{
        public static Material material = Material.BOWL;
        public static String id = "minecraft:bowl";
        public static String name = "ほんものの地雷";
        public static String color = "red";
    }

    public static class Sweeper{
        public static Material material = Material.WHEAT;
        public static String id = "minecraft:wheat";
        public static String name = "マインスイーパー";
        public static String color = "yellow";
    }
}
