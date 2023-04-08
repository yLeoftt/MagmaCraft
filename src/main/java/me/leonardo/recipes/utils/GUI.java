package me.leonardo.recipes.utils;

import me.leonardo.recipes.Main;
import me.leonardo.recipes.recipes.dollars;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.IOException;
import java.util.List;

public class GUI extends dollars {

    public void carteira(Player p) {
        p.openInventory(getCarteira(p));
    }

    public Inventory getCarteira(OfflinePlayer p) {
        Inventory inv = Bukkit.createInventory(null, 18, getTitle(p));
        String path = p.getUniqueId().toString()+".carteira";

        if(Main.main.getConfig().contains(path)) {
            try {
                inv.setContents(Main.itemStackArrayFromBase64(Main.main.getConfig().getString(path)));
            } catch (IOException e) {
            }
        }

        return inv;
    }


    public String getTitle(OfflinePlayer p) {
        return Main.main.clr("&aCarteira! &7- &e"+getSaldo(p)+"R$");
    }


    public int getSaldo(OfflinePlayer p) {
        String path = p.getUniqueId().toString()+".carteira";

        int saldo = 0;

        if(Main.main.getConfig().contains(path)) {
            try {
                ItemStack[] itens = Main.itemStackArrayFromBase64(Main.main.getConfig().getString(path));
                for(ItemStack item : itens) {
                    if(item != null) {
                        ItemMeta meta = item.getItemMeta();
                        int amt = item.getAmount();

                        if(meta != null) {
                            List<String> list = meta.getLore();

                            if(list != null) {
                                if (!list.isEmpty()) {
                                    String lore = list.get(0);

                                    if(lore.equals(Main.main.dollar100)) saldo = saldo+(100*amt);
                                    if(lore.equals(Main.main.dollar50)) saldo = saldo+(50*amt);
                                    if(lore.equals(Main.main.dollar10)) saldo = saldo+(10*amt);
                                    if(lore.equals(Main.main.dollar5)) saldo = saldo+(5*amt);
                                    if(lore.equals(Main.main.dollar1)) saldo = saldo+(amt);
                                }
                            }
                        }
                    }
                }
            }catch (IOException e) {
            }
        }

        return saldo;
    }

    public void addSaldo(OfflinePlayer p, int saldoR) {
        Inventory inv = getCarteira(p);

        while (saldoR > 0) {
            if(saldoR >= 100) {
                inv.addItem(Main.dol.dollar100);
                saldoR = saldoR-100;
            }else if(saldoR >= 50) {
                inv.addItem(Main.dol.dollar50);
                saldoR = saldoR-50;
            }else if(saldoR >= 10) {
                inv.addItem(Main.dol.dollar10);
                saldoR = saldoR-10;
            }else if(saldoR >= 5) {
                inv.addItem(Main.dol.dollar5);
                saldoR = saldoR-5;
            }else if(saldoR >= 1) {
                inv.addItem(Main.dol.dollar1);
                saldoR = saldoR-1;
            }
        }

        setCarteira(p, inv);
    }

    public boolean hasSaldo(OfflinePlayer p, int saldoR) {
        if(getSaldo(p) >= saldoR) {
            return true;
        }

        return false;
    }

    public boolean hasSaldoExactSet(OfflinePlayer p, int saldoR) {
        Inventory inv = getCarteira(p);

        ItemStack[] itens = inv.getContents();
        for(ItemStack itemI : itens) {
            if(itemI != null) {
                ItemMeta meta = itemI.getItemMeta();

                if(meta != null) {
                    List<String> list = meta.getLore();

                    if(list != null) {
                        if (!list.isEmpty()) {
                            ItemStack item = new ItemStack(itemI.getType());
                            item.setItemMeta(itemI.getItemMeta());

                            String lore = list.get(0);

                            if(lore.equals(Main.main.dollar100)) {
                                int n = 100;
                                int cab = saldoR/n;
                                if(saldoR >= n*cab) {
                                    while(cab > 0) {
                                        inv.removeItem(item);
                                        saldoR = saldoR-n;
                                        cab--;
                                    }
                                }
                            }
                            if(lore.equals(Main.main.dollar50)) {
                                int n = 50;
                                int cab = saldoR/n;
                                if(saldoR >= n*cab) {
                                    while(cab > 0) {
                                        inv.removeItem(item);
                                        saldoR = saldoR-n;
                                        cab--;
                                    }
                                }
                            }
                            if(lore.equals(Main.main.dollar10)) {
                                int n = 10;
                                int cab = saldoR/n;
                                if(saldoR >= n*cab) {
                                    while(cab > 0) {
                                        inv.removeItem(item);
                                        saldoR = saldoR-n;
                                        cab--;
                                    }
                                }
                            }
                            if(lore.equals(Main.main.dollar5)) {
                                int n = 5;
                                int cab = saldoR/n;
                                if(saldoR >= n*cab) {
                                    while(cab > 0) {
                                        inv.removeItem(item);
                                        saldoR = saldoR-n;
                                        cab--;
                                    }
                                }
                            }
                            if(lore.equals(Main.main.dollar1)) {
                                int n = 1;
                                int cab = saldoR;
                                if(saldoR >= n*cab) {
                                    while(cab > 0) {
                                        inv.removeItem(item);
                                        saldoR = saldoR-n;
                                        cab--;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if(saldoR == 0) {
            setCarteira(p, inv);
            return true;
        }
        return false;
    }

    public void removeSaldo(OfflinePlayer p, int saldoR) {
        Inventory inv = getCarteira(p);

        if(getSaldo(p) <= saldoR) {
            inv.clear();
            return;
        }else {
            ItemStack[] itens = inv.getContents();
            for(ItemStack itemI : itens) {
                if(itemI != null) {
                    ItemMeta meta = itemI.getItemMeta();

                    if(meta != null) {
                        List<String> list = meta.getLore();

                        if(list != null) {
                            if (!list.isEmpty()) {
                                ItemStack item = new ItemStack(itemI.getType());
                                item.setItemMeta(itemI.getItemMeta());

                                String lore = list.get(0);

                                if(lore.equals(Main.main.dollar100)) {
                                    int n = 100;
                                    if(saldoR >= n) {
                                        inv.removeItem(item);
                                        saldoR = saldoR-n;
                                    }
                                }
                                if(lore.equals(Main.main.dollar50)) {
                                    int n = 50;
                                    if(saldoR >= n) {
                                        inv.removeItem(item);
                                        saldoR = saldoR-n;
                                    }
                                }
                                if(lore.equals(Main.main.dollar10)) {
                                    int n = 10;
                                    if(saldoR >= n) {
                                        inv.removeItem(item);
                                        saldoR = saldoR-n;
                                    }
                                }
                                if(lore.equals(Main.main.dollar5)) {
                                    int n = 5;
                                    if(saldoR >= n) {
                                        inv.removeItem(item);
                                        saldoR = saldoR-n;
                                    }
                                }
                                if(lore.equals(Main.main.dollar1)) {
                                    int n = 1;
                                    if(saldoR >= n) {
                                        inv.removeItem(item);
                                        saldoR = saldoR-n;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            setCarteira(p, inv);
        }
    }

    public void setCarteira(OfflinePlayer p, Inventory inv) {
        String path = p.getUniqueId().toString()+".carteira";
        Main.main.getConfig().set(path, Main.itemStackArrayToBase64(inv.getContents()));
        Main.main.saveConfig();
    }

}
