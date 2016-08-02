package ginie.settings;

import java.util.Map;

/**
 * Created by dhruvr on 27/6/16.
 */
public interface GinieSettings {

    Map<String, String> getMap();

    String get(String setting);

    String get(String setting, String defaultValue);

    Integer getAsInt(String setting, Integer defaultValue);

    Long getAsLong(String setting, Long defaultValue);

    Boolean getAsBoolean(String setting, Boolean defaultValue);

    interface Builder {
        GinieSettings build();
    }
}
