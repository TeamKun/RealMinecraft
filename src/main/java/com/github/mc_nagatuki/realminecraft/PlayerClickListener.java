package com.github.mc_nagatuki.realminecraft;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerClickListener implements Listener {
    private RealMinecraft plugin;

    public PlayerClickListener(RealMinecraft plugin){
        this.plugin = plugin;
    }

    @EventHandler(ignoreCancelled = true)
    public void onItemClick(PlayerInteractEvent e){
        Player p = e.getPlayer();
        Action a = e.getAction();

        // 右クリックしてなかったら無視する
        if(a != Action.RIGHT_CLICK_BLOCK) return;
        // アイテムを持っていなかったら無視する
        if(e.getItem() == null) return;

        ItemStack item = e.getItem();
        Material type = item.getType();
        ItemMeta im = item.getItemMeta();

        Block block = e.getClickedBlock();
        int x = block.getX();
        int z = block.getZ();

        // Detector
        if(type == Items.Detector.material && im.getDisplayName().endsWith(Items.Detector.name)){
            if(this.plugin.getMineManager().hasMine(x, z)){
                p.sendMessage(ChatColor.RED +  "地雷が埋まっています");
            } else{
                p.sendMessage(ChatColor.GREEN +  "地雷は見つかりませんでした");
            }
        }

        // Installer
        if(type == Items.Installer.material && im.getDisplayName().endsWith(Items.Installer.name)){
            GameMode gm = p.getGameMode();
            if(gm == GameMode.SURVIVAL || gm == GameMode.ADVENTURE){
                // アイテムを一個減らす
                int num = item.getAmount();
                if(num > 0) --num;
                item.setAmount(num);
            }

            this.plugin.getMineManager().lay(x, z);
            p.sendMessage("地雷を設置しました");
        }

        // Sweeper
        if(type == Items.Sweeper.material && im.getDisplayName().endsWith(Items.Sweeper.name)){
            if(this.plugin.getMineManager().hasMine(x, z)){
                this.plugin.getMineManager().demine(x, z);
            } else{
                this.plugin.getMineManager().lay(x, z);
            }
            p.sendMessage("地雷を除去しました");
        }

//       this.plugin.getServer().broadcastMessage("§9"+e.getPlayer().getName()+"は地雷を踏み抜いた");
    }
}
