package com.github.mc_nagatuki.realminecraft;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

// TODO: 各プレイヤーモード時にどうなるか確認すべし
    // survivalのみ
// netherはどうなっている？
// モブに乗っていると爆発しないなぁ
// そのままの方がモブ探しになってネタが続くか

public final class RealMinecraft extends JavaPlugin {
    private PlayerMovementListener pml;
    private MineManager mm;
    private PlayerExploder pe;
    private CommandManager cmdMng;

    private boolean activated = false;
    private int power = 1;
    private double damage = 5.0;
    private double probability = 0.3; // default
    private int x1, z1, x2, z2;

    @Override
    public void onEnable() {
        this.mm = new MineManager();
        this.pe = new PlayerExploder();

        this.pml = new PlayerMovementListener(this);
        this.getServer().getPluginManager().registerEvents(this.pml, this);

        this.cmdMng = new CommandManager(this);
        this.getCommand("real").setExecutor(this.cmdMng);
    }

    public MineManager getMineManager(){
        return this.mm;
    }

    public PlayerExploder getPlayerExploder(){
        return this.pe;
    }

    public void setActivated(boolean flag){
        this.activated = flag;

//        if(flag){
//            this.getServer().broadcastMessage("§9" + "Nagatukiは地雷を踏み抜いた");
//        }else{
//            this.getServer().broadcastMessage("aho shine");
//        }
    }
    public boolean getActivated(){
        return this.activated;
    }

    public void setPower(int power){
        this.power = power;

//        this.getServer().broadcastMessage("power: " + this.power);
    }
    public int getPower(){
        return this.power;
    }

    public void setDamage(double damage) {
        this.damage = damage;

//        this.getServer().broadcastMessage("damage: " + this.damage);
    }
    public double getDamage() {
        return damage;
    }

    public void setProbability(double probability){
        this.probability = probability;

//        this.getServer().broadcastMessage("probability: " + this.probability);
    }
    public double getProbability() {
        return probability;
    }

    public void setPos1(int x, int z){
        this.x1 = x;
        this.z1 = z;

//        this.getServer().broadcastMessage("x: " + this.x1 + " y: " + this.z1);
    }
    public int getX1(){
        return this.x1;
    }
    public int getZ1(){
        return this.z1;
    }

    public void setPos2(int x, int z){
        this.x2 = x;
        this.z2 = z;
    }
    public int getX2(){
        return this.x2;
    }
    public int getZ2(){
        return this.z2;
    }

    // コマンド
    // 地雷設置
        // 自動設置
        // 全解除
    // アイテムクリックで爆弾解除
    // アイテムクリックで爆弾設置

    // 設定記録
}
