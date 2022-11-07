package kr.cafemoca.shimenawa;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Shimenawa extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		System.out.println("§cShimenawa§f For Neko initialized");
		Bukkit.getPluginManager().registerEvents(this, this);
	}

	@Override
	public void onDisable() {}

	private static final Material[] swords = new Material[] {
			Material.NETHERITE_SWORD, Material.DIAMOND_SWORD, Material.GOLDEN_SWORD, Material.IRON_SWORD, Material.STONE_SWORD, Material.WOODEN_SWORD
	};

	@EventHandler
	public void onChoponpoppy(PlayerInteractEvent e) { // not workinggggggg
		Player p = e.getPlayer();
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (e.getClickedBlock().getType() == Material.POPPY) {
				if (e.getHand().equals(swords)) {
					p.sendMessage("Right Clicked by types of sword");
				}
			}
		}
	}
}
