package src;
import java.util.Iterator;

class Account {
    boolean beingViewed;
    boolean isLogined;
    int accountID;
    String username;
    String birthdate;
    String location;
    LDLinkedList<Post> posts = new LDLinkedList<Post>();
    LDLinkedList<Message> sentMessages = new LDLinkedList<Message>();
    LDLinkedList<Message> receivedMessages = new LDLinkedList<Message>();
    LDLinkedList<Account> followers = new LDLinkedList<Account>();
    LDLinkedList<Account> following = new LDLinkedList<Account>();
    LDLinkedList<Account> blocked = new LDLinkedList<Account>();
    LDLinkedList<String> actionHistory = new LDLinkedList<String>();


    public Account() {
        beingViewed = false;
        isLogined = false;
        accountID = -1;
        username = "";
        birthdate = "";
        location = "";
    }


    public Account(int accountID, String username, String birthdate, String location) {
        beingViewed = false;
        isLogined = false;
        this.accountID = accountID;
        this.username = username;
        this.birthdate = birthdate;
        this.location = location;
    }

    public void login(String username) {
        if (isLogined) {
            System.out.println("You are already logged in!");
            return;
        } else if (username == this.username) {
            isLogined = true;
            System.out.println("You have successfully logged in!");
        } else {
            System.out.println("Username is incorrect!");
        }
    }

    public void logout() {
        if (isLogined) {
            isLogined = false;
            beingViewed = false;
            System.out.println("You have successfully logged out!");
        } else {
            System.out.println("You are not logged in!");
        }
    }

    public void follow(Account account) {
        if (!isLogined)
            System.out.println("You are not logged in!");
        else if (this.blocked.contains(account))
            System.out.println("You have blocked this account!");
        else if (account.blocked.contains(this))
            System.out.println("This account has blocked you!");
        else if (this.following.contains(account))
            System.out.println("You are already following this account!");
        else {
            this.following.add(account);
            account.followers.add(this);
            actionHistory.add("You are now following " + account.username + " !");
            account.actionHistory.add(this.username + " is now following you!");
            System.out.println("You are now following " + account.username + "!");
        }
    }

    public void unfollow(Account account) {
        if (!isLogined)
            System.out.println("You are not logged in!");
        else if (!this.following.contains(account))
            System.out.println("You are not following this account!");
        else {
            this.following.remove(account);
            account.followers.remove(this);
            actionHistory.add("You are no longer following " + account.username + " !");
            account.actionHistory.add(this.username + " is no longer following you!");
            System.out.println("You are no longer following " + account.username + "!");
        }
    }

    public void block(Account account) {
        if (!isLogined)
            System.out.println("You are not logged in!");
        else if (this.blocked.contains(account))
            System.out.println("You have already blocked this account!");
        else {
            this.blocked.add(account);
            actionHistory.add("You have blocked " + account.username + " !");
            account.actionHistory.add(this.username + " has blocked you!");
            System.out.println("You have blocked " + account.username + "!");
            if (this.following.contains(account)) {
                following.remove(account);
                account.followers.remove(this);
            }
            if (followers.contains(account)) {
                followers.remove(account);
                account.following.remove(this);
            }
        }
    }

    public void unblock(Account account) {
        if (!isLogined)
            System.out.println("You are not logged in!");
        else if (!this.blocked.contains(account))
            System.out.println("You have not blocked this account!");
        else {
            this.blocked.remove(account);
            account.blocked.remove(this);
            actionHistory.add("You have unblocked " + account.username + " !");
            account.actionHistory.add(this.username + " has unblocked you!");
            System.out.println("You have unblocked " + account.username + "!");
        }
    }

    public void sharePost(Post post) {
        if (!isLogined)
            System.out.println("You are not logged in!");
        else {
            this.posts.add(post);
            actionHistory.add("You have shared a post!");
            System.out.println("You have shared a post!");
        }
    }

    public void deletePost(int postID) {
        if (!isLogined)
            System.out.println("You are not logged in!");
        else {
            Iterator<Post> iterator = posts.iterator();
            while (iterator.hasNext()) {
                Post post = iterator.next();
                if (post.postID == postID) {
                    iterator.remove();
                    actionHistory.add("You have deleted a post!");
                    System.out.println("You have deleted a post!");
                    return;
                }
            }
            System.out.println("You don't have a post with this ID!");
        }
    }

    public void likePost(Account account, int postID) {
        if (!isLogined)
            System.out.println("You are not logged in!");
        else if (account.blocked.contains(this))
            System.out.println("This account has blocked you!");
        else if (this.blocked.contains(account))
            System.out.println("You have blocked this account!");
        else {
            Iterator<Post> iterator = account.posts.iterator();
            while (iterator.hasNext()) {
                Post post = iterator.next();
                if (post.postID == postID) {
                    if (post.addLike(this.accountID)) {
                        actionHistory.add("You have liked a post!");
                        account.actionHistory.add(this.username + " has liked your post!");
                        System.out.println("You have liked a post!");
                    } else {
                        System.out.println("You have already liked this post!");
                    }
                    return;
                }
            }
            System.out.println("This account doesn't have a post with this ID!");
        }
    }

    public void unlikePost(Account account, int postID) {
        if (!isLogined)
            System.out.println("You are not logged in!");
        else if (account.blocked.contains(this))
            System.out.println("This account has blocked you!");
        else if (this.blocked.contains(account))
            System.out.println("You have blocked this account!");
        else {
            Iterator<Post> iterator = account.posts.iterator();
            while (iterator.hasNext()) {
                Post post = iterator.next();
                if (post.postID == postID) {
                    if (post.removeLike(this.accountID)) {
                        actionHistory.add("You have unliked a post!");
                        account.actionHistory.add(this.username + " has unliked your post!");
                        System.out.println("You have unliked a post!");
                    } else {
                        System.out.println("You haven't liked this post!");
                    }
                    return;
                }
            }
            System.out.println("This account doesn't have a post with this ID!");
        }
    }

    public void comment(Account account, int postID, String content) {
        if (!isLogined)
            System.out.println("You are not logged in!");
        else if (account.blocked.contains(this))
            System.out.println("This account has blocked you!");
        else if (this.blocked.contains(account))
            System.out.println("You have blocked this account!");
        else {
            Iterator<Post> iterator = account.posts.iterator();
            while (iterator.hasNext()) {
                Post post = iterator.next();
                if (post.postID == postID) {
                    if (post.addComment(this.accountID, content)) {
                        actionHistory.add("You have commented a post!");
                        account.actionHistory.add(this.username + " has commented to your post!");
                        System.out.println("You have commented a post!");
                    } else {
                        System.out.println("You have already commented this post!");
                    }
                    return;
                }
            }
            System.out.println("This account doesn't have a post with this ID!");
        }
    }

    public void uncomment(Account account, int postID) {
        if (!isLogined)
            System.out.println("You are not logged in!");
        else if (account.blocked.contains(this))
            System.out.println("This account has blocked you!");
        else if (this.blocked.contains(account))
            System.out.println("You have blocked this account!");
        else {
            Iterator<Post> iterator = account.posts.iterator();
            while (iterator.hasNext()) {
                Post post = iterator.next();
                if (post.postID == postID) {
                    if (post.removeComment(this.accountID)) {
                        actionHistory.add("You have deleted a comment!");
                        account.actionHistory.add(this.username + " has deleted his/her comment from your post!");
                        System.out.println("You have deleted a comment!");
                    } else {
                        System.out.println("You don't have a comment on this post!");
                    }
                    return;
                }
            }
            System.out.println("This account doesn't have a post with this ID!");
        }
    }

    public void viewProfile(Account account) {
        if (!isLogined)
            System.out.println("You are not logged in!");
        else if (account.blocked.contains(this))
            System.out.println("This account has blocked you!");
        else if (this.blocked.contains(account))
            System.out.println("You have blocked this account!");
        else {
            account.beingViewed = true;
            System.out.println("Account ID: " + account.accountID);
            System.out.println("Username: " + account.username);
            System.out.println("Birth Date: " + account.birthdate);
            System.out.println("Location: " + account.location);
            System.out.println("Number of Posts: " + account.posts.size());
            System.out.println("Number of Followers: " + account.followers.size());
            account.listFollowers();
            System.out.println("Number of Following: " + account.following.size());
            account.listFollowing();
        }

    }

    private void listFollowers() {
        Iterator<Account> iterator = followers.iterator();
        while (iterator.hasNext()) {
            Account account = iterator.next();
            System.out.println(account.username);
        }
    }

    private void listFollowing() {
        Iterator<Account> iterator = following.iterator();
        while (iterator.hasNext()) {
            Account account = iterator.next();
            System.out.println(account.username);
        }
    }

    public void viewPosts(Account account) {
        if (!isLogined)
            System.out.println("You are not logged in!");
        else if (account.blocked.contains(this))
            System.out.println("This account has blocked you!");
        else if (this.blocked.contains(account))
            System.out.println("You have blocked this account!");
        else if (account.beingViewed) {
            Iterator<Post> iterator = account.posts.iterator();
            while (iterator.hasNext()) {
                Post post = iterator.next();
                System.out.println("Post ID: " + post.postID);
                System.out.println("Content: " + post.content);
                System.out.println("Number of Likes: " + post.likes.size());
                System.out.println("Number of Comments: " + post.comments.size());
                viewComments(account, post.postID);
            }
            account.beingViewed = false;
            return;
        }
        System.out.println("You should view this account first!");
    }


    private void viewComments(Account account, int postID) {
        if (!isLogined)
            System.out.println("You are not logged in!");
        else if (account.blocked.contains(this))
            System.out.println("This account has blocked you!");
        else if (this.blocked.contains(account))
            System.out.println("You have been blocked by this account!");
        else if (account.beingViewed) {
            Iterator<Post> iterator = account.posts.iterator();
            while (iterator.hasNext()) {
                Post post = iterator.next();
                if (post.postID == postID) {
                    post.listComments();
                    return;
                }
            }
            System.out.println("This account doesn't have a post with this ID!");
            account.beingViewed = false;
        }
    }

    public void viewInbox() {
        if (!isLogined)
            System.out.println("You are not logged in!");
        else if (receivedMessages.isEmpty())
            System.out.println("You have not received any messages yet!");
        else {
            Iterator<Message> iterator = receivedMessages.iterator();
            while (iterator.hasNext()) {
                Message message = iterator.next();
                System.out.println("Message ID: " + message.messageID);
                System.out.println("Sender: " + message.senderID);
                System.out.println("Content: " + message.content);
            }
        }
    }

    public void viewOutbox() {
        if (!isLogined)
            System.out.println("You are not logged in!");
        else if (sentMessages.isEmpty())
            System.out.println("You have not received any messages yet!");
        else {
            System.out.println("You have sent " + sentMessages.size() + " messages!");
            Iterator<Message> iterator = sentMessages.iterator();
            while (iterator.hasNext()) {
                Message message = iterator.next();
                System.out.println("Message ID: " + message.messageID);
                System.out.println("Receiver: " + message.receiverID);
                System.out.println("Content: " + message.content);
            }
        }
    }

    public void sendMessage(Account account, String content) {
        if (!isLogined)
            System.out.println("You are not logged in!");
        else if (account.blocked.contains(this))
            System.out.println("This account has blocked you!");
        else if (this.blocked.contains(account))
            System.out.println("You have blocked this account!");
        else {
            Message message = new Message(sentMessages.size(), this.accountID, account.accountID, content);
            sentMessages.add(message);
            account.receivedMessages.add(message);
            actionHistory.add("You have sent a message to " + account.username + "!");
            account.actionHistory.add(this.username + " has sent you a message!");
            System.out.println("You have sent a message to " + account.username + "!");
        }
    }

    public void viewActionHistory() {
        if (!isLogined)
            System.out.println("You are not logged in!");
        else {
            Iterator<String> iterator = actionHistory.iterator();
            while (iterator.hasNext()) {
                String action = iterator.next();
                System.out.println(action);
            }
        }
    }

    public void numOfMessagesOutbox() {
        System.out.println("Number of messages in outbox: " + sentMessages.size());
    }

    public void numOfMessagesInbox() {
        System.out.println("Number of messages in inbox: " + receivedMessages.size());
    }

    public int getAccountID() {
        return accountID;
    }

}
