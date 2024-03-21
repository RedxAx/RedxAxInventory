package me.redxax.redxaxinventory;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GuiManager {
    private File file;
    private FileConfiguration config;

    public GuiManager() {
        file = new File("plugins/RedxAxInventory/whitelist.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void addGui(String guiName) {
        List<String> whitelist = getWhitelist();
        whitelist.add(guiName);
        config.set("whitelist", whitelist);
        save();
    }
    public void reload() {
        config = YamlConfiguration.loadConfiguration(file);
    }
    public void removeGui(String guiName) {
        List<String> whitelist = getWhitelist();
        whitelist.remove(guiName);
        config.set("whitelist", whitelist);
        save();
    }

    public boolean isGuiWhitelisted(String guiName) {
        return getWhitelist().contains(guiName);
    }

    public Set<String> getGuiNames() {
        return new HashSet<>(getWhitelist());
    }

    private List<String> getWhitelist() {
        return config.getStringList("whitelist");
    }

    private void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}