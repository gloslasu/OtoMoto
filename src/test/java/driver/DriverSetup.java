package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.propeties.PropertiesSetup;

import static java.awt.GraphicsEnvironment.headless;

public class DriverSetup {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>(); // Służy do przechowywania instancji WebDrivera. Każde wywołanie testu automatycznego jest uruchamiane w osobnym wątku. Dzięki ThreadLocal<WebDriver> możemy utrzymać oddzielną instancję WebDrivera dla każdego wątku, co zapewnia izolację i uniknięcie konfliktów między testami. Główne zadanie ThreadLocal<WebDriver> polega na tym, że przechowuje osobną instancję WebDrivera dla każdego wątku. W momencie gdy test jest uruchamiany w danym wątku, możemy uzyskać dostęp do instancji WebDrivera z ThreadLocal<WebDriver> i użyć go do interakcji z przeglądarką. Dzięki temu, każdy wątek testowy ma własną instancję WebDrivera, a zmiany dokonywane w jednym teście nie mają wpływu na inne testy. To zapewnia czystość testów, izolację i poprawność ich wykonywania.
    private boolean isRemote;
    private DriverSetup(boolean isRemote) {
        this.isRemote = isRemote;
    }

    private WebDriver getBrowswer(boolean headless){
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
            System.setProperty("webdriver.chromedriver", PropertiesSetup.getChromedriverRemoteLocation());
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

//    private WebDriver getRemoteDriver(){to fill in the body} // not use yet

    public static void setDriver() {

    }

    public static void closeDriver() {
        driverThreadLocal.get().close();
//        driverThreadLocal.get().quit(); // firefox
        driverThreadLocal.remove();
    }














}
