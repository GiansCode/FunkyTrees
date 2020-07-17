package me.piggypiglet.funkytrees.tree.replacement;

import com.google.inject.Inject;
import me.piggypiglet.funkytrees.file.objects.Config;
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

    public void replace(@NotNull final Set<BlockState> logs, @NotNull final Set<BlockState> leaves) {
        replace(logs, config.getLogReplacement());
        replace(leaves, config.getLeafReplacement());
    }

    private void replace(@NotNull final Set<BlockState> states, @NotNull final Material material) {
        states.stream()
                .map(BlockState::getBlock)
                .forEach(block -> block.setType(material));
    }
}
