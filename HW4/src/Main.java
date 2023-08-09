package src;
public class Main {
    public static void main(String[] args) {
        checkIfValidPasswordTest();

        isPalindromePossibleTest();

        isExactDivisionTest();

        isBalancedPasswordTest();

        containsUserNameSpiritTest();

        finalOutputs();

    }

    /**
     * This method tests the checkIfValidPassword method.
     */
    public static void checkIfValidPasswordTest() {
        System.out.println("checkIfValidPassword:");
        System.out.println(RecursiveMethods.checkIfValidUsername("abc"));               //  true
        System.out.println(RecursiveMethods.checkIfValidUsername("abc1"));              //  false
        System.out.println(RecursiveMethods.checkIfValidUsername(""));                  //  false
        System.out.println(RecursiveMethods.checkIfValidUsername("abc)"));              //  false
    }

    /**
     * This method tests the isPalindromePossible method.
     */
    public static void isPalindromePossibleTest() {
        System.out.println("\nisPalindromePossible:");
        System.out.println(RecursiveMethods.isPalindromePossible("abba"));             //  false
        System.out.println(RecursiveMethods.isPalindromePossible("abbaa"));            //  false
        System.out.println(RecursiveMethods.isPalindromePossible(""));                 //  false
        System.out.println(RecursiveMethods.isPalindromePossible("a"));                //  false
        System.out.println(RecursiveMethods.isPalindromePossible("{[(ecarcar)]}"));    //  true
        System.out.println(RecursiveMethods.isPalindromePossible("{ab[bac]aaba}"));    //  false
        System.out.println(RecursiveMethods.isPalindromePossible("{(abba)cac}"));      //  true
    }

    /**
     * This method tests the isExactDivision method.
     */
    public static void isExactDivisionTest() {
        int[] arr1 = {3, 5, 19, 20};
        int[] arr2 = {100, 10, 12, 2};
        System.out.println("\nisExactDivision:");
        System.out.println(RecursiveMethods.isExactDivision(75));                       //  true
        System.out.println(RecursiveMethods.isExactDivision(35));                       //  false
        System.out.println(RecursiveMethods.isExactDivision(54));                       //  true
        System.out.println(RecursiveMethods.isExactDivision(4));                        //  false
        System.out.println(RecursiveMethods.isExactDivision(10001));                    //  false
        System.out.println(RecursiveMethods.isExactDivision(9999));                     //  true
        System.out.println(RecursiveMethods.isExactDivision(9999, arr1));               //  true
        System.out.println(RecursiveMethods.isExactDivision(9998));                     //  true
        System.out.println(RecursiveMethods.isExactDivision(9999, arr2));               //  false
    }

    /**
     * This method tests the isBalancedPassword method.
     */
    public static void isBalancedPasswordTest() {
        System.out.println("\nisBalancedPassword:");
        System.out.println(StackMethods.isBalancedPassword("abc"));                         //  false
        System.out.println(StackMethods.isBalancedPassword("()"));                          //  false
        System.out.println(StackMethods.isBalancedPassword("(aaa)[aaa]{aa}"));              //  true
        System.out.println(StackMethods.isBalancedPassword("(aaaaaaa]"));                   //  false
        System.out.println(StackMethods.isBalancedPassword("(aa[aa)aa]"));                  //  false
        System.out.println(StackMethods.isBalancedPassword("{aa[aa]aa}"));                  //  true
        System.out.println(StackMethods.isBalancedPassword("aaaaaaaa(b)c"));                //  true
    }

    /**
     * This method tests the containsUserNameSpirit method.
     */
    public static void containsUserNameSpiritTest() {
        System.out.println("\ncontainsUserNameSpirit:");
        System.out.println(StackMethods.containsUserNameSpirit("gizem", "{[(abacaba)]}"));     // false
        System.out.println(StackMethods.containsUserNameSpirit("gokhan", "{[(abacaba)]}"));     //  true
    }

    /**
     * This method calls the check method with different parameters.
     */
    public static void finalOutputs() {
        int[] arr1 = {};
        int[] arr2 = {3, 7, 9};
        System.out.println("\n1: ");
        check("", "", 0, arr1);                             //  Username is empty.
        System.out.println("\n2: ");
        check("OmerFaruk", "[aaaaa]", 100, arr1);           //  Password1 is too short.
        System.out.println("\n3: ");
        check("OmerFaruk", "[aaaaaa]", 100, arr1);          //  Door is opening...
        System.out.println("\n4: ");
        check("OmerFaruk", "[bbbbbb]", 100, arr1);          //  Not contains username spirit.
        System.out.println("\n5: ");
        check("OmerFaruk", "[}bbbba]", 100, arr1);          //  Password1 is not balanced.
        System.out.println("\n6: ");
        check("OmerFaruk", "aaaaaaaaa", 100, arr1);         //  Password1 does not contain any brackets.
        System.out.println("\n7: ");
        check("OmerFaruk", "[aaaaaa]", 9, arr1);            //  Password2 is out of range.
        System.out.println("\n8: ");
        check("OmerFaruk", "[aaaaaa]", 10, arr1);           //  Not divisible by 4, 17, 29.
        System.out.println("\n9: ");
        check("OmerFaruk", "[aaaaaa]", 10000, arr1);        //  Door is opening...
        System.out.println("\n10: ");
        check("OmerFaruk", "[aaaaaa]", 10001, arr1);        //  Out of range.
        System.out.println("\n11: ");
        check("OmerFaruk", "[aaaaaa]", 100, arr2);          //  Not divisible by 3, 7, 9.
        System.out.println("\n12: ");
        check("sibelgulmez", "[rac()ecar]", 74, arr1);      //  Door is opening...
        System.out.println("\n13: ");
        check("", "[rac()ecar]", 74, arr1);                 //  The username is invalid
        System.out.println("\n14: ");
        check("sibel1", "[rac()ecar]", 74, arr1);           //  The username is invalid. It should have letters only.
        System.out.println("\n15: ");
        check("sibel", "pass[]", 74, arr1);                 //  The password1 is invalid. It should have at least 8 characters.
        System.out.println("\n16: ");
        check("sibel", "abcdabcd", 74, arr1);               //  The password1 is invalid. It should have at least 2 brackets.
        System.out.println("\n17: ");
        check("sibel", "[[[[]]]]", 74, arr1);               //  The password1 is invalid. It should have letters too.
        System.out.println("\n18: ");
        check("sibel", "[no](no)", 74, arr1);               //  The password1 is invalid. It should have at least 1 character from the username.
        System.out.println("\n19: ");
        check("sibel", "[rac()ecar]]", 74, arr1);           //  It should be balanced.
        System.out.println("\n20: ");
        check("sibel", "[rac()ecars]", 74, arr1);           //  Not a palindrome
        System.out.println("\n21: ");
        check("sibel", "[rac()ecar]", 5, arr1);             //  Range
        System.out.println("\n22: ");
        check("sibel", "[rac()ecar]", 35, arr1);            //  Not divisible by 4, 17, 29

    }

    /**
     * This method checks if the username and passwords are valid.
     *
     * @param username  is the username of the user.
     * @param password1 is the password1 of the user.
     * @param password2 is the password2 of the user.
     */
    public static void check(String username, String password1, int password2, int[] arr) {
        if (!RecursiveMethods.checkIfValidUsername(username))
            return;
        if (!StackMethods.containsUserNameSpirit(username, password1))
            return;
        if (!StackMethods.isBalancedPassword(password1))
            return;
        if (!RecursiveMethods.isPalindromePossible(password1))
            return;
        if (!RecursiveMethods.isExactDivision(password2, arr))
            return;
        System.out.println("The username and passwords are valid. The door is opening, please wait...");
    }


}