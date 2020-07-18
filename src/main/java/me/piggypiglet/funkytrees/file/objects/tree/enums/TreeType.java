package me.piggypiglet.funkytrees.file.objects.tree.enums;

import com.google.common.collect.Sets;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Set;

import static org.bukkit.Material.*;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public enum TreeType {
    OAK(OAK_LOG, OAK_LEAVES),
    SPRUCE(SPRUCE_LOG, SPRUCE_LEAVES),
    BIRCH(BIRCH_LOG, BIRCH_LEAVES),
    JUNGLE(JUNGLE_LOG, JUNGLE_LEAVES),
    ACACIA(ACACIA_LOG, ACACIA_LEAVES),
    DARK_OAK(DARK_OAK_LOG, DARK_OAK_LEAVES),
    CRIMSON(CRIMSON_STEM, NETHER_WART_BLOCK),
    WARPED(WARPED_STEM, WARPED_WART_BLOCK);

    public static final Set<Material> LOGS = Sets.newHashSet(
            OAK.log, SPRUCE.log, BIRCH.log, JUNGLE.log,
            ACACIA.log, DARK_OAK.log, CRIMSON.log, WARPED.log
    );
    public static final Set<Material> LEAVES = Sets.newHashSet(
            OAK.leaf, SPRUCE.leaf, BIRCH.leaf, JUNGLE.leaf,
            ACACIA.leaf, DARK_OAK.leaf, CRIMSON.leaf, WARPED.leaf
    );

    private final Material log;
    private final Material leaf;

    TreeType(@NotNull final Material log, @NotNull final Material leaf) {
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

    @Nullable
    public static TreeType fromLog(@NotNull final Material log) {
        return Arrays.stream(values())
                .filter(type -> type.log == log)
                .findAny().orElse(null);
    }
}
