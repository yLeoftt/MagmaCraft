package me.leonardo.recipes.variables;

import me.leonardo.recipes.Main;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;


public class PluginYAMLManager {

    private PluginDescriptionFile file;
    public Main main = Main.main;

    public PluginYAMLManager() {
        this.file = main.getDescription();
    }

    public void registerCommand(String command, CommandExecutor ce){
        if(file.getCommands().containsKey(command)) {
            main.getCommand(command).setExecutor(ce);
        }else {
            main.getLogger().severe("§4Couldn't load comamnd §e"+command);
        }
    }

    public void registerCommandTabCompleter(String command, TabCompleter tc){
        if(file.getCommands().containsKey(command)) {
            main.getCommand(command).setTabCompleter(tc);
        }else {
            main.getLogger().severe("§4Couldn't load comamnd §e"+command);
        }
    }

    public void registerEvent(Listener l) {
        main.getServer().getPluginManager().registerEvents(l, main);
    }

}
