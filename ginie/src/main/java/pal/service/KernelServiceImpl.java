package pal.service;

import com.google.inject.Injector;
import pal.ControlException;
import pal.control.CommandOptions;
import pal.control.ControlServletModule;
import pal.database.DBModule;
import pal.rest.RestModule;
import pal.server.ServerModule;
import pal.server.ServerService;
import pal.settings.ControlSettings;

/**
 * Created by dhruvr on 31/7/16.
 */
public class KernelServiceImpl extends AbstractLifeCycleService<KernelService> implements KernelService {

    private Injector injector;

    public KernelServiceImpl(ControlSettings controlSettings, CommandOptions commandOptions) {
        super(controlSettings, commandOptions);
        initialize();
    }

    @Override
    protected void dostart() throws ControlException {
        try {

            if (lifeCycle.canMoveToStarted()) {
                injector.getInstance(ServerService.class).start();
            }

            lifeCycle.moveToStarted();

        } catch (Exception e) {
            throw new ControlException("Problem in Kernel Service ", e);
        }
    }

    @Override
    protected void dostop() throws ControlException {
        if (lifeCycle.canMoveToStopped()) {
            injector.getInstance(ServerService.class).stop();
        }
        lifeCycle.canMoveToStopped();
    }

    @Override
    protected void doClose() throws ControlException {
        if (lifeCycle.canMoveToClosed()) {
            injector.getInstance(ServerService.class).close();
        }
        lifeCycle.moveToClosed();
    }

    @Override
    public void initialize() {

        ServiceBuilder serviceBuilder = new ServiceBuilder();
        serviceBuilder.addModule(binder -> {
            binder.bind(ControlSettings.class).toInstance(controlSettings);
            binder.bind(CommandOptions.class).toInstance(commandOptions);
        });
        serviceBuilder.addModule(new DBModule());
        serviceBuilder.addModule(new ServerModule());
        serviceBuilder.addModule(new ControlServletModule());
        serviceBuilder.addModule(new RestModule());
        serviceBuilder.addModule(new ServiceModule());

        injector = serviceBuilder.createInjector();
    }
}
