package sputnik;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

    @RunWith(CucumberWithSerenity.class)
    @CucumberOptions(features = "src/test/java/resources/search_in_sputnik.feature")
    public class SearchTest {
    }
