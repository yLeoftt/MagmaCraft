package me.leonardo.recipes.tabcompleters;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PixTabCompleter implements TabCompleter {

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        List<String> list = new ArrayList<>();

        if(args.length == 1) {
            for(OfflinePlayer off : Bukkit.getOfflinePlayers()) {
                String name = off.getName();
                if(name.toLowerCase().startsWith(args[0].toLowerCase())) {
                    list.add(off.getName());
                }
            }

            Collections.sort(list);
        }

        return list;
    }
}
