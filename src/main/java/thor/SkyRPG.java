package thor;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public final class SkyRPG extends JavaPlugin {
    public static World lobby;
    public static World world;
    public static BukkitScheduler scheduler;
    public static Plugin plugin;

    @Override
    public void onEnable() {
        lobby = Bukkit.getWorld("world");
        world = Bukkit.createWorld(new WorldCreator("skyRPG"));
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
        world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        getServer().getPluginManager().registerEvents(new MyListener(), this);
        startTimer();
    }
    public static void startTimer() {
        scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {

            }
        }, 0L, 20L);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
