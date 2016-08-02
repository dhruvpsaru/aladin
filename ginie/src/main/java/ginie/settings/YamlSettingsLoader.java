package ginie.settings;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.common.collect.ImmutableMap;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.beust.jcommander.internal.Lists.newArrayList;
import static com.google.inject.internal.util.$Maps.newHashMap;

/**
 * Created by dhruvr on 28/6/16.
 */
public class YamlSettingsLoader extends SettingsLoader {

    private final static YAMLFactory yamlFactory;

    static {
        yamlFactory = new YAMLFactory();
    }

    @Override
    public ImmutableMap<String, String> load(File sourceFile) throws IOException {
        JsonParser jsonParser = yamlFactory.createParser(sourceFile);
        return load(jsonParser);
    }

    private ImmutableMap<String, String> load(final JsonParser parser) throws IOException {

        StringBuilder sb = new StringBuilder();
        Map<String, String> settings = newHashMap();
        List<String> path = newArrayList();

        JsonToken token = parser.nextToken();
        if (token == null) {
            throw new IllegalArgumentException("Token is null");
        }

        if (!token.equals(JsonToken.START_OBJECT)) {
            throw new IllegalArgumentException("malformed, expected pal.settings to start with 'object', instead was [" + token + "]");
        }

        serializeObject(settings, sb, path, parser, null);
        return ImmutableMap.<String, String>builder().putAll(settings).build();
    }

    private void serializeObject(Map<String, String> settings, StringBuilder sb, List<String> path, JsonParser parser, String objFieldName) throws IOException {
        if (objFieldName != null) {
            path.add(objFieldName);
        }

        String currentFieldName = null;
        JsonToken token;
        while ((token = parser.nextToken()) != JsonToken.END_OBJECT) {
            if (token == JsonToken.START_OBJECT) {
                serializeObject(settings, sb, path, parser, currentFieldName);
            } else if (token == JsonToken.START_ARRAY) {
                serializeArray(settings, sb, path, parser, currentFieldName);
            } else if (token == JsonToken.FIELD_NAME) {
                currentFieldName = parser.getCurrentName();
            } else if (token == JsonToken.VALUE_NULL) {
                // ignore this
            } else {
                serializeValue(settings, sb, path, parser, currentFieldName);
            }
        }

        if (objFieldName != null) {
            path.remove(path.size() - 1);
        }
    }


    private void serializeArray(Map<String, String> settings, StringBuilder sb, List<String> path, JsonParser parser, String fieldName) throws IOException {
        JsonToken token;
        int counter = 0;
        while ((token = parser.nextToken()) != JsonToken.END_ARRAY) {
            if (token == JsonToken.START_OBJECT) {
                serializeObject(settings, sb, path, parser, fieldName + '.' + (counter++));
            } else if (token == JsonToken.START_ARRAY) {
                serializeArray(settings, sb, path, parser, fieldName + '.' + (counter++));
            } else if (token == JsonToken.FIELD_NAME) {
                fieldName = parser.getCurrentName();
            } else if (token == JsonToken.VALUE_NULL) {
                // ignore
            } else {
                serializeValue(settings, sb, path, parser, fieldName + '.' + (counter++));
            }
        }
    }

    private void serializeValue(Map<String, String> settings, StringBuilder sb, List<String> path, JsonParser parser, String fieldName) throws IOException {
        sb.setLength(0);
        for (String pathEle : path) {
            sb.append(pathEle).append('.');
        }
        sb.append(fieldName);
        settings.put(sb.toString(), parser.getText());
    }

}
