package pal.server;

import com.google.inject.Inject;
import org.eclipse.jetty.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pal.ControlException;
import pal.control.CommandOptions;
import pal.service.AbstractLifeCycleService;
import pal.settings.ControlSettings;

/**
 * Created by dhruvr on 25/7/16.
 */
public class ServerServiceImpl extends AbstractLifeCycleService<ServerService> implements ServerService {


    private static final Logger LOGGER = LoggerFactory.getLogger(ServerServiceImpl.class);

    private final Server server;
    private final ServerServiceListener listener;

    @Inject
    public ServerServiceImpl(ControlSettings controlSettings,
                             CommandOptions commandOptions,
                             Server server,
                             ServerServiceListener listener) {
        super(controlSettings, commandOptions);
        this.server = server;
        this.listener = listener;
    }

    @Override
    protected void dostart() throws ControlException {
        startServer();
        addLifeCycleListener(listener);
    }

    @Override
    protected void dostop() throws ControlException {

    }

    @Override
    protected void doClose() throws ControlException {
        if (!lifeCycle.canMoveToClosed()) {
            stopServer();
        }
    }


    @Override
    public void startServer() {
        try {
            server.start();
        } catch (Exception e) {
            throw new ControlException("Problem in starting server ", e);
        }
    }

    @Override
    public void stopServer() {
        try {
            server.stop();
        } catch (Exception e) {
            throw new ControlException("Problem while closing server ", e);
        }
    }

    @Override
    public void serverGracefulStop() {
        server.setGracefulShutdown(10);
    }
}
