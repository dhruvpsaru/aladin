package pal.service;

import pal.ControlException;
import pal.control.CommandOptions;
import pal.settings.ControlSettings;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by dhruvr on 16/7/16.
 */
public abstract class AbstractLifeCycleService<T> extends AbstractService<T> implements ServiceCycle<T> {

    protected final Lifecycle lifeCycle = new Lifecycle();
    private final List<LifeCycleListener> listeners = new CopyOnWriteArrayList<LifeCycleListener>();

    protected AbstractLifeCycleService(ControlSettings controlSettings, CommandOptions commandOptions) {
        super(controlSettings, commandOptions);
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
    public T start() throws ControlException {

        if (!lifeCycle.canMoveToStarted()) {
            return (T) this;
        }

        listeners.forEach(LifeCycleListener::beforeStart);
        dostart();
        lifeCycle.moveToStarted();
        listeners.forEach(LifeCycleListener::afterStart);

        return (T) this;
    }


    protected abstract void dostart() throws ControlException;

    @Override
    public T stop() throws ControlException {

        if (!lifeCycle.canMoveToStopped()) {
            return (T) this;
        }

        listeners.forEach(LifeCycleListener::beforeStop);
        dostop();
        listeners.forEach(LifeCycleListener::afterStop);

        return (T) this;
    }


    protected abstract void dostop() throws ControlException;

    @Override
    public void close() throws ControlException {

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

    protected abstract void doClose() throws ControlException;


}
