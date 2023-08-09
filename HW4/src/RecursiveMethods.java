package src;
public class RecursiveMethods {
    // PART 1

    /**
     * Method that checks if the username is valid (contains only letters)
     *
     * @param username the username to check
     * @return true if the username is valid, false otherwise
     */
    public static boolean checkIfValidUsername(String username) {
        if (username.length() == 0) {
            System.out.println("The username is invalid due to it's being empty. Try again...");
            return false;
        }
        return onlyLetters(username);
    }

    /**
     * Helper recursive method for checkIfValidUsername() that checks if the username contains only letters
     *
     * @param str the string to check
     * @return true if the string contains only letters, false otherwise
     */
    private static boolean onlyLetters(String str) {
        if (str.length() == 0)
            return true;
        else if (str.charAt(0) <= 'z' && str.charAt(0) >= 'A')
            return onlyLetters(str.substring(1));
        System.out.println("The username is invalid due to having non-letter character! Try again...");
        return false;
    }

    // PART 4

    /**
     * Method that checks if the password is a palindrome
     * It checks password's length and calls the helper method to remove the brackets
     * Then it calls the helper recursive method to check if the password is a palindrome
     *
     * @param password1 the string to check
     * @return true if the string is a palindrome, false otherwise
     */
    public static boolean isPalindromePossible(String password1) {
        if (password1.length() < 8) {
            System.out.println("The string password is invalid due to being too short. Try again...");
            return false;
        }
        String temp = StackMethods.removeLetters(password1);
        if(temp.length() < 2) {
            System.out.println("The string password1 is invalid due to not having brackets. Try again...");
            return false;
        }
        password1 = removeBrackets(password1);
        if (password1.length() < 2) {
            System.out.println("The string password is invalid due to not having brackets. Try again...");
            return false;
        }
        return isPalindromePossibleHelper(password1, 0);
    }

    /**
     * Helper recursive method for isPalindromePossible() that checks if the password is a palindrome
     *
     * @param str   the string to check
     * @param index the index of the character to check
     * @return true if the string is a palindrome, false otherwise
     */
    public static boolean isPalindromePossibleHelper(String str, int index) {
        // base case: if the string is empty or has only one character, it's a palindrome
        if (str.length() <= 1)
            return true;
        // if the first and last characters are the same, check the rest of the string
        if (str.charAt(0) == str.charAt(str.length() - 1))
            return isPalindromePossibleHelper(str.substring(1, str.length() - 1), 0);
        // if the first and last characters are not the same, check if the string is a palindrome without the first character
        if (index == str.length() - 1) {
            // if the string's length is not even, it can have only one character in the middle
            // if the string's length is even, it can't have a character without a pair
            if (str.length() % 2 == 0) {
                System.out.println("The string password is invalid due to not being a palindrome. Try again...");
                return false;
            } else
                return isPalindromePossibleHelper(str.substring(0, str.length() - 1), 0);
        }
        // if the first and last characters are not the same, check if the string is a palindrome without the last character
        // then reconstruct the string without the character at index
        else if (str.charAt(index) == str.charAt(str.length() - 1)) {
            str = str.substring(0, index) + str.substring(index + 1, str.length() - 1);
            return isPalindromePossibleHelper(str, 0);
        } else
            // if the character at index and last characters are not the same,
            // check if the string is a palindrome without the character at next index
            return isPalindromePossibleHelper(str, index + 1);
    }

    /**
     * Helper method for isPalindromePossible() that removes all the brackets from the string
     *
     * @param str the string to remove the brackets from
     * @return the string without the brackets
     */
    public static String removeBrackets(String str) {
        StringBuilder newStr = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '(' && str.charAt(i) != ')' && str.charAt(i) != '[' && str.charAt(i) != ']' && str.charAt(i) != '{' && str.charAt(i) != '}')
                newStr.append(str.charAt(i));
        }
        return newStr.toString();
    }

    // PART 5

    /**
     * Method that checks if the password is divisible by 4, 17, and 29
     * If user doesn't enter any denominations, it calls method with the same name using default denominations
     *
     * @param password2 the integer password to check
     * @return true if the integer password is divisible by 4, 17, and 29, false otherwise
     */
    public static boolean isExactDivision(int password2) {
        int[] denominations = {4, 17, 29};
        return isExactDivision(password2, denominations);
    }

    /**
     * Method that checks if the password is divisible by the denominations
     * It checks password's length and calls the helper method to check if the password is divisible by the denominations
     *
     * @param password2     the integer password to check
     * @param denominations the denominations to check if the password is divisible by
     * @return true if the integer password is divisible by the denominations, false otherwise
     */
    public static boolean isExactDivision(int password2, int[] denominations) {
        if (denominations.length == 0)
            return isExactDivision(password2);

        if (password2 < 10 || password2 > 10000) {
            System.out.println("The integer password is invalid due to being out of range. Try again...");
            return false;
        }
        if (!sorter(denominations))
            return false;
        if (!isExactDivisionHelper(password2, denominations, 0)) {
            System.out.println("The integer password is invalid due to not being divisible by any of the denominations. Try again...");
            return false;
        }
        return true;
    }

    /**
     * Helper method for isExactDivision() that sorts the denominations array in ascending order
     * Lower denominations will be checked first
     *
     * @param arr the array to sort
     */
    private static boolean sorter(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 1) {
                System.out.println("The integer password is invalid due to having a denomination less than 1. Try again...");
                return false;
            }
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return true;
    }

    /**
     * Helper recursive method for isExactDivision() that checks if the password is divisible by the denominations
     *
     * @param password2     the integer password to check
     * @param denominations the denominations to check if the password is divisible by
     * @param index         the index of the denomination to check
     * @return true if the integer password is divisible by the denominations, false otherwise
     */
    private static boolean isExactDivisionHelper(int password2, int[] denominations, int index) {
        if (password2 < 0)
            return false;
        else if (password2 % denominations[index] == 0)
            return true;
        else if (index == denominations.length - 1)
            return isExactDivisionHelper(password2 - denominations[index - 1], denominations, 0);
        else return isExactDivisionHelper(password2, denominations, index + 1);
    }
}