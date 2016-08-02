package pal.service;

import com.google.inject.AbstractModule;
import pal.server.ServerService;
import pal.server.ServerServiceImpl;

/**
 * Created by dhruvr on 31/7/16.
 */
public class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ServerService.class).to(ServerServiceImpl.class).asEagerSingleton();
    }
}
