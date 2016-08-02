package pal.control;

import com.beust.jcommander.IValueValidator;
import com.beust.jcommander.ParameterException;

import java.io.File;

/**
 * Created by dhruvr on 26/6/16.
 */
public class FileExistsValidator implements IValueValidator<File> {

    public void validate(String name, File value) throws ParameterException {
        if(!value.exists()){
            throw new ParameterException(String.format("File %s doesn't exist", value));
        }
    }
}
