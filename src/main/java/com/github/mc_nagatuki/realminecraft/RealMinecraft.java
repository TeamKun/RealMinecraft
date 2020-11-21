package com.github.mc_nagatuki.realminecraft;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

// TODO: 各プレイヤーモード時にどうなるか確認すべし
// モブに乗っていると爆発しないなぁ
// そのままの方がモブ探しになってネタが続くか

public final class RealMinecraft extends JavaPlugin {
    private PlayerMovementListener pml;
    private MineManager mm;
    private PlayerExploder pe;

    private boolean activated = false;
    private int power = 1;
    private double damage = 5.0;
    private double probability = 0.3; // default
    // start position
    // end position

    @Override
    public void onEnable() {
        this.mm = new MineManager();
        this.pe = new PlayerExploder();

        this.pml = new PlayerMovementListener(this);
        getServer().getPluginManager().registerEvents(this.pml, this);
    }

    @Override
    public void onDisable(){
    }

    public MineManager getMineManager(){
        return this.mm;
    }

    public PlayerExploder getPlayerExploder(){
        return this.pe;
    }

    public void setActivated(boolean flag){
        this.activated = flag;
    }
    public boolean getActivated(){
        return this.activated;
    }

    public void setPower(int power){
        this.power = power;
    }
    public int getPower(){
        return this.power;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }
    public double getDamage() {
        return damage;
    }

    public void setProbability(double probability){
        this.probability = probability;
    }
    public double getProbability() {
        return probability;
    }


    // コマンド
    // 地雷設置
    // 各種変数設定
    // 設定記録
    // プラグイン起動・終了
}
