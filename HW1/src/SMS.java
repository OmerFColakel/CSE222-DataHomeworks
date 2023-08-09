class SMS {
    private boolean isLogined = false;
    private Account[] accounts = new Account[10];
    private int indexOfLoginedAccount = -1;
    private int accountsCount = 0;

    SMS() {
        System.out.println("Social Media Software has been created");

    }

    SMS(Account account) {
        System.out.println("Social Media Software has been created");
        addAccount(account);
    }

    SMS(Account[] accounts) {
        System.out.println("Social Media Software has been created");
        addAccounts(accounts);
    }

    public boolean addAccount(Account account) {
        if (checkExistingAccounts(account)) {
            System.out.println("Account has been added:" + account.getUserName());
            accounts[accountsCount] = account;
            accountsCount++;
            if (accountsCount == accounts.length)
                increaseAccountsSize();
            return true;
        }
        return false;
    }

    public void addAccounts(Account[] accounts) {
        for (int i = 0; i < accounts.length; i++)
            addAccount(accounts[i]);
    }

    private boolean checkExistingAccounts(Account account) {
        for (int i = 0; i < accountsCount; i++) {
            if (accounts[i].getUserName() == account.getUserName()) {
                System.out.println("This account already exists: " + account.getUserName());
                return false;
            }
        }
        return true;
    }

    public void listAllAccounts() {
        for (int i = 0; i < accountsCount; i++)
            System.out.println(accounts[i]);
    }

    public void followSomeone(String userName) {
        if (isLogined) {
            for (int i = 0; i < accountsCount; i++) {
                if (accounts[i].getUserName() == userName) {
                    accounts[indexOfLoginedAccount].addFollowing(accounts[i]);
                    accounts[i].addFollower(accounts[indexOfLoginedAccount]);
                    return;
                }
            }
        }
    }
    public void unfollowSomeone(String userName) {
        if (isLogined) {
            for (int i = 0; i < accountsCount; i++) {
                if (accounts[i].getUserName() == userName) {
                    accounts[indexOfLoginedAccount].removeFollowing(accounts[i]);
                    accounts[i].removeFollower(accounts[indexOfLoginedAccount]);
                    return;
                }
            }
        }
    }

    private boolean increaseAccountsSize() {
        Account[] temp = new Account[accounts.length + 10];
        for (int i = 0; i < accounts.length; i++) {
            temp[i] = accounts[i];
        }
        accounts = temp;
        return true;
    }

    public boolean login(String userName, String password) {
        if (!isLogined) {
            for (int i = 0; i < accountsCount; i++) {
                if (accounts[i].login(userName, password)) {
                    System.out.println("Login successful");
                    isLogined = true;
                    indexOfLoginedAccount = i;
                    return true;
                }

            }
        }
        System.out.println("Login failed");
        return false;
    }

    public boolean addPost(String content) {
        if (isLogined) {
            Post post = new Post(accounts[indexOfLoginedAccount].getAccountId(), content);
            accounts[indexOfLoginedAccount].addPost(post);
            return true;
        }
        return false;
    }

    public String getAccount(String username) {
        if (isLogined) {
            for (int i = 0; i < accountsCount; i++) {
                if (accounts[i].getUserName() == username) {
                    return accounts[i].toString();
                }
            }
        }

        return "Account not found";
    }

    public String getPosts(String username) {
        if (isLogined) {
            for (int i = 0; i < accountsCount; i++) {
                if (accounts[i].getUserName() == username) {
                    System.out.println(accounts[i].getPosts());
                }
            }
        }

        return null;
    }


    public boolean logout() {
        if (isLogined) {
            isLogined = false;
            accounts[indexOfLoginedAccount].logout();
            indexOfLoginedAccount = -1;
            System.out.println("Logout successful");
            return true;
        }
        return false;
    }

    public void viewProfile(String username) {
        System.out.println("Viewing profile...");
        if (isLogined) {
            for (int i = 0; i < accountsCount; i++) {
                if (accounts[i].getUserName() == username) {
                    System.out.println(accounts[i].viewProfile());
                    return;
                }
            }
        }
        System.out.println("Account not found");
    }

    public boolean likePost(int PostId) {
        if (isLogined) {
            for (int i = 0; i < accountsCount; i++) {

                if (accounts[i].likePost(PostId, accounts[indexOfLoginedAccount].getAccountId())) {
                    System.out.println("Post liked");
                    return true;
                }

            }
        }
        return false;
    }

    public void comment(int postId, String content) {
        if (isLogined) {
            for (int i = 0; i < accountsCount; i++) {
                if (accounts[i].comment(postId, content, accounts[indexOfLoginedAccount].getAccountId())) {
                    System.out.println("Comment added");
                    return;
                }
            }
        }
    }

    public void listComments(int postId) {
        if (isLogined) {
            for (int i = 0; i < accountsCount; i++) {
                if (accounts[i].listComments(postId)) {
                    return;
                }
            }
        }
    }

    public void sendMessage(String content, String username) {
        if (isLogined) {
            for (int i = 0; i < accountsCount; i++) {
                if (accounts[i].getUserName() == username) {
                    accounts[indexOfLoginedAccount].sendMessage(content, accounts[i].getAccountId());
                    accounts[i].receiveMessage(content, accounts[indexOfLoginedAccount].getAccountId());
                    return;
                }
            }
        }
        System.out.println("Couldn't send the message");
    }

    public void getInbox() {
        if (isLogined) {
            accounts[indexOfLoginedAccount].getInbox();
        }
    }

    public void getOutbox() {
        if (isLogined) {
            accounts[indexOfLoginedAccount].getOutbox();
        }
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < accountsCount; i++) {
            result += accounts[i].getUserName() + "\n";
        }
        return result;
    }


}