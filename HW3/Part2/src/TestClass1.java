package src;

public class TestClass1{
    public static void main(String[] args) {
    	Account gizemsungu = new Account(0, "gizemsungu", "01/01/2000", "Turkey");
        Account gokhankaya = new Account(1, "gokhankaya", "01/01/2000", "UK");
        Account sibelgulmez = new Account(2, "sibelgulmez", "01/01/2000", "USA");

        //  SCENARIO 1
        sibelgulmez.login("sibelgulmez");
        sibelgulmez.sharePost(new Post(0, "Hello!", sibelgulmez.getAccountID()));
        sibelgulmez.sharePost(new Post(1, "Sibel Gülmez", sibelgulmez.getAccountID()));
        sibelgulmez.follow(gizemsungu);
        sibelgulmez.follow(gokhankaya);
        sibelgulmez.logout();
        gokhankaya.login("gokhankaya");
        gokhankaya.viewPosts(sibelgulmez);
        gokhankaya.viewProfile(sibelgulmez);
        gokhankaya.viewPosts(sibelgulmez);

        gokhankaya.viewProfile(gizemsungu);
        gokhankaya.viewPosts(gizemsungu);

        gokhankaya.likePost(sibelgulmez, 0);
        gokhankaya.likePost(sibelgulmez, 0);

        gokhankaya.comment(sibelgulmez, 0, "Hello Sibel!");

        gokhankaya.viewProfile(sibelgulmez);
        gokhankaya.viewPosts(sibelgulmez);

        gokhankaya.follow(sibelgulmez);
        gokhankaya.follow(gizemsungu);

        gokhankaya.sendMessage(gizemsungu, "Hi Gizem!");
        gokhankaya.viewOutbox();
        gokhankaya.logout();

        gizemsungu.login("gizemsungu");
        gizemsungu.numOfMessagesOutbox();
        gizemsungu.numOfMessagesInbox();
        gizemsungu.viewInbox();
        gizemsungu.viewProfile(sibelgulmez);
        gizemsungu.viewPosts(sibelgulmez);
        gizemsungu.logout();
    }



}