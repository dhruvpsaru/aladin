package pal.service;

import pal.ControlException;

/**
 * Lifecycle state. Allows the following transitions:
 * <ul>
 * <li>INITIALIZED -> STARTED, STOPPED, CLOSED</li>
 * <li>STARTED     -> STOPPED</li>
 * <li>STOPPED     -> STARTED, CLOSED</li>
 * <li>CLOSED      -> </li>
 * </ul>
 * <p/>
 * <p>Also allows to stay in the same state. For example, when calling stop on a component, the
 * following logic can be applied:
 * <p/>
 * <pre>
 * public void stop() {
 *  if (!lifeccycleState.moveToStopped()) {
 *      return;
 *  }
 * // continue with stop logic
 * }
 * </pre>
 * <p/>
 * <p>Note, closed is only allowed to be called when stopped, so make sure to stop the component first.
 * Here is how the logic can be applied:
 * <p/>
 * <pre>
 * public void close() {
 *  if (lifecycleState.started()) {
 *      stop();
 *  }
 *  if (!lifecycleState.moveToClosed()) {
 *      return;
 *  }
 *  // perofrm close logic here
 * }
 * </pre>
 */
public class Lifecycle {


    public static enum State {
        INITIALIZED,
        STOPPED,
        STARTED,
        CLOSED
    }

    private volatile State state = State.INITIALIZED;


    public State state() {
        return state;
    }


    public boolean initialized() {
        return state == State.INITIALIZED;
    }

    public boolean started() {
        return state == State.STARTED;
    }

    public boolean closed() {
        return state == State.CLOSED;
    }

    public boolean stopped() {
        return state == State.STOPPED;
    }


    public boolean stoppedOrClosed() {
        Lifecycle.State state = this.state;
        return state == State.STOPPED || state == State.CLOSED;
    }

    public boolean canMoveToStarted() throws ControlException {
        State localState = this.state;
        if (localState == State.INITIALIZED || localState == State.STOPPED) {
            return true;
        }
        if (localState == State.STARTED) {
            return false;
        }
        if (localState == State.CLOSED) {
            throw new ControlException("Can't move to started state when closed");
        }
        throw new ControlException("Can't move to started with unknown state");
    }


    public boolean moveToStarted() throws ControlException {
        State localState = this.state;
        if (localState == State.INITIALIZED || localState == State.STOPPED) {
            state = State.STARTED;
            return true;
        }
        if (localState == State.STARTED) {
            return false;
        }
        if (localState == State.CLOSED) {
            throw new ControlException("Can't move to started state when closed");
        }
        throw new ControlException("Can't move to started with unknown state");
    }

    public boolean canMoveToStopped() throws ControlException {
        State localState = state;
        if (localState == State.STARTED) {
            return true;
        }
        if (localState == State.INITIALIZED || localState == State.STOPPED) {
            return false;
        }
        if (localState == State.CLOSED) {
            throw new ControlException("Can't move to started state when closed");
        }
        throw new ControlException("Can't move to started with unknown state");
    }

    public boolean moveToStopped() throws ControlException {
        State localState = state;
        if (localState == State.STARTED) {
            state = State.STOPPED;
            return true;
        }
        if (localState == State.INITIALIZED || localState == State.STOPPED) {
            return false;
        }
        if (localState == State.CLOSED) {
            throw new ControlException("Can't move to started state when closed");
        }
        throw new ControlException("Can't move to started with unknown state");
    }

    public boolean canMoveToClosed() throws ControlException {
        State localState = state;
        if (localState == State.CLOSED) {
            return false;
        }
        if (localState == State.STARTED) {
            throw new ControlException("Can't move to closed before moving to stopped mode");
        }
        return true;
    }


    public boolean moveToClosed() throws ControlException {
        State localState = state;
        if (localState == State.CLOSED) {
            return false;
        }
        if (localState == State.STARTED) {
            throw new ControlException("Can't move to closed before moving to stopped mode");
        }
        state = State.CLOSED;
        return true;
    }


    @Override
    public String toString() {
        return state.toString();
    }
}
