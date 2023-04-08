package me.leonardo.recipes.recipes;

import me.leonardo.recipes.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class Charms {

    public ItemStack gluttony_charm;

    public void registerGluttonyCharmRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(Main.main, "glottony_charm"), gluttony_charm);

        recipe.shape(
                " S ",
                "SBS",
                "PGC"
        );
        recipe.setIngredient('S', Material.STRING);
        recipe.setIngredient('B', Material.BREAD);
        recipe.setIngredient('P', Material.POTATO);
        recipe.setIngredient('G', Material.GOLDEN_APPLE);
        recipe.setIngredient('C', Material.CARROT);

        Bukkit.addRecipe(recipe);
    }

}
