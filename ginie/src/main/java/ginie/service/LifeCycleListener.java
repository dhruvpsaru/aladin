package ginie.service;

/**
 * Created by dhruvr on 16/7/16.
 */
public interface LifeCycleListener {
    void beforeStart();

    void afterStart();

    void beforeStop();

    void afterStop();

    void beforeClose();

    void afterClose();
}
