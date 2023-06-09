import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Blackjack {
    public int dealercount = 0;
    public int playercount = 0;
    private List<String> deck;
    public ArrayList<Object> playerCard; // 플레이어가 가진 카드
    public ArrayList<Object> dealerCard; // 딜러가 가진 카드

    public Blackjack() {
        // 덱 설정
        deck = new ArrayList<>(Arrays.asList(
                "A(♠)", "2(♠)", "3(♠)", "4(♠)", "5(♠)", "6(♠)", "7(♠)", "8(♠)", "9(♠)", "10(♠)", "J(♠)", "Q(♠)", "K(♠)",
                "A(♦)", "2(♦)", "3(♦)", "4(♦)", "5(♦)", "6(♦)", "7(♦)", "8(♦)", "9(♦)", "10(♦)", "J(♦)", "Q(♦)", "K(♦)",
                "A(♥)", "2(♥)", "3(♥)", "4(♥)", "5(♥)", "6(♥)", "7(♥)", "8(♥)", "9(♥)", "10(♥)", "J(♥)", "Q(♥)", "K(♥)",
                "A(♣)", "2(♣)", "3(♣)", "4(♣)", "5(♣)", "6(♣)", "7(♣)", "8(♣)", "9(♣)", "10(♣)", "Q(♣)", "K(♣)"
        ));
        playerCard = new ArrayList<>();
        dealerCard = new ArrayList<>();
    }

    public void shuffle() {
        // 카드 섞기
        Random random = new Random();
        List<String> newDeck = new ArrayList<>();
        System.out.println(deck);
        for (int i = 0; i < deck.size(); i++) {
            int j = random.nextInt(deck.size());
            String card = deck.get(j);

            if (!newDeck.contains(card)) {
                newDeck.add(card);
            } else {
                i--;
            }
        }
        deck = newDeck;

        System.out.println(deck);
    }

    public void startGame() {
        System.out.println("---------BlackJack Game---------");
        for (int i = 0; i < 2; i++) {
            playerCard.add(deck.get(0));
            Object pcard = deck.get(0);
            if (pcard instanceof Integer) {
                playercount += (int) pcard;
            } else if (pcard instanceof String) {
                String cardString = (String) pcard;
                int pcardValue = 0; // 변수 선언
                if (cardString.startsWith("A")) {
                    if (playercount > 21) {
                        pcardValue = 1;
                    } else {
                        pcardValue = 11;
                    }
                } else {
                    pcardValue = 10;
                }
                playercount += pcardValue;
            }
            deck.remove(0);

            dealerCard.add(deck.get(0));
            Object dcard = deck.get(0);
            if (dcard instanceof Integer) {
                dealercount += (int) dcard;
            } else if (dcard instanceof String) {
                String cardString = (String) dcard;
                int dcardValue = 0;
                if (cardString.startsWith("A")) {
                    if (dealercount > 21) {
                        dcardValue = 1;
                    } else {
                        dcardValue = 11;
                    }
                } else {
                    dcardValue = 10;
                }
                dealercount += dcardValue;
            }
            deck.remove(0);
        } // 카드 두 장 뽑기
        System.out.println(" # Dealer: " + dealerCard.get(0) + "XX");
        System.out.println(" # Player: " + playerCard.get(0) + playerCard.get(1));
        System.out.println("--------------------------------");
        // 딜러 카드 하나 가려서 보여주고 유저 카드는 그냥 보여줌
    }

    public void showCard() {
        System.out.println("---------BlackJack Game---------");
        System.out.println(" # Dealer: ");
        for (Object card : dealerCard) {
            System.out.print(card + " ");
        }
        System.out.println(" ");

        System.out.println(" # Player: ");
        for (Object card : playerCard) {
            System.out.print(card + " ");
        }
        System.out.println();
        System.out.println("--------------------------------");
    }

    public void playerTurn() {
        String data = "z";

        System.out.println("Hit or Stand? (H/S)");
        Scanner scan = new Scanner(System.in);
        data = scan.nextLine();
        if (data.equals("H")) {
            playerCard.add(deck.get(0));
            String card = (String) deck.get(0);
            String number = card.substring(0, card.indexOf("("));
            int pcardValue = 0;
            if (number.equals("A")) {
                if (playercount + 11 <= 21) {
                    pcardValue = 11;
                } else {
                    pcardValue = 1;
                }
            } else if (number.equals("K") || number.equals("Q") || number.equals("J")) {
                pcardValue = 10;
            } else {
                pcardValue = Integer.parseInt(number);
            }
            playercount += pcardValue;
            deck.remove(0);
            showCard();
        } else if (data.equals("S")) {
            Stand();
        }
    }



    public void playing() {
        shuffle();
        startGame();
        playerTurn();
    }

    public void Stand() {
        dealerTurn();
        int pscore = 21 - playercount;
        int dscore = 21 - dealercount;

        if (pscore < dscore) {
            System.out.println("Player Wins!!");
        } else {
            System.out.println("Dealer Wins!!");
        }
    }


    public void dealerTurn() {
        while (dealercount < 17) {
            dealerCard.add(deck.get(0));
            String card = (String) deck.get(0);
            String number = card.substring(0, card.indexOf("("));
            int dcardValue = 0;
            if (number.equals("A")) {
                if (dealercount + 11 <= 21) {
                    dcardValue = 11;
                } else {
                    dcardValue = 1;
                }
            } else if (number.equals("K") || number.equals("Q") || number.equals("J")) {
                dcardValue = 10;
            } else {
                dcardValue = Integer.parseInt(number);
            }
            dealercount += dcardValue;
            deck.remove(0);
            showCard();

            if (dealercount > 21) {
                System.out.println("Player wins!!");
                break;
            }
        }
    }

    public static void main(String[] args) {
        Blackjack game = new Blackjack();
        game.playing();
    }
}