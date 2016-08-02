package pal.control;

import pal.ControlException;
import pal.service.AbstractLifeCycleService;
import pal.service.KernelServiceImpl;
import pal.service.KernelServiceListener;
import pal.settings.ControlSettings;

import java.util.concurrent.CountDownLatch;

/**
 * Created by dhruvr on 31/7/16.
 */
public class BootStrap {

    private final CommandOptions commandOptions;
    private final ControlSettings controlSettings;

    public BootStrap(ControlSettings controlSettings, CommandOptions commandOptions) {
        this.controlSettings = controlSettings;
        this.commandOptions = commandOptions;
    }

    public void start() {

        try {
            AbstractLifeCycleService kernelService = new KernelServiceImpl(controlSettings, commandOptions);
            kernelService.addLifeCycleListener(new KernelServiceListener());

            final CountDownLatch countDownLatch = new CountDownLatch(1);
            kernelService.start();
            countDownLatch.await();

            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    countDownLatch.countDown();
                }
            }));

            kernelService.stop();

        } catch (Exception e) {
            throw new ControlException("Problem in booting ", e);
        }
    }

}

