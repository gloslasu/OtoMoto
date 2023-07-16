package utils;


import driver.DriverSetup;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class ReportUtil {

//    private static Logger logger = LogManager.getLogger(ReportUtil.class);
    private static Logger logger = LogManager.getLogger(ReportUtil.class);

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] takeScreenshot(String methodName) {
        byte[] srcReturn = null;
        try {
            DriverSetup.getDriver().manage().window().maximize();
            TakesScreenshot src =((TakesScreenshot) DriverSetup.getDriver());
            srcReturn = src.getScreenshotAs(OutputType.BYTES);
            File srcFile = src.getScreenshotAs(OutputType.FILE);
            try {
                String time = new SimpleDateFormat("HH-mm-ss").format(Calendar.getInstance().getTime());
                String date = new SimpleDateFormat("yy-MM-dd").format(Calendar.getInstance().getTime());
                String screenshotName = (date + "_" + methodName + "_" + time + ".png");
                FileHandler.copy(srcFile, new File("testOutput//" +screenshotName));
                logger.info("Screenshot: " + screenshotName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (NullPointerException e) {
            logger.info("No driver. No screenshot on test failure.");
        }
        return srcReturn;
    }

    @Attachment(value = "Process Id")
    public static String putProcessIdToAllureReport(String processId) {
        return processId;
    }

}
