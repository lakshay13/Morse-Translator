package com.MorseTestPackage;

import com.MorsePackage.MorseTranslator;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

/**
 * Implementation Notes:
 *
 * Step 1: Obtain the word list from dictionary
 * Step 2: Obtain the morse input from the file specified.
 * Step 3: Obtain the matching words in dictionary for the morse input
 * Step 4: Check word obtained is the same as expected.
 *
 * Created by @LakshaySuri (lakshay13@gmail.com) on 05/11/16.
 */
public class MorseTranslationTest {

    private static final Logger LOGGER = Logger.getLogger(MorseTranslationTest.class.toString());

    /**
     * Test Morse translation for the word "and"
     *
     * @throws IOException if morse input could not be read from file.
     */
    @Test
    public void testMorseTranslation1() throws IOException {

        List<String> dictionaryWordList = MorseTranslator.getWordsFromFile();

        String path = "src/test/java/com/MorseTestPackage/fixtures/morsedtest1.txt";
        List<String> morsedInput = getMorsedInputFromFile(path);

        List<String> listOfWordsObtained = MorseTranslator.getMatchedHumanWords(dictionaryWordList, morsedInput);
        LOGGER.info("Found Morse Translation===>" + listOfWordsObtained.get(0));

        assertEquals(1, listOfWordsObtained.size());
        assertEquals("and", listOfWordsObtained.get(0));
        LOGGER.info("Test Morse Translation 1 finished");
    }

    /**
     * Test Morse translation for the word "the"
     *
     * @throws IOException if morse input could not be read from file.
     */
    @Test
    public void testMorseTranslation2() throws IOException {

        List<String> dictionaryWordList = MorseTranslator.getWordsFromFile();

        String path = "src/test/java/com/MorseTestPackage/fixtures/morsedtest2.txt";
        List<String> morsedInput = getMorsedInputFromFile(path);

        List<String> listOfWordsObtained = MorseTranslator.getMatchedHumanWords(dictionaryWordList, morsedInput);
        LOGGER.info("Found Morse Translation===>" + listOfWordsObtained.get(0));

        assertEquals(1, listOfWordsObtained.size());
        assertEquals("the", listOfWordsObtained.get(0));
        LOGGER.info("Test Morse Translation 2 finished");
    }


    private static List<String> getMorsedInputFromFile(String path)  throws IOException {

        Path filePath = new File(path).toPath();
        Charset charset = Charset.defaultCharset();

        List<String> list = null;
        try {
            list = Files.readAllLines(filePath, charset);
        } catch (IOException e) {
            LOGGER.info("Exception raised while reading from file" + e.getMessage());
        }
        return list;
    }
}
