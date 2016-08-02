package ginie.server;

import com.google.inject.Inject;
import ginie.settings.GinieSettings;
import org.eclipse.jetty.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ginie.GinieException;
import ginie.boot.CommandOptions;
import ginie.service.AbstractLifeCycleService;

/**
 * Created by dhruvr on 25/7/16.
 */
public class ServerServiceImpl extends AbstractLifeCycleService<ServerService> implements ServerService {


    private static final Logger LOGGER = LoggerFactory.getLogger(ServerServiceImpl.class);

    private final Server server;
    private final ServerServiceListener listener;

    @Inject
    public ServerServiceImpl(GinieSettings ginieSettings,
                             CommandOptions commandOptions,
                             Server server,
                             ServerServiceListener listener) {
        super(ginieSettings, commandOptions);
        this.server = server;
        this.listener = listener;
    }

    @Override
    protected void dostart() throws GinieException {
        startServer();
        addLifeCycleListener(listener);
    }

    @Override
    protected void dostop() throws GinieException {

    }

    @Override
    protected void doClose() throws GinieException {
        if (!lifeCycle.canMoveToClosed()) {
            stopServer();
        }
    }


    @Override
    public void startServer() {
        try {
            server.start();
        } catch (Exception e) {
            throw new GinieException("Problem in starting server ", e);
        }
    }

    @Override
    public void stopServer() {
        try {
            server.stop();
        } catch (Exception e) {
            throw new GinieException("Problem while closing server ", e);
        }
    }

    @Override
    public void serverGracefulStop() {
        server.setGracefulShutdown(10);
    }
}
