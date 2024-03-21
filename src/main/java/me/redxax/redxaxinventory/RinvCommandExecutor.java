package me.redxax.redxaxinventory;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class RinvCommandExecutor implements CommandExecutor {
    private GuiManager guiManager;
    private Set<UUID> playersToAddGui;


    public RinvCommandExecutor(GuiManager guiManager) {
        this.guiManager = guiManager;
        this.playersToAddGui = new HashSet<>();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be run by a player.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage("§8[§4RedxAx§bInv§8] > §7Usage: §3/rinv §8<§aadd §8| §cdelete§8> §3[name]");
            return true;
        }

        if ("add".equalsIgnoreCase(args[0])) {
            playersToAddGui.add(player.getUniqueId());
            player.sendMessage("Open a GUI to add it to the whitelist.");
        } else if ("delete".equalsIgnoreCase(args[0])) {
            if (args.length < 2) {
                player.sendMessage("§8[§4RedxAx§bInv§8] > §7Usage: §3/rinv §cdelete §3<name>");
                return true;
            }

            String guiName = args[1];
            guiManager.removeGui(guiName);
            player.sendMessage("§8[§4RedxAx§bInv§8] > §8[§f" + guiName + "§8] §cRemoved §8from the §fWhitelist.");
        } else if ("reload".equalsIgnoreCase(args[0])) {
            guiManager.reload();
            player.sendMessage("§8[§4RedxAx§bInv§8] > Configuration §3reloaded.");
        } else {
            player.sendMessage("§8[§4RedxAx§bInv§8] > Unknown Command. §7Usage: §3/rinv §8<§aadd §8| §cdelete§8> §3[name]");
        }

        return true;
    }

    public boolean isPlayerAddingGui(UUID playerId) {
        return playersToAddGui.contains(playerId);
    }

    public void removePlayerAddingGui(UUID playerId) {
        playersToAddGui.remove(playerId);
    }
}