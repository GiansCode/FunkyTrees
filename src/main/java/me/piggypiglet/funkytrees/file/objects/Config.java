package me.piggypiglet.funkytrees.file.objects;

import com.google.common.collect.Table;
import com.google.gson.annotations.JsonAdapter;
import com.google.inject.Singleton;
import me.piggypiglet.funkytrees.file.annotations.File;
import me.piggypiglet.funkytrees.file.objects.json.TreesDeserializer;
import me.piggypiglet.funkytrees.file.objects.tree.Tree;
import me.piggypiglet.funkytrees.file.objects.tree.enums.GrowMethod;
import me.piggypiglet.funkytrees.file.objects.tree.enums.TreeType;
import me.piggypiglet.funkytrees.utils.collection.ProbabilityCollection;
import org.jetbrains.annotations.NotNull;

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
    @JsonAdapter(TreesDeserializer.class) private Table<TreeType, GrowMethod, ProbabilityCollection<Tree>> trees;
    private Set<String> enabledWorlds;

    @NotNull
    public Set<String> getEnabledWorlds() {
        return enabledWorlds;
    }

    @NotNull
    public Table<TreeType, GrowMethod, ProbabilityCollection<Tree>> getTrees() {
        return trees;
    }
}
