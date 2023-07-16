package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import utils.listeners.DriverEventListener;
import utils.propeties.PropertiesSetup;

import java.util.concurrent.TimeUnit;

import static utils.propeties.PropertiesSetup.getImplicitWaitTime;


public class DriverSetup {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>(); // Służy do przechowywania instancji WebDrivera. Każde wywołanie testu automatycznego jest uruchamiane w osobnym wątku. Dzięki ThreadLocal<WebDriver> możemy utrzymać oddzielną instancję WebDrivera dla każdego wątku, co zapewnia izolację i uniknięcie konfliktów między testami. Główne zadanie ThreadLocal<WebDriver> polega na tym, że przechowuje osobną instancję WebDrivera dla każdego wątku. W momencie gdy test jest uruchamiany w danym wątku, możemy uzyskać dostęp do instancji WebDrivera z ThreadLocal<WebDriver> i użyć go do interakcji z przeglądarką. Dzięki temu, każdy wątek testowy ma własną instancję WebDrivera, a zmiany dokonywane w jednym teście nie mają wpływu na inne testy. To zapewnia czystość testów, izolację i poprawność ich wykonywania.
    private boolean isRemote;
    private DriverSetup(boolean isRemote) {
        this.isRemote = isRemote;
    }

    private WebDriver getBrowser(boolean headless){
        if (isRemote) {
            WebDriver driver;
            System.setProperty("webdriver.chromedriver", PropertiesSetup.getChromedriverRemoteLocation());
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-setuid-sandbox");
            options.addArguments("--no-sandbox");
            options.addArguments("--headless");
            options.addArguments("--window-size=1920x1080");
//            options.addArguments("--single-process");
            options.setExperimentalOption("useAutomationExtension", false);
            driver = new ChromeDriver(options);
            driver.manage().deleteAllCookies();
//            driver.manage().window().maximize();
            return driver;
        } else {
            WebDriver driver;
            System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true"); // Jeśli ustawisz tę właściwość na "true", to wyłączysz wyjście diagnostyczne (logi) ChromeDrivera na konsolę, co oznacza, że nie będą one widoczne podczas uruchamiania testów. Jest to przydatne, gdy chcesz zredukować ilość wyjścia diagnostycznego i utrzymać czystość logów podczas wykonywania automatycznych testów za pomocą ChromeDrivera.
            System.setProperty("webdriver.chromedriver", PropertiesSetup.getChromedriverLocation());
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-setuid-sandbox");
            options.addArguments("--no-sandbox");
            if (headless) {
                options.addArguments("--headless");
                options.addArguments("--window-size=1920x1080");
            }
            options.setExperimentalOption("useAutomationExtension", false);
            driver = new ChromeDriver(options);
            driver.manage().deleteAllCookies();
            return driver;
        }
    }

//    private WebDriver getBrowser(){
//            WebDriver driver;
//            System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true"); // Jeśli ustawisz tę właściwość na "true", to wyłączysz wyjście diagnostyczne (logi) ChromeDrivera na konsolę, co oznacza, że nie będą one widoczne podczas uruchamiania testów. Jest to przydatne, gdy chcesz zredukować ilość wyjścia diagnostycznego i utrzymać czystość logów podczas wykonywania automatycznych testów za pomocą ChromeDrivera.
//            System.setProperty("webdriver.chromedriver", PropertiesSetup.getChromedriverLocation()); // -----------------
////            System.setProperty("webdriver.chromedriver", "D:\\projekty\\chromedriver\\chromedriver.exe");
//            ChromeOptions options = new ChromeOptions();
//            options.addArguments("--disable-setuid-sandbox");
//            options.addArguments("--no-sandbox");
//            options.setExperimentalOption("useAutomationExtension", false);
//            driver = new ChromeDriver(options);
//            driver.manage().deleteAllCookies();
//            return driver;
//        }


//    private WebDriver getRemoteDriver(DesiredCapabilities capabilities) {
//        RemoteWebDriver remoteWebDriver;
//        try {
//            remoteWebDriver = new RemoteWebDriver(new URL(PropertiesSetup.getGridUrl(), capabilities));
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//            throw new RuntimeException();
//        }
//        return remoteWebDriver;
//    }

//    public static void setDriver() {
//        WebDriver driver;
//        driver = new DriverSetup(PropertiesSetup.getIsRemote()).getBrowser();
//        driver = registerWebDriverEventListener(driver);
//        driver.manage().timeouts().implicitlyWait(getImplicitWaitTime(), TimeUnit.SECONDS);
//        driverThreadLocal.set(driver);
//        Thread.currentThread().setName("Thread " + Thread.currentThread().getId());
//    }

    public static void setDriver(boolean headless) {
        WebDriver driver;
        driver = new DriverSetup(PropertiesSetup.getIsRemote()).getBrowser(headless);
        driver = registerWebDriverEventListener(driver);
        driver.manage().timeouts().implicitlyWait(getImplicitWaitTime(), TimeUnit.SECONDS);
        driverThreadLocal.set(driver);
        Thread.currentThread().setName("Thread " + Thread.currentThread().getId());
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }


    public static void closeDriver() {
        driverThreadLocal.get().close();
//        driverThreadLocal.get().quit(); // firefox
        driverThreadLocal.remove();
    }

    private synchronized static WebDriver registerWebDriverEventListener(WebDriver driver) {
        EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
        DriverEventListener driverEventListener = new DriverEventListener();
        return eventFiringWebDriver.register(driverEventListener);
    }




}
