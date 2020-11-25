package com.github.mc_nagatuki.realminecraft;

import org.bukkit.plugin.java.JavaPlugin;

public final class RealMinecraft extends JavaPlugin {
    public static RealMinecraft plugin;

    private MineManager mm;
    private MineConfiguration config;

    @Override
    public void onEnable() {
        plugin = this;

        this.mm = new MineManager();
        this.config = new MineConfiguration();

        // Events
        this.getServer().getPluginManager().registerEvents(new PlayerMovementListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerClickListener(), this);

        // Commands
        this.getCommand("real").setExecutor(new CommandManager());
    }

    public MineManager getMineManager() {
        return this.mm;
    }

    public MineConfiguration getMineConfig() {
        return this.config;
    }

}
