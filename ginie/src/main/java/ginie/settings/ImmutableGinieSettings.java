package ginie.settings;

import com.beust.jcommander.internal.Maps;
import com.google.common.collect.ImmutableMap;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

/**
 * Created by dhruvr on 29/6/16.
 */
public class ImmutableGinieSettings implements GinieSettings {

    private final Map<String, String> map;

    private ImmutableGinieSettings(Map<String, String> settings) {
        this.map = ImmutableMap.copyOf(settings);
    }

    public Map<String, String> getMap() {
        return map;
    }

    public String get(String setting) {
        return map.get(setting);
    }

    public String get(String setting, String defaultValue) {
        String value = get(setting);
        return value == null ? defaultValue : value;
    }

    public Integer getAsInt(String setting, Integer defaultValue) {
        String sValue = get(setting);
        if (sValue == null) {
            return defaultValue;
        }

        try {
            return Integer.parseInt(sValue);
        } catch (NumberFormatException e) {
            throw new SettingsException("Failed to parse int setting [" + setting + "] with value [" + sValue + "]", e);
        }
    }

    public Long getAsLong(String setting, Long defaultValue) {
        String sValue = get(setting);
        if (sValue == null) {
            return defaultValue;
        }

        try {
            return Long.parseLong(sValue);
        } catch (NumberFormatException e) {
            throw new SettingsException("Failed to parse Long setting [" + setting + "] with value [" + sValue + "]", e);
        }
    }

    public Boolean getAsBoolean(String setting, Boolean defaultValue) {
        String sValue = get(setting);
        if (sValue == null) {
            return defaultValue;
        }

        try {
            return Boolean.parseBoolean(sValue);
        } catch (NumberFormatException e) {
            throw new SettingsException("Failed to parse Boolean setting [" + setting + "] with value [" + sValue + "]", e);
        }
    }

    public static class Builder implements GinieSettings.Builder {

        private final Map<String, String> map = Maps.newLinkedHashMap();

        private Builder() {

        }

        public Builder put(Map<String, String> settings) {
            map.putAll(settings);
            return this;
        }

        public Builder loadFromFile(File sourceFile) {
            SettingsLoader settingsLoader = SettingsLoaderFactory.loadFromResource(sourceFile.getName());
            try {
                Map<String, String> loadedSettings = settingsLoader.load(sourceFile);
                put(loadedSettings);
            } catch (IOException e) {
                throw new SettingsException("Failed to load settings from [" + sourceFile.getName() + "]", e);
            }
            return this;
        }

        public GinieSettings build() {
            return new ImmutableGinieSettings(Collections.unmodifiableMap(map));
        }
    }

    public static Builder settingsBuilder(){
        return new Builder();
    }
}
