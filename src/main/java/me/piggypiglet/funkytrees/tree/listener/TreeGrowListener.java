package me.piggypiglet.funkytrees.tree.listener;

import com.google.inject.Inject;
import me.piggypiglet.funkytrees.file.objects.Config;
import me.piggypiglet.funkytrees.file.objects.tree.enums.GrowMethod;
import me.piggypiglet.funkytrees.file.objects.tree.enums.TreeType;
import me.piggypiglet.funkytrees.tree.replacement.TreeReplacer;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.StructureGrowEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class TreeGrowListener implements Listener {
    @Inject private Config config;
    @Inject private TreeReplacer replacer;

    @EventHandler
    public void onTreeGrow(@NotNull final StructureGrowEvent event) {
        if (!config.getEnabledWorlds().contains(event.getWorld().getName())) return;

        final List<BlockState> states = event.getBlocks();

        final AtomicReference<TreeType> treeType = new AtomicReference<>(null);
        final Set<BlockState> logs = states.stream()
                .filter(state -> TreeType.LOGS.contains(state.getType()))
                .peek(state -> {
                    if (treeType.get() == null) treeType.set(TreeType.fromLog(state.getType()));
                })
                .collect(Collectors.toSet());

        if (logs.isEmpty()) return;

        final Set<BlockState> leaves = states.stream()
                .filter(state -> TreeType.LEAVES.contains(state.getType()))
                .collect(Collectors.toSet());

        if (leaves.isEmpty()) return;

        replacer.replace(logs, leaves, treeType.get(), event.isFromBonemeal() ? GrowMethod.BONEMEAL : GrowMethod.NATURAL);
    }
}
