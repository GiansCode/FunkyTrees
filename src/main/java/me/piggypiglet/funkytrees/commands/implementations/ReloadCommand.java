package me.piggypiglet.funkytrees.commands.implementations;

import com.google.inject.Inject;
import me.piggypiglet.funkytrees.commands.framework.Command;
import me.piggypiglet.funkytrees.file.FileManager;
import me.piggypiglet.funkytrees.file.annotations.File;
import me.piggypiglet.funkytrees.file.objects.Config;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class ReloadCommand extends Command {
    @Inject private FileManager fileManager;

    public ReloadCommand() {
        super("reload");

        options
                .permissions("funkytrees.admin", "funkytrees.reload")
                .description("Reload the plugin config.")
                .usage("");
    }

    @Override
    public boolean execute(@NotNull final CommandSender sender, @NotNull final String[] args) {
        final File data = Config.class.getAnnotation(File.class);
        fileManager.loadFile(Config.class, data.internalPath(), data.externalPath());
        sender.sendMessage("&7Successfully loaded latest config values into memory.");
        return true;
    }
}
