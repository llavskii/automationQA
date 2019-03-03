package sputnik;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("https://www.sputnik.ru/")
public class SearchPage extends PageObject {

    @FindBy(xpath = "//*[@id=\"js-search-input\"]")
    WebElementFacade searchInput;

    @FindBy(xpath = "//div[3]/button")
    WebElementFacade searchButton;

    public void search(String text) {
        searchInput.type(text);
        searchButton.click();
    }

    public void inputToSearchField(String text) {
        searchInput.type(text);
    }

    public void clickOnInput() {
        searchInput.click();
    }

    public void clickOnSubmitButton() {
        searchButton.click();
    }
}

