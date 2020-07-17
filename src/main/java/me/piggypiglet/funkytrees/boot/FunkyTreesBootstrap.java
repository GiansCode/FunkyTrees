package me.piggypiglet.funkytrees.boot;

import com.google.common.collect.Lists;
import com.google.inject.Injector;
import me.piggypiglet.funkytrees.boot.framework.Registerable;
import me.piggypiglet.funkytrees.commands.registerables.CommandHandlerRegisterable;
import me.piggypiglet.funkytrees.commands.registerables.CommandsRegisterable;
import me.piggypiglet.funkytrees.events.EventsRegisterable;
import me.piggypiglet.funkytrees.file.registerables.FileObjectsRegisterable;
import me.piggypiglet.funkytrees.file.registerables.FileRegisterable;
import me.piggypiglet.funkytrees.guice.ExceptionalInjector;
import me.piggypiglet.funkytrees.guice.modules.DynamicModule;
import me.piggypiglet.funkytrees.guice.modules.InitialModule;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class FunkyTreesBootstrap extends JavaPlugin {
    private static final List<Class<? extends Registerable>> REGISTERABLES = Lists.newArrayList(
            FileObjectsRegisterable.class,
            FileRegisterable.class,
            CommandsRegisterable.class,
            CommandHandlerRegisterable.class,
            EventsRegisterable.class
    );

    @Override
    public void onEnable() {
        Injector injector = new ExceptionalInjector(new InitialModule(this).createInjector());

        for (final Class<? extends Registerable> registerableClass : REGISTERABLES) {
            final Registerable registerable = injector.getInstance(registerableClass);
            registerable.run(injector);

            if (!registerable.getBindings().isEmpty() || registerable.getStaticInjections().length > 0) {
                injector = injector.createChildInjector(new DynamicModule(
                        registerable.getBindings(),
                        registerable.getStaticInjections()
                ));
            }
        }
    }
}
