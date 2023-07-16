package utils;


import driver.DriverSetup;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {

    private static WebDriverWait getWebDriverWait() {
        return new WebDriverWait(DriverSetup.getDriver(), 5);
    }

    public static void waitUntilElementIsVisible (WebElement element) {
        WebDriverWait webDriverWait = getWebDriverWait();
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitUntilElementIsClicable (WebElement element) {
        WebDriverWait webDriverWait = getWebDriverWait();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    static void waitForPageLoad() {
        WebDriverWait webDriverWait = getWebDriverWait();
        webDriverWait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"compleate\";"));
    }

}
