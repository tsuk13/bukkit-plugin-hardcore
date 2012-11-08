package edu.unca.twreese.Hardcore;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

/*
 * This is a sample event listener
 */
public class HardcoreListener implements Listener {
    @SuppressWarnings("unused")
	private final Hardcore plugin;
    private final int worldSize = 21550; //defines how many blocks a side of the square you are restricted you in this mod is

    /*
     * This listener needs to know about the plugin which it came from
     */
    public HardcoreListener(Hardcore plugin) {
        // Register the listener
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
    	if(!event.getPlayer().hasPlayedBefore()){
    		Location loc = new Location(event.getPlayer().getWorld(), 0, 0, 0);
        	Random rnd = new Random();
        	loc.setX(loc.getX() -(worldSize/2) + (rnd.nextDouble() * worldSize));
        	loc.setZ(loc.getZ() - (worldSize/2)+ (rnd.nextDouble() * worldSize));
        	loc.setY(event.getPlayer().getWorld().getHighestBlockYAt(loc));
        	event.getPlayer().getWorld().loadChunk(loc.getChunk());
        	while(!loc.getChunk().isLoaded()){
        	}
        	event.getPlayer().teleport(loc);
    	}
    	event.getPlayer().sendMessage("This is a Hardcore Server.");
    	event.getPlayer().sendMessage("On death you will spawn somewhere random in a great distance.");
    	event.getPlayer().sendMessage("This may cause you to be kicked relog and it will have finished loading your new spawn.");
    }
    

    
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event){
    	Location loc = new Location(event.getPlayer().getWorld(), 0, 0, 0);
    	Random rnd = new Random();
    	loc.setX(loc.getX()-(worldSize/2) + (rnd.nextDouble() * worldSize));
    	loc.setZ(loc.getZ() - (worldSize/2) + (rnd.nextDouble() * worldSize));
    	loc.setY(event.getPlayer().getWorld().getHighestBlockYAt(loc));
    	event.getPlayer().getWorld().loadChunk(loc.getChunk());
    	while(!loc.getChunk().isLoaded()){
    	}
    	event.setRespawnLocation(loc);
    }
    
}
