package ginie.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ginie.server.ServerServiceListener;

/**
 * Created by dhruvr on 31/7/16.
 */
public class KernelServiceListener implements LifeCycleListener {


    private static final Logger LOGGER = LoggerFactory.getLogger(ServerServiceListener.class);

    @Override
    public void beforeStart() {

    }

    @Override
    public void afterStart() {
        LOGGER.info("\t\t kernel service started");
    }

    @Override
    public void beforeStop() {

    }

    @Override
    public void afterStop() {
        LOGGER.info("\t\t Kernel service stopped ");
    }

    @Override
    public void beforeClose() {

    }

    @Override
    public void afterClose() {
        LOGGER.info("\t\t Kernel service closed");
    }
}
