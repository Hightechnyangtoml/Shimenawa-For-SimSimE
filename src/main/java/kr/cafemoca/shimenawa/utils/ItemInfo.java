package kr.cafemoca.shimenawa.utils;

import com.google.common.collect.Lists;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemInfo {
	private final ItemStack item;
	private ItemMeta meta;

	public ItemInfo(@Nonnull ItemStack item) {
		this.item = item;
		if(item.hasItemMeta()) {
			this.meta = item.getItemMeta();
		}
	}

	public String getLocalizedName() {
		if(meta != null && meta.hasLocalizedName()) {
			return meta.getLocalizedName();
		}
		return "";
	}

	@Nonnull
	public String getDisplayName() {
		if(meta != null && meta.hasDisplayName()) {
			return meta.getDisplayName();
		}
		return "";
	}

	@Nonnull
	public List<String> getLore() {
		if(meta != null && meta.hasLore()) {
			return meta.getLore();
		}
		return Lists.newArrayList();
	}

	@Nonnull
	public String getLore(int index) {
		List<String> lore = getLore();

		return index <= lore.size()-1 ? lore.get(index) : "";
	}

	public byte getData() {
		return item.getData().getData();
	}

	@Nonnull
	public ItemStack getItemStack(boolean clone) {
		return clone ? item.clone() : item;
	}
}
