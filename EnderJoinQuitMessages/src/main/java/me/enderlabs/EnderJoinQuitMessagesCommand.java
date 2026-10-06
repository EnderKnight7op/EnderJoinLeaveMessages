package me.enderlabs;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class EnderJoinQuitMessagesCommand implements CommandExecutor {

    private final EnderJoinQuitMessages plugin;

    public EnderJoinQuitMessagesCommand(EnderJoinQuitMessages plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(
            CommandSender sender,
            Command command,
            String label,
            String[] args
    ) {

        if (args[0].equalsIgnoreCase("help")) {
            sender.sendMessage("§6§lEnderJoinQuitMessages Commands");
            sender.sendMessage("§e/jqm set join-message <message>");
            sender.sendMessage("§e/jqm set leave-message <message>");
            return true;
        }
        

        if (args.length == 0) {
            sender.sendMessage("§6§lEnderJoinQuitMessages Commands");
            sender.sendMessage("§e/jqm set join-message <message>");
            sender.sendMessage("§e/jqm set leave-message <message>");
            return true;
        }

        if (args[0].equalsIgnoreCase("set")) {

            if (args.length < 3) {
                sender.sendMessage("Usage: /jqm set <setting> <message>");
                return true;
            }


            StringBuilder messageBuilder = new StringBuilder();

            for (int i = 2; i < args.length; i++) {
                if (i > 2) {messageBuilder.append(" ");}

                messageBuilder.append(args[i]);
            }

            String message = messageBuilder.toString();

            String setting = args[1];


            if (setting.equalsIgnoreCase("join-message")) {
                plugin.getConfig().set("join-message", message);
                plugin.saveConfig();

                sender.sendMessage("Join message updated!");
                
            } else if (setting.equalsIgnoreCase("leave-message")) {
                plugin.getConfig().set("leave-message", message);
                plugin.saveConfig();

                sender.sendMessage("Leave message updated!");
            }

        } else {
            sender.sendMessage("Unknown command. Use /jqm set <setting> <message>");
        }

        return true;
    }
}
