
import java.util.Iterator;
import java.util.Scanner;

public class Game {
    public static Deck gameDeck;
    public static Player player;
    public static Dealer dealer;

    public Game() {
    }

    public static void startGame() {
        Scanner sc = new Scanner(System.in);
        gameDeck = new Deck();
        gameDeck.Shuffle();
        player = new Player();
        dealer = new Dealer();
        player.draw(gameDeck, 2);
        dealer.draw(gameDeck, 2);

        while(true) {
            if (player.handCount > 21) {
                System.out.println("Dealer Win...");
                printGame(true);
                break;
            }

            printGame(true);
            System.out.print("Hit or Stand? (H/S):");
            String input = sc.next();
            if (!input.equals("H") && !input.equals("h")) {
                if (input.equals("S") || input.equals("s")) {
                    printGame(false);
                    dealer.hit(gameDeck);
                    if (dealer.handCount <= 21 && 21 - dealer.handCount <= 21 - player.handCount) {
                        System.out.println("Dealer Win...");
                        printGame(false);
                        break;
                    }

                    System.out.println("Player Win...");
                    printGame(false);
                    break;
                }
            } else {
                player.hit(gameDeck);
            }
        }

    }

    public static void printGame(boolean Dhide) {
        System.out.println("---------------------Jack's BlackJack Game---------------------");
        System.out.print("# Dealer: ");
        Iterator var1;
        Card cd;
        if (Dhide) {
            ((Card)dealer.handDeck.get(0)).showCard();
            System.out.println(" XX");
        } else {
            var1 = dealer.handDeck.iterator();

            while(var1.hasNext()) {
                cd = (Card)var1.next();
                cd.showCard();
            }

            System.out.println();
        }

        System.out.print("# Player: ");
        var1 = player.handDeck.iterator();

        while(var1.hasNext()) {
            cd = (Card)var1.next();
            cd.showCard();
        }

        System.out.println();
        System.out.println("---------------------------------------------------------------");
    }
}
