package me.leonardo.recipes.commands;

import me.leonardo.recipes.DiscordWebhook;
import me.leonardo.recipes.Main;
import me.leonardo.recipes.utils.GUI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.awt.*;
import java.io.IOException;

public class PixCommand extends GUI implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(s instanceof Player) {
            Player p = (Player)s;
            String usagem = "&c-> /pix (Player) (Valor)";
            String naovalor = "&cO valor inserido não é um numero!";
            String playernaoexiste = "&cO player definido, nunca entrou no servidor!";
            String semsaldo = "&cVocê não tem saldo o suficiente no /carteira para esse pix! &4(&cLembrando que para o pix funcionar você deve ter as notas exatas no inventario!&4)";
            String enviado = "&eVocê enviou um pix!";
            String recebido = "&eVocê recebeu um pix!";

            if(args.length == 2) {
                String tN = args[0];
                String amtS = args[1];

                for(OfflinePlayer off : Bukkit.getOfflinePlayers()) {
                    if(off.getName().equals(tN)) {
                        OfflinePlayer t = off;
                        if(Main.iu.isInteger(amtS)) {
                            int amt = Integer.parseInt(amtS);
                            if(hasSaldoExactSet(p, amt)) {
                                addSaldo(t, amt);

                                s(p, p, t, amt, enviado);
                                if(t.isOnline()) {
                                    Player to = (Player) t;
                                    s(to, p, t, amt, recebido);
                                }

                                // WebHook
                                DiscordWebhook hook = new DiscordWebhook(Main.hook);

                                boolean inline = false;
                                hook.addEmbed(new DiscordWebhook.EmbedObject()
                                        .setTitle("PIX!")
                                        .addField("Remetente (Enviou)", p.getName(), inline)
                                        .addField("Destinatario (Recebeu)", t.getName(), inline)
                                        .addField("Status", "Enviado", inline)
                                        .addField("Valor", String.valueOf(amt)+" R$", inline)
                                        .setColor(new Color(33, 222, 131))
                                );

                                try {
                                    hook.execute();
                                } catch (IOException e) {
                                    Main.main.getLogger().severe("Impossibilitado de enviar webhook! (Embed pix)");
                                }
                            }else {
                                p.sendMessage(Main.main.clr(semsaldo));
                            }
                        }else {
                            p.sendMessage(Main.main.clr(naovalor));
                        }
                        return false;
                    }
                }
                p.sendMessage(Main.main.clr(playernaoexiste));
            }else {
                p.sendMessage(Main.main.clr(usagem));
            }
        }
        return false;
    }

    public void s(Player enviar, Player p, OfflinePlayer t, int amt, String extra) {
        // Mensagens
        String equalss = "&b-=@=-";
        String enviado = "&eStatus: &aEnviado!";
        String remetente = "&eRemetente (Enviou): &a"+p.getName();
        String destinatario = "&eDestinatario (Recebeu): &a"+t.getName();
        String valor = "&eValor: &a"+amt;

        // Sends
        enviar.sendMessage(Main.main.clr(equalss));
        enviar.sendMessage(Main.main.clr(extra));
        enviar.sendMessage(Main.main.clr(remetente));
        enviar.sendMessage(Main.main.clr(destinatario));
        enviar.sendMessage(Main.main.clr(enviado));
        enviar.sendMessage(Main.main.clr(valor));
        enviar.sendMessage(Main.main.clr(equalss));
    }
}