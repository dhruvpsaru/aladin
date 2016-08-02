package pal.server;

import pal.service.ServiceCycle;

/**
 * Created by dhruvr on 31/7/16.
 */
public interface ServerService extends ServiceCycle<ServerService> {
    void startServer();

    void serverGracefulStop();

    void stopServer();
}
