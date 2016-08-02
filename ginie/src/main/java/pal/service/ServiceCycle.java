package pal.service;

import pal.ControlException;

/**
 * Created by dhruvr on 16/7/16.
 */
public interface ServiceCycle<T> {

    Lifecycle.State lifecycleState();

    void addLifeCycleListener(LifeCycleListener lifeCycleListener);

    void removeLifeCycleListener(LifeCycleListener lifeCycleListener);

    T start() throws ControlException;

    T stop() throws ControlException;

    void close() throws ControlException;

}
