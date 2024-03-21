package me.redxax.redxaxinventory;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class RinvTabCompleter implements TabCompleter {
    private GuiManager guiManager;

    public RinvTabCompleter(GuiManager guiManager) {
        this.guiManager = guiManager;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            if ("add".startsWith(args[0].toLowerCase())) {
                completions.add("add");
            }
            if ("delete".startsWith(args[0].toLowerCase())) {
                completions.add("delete");
            }
            if ("reload".startsWith(args[0].toLowerCase())) {
                completions.add("reload");
            }
        } else if (args.length == 2 && "delete".equalsIgnoreCase(args[0])) {
            for (String guiName : guiManager.getGuiNames()) {
                if (guiName.toLowerCase().startsWith(args[1].toLowerCase())) {
                    completions.add(guiName);
                }
            }
        }

        return completions;
    }
}