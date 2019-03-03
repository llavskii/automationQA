package blazedemo.site.pages;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import org.openqa.selenium.support.FindBy;

@JPage(url = "/reserve.php", title = "BlazeDemo - reserve")
public class ReservePage extends WebPage {

    @FindBy(xpath = "//tr[1]/td[1]/input")
    public Button chooseFlightButton;

}
