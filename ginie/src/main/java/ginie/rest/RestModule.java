package ginie.rest;

import com.google.inject.AbstractModule;
import org.eclipse.jetty.servlet.DefaultServlet;

/**
 * Created by dhruvr on 25/7/16.
 */
public class RestModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(DefaultServlet.class).asEagerSingleton();
        bind(TestAPI.class).asEagerSingleton();
    }
}
