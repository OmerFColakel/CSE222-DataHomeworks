// TODO: Blocking accounts
// TODO: Doing all scenarios successfully
// TODO: Tests in Linux
// TODO: Javadoc
// TODO: Use Case Diagram
// TODO: Deleting posts
// TODO: Deleting comments
// TODO: Deleting messages
// TODO: Deleting accounts



public class Main {
    public static void main(String[] args) {
        int[] A = { 1, 2, 3, -4, 5, 6, 0, 8, 9, 101 };
        int[] B = { 1, 2, 3, -4, 5, 6, 0, 8, 9, 101 };
        int minA = A [0], minB = B [0], maxA = A [0], maxB = B [0];
        for(int i=0;i< A.length;++i)
        {
            if(minA > A[i])
                minA = A[i];
            if(maxA < A[i])
                maxA = A[i];
        }
        for (int i = 0; i < B.length; ++i) {
            if (minB > B[i])
                minB = B[i];
            if (maxB < B[i])
                maxB = B[i];
        }
        int[] minArr = {minA*minB, minA*maxB, maxA*minB, maxA*maxB};
        int min = minArr[0];
        for(int i=0;i<minArr.length;++i)
        {
            if(min > minArr[i])
                min = minArr[i];
        }
    }
}
/*
Account[] accounts = new Account[4];
        accounts[0] = new Account("gizemsungu", "GizemSungu", "01/01/2000", "Istanbul");
        accounts[1] = new Account("sibelgulmez", "SibelGulmez", "01/01/2001", "Ankara");
        accounts[2] = new Account("gokhankaya", "GokhanKaya", "01/01/2002", "Izmir");
        accounts[3] = new Account("gizemsungu", "GizemSungu", "01/01/2000", "Istanbul");


        // Scenario 1
        SMS sms1 = new SMS(accounts);
        sms1.addAccount(new Account("omerfaruk", "OmerFaruk", "01/01/2003", "Giresun"));

        sms1.login("sibelgulmez", "SibelGulmez");
        sms1.addPost("Hello world1");
        sms1.addPost("Hello world2");
        sms1.logout();
        sms1.login("gokhankaya", "GokhanKaya");
        sms1.viewProfile("sibelgulmez");
        sms1.likePost(0);
        sms1.likePost(0);
        sms1.likePost(0);
        sms1.getPosts("sibelgulmez");
        sms1.comment(0, "Nice post");
        sms1.getPosts("sibelgulmez");
        sms1.listComments(0);
        sms1.followSomeone("sibelgulmez");
        sms1.followSomeone("gizemsungu");
        sms1.listAllAccounts();
        sms1.sendMessage("Hello", "sibelgulmez");
        sms1.getOutbox();
        sms1.logout();
        sms1.login("sibelgulmez", "SibelGulmez");
        sms1.getInbox();
        sms1.listAllAccounts();
        sms1.logout();
        sms1.login("gokhankaya", "GokhanKaya");
        sms1.unfollowSomeone("sibelgulmez");
        sms1.listAllAccounts();
        sms1.logout();
 */