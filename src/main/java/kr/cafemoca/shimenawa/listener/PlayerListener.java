package kr.cafemoca.shimenawa.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class PlayerListener implements Listener {

	private boolean isCantEatFood(List<String> lore) {
		for (String st : lore)
			if (st.startsWith("§c섭취 불가"))
				return true;
		return false;
	}

	@EventHandler
	public void onTryToEatCantEatFood(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		ItemMeta im = p.getItemInHand().getItemMeta();
		List<String> lore = im.hasLore() ? im.getLore() : new ArrayList<String>();
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (isCantEatFood(lore)) {
				e.setCancelled(true);
				p.sendMessage("§c해당 아이템은 섭취할 수 없습니다.");
			}
		}
	}
}
