package utils.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import java.util.Arrays;

public class DriverEventListener implements WebDriverEventListener {

    private static Logger logger = LogManager.getLogger(DriverEventListener.class);

    @Override
    public void beforeAlertAccept(WebDriver driver) {
    logger.info("before Alert Accept");
    }

    @Override
    public void afterAlertAccept(WebDriver driver) {
        logger.info("after Alert Accept");
    }

    @Override
    public void afterAlertDismiss(WebDriver driver) {
        logger.info("afterAlertDismiss");
    }

    @Override
    public void beforeAlertDismiss(WebDriver driver) {
        logger.info("beforeAlertDismiss");
    }

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        logger.info("Attempting to navigate to " + url);
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        logger.info("Navigated to " + url);
    }

    @Override
    public void beforeNavigateBack(WebDriver driver) {
        logger.info("Trying to navigate back to URL " + driver.getCurrentUrl());
    }

    @Override
    public void afterNavigateBack(WebDriver driver) {
        logger.info("Navigated back to URL: " + driver.getCurrentUrl());
    }

    @Override
    public void beforeNavigateForward(WebDriver driver) {
        logger.info("Attempting to navigate forward to URL: " + driver.getCurrentUrl());
    }

    @Override
    public void afterNavigateForward(WebDriver driver) {
        logger.info("Navigated forward to URL: " + driver.getCurrentUrl());
    }

    @Override
    public void beforeNavigateRefresh(WebDriver driver) {
        logger.info("Attempting to refresh URL: " + driver.getCurrentUrl());
    }

    @Override
    public void afterNavigateRefresh(WebDriver driver) {
        logger.info("Refreshed page with URL: " + driver.getCurrentUrl());
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        logger.info("Attempting to find element with locator " + by.toString());
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        logger.info("Found element with locator " + by.toString());
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        logger.info("Attempting to click on element " + " [" + element.getTagName() + "]");
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        logger.info("Clicked on element ");
    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        if (keysToSend == null) {
            logger.info("Attempting to enter empty string or clear element: " + element.getTagName());
        } else {
            logger.info("Attempting to type into element: " + element.getTagName() + " text: " + Arrays.toString(keysToSend));
        }
    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        if (keysToSend == null) {
            logger.info("Entered empty string or cleared element: " + element.getTagName());
        } else {
            logger.info("Typed into element: " + element.getTagName() + " text: " + Arrays.toString(keysToSend));
        }
    }

    @Override
    public void beforeScript(String script, WebDriver driver) {
        logger.info("Attempting to execute JS script: " + script);
    }

    @Override
    public void afterScript(String script, WebDriver driver) {
        logger.info("Executed JS script: " + script);
    }

    @Override
    public void beforeSwitchToWindow(String s, WebDriver webDriver) {
        logger.info("Attempting to switch to window " + s);
    }

    @Override
    public void afterSwitchToWindow(String s, WebDriver webDriver) {
        logger.info("Switched to window: " + s);
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        logger.info("Exception: " + throwable.getMessage().split("}") [0] + "}");
    }

    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> outputType) {
        logger.info("Attempting to make screenshot ");
    }

    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> outputType, X x) {
        logger.info("Screenshot made ");
    }

    @Override
    public void beforeGetText(WebElement webElement, WebDriver webDriver) {
        logger.info("Attempting to get text for WebElement " );
    }

    @Override
    public void afterGetText(WebElement webElement, WebDriver webDriver, String s) {
        logger.info("Text of WebElement " + s);
    }
}
