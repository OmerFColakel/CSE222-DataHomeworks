package src;
abstract class Interaction
{
    int interactionID;
    int accountID;
    int postID;

    public Interaction()
    {
        interactionID = -1;
        accountID = -1;
        postID = -1;
    }
    public Interaction(int interactionID, int accountID, int postID)
    {
        this.interactionID = interactionID;
        this.accountID = accountID;
        this.postID = postID;
    }

}