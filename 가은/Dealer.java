import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
public class Dealer {
    private List<String> dealerCards;

    public Dealer() {
        dealerCards = new ArrayList<>();
    }

    public void addCard(String card) {
        dealerCards.add(card);
    }

    public List<String> getDealerCards() {
        return dealerCards;
    }
}