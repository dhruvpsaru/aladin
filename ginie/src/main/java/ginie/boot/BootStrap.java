package ginie.boot;

import ginie.GinieException;
import ginie.service.AbstractLifeCycleService;
import ginie.service.KernelServiceImpl;
import ginie.service.KernelServiceListener;
import ginie.settings.GinieSettings;

import java.util.concurrent.CountDownLatch;

/**
 * Created by dhruvr on 31/7/16.
 */
public class BootStrap {

    private final CommandOptions commandOptions;
    private final GinieSettings ginieSettings;

    public BootStrap(GinieSettings ginieSettings, CommandOptions commandOptions) {
        this.ginieSettings = ginieSettings;
        this.commandOptions = commandOptions;
    }

    public void start() {

        try {
            AbstractLifeCycleService kernelService = new KernelServiceImpl(ginieSettings, commandOptions);
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
            throw new GinieException("Problem in booting ", e);
        }
    }

}

