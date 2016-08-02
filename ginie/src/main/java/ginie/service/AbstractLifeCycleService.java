package ginie.service;

import ginie.GinieException;
import ginie.boot.CommandOptions;
import ginie.settings.GinieSettings;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by dhruvr on 16/7/16.
 */
public abstract class AbstractLifeCycleService<T> extends AbstractService<T> implements ServiceCycle<T> {

    protected final Lifecycle lifeCycle = new Lifecycle();
    private final List<LifeCycleListener> listeners = new CopyOnWriteArrayList<LifeCycleListener>();

    protected AbstractLifeCycleService(GinieSettings ginieSettings, CommandOptions commandOptions) {
        super(ginieSettings, commandOptions);
    }

    public Lifecycle.State lifecycleState() {
        return this.lifeCycle.state();
    }

    @Override
    public void addLifeCycleListener(LifeCycleListener lifeCycleListener) {
        listeners.add(lifeCycleListener);
    }

    @Override
    public void removeLifeCycleListener(LifeCycleListener lifecycleListener) {
        listeners.remove(lifecycleListener);
    }

    @Override
    public T start() throws GinieException {

        if (!lifeCycle.canMoveToStarted()) {
            return (T) this;
        }

        listeners.forEach(LifeCycleListener::beforeStart);
        dostart();
        lifeCycle.moveToStarted();
        listeners.forEach(LifeCycleListener::afterStart);

        return (T) this;
    }


    protected abstract void dostart() throws GinieException;

    @Override
    public T stop() throws GinieException {

        if (!lifeCycle.canMoveToStopped()) {
            return (T) this;
        }

        listeners.forEach(LifeCycleListener::beforeStop);
        dostop();
        listeners.forEach(LifeCycleListener::afterStop);

        return (T) this;
    }


    protected abstract void dostop() throws GinieException;

    @Override
    public void close() throws GinieException {

        if (lifeCycle.started()) {
            stop();
        }

        if (!lifeCycle.canMoveToClosed()) {
            return;
        }


        listeners.forEach(LifeCycleListener::beforeClose);
        doClose();
        lifeCycle.moveToClosed();
        listeners.forEach(LifeCycleListener::afterClose);
    }

    protected abstract void doClose() throws GinieException;


}
