package com.github.mc_nagatuki.realminecraft;

import org.bukkit.plugin.java.JavaPlugin;

// TODO: netherはどうなっている？
    // 同じ座標で爆発した
    // ワールドごとに地雷位置変えるなら持ち訳ないと...
// TODO: モブに乗っていると爆発しないなぁ
    // そのままの方がモブ探しになってネタが続くか
    // Entityの上に乗っていたら爆発させるのもあり
// TODO: 火もコマンドでオンオフするか
    // そうすれば延焼オフとの組み合わせで分かり易く爆発できるかも

public final class RealMinecraft extends JavaPlugin {
    private PlayerMovementListener pml;
    private MineManager mm;
    private PlayerExploder pe;
    private CommandManager cmdMng;

    private int power = 1;
    private int x1 = 0, z1 = 0, x2 = 0, z2 = 0;
    private double damage = 5.0;
    private double probability = 0.3;
    private boolean activated = false;

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

    public void setPos1(int x, int z){
        this.x1 = x;
        this.z1 = z;
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


    // アイテムクリックで爆弾解除
    // アイテムクリックで爆弾設置

    // 設定記録
}
