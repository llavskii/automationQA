package sputnik;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;

public class SearchScenarioSteps {

    SearchPage searchPage;
    ResultsPage resultPage;

    @Given("user (?:opens|opened) the site")
    public void givenUserOpensTheSite() {
        searchPage.open();
    }

    @Given("user clicks input")
    public void givenUserClicksOnInput() {
        searchPage.clickOnInput();
    }

    @When("user search for '(.*)'")
    public void whenUserSearchesFor(String text) {
        searchPage.inputToSearchField(text);
    }

    @When("user clicks Search")
    public void whenUserClicksOnSearch() {
        searchPage.clickOnSubmitButton();
    }

    @Then("user sees list of results")
    public void thenUserSeesListOfResults() {
        assertTrue(resultPage.resultsAreDisplayed());
    }

    @And("'(.*)' should be contained")
    public void thenPersonShouldBeDisplayed(String personName) {
        assertTrue(resultPage.titleIsDisplayed());
    }
}
