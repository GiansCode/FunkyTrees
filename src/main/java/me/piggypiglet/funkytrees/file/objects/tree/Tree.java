package me.piggypiglet.funkytrees.file.objects.tree;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class Tree {
    private final Material log;
    private final Material leaf;

    public Tree(@NotNull final Material log, @NotNull final Material leaf) {
        this.log = log;
        this.leaf = leaf;
    }

    @NotNull
    public Material getLog() {
        return log;
    }

    @NotNull
    public Material getLeaf() {
        return leaf;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "log=" + log +
                ", leaf=" + leaf +
                '}';
    }
}
