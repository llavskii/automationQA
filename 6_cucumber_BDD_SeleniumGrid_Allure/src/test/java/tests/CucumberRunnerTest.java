package tests;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import utilities.EventListener;
import utilities.TestListener;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import static com.codeborne.selenide.WebDriverRunner.addListener;

@CucumberOptions(
        strict = true,
        monochrome = true,
        features = "src/test/java/resources/features",
        glue = "tests/stepdefinitions",
        plugin = {"pretty", "html:target/cucumber-html-report",
                "io.qameta.allure.cucumber2jvm.AllureCucumber2Jvm"})
@Listeners(TestListener.class)
public class CucumberRunnerTest extends AbstractTestNGCucumberTests {

    public static Properties config = null;
    public static WebDriver driver = null;
    final static Logger loggerITestListener = Logger.getLogger(ITestListener.class);
    public static LogEntries logEntries;

    @Step
    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {

        addListener(new EventListener());
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        LoadConfigProperty();
        if (config.getProperty("browserType").equals("Chrome")) {
            System.setProperty("webdriver.chrome.driver",
                    System.getProperty("user.dir") + "//src//test//java//resources//drivers/chromedriver.exe");
            driver = new ChromeDriver(caps);
        }
        maximizeWindow();
        implicitWait(30);
        deleteAllCookies();
        setEnv();
    }

    public void LoadConfigProperty() throws IOException {
        config = new Properties();
        FileInputStream ip = new FileInputStream(
                System.getProperty("user.dir") + "//src//test//java//resources//config//config.properties");
        config.load(ip);
    }

    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    public void implicitWait(int time) {
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

    public void pageLoad(int time) {
        driver.manage().timeouts().pageLoadTimeout(time, TimeUnit.SECONDS);
    }

    public void deleteAllCookies() {
        driver.manage().deleteAllCookies();
    }

    public void setEnv() throws Exception {
        LoadConfigProperty();
        String baseUrl = config.getProperty("siteUrl");
        driver.get(baseUrl);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownr(ITestResult result) {

        String messageITest = result.getMethod() + " " + result.isSuccess();
        loggerITestListener.info(messageITest);
    }

    @AfterSuite(alwaysRun = true)
    public void quit() {
        driver.quit();
    }
}