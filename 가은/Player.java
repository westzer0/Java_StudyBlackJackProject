import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Player {
    private List<String> playerCards;

    public Player() {
        playerCards = new ArrayList<>();
    }

    public void addCard(String card) {
        playerCards.add(card);
    }

    public List<String> getPlayerCards() {
        return playerCards;
    }
}