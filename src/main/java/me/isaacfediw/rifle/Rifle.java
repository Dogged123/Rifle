package me.isaacfediw.rifle;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Rifle extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("Rifle plugin has started successfully!!");

        Bukkit.getServer().getPluginManager().registerEvents(new rifleworkings(this), this);
    }
}
