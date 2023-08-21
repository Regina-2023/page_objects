package ru.netology.testmode.test;

import com.codeborne.selenide.Configuration;
import data.DataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class TestBalance {


    @BeforeEach
    void setup() {
        Configuration.browser = "chrome";
        open("http://localhost:9999");

    }

    @Test
    void testFirstCard() {
        var user = DataHelper.getAuthInfo();
        var code = DataHelper.getVerificationCode();
        var firstCard = DataHelper.getCard("5559000000000001", "5");
        var loginPage = new LoginPage();
        var codePage = loginPage.login(user.getLogin(), user.getPassword());
        var balancePage = codePage.sendCode(code.getCode());

        var startFirstCardBalance = balancePage.getFirstCardBalance();
        balancePage.openSecondCard().addBalance(firstCard.getAmount(), firstCard.getCardNumber());
        var endFirstCardBalance = balancePage.getFirstCardBalance();
        var expectedBalance = Integer.parseInt(startFirstCardBalance) - Integer.parseInt(firstCard.getAmount());
        assertThat(Integer.parseInt(endFirstCardBalance)).isEqualTo(expectedBalance);
    }


    @Test
    void testSecondCard() {
        var user = DataHelper.getAuthInfo();
        var code = DataHelper.getVerificationCode();
        var secondCard = DataHelper.getCard("5559000000000002", "7");
        var loginPage = new LoginPage();
        var codePage = loginPage.login(user.getLogin(), user.getPassword());
        var balancePage = codePage.sendCode(code.getCode());

        var startSecondCardBalance = balancePage.getSecondCardBalance();
        balancePage.openFirstCard().addBalance(secondCard.getAmount(), secondCard.getCardNumber());
        var endSecondCardBalance = balancePage.getSecondCardBalance();
        var expectedBalance = Integer.parseInt(startSecondCardBalance) - Integer.parseInt(secondCard.getAmount());
        assertThat(Integer.parseInt(endSecondCardBalance)).isEqualTo(expectedBalance);
    }
}
