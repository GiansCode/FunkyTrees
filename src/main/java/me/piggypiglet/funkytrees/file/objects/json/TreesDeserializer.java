package me.piggypiglet.funkytrees.file.objects.json;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import me.piggypiglet.funkytrees.file.objects.tree.Tree;
import me.piggypiglet.funkytrees.file.objects.tree.enums.GrowMethod;
import me.piggypiglet.funkytrees.file.objects.tree.enums.TreeType;
import me.piggypiglet.funkytrees.utils.collection.ProbabilityCollection;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------or
public final class TreesDeserializer implements JsonDeserializer<Table<TreeType, GrowMethod, ProbabilityCollection<Tree>>> {
    private static final Type MAP_TYPE = new TypeToken<Map<String, Set<Map<String, Object>>>>() {}.getType();

    @Override
    public Table<TreeType, GrowMethod, ProbabilityCollection<Tree>> deserialize(@NotNull final JsonElement json, @NotNull final Type typeOfT,
                                                                                @NotNull final JsonDeserializationContext context) {
        final Map<String, Set<Map<String, Object>>> rawDeserialization = context.deserialize(json, MAP_TYPE);
        final Table<TreeType, GrowMethod, ProbabilityCollection<Tree>> result = HashBasedTable.create();

        for (final TreeType type : TreeType.values()) {
            for (final GrowMethod method : GrowMethod.VALUES) {
                result.put(type, method, new ProbabilityCollection<>());
            }
        }

        rawDeserialization.forEach((key, value) -> {
            final TreeType type = TreeType.valueOf(key.toUpperCase());

            value.forEach(map -> {
                final GrowMethod growMethod = GrowMethod.valueOf(((String) map.get("grow_method")).toUpperCase());

                final Tree tree = new Tree(
                        Material.valueOf(((String) map.get("log")).toUpperCase()),
                        Material.valueOf(((String) map.get("leaf")).toUpperCase())
                );
                final int chance = (int) (double) map.get("chance");

                if (growMethod == GrowMethod.BOTH) {
                    Arrays.stream(GrowMethod.VALUES).forEach(method -> result.get(type, method).add(tree, chance));
                } else {
                    result.get(type, growMethod).add(tree, chance);
                }
            });
        });

        return result;
    }
}
