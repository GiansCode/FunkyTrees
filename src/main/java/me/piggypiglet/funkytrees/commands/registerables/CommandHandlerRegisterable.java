package me.piggypiglet.funkytrees.commands.registerables;

import com.google.inject.Inject;
import me.piggypiglet.funkytrees.boot.framework.Registerable;
import me.piggypiglet.funkytrees.commands.CommandHandler;
import me.piggypiglet.funkytrees.commands.exceptions.NoRegisteredCommandException;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class CommandHandlerRegisterable extends Registerable {
    @Inject private JavaPlugin main;
    @Inject private CommandHandler commandHandler;

    @Override
    protected void execute() {
        Optional.ofNullable(main.getCommand("ft"))
                .orElseThrow(() -> new NoRegisteredCommandException("The lu command hasn't been registered in the plugin YAML."))
                .setExecutor(commandHandler);
    }
}
