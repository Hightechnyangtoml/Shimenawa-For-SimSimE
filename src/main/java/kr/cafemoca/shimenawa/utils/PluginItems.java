package kr.cafemoca.shimenawa.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("deprecation")
public class PluginItems {
	private Material material;
	private int amount = 1;
	private short dur = 0;
	private byte data =0;
	private String name;
	private boolean hideFlag = false;
	private boolean hideDur = false;
	private final List<String> lore = new ArrayList<>();

	private PluginItems(Material material) {
		this.material = material;
	}

	public static PluginItems of(Material material) {
		return new PluginItems(material);
	}

	public PluginItems data(byte b) {
		this.data = b;
		return this;
	}

	public PluginItems amount(int i) {
		this.amount = i;
		return this;
	}

	public PluginItems name(String name) {
		this.name = name;
		return this;
	}

	public PluginItems lore() {
		this.lore.add("");
		return this;
	}

	public PluginItems lore(String... lore) {
		this.lore.addAll(Arrays.asList(lore));
		return this;
	}

	public PluginItems lore(String lore) {
		this.lore.add(lore);
		return this;
	}

	public PluginItems durability(short value) {
		this.dur = value;
		return this;
	}

	public PluginItems hideFlag() {
		this.hideFlag = true;
		return this;
	}

	public PluginItems hideDurability() {
		this.hideDur = true;
		return this;
	}

	@Nonnull
	public ItemStack create() {
		ItemStack item = new ItemStack(material, 1, (byte) 0, data);
		item.setAmount(amount);
		if(dur > 0) item.setDurability(dur);

		ItemMeta meta = item.getItemMeta();

		if(hideFlag) meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		if(hideDur) {
			meta.setUnbreakable(true);
			meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		}
		if(name != null) meta.setDisplayName(name);
		if(lore.size() > 0) meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
}
