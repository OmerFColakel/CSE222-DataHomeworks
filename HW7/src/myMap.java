package src;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * This class is a map that stores the frequency of each word in a string.
 */
public class myMap {
    private LinkedHashMap<String, info> map;
    private int mapSize;
    private String str;

    /**
     * Default constructor
     */
    public myMap() {
        map = new LinkedHashMap<String, info>();
        mapSize = 0;
        str = "";
    }

    /**
     * Constructor with a parameter
     *
     * @param newStr The string to make a map of
     */
    public myMap(String newStr) {
        try {
            if (newStr == null)
                throw new IllegalArgumentException("String cannot be null");
            if (newStr.length() == 0)
                throw new IllegalArgumentException("String cannot be empty");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

        map = new LinkedHashMap<String, info>();
        mapSize = 0;
        str = newStr;
        add();
    }

    /**
     * Adds the characters in the string to the map.
     * Checks if the character is already in the map.
     * If it is, it adds the word to the info with the same key.
     * If not, it creates a new info object and adds it to the map.
     */
    public void add() {
        int index = 0, indexOfWord = 0;
        String[] words = getWords();
        try {
            if (words.length == 0)
                throw new IllegalArgumentException("String does not contain any words");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

        while (index < str.length()) {
            if (str.charAt(index) == ' ') {
                indexOfWord++;
            } else if (map.containsKey(String.valueOf(str.charAt(index)))) {
                map.get(String.valueOf(str.charAt(index))).push(words[indexOfWord]);
            } else {
                map.put(String.valueOf(str.charAt(index)), new info(words[indexOfWord]));
                mapSize++;
            }
            index++;
        }
    }

    /**
     * Returns an array of words in the string
     *
     * @return an array of words in the string
     */
    private String[] getWords() {
        ArrayList<String> words = new ArrayList<String>();
        String word = "";
        int index = 0;
        while (index < str.length()) {
            if (str.charAt(index) == ' ') {
                if (word.length() > 0)
                    words.add(word);
                word = "";
            } else {
                word += str.charAt(index);
            }
            ++index;
        }
        if (word.length() > 0)
            words.add(word);
        return words.toArray(new String[words.size()]);
    }

    /**
     * Returns an array of keys in the map
     *
     * @return an array of keys in the map
     */
    public String[] getKeys() {
        return map.keySet().toArray(new String[map.keySet().size()]);
    }

    /**
     * Adds a new key-value pair to the map
     *
     * @param key   The key(String)
     * @param value The value(info)
     */
    public void put(String key, info value) {
        map.put(key, value);
        mapSize++;
    }

    /**
     * Returns the info object associated with the key
     *
     * @param key The key(String)
     * @return The info object associated with the key
     */
    public info get(String key) {
        return map.get(key);
    }

    /**
     * Overrides the toString method
     *
     * @return A string representation of the map
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String key : map.keySet()) {
            sb.append("Letter " + key + " - " + map.get(key).getCount() + "\n");
        }
        return sb.toString();
    }
}
