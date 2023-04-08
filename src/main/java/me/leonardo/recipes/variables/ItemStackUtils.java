package me.leonardo.recipes.variables;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemStackUtils {

    public ItemStack newItemStack(Material m, int amount, String name, String lore) {
        ItemStack item = new ItemStack(m);
        ItemMeta meta = item.getItemMeta();

        if(amount != 0) {
            item.setAmount(amount);
        }

        if(meta != null) {
            meta.setDisplayName(name);

            List<String> loreL = new ArrayList<>();
            loreL.add(lore);
            meta.setLore(loreL);

            item.setItemMeta(meta);
        }

        return item;
    }


    public boolean isSameIgnoreAmount(ItemStack i, ItemStack s) {
        if(i == null || s == null) return false;

        ItemMeta im = i.getItemMeta();
        ItemMeta sm = s.getItemMeta();

        if(im == null || sm == null) return false;
        String idn = im.getDisplayName();
        String sdn = sm.getDisplayName();

        if(idn.equals(null) || sdn.equals(null)) return false;
        if(!idn.equals(sdn)) return false;
        List<String> il = im.getLore();
        List<String> sl = sm.getLore();

        if(il == null || sl == null) return false;
        if(!il.isEmpty() && sl.isEmpty()) return false;
        if(il.isEmpty() && !sl.isEmpty()) return false;
        if(il.isEmpty() && sl.isEmpty()) return true;
        if(il.equals(sl)) return true;

        return false;
    }

}
