package src;
import java.util.ArrayList;

/**
 * This class is used to store info about keys in the map. It stores the count of the key and the words associated with it.
 */
public class info {

    private int count;
    private ArrayList<String> words;

    /**
     * Default Constructor
     */
    public info() {
        count = 0;
        words = new ArrayList<String>();
    }

    /**
     * Constructor with a word to be added
     * @param word The word to be added
     */
    public info(String word) {
        count = 0;
        words = new ArrayList<String>();
        push(word);
    }

    /**
     * Adds a word to the list of words associated with the key. Also increments the count.
     * @param word The word to be added
     */
    public void push(String word) {
        words.add(word);
        increment();
    }

    /**
     * Increments the count of the key
     */
    private void increment() {
        count++;
    }

    /**
     * Returns the count of the key
     * @return The count of the key
     */
    public int getCount() {
        return count;
    }

    /**
     * Overrides the toString method of the Object class
     * @return A string representation of the object
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Count: " + count + " Words: [");
        for(int i=0;i<words.size();++i)
        {
            sb.append(words.get(i));
            if(i!=words.size()-1)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
