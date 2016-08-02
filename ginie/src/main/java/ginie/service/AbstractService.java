package ginie.service;

import ginie.boot.CommandOptions;
import ginie.settings.GinieSettings;

/**
 * Created by dhruvr on 25/7/16.
 */
public class AbstractService<T> {

    protected final GinieSettings ginieSettings;
    protected final CommandOptions commandOptions;

    protected AbstractService(GinieSettings ginieSettings, CommandOptions commandOptions) {
        this.ginieSettings = ginieSettings;
        this.commandOptions = commandOptions;
    }

}
