import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws Exception {
        Trie dict = new Trie();
        try {
            Scanner sn = new Scanner(new File(System.getProperty("user.dir") +
                    "\\bin\\dict.txt"));

            while (sn.hasNext())
                dict.insert(sn.next());

            sn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(dict.size());

        System.out.println(dict.allWordsPrefix("s").length);

    }

}
