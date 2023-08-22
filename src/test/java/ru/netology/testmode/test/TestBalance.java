package ru.netology.testmode.test;

import com.codeborne.selenide.Configuration;
import data.DataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;


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
        var firstCard = DataHelper.getFirstCard();
        var loginPage = new LoginPage();
        var codePage = loginPage.login(user.getLogin(), user.getPassword());
        var balancePage = codePage.sendCode(code.getCode());

        var startFirstCardBalance = balancePage.getFirstCardBalance();
        var startSecondCardBalance = balancePage.getSecondCardBalance();
        balancePage.openSecondCard().addBalance(firstCard.getAmount(), firstCard.getCardNumber());
        var endFirstCardBalance = balancePage.getFirstCardBalance();
        var endSecondCardBalance = balancePage.getSecondCardBalance();
        var expectedFirstCardBalance = Integer.parseInt(startFirstCardBalance) - Integer.parseInt(firstCard.getAmount());
        var expectedSecondCardBalance = Integer.parseInt(startSecondCardBalance) + Integer.parseInt(firstCard.getAmount());
        assertEquals(Integer.parseInt(endFirstCardBalance), expectedFirstCardBalance);
        assertEquals(Integer.parseInt(endSecondCardBalance), expectedSecondCardBalance);
    }


    @Test
    void testSecondCard() {
        var user = DataHelper.getAuthInfo();
        var code = DataHelper.getVerificationCode();
        var secondCard = DataHelper.getSecondCard();
        var loginPage = new LoginPage();
        var codePage = loginPage.login(user.getLogin(), user.getPassword());
        var balancePage = codePage.sendCode(code.getCode());

        var startFirstCardBalance = balancePage.getFirstCardBalance();
        var startSecondCardBalance = balancePage.getSecondCardBalance();
        balancePage.openFirstCard().addBalance(secondCard.getAmount(), secondCard.getCardNumber());
        var endFirstCardBalance = balancePage.getFirstCardBalance();
        var endSecondCardBalance = balancePage.getSecondCardBalance();
        var expectedFirstCardBalance = Integer.parseInt(startFirstCardBalance) + Integer.parseInt(secondCard.getAmount());
        var expectedSecondCardBalance = Integer.parseInt(startSecondCardBalance) - Integer.parseInt(secondCard.getAmount());
        assertEquals(Integer.parseInt(endFirstCardBalance), expectedFirstCardBalance);
        assertEquals(Integer.parseInt(endSecondCardBalance), expectedSecondCardBalance);
    }
}
