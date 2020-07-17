package me.piggypiglet.funkytrees.file.objects;

import com.google.inject.Singleton;
import me.piggypiglet.funkytrees.file.annotations.File;
import me.piggypiglet.funkytrees.file.objects.tree.Tree;
import me.piggypiglet.funkytrees.utils.collection.ProbabilityCollection;
import org.bukkit.TreeType;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
@Singleton
@File(
        internalPath = "/config.yml",
        externalPath = "config.yml"
)
public final class Config {
    private Map<TreeType, ProbabilityCollection<Tree>> trees;
    private Set<String> enabledWorlds;

    @NotNull
    public Set<String> getEnabledWorlds() {
        return enabledWorlds;
    }

    @NotNull
    public Map<TreeType, ProbabilityCollection<Tree>> getTrees() {
        return trees;
    }
}
