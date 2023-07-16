package utils;

import driver.DriverSetup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestContext;
import org.testng.Reporter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.fail;
import static utils.WaitUtil.waitForPageLoad;
import static utils.WaitUtil.waitUntilElementIsVisible;
import static utils.propeties.PropertiesSetup.*;

public class CommonUtil {

    protected Logger logger = LogManager.getLogger(this.getClass().getName());
    protected static Logger staticLogger = LogManager.getLogger(CommonUtil.class);

    public void clickElement(WebElement element) {
        customSleep(50);
        waitForPageLoad();
        try {
            element.click();
        } catch (ElementClickInterceptedException | StaleElementReferenceException e) {
            waitForPageLoad();
            JavascriptExecutor jse = (JavascriptExecutor) DriverSetup.getDriver();
            (jse).executeScript("arguments[0].scrollIntoView(true);", element);
            (jse).executeScript("arguments[0].click();", element);
        } catch (NoSuchElementException e) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            String methodName = stackTrace[2].getMethodName();
            fail("no such element in method: " + methodName);
        }

    }

    public void clickElementJS(WebElement element) {
        customSleep(50);
        waitForPageLoad();
        try {
            logger.info("Attempting to click on element: " + element.getTagName() + " [" + element.getText() + "]");
            JavascriptExecutor jse = (JavascriptExecutor) DriverSetup.getDriver();
            (jse).executeScript("arguments[0].click();", element);
        } catch (NoSuchElementException e) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            String methodName = stackTrace[2].getMethodName();
            fail("No such element in method: " + methodName);
        } catch (ElementClickInterceptedException | StaleElementReferenceException e) {
            waitForPageLoad();
            logger.info("Second try (exception). Attempting to click on element: " + element.getTagName() + " [" + element.getText() + "]");
            JavascriptExecutor jse = (JavascriptExecutor) DriverSetup.getDriver();
            (jse).executeScript("arguments[0].scrollIntoView(true);", element);
            (jse).executeScript("arguments[0].click();", element);
        }
    }


    public void clickElementActions(WebElement element, int x, int y) {
        customSleep(50);
        waitForPageLoad();
        try {
            Actions action = new Actions(DriverSetup.getDriver());
            action.moveToElement(element, x, y).click().build().perform();
        } catch (NoSuchElementException e) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            String methodName = stackTrace[2].getMethodName();
            fail("No such element in method: " + methodName);
        } catch (ElementClickInterceptedException | StaleElementReferenceException e) {
            waitForPageLoad();
            JavascriptExecutor jse = (JavascriptExecutor) DriverSetup.getDriver();
            (jse).executeScript("arguments[0].scrollIntoView(true);", element);
            (jse).executeScript("arguments[0].click();", element);
        }
    }

    public void sendTextToElement(WebElement element, String textToSend) {
        customSleep(50);
        waitForPageLoad();
        try {
            element.clear();
            element.sendKeys(textToSend);
        } catch (NoSuchElementException e) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            String methodName = stackTrace[2].getMethodName();
            fail("No such element in method: " + methodName);
        }
    }


    protected String readTextFromElement(WebElement element) {
        waitUntilElementIsVisible(element);
        return element.getText();
    }

    protected String readValueFromElement(WebElement element) {
        waitUntilElementIsVisible(element);
        return element.getAttribute("value");
    }

    protected void switchToIframe(WebElement element) {
        DriverSetup.getDriver().switchTo().frame(element);
    }

    protected void switchToDefaultContent() {
        DriverSetup.getDriver().switchTo().defaultContent();
    }

    protected void sendFile(WebElement element, String filePath) {
        File file = new File(filePath);
        element.sendKeys(file.getAbsolutePath());
    }

    protected void scrollElement(WebElement element) {
        waitForPageLoad();
        customSleep(100);
        JavascriptExecutor jse = (JavascriptExecutor) DriverSetup.getDriver();
        (jse).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void customSleep(long sleep) {
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            logger.info("Interrupted Exception during Thread.sleep!");
        }
    }

    protected boolean booleanListAssertion(List<WebElement> elements) {
        WebDriver driver = DriverSetup.getDriver();
        driver.manage().timeouts().implicitlyWait(getImplicitWaitAssertionTime(), TimeUnit.SECONDS);
        waitForPageLoad();
        boolean isPresent = elements.size() != 0;
        if (isPresent) {
            if (elements.get(0).getText().equals("")) {
                logger.info("The checked element is present: " + elements.get(0).getTagName());
            } else  {
                logger.info("The checked element is present: " + elements.get(0).getText());
            }
        } else {
            logger.info("The checked element is not present: ");
        }
        driver.manage().timeouts().implicitlyWait(getImplicitWaitTime(), TimeUnit.SECONDS);
        return isPresent;
    }


    protected boolean booleanListAssertionMilliseconds(List<WebElement> elements, long time) {
        WebDriver driver = DriverSetup.getDriver();
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.MILLISECONDS);
        waitForPageLoad();
        boolean isPresent = elements.size() != 0;
        if (isPresent) {
            if (elements.get(0).getText().equals("")) {
                logger.info("The checked element is present: " + elements.get(0).getTagName());
            } else  {
                logger.info("The checked element is present: " + elements.get(0).getText());
            }
        } else {
            logger.info("The checked element is not present: ");
        }
        driver.manage().timeouts().implicitlyWait(getImplicitWaitTime(), TimeUnit.SECONDS);
        return isPresent;
    }

    protected boolean isElementPresent(List<WebElement> elements, String text) {
        WebDriver driver = DriverSetup.getDriver();
        driver.manage().timeouts().implicitlyWait(200, TimeUnit.MILLISECONDS);
        waitForPageLoad();
        boolean isPresent = !elements.isEmpty();
        logger.info("Size: " + elements.size() + " " + text);
        if (isPresent) {
            logger.info("The checked element is present: " + elements.get(0).getText());
        } else {
            logger.info("The checked element is not present");
        }
        driver.manage().timeouts().implicitlyWait(getImplicitWaitTime(), TimeUnit.SECONDS);
        return isPresent;
    }

    protected void implicitWaitTimeChange(long time) {
        WebDriver driver = DriverSetup.getDriver();
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

    public String saveProcessIdToLogFile(String login) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String methodName = stackTrace[3].getMethodName();
        String[] html = DriverSetup.getDriver().getCurrentUrl().trim().split("/");
        String processId = html[html.length -1];
        if (processId.length() != 36) {
            processId = null;
        } else {
            try {
                 FileWriter fileWriter = new FileWriter(getProcessIdLogPath(), true);
                 BufferedWriter bw = new BufferedWriter(fileWriter);
                 PrintWriter pw = new PrintWriter(bw);
                 String time = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
                 if (methodName.length() < 16)
                    pw.println(processId + "\t\t\t\t" + time + "   " + methodName + "\t\t\t\t" + login);
                 else if (methodName.length() < 24)
                    pw.println(processId + "\t\t\t\t" + time + "   " + methodName + "\t\t\t" + login);
                 else
                    pw.println(processId + "\t\t\t\t" + time + "   " + methodName + "\t\t" + login);
                 logger.info("Id added to processId.log: " + processId);
        } catch (IOException e) {
            logger.info("IO exception. \n", e);
            }
        }
        return processId;
    }

    public void savePeselToProcessIdLogFile(String login, String pesel, int a) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String methodName = stackTrace[3].getMethodName();
            try {
                FileWriter fileWriter = new FileWriter(getProcessIdLogPath(), true);
                BufferedWriter bw = new BufferedWriter(fileWriter);
                PrintWriter pw = new PrintWriter(bw);
                String time = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
                if (methodName.length() < 16)
                    pw.println(pesel + "\t\t\t\t" + time + "   " + methodName + "\t\t\t\t" + login);
                else if (methodName.length() < 24)
                    pw.println(pesel + "\t\t\t\t" + time + "   " + methodName + "\t\t\t" + login);
                else
                    pw.println(pesel + "\t\t\t\t" + time + "   " + methodName + "\t\t" + login);
            } catch (IOException e) {
                logger.info("IO exception. \n", e);
            }
        }


        protected String getProcessId() {
            String[] html = DriverSetup.getDriver().getCurrentUrl().trim().split("/");
            System.out.println(html.length);
            int i = 0;
            while (html.length < 7 && i < 20) {
                customSleep(100);
                html = DriverSetup.getDriver().getCurrentUrl().trim().split("/");
                i++;
            }
            logger.info("Process id: " + html[html.length - 1]);
            return html[html.length - 1];
        }

        protected void saveTestDataResult(String methodName, String idNumber) {
            try
                 (FileWriter fw = new FileWriter(Paths.get(getResultDataPath()).toString(), true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter pw = new PrintWriter(bw))      {
                    pw.println(methodName + ";" + idNumber);
                    logger.info("Id number added to resultData.csv");
                } catch (IOException e) {
                    logger.info("IO exception.\n", e);
                    }
            }

//        protected void saveEqToTestContext(String eqNumber) {
//            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
//            String methodName = stackTrace[3].getMethodName();
//            ITestContext i = Reporter.getCurrentTestResult().getTestContext();
//            i.setAttribute("eqNum", Thread.currentThread().getId(), eqNumber;
//        }

        protected void clearTextFile(String path) {
            try (PrintWriter clear = new PrintWriter(path)) {
                    clear.println("");
                } catch (IOException e) {
                    logger.error("IO exception. \n", e);
                }
            }

         protected static void saveStringtoFile(String path, String result) {
            try (
                 FileWriter fw = new FileWriter(path, true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter pw = new PrintWriter(bw) ) {
                pw.println(result);
            } catch (IOException e) {
                staticLogger.error("IO exception.\n", e);
            }
         }

         protected static void saveEnvironmentPropetiesFile() {
            String path = Paths.get(getEnvironmentPropertiesPath()).toString();
            saveStringtoFile(path, "Environment=" + getEnvironment());
            saveStringtoFile(path, "Environment=" + getBrowser());
         }

         protected List<String> getDataFromFileToList(String sourcePath, String file) {
            List<String> data = new ArrayList<>();
            if (!file.isEmpty()) sourcePath = sourcePath + file + ".csv";
            try {
                BufferedReader sc = new BufferedReader(new FileReader(sourcePath));
                sc.lines().forEach(data::add);
                data = data.stream().filter(x -> x.length() > 0).collect(Collectors.toList());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return data;
         }

         protected static void copyFile(File source, File dest) throws IOException {
             Files.copy(source.toPath(), dest.toPath());
         }

}
















