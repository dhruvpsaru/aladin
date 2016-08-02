package ginie.server;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.servlet.GuiceFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

import java.util.EnumSet;

/**
 * Created by dhruvr on 25/7/16.
 */
public class ServletContextHandlerProvider implements Provider<ServletContextHandler> {


    private static ServletContextHandler SERVLET_CONTEXT_PROVIDER;

    @Inject
    public ServletContextHandlerProvider(Server server) {
        if (SERVLET_CONTEXT_PROVIDER == null) {
            SERVLET_CONTEXT_PROVIDER = servletContextHandlerProvider(server);
        }
    }

    @Override
    public ServletContextHandler get() {
        return SERVLET_CONTEXT_PROVIDER;
    }


    private ServletContextHandler servletContextHandlerProvider(Server server) {
        ServletContextHandler context = new ServletContextHandler(server, "/");
        context.addFilter(GuiceFilter.class, "/*", EnumSet.of(javax.servlet.DispatcherType.REQUEST, javax.servlet.DispatcherType.ASYNC));
        context.addServlet(DefaultServlet.class, "/*");
        return context;
    }
}
