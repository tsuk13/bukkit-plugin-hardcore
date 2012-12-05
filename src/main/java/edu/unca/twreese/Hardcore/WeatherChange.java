package edu.unca.twreese.Hardcore;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.bukkit.plugin.Plugin;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class WeatherChange implements Runnable {
	
	Plugin plugin;
	String code;
	URL uri;
	int cycle;
	boolean isUpToDate;
	
	WeatherChange(Plugin plugin, String code){
		this.plugin = plugin;
		this.code = code;
		try{
		uri = new URL("http://api.wunderground.com/api/" + code + "/conditions/q/NC/Asheville.json");
		} catch(Exception ex){
			System.out.println(ex);
		}
		cycle = 72000;
	}

	public void run() {
		if(plugin.getServer().getOnlinePlayers().length > 0){
			if(isUpToDate){
				cycle -= 60;
				if(cycle <= 0)
					isUpToDate = false;
				return;
			}
			JSONParser parser = new JSONParser();
			Object obj;
			try{
				obj = parser.parse(new InputStreamReader(uri.openStream()));
			} catch(Exception ex){
				System.out.println(ex);
				System.out.println(uri);
				return;
			}
			JSONObject jsonObject = (JSONObject) obj;
			String weather = (String)(((JSONObject)(jsonObject.get("current_observation"))).get("weather"));
			System.out.println(weather);
			weather = weather.toLowerCase();
			//set the weather based on input
			
			if(weather.contains("rain") || weather.contains("snow") || weather.contains("storm")){
				plugin.getServer().getWorld("world").setStorm(true);
			}
			if(weather.contains("thunder")){
				plugin.getServer().getWorld("world").setThundering(true);
				plugin.getServer().getWorld("world").setThunderDuration(72000);
			}
			plugin.getServer().getWorld("world").setWeatherDuration(72000);
			//
			cycle = 72000;
			isUpToDate = true;
		}
	}
	

}
