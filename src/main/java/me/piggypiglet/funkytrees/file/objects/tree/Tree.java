package me.piggypiglet.funkytrees.file.objects.tree;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class Tree {
    private Material log;
    private Material leaf;

    @NotNull
    public Material getLog() {
        return log;
    }

    @NotNull
    public Material getLeaf() {
        return leaf;
    }
}
