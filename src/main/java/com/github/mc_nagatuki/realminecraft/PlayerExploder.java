package com.github.mc_nagatuki.realminecraft;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerExploder {
    public void explode(Location l, Player p, int power, double damage){
        l.getWorld().createExplosion(l, power, false, false);
        this.causeDamage(p, damage);
    }

    private void causeDamage(Player p, double damage){
        // TODO: 実装する
        // NOTE: でもその前にダメージの有無を確認
    }
}
