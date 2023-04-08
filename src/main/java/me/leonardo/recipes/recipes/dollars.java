package me.leonardo.recipes.recipes;

import me.leonardo.recipes.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class dollars {

    public ItemStack dollar100 = null;
    public ItemStack dollar50 = null;
    public ItemStack dollar10 = null;
    public ItemStack dollar5 = null;
    public ItemStack dollar1 = null;

    public void newRecipe(String lore1, String lore2, String name1, String name2, int amount, String key, String... shape) {
        try {
            Bukkit.removeRecipe(new NamespacedKey(Main.main, key));
        }catch (Exception e) {
        }
        ItemStack i = new ItemStack(Material.PAPER, 1);
        ItemMeta im = i.getItemMeta();
        List<String> il = new ArrayList<>();
        il.add(lore1);
        im.setLore(il);
        im.setDisplayName(name1);
        i.setItemMeta(im);

        ItemStack i2 = new ItemStack(Material.PAPER, amount);
        ItemMeta i2m = i2.getItemMeta();
        List<String> i2l = new ArrayList<>();
        i2l.add(lore2);
        i2m.setLore(i2l);
        i2m.setDisplayName(name2);
        i2.setItemMeta(i2m);

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(Main.main, key), i2);
        try {
            recipe.shape(shape);
        }catch (Exception e) {
            recipe.shape("1");
        }
        recipe.setIngredient('1', new RecipeChoice.MaterialChoice.ExactChoice(i));

        Bukkit.addRecipe(recipe);
    }

}
