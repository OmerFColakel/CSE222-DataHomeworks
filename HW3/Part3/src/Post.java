package src;
import java.util.Iterator;

class Post {
    int postID;
    int accountID;
    String content;
    LDLinkedList<Like> likes = new LDLinkedList<>();
    LDLinkedList<Comment> comments = new LDLinkedList<>();

    public Post() {
        this.postID = -1;
        this.accountID = -1;
        this.content = "";
    }

    public Post(int postID, String content, int accountID) {
        this.postID = postID;
        this.accountID = accountID;
        this.content = content;
    }

    public boolean addLike(int accountID) {
        Iterator<Like> iterator = likes.iterator();
        while (iterator.hasNext()) {
            Like like = iterator.next();
            if (like.accountID == accountID)
                return false;
        }
        likes.add(new Like(likes.size(), accountID, this.postID));
        return true;
    }

    public boolean removeLike(int accountID) {
        Iterator<Like> iterator = likes.iterator();
        while (iterator.hasNext()) {
            Like like = iterator.next();
            if (like.accountID == accountID) {
                likes.remove(like);
                return true;
            }
        }
        return false;
    }

    public void listLikes() {
        if (likes.isEmpty()) {
            System.out.println("No one liked it yet.");
            return;
        }
        System.out.println("Like count: " + likes.size());
        System.out.println("Liked by:");
        for (Like like : likes) {
            System.out.print(like.accountID + ", ");
        }

    }

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

    public boolean addComment(int accountID, String content) {
        Iterator<Comment> iterator = comments.iterator();
        while (iterator.hasNext()) {
            Comment comment = iterator.next();
            if (comment.accountID == accountID)
                return false;
        }
        Comment comment = new Comment(comments.size(), accountID, this.postID, content);
        comments.add(comment);
        return true;
    }

    public boolean removeComment(int accountID) {
        if (!comments.isEmpty()) {
            Iterator<Comment> iterator = comments.iterator();
            while (iterator.hasNext()) {
                Comment comment = iterator.next();
                if (comment.accountID == accountID) {
                    comments.remove(comment);
                    return true;
                }
            }
        }
        return false;
    }

}