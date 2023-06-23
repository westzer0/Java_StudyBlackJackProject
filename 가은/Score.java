import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
public class Score {
    private int score;
    private int numAces;

    public Score() {
        score = 0;
        numAces = 0;
    }

    public int getScore() {
        return score;
    }

    public void calculateScore(List<String> cards) {
        score = 0;
        numAces = 0;

        for (String card : cards) {
            String cardValue = card.substring(0, card.length() - 3);

            if (cardValue.equals("A")) {
                score += 11;
                numAces++;
            } else if (cardValue.equals("K") || cardValue.equals("Q") || cardValue.equals("J")) {
                score += 10;
            } else {
                score += Integer.parseInt(cardValue);
            }
        }

        while (score > 21 && numAces > 0) {
            score -= 10;
            numAces--;
        }
    }
}