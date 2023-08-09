class Post {
    private static int postCount = 0;
    private int postId = postCount++;
    private int accountId = 0;
    private Like[] likes = new Like[10];
    private int likesCount = 0;
    private Comment[] comments = new Comment[10];
    private int commentsCount = 0;
    private String content = "";

    Post() {
    }

    Post(int accountId, String content) {
        this.content = content;
        this.accountId = accountId;
    }

    public String getContent() {
        return content;
    }

    public int getAccountId() {
        return accountId;
    }

    public int getPostId() {
        return postId;
    }

    public boolean like(int accountId) {
        if (checkLikes(accountId)) {
            if (likesCount == likes.length)
                increaseLikesSize();
            likes[likesCount] = new Like(accountId, postId);
            likesCount++;
            return true;
        }
        return false;
    }
    public boolean comment(String comment, int accountId)
    {
        int index=checkComments(accountId);
        if(index == -1)
        {
            if(commentsCount==comments.length)
                increaseCommentsSize();
            comments[commentsCount]=new Comment(comment,accountId,postId);
            commentsCount++;
            return true;
        }
        else
        {
            comments[index].setContent(comment);
            return true;
        }



    }
    public void listComments()
    {
        if(commentsCount==0)
            return;
        for(int i=0;i<commentsCount;i++)
        {
            System.out.println(comments[i].getContent());
        }

    }

    private int checkComments(int accountId)
    {
        for(int i=0;i<commentsCount;i++)
        {
            if(comments[i].getAccountId()==accountId)
                return i;
        }
        return -1;
    }

    private boolean checkLikes(int accountId) {
        for (int i = 0; i < likesCount; i++) {
            if (likes[i].getAccountId() == accountId)
                return false;
        }
        return true;
    }

    private boolean increaseLikesSize() {
        Like[] temp = new Like[likes.length + 10];
        for (int i = 0; i < likes.length; i++) {
            temp[i] = likes[i];
        }
        likes = temp;
        return true;
    }
    private boolean increaseCommentsSize()
    {
        Comment[] temp=new Comment[comments.length+10];
        for(int i=0;i<comments.length;i++)
        {
            temp[i]=comments[i];
        }
        comments=temp;
        return true;
    }

    public String toString() {
        return "PostId: " + postId + " Content: " + content + " Likes: " + likesCount + " Comments: " + commentsCount;
    }


}