package src;
/**
 * Message class is used to store message information.
 */
public class Message {
    int messageID;
    int senderID;
    int receiverID;
    String content;

    /**
     * Default constructor.
     */
    public Message() {
        this.content = "";
        this.messageID = -1;
        this.receiverID = -1;
        this.senderID = -1;
    }

    /**
     * Constructor with parameters.
     * @param messageID is the message's ID.
     * @param senderID is the sender account's ID.
     * @param receiverID is the receiver account's ID.
     * @param content is the message's content.
     */
    public Message(int messageID, int senderID, int receiverID, String content) {
        this.messageID = messageID;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.content = content;
    }
}