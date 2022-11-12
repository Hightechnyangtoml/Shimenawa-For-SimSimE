package kr.cafemoca.shimenawa.handler;

import kr.cafemoca.shimenawa.utils.ItemInfo;
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
							e.getClickedBlock().getWorld().dropItem(e.getClickedBlock().getLocation(), liquidopium);
							break;
						default:
							p.sendMessage("default error");
							break;
					}
				}
			}
		}
	}

	// -----------------------↑ 아편 채취-아이템 ↓---------------------------------------

	ItemStack liquidopium = PluginItems.of(Material.LEGACY_MILK_BUCKET).data((byte) 1)
			.name("§f액체 아편")
			.lore("§c섭취 불가")
			.lore("")
			.lore("§7양귀비에서 나온 액체.")
			.lore("§7가공하면 큰 효과를 낼 수 있다.").create();

	Inventory inv = Bukkit.createInventory(null, 9, "건조대");

	ItemStack pane = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
	ItemStack green = new ItemStack(Material.LIME_STAINED_GLASS);

	ItemStack opium = PluginItems.of(Material.LEGACY_RABBIT_STEW)
			.name("§r§c아편")
			.lore("§r§f사용 시 엄청난 힘을 얻을 수 있다.")
			.lore("§r§7엄청난 힘에는 항상 댓가가 따른다...").create();

	// -----------------------↑ 아이템-건조기 ↓---------------------------------------

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
		ItemStack getslot = inv.getItem(4);
		Player p = (Player) e.getWhoClicked();
			if (e.getClickedInventory() == inv) {
				if (e.getCurrentItem() != null) {
					if (e.getCurrentItem().getType() == Material.WHITE_STAINED_GLASS_PANE || e.getCurrentItem().getType() == Material.PAPER) {
						e.setCancelled(true);
					} else if (e.getCurrentItem().getType() == Material.LIME_STAINED_GLASS) {
						e.setCancelled(true);
						if (getslot != null) {
							ItemInfo info = new ItemInfo(getslot);
						if (inv.getItem(4).getType() == null)	{
							p.sendMessage("§c아이템이 존재하지 않습니다!");
						}else if (info.getDisplayName().equalsIgnoreCase("§f액체 아편")) {
							p.sendMessage("§a아이템을 건조하는데 성공했습니다!");
							inv.removeItem(getslot);
							p.playSound(p, Sound.BLOCK_FIRE_EXTINGUISH, 1, 1);
							inv.setItem(4, opium);
						}else {
							p.sendMessage("§c해당 아이템은 건조할 수 없습니다!");
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onCloseInv(InventoryCloseEvent e) {
		if (e.getInventory() == inv) {
			Player p = (Player) e.getPlayer();
			ItemStack backitem = inv.getItem(4);
			if (backitem != null) {
				p.getInventory().addItem(backitem);
				inv.removeItem(backitem);
			}
		}
	}

	// -----------------------↑ 건조기-아편 ↓---------------------------------------
	// 사용시 힘4 신속 2 점강1 저항2 2분 멀미, 허기, 독 4초 즉시사용
	//ToDo: 작동하게 하기
	@EventHandler
	public void onUseOpium(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		ItemInfo info = new ItemInfo(p.getItemInHand());
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
			if (info.getDisplayName().equalsIgnoreCase("§r§c아편")) {
				p.getInventory().removeItem(p.getItemInHand());
				p.playSound(p, Sound.ENTITY_GENERIC_DRINK, 1, 1);
			}
		}
	}
}
