package utils.propeties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    public Properties getProperties(String propetiesFileName) {

        InputStream inputStream = null;
        Properties properties = new Properties();
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(propetiesFileName);
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            closeInputStream(inputStream);
        }
        return properties;
    }

    private void closeInputStream(InputStream inputStream) {
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
