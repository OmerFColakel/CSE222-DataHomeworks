package src;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class is used to create an account object which has a username, birthdate, location, and an
 * arraylist of posts, sent messages, received messages, followers, following, blocked, and action
 * history.
 */
public class Account {
    boolean beingViewed;
    boolean isLogined;
    int accountID;
    String username;
    String birthdate;
    String location;
    ArrayList<Post> posts = new ArrayList<Post>();
    ArrayList<Message> sentMessages = new ArrayList<Message>();
    ArrayList<Message> receivedMessages = new ArrayList<Message>();
    ArrayList<Account> followers = new ArrayList<Account>();
    ArrayList<Account> following = new ArrayList<Account>();
    ArrayList<Account> blocked = new ArrayList<Account>();
    ArrayList<String> actionHistory = new ArrayList<String>();

    /**
     * Default constructor
     */
    public Account() {
        beingViewed = false;
        isLogined = false;
        accountID = -1;
        username = "";
        birthdate = "";
        location = "";
    }

    /**
     * Constructor with parameters of accountID, username, birthdate, and location
     */
    public Account(int accountID, String username, String birthdate, String location) {
        beingViewed = false;
        isLogined = false;
        this.accountID = accountID;
        this.username = username;
        this.birthdate = birthdate;
        this.location = location;
    }

    /**
     * If the user is already logged in, print "You are already logged in!"; otherwise, if the username
     * is correct, set isLogined to true and print "You have successfully logged in!"; otherwise, print
     * "Username is incorrect!"
     *
     * @param username The username of the user.
     */
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

    /**
     * The function checks if the user is logged in, and if so, logs them out
     */
    public void logout() {
        if (isLogined) {
            isLogined = false;
            beingViewed = false;
            System.out.println("You have successfully logged out!");
        } else {
            System.out.println("You are not logged in!");
        }
    }

    /**
     * If the user is logged in, not blocked by the account, not blocking the account, and not already
     * following the account, then the user follows the account
     *
     * @param account The account that you want to follow.
     */
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

    /**
     * This function removes the account from the list of accounts that the user is following and
     * removes the user from the list of followers of the account
     *
     * @param account the account that you want to unfollow
     */
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

    /**
     * If the user is logged in, and the account is not already blocked, then the user blocks the
     * account, and the user is removed from the account's followers and following lists
     *
     * @param account the account that you want to block
     */
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

    /**
     * If the user is logged in, and the account is blocked, then the user unblocks the account
     *
     * @param account the account that you want to unblock
     */
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

    /**
     * If the user is logged in, add the post to the user's posts and add the action to the user's
     * action history
     *
     * @param post the post that you want to share
     */
    public void sharePost(Post post) {
        if (!isLogined)
            System.out.println("You are not logged in!");
        else {
            this.posts.add(post);
            actionHistory.add("You have shared a post!");
            System.out.println("You have shared a post!");
        }
    }

    /**
     * If the user is logged in and if the post exists then the function will delete the post with the given ID
     *
     * @param postID The ID of the post you want to delete.
     */
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

    /**
     * It checks if the user is logged in, if the user is blocked by the account that shared the post, if the user has
     * blocked the account, if the post exists, and if the user hasn't already liked the post
     *
     * @param account The account that the post belongs to
     * @param postID  the ID of the post
     */
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

    /**
     * This method unlikes a post.
     * It checks if the user is logged in, if the user is not blocked by the account that shared the post, if the user has not
     * blocked the account, if the post exists, and if the user has liked the post.
     * If all the conditions are met, then the user unlikes the post.
     *
     * @param account The account that the post belongs to
     * @param postID  the ID of the post
     */
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

    /**
     * This method comments a post.
     * It checks if the user is logged in, if the user is not blocked by the account that shared the post, if the user has not
     * blocked the account, if the post exists, and if the user has not already commented the post.
     * If all the conditions are met, then the user comments the post.
     *
     * @param account
     * @param postID
     * @param content
     */
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
            System.out.println("Comment: This account doesn't have a post with this ID!");
        }
    }

    /**
     * This method deletes a comment.
     * It checks if the user is logged in, if the user is not blocked by the account that shared the post, if the user has not
     * blocked the account, if the post exists, and if the user has commented the post.
     * If all the conditions are met, then the user deletes the comment.
     *
     * @param account
     * @param postID
     */
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

    /**
     * This method is used to view the profile of an account.
     * It checks if the user is logged in, if the user is not blocked by the account, if the user has not blocked the account.
     * If all the conditions are met, then the user views the profile of the account.
     *
     * @param account The account that the user wants to view
     */
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

    /**
     * This method is used list the followers of an account.
     */
    private void listFollowers() {
        Iterator<Account> iterator = followers.iterator();
        while (iterator.hasNext()) {
            Account account = iterator.next();
            System.out.println(account.username);
        }
    }

    /**
     * This method is used list the accounts that this account is following.
     */
    private void listFollowing() {
        Iterator<Account> iterator = following.iterator();
        while (iterator.hasNext()) {
            Account account = iterator.next();
            System.out.println(account.username);
        }
    }

    /**
     * This method is used to view the posts of an account.
     * It checks if the user is logged in, if the user is not blocked by the account, if the user has not blocked the account.
     * If all the conditions are met, then the user views the posts of the account.
     *
     * @param account The account that the user wants to view the posts of
     */
    public void viewPosts(Account account) {
        if (!isLogined)
            System.out.println("You are not logged in!");
        else if (account.blocked.contains(this))
            System.out.println("This account has blocked you!");
        else if (this.blocked.contains(account))
            System.out.println("You have blocked this account!");
        else if (account.beingViewed) {
            if (account.posts.size() == 0) {
                System.out.println("This account doesn't have any posts!");
                return;
            }
            System.out.println("\nPost's of " + account.username + ":\n");
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

    /**
     * This method is used to view the comments of a post.
     * It checks if the user is logged in, if the user is not blocked by the account, if the user has not blocked the account.
     * If all the conditions are met, then the user views the comments of the post.
     *
     * @param account The account that the user wants to view the comments of
     * @param postID  The ID of the post that the user wants to view the comments of
     */
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
        }
    }

    /**
     * This method is used to view the inbox of an account.
     */
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

    /**
     * This method is used to view the outbox of an account.
     */
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

    /**
     * This method is used to send a message to another account.
     * It checks if the user is logged in, if the user is not blocked by the account, if the user has not blocked the account.
     * If all the conditions are met, then the user sends a message to the account.
     *
     * @param account The account that the user wants to send a message to
     * @param content The content of the message
     */
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

    /**
     * This method is used to view the action history of an account.
     */
    public void viewActionHistory() {
        if (!isLogined)
            System.out.println("You are not logged in!");
        else {
            Iterator<String> iterator = actionHistory.iterator();
            while (iterator.hasNext()) {
            	String str = iterator.next();
                System.out.println(str);
            }

        }
    }

    /**
     * This method is used to view the number of messages in the inbox of an account.
     */
    public void numOfMessagesOutbox() {
        System.out.println("Number of messages in outbox: " + sentMessages.size());
    }

    /**
     * This method is used to view the number of messages in the outbox of an account.
     */
    public void numOfMessagesInbox() {
        System.out.println("Number of messages in inbox: " + receivedMessages.size());
    }

    /**
     * This method is used to get the ID of this account.
     */
    public int getAccountID() {
        return accountID;
    }

}
