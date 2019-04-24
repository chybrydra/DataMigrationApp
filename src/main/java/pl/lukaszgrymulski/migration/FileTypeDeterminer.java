package pl.lukaszgrymulski.migration;

import java.io.File;

public class FileTypeDeterminer {

    public static String determineFileType(File file) {
        String path = file.getAbsolutePath();
        int indexOfDot = path.lastIndexOf(".");
        String fileType = path.substring(indexOfDot+1);
        return fileType;
    }

}
