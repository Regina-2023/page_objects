package ru.netology.testmode.test;

//import com.codeborne.selenide.Configuration;
import data.DataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class TestBalance {


    @BeforeEach
    void setup() {
//        Configuration.browser = "chrome";
        open("http://localhost:9999");

    }


    @Test
    void testTwoCard() {
        var userInfo = new DataHelper();

        var loginPage = new LoginPage();
        var codePage = loginPage.login(userInfo.username, userInfo.password);
        var balancePage = codePage.sendCode(userInfo.code);
        var startFirstCardBalance = balancePage.getFirstCardBalance();
        var startSecondCardBalance = balancePage.getSecondCardBalance();
        balancePage.openFirstCard().addBalance(userInfo.amount, userInfo.secondCardNumber);
        balancePage.openSecondCard().addBalance(userInfo.amount, userInfo.firstCardNumber);
        var endFirstCardBalance = balancePage.getFirstCardBalance();
        var endSecondCardBalance = balancePage.getFirstCardBalance();
        assertThat(startFirstCardBalance).isEqualTo(endFirstCardBalance);
        assertThat(startSecondCardBalance).isEqualTo(endSecondCardBalance);
    }
}



