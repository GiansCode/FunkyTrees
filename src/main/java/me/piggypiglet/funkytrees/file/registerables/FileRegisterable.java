package me.piggypiglet.funkytrees.file.registerables;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import me.piggypiglet.funkytrees.boot.framework.Registerable;
import me.piggypiglet.funkytrees.file.FileManager;
import me.piggypiglet.funkytrees.file.annotations.File;

import java.util.Set;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class FileRegisterable extends Registerable {
    @Inject @Named("files") private Set<Class<?>> fileClasses;
    @Inject private FileManager fileManager;

    @Override
    protected void execute() {
        for (final Class<?> clazz : fileClasses) {
            final File data = clazz.getAnnotation(File.class);
            fileManager.loadFile(clazz, data.internalPath(), data.externalPath());
        }
    }
}
