package kr.cafemoca.shimenawa.handler;

import kr.cafemoca.shimenawa.utils.PluginItems;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.data.type.Campfire;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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

	Inventory inv = Bukkit.createInventory(null, 9, "건조대");

	ItemStack pane = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
	ItemStack green = new ItemStack(Material.LIME_STAINED_GLASS);

	@EventHandler
	public void onClickCampFire(PlayerInteractEvent e) { // ???????
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Player p = e.getPlayer();
			if (e.getClickedBlock().getBlockData() instanceof Campfire) {
				e.setCancelled(true);

				ItemMeta meta = pane.getItemMeta();
				meta.setDisplayName("§r");
				pane.setItemMeta(meta);

				ItemMeta greenmeta = green.getItemMeta();
				greenmeta.setDisplayName("§r§a건조하기");
				green.setItemMeta(greenmeta);

				ItemStack inf = PluginItems.of(Material.LEGACY_PAPER).name("§r§9§l[도움말]").lore("§r§f특정 아이템을 건조 또는 말릴수 있습니다.").create();

				inv.setItem(0, inf);
				inv.setItem(1, pane);
				inv.setItem(2, pane);
				inv.setItem(3, pane);
				inv.setItem(5, pane);
				inv.setItem(6, pane);
				inv.setItem(7, pane);
				inv.setItem(8, green);

				p.openInventory(inv);
			}
		}
	}

	@EventHandler
	public void onInvPaneClick(InventoryClickEvent e) {
		if (e.getCurrentItem() != null) {
			if (e.getCurrentItem().getType() == Material.WHITE_STAINED_GLASS_PANE || e.getCurrentItem().getType() == Material.PAPER) {
				e.setCancelled(true);
			}
			if (e.getCurrentItem().getType() == Material.LIME_STAINED_GLASS) {
				e.setCancelled(true);

			}
		}
	}

	@EventHandler
	public void onCloseInv(InventoryCloseEvent e) {
		if (e.getInventory() == inv) {
			Player p = (Player) e.getPlayer();
			ItemStack backitem = inv.getItem(4);
			p.getInventory().addItem(backitem);
			inv.removeItem(backitem);
		}
	}
}
