import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class BlackJackGame {
    private Deck deck;
    private List<Card> dealerCards;
    private List<Card> playerCards;

    public BlackJackGame() {
        deck = new Deck();
        dealerCards = new ArrayList<>();
        playerCards = new ArrayList<>();
    }

    public void startGame() {
        dealerCards.add(deck.drawCard());
        dealerCards.add(deck.drawCard());
        playerCards.add(deck.drawCard());
        playerCards.add(deck.drawCard());

        boolean gameOver = false;

        while (!gameOver) {
            System.out.println("Dealer: " + dealerCards.get(0) + " XX");
            System.out.print("Player: ");
            for (Card card : playerCards) {
                System.out.print(card + " ");
            }
            System.out.println();

            String choice = getPlayerChoice();
            if (choice.equalsIgnoreCase("H")) { // Hit
                playerCards.add(deck.drawCard());
                if (calculateScore(playerCards) > 21) {
                    gameOver = true;
                    System.out.println("Player Busted. Dealer Wins...");
                    revealCards();
                }
            } else if (choice.equalsIgnoreCase("S")) { // Stand
                gameOver = true;
                revealCards();

                int dealerScore = calculateScore(dealerCards);
                int playerScore = calculateScore(playerCards);

                if (dealerScore > 21) {
                    System.out.println("Dealer Busted. Player Wins!");
                } else if (playerScore > dealerScore) {
                    System.out.println("Player Wins!");
                } else if (playerScore < dealerScore) {
                    System.out.println("Dealer Wins...");
                } else {
                    System.out.println("It's a tie.");
                }
            }
        }
    }

    private void revealCards() {
        System.out.print("Dealer: ");
        for (Card card : dealerCards) {
            System.out.print(card + " ");
        }
        System.out.println();
        System.out.print("Player: ");
        for (Card card : playerCards) {
            System.out.print(card + " ");
        }
        System.out.println();
    }

    private String getPlayerChoice() {
        Scanner scanner = new Scanner(System.in);
        String choice = "";

        while (!choice.equalsIgnoreCase("H") && !choice.equalsIgnoreCase("S")) {
            System.out.print("Hit or Stand? (H/S): ");
            try {
                choice = scanner.nextLine().toUpperCase();
                if (!choice.equalsIgnoreCase("H") && !choice.equalsIgnoreCase("S")) {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please enter 'H' or 'S'.");
            }
        }

        return choice;
    }

    private int calculateScore(List<Card> cards) {
        int score = 0;
        int aceCount = 0;

        for (Card card : cards) {
            score += card.getValue();
            if (card.getValue() == 1) {
                aceCount++;
            }
        }

        while (score < 21 && aceCount > 0) {
            score += 10;
            aceCount--;
        }

        return score;
    }
}
