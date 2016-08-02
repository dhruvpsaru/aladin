package ginie.settings;

import com.google.common.collect.ImmutableMap;

import java.io.File;
import java.io.IOException;

/**
 * Created by dhruvr on 28/6/16.
 */
public abstract class SettingsLoader {

    public abstract ImmutableMap<String, String> load(File sourceFile) throws IOException;


}
