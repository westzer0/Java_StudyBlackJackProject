import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
public class Card {
    private List<String> deck;
    private Random random;
    private Player player;
    private Dealer dealer;
    private Score playerScore;
    private Score dealerScore;

    public Card() {
        deck = new ArrayList<>(Arrays.asList(
                "A(♠)", "2(♠)", "3(♠)", "4(♠)", "5(♠)", "6(♠)", "7(♠)", "8(♠)", "9(♠)", "10(♠)", "J(♠)", "Q(♠)", "K(♠)",
                "A(♦)", "2(♦)", "3(♦)", "4(♦)", "5(♦)", "6(♦)", "7(♦)", "8(♦)", "9(♦)", "10(♦)", "J(♦)", "Q(♦)", "K(♦)",
                "A(♥)", "2(♥)", "3(♥)", "4(♥)", "5(♥)", "6(♥)", "7(♥)", "8(♥)", "9(♥)", "10(♥)", "J(♥)", "Q(♥)", "K(♥)",
                "A(♣)", "2(♣)", "3(♣)", "4(♣)", "5(♣)", "6(♣)", "7(♣)", "8(♣)", "9(♣)", "10(♣)", "J(♣)", "Q(♣)", "K(♣)"
        ));
        random = new Random();
        player = new Player();
        dealer = new Dealer();
        playerScore = new Score();
        dealerScore = new Score();
    }

    public void playGame() {
        try {
            shuffleDeck();
            dealInitialCards();
            playerTurn();
            dealerTurn();
            printResult();
            System.out.println("--------------------------------------------");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void shuffleDeck() {
        try {
            for (int i = 0; i < deck.size(); i++) {
                int j = random.nextInt(deck.size());
                String temp = deck.get(i);
                deck.set(i, deck.get(j));
                deck.set(j, temp);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while shuffling the deck.");
        }
    }

    private void dealInitialCards() {
        try {
            for (int i = 0; i < 2; i++) {
                dealer.addCard(deck.remove(deck.size() - 1));
                player.addCard(deck.remove(deck.size() - 1));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while dealing initial cards.");
        }
    }

    private void playerTurn() {
        try {
            while (true) {
                int playerScoreValue = playerScore.getScore();
                System.out.println("-------- Jack's Blackjack Game --------");
                System.out.println("Dealer: [" + dealer.getDealerCards().get(1) + ", XX]");
                System.out.println("Players: " + player.getPlayerCards());

                if (playerScoreValue > 21) {
                    break;
                }

                System.out.print("Hit or Stand? (H/S): ");
                Scanner scanner = new Scanner(System.in);
                String choice = scanner.nextLine().toUpperCase();

                if (choice.equals("H")) {
                    player.addCard(deck.remove(deck.size() - 1));
                } else if (choice.equals("S")) {
                    break;
                }
            }

            playerScore.calculateScore(player.getPlayerCards());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred during the player's turn.");
        }
    }

    private void dealerTurn() {
        try {
            System.out.println("-------- Jack's Blackjack Game --------");
            System.out.println("Dealer's Cards: " + dealer.getDealerCards());
            System.out.println("Players: " + player.getPlayerCards());

            while (dealerScore.getScore() <= 16) {
                dealer.addCard(deck.remove(deck.size() - 1));
                dealerScore.calculateScore(dealer.getDealerCards());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred during the dealer's turn.");
        }
    }

    private void printResult() {
        try {
            int dealerScoreValue = dealerScore.getScore();
            int playerScoreValue = playerScore.getScore();

            System.out.println();
            System.out.println("------------------------------------");

            if (playerScoreValue > 21) {
                System.out.println("Dealer Wins...");
            } else if (dealerScoreValue > 21) {
                System.out.println("Player Wins...");
            } else {
                if (playerScoreValue > dealerScoreValue) {
                    System.out.println("Player Wins...");
                } else if (playerScoreValue < dealerScoreValue) {
                    System.out.println("Dealer Wins...");
                } else {
                    System.out.println("It's a tie.");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while printing the result.");
        }
    }

    public static void main(String[] args) {
        try {
            Card game = new Card();
            game.playGame();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}