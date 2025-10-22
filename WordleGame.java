/**
 * Fully working Wordle game
 * Author: Mushtariy I
 * Purpose: Play Wordle in terminal with user input
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.List;

public class WordleGame {

    int guessNum;
    String secretWord;
    HashMap<String, String> guesses;
    boolean Validation;
    Scanner scan;

    public WordleGame() {
        this.scan = new Scanner(System.in);
    }

    // Interactive version (no parameters) - for actual gameplay
    public String MakeGuess() {
        Dictionary dic = new Dictionary();
        boolean not_valid = true;
        String word = "";

        while (not_valid) {
            System.out.println("Enter your guess: ");
            word = scan.nextLine();

            if (dic.isValidWord(word)) {
                not_valid = false;
            } else {
                System.out.println("Word not in list.");
            }
        }

        // Call the overloaded method to process the guess
        MakeGuess(word);
        return word;
    }

    // Overloaded version (with parameter) - for testing
    public String MakeGuess(String word) {
        Dictionary dic = new Dictionary();
        
        if (!dic.isValidWord(word)) {
            return "Word not in list.";
        }
        
        Feedback feed = new Feedback(word, secretWord);
        this.guesses.put(word, feed.getPattern());
        this.Validation = feed.getValidation();
        this.guessNum++;
        
        return feed.getPattern();
    }

    public String getSecretWord() {
        Dictionary dic = new Dictionary();
        return dic.getRandomWord();
    }

    public boolean isGameOver() {
        if (Validation || guessNum >= 6) {
            return true;
        }
        return false;
    }

    public void startGame() {
        this.guesses = new HashMap<String, String>();
        this.secretWord = getSecretWord();
        this.guessNum = 0;
        this.Validation = false;
    }

    public static void main(String[] args) {
        WordleGame game = new WordleGame();
        game.startGame();
        System.out.println("Welcome to Wordle");
        System.out.println("Make a guess and you shall receive a corresponding string as the pattern.");
        System.out.println("Each letter in the pattern will either be 'G','Y' or 'B'");
        System.out.println("G = correct letter in correct spot");
        System.out.println("Y = correct letter in wrong spot");
        System.out.println("B = letter not in word");
        System.out.println("You have 6 guesses good luck!");

        while (!(game.isGameOver())) {
            System.out.println("_________________________");
            game.MakeGuess();
            System.out.println("Number of guesses: " + game.guessNum);
            List<String> keyList = new ArrayList<>(game.guesses.keySet());
            List<String> valueList = new ArrayList<>(game.guesses.values());
            for (int i = 0; i < keyList.size(); i++) {
                System.out.println(keyList.get(i) + ": " + valueList.get(i));
            }
        }

        if (game.Validation) {
            System.out.println("Congratulations! You guessed the word!");
        } else {
            System.out.println("Game over! Better luck next time.");
        }
        System.out.println(game.secretWord + " was the secret word.");
        game.scan.close();
    }
}
