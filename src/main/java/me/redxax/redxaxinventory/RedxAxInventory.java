package me.redxax.redxaxinventory;

import org.bukkit.plugin.java.JavaPlugin;

public final class RedxAxInventory extends JavaPlugin {
    private GuiManager guiManager;
    private GuiListener guiListener;
    private RinvCommandExecutor commandExecutor;
    private InventoryManager inventoryManager;
    private RinvTabCompleter tabCompleter;

    @Override
    public void onEnable() {
        guiManager = new GuiManager();
        commandExecutor = new RinvCommandExecutor(guiManager);
        inventoryManager = new InventoryManager();
        guiListener = new GuiListener(guiManager, commandExecutor, inventoryManager);
        tabCompleter = new RinvTabCompleter(guiManager);

        getServer().getPluginManager().registerEvents(guiListener, this);
        getCommand("rinv").setExecutor(commandExecutor);
        getCommand("rinv").setTabCompleter(tabCompleter);
    }

}