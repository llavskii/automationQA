package blazedemo.site.sections;

import blazedemo.entities.User;
import com.epam.jdi.uitests.web.selenium.elements.common.TextArea;
import com.epam.jdi.uitests.web.selenium.elements.composite.Form;
import org.openqa.selenium.support.FindBy;

public class PurchaseForms extends Form<User> {
    @FindBy(css = "#inputName")
    public TextArea name;
    @FindBy(css = "#address")
    public TextArea address;
    @FindBy(css = "#city")
    public TextArea city;
    @FindBy(css = "#state")
    public TextArea state;
    @FindBy(css = "#zipCode")
    public TextArea zipCode;
    @FindBy(css = "#creditCardNumber")
    public TextArea creditCardNumber;
    @FindBy(css = "#creditCardMonth")
    public TextArea creditCardMonth;
    @FindBy(css = "#creditCardYear")
    public TextArea creditCardYear;
    @FindBy(css = "#nameOnCard")
    public TextArea nameOnCard;

    public void fillAllForms(User user) {
        name.inputLines(user.name);
        address.inputLines(user.address);
        city.inputLines(user.city);
        state.inputLines(user.state);
        zipCode.inputLines(user.zipCode);
        creditCardNumber.inputLines(user.creditCardNumber);
        creditCardMonth.inputLines(user.month);
        creditCardYear.inputLines(user.year);
        nameOnCard.inputLines(user.nameOnCard);
    }
}
