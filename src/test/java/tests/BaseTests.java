/*    This class contains methods:
   - @BeforeAll - initialization WebDriver
   - @BeforeEach - cleaning of cookies files
   - @AfterAll - closing WebDriver   */

package tests;

import driver.DriverSetup;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.*;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import utils.CommonUtil;
import utils.ReportUtil;
import utils.propeties.PropertiesLoader;
import utils.propeties.PropertiesSetup;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static utils.propeties.PropertiesSetup.getResultDataPath;

public class BaseTests extends CommonUtil implements IHookable {

    SoftAssert softAssert = new SoftAssert();

    @Override
    public void run(IHookCallBack callBack, ITestResult testResult) {
        callBack.runTestMethod(testResult);
        if(testResult.getThrowable() != null) {
            ReportUtil.takeScreenshot(testResult.getMethod().getMethodName());
        }
        ITestContext iTestContext = Reporter.getCurrentTestResult().getTestContext();
        if (iTestContext.getAttribute("processId_" + Thread.currentThread().getId()) != null) {
            ReportUtil.putProcessIdToAllureReport(iTestContext.getAttribute("processId_" + Thread.currentThread().getId()).toString());
            }
        }

@BeforeSuite(groups = {"full_regression", "regression_limited", "short_check", "fiveHundred"})
    public void beforeSuite() {
        PropertiesLoader propertiesLoader = new PropertiesLoader();
        Properties propertiesFromFile = propertiesLoader.getProperties("properties"); // powinno wstazywać na plik properties
        PropertiesSetup.setProperties(propertiesFromFile);
        clearTextFile(getResultDataPath());
    }

@AfterSuite(groups = {"full_regression", "regression_limited", "short_check", "fivehundred"})
    public void afterSuite() {
        saveEnvironmentPropetiesFile();
    }

@BeforeTest(groups = {"aftertest_standard", "aftertest_limited"})
    public void beforeTest() {
        PropertiesLoader propertiesLoader = new PropertiesLoader();
        Properties propertiesFromFile = propertiesLoader.getProperties("properties");
        PropertiesSetup.setProperties(propertiesFromFile);
    }

@BeforeMethod(groups = {"aftertest_standard", "aftertest_limited"})
    public void beforeAfterTestMethod() {
        Thread.currentThread().setName("Thread" + Thread.currentThread().getId());
    }

@BeforeMethod(groups = {"full_regression", "regression_limited", "short_check", "fivehundred"})
    public void beforeMethod() {
        // setDriver i getDriver jest jak testuje się na przeglądarce
//    DriverSetup.setDriver(true); // false - tryb graficzny. true - tryb headless
    DriverSetup.setDriver(false);
    DriverSetup.getDriver();
  }


  @AfterMethod(groups = {"full_regression", "regression_limited", "short_check", "fivehundred"})
    public void afterMethod() {
//        DriverSetup.closeDriver();
  }

//  @AfterTest
//    public void afterTest(){
//        softAssert.assertAll();
//  }

}



