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
    public static class Card {
        String cardNumber;
        String amount;
    }


    public static Card getFirstCard(){
        return new Card("5559 0000 0000 0001", "1");
    }

    public static  Card getSecondCard(){
        return new Card("5559 0000 0000 0002", "1");
    }
}
