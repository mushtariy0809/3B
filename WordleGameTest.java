/**
 * Fully testing Wordle game
 * Author: Mushtariy I
 * Purpose: to test the game to ensure it runs smoothly 
 */




import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.HashMap;

class WordleGameTest {

    WordleGame game;

    @BeforeEach
    void setUp() {
        game = new WordleGame();
        game.guesses = new HashMap<>();
        game.secretWord = "world";
        game.guessNum = 0;
        game.Validation = false;
    }

    @Test
    void testCorrectGuess() {
        String feedback = game.MakeGuess("world");
        assertEquals("GGGGG", feedback, "All letters should be correct");
        assertTrue(game.isGameOver(), "Game should be over after correct guess");
        assertEquals(1, game.guessNum, "Guess count should be 1");
    }

    @Test
    void testIncorrectGuess() {
        String feedback = game.MakeGuess("apple");
        assertFalse(game.isGameOver(), "Game should not be over after one wrong guess");
        assertEquals(1, game.guessNum, "Guess count should be 1");
        assertFalse(game.Validation, "Validation should be false");
    }

    @Test
    void testPartialCorrectGuess() {
        String feedback = game.MakeGuess("words");
        assertFalse(game.isGameOver(), "Game should not be over");
        assertEquals(1, game.guessNum, "Guess count should be 1");
        assertFalse(game.Validation, "Validation should be false");
    }

    @Test
    void testMaxGuesses() {
        for (int i = 0; i < 6; i++) {
            game.MakeGuess("apple");
        }
        assertTrue(game.isGameOver(), "Game should be over after 6 guesses");
        assertEquals(6, game.guessNum, "Guess count should be 6");
    }

    @Test
    void testInvalidWord() {
        String feedback = game.MakeGuess("xxxxx");
        assertEquals("Word not in list.", feedback, "Invalid word should return error message");
        assertEquals(0, game.guessNum, "Guess count should still be 0");
        assertFalse(game.isGameOver(), "Game should not be over");
    }

    @Test
    void testMultipleGuesses() {
        game.MakeGuess("apple");
        assertEquals(1, game.guessNum);
        
        game.MakeGuess("bread");
        assertEquals(2, game.guessNum);
        
        game.MakeGuess("world");
        assertEquals(3, game.guessNum);
        assertTrue(game.Validation, "Should have guessed correctly");
        assertTrue(game.isGameOver(), "Game should be over");
    }

    @Test
    void testGuessesStoredCorrectly() {
        game.MakeGuess("apple");
        assertTrue(game.guesses.containsKey("apple"), "Guess should be stored");
        
        game.MakeGuess("bread");
        assertTrue(game.guesses.containsKey("bread"), "Second guess should be stored");
        
        assertEquals(2, game.guesses.size(), "Should have 2 guesses stored");
    }

    @Test
    void testGameOverAtSixGuesses() {
        assertFalse(game.isGameOver(), "Game should not be over at start");
        
        for (int i = 0; i < 5; i++) {
            game.MakeGuess("apple");
            assertFalse(game.isGameOver(), "Game should not be over before 6 guesses");
        }
        
        game.MakeGuess("apple");
        assertTrue(game.isGameOver(), "Game should be over at exactly 6 guesses");
    }

    @Test
    void testStartGame() {
        game.startGame();
        
        assertNotNull(game.secretWord, "Secret word should be set");
        assertEquals(0, game.guessNum, "Guess count should be 0");
        assertFalse(game.Validation, "Validation should be false");
        assertNotNull(game.guesses, "Guesses map should be initialized");
        assertTrue(game.guesses.isEmpty(), "Guesses map should be empty");
    }
}
