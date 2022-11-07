package kr.cafemoca.shimenawa;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class Shimenawa extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		System.out.println("Shimenawa For Neko initialized");
		Bukkit.getPluginManager().registerEvents(this, this);
	}

	@Override
	public void onDisable() {}

	private boolean isSword(ItemStack is) {
		return is.getType().name().contains("SWORD");
	}

	@EventHandler
	public void onChoponpoppy(PlayerInteractEvent e) { // now working but why text print out twice???
		Player p = e.getPlayer();
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (e.getClickedBlock().getType() == Material.POPPY) {
				if (isSword(p.getItemInHand())) {
					p.sendMessage("Right Clicked by types of swords on Poppy");
				}
			}
		}
	}
}
