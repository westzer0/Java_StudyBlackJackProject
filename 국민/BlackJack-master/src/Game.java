import java.util.Objects;
import java.util.Scanner;
public class Game {
    Dealer dealer;
    Player player;
    Deck deck;
    Scanner scanner = new Scanner(System.in);
    boolean check = true;
    public final int BUSTED_NUM = 21;
    public final int DEALER_NUM = 17;
    public Game(){

    }

    public void init(){
        dealer = new Dealer();
        player = new Player();
        deck = new Deck();

        dealer.secretPut(deck);
        dealer.put(deck);
        player.put(deck);
        player.put(deck);
        player.sum = player.calculateSum();

        while (check){
            check = turn();
            player.sum = player.calculateSum();
            if(player.sum > BUSTED_NUM){//플레이가 버스트될 경우
                printBoard();
                System.out.println("Dealer Wins...");
                showSecret();
                printBoard();
                break;

            }

            //플레이어 스탠 할때
            if(!check){
                showSecret();
                dealer.sum = dealer.calculateSum();
                printBoard();
                if(dealer.sum < DEALER_NUM){
                    while(dealer.sum < DEALER_NUM){
                        try {
                            Thread.sleep(1000);
                            dealer.put(deck);
                            dealer.sum = dealer.calculateSum();
                            printBoard();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if(dealer.sum > BUSTED_NUM){
                        System.out.println("Player Win...");
                        printBoard();
                        break;
                    }
                }

                int dealerScore = BUSTED_NUM - dealer.sum;
                int playerScore = BUSTED_NUM - player.sum;
                if(dealerScore > playerScore){
                    System.out.println("Player Win...");
                    printBoard();
                    break;
                }
                else if(playerScore > dealerScore){
                    System.out.println("Dealer Win...");
                    printBoard();
                    break;
                }
                else{
                    System.out.println("Draw...");
                    printBoard();
                    break;
                }
            }
        }
    }

    public boolean isH(String input) {
        return input.equalsIgnoreCase("H");
    }
    public boolean isS(String input) {
        return input.equalsIgnoreCase("S");
    }
    public boolean turn(){
        boolean check = true;
        printBoard();
        System.out.print("Hit or Stand?  (H/S): ");
        String input = scanner.next();
        return isCheck(input, check);
    }
    public void printBoard(){
        System.out.print("-------------");
        System.out.print("Jack's BlackJack Game");
        System.out.print("-------------");
        System.out.println();
        System.out.print("# Dealer: ");
        for(String card : dealer.cards.keySet()){
            System.out.print(card + "  ");
        }
        System.out.println();
        System.out.print("# Player: ");
        for(String card : player.cards.keySet()){
            System.out.print(card + "  ");
        }
        System.out.println();
        System.out.println("-----------------------------------------------");
        //print_score();
    }
    public boolean isCheck(String input, boolean check){
        if(isH(input)){player.put(deck);
        }
        else if(isS(input)){check = false;
        }
        else{System.out.println("잘못입력하셨습니다. ");
        }

        return check;
    }
    public void print_score(){
        System.out.println("점수확인용");
        System.out.println("player : " + player.sum);
        System.out.println("dealer : " + dealer.sum);
    }
    public void showSecret(){
        for(String card : dealer.cards.keySet()){
            if(Objects.equals(card, "XX")){
                dealer.cards.remove(card);
                dealer.cards.put(dealer.secretCard.getKey(), dealer.secretCard.getValue());
                dealer.sum = dealer.calculateSum();
                break;
            }
        }
    }



}
