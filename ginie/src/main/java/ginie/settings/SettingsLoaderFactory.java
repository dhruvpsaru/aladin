package ginie.settings;

/**
 * Created by dhruvr on 28/6/16.
 */
public class SettingsLoaderFactory {

    private SettingsLoaderFactory() {
    }

    public static SettingsLoader loadFromResource(String resourceName) {
        if (resourceName.endsWith(".json")) {
            return new JsonSettingsLoader();
        } else if (resourceName.endsWith(".yml") || resourceName.endsWith(".yaml")) {
            return new YamlSettingsLoader();
        } else {
            throw new IllegalArgumentException(String.format("%s resource is not supported right now"));
        }
    }

}
