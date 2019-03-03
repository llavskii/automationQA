package blazedemo;

import org.testng.annotations.Test;

import static blazedemo.site.JDIExampleSite.*;

public class BlazeDemoTest extends TestInit {

    @Test
    public void buyFlightTest() {
        homePage.open();
        homePage.checkOpened();
        submitOnHomePage();
        reservePage.checkOpened();
        chooseFlight();
        purchasePage.checkOpened();
        fillAllFormsAndSubmit();
        confirmationPage.checkOpened();
    }
}
