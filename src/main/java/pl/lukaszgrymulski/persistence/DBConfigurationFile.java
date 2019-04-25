package pl.lukaszgrymulski.persistence;

import java.io.File;

public class DBConfigurationFile {

    private static String DB_CONFIG_FILE_PATH = null;

    public static String getDbConfigFilePath() {
        if (DB_CONFIG_FILE_PATH==null) {
            String path = System.getProperty("user.dir");
            String absPath = path + "/config/dbconfig.properties";
            File file = new File(absPath);
            if (file.exists()) {
                DB_CONFIG_FILE_PATH = absPath;
            } else {
                DB_CONFIG_FILE_PATH = "src/main/resources/dbconfig.properties";
            }
        }

        return DB_CONFIG_FILE_PATH;
    }
}
