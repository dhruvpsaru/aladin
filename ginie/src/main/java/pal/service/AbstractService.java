package pal.service;

import pal.control.CommandOptions;
import pal.settings.ControlSettings;

/**
 * Created by dhruvr on 25/7/16.
 */
public class AbstractService<T> {

    protected final ControlSettings controlSettings;
    protected final CommandOptions commandOptions;

    protected AbstractService(ControlSettings controlSettings, CommandOptions commandOptions) {
        this.controlSettings = controlSettings;
        this.commandOptions = commandOptions;
    }

}
