package kr.cafemoca.shimenawa.handler;

import kr.cafemoca.shimenawa.utils.PluginItems;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class Opium implements Listener {

	private boolean isSword(ItemStack is) {
		return is.getType().name().contains("SWORD");
	}

	private static Integer getRandomInt(Integer max) {
		Random ran = new Random();
		return ran.nextInt(max);
	}

	@EventHandler
	public void onChoponpoppy(PlayerInteractEvent e) {
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
							e.getClickedBlock().getWorld().dropItem(e.getClickedBlock().getLocation(),
									PluginItems.of(Material.LEGACY_MILK_BUCKET).data((byte) 1)
											.name("§f액체 아편").lore("§c섭취 불가")
											.lore("")
											.lore("§7양귀비에서 나온 액체.")
											.lore("§7가공하면 큰 효과를 낼 수 있다.").create());
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
