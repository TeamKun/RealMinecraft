package com.github.mc_nagatuki.realminecraft;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMovementListener implements Listener {
    private RealMinecraft plugin;

    public PlayerMovementListener(RealMinecraft plugin){
        this.plugin = plugin;
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerMoveEvent(PlayerMoveEvent e){
        // プラグインがオフなら何もしない
        if(!this.plugin.getActivated()) return;

        // プレイヤーを取得
        Player p = e.getPlayer();

        // プレイヤーがクリエイティブかスペクテイターならなにもしない
        if(p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR) return;

        // プレイヤーが浮いていたら何もしない
        // ただし乗り物に乗っている場合を除く
        // 厳密にやるならy座標を見た方がいいらしい
        if(!(p.isOnGround() || p.isInsideVehicle())) return;

        //移動先の座標を得る
        Location loc = e.getTo();
        int x = loc.getBlockX();
        int z = loc.getBlockZ();

        // 当該座標に地雷がなければ何もしない
        if(!this.plugin.getMineManager().hasMine(x, z)) return;

        // 爆発処理
        this.plugin.getPlayerExploder().explode(loc, p, this.plugin.getPower(), this.plugin.getDamage());

        // 地雷除去（爆発しても地雷がなくならないようにするならここを変更）
        this.plugin.getMineManager().demine(x, z);

        this.plugin.getServer().broadcastMessage("§9"+e.getPlayer().getName()+"は地雷を踏み抜いた");
    }
}
