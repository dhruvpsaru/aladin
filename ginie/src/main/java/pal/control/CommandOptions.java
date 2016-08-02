package pal.control;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.converters.FileConverter;

import java.io.File;

/**
 * Created by dhruvr on 26/6/16.
 */

@Parameters(commandNames = {"indexer", "Creates indexes"})
public class CommandOptions {

    @Parameter(names = {"-c", "--conf-file"}, required = true, converter = FileConverter.class, validateValueWith = FileExistsValidator.class)
    public File confFile;

    @Parameter(names = {"-l", "--log-conf-file"}, required = true, converter = FileConverter.class, validateValueWith = FileExistsValidator.class)
    public File logConfFile;

}
