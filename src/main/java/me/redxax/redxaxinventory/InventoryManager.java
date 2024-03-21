package me.redxax.redxaxinventory;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InventoryManager {
    private Map<UUID, ItemStack[]> savedInventories;

    public InventoryManager() {
        this.savedInventories = new HashMap<>();
    }

    public void saveInventory(Player player) {
        UUID playerId = player.getUniqueId();
        savedInventories.put(playerId, player.getInventory().getContents());
    }

    public void clearInventory(Player player) {
        player.getInventory().clear();
    }

    public void restoreInventory(Player player) {
        UUID playerId = player.getUniqueId();
        if (savedInventories.containsKey(playerId)) {
            player.getInventory().setContents(savedInventories.get(playerId));
            savedInventories.remove(playerId);
        }
    }
}