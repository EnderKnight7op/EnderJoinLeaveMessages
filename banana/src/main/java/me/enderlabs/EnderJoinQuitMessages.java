package me.enderlabs;

import java.util.List;
import com.mojang.brigadier.arguments.StringArgumentType;

import org.bukkit.plugin.java.JavaPlugin;

import com.mojang.brigadier.Command;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;

public class EnderJoinQuitMessages extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("EnderJoinQuitMessages has been enabled!");

        saveDefaultConfig();

        getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {

            event.registrar().register(
                    Commands.literal("EnderJoinQuitMessages")
                            .then(Commands.literal("help")
                                    .executes(context -> {

                                        if (!context.getSource().getSender().hasPermission("enderjoinquitmessages.command.help")) {
                                            context.getSource().getSender()
                                                    .sendPlainMessage("You don't have permission to do that.");
                                            return 0;
                                        }

                                        context.getSource().getSender()
                                                .sendPlainMessage("EnderJoinQuitMessages Commands:");

                                        context.getSource().getSender()
                                                .sendPlainMessage("/ejqm set join-message <message>");

                                        context.getSource().getSender()
                                                .sendPlainMessage("/ejqm set leave-message <message>");

                                        return Command.SINGLE_SUCCESS;
                                    }))
                            .then(Commands.literal("reload")
                                    .executes(context -> {

                                        if (!context.getSource().getSender().hasPermission("enderjoinquitmessages.command.reload")) {
                                            context.getSource().getSender()
                                                    .sendPlainMessage("You don't have permission to do that.");
                                            return 0;
                                        }

                                        reloadConfig();

                                        context.getSource().getSender()
                                                .sendPlainMessage("EnderJoinQuitMessages configuration reloaded!");

                                        return Command.SINGLE_SUCCESS;
                                    }))
                            .then(Commands.literal("set")
                                    .then(Commands.literal("join-message")
                                            .then(Commands.argument(
                                                    "message",
                                                    StringArgumentType.greedyString())
                                                    .executes(context -> {

                                                        if (!context.getSource().getSender()
                                                                .hasPermission("enderjoinquitmessages.command.set")) {
                                                            context.getSource().getSender()
                                                                    .sendPlainMessage(
                                                                            "You don't have permission to do that.");
                                                            return 0;
                                                        }

                                                        String message = StringArgumentType.getString(
                                                                context,
                                                                "message");

                                                        getConfig().set("join-message", message);
                                                        saveConfig();

                                                        context.getSource().getSender()
                                                                .sendPlainMessage("Join message updated!");

                                                        return Command.SINGLE_SUCCESS;
                                                    })))
                                    .then(Commands.literal("leave-message")
                                            .then(Commands.argument(
                                                    "message",
                                                    StringArgumentType.greedyString())
                                                    .executes(context -> {

                                                        if (!context.getSource().getSender()
                                                                .hasPermission("enderjoinquitmessages.command.set")) {
                                                            context.getSource().getSender()
                                                                    .sendPlainMessage(
                                                                            "You don't have permission to do that.");
                                                            return 0;
                                                        }

                                                        String message = StringArgumentType.getString(
                                                                context,
                                                                "message");

                                                        getConfig().set("leave-message", message);
                                                        saveConfig();

                                                        context.getSource().getSender()
                                                                .sendPlainMessage("Leave message updated!");

                                                        return Command.SINGLE_SUCCESS;
                                                    }))))
                            .build(),
                    "EnderJoinQuitMessages commands",
                    List.of("jqm", "ejqm"));

            getLogger().info("EnderJoinQuitMessages command registered!");
        });

        getServer().getPluginManager().registerEvents(new JoinListener(this), this);
        getServer().getPluginManager().registerEvents(new LeaveListener(this), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("EnderJoinQuitMessages has been disabled!");
    }
}