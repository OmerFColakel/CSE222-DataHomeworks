package src;

public class TestClass3{
    public static void main(String[] args) {
    	Account gizemsungu = new Account(0, "gizemsungu", "01/01/2000", "Turkey");
        Account gokhankaya = new Account(1, "gokhankaya", "01/01/2000", "UK");
        Account sibelgulmez = new Account(2, "sibelgulmez", "01/01/2000", "USA");
        //  SCENARIO 3
        gizemsungu.login("gizemsungu");
        gizemsungu.block(sibelgulmez);
        gizemsungu.logout();

        sibelgulmez.login("sibelgulmez");
        sibelgulmez.follow(gizemsungu);
        sibelgulmez.viewProfile(gizemsungu);
        sibelgulmez.sendMessage(gizemsungu, "Hello!");
    }



}