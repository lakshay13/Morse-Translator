package com.MorsePackage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.logging.Logger;

/**
 * Implementation Notes:
 *
 * This class is used to compute the human words from a given dictionary that
 * matches the given morsed input. The following steps are involved:
 * --> Get the list of words from dictionary
 * --> Get the morse input.
 * --> Obtain the map of dictionary words and their morsed values
 * --> Search the morse input in the map obtained.
 *
 *  Created by @LakshaySuri (lakshay13@gmail.com) on 05/11/16.
 */
public class MorseTranslator {

    private static final Logger LOGGER = Logger.getLogger(MorseTranslator.class.toString());

    private MorseTranslator() {

    }

    public static MorseTranslator getNewInstance() {
        return new MorseTranslator();
    }

    public static void main(String[] args) throws IOException {

        // get the return list of human words contained in the dictionary.
        List<String> dictionaryWordList = getWordsFromFile();
        LOGGER.info("==> Words fetched from the dictionary file");

        // get the morsed code input given.
        List<String> morsedInput = getMorsedInputFromFile();
        LOGGER.info("==> Morsed Coded input fetched from the file");

        // get the words that match with morsed coded input in the dictionary
        List<String> listOfWordsObtained = getMatchedHumanWords(dictionaryWordList, morsedInput);
        LOGGER.info("Number of human words that represent the morsed pattern from the dictionary==>"
                + listOfWordsObtained.size());

        // Display the list of human words obtained,
        if (!listOfWordsObtained.isEmpty()) {
            LOGGER.info("Human words that represent the given morsed pattern ==>" + morsedInput + "from the dictionary");
            for (int number = 1; number <= listOfWordsObtained.size(); number++) {
                LOGGER.info(number + ": " + listOfWordsObtained.get(number) + " ");
            }
        }
    }

    /**
     * This method does two operations:
     *  1. It obtains a map of word in dictionary with its morsed code.
     *  2. It calls a method to check if the morsed code obtained above
     *  in the map matches with input morsed code.
     *
     * @param dictionaryWordList: list of words from dictionary.
     * @param morsedInput: input that needs to be converted to a human word.
     * @return list of human words that match with morsed input.
     */
    public static List<String> getMatchedHumanWords(List<String> dictionaryWordList, List<String> morsedInput) {

        char[] englishCharacters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
                'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        String[] morseCodedCharacters = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---",
                "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-",
                "-.--", "--.."};

        Map<String, String> mapWordMorse = new LinkedHashMap<String, String>();
        StringBuilder morseFormed;

        for (String word : dictionaryWordList) {
            String wordObtained = word.toUpperCase();
            morseFormed = new StringBuilder();
            for (int x = 0; x < wordObtained.length(); x++) {
                for (int y = 0; y < englishCharacters.length; y++) {
                    if (englishCharacters[y] == wordObtained.charAt(x)) {
                        morseFormed.append(morseCodedCharacters[y]);
                        break;
                    }
                }
            }
            mapWordMorse.put(word, morseFormed.toString());
        }
        LOGGER.info("==> Entered the morsed key in the map for all the words in dictionary");

        List<String> listOfWordsObtained = null;
        for(String morsedInputValue: morsedInput) {
            listOfWordsObtained = getListOfHumanWords(mapWordMorse, morsedInputValue);
        }

        return listOfWordsObtained;
    }

    /**
     * This method is used to get the list of human words that matches with morsed input.
     * by comparing the morsed code values in map with the input value.
     *
     * @param mapWordMorse map of words and their morse code values
     * @param morsedInputValue input morse value whose human word is to be found.
     * @return list of matched human words.
     */
    public static List<String> getListOfHumanWords(Map<String, String> mapWordMorse, String morsedInputValue) {
        List<String> listOfWordsObtained = new LinkedList<String>();

        for (String key : mapWordMorse.keySet()) {
            if (morsedInputValue.equals(mapWordMorse.get(key))) {
                listOfWordsObtained.add(key);
            }
        }

        return listOfWordsObtained;
    }

    /**
     * This method obtains the list of possible human words from the dictionary.
     *
     * @return the list of possible human words obtained.
     * @throws IOException if file could not be read.
     */
    public static List<String> getWordsFromFile() throws IOException {

        Path filePath = new File("src/main/resources/google1000.txt").toPath();
        Charset charset = Charset.defaultCharset();
        return Files.readAllLines(filePath, charset);
    }

    /**
     * This method obtains the list of morsed input for which human words have to be found.
     *
     * @return list of morsed input.
     * @throws IOException if file could not be read.
     */
    public static List<String> getMorsedInputFromFile()  throws IOException {

        Path filePath = new File("src/main/resources/morsedInput.txt").toPath();
        Charset charset = Charset.defaultCharset();
        return Files.readAllLines(filePath, charset);
    }
}
