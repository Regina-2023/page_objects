package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class increaseBalance {
    private String deleteString = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;
    private SelenideElement h1Text = $("h1");
    private SelenideElement amount = (SelenideElement) $$("input[type='text']").get(0);
    private SelenideElement cardNumber = $("input[type='tel']");
    private SelenideElement submitButton = $("button[data-test-id='action-transfer']");

    public increaseBalance() {
        h1Text.shouldBe(Condition.visible);
    }

    public balancePage addBalance(String amountRUB, String card) {
        amount.sendKeys(deleteString);
        amount.setValue(amountRUB);
        cardNumber.sendKeys(deleteString);
        cardNumber.setValue(card);
        submitButton.click();
        return new balancePage();
    }

}
