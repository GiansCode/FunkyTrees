package me.piggypiglet.funkytrees.commands.registerables;

import com.google.inject.Inject;
import me.piggypiglet.funkytrees.boot.framework.Registerable;
import me.piggypiglet.funkytrees.commands.CommandHandler;
import me.piggypiglet.funkytrees.commands.framework.Command;
import me.piggypiglet.funkytrees.scanning.framework.Scanner;

import java.util.stream.Collectors;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class CommandsRegisterable extends Registerable {
    @Inject private Scanner scanner;
    @Inject private CommandHandler commandHandler;

    @Override
    protected void execute() {
        commandHandler.populate(scanner.getSubTypesOf(Command.class).stream()
                .map(injector::getInstance)
                .collect(Collectors.toSet()));
    }
}
