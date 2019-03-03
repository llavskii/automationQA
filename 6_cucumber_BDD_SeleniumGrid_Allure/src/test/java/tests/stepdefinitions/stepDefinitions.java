package tests.stepdefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.qameta.allure.Allure;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.MainPage;
import pageObject.PageObject;
import pageObject.TShirtsPage;
import tests.CucumberRunnerTest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.testng.Assert.assertEquals;


public class stepDefinitions extends CucumberRunnerTest {
    protected PageObject pageObject;
    protected Actions action = new Actions(driver);

    @Given("^I am on main page of automationpractice$")
    public void iAmOnMainPageOfAutomationpractice() {
        pageObject = new MainPage(driver);
        action.moveByOffset(0, 0).perform();
        assertEquals(driver.getTitle(), "My Store");

    }

    @When("^I move mouse to button Women and check it\\'s dimension$")
    public void iMoveMouseToButtonWomen() {
        MainPage mainPage = (MainPage) pageObject;
        Dimension dimensionBefore = pageObject.getDimensionOfWE(mainPage.getWomenButton());
        action.moveToElement(mainPage.getWomenButton()).moveToElement(mainPage.getWomenButton()).perform();
        Dimension dimensionAfter = mainPage.getDimensionOfWE(mainPage.getWomenButton());
        assertEquals(dimensionBefore, dimensionBefore);
        Allure.addAttachment("I move mouse to button Women and check it's dimension", mainPage.toString());
    }

    @And("^I move and click mouse to T-Shirts in list under button Women$")
    public void iMoveAndClickMouseToTShirtsInListUnderButtonWomen() {
        MainPage mainPage = (MainPage) pageObject;
        pageObject = mainPage.waitForVisibilityOfElementAndGoToPage(mainPage.getShirtsUnderWomenButton());
    }

    @Then("^I see \"([^\"]*)\" for women$")
    public void iSeeTShirtsForWomen(String wear) {
        TShirtsPage tShirtsPage = (TShirtsPage) pageObject;
        new WebDriverWait(driver, 5).until(tShirtsPage.visibilityOfElement(tShirtsPage.getPageBanner()));
        assertEquals(String.format("%1$s - My Store", wear), driver.getTitle());
        byte[] array = new byte[0];
        try {
            array = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "//target" + "//trafficDump//dump.log"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String txt = null;
        try {
            txt = new String(array, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Allure.addAttachment("Test title", "text/html", txt);
    }
}
