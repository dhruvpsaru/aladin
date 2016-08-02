package pal.logger;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by dhruvr on 27/6/16.
 */
public class LoggerConfigurator {

    private final File logConfFile;

    public LoggerConfigurator(File logConfFile) {
        this.logConfFile = logConfFile;
    }


    public void configureLogger() throws JoranException {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        JoranConfigurator joranConfigurator = new JoranConfigurator();
        joranConfigurator.setContext(loggerContext);
        loggerContext.reset();
        joranConfigurator.doConfigure(logConfFile);
    }

}
