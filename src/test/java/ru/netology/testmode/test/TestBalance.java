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
    void testTwoCard() {
        var user = DataHelper.getAuthInfo();
        var code = DataHelper.getVerificationCode();
        var firstCard = DataHelper.getFirstCard();
        var secondCard = DataHelper.getSecondCard();

        var loginPage = new LoginPage();
        var codePage = loginPage.login(user.getLogin(), user.getPassword());
        var balancePage = codePage.sendCode(code.getCode());
        var startFirstCardBalance = balancePage.getFirstCardBalance();
        var startSecondCardBalance = balancePage.getSecondCardBalance();
        balancePage.openFirstCard().addBalance(firstCard.getAmount(), secondCard.getSecondCardNumber());
        balancePage.openSecondCard().addBalance(secondCard.getAmount(), firstCard.getFirstCardNumber());
        var endFirstCardBalance = balancePage.getFirstCardBalance();
        var endSecondCardBalance = balancePage.getSecondCardBalance();
        assertThat(startFirstCardBalance).isEqualTo(endFirstCardBalance);
        assertThat(startSecondCardBalance).isEqualTo(endSecondCardBalance);
    }
}



