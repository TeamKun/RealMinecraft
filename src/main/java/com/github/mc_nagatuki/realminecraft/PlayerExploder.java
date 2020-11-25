package com.github.mc_nagatuki.realminecraft;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerExploder {
    public static void explode(Location l, Player p, int power, double damage) {
        l.getWorld().createExplosion(l, power, false, false);
    }
}
