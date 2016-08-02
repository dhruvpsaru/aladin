package ginie.service;

import com.google.inject.Injector;
import ginie.GinieException;
import ginie.boot.CommandOptions;
import ginie.boot.ControlServletModule;
import ginie.database.DBModule;
import ginie.rest.RestModule;
import ginie.server.ServerModule;
import ginie.server.ServerService;
import ginie.settings.GinieSettings;

/**
 * Created by dhruvr on 31/7/16.
 */
public class KernelServiceImpl extends AbstractLifeCycleService<KernelService> implements KernelService {

    private Injector injector;

    public KernelServiceImpl(GinieSettings ginieSettings, CommandOptions commandOptions) {
        super(ginieSettings, commandOptions);
        initialize();
    }

    @Override
    protected void dostart() throws GinieException {
        try {

            if (lifeCycle.canMoveToStarted()) {
                injector.getInstance(ServerService.class).start();
            }

            lifeCycle.moveToStarted();

        } catch (Exception e) {
            throw new GinieException("Problem in Kernel Service ", e);
        }
    }

    @Override
    protected void dostop() throws GinieException {
        if (lifeCycle.canMoveToStopped()) {
            injector.getInstance(ServerService.class).stop();
        }
        lifeCycle.canMoveToStopped();
    }

    @Override
    protected void doClose() throws GinieException {
        if (lifeCycle.canMoveToClosed()) {
            injector.getInstance(ServerService.class).close();
        }
        lifeCycle.moveToClosed();
    }

    @Override
    public void initialize() {

        ServiceBuilder serviceBuilder = new ServiceBuilder();
        serviceBuilder.addModule(binder -> {
            binder.bind(GinieSettings.class).toInstance(ginieSettings);
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
