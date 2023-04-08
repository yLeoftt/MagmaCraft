package me.leonardo.recipes.separetes;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class Armor {

    public List<Material> helmets = new ArrayList<>();
    public List<Material> chestplates = new ArrayList<>();
    public List<Material> leggings= new ArrayList<>();
    public List<Material> boots = new ArrayList<>();

    public Armor() {
        helmets.clear();
        chestplates.clear();
        leggings.clear();
        boots.clear();

        helmets.add(Material.CHAINMAIL_HELMET);
        helmets.add(Material.LEATHER_HELMET);
        helmets.add(Material.GOLDEN_HELMET);
        helmets.add(Material.IRON_HELMET);
        helmets.add(Material.DIAMOND_HELMET);
        helmets.add(Material.NETHERITE_HELMET);
        helmets.add(Material.TURTLE_HELMET);

        chestplates.add(Material.CHAINMAIL_CHESTPLATE);
        chestplates.add(Material.LEATHER_CHESTPLATE);
        chestplates.add(Material.GOLDEN_CHESTPLATE);
        chestplates.add(Material.IRON_CHESTPLATE);
        chestplates.add(Material.DIAMOND_CHESTPLATE);
        chestplates.add(Material.NETHERITE_CHESTPLATE);
        chestplates.add(Material.ELYTRA);

        leggings.add(Material.CHAINMAIL_LEGGINGS);
        leggings.add(Material.LEATHER_LEGGINGS);
        leggings.add(Material.GOLDEN_LEGGINGS);
        leggings.add(Material.IRON_LEGGINGS);
        leggings.add(Material.DIAMOND_LEGGINGS);
        leggings.add(Material.NETHERITE_LEGGINGS);

        boots.add(Material.CHAINMAIL_BOOTS);
        boots.add(Material.LEATHER_BOOTS);
        boots.add(Material.GOLDEN_BOOTS);
        boots.add(Material.IRON_BOOTS);
        boots.add(Material.DIAMOND_BOOTS);
        boots.add(Material.NETHERITE_BOOTS);
    }

}
