package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    WebDriver driver; // because our driver is here so every other class inherits this driver
    WebDriverWait wait;

    static final String BASE_URL = "https://www.otomoto.pl";
    public BasePage(WebDriver driverIn, WebDriverWait waitIn){ // we need create constructor to give 'static WebDriver driver;' from src/tests/java/tests/BaseTests
        this.driver = driverIn; // driverIn is driver with comes from our method executed.
        this.wait = waitIn;
        // we must say in constructor that intelij should initialize and find elements @FindBy
        PageFactory.initElements(driver, this);
    }

}
