package pal.control;

import ch.qos.logback.core.joran.spi.JoranException;
import com.beust.jcommander.JCommander;
import pal.logger.LoggerConfigurator;
import pal.settings.ControlSettings;

import static pal.settings.ImmutableControlSettings.settingsBuilder;

/**
 * Created by dhruvr on 26/6/16.
 */
public class Main {

    public static void main(String[] args) throws JoranException {

        final CommandOptions commandOptions = new CommandOptions();
        final JCommander jCommander = new JCommander(commandOptions, args);

        //set up logger
        LoggerConfigurator loggerConfigurator = new LoggerConfigurator(commandOptions.logConfFile);
        loggerConfigurator.configureLogger();

        //load controlSettings
        ControlSettings controlSettings = settingsBuilder().loadFromFile(commandOptions.confFile).build();

        //set up guice
        BootStrap bootStrap = new BootStrap(controlSettings, commandOptions);
        bootStrap.start();

    }
}
