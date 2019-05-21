package spellchecker;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.*;
import java.io.*;

/**
 * @author daniilbolotov
 * 21.05.2019
 * This is the first version of the implementation of the SpellCheck interface.
 * This class contains the simplest algorithm that searches for a word in our dictionary.
 * The class works successfully
 * <p>
 * However, I need to add some constructors, and also create the new Dictionary class for processing dictionaries.
 * <p>
 * In developing this class, I used the article https://habr.com/ru/post/105450/
 * <p>
 * In the next version, I'm going to implement this interface using a new algorithm that uses Levenshtein distance
 */
public class SpellCheckerImpl implements SpellChecker {

    private HashMap dictionary;
    private List<String> suggested;
    private BufferedReader in;
    private final String alpha;
    private final char[] alphabet;
    //TODO : create some constructors which can create dictionaries from given URL
    //defualt realization
    SpellCheckerImpl(String path, String language) throws IOException {
        alpha = LanguageSupport.getLang(language);

        alphabet = alpha.toCharArray();

        dictionary = new HashMap();
        in = new BufferedReader(new FileReader(path));
        uploadDictionary();
        checkDictionary();
    }


    public SpellCheckerImpl(HashMap dictionary, String language) {
        alpha = LanguageSupport.getLang(language);
        alphabet = alpha.toCharArray();

        this.dictionary = dictionary;

    }

    @SuppressWarnings("unchecked")
    public void addToDictionary(String word) {
        if (!dictionary.containsKey(word)) {
            dictionary.put(word, null);
        }
    }

    private void checkDictionary() throws IOException {
        if (dictionary.isEmpty()) {
            throw new IOException("Your dictionary is empty");
        }
    }


    @SuppressWarnings("unchecked")
    private void uploadDictionary() throws IOException {

        try {
            String line;
            line = in.readLine();
            while ((line) != null) {
                String word = line;
                dictionary.put(word, null);
                line = in.readLine();
            }


        } catch (IOException e) {
            System.out.println("Your path to dictionary is incorrect");
        }

    }

    @Override
    public boolean checkWord(String word) {

        return dictionary.containsKey(word);
    }

    //ALGORITHM
    @Override
    public void suggestWords(String word) {

        suggested = new ArrayList<String>();


        StringBuilder builder;

        for (int i = 0; i < word.length(); i++) {

            if (!checkWord(word)) {
                builder = new StringBuilder(word);

                StringBuilder modification = builder.deleteCharAt(i);
                String modified = modification.toString();

                addToList(modified);

            }
        }

        for (int i = 0; i < word.length(); i++) {
            for (int j = 0; j < alphabet.length; j++) {

                if (!checkWord(word)) {
                    builder = new StringBuilder(word);

                    StringBuilder tempWord = builder.deleteCharAt(i);
                    StringBuilder inserModWord = builder.insert(i, alphabet[j]);
                    String modified = tempWord.toString();

                    addToList(modified);

                }
            }
        }


        for (int i = 0; i < word.length() + 1; i++) {
            for (int j = 0; j < alphabet.length; j++) {
                if (!checkWord(word)) {
                    builder = new StringBuilder(word);

                    StringBuilder iModifiedWord = builder.insert(i, alphabet[j]);
                    String modified = iModifiedWord.toString();

                    addToList(modified);


                }
            }
        }


        for (int i = 0; i < word.length() - 1; i++) {
            if (!checkWord(word)) {
                builder = new StringBuilder(word);

                char first = builder.charAt(i), second = builder.charAt(i + 1);
                builder.setCharAt(i, second);
                builder.setCharAt(i + 1, first);
                String modified = builder.toString();

                addToList(modified);

            }
        }


        String combined = "";
        for (int i = 0; i < word.length() - 1; i++) {
            if (!checkWord(word)) {
                String sLeft = word.substring(0, i);
                String sRight = word.substring(i, word.length());
                if ((dictionary.containsKey(sLeft)) && (dictionary.containsKey(sRight))) {
                    combined = sLeft + " " + sRight;

                    if (!suggested.contains(combined)) {
                        suggested.add(combined);


                        checkWord(word);
                    }
                }
            }
        }


        System.out.println("List of suggested words: " + suggested);
    }

    private void addToList(String modified) {
        if ((dictionary.containsKey(modified)) && (!suggested.contains(modified))) {

            suggested.add(modified);
        }
    }
}
