package src;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Post class is used to create posts.
 */
public class Post
{
    int postID;
    int accountID;
    String content;
    ArrayList<Like> likes = new ArrayList<Like>();
    ArrayList<Comment> comments = new ArrayList<Comment>();
    /**
     * Default constructor.
     */
    public Post() {
        this.postID = -1;
        this.accountID = -1;
        this.content = "";
    }

    /**
     * Constructor with parameters.
     * @param postID is the ID of the post.
     * @param content is the content of the post.
     * @param accountID is the ID of the account that created the post.
     */
    public Post(int postID, String content, int accountID) {
        this.postID = postID;
        this.accountID = accountID;
        this.content = content;
    }

    /**
     * This method is used to add a like to the post.
     * It checks if the account has already liked the post.
     * If not, it adds the like to the post.
     * @param accountID is the ID of the account that liked the post.
     * @return true if the like is added, false if the account has already liked the post.
     */
    public boolean addLike(int accountID) {
        for (Like like : likes)
            if (like.accountID == accountID)
                return false;
        likes.add(new Like(likes.size(), accountID, this.postID));
        return true;
    }

    /**
     * This method is used to remove a like from the post.
     * It checks if the account has liked the post.
     * If yes, it removes the like from the post.
     * @param accountID is the ID of the account that unliked the post.
     * @return true if the like is removed, false if the account hasn't liked the post.
     */
    public boolean removeLike(int accountID) {
        Iterator<Like> iterator = likes.iterator();
        while (iterator.hasNext()) {
            Like like = iterator.next();
            if (like.accountID == accountID) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * This method is used to list the likes of the post.
     */
    public void listLikes() {
        if (likes.isEmpty()) {
            System.out.println("No one liked it yet.");
            return;
        }
        System.out.println("Like count: " + likes.size());
        System.out.println("Liked by:");
        Iterator<Like> iterator = likes.iterator();
        while (iterator.hasNext()) {
            Like like = iterator.next();
            System.out.print(like.accountID + ", ");
        }
    }

    /**
     * This method is used to list the comments of the post.
     */
    public void listComments() {
        if (comments.isEmpty()) {
            System.out.println("No one commented it yet.");
            return;
        }
        System.out.println("Comment count: " + comments.size());
        System.out.println("Comments:");
        Iterator<Comment> iterator = comments.iterator();
        while (iterator.hasNext()) {
            Comment comment = iterator.next();
            System.out.println(comment.accountID + ": " + comment.content);
        }
    }

    /**
     * This method is used to add a comment to the post.
     * It checks if the account has already commented the post.
     * If not, it adds the comment to the post.
     * @param accountID is the ID of the account that commented the post.
     * @param content is the content of the comment.
     * @return true if the comment is added, false if the account has already commented the post.
     */
    public boolean addComment(int accountID, String content) {
        for (Comment comment : comments) {
            if (comment.accountID == accountID)
                return false;

        }
        Comment comment = new Comment(comments.size(), accountID, this.postID, content);
        comments.add(comment);
        return true;
    }

    /**
     * This method is used to remove a comment from the post.
     * It checks if the account has commented the post.
     * If yes, it removes the comment from the post.
     * @param accountID is the ID of the account that commented the post.
     * @return true if the comment is removed, false if the account hasn't commented the post.
     */
    public boolean removeComment(int accountID) {
        if (!comments.isEmpty()) {
            for (Comment comment : comments) {
                if (comment.accountID == accountID) {
                    comments.remove(comment);
                    return true;
                }
            }
        }
        return false;
    }
}