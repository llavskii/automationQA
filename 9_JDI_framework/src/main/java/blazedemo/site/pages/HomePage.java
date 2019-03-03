package blazedemo.site.pages;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.complex.Dropdown;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import org.openqa.selenium.support.FindBy;

@JPage(url = "/", title = "BlazeDemo")
public class HomePage extends WebPage {
    @FindBy(xpath = "//form/select[1]")
    public Dropdown firstCityForm;

    @FindBy(xpath = "//form/select[2]")
    public Dropdown secondCityForm;

    @FindBy(css = ".btn-primary")
    public Button submitFindButton;
}
