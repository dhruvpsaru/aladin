package ginie.boot;

import ch.qos.logback.core.joran.spi.JoranException;
import com.beust.jcommander.JCommander;
import ginie.logger.LoggerConfigurator;
import ginie.settings.GinieSettings;

import static ginie.settings.ImmutableGinieSettings.settingsBuilder;

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

        //load ginieSettings
        GinieSettings ginieSettings = settingsBuilder().loadFromFile(commandOptions.confFile).build();

        //set up guice
        BootStrap bootStrap = new BootStrap(ginieSettings, commandOptions);
        bootStrap.start();

    }
}
