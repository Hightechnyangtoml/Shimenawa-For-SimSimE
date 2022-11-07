package kr.cafemoca.shimenawa;

import kr.cafemoca.shimenawa.utils.PluginItems;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

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

	private static Integer getRandomInt(Integer max) {
		Random ran = new Random();
		return ran.nextInt(max);
	}

	@EventHandler
	public void onChoponpoppy(PlayerInteractEvent e) {
		/* java.lang.IllegalArgumentException: Cannot get new data of Modern Material
		   at kr.cafemoca.shimenawa.utils.PluginItems.create(PluginItems.java:79) ~[shimenawa-0.1.jar:?]
           at kr.cafemoca.shimenawa.Shimenawa.onChoponpoppy(Shimenawa.java:54) ~[shimenawa-0.1.jar:?]
		 */
		Player p = e.getPlayer();
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (e.getClickedBlock().getType() == Material.POPPY) {
				if (isSword(p.getItemInHand())) {
					int random = getRandomInt(2);
					switch (random) {
						case 0:
							p.sendMessage("§cYou failed to get liquid opium.");
							e.getClickedBlock().setType(Material.AIR);
							p.playSound(p, Sound.BLOCK_GRASS_BREAK, 1.0F, 0.8F);
							break;
						case 1:
							p.sendMessage("§aYou have successfully obtained liquid opium");
							e.getClickedBlock().setType(Material.AIR);
							p.playSound(p, Sound.BLOCK_GRASS_BREAK, 1.0F, 0.8F);
							e.getClickedBlock().getWorld().dropItem(e.getClickedBlock().getLocation(), new ItemStack(PluginItems.of(Material.MILK_BUCKET).data((byte) 1).name("액체 아편").lore("§c섭취 불가").lore("").lore("양귀비에서 나온 액체.").lore("가공하면 큰 효과를 낼 수 있다.").create()));
							break;
						default:
							p.sendMessage("default error");
							break;
					}
				}
			}
		}
	}
}
