import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final int TARGET_SCORE = 21;
    private static final int DEALER_STAND_SCORE = 17;
    private List<String> deck;
    private List<String> playerHand;
    private List<String> dealerHand;
    private Random random;
    private Scanner scanner;

    public Main() {
        deck = new ArrayList<>();
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
        random = new Random();
        scanner = new Scanner(System.in);
        initializeDeck();
    }

    private void initializeDeck() {
        String[] suits = {"♠", "♣", "◆", "♥"};
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        for (int i = 0; i < suits.length; i++) {
            String suit = suits[i];
            for (int j = 0; j < ranks.length; j++) {
                String rank = ranks[j];
                String card = rank + "(" + suit + ")";
                deck.add(card);
            }
        }
    }

    private void shuffleDeck() {
        int size = deck.size();
        for (int i = 0; i < size; i++) {
            int j = random.nextInt(size);
            deck.set(i, deck.get(j));
        }
    }

    private int calculateHandScore(List<String> hand) {
        int score = 0;
        int numAces = 0;

        for (String card : hand) {
            String rank = card.split("\\(")[0];

            if (rank.equals("A")) {
                score += 11;
                numAces++;
            }

            else if (rank.equals("K") || rank.equals("Q") || rank.equals("J")) {
                score += 10;
            }

            else {
                score += Integer.parseInt(rank);
            }
        }

        while (score > TARGET_SCORE && numAces > 0) {
            score -= 10;
            numAces--;
        }

        return score;
    }

    private void dealInitialCards() {
        playerHand.clear();
        dealerHand.clear();

        for (int i = 0; i < 2; i++) {
            playerHand.add(deck.remove(deck.size() - 1));
            dealerHand.add(deck.remove(deck.size() - 1));
        }
    }

    private void printHands(boolean showDealerHand) {
        System.out.println("# 딜러의 카드:");
        if (showDealerHand) {
            for (int i = 0; i < dealerHand.size(); i++) {
                String card = dealerHand.get(i);
                System.out.print(card + " ");
            }
            System.out.println();
        } else {
            System.out.println(dealerHand.get(0) + " XX");
        }

        System.out.println("# 플레이어의 카드:");
        for (int i = 0; i < playerHand.size(); i++) {
            String card = playerHand.get(i);
            System.out.print(card + " ");
        }
        System.out.println();
    }

    private void playerTurn() {

        while (true) {
            System.out.print("카드 받기(H) 또는 그만두기(S): ");
            String choice = scanner.nextLine().trim().toLowerCase();
            if (choice.equals("h")) {
                playerHand.add(deck.remove(deck.size() - 1));
                printHands(false);

                if (calculateHandScore(playerHand) > TARGET_SCORE) {
                    System.out.println("버스트! 플레이어 패배");
                    break;
                }

            } else if (choice.equals("s")) {
                break;

            } else {
                System.out.println("잘못된 입력입니다. 다시 입력하세요.");
            }
        }
    }

    public void playGame() {
        shuffleDeck();
        dealInitialCards();
        printHands(false);
        playerTurn();

        if (calculateHandScore(playerHand) <= TARGET_SCORE) {
            dealerTurn();
        }
        determineWinner();
    }

    private void dealerTurn() {
        System.out.println("# 딜러의 턴");
        printHands(true);
        while (calculateHandScore(dealerHand) < DEALER_STAND_SCORE) {
            dealerHand.add(deck.remove(deck.size() - 1));
            printHands(true);
        }

        if (calculateHandScore(dealerHand) > TARGET_SCORE) {
            System.out.println("딜러 버스트! 플레이어 승리");
        }
    }

    private void determineWinner() {
        int playerScore = calculateHandScore(playerHand);
        int dealerScore = calculateHandScore(dealerHand);

        System.out.println("# 결과");
        System.out.println("플레이어 점수: " + playerScore);
        System.out.println("딜러 점수: " + dealerScore);

        if (playerScore > TARGET_SCORE) {
            System.out.println("플레이어 버스트! 딜러 승리");

        } else if (dealerScore > TARGET_SCORE) {
            System.out.println("딜러 버스트! 플레이어 승리");

        } else if (playerScore > dealerScore) {
            System.out.println("플레이어 승리");

        } else if (playerScore < dealerScore) {
            System.out.println("플레이어 승리");

        } else {
            System.out.println("플레이어 승리");
        }
    }

    public static void main(String[] args) {
        Main game = new Main();
        game.playGame();
    }
}
