class Like extends Interaction {
    Like() {
        interactionId = 0;
        accountId = 0;
        postId = 0;
    }

    Like(int accountId, int postId) {

        this.interactionId = interactionCount++;
        this.accountId = accountId;
        this.postId = postId;
    }
}