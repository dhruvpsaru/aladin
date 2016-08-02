package pal.server;

import com.google.inject.AbstractModule;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

/**
 * Created by dhruvr on 25/7/16.
 */
public class ServerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Server.class).toProvider(JettyServerProvider.class).asEagerSingleton();
        bind(ServletContextHandler.class).toProvider(ServletContextHandlerProvider.class).asEagerSingleton();
        bind(ServerServiceListener.class).asEagerSingleton();
    }
}
