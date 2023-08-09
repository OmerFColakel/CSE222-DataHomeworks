package src;
/**
 * This class represents a comment on a post.
 * */
public class Comment extends Interaction
{
    String content;
    /**
     * Default constructor.
     * */
    public Comment()
    {
        super();
        content = "";
    }
    /**
     * Constructor with parameters.
     * @param interactionID ID of the interaction.
     * @param accountID ID of the account that commented.
     * @param postID ID of the post.
     * @param content Content of the comment.
     * */
    public Comment(int interactionID, int accountID, int postID, String content)
    {
        super(interactionID, accountID, postID);
        this.content = content;
    }



}