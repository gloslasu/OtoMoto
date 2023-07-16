package pages;

import driver.DriverSetup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CommonUtil;

public abstract class BasePage extends CommonUtil {

    public BasePage() {
        PageFactory.initElements(DriverSetup.getDriver(), this);
    }

}
