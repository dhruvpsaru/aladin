package ginie.boot;

import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import java.util.HashMap;

/**
 * Created by dhruvr on 31/7/16.
 */
public class ControlServletModule extends ServletModule {

    @Override
    protected void configureServlets() {
        HashMap<String, String> options = new HashMap<>();
        options.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
        serve("/*").with(GuiceContainer.class, options);
    }
}
