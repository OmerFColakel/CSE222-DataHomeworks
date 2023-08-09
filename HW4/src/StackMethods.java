package src;
import java.util.Stack;

class StackMethods {
    //  PART 2

    /**
     * Method that checks if the password contains the username spirit
     *
     * @param username  is the username of the user
     * @param password1 is the first password of the user
     * @return true if the password contains the username spirit, false otherwise
     */
    public static boolean containsUserNameSpirit(String username, String password1) {
        if (username.length() < 1) {
            System.out.println("The string username is invalid due to being too short. Try again...");
            return false;
        }
        if (password1.length() < 8) {
            System.out.println("The string password 1 is invalid due to being too short. Try again...");
            return false;
        }
        password1 = RecursiveMethods.removeBrackets(password1);
        if (password1.length() < 2) {
            System.out.println("The string password1 is invalid due to not having any brackets! Try again...");
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < username.length(); i++)
            stack.push(username.charAt(i));
        for (int i = 0; i < username.length(); i++) {
            for (int j = 0; j < password1.length(); j++)
                if (password1.charAt(j) == stack.peek())
                    return true;
            stack.pop();
        }
        System.out.println("The string password 1 is invalid due to not containing the username spirit! Try again...");
        return false;
    }


    //  PART 3

    /**
     * Method that checks if the password has the same number of opening and closing brackets and the brackets are in the right order
     *
     * @param password1 is the password of the user
     * @return true if the password is balanced, false otherwise
     */
    public static boolean isBalancedPassword(String password1) {
        if (password1.length() < 8) {
            System.out.println("The string password 1 is invalid due to being too short. Try again...");
            return false;
        }
        int index = 0;
        Stack<Character> stack = new Stack<>();
        password1 = removeLetters(password1);
        if (password1.length() < 2) {
            System.out.println("The string password 1 is invalid due to not having any brackets! Try again...");
            return false;
        }
        while (index < password1.length()) {
            if (password1.charAt(index) == '(' || password1.charAt(index) == '[' || password1.charAt(index) == '{')
                stack.push(password1.charAt(index));
            else if (stack.isEmpty()) {
                System.out.println("The string password1 is invalid due to not being balanced! Try again...");
                return false;
            } else if (password1.charAt(index) == ')' && stack.peek() == '(')
                stack.pop();
            else if (password1.charAt(index) == ']' && stack.peek() == '[')
                stack.pop();
            else if (password1.charAt(index) == '}' && stack.peek() == '{')
                stack.pop();
            else {
                System.out.println("The string password1 is invalid due to not being balanced! Try again...");
                return false;
            }

            ++index;
        }
        return stack.isEmpty();
    }

    /**
     * Helper method that removes all the letters from the password
     *
     * @param password1 is the password of the user
     * @return the password without the letters
     */
    public static String removeLetters(String password1) {
        StringBuilder newStr = new StringBuilder();

        for (int i = 0; i < password1.length(); i++)
            if (password1.charAt(i) == '(' || password1.charAt(i) == ')'
                    || password1.charAt(i) == '[' || password1.charAt(i) == ']'
                    || password1.charAt(i) == '{' || password1.charAt(i) == '}') {

                newStr.append(password1.charAt(i));
            }
        return newStr.toString();
    }


}