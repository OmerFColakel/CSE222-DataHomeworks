package src;
class Message {
    int messageID;
    int senderID;
    int receiverID;
    String content;

    public Message() {
        this.content = "";
        this.messageID = -1;
        this.receiverID = -1;
        this.senderID = -1;
    }
    public Message(int messageID, int senderID, int receiverID, String content) {
        this.messageID = messageID;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.content = content;
    }
}