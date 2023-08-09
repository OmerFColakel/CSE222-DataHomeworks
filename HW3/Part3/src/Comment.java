package src;
class Comment extends Interaction
{
    String content;
    public Comment()
    {
        super();
        content = "";
    }
    public Comment(int interactionID, int accountID, int postID, String content)
    {
        super(interactionID, accountID, postID);
        this.content = content;
    }

}