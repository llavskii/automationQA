package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

public class SetupTest {

    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    protected static int timeOut = 10;
    protected Actions action;

    @Parameters({"browser", "headlessOption"})
    @BeforeClass
    public void beforeClass(String browser, String headlessOption) {
        //инициализация Вебдрайвера с учетом параметров
        if (browser.equalsIgnoreCase("edge")) {
            driver = new InternetExplorerDriver();
        } else if (browser.equalsIgnoreCase("chrome") & headlessOption.equals("off")) {
            driver = new ChromeDriver();
        } else if ((browser.equalsIgnoreCase("chrome") & headlessOption.equals("on"))) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
        } else throw new WebDriverException();
        driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
        action = new Actions(driver);
        webDriverWait = new WebDriverWait(driver, timeOut);
        driver.manage().window().maximize();
    }

    public static int getTimeOut() {
        return timeOut;
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}

