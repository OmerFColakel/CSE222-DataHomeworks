package src;

public class TestClass4{
    public static void main(String[] args) {
    	Account gizemsungu = new Account(0, "gizemsungu", "01/01/2000", "Turkey");
        Account gokhankaya = new Account(1, "gokhankaya", "01/01/2000", "UK");
        Account sibelgulmez = new Account(2, "sibelgulmez", "01/01/2000", "USA");
	Account account4 = new Account(3, "account4", "01/01/2000", "Turkey");
        Account account5 = new Account(4, "account5", "01/01/2000", "UK");
        Account account6 = new Account(5, "account6", "01/01/2000", "USA");
        Account account7 = new Account(6, "account7", "01/01/2000", "Turkey");
        Account account8 = new Account(7, "account8", "01/01/2000", "UK");
        Account account9 = new Account(8, "account9", "01/01/2000", "USA");
        Account account10 = new Account(9, "account10", "01/01/2000", "Turkey");
        
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

        //  SCENARIO 3
        gizemsungu.login("gizemsungu");
        gizemsungu.block(sibelgulmez);
        gizemsungu.logout();

        sibelgulmez.login("sibelgulmez");
        sibelgulmez.follow(gizemsungu);
        sibelgulmez.viewProfile(gizemsungu);
        sibelgulmez.sendMessage(gizemsungu, "Hello!");
        sibelgulmez.logout();

        //  SCENARIO 4
        System.out.println("SCENARIO 4");
        gizemsungu.login("gizemsungu");
        gizemsungu.follow(account4);
        gizemsungu.follow(account5);
        gizemsungu.follow(account6);
        gizemsungu.follow(account7);
        gizemsungu.follow(account8);
        gizemsungu.follow(account9);
        gizemsungu.follow(account10);
        gizemsungu.logout();
        System.out.println("UNLIKE:\n");
        gokhankaya.login("gokhankaya");
        System.out.println("\nBefore Like:");
        gokhankaya.viewProfile(gizemsungu);
        gokhankaya.viewPosts(gizemsungu);
        gokhankaya.likePost(gizemsungu, 0);
        System.out.println("\nAfter Like:");
        gokhankaya.viewProfile(gizemsungu);
        gokhankaya.viewPosts(gizemsungu);
        gokhankaya.unlikePost(gizemsungu, 0);
        System.out.println("\nAfter Unlike:");
        gokhankaya.viewProfile(gizemsungu);
        gokhankaya.viewPosts(gizemsungu);
        gokhankaya.logout();

        System.out.println("\nUNBLOCK:\n");
        gizemsungu.login("gizemsungu");
        gizemsungu.unblock(sibelgulmez);
        gizemsungu.logout();
        sibelgulmez.login("sibelgulmez");
        sibelgulmez.viewProfile(gizemsungu);
        sibelgulmez.viewPosts(gizemsungu);
        sibelgulmez.follow(gizemsungu);
        sibelgulmez.sendMessage(gizemsungu, "Hello!");
        sibelgulmez.logout();
        gizemsungu.login("gizemsungu");
        gizemsungu.viewInbox();
        gizemsungu.logout();

        System.out.println("\nUNCOMMENT:\n");
        gokhankaya.login("gokhankaya");
        gokhankaya.viewProfile(sibelgulmez);
        gokhankaya.viewPosts(sibelgulmez);
        gokhankaya.uncomment(sibelgulmez, 0);
        gokhankaya.viewProfile(sibelgulmez);
        gokhankaya.viewPosts(sibelgulmez);

        System.out.println("\nUNFOLLOW:\n");
        gokhankaya.viewProfile(sibelgulmez);
        gokhankaya.viewProfile(gokhankaya);
        gokhankaya.unfollow(sibelgulmez);
        gokhankaya.viewProfile(gokhankaya);
        gokhankaya.logout();
        sibelgulmez.login("sibelgulmez");
        sibelgulmez.viewProfile(sibelgulmez);
        sibelgulmez.logout();

        System.out.println("\nAction History:\n");
        gokhankaya.login("gokhankaya");
        gokhankaya.viewActionHistory();
        gokhankaya.logout();
    }



}
