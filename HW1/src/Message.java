class Message {
    private static int MessageCount = 0;
    private int messageId = MessageCount++;
    private int senderID = 0;
    private int receiverID = 0;
    private String content = "";

    Message() {
        System.out.println("Message class constructor");
    }

    Message(String msg, int senderID, int receiverID) {
        this.content = msg;
        this.senderID = senderID;
        this.receiverID = receiverID;
    }

    public String getContent() {
        return content;
    }

    public int getSenderID() {
        return senderID;
    }

    public int getReceiverID() {
        return receiverID;
    }
}