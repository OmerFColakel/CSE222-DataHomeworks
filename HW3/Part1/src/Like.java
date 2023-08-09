package src;
/**
 * Like class is a subclass of Interaction class. It is used to create a like object.
 */
public class Like extends Interaction
{
    /**
     * Default constructor of Like class.
     */
    public Like()
    {
        super();
    }
    /**
     * Constructor of Like class.
     * @param interactionID ID of the interaction.
     * @param accountID ID of the account that liked the post.
     * @param postID ID of the post that is liked.
     */
    public Like(int interactionID, int accountID, int postID)
    {
        super(interactionID, accountID, postID);
    }
}