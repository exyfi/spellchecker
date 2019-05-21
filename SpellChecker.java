package ru.ifmo.rain.bolotov.spellchecker;

/**
 * @author Daniil Bolotov
 * @version 1.0
 * An interface that implements classes that check spelling words
 */
public interface SpellChecker {
    /**
     *
     * @param word
     * @return true if dictionary contains this word else return false
     */
    boolean checkWord(String word);

    /**
     *
     * @param word word which User has written to check
     * this method invokes algorithms to find similar words which contain in dictionary
     * after search returns List with suggested words
     */
    void suggestWords(String word);



}
