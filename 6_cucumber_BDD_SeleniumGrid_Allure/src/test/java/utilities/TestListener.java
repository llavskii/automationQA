package utilities;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.CucumberRunnerTest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static tests.CucumberRunnerTest.driver;

public class TestListener implements ITestListener {
    private Logger logger = Logger.getLogger(TestListener.class);
    private static String userDirTarget = System.getProperty("user.dir") + "//target";

    public static void takeScreenshotResult(ITestResult result) {
        WebDriver driver = CucumberRunnerTest.driver;
        File imageFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String imageName = String.format("%1$s Result-%2$s %3$s.png", result.getMethod().getMethodName(),
                result.isSuccess(), currentDateTime());
        File imageFileName = new File(userDirTarget.concat("//screenshots//").concat(imageName));
        if (!imageFileName.exists()) {
            try {
                imageFileName.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileUtils.copyFile(imageFile, imageFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String currentDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        Calendar cal = Calendar.getInstance();
        String cal1 = dateFormat.format(cal.getTime());
        return cal1;
    }

    public static String getResultName(ITestResult result) {
        return result.getName() + " " + result.isSuccess() + "\n";
    }

    public void onTestStart(ITestResult result) {
        logger.info("On test start: " + getResultName(result));
        takeScreenshotResult(result);
    }

    public void onTestSuccess(ITestResult result) {
        logger.info("On test success: " + getResultName(result));
        takeScreenshotResult(result);
    }

    public void onTestFailure(ITestResult result) {
        logger.error("On test failure: " + getResultName(result));
        takeScreenshotResult(result);
    }

    public void onTestSkipped(ITestResult result) {
        logger.info("On test skipped: " + getResultName(result));
        takeScreenshotResult(result);
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        logger.error("On test failed but within success percentage: " + getResultName(result));
        takeScreenshotResult(result);
    }

    public void onStart(ITestContext context) {
        logger.info("On start: " + context.toString() + "\n");
    }

    public void onFinish(ITestContext context) {
        logger.info("On finish: " + context.toString() + "\n");
        LogEntries logEntries = driver.manage().logs().get(LogType.PERFORMANCE);
        String pathDumpFile = userDirTarget.concat("//trafficDump//dump.log");
        File driverLog = new File(pathDumpFile);
        if (!driverLog.exists()) {
            driverLog.getParentFile().mkdirs();
            try {
                driverLog.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileWriter out = new FileWriter(driverLog, true);
            out.write(String.format("!!!================   Start Log Date %s     ===============!!!\n\n\n", currentDateTime()));
            for (LogEntry logEntry : logEntries.getAll()) {
                out.write(logEntry.toString() + "\n");
            }
            out.write(String.format("!!!================^^^Finish=Log Date %s ^^^===============!!!\n\n\n", currentDateTime()));
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
