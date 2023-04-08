package me.leonardo.recipes.events;

import me.leonardo.recipes.DiscordWebhook;
import me.leonardo.recipes.Main;
import me.leonardo.recipes.separetes.Armor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Container;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.Random;

public class PlayerEvents implements Listener {

    @EventHandler
    public void onPlayerChatEvent(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();

        String msg = e.getMessage();
        msg = Main.main.clr(msg);

        String msgP = msg.replaceAll("@everyone", Main.main.clr("&4@everyone&f"));

        Sound s = Sound.ENTITY_EXPERIENCE_ORB_PICKUP;
        int vol = 10;
        int pit = 10;
        if(msgP.contains("@everyone") || msgP.contains("@here")) {
            if (msgP.contains("@everyone")) {
                for(Player on : Bukkit.getOnlinePlayers()) {
                    on.playSound(on.getLocation(), s, vol, pit);
                }
            }else {
                for(Player onw : Bukkit.getOnlinePlayers()) {
                    if(onw.getWorld().equals(p.getWorld())) {
                        onw.playSound(onw.getLocation(), s, vol, pit);
                        msgP = msgP.replaceAll("@here", Main.main.clr("&4@here&f"));
                    }
                }
            }
        }else {
            for(Player on : Bukkit.getOnlinePlayers()) {
                if((msgP.toLowerCase()).contains(("@"+on.getName()).toLowerCase())) {
                    msgP = msgP.replaceAll("(?i)@"+on.getName(), Main.main.clr("&4@"+on.getName()+"&f"));
                    msg = msg.replaceAll("(?i)@"+on.getName(), "`@"+on.getName()+"`");
                    on.playSound(on.getLocation(), s, vol, pit);
                }
            }
        }

        e.setMessage(msgP);

        e.setFormat("§f"+p.getName()+" §7>> §f"+"%2$s");

        msg = msg.replaceAll("@everyone", "`@everyone`");
        msg = msg.replaceAll("@here", "`@here`");

        // WebHook
        DiscordWebhook hook = new DiscordWebhook(Main.hookChat);
        hook.setContent("**"+p.getName()+"**"+" >> "+msg);

        try {
            hook.execute();
        }catch (IOException err) {
            Main.main.getLogger().severe("Impossibilitado de enviar webhook! (Chat)");
        }
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Action act = e.getAction();
        ItemStack item = p.getInventory().getItemInMainHand();
        Block b = e.getClickedBlock();

        if(act.equals(Action.RIGHT_CLICK_AIR) || act.equals(Action.RIGHT_CLICK_BLOCK)) {
            if(b != null) {
                BlockState bs = b.getState();
                if(bs == null) return;
                if(bs instanceof Container) return;
            }
            if(item != null) {
                if(!Main.main.changedArmor.contains(p)) {
                    Material it = item.getType();
                    Armor arm = new Armor();

                    if(arm.helmets.contains(it)) {
                        ItemStack armor = p.getInventory().getHelmet();
                        p.getInventory().setHelmet(item);
                        p.getInventory().setItemInMainHand(armor);
                        Main.main.changedArmor.add(p);
                    }else if(arm.chestplates.contains(it)) {
                        ItemStack armor = p.getInventory().getChestplate();
                        p.getInventory().setChestplate(item);
                        p.getInventory().setItemInMainHand(armor);
                        Main.main.changedArmor.add(p);
                    }else if(arm.leggings.contains(it)) {
                        ItemStack armor = p.getInventory().getLeggings();
                        p.getInventory().setLeggings(item);
                        p.getInventory().setItemInMainHand(armor);
                        Main.main.changedArmor.add(p);
                    }else if(arm.boots.contains(it)) {
                        ItemStack armor = p.getInventory().getBoots();
                        p.getInventory().setBoots(item);
                        p.getInventory().setItemInMainHand(armor);
                        Main.main.changedArmor.add(p);
                    }

                    Bukkit.getScheduler().runTaskLater(Main.main, () -> {
                        if(Main.main.changedArmor.contains(p)) {
                            Main.main.changedArmor.remove(p);
                        }
                    }, 8);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        String joinmsg = "&7[&a+&7] &f"+p.getName();
        String joinmsgD = "**[+]** "+p.getName();

        e.setJoinMessage(Main.main.clr(joinmsg));

        // WebHook
        DiscordWebhook hook = new DiscordWebhook(Main.hookChat);
        hook.setContent(joinmsgD);

        try {
            hook.execute();
        }catch (IOException err) {
            Main.main.getLogger().severe("Impossibilitado de enviar webhook! (Chat)");
        }
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        String quitmsg = "&7[&c-&7] &f"+p.getName();
        String quitmsgD = "**[-]** "+p.getName();

        e.setQuitMessage(Main.main.clr(quitmsg));

        // WebHook
        DiscordWebhook hook = new DiscordWebhook(Main.hookChat);
        hook.setContent(quitmsgD);

        try {
            hook.execute();
        }catch (IOException err) {
            Main.main.getLogger().severe("Impossibilitado de enviar webhook! (Chat)");
        }
    }

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent e){
        Player p = e.getEntity();
        String msg = p.getName()+" foi de arrasta pra cima";

        Random r = new Random();
        int num = r.nextInt(5);

        switch (num) {
            case 0:
                msg = p.getName()+" foi de comes e bebes";
                break;
            case 1:
                msg = p.getName()+" não tankou";
                break;
            case 2:
                msg = p.getName()+" foi de Americanas";
                break;
            case 4:
                msg = p.getName()+" foi de base";
                break;
            case 3:
            default:
                msg = p.getName()+" foi de arrasta pra cima";
                break;
        }

        e.setDeathMessage(msg);
        p.setExp((float) (p.getExp()/1.5));

        DiscordWebhook hook = new DiscordWebhook(Main.hookChat);
        hook.setContent("**"+msg+"**");

        try {
            hook.execute();
        }catch (Exception err) {
        }
    }

}
