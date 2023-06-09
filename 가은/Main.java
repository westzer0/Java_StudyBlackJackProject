import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    // 카드 덱을 나타내는 리스트
    private List<String> deck;
    // 딜러의 카드를 저장하는 리스트
    private List<String> dealerCards;
    // 플레이어의 카드를 저장하는 리스트
    private List<String> playerCards;
    // 랜덤한 카드를 뽑기 위한 Random 객체
    private Random random;
    // 승리한 사람을 저장하는 변수
    private String winner;

    public Main() {
        // 카드 덱 초기화
        deck = new ArrayList<>(Arrays.asList(
                "A(♠)", "2(♠)", "3(♠)", "4(♠)", "5(♠)", "6(♠)", "7(♠)", "8(♠)", "9(♠)", "10(♠)", "J(♠)", "Q(♠)", "K(♠)",
                "A(♦)", "2(♦)", "3(♦)", "4(♦)", "5(♦)", "6(♦)", "7(♦)", "8(♦)", "9(♦)", "10(♦)", "J(♦)", "Q(♦)", "K(♦)",
                "A(♥)", "2(♥)", "3(♥)", "4(♥)", "5(♥)", "6(♥)", "7(♥)", "8(♥)", "9(♥)", "10(♥)", "J(♥)", "Q(♥)", "K(♥)",
                "A(♣)", "2(♣)", "3(♣)", "4(♣)", "5(♣)", "6(♣)", "7(♣)", "8(♣)", "9(♣)", "10(♣)", "J(♣)", "Q(♣)", "K(♣)"
        ));
        // 딜러와 플레이어 카드 리스트 초기화
        dealerCards = new ArrayList<>();
        playerCards = new ArrayList<>();
        // Random 객체 초기화
        random = new Random();
        // 승리자 초기화
        winner = "";
    }

    public void playGame() {
        // 카드를 섞음
        shuffleDeck();

        // 딜러와 플레이어에게 2장의 카드를 나눠줌
        dealInitialCards();

        // 플레이어의 차례
        playerTurn();

        // 딜러의 차례
        dealerTurn();

        // 결과 출력
        printResult();

        // 게임 종료 메시지 출력
        System.out.println("--------------------------------------------");
    }

    private void shuffleDeck() {
        // 카드 덱을 섞음
        for (int i = 0; i < deck.size(); i++) {
            int j = random.nextInt(deck.size());
            String temp = deck.get(i);
            deck.set(i, deck.get(j));
            deck.set(j, temp);
        }
    }

    private void dealInitialCards() {
        // 카드 덱에서 딜러와 플레이어에게 2장씩 나눠줌
        for (int i = 0; i < 2; i++) {
            dealerCards.add(deck.remove(deck.size() - 1));
            playerCards.add(deck.remove(deck.size() - 1));
        }
    }

    private void playerTurn() {
        // 플레이어의 차례 시작
        while (true) {
            // 플레이어의 현재 점수 계산
            int playerScore = calculateScore(playerCards);

            // 플레이어의 현재 카드와 점수 출력
            System.out.println("-------- Jack's Blackjack Game --------");
            System.out.println("Dealer: [" + dealerCards.get(1) + ", XX]");
            System.out.println("Players: " + playerCards);

            // 플레이어가 21을 초과한 경우 (Busted)
            if (playerScore > 21) {
                winner = "Dealer";
                break;
            }

            // 플레이어에게 Hit 또는 Stand 선택을 물어봄
            System.out.print("Hit or Stand? (H/S): ");
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine().toUpperCase();

            // Hit 선택
            if (choice.equals("H")) {
                // 플레이어에게 카드 1장 추가
                playerCards.add(deck.remove(deck.size() - 1));
            }
            // Stand 선택
            else if (choice.equals("S")) {
                break;
            }
        }
    }

    private void dealerTurn() {

        // 딜러의 첫 번째 카드 공개
        System.out.println("-------- Jack's Blackjack Game --------");
        System.out.println("Dealer's Cards: " + dealerCards);
        System.out.println("Players: " + playerCards);

        // 딜러의 현재 점수 계산
        int dealerScore = calculateScore(dealerCards);

        // 딜러가 16 이하인 경우 Hit (17 이상이 될 때까지)
        while (dealerScore <= 16) {
            //System.out.println("Player Wins...");
            dealerCards.add(deck.remove(deck.size() - 1));
            dealerScore = calculateScore(dealerCards);
        }
    }

    private void printResult() {
        // 딜러와 플레이어의 최종 점수 계산
        int dealerScore = calculateScore(dealerCards);
        int playerScore = calculateScore(playerCards);

        System.out.println();
        System.out.println("------------------------------------");

        // 플레이어가 Busted된 경우
        if (playerScore > 21) {
            System.out.println("Dealer Wins...");
        }
        // 딜러가 Busted된 경우
        else if (dealerScore > 21) {
            System.out.println("Player Wins...");
        }
        // 둘 다 Busted되지 않은 경우
        else {
            // 더 점수가 큰 쪽이 승리
            if (playerScore > dealerScore) {
                System.out.println("Player Wins...");
            } else if (playerScore < dealerScore) {
                System.out.println("Dealer Wins...");
            } else {
                System.out.println("It's a tie.");
            }
        }
    }

    private int calculateScore(List<String> cards) {
        int score = 0;
        int numAces = 0;

        // 카드를 하나씩 확인하면서 점수 계산
        for (String card : cards) {
            String cardValue = card.substring(0, card.length() - 3); // 숫자 부분만 추출

            if (cardValue.equals("A")) {
                score += 11;
                numAces++;
            } else if (cardValue.equals("K") || cardValue.equals("Q") || cardValue.equals("J")) {
                score += 10;
            } else {
                score += Integer.parseInt(cardValue);
            }
        }

        // Ace 처리
        while (score > 21 && numAces > 0) {
            score -= 10;
            numAces--;
        }

        return score;
    }


    public static void main(String[] args) {
        // 블랙잭 게임 객체 생성 및 실행
        Main game = new Main();
        game.playGame();
    }
}
