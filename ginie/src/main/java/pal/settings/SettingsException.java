package pal.settings;

import pal.ControlException;

/**
 * Created by dhruvr on 29/6/16.
 */
public class SettingsException extends ControlException {
    public SettingsException(String message, Throwable cause) {
        super(message, cause);
    }
}
