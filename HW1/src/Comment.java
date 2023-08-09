class Comment extends Interaction {

    private String content;

    Comment() {
        content = "";
        interactionId = 0;
        accountId = 0;
        postId = 0;
    }

    Comment(String content, int accountId, int postId) {
        this.content = content;
        this.interactionId = interactionCount++;
        this.accountId = accountId;
        this.postId = postId;
    }
    public void setContent(String content)
    {

        this.content=content;
    }
    public String getContent() {
        return content;
    }


}