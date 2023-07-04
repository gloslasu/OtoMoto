package utils.propeties;

import java.util.Properties;

public class PropertiesSetup {

    private static Properties properties; // This class is thread-safe: multiple threads can share a single Properties object without the need for external synchronization.
    private PropertiesSetup() {
    }

    public static void setProperties(Properties properties) {
        PropertiesSetup.properties = properties;
    }

    public static Properties getProperties() {
        return properties;
    }

    public static String getEnvironment() {
        return  getProperties().getProperty("environment");
    }

    public static String getEnvironmentPropertiesPath() {
        return  getProperties().getProperty("environmentPropertiesPath");
    }

    public static String getBrowser() {
        return  getProperties().getProperty("browser");
    }

    public static String getChromedriverLocation() {
        return  getProperties().getProperty("chromedriverLocation");
    }

    public static String getBaseUrl() {
        return  getProperties().getProperty("baseUrl");
    }

    public static boolean getIsRemote() {
        return Boolean.parseBoolean(PropertiesSetup.getProperties().getProperty("isRemote"));
    }

    public static String getInputPath() {
        return  getProperties().getProperty("inputPath");
    }

    public static String getOutputPath() {
        return  getProperties().getProperty("outputPath");
    }


    public static String getTestDataPath() {
        return  getProperties().getProperty("testDataPath");
    }

    public static String getResultDataPath() {
        return  getProperties().getProperty("resultDataPath");
    }

    public static String getMockupsUrl() {
        return  PropertiesSetup.getProperties().getProperty("mockupsUrl");
    }

    public static String getApiUrl() {
        return  PropertiesSetup.getProperties().getProperty("apiUrl");
    }

    public static String getPassword() {
        return  PropertiesSetup.getProperties().getProperty("password");
    }

    public static String getEmail() {
        return  PropertiesSetup.getProperties().getProperty("email");
    }

    public static String getPhone() {
        return  PropertiesSetup.getProperties().getProperty("phone");
    }

    public static int getImplicitWaitTime() {
        return Integer.parseInt(PropertiesSetup.getProperties().getProperty("implicitWaitTime"));
    }

    public static int getImplicitWaitTimeLong() {
        return Integer.parseInt(PropertiesSetup.getProperties().getProperty("implicitWaitTimeLong"));
    }

    public static int getImplicitWaitAssertionTime() {
        return Integer.parseInt(PropertiesSetup.getProperties().getProperty("implicitWaitAssertionTime"));
    }

    public static long getShortSleep() {
        return Long.parseLong(PropertiesSetup.getProperties().getProperty("shortSleep"));
    }

    public static long getMidSleep() {
        return Long.parseLong(PropertiesSetup.getProperties().getProperty("midSleep"));
    }

    public static long getLongSleep() {
        return Long.parseLong(PropertiesSetup.getProperties().getProperty("longSleep"));
    }

    public static String getIdFront() {
        return getProperties().getProperty("skanDowoduOsobistegoFront");
    }








}











