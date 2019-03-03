package sputnik;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

public class ResultsPage extends PageObject {

    @FindBy(css = ".js-result-list")
    WebElementFacade results;

    public boolean resultsAreDisplayed() {
        results.waitUntilVisible();
        return results.isDisplayed();
    }

    public boolean titleIsDisplayed() {
        return ($("//*[@id=\"js-search-input\"]").getValue()).equals("Виктор Пелевин");
    }
}
