package src;
/**
 * This class is the abstract class for all interactions (likes and comments).
 */
public abstract class Interaction
{
    int interactionID;
    int accountID;
    int postID;
    /**
     * Default constructor for the Interaction class.
     * */
    public Interaction()
    {
        interactionID = -1;
        accountID = -1;
        postID = -1;
    }
    /**
     * Constructor for the Interaction class.
     * @param interactionID The ID of the interaction.
     * @param accountID The ID of the account that made the interaction.
     * @param postID The ID of the post that the interaction is made on.
     * */
    public Interaction(int interactionID, int accountID, int postID)
    {
        this.interactionID = interactionID;
        this.accountID = accountID;
        this.postID = postID;
    }

}