package data;

import lombok.Value;


public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    public static VerificationCode getVerificationCode() {
        return new VerificationCode("12345");
    }

    @Value
    public static class FirstCard {
        String firstCardNumber;
        String amount;
    }

    public static FirstCard getFirstCard() {
        return new FirstCard("5559000000000001", "1");
    }

    @Value
    public static class SecondCard {
        String secondCardNumber;
        String amount;
    }

    public static SecondCard getSecondCard() {
        return new SecondCard("5559000000000002", "1");
    }
}
