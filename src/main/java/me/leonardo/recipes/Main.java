package me.leonardo.recipes;

import me.leonardo.recipes.commands.CarteiraCommand;
import me.leonardo.recipes.commands.PixCommand;
import me.leonardo.recipes.events.InventoryEvents;
import me.leonardo.recipes.events.PlayerEvents;
import me.leonardo.recipes.recipes.Charms;
import me.leonardo.recipes.recipes.dollars;
import me.leonardo.recipes.tabcompleters.PixTabCompleter;
import me.leonardo.recipes.utils.GUI;
import me.leonardo.recipes.variables.IntegerUtils;
import me.leonardo.recipes.variables.ItemStackUtils;
import me.leonardo.recipes.variables.PluginYAMLManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin {

    public static Main main;
    public static PluginYAMLManager pym;
    public static Charms charms;
    public static dollars dol;
    public static IntegerUtils iu;
    public static ItemStackUtils isu;
    public static GUI gui;

    public List<Player> changedArmor = new ArrayList<>();

    public static String hook = "https://discord.com/api/webhooks/1093718393137405983/kB5brn1f5QyiAkJCCfGhEoSPGsJiVaz8WsnBj23Ei8cvMHU2eMkCrFBnB3o_FWIm8BMd";
    public static String hookChat = "https://discord.com/api/webhooks/1093732092677988382/2bpDZzKMaEyzTEi7kRomotC5F_F_CRVXlmnCNq_iJlLoWWnaPpr1qfva8Onq8EBz7lAF";

    public String gluttony_charm;

    public String dollar100;
    public String dollar50;
    public String dollar10;
    public String dollar5;
    public String dollar1;

    @Override
    public void onEnable() {
        main = Main.this;
        charms = new Charms();
        dol = new dollars();
        iu = new IntegerUtils();
        isu = new ItemStackUtils();
        gui = new GUI();
        pym = new PluginYAMLManager();

        File f = new File(getDataFolder(), "config.yml");
        if(!f.exists()) {
            saveDefaultConfig();
        }

        // Commands Register
        pym.registerCommand("carteira", new CarteiraCommand());
        pym.registerCommand("pix", new PixCommand());
        // TabCompleters Register
        pym.registerCommandTabCompleter("pix", new PixTabCompleter());
        // Events Register
        pym.registerEvent(new InventoryEvents());
        pym.registerEvent(new PlayerEvents());

        // Charms
        gluttony_charm = "§7Use esse charm para comer instantaneamente!";

        String gluttony_charmn = "§6Gluttony Charm";

        charms.gluttony_charm = isu.newItemStack(Material.CHAINMAIL_CHESTPLATE, 1, gluttony_charmn, gluttony_charm);

        charms.registerGluttonyCharmRecipe();


        // Dinheiro
        dollar100 = "§5Nota de 100 R$. (Verdadeira)";
        dollar50 = "§5Nota de 50 R$. (Verdadeira)";
        dollar10 = "§5Nota de 10 R$. (Verdadeira)";
        dollar5 = "§5Nota de 5 R$. (Verdadeira)";
        dollar1 = "§5Nota de 1 R$. (Verdadeira)";

        String dollar100n = "§f100 R$";
        String dollar50n = "§f50 R$";
        String dollar10n = "§f10 R$";
        String dollar5n = "§f5 R$";
        String dollar1n = "§f1 R$";

        dol.dollar100 = isu.newItemStack(Material.PAPER, 0, dollar100n, dollar100);
        dol.dollar50 = isu.newItemStack(Material.PAPER, 0, dollar50n, dollar50);
        dol.dollar10 = isu.newItemStack(Material.PAPER, 0, dollar10n, dollar10);
        dol.dollar5 = isu.newItemStack(Material.PAPER, 0, dollar5n, dollar5);
        dol.dollar1 = isu.newItemStack(Material.PAPER, 0, dollar1n, dollar1);


        dol.newRecipe(dollar100, dollar50, dollar100n, dollar50n, 2, "dollar100todollar50");
        dol.newRecipe(dollar50, dollar10, dollar50n, dollar10n, 5, "dollar50todollar10");
        dol.newRecipe(dollar10, dollar5, dollar10n, dollar5n, 2, "dollar10todollar5");
        dol.newRecipe(dollar5, dollar1, dollar5n, dollar1n, 5, "dollar5todollar1");

        //dollar1 to dollar5
        dol.newRecipe(dollar1, dollar5, dollar1n, dollar5n, 1, "dollar1todollar5",
                "111",
                "11 "
        );
        dol.newRecipe(dollar1, dollar5, dollar1n, dollar5n, 1, "dollar1todollar5_2",
                "111",
                " 11"
        );
        dol.newRecipe(dollar1, dollar5, dollar1n, dollar5n, 1, "dollar1todollar5_3",
                " 11",
                "111"
        );
        dol.newRecipe(dollar1, dollar5, dollar1n, dollar5n, 1, "dollar1todollar5_4",
                "11 ",
                "111"
        );
        dol.newRecipe(dollar1, dollar5, dollar1n, dollar5n, 1, "dollar1todollar5_5",
                "11",
                "11",
                "1 "
        );
        dol.newRecipe(dollar1, dollar5, dollar1n, dollar5n, 1, "dollar1todollar5_6",
                "11",
                "11",
                " 1"
        );
        dol.newRecipe(dollar1, dollar5, dollar1n, dollar5n, 1, "dollar1todollar5_7",
                "11 ",
                "11 ",
                "  1"
        );

        //dollar5 to dollar10
        dol.newRecipe(dollar5, dollar10, dollar5n, dollar10n, 1, "dollar5todollar10",
                "11"
        );
        dol.newRecipe(dollar5, dollar10, dollar5n, dollar10n, 1, "dollar5todollar10_2",
                "1",
                "1"
        );
        dol.newRecipe(dollar5, dollar10, dollar5n, dollar10n, 1, "dollar5todollar10_3",
                "1 ",
                " 1"
        );
        dol.newRecipe(dollar5, dollar10, dollar5n, dollar10n, 1, "dollar5todollar10_4",
                " 1",
                "1 "
        );
        dol.newRecipe(dollar5, dollar10, dollar5n, dollar10n, 1, "dollar5todollar10_5",
                "1 1"
        );
        dol.newRecipe(dollar5, dollar10, dollar5n, dollar10n, 1, "dollar5todollar10_6",
                "1",
                " ",
                "1"
        );
        dol.newRecipe(dollar5, dollar10, dollar5n, dollar10n, 1, "dollar5todollar10_7",
                "1 ",
                "  ",
                " 1"
        );
        dol.newRecipe(dollar5, dollar10, dollar5n, dollar10n, 1, "dollar5todollar10_8",
                " 1",
                "  ",
                "1 "
        );
        dol.newRecipe(dollar5, dollar10, dollar5n, dollar10n, 1, "dollar5todollar10_9",
                "1  ",
                "   ",
                "  1"
        );
        dol.newRecipe(dollar5, dollar10, dollar5n, dollar10n, 1, "dollar5todollar10_10",
                "1  ",
                "  1"
        );
        dol.newRecipe(dollar5, dollar10, dollar5n, dollar10n, 1, "dollar5todollar10_11",
                "  1",
                "1  "
        );
        dol.newRecipe(dollar5, dollar10, dollar5n, dollar10n, 1, "dollar5todollar10_12",
                "  1",
                "   ",
                "1  "
        );

        //dollar10 to dollar50
        dol.newRecipe(dollar10, dollar50, dollar10n, dollar50n, 1, "dollar10todollar50",
                "111",
                "11 "
        );
        dol.newRecipe(dollar10, dollar50, dollar10n, dollar50n, 1, "dollar10todollar50_2",
                "111",
                " 11"
        );
        dol.newRecipe(dollar10, dollar50, dollar10n, dollar50n, 1, "dollar10todollar50_3",
                " 11",
                "111"
        );
        dol.newRecipe(dollar10, dollar50, dollar10n, dollar50n, 1, "dollar10todollar50_4",
                "11 ",
                "111"
        );
        dol.newRecipe(dollar10, dollar50, dollar10n, dollar50n, 1, "dollar10todollar50_5",
                "11",
                "11",
                "1 "
        );
        dol.newRecipe(dollar10, dollar50, dollar10n, dollar50n, 1, "dollar10todollar50_6",
                "11",
                "11",
                " 1"
        );
        dol.newRecipe(dollar10, dollar50, dollar10n, dollar50n, 1, "dollar10todollar50_7",
                "11 ",
                "11 ",
                "  1"
        );

        //dollar50 to dollar100
        dol.newRecipe(dollar50, dollar100, dollar50n, dollar100n, 1, "dollar50todollar100",
                "11"
        );
        dol.newRecipe(dollar50, dollar100, dollar50n, dollar100n, 1, "dollar50todollar100_2",
                "1",
                "1"
        );
        dol.newRecipe(dollar50, dollar100, dollar50n, dollar100n, 1, "dollar50todollar100_3",
                "1 ",
                " 1"
        );
        dol.newRecipe(dollar50, dollar100, dollar50n, dollar100n, 1, "dollar50todollar100_4",
                " 1",
                "1 "
        );
        dol.newRecipe(dollar50, dollar100, dollar50n, dollar100n, 1, "dollar50todollar100_5",
                "1 1"
        );
        dol.newRecipe(dollar50, dollar100, dollar50n, dollar100n, 1, "dollar50todollar100_6",
                "1",
                " ",
                "1"
        );
        dol.newRecipe(dollar50, dollar100, dollar50n, dollar100n, 1, "dollar50todollar100_7",
                "1 ",
                "  ",
                " 1"
        );
        dol.newRecipe(dollar50, dollar100, dollar50n, dollar100n, 1, "dollar50todollar100_8",
                " 1",
                "  ",
                "1 "
        );
        dol.newRecipe(dollar50, dollar100, dollar50n, dollar100n, 1, "dollar50todollar100_9",
                "1  ",
                "   ",
                "  1"
        );
        dol.newRecipe(dollar50, dollar100, dollar50n, dollar100n, 1, "dollar50todollar100_10",
                "1  ",
                "  1"
        );
        dol.newRecipe(dollar50, dollar100, dollar50n, dollar100n, 1, "dollar50todollar100_11",
                "  1",
                "1  "
        );
        dol.newRecipe(dollar50, dollar100, dollar50n, dollar100n, 1, "dollar50todollar100_12",
                "  1",
                "   ",
                "1  "
        );
    }

    @Override
    public void onDisable() {
        getLogger().severe(clr("&7[&c-&7] &fRecipes &7(Plugin)"));
    }


    public String clr(String txt) {
        return ChatColor.translateAlternateColorCodes('&', txt);
    }




    public static String[] playerInventoryToBase64(PlayerInventory playerInventory) throws IllegalStateException {
        //get the main content part, this doesn't return the armor
        String content = toBase64(playerInventory);
        String armor = itemStackArrayToBase64(playerInventory.getArmorContents());

        return new String[] { content, armor };
    }

    /**
     *
     * A method to serialize an {@link ItemStack} array to Base64 String.
     *
     * <p />
     *
     * Based off of {@link #toBase64(Inventory)}.
     *
     * @param items to turn into a Base64 String.
     * @return Base64 string of the items.
     * @throws IllegalStateException
     */
    public static String itemStackArrayToBase64(ItemStack[] items) throws IllegalStateException {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

            // Write the size of the inventory
            dataOutput.writeInt(items.length);

            // Save every element in the list
            for (int i = 0; i < items.length; i++) {
                dataOutput.writeObject(items[i]);
            }

            // Serialize that array
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (Exception e) {
            throw new IllegalStateException("Unable to save item stacks.", e);
        }
    }

    /**
     * A method to serialize an inventory to Base64 string.
     *
     * <p />
     *
     * Special thanks to Comphenix in the Bukkit forums or also known
     * as aadnk on GitHub.
     *
     * <a href="https://gist.github.com/aadnk/8138186">Original Source</a>
     *
     * @param inventory to serialize
     * @return Base64 string of the provided inventory
     * @throws IllegalStateException
     */
    public static String toBase64(Inventory inventory) throws IllegalStateException {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

            // Write the size of the inventory
            dataOutput.writeInt(inventory.getSize());

            // Save every element in the list
            for (int i = 0; i < inventory.getSize(); i++) {
                dataOutput.writeObject(inventory.getItem(i));
            }

            // Serialize that array
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (Exception e) {
            throw new IllegalStateException("Unable to save item stacks.", e);
        }
    }

    /**
     *
     * A method to get an {@link Inventory} from an encoded, Base64, string.
     *
     * <p />
     *
     * Special thanks to Comphenix in the Bukkit forums or also known
     * as aadnk on GitHub.
     *
     * <a href="https://gist.github.com/aadnk/8138186">Original Source</a>
     *
     * @param data Base64 string of data containing an inventory.
     * @return Inventory created from the Base64 string.
     * @throws IOException
     */
    public static Inventory fromBase64(String data) throws IOException {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            Inventory inventory = Bukkit.getServer().createInventory(null, dataInput.readInt());

            // Read the serialized inventory
            for (int i = 0; i < inventory.getSize(); i++) {
                inventory.setItem(i, (ItemStack) dataInput.readObject());
            }

            dataInput.close();
            return inventory;
        } catch (ClassNotFoundException e) {
            throw new IOException("Unable to decode class type.", e);
        }
    }

    /**
     * Gets an array of ItemStacks from Base64 string.
     *
     * <p />
     *
     * Base off of {@link #fromBase64(String)}.
     *
     * @param data Base64 string to convert to ItemStack array.
     * @return ItemStack array created from the Base64 string.
     * @throws IOException
     */
    public static ItemStack[] itemStackArrayFromBase64(String data) throws IOException {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            ItemStack[] items = new ItemStack[dataInput.readInt()];

            // Read the serialized inventory
            for (int i = 0; i < items.length; i++) {
                items[i] = (ItemStack) dataInput.readObject();
            }

            dataInput.close();
            return items;
        } catch (ClassNotFoundException e) {
            throw new IOException("Unable to decode class type.", e);
        }
    }
}
