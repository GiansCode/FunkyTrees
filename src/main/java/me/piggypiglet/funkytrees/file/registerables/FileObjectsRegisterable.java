package me.piggypiglet.funkytrees.file.registerables;

import com.google.inject.Inject;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import me.piggypiglet.funkytrees.boot.framework.Registerable;
import me.piggypiglet.funkytrees.file.annotations.File;
import me.piggypiglet.funkytrees.scanning.framework.Scanner;
import me.piggypiglet.funkytrees.utils.annotation.wrapper.AnnotationRules;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class FileObjectsRegisterable extends Registerable {
    private static final Named FILES = Names.named("files");

    @Inject private Scanner scanner;

    @Override
    protected void execute() {
        final Set<Class<?>> fileClasses = scanner.getClassesAnnotatedWith(AnnotationRules.hasAnnotation(File.class));

        addBinding(new TypeLiteral<Set<Class<?>>>(){}, FILES, fileClasses);
        addBinding(new TypeLiteral<Map<Class<?>, Object>>(){}, FILES, fileClasses.stream()
                .collect(Collectors.toMap(clazz -> clazz, injector::getInstance)));
    }
}