package me.piggypiglet.funkytrees.file.objects;

import com.google.gson.annotations.JsonAdapter;
import me.piggypiglet.funkytrees.file.annotations.File;
import me.piggypiglet.funkytrees.file.objects.json.MaterialDeserializer;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
@File(
        internalPath = "/config.yml",
        externalPath = "config.yml"
)
public final class Config {
    @JsonAdapter(MaterialDeserializer.class) private Material leafReplacement;
    @JsonAdapter(MaterialDeserializer.class) private Material logReplacement;
    private Set<String> enabledWorlds;

    @NotNull
    public Material getLeafReplacement() {
        return leafReplacement;
    }

    @NotNull
    public Material getLogReplacement() {
        return logReplacement;
    }

    @NotNull
    public Set<String> getEnabledWorlds() {
        return enabledWorlds;
    }
}
