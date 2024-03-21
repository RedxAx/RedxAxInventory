package me.redxax.redxaxinventory;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

import java.util.UUID;

public class GuiListener implements Listener {
    private GuiManager guiManager;
    private RinvCommandExecutor commandExecutor;
    private InventoryManager inventoryManager;

    public GuiListener(GuiManager guiManager, RinvCommandExecutor commandExecutor, InventoryManager inventoryManager) {
        this.guiManager = guiManager;
        this.commandExecutor = commandExecutor;
        this.inventoryManager = inventoryManager;
    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        Player player = (Player) event.getPlayer();
        String guiTitle = event.getView().getTitle();

        if (guiManager.isGuiWhitelisted(guiTitle)) {
            inventoryManager.saveInventory(player);
            inventoryManager.clearInventory(player);
        }
    }


    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        UUID playerId = player.getUniqueId();
        String guiTitle = event.getView().getTitle();

        if (event.getCurrentItem() == null && commandExecutor.isPlayerAddingGui(playerId)) {
            guiManager.addGui(guiTitle);
            commandExecutor.removePlayerAddingGui(playerId);
            player.sendMessage("§8[§4RedxAx§bInv§8] > §8[§f" + guiTitle + "§8] Added To The §fWhitelist.");
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        String guiTitle = event.getView().getTitle();

        if (guiManager.isGuiWhitelisted(guiTitle)) {
            inventoryManager.restoreInventory(player);
        }
    }
}