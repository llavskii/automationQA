package blazedemo.site;

import blazedemo.entities.User;
import blazedemo.site.pages.ConfirmationPage;
import blazedemo.site.pages.HomePage;
import blazedemo.site.pages.PurchasePage;
import blazedemo.site.pages.ReservePage;
import blazedemo.site.sections.PurchaseForms;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JSite;
import ru.yandex.qatools.allure.annotations.Step;

@JSite("http://blazedemo.com/")
public class JDIExampleSite extends WebSite {
    public static HomePage homePage;
    public static ReservePage reservePage;
    public static PurchasePage purchasePage;
    public static PurchaseForms purchaseForms;
    public static ConfirmationPage confirmationPage;

    @Step
    public static void submitOnHomePage() {
        homePage.submitFindButton.click();
    }

    @Step
    public static void chooseFlight() {
        reservePage.chooseFlightButton.click();
    }

    @Step
    public static void fillAllFormsAndSubmit() {
        purchaseForms.fillAllForms(new User());
        purchasePage.submitButton.click();
    }
}
