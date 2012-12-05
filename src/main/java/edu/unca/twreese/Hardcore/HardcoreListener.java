package edu.unca.twreese.Hardcore;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.server.PluginEnableEvent;

/*
 * This is a sample event listener
 */
public class HardcoreListener implements Listener {
    private final Hardcore plugin;
    private final int worldSize = 21550; //defines how many blocks a side of the square you are restricted you in this mod is
    private World world;
    WeatherChange wet;

    /*
     * This listener needs to know about the plugin which it came from
     */
    public HardcoreListener(Hardcore plugin) {
        // Register the listener
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        
        this.plugin = plugin;
        world = plugin.getServer().getWorld("world");
        wet = new WeatherChange(plugin, "9f545d0dc34e7b2a");
    }
    
    @EventHandler
    public void onPluginEnable(PluginEnableEvent event){
    	event.getPlugin().getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new TimeChange(plugin), 0, 20);
    	event.getPlugin().getServer().getScheduler().scheduleSyncRepeatingTask(plugin, wet, 0, 60);
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
    	wet.run();
    	if(!event.getPlayer().hasPlayedBefore()){
    		Location loc = new Location(world, 0, 63, 0);
        	Random rnd = new Random();
        	boolean looking = true;
        	while(looking){
        		loc.setX(loc.getX() -(worldSize/2) + (rnd.nextDouble() * worldSize));
        		loc.setZ(loc.getZ() - (worldSize/2)+ (rnd.nextDouble() * worldSize));
        		loc.getChunk().load(true);
        		loc.setY(world.getHighestBlockYAt(loc));
        		if(world.getBiome(loc.getBlockX(), loc.getBlockZ()).compareTo(Biome.OCEAN) != 0)
        			looking = false;
        	}
        	event.getPlayer().teleport(loc.add(0, 2, 0));
    	}
    	event.getPlayer().sendMessage("This is a Hardcore Server.");
    	event.getPlayer().sendMessage("On death you will spawn somewhere random in a great distance.");
    	event.getPlayer().sendMessage("This may cause you to be kicked relog and it will have finished loading your new spawn.");
    }
    

    
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event){
    	Location loc = new Location(world, 0, 63, 0);
    	Random rnd = new Random();
    	boolean looking = true;
    	while(looking){
    		loc.setX(loc.getX() -(worldSize/2) + (rnd.nextDouble() * worldSize));
    		loc.setZ(loc.getZ() - (worldSize/2)+ (rnd.nextDouble() * worldSize));
    		loc.getChunk().load(true);
    		loc.setY(world.getHighestBlockYAt(loc));
    		if(world.getBiome(loc.getBlockX(), loc.getBlockZ()).compareTo(Biome.OCEAN) != 0)
    			looking = false;
    	}
    	event.setRespawnLocation(loc.add(0, 2, 0));
    }
    
}
