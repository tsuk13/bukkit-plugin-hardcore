package edu.unca.twreese.Hardcore;

import java.util.Calendar;
import java.util.TimeZone;

import org.bukkit.World;
import org.bukkit.plugin.Plugin;

public class TimeChange implements Runnable {

	Plugin plugin;
	Calendar cal;
	World world;
	
	TimeChange(Plugin p){
		plugin = p;
		world = plugin.getServer().getWorld("world");
	}
	
	public void run() {
		cal = Calendar.getInstance(TimeZone.getTimeZone("EST"));
		world.setTime(((((long)cal.get(Calendar.HOUR_OF_DAY) + ((long)cal.get(Calendar.MINUTE) / 60))*1000) + 18000) % 24000);
	}

}
