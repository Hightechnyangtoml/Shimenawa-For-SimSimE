package kr.cafemoca.shimenawa.handler;

import kr.cafemoca.shimenawa.utils.PluginItems;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
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
							p.sendMessage("§c액체 아편 획득에 실패하였습니다.");
							e.getClickedBlock().setType(Material.AIR);
							p.playSound(p, Sound.BLOCK_GRASS_BREAK, 1.0F, 0.8F);
							break;
						case 1:
							p.sendMessage("§a액체 아편 획득에 성공하셨습니다.");
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

	@EventHandler
	public void onClickCampFire(PlayerInteractEvent e) { // 작동안해 tq
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Player p = e.getPlayer();
			if (e.getClickedBlock().getType() == Material.CAMPFIRE) {
				ItemStack NULL = PluginItems.of(Material.WHITE_STAINED_GLASS_PANE).name("§r").create();
				Inventory inv = Bukkit.createInventory(null, 9, "건조기");
				inv.setItem(1, NULL);

				p.openInventory(inv);
			}
		}
	}
}
