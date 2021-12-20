import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.io.File;
import java.io.IOException;

public class App {

    private static Trie userTrie;
    private static boolean trieCreated = false;

    public static void main(String[] args) throws Exception {

        Scanner user_input = new Scanner(System.in);
        String choice = "";
        int choice_num = 0;

        do {
            printMenu();
            choice = user_input.next();
            if (!isNumeric(choice))
                choice = "0";

            if (!validate(Integer.parseInt(choice)))
                System.out.println("\n*** WRONG CHOICE.. Try again ***\n");

            else {
                choice_num = Integer.parseInt(choice);
                if (choice_num == 1)
                    if (!trieCreated) {
                        userTrie = createEmptyTrie();
                        trieCreated = true;
                    } else
                        System.out.println(
                                "Trie is already created.. you should end the program if you want to create another trie");
                else if (choice_num == 2)
                    if (!trieCreated) {
                        userTrie = createInitialTrie();
                        trieCreated = true;
                    } else
                        System.out.println(
                                "Trie is already created.. you should end the program if you want to create another trie");

                else if (choice_num == 3)
                    insertWord();

                else if (choice_num == 4)
                    deleteWord();

                else if (choice_num == 5)
                    allPrefixWords();

                else if (choice_num == 6)
                    size();
            }

        } while (choice_num != 7);

        user_input.close();
    }

    private static void size() {
        if (!trieCreated) {
            System.out.println("Trie does not exist, try creating a trie first..");
            return;
        }

        System.out.println("Size of the Trie is: " + userTrie.size());

    }

    private static void allPrefixWords() {
        if (!trieCreated) {
            System.out.println("Trie does not exist, try creating a trie first..");
            return;
        }

        System.out.print("Enter a prefix: ");
        Scanner in = new Scanner(System.in);
        String prefix = in.next().toUpperCase();
        String[] words = userTrie.allWordsPrefix(prefix);

        System.out.print("Found the following words: ");
        for (int i = 0; i < words.length; i++) {
            System.out.print(words[i]);
            if (i != words.length - 1)
                System.out.print(", ");
        }
        System.out.println();
    }

    private static void deleteWord() {
        if (!trieCreated) {
            System.out.println("Trie does not exist, try creating a trie first..");
            return;
        }

        System.out.print("Enter a word to delete: ");
        Scanner in = new Scanner(System.in);
        String word = in.next().toUpperCase();

        userTrie.delete(word);

    }

    private static void insertWord() {
        if (!trieCreated) {
            System.out.println("Trie does not exist, try creating a trie first..");
            return;
        }

        System.out.print("Enter a word to insert: ");
        Scanner in = new Scanner(System.in);
        String word = in.next().toUpperCase();

        userTrie.insert(word);
        System.out.println("Word inserted!");
    }

    private static Trie createInitialTrie() {
        Trie trie = new Trie();

        ArrayList<String> charsList = new ArrayList<>();

        System.out.print("Enter your list of letters> ");

        Scanner in = new Scanner(System.in);
        String[] letters = in.nextLine().split("");
        for (String letter : letters)
            charsList.add(letter.toUpperCase());
        // in.close();

        try {
            Scanner dict = new Scanner(new File(System.getProperty("user.dir") +
                    "\\dict.txt"));

            while (dict.hasNext()) {
                String word = dict.next();
                if (word.length() <= charsList.size()) {
                    if (isPermute(charsList, word)) {
                        trie.insert(word);
                    }
                }
            }

            dict.close();
            System.out.print("Trie Created!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return trie;
    }

    private static Trie createEmptyTrie() {

        System.out.println("Empty Trie is created !");
        return new Trie();
    }

    public static void printMenu() {
        System.out.println("\n\n***************************************************");
        System.out.println("******************   Trie Project   ***************");
        System.out.println("Choices:");
        System.out.println("    1) Create an Empty Trie");
        System.out.println("    2) Create a trie with initial letters");
        System.out.println("    3) Insert a word");
        System.out.println("    4) Delete a word");
        System.out.println("    5) List all words that begin with a prefix");
        System.out.println("    6) Size of the trie");
        System.out.println("    7) End");
        System.out.println("***************************************************\n");
        System.out.print("Enter your choice>> ");
    }

    public static boolean validate(int input) {
        if (input != 1 && input != 2 && input != 3 && input != 4 && input != 5 && input != 6 && input != 7)
            return false;
        return true;
    }

    public static boolean isPermute(ArrayList<String> chars, String word) {
        String[] wordlist = word.split("");
        String charsword = chars.stream().collect(Collectors.joining(""));

        for (int i = 0; i < wordlist.length; i++) {
            int wordcount = countLetters(word, wordlist[i].charAt(0));
            int charscount = countLetters(charsword, wordlist[i].charAt(0));

            if (charscount < wordcount)
                return false;

        }
        return true;
    }

    private static int countLetters(String word, char letter) {
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter)
                count++;
        }
        return count;
    }

    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
