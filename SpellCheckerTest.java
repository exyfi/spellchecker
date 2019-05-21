package ru.ifmo.rain.bolotov.spellchecker;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class SpellCheckerTest implements Runnable {
    private BufferedReader br;
    private StringTokenizer in;
    private PrintWriter out;

    public static void main(String[] args) {
        new Thread(new SpellCheckerTest()).run();
    }


    public String nextToken() throws IOException {
        while (in == null || !in.hasMoreTokens()) {
            in = new StringTokenizer(br.readLine());
        }

        return in.nextToken();
    }

    /*I'm presenting default realization
      In next version I'm going to create reading from console arguments to read path to Dictionary and read language type
     */
    public void solve() throws IOException {
        //TODO : create reading from console arguments to read path to Dictionary and read language
        // String path=nextToken().toLowerCase();


        String word = nextToken().toLowerCase();
        SpellChecker dictionary = new SpellCheckerImpl("/Users/daniilbolotov/Desktop/java-advanced-2019/java/ru/ifmo/rain/bolotov/spellchecker/eng.txt", "EN");

        if (dictionary.checkWord(word)) {
            out.println("Word is correct: " + word);
        } else {
            dictionary.suggestWords(word);
            out.println("Perhaps, you misspelled");
        }
    }

    public void run() {
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}


