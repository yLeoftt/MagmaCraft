package me.leonardo.recipes.events;

import me.leonardo.recipes.Main;
import me.leonardo.recipes.utils.GUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class InventoryEvents extends GUI implements Listener {

    @EventHandler
    public void onInventoryCloseEvent(InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();

        if(e.getView().getTitle().contains(Main.main.clr("&aCarteira!"))) {
            setCarteira(p, e.getInventory());
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryClickEvent(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if(e.getView().getTitle().contains(Main.main.clr("&aCarteira!"))) {
            ItemStack item = e.getCurrentItem();

            if(item == null) {
                return;
            }
            ItemMeta meta = item.getItemMeta();

            if(meta == null) {
                e.setCancelled(true);
                return;
            }
            List<String> list = meta.getLore();

            if(list == null || list.isEmpty()) {
                e.setCancelled(true);
                return;
            }
            String lore = list.get(0);

            if(
                !lore.equals(Main.main.dollar100) &&
                !lore.equals(Main.main.dollar50) &&
                !lore.equals(Main.main.dollar10) &&
                !lore.equals(Main.main.dollar5) &&
                !lore.equals(Main.main.dollar1)
            ) {
                e.setCancelled(true);
                return;
            }
        }
    }

}
