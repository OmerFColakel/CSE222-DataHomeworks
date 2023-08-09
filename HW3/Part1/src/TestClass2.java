package src;

public class TestClass2{
    public static void main(String[] args) {
    	Account gizemsungu = new Account(0, "gizemsungu", "01/01/2000", "Turkey");
        Account gokhankaya = new Account(1, "gokhankaya", "01/01/2000", "UK");
        Account sibelgulmez = new Account(2, "sibelgulmez", "01/01/2000", "USA");
        // SCENARIO 2
        gizemsungu.login("gizemsungu");
        gizemsungu.sharePost(new Post(0, "Hello1!", gizemsungu.getAccountID()));
        gizemsungu.sharePost(new Post(1, "Hello2!", gizemsungu.getAccountID()));
        gizemsungu.logout();

        sibelgulmez.login("sibelgulmez");
        sibelgulmez.viewProfile(gizemsungu);
        sibelgulmez.viewPosts(gizemsungu);
        sibelgulmez.likePost(gizemsungu, 0);
        sibelgulmez.logout();

        gokhankaya.login("gokhankaya");
        gokhankaya.viewProfile(gizemsungu);
        gokhankaya.viewPosts(gizemsungu);
        gokhankaya.comment(gizemsungu, 1, "Nice!");
        gokhankaya.sendMessage(gizemsungu, "Hello!");
        gokhankaya.logout();

        gizemsungu.login("gizemsungu");
        gizemsungu.viewProfile(gizemsungu);
        gizemsungu.viewPosts(gizemsungu);
        gizemsungu.viewInbox();
        gizemsungu.logout();
    }



}