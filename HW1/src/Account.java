class Account {
    static int accountCount = 0;
    private boolean isLogined = false;
    private int accountId = accountCount++;
    private String userName = "";
    private String password = "";
    private String birthDate = "";
    private String location = "";
    private Post[] posts = new Post[10];
    private int postsCount = 0;
    private Message[] sentMessages = new Message[10];
    private int sentMessagesCount = 0;
    private Message[] receivedMessages = new Message[10];
    private int receivedMessagesCount = 0;
    private Account[] following = new Account[10];
    private int followersCount = 0;
    private Account[] followers = new Account[10];
    private int followingCount = 0;

    Account() {
        System.out.println("Account has been created(Type 1)");
    }

    Account(String userName, String password, String birthDate, String location) {
        this.userName = userName;
        this.password = password;
        this.birthDate = birthDate;
        this.location = location;
    }


    public boolean addFollower(Account follower) {
        followers[followersCount] = follower;
        followersCount++;
        if (followersCount == followers.length)
            increaseFollowerSize();
        return true;
    }

    public boolean addFollowing(Account following) {

        if (isLogined && (!checkFollowing(following))) {
            this.following[followingCount] = following;
            followingCount++;
            if (followingCount == this.following.length)
                increaseFollowingSize();
            System.out.println("Following successful");
            return true;
        }
        System.out.println("Following failed");
        return false;
    }
    public boolean removeFollower(Account follower) {
        for (int i = 0; i < followersCount; i++) {
            if (followers[i].getAccountId() == follower.getAccountId()) {
                followers[i] = followers[followersCount - 1];
                followersCount--;
                if(i != followersCount)
                    followers[i] = followers[followersCount];

                return true;
            }
        }
        return false;
    }
    public boolean removeFollowing(Account following) {
        for (int i = 0; i < followingCount; i++) {
            if (this.following[i].getAccountId() == following.getAccountId()) {
                this.following[i] = this.following[followingCount - 1];
                followingCount--;
                if(i != followingCount)
                    this.following[i] = this.following[followersCount];
                return true;
            }
        }
        return false;
    }

    public boolean checkFollowing(Account following) {
        for (int i = 0; i < followingCount; i++) {
            if (this.following[i].getAccountId() == following.getAccountId())
                return true;
        }
        return false;
    }

    public boolean addPost(Post post) {
        if (isLogined) {
            posts[postsCount] = post;
            postsCount++;
            if (postsCount == posts.length)
                increasePostsSize();
            return true;
        }
        return false;

    }

    public boolean receiveMessage(String content, int senderID) {
        Message message = new Message(content, senderID, this.accountId);
        receivedMessages[receivedMessagesCount] = message;
        receivedMessagesCount++;
        if (receivedMessagesCount == receivedMessages.length)
            increaseReceivedMessagesSize();
        return true;
    }

    public boolean sendMessage(String content, int receiverID) {
        Message message = new Message(content, this.accountId, receiverID);
        sentMessages[sentMessagesCount] = message;
        sentMessagesCount++;
        if (sentMessagesCount == sentMessages.length)
            increaseSentMessagesSize();
        return true;
    }

    public void getInbox() {
        if (receivedMessagesCount == 0) {
            System.out.println("No messages");
            return;
        }
        for (int i = 0; i < receivedMessagesCount; i++) {
            System.out.println(receivedMessages[i].getContent());
        }
    }

    public void getOutbox() {
        if (sentMessagesCount == 0) {
            System.out.println("No messages");
            return;
        }
        for (int i = 0; i < sentMessagesCount; i++) {
            System.out.println(sentMessages[i].getContent());
        }
    }

    public String getUserName() {
        return userName;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getLocation() {
        return location;
    }

    public String getPosts() {
        String content = "";

        if (postsCount == 0)
            return "No posts";

        for (int i = 0; i < postsCount; i++) {
            content += posts[i] + "\n";
        }

        return content;
    }

    public Message[] getReceivedMessages() {
        return receivedMessages;
    }

    public Message[] getSentMessages() {
        return sentMessages;
    }

    public Account[] getFollowing() {
        return following;
    }

    public Account[] getFollowers() {
        return followers;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public int getPostsCount() {
        return postsCount;
    }

    public int getReceivedMessagesCount() {
        return receivedMessagesCount;
    }

    public int getSentMessagesCount() {
        return sentMessagesCount;
    }

    private void increaseFollowerSize() {
        Account[] temp = new Account[followers.length * 2];
        for (int i = 0; i < followers.length; i++)
            temp[i] = followers[i];
        followers = temp;
    }

    private void increaseFollowingSize() {
        Account[] temp = new Account[following.length * 2];
        for (int i = 0; i < following.length; i++)
            temp[i] = following[i];
        following = temp;
    }

    private void increasePostsSize() {
        Post[] temp = new Post[posts.length * 2];
        for (int i = 0; i < posts.length; i++)
            temp[i] = posts[i];
        posts = temp;
    }

    private void increaseSentMessagesSize() {
        Message[] temp = new Message[sentMessagesCount * 2];
        for (int i = 0; i < sentMessagesCount; i++)
            temp[i] = sentMessages[i];
        sentMessages = temp;
    }

    private void increaseReceivedMessagesSize() {
        Message[] temp = new Message[receivedMessagesCount * 2];
        for (int i = 0; i < receivedMessagesCount; i++)
            temp[i] = receivedMessages[i];
        receivedMessages = temp;
    }

    public String toString() {
        return "Account ID: " + accountId + " Username: " + userName + " Password: " + password + " Birth Date: " + birthDate + " Location: " + location + " Post Count: " + postsCount + " Sent Messages: " + sentMessagesCount + " Received Messaged: " + receivedMessagesCount + " Followers: " + followersCount + " Following: " + followingCount + " Logined: " + isLogined;
    }


    public boolean login(String userName, String password) {
        if (!isLogined) {
            if ((this.userName == userName) && (this.password == password)) {
                isLogined = true;
                return true;
            }
        }

        return false;
    }

    public boolean logout() {
        if (isLogined) {
            isLogined = false;
            return true;
        }
        return false;
    }

    public boolean likePost(int postId, int accountId) {
        for (int i = 0; i < postsCount; i++) {
            if (posts[i].getPostId() < postId) {
                return false;
            } else if (posts[i].getPostId() == postId) {
                posts[i].like(accountId);
                return true;
            }
        }
        return false;
    }

    public boolean listComments(int postId) {
        for (int i = 0; i < postsCount; i++) {
            if (posts[i].getPostId() < postId) {
                return false;
            } else if (posts[i].getPostId() == postId) {
                posts[i].listComments();
                return true;
            }
        }
        return false;
    }

    public boolean comment(int postId, String comment, int accountId) {
        for (int i = 0; i < postsCount; i++) {
            if (posts[i].getPostId() < postId) {
                return false;
            } else if (posts[i].getPostId() == postId) {
                posts[i].comment(comment, accountId);
                return true;
            }
        }
        return false;
    }

    public String viewProfile() {
        return "Username: " + userName + " Birth Date: " + birthDate + " Location: " + location + " Post Count: " + postsCount + " Followers: " + followersCount + " Following: " + followingCount;
    }


}