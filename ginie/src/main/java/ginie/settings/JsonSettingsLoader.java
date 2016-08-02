package ginie.settings;

import com.google.common.collect.ImmutableMap;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.io.IOException;

/**
 * Created by dhruvr on 28/6/16.
 */
public class JsonSettingsLoader extends SettingsLoader{

    public ImmutableMap<String, String> load(File sourceFile) throws IOException {
        throw new NotImplementedException();
    }
}
