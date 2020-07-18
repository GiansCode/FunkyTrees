package me.piggypiglet.funkytrees.tree.replacement;

import com.google.common.collect.Table;
import com.google.inject.Inject;
import me.piggypiglet.funkytrees.file.objects.Config;
import me.piggypiglet.funkytrees.file.objects.tree.Tree;
import me.piggypiglet.funkytrees.file.objects.tree.enums.GrowMethod;
import me.piggypiglet.funkytrees.file.objects.tree.enums.TreeType;
import me.piggypiglet.funkytrees.utils.collection.ProbabilityCollection;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class TreeReplacer {
    @Inject private Config config;

    public void replace(@NotNull final Set<BlockState> logs, @NotNull final Set<BlockState> leaves,
                        @NotNull final TreeType treeType, @NotNull final GrowMethod growMethod) {
        final Table<TreeType, GrowMethod, ProbabilityCollection<Tree>> trees = config.getTrees();

        if (trees.isEmpty()) return;

        final Tree tree = trees.get(treeType, growMethod).get();

        replace(logs, tree.getLog());
        replace(leaves, tree.getLeaf());
    }

    private void replace(@NotNull final Set<BlockState> states, @NotNull final Material material) {
        states.forEach(state -> state.setType(material));
    }
}
