package kr.cafemoca.shimenawa;

import kr.cafemoca.shimenawa.handler.Opium;
import kr.cafemoca.shimenawa.listener.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Shimenawa extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
		Bukkit.getPluginManager().registerEvents(new Opium(), this);
	}

	@Override
	public void onDisable() {}

}
