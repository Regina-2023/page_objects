package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BalancePage {
    private SelenideElement refreshButtonField = $("button[data-test-id='action-reload']");
    private ElementsCollection listIncreaseButtonField = $$("button[data-test-id='action-deposit']");
    private ElementsCollection balances = $$("[class='list__item']");

    public BalancePage() {
        refreshButtonField.shouldBe(Condition.visible);
    }

    public IncreaseBalance openFirstCard() {
        listIncreaseButtonField.get(0).click();
        return new IncreaseBalance();
    }

    public IncreaseBalance openSecondCard() {
        listIncreaseButtonField.get(1).click();
        return new IncreaseBalance();
    }
    public String getFirstCardBalance(){
        var firstBalance = balances.get(0).text();
        return firstBalance.substring(firstBalance.lastIndexOf(":")+1,firstBalance.lastIndexOf("р")).strip();
    }

    public String getSecondCardBalance(){
        var secondBalance = balances.get(1).text();
        return secondBalance.substring(secondBalance.lastIndexOf(":")+1,secondBalance.lastIndexOf("р")).strip();
    }
}
