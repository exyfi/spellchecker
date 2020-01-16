import org.exyfi.spellchecker.implementation.SpellChecker;
import org.exyfi.spellchecker.implementation.SpellCheckerImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * It's a test to show you how to org.exyfi.spellchecker.implementation.SpellChecker works
 * There are not tests which use jUnit
 * @author daniilbolotov
 */
public class SpellCheckerTest {

    private static StringTokenizer in;

    public static void main(String[] args) {

        startSpellCheck();

    }

    private static String nextToken(BufferedReader br) throws IOException {
        while (in == null || !in.hasMoreTokens()) {
            in = new StringTokenizer(br.readLine());
        }

        return in.nextToken();
    }

    public static void execute(String word) throws IOException {
        SpellChecker dictionary = new SpellCheckerImpl("eng.txt", "EN");

        if (dictionary.checkWord(word)) {
            System.out.println("Word is correct: " + word);
        } else {
            dictionary.suggestWords(word);
            System.out.println("Perhaps, you misspelled");
        }
    }

    public static void startSpellCheck() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));) {
            String word = nextToken(br);
            execute(word);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}


