package me.piggypiglet.funkytrees.tree.listener;

import com.google.common.collect.Sets;
import com.google.inject.Inject;
import me.piggypiglet.funkytrees.file.objects.Config;
import me.piggypiglet.funkytrees.tree.replacement.TreeReplacer;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.StructureGrowEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class TreeGrowListener implements Listener {
    private static final Set<Material> LOGS = Sets.newHashSet(
            Material.OAK_LOG,
            Material.SPRUCE_LOG,
            Material.BIRCH_LOG,
            Material.JUNGLE_LOG,
            Material.ACACIA_LOG,
            Material.DARK_OAK_LOG,
            Material.CRIMSON_STEM,
            Material.WARPED_STEM
    );

    private static final Set<Material> LEAVES = Sets.newHashSet(
            Material.OAK_LEAVES,
            Material.SPRUCE_LEAVES,
            Material.BIRCH_LEAVES,
            Material.JUNGLE_LEAVES,
            Material.ACACIA_LEAVES,
            Material.DARK_OAK_LEAVES,
            Material.NETHER_WART_BLOCK,
            Material.WARPED_WART_BLOCK
    );

    @Inject private Config config;
    @Inject private TreeReplacer replacer;

    @EventHandler
    public void onTreeGrow(@NotNull final StructureGrowEvent event) {
        System.out.println(config.getEnabledWorlds());
        if (!config.getEnabledWorlds().contains(event.getWorld().getName())) return;

        final List<BlockState> states = event.getBlocks();

        final Set<BlockState> logs = states.stream()
                .filter(state -> LOGS.contains(state.getType()))
                .collect(Collectors.toSet());

        if (logs.isEmpty()) return;

        final Set<BlockState> leaves = states.stream()
                .filter(state -> LEAVES.contains(state.getType()))
                .collect(Collectors.toSet());

        if (leaves.isEmpty()) return;

        replacer.replace(logs, leaves);
    }
}
