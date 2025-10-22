public class Feedback {

    String guess;
    String pattern;
    boolean validation;

    public Feedback(String guess, String secretWord){
        this.guess = guess.toLowerCase();
        this.pattern = getPattern(this.guess, secretWord.toLowerCase());
        this.validation = isCorrect(pattern);
    }

    public static String getPattern(String guess, String secretWord) {
        char[] pattern = new char[5];
        boolean[] secretUsed = new boolean[5];

        // First pass: mark 'G' for correct letters in correct spot
        for (int i = 0; i < 5; i++) {
            if (guess.charAt(i) == secretWord.charAt(i)) {
                pattern[i] = 'G';
                secretUsed[i] = true;
            } else {
                pattern[i] = '\0'; // placeholder
            }
        }

        // Second pass: mark 'Y' for correct letters in wrong spot
        for (int i = 0; i < 5; i++) {
            if (pattern[i] == 'G') continue;

            for (int j = 0; j < 5; j++) {
                if (!secretUsed[j] && guess.charAt(i) == secretWord.charAt(j)) {
                    pattern[i] = 'Y';
                    secretUsed[j] = true;
                    break;
                }
            }
        }

        // Third pass: mark 'B' for letters not in word
        for (int i = 0; i < 5; i++) {
            if (pattern[i] == '\0') pattern[i] = 'B';
        }

        return new String(pattern);
    }

    public static boolean isCorrect(String pattern){
        return pattern.equals("GGGGG");
    }

    public String getGuess(){
        return guess;
    }

    public String getPattern(){
        return pattern;
    }

    public boolean getValidation(){
        return validation;
    }
}
