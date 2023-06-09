package org.example;


import java.util.Scanner;

public class Game {



    public void runGame(){
        Deck deck = new Deck();
        Scanner scanner = new Scanner(System.in);
        Player player = new Player();
        Dealer dealer = new Dealer();

        initGame(deck, player, dealer);

        showCurrentState(player, dealer);
        selectHitStand(scanner, deck, player, dealer);

        showResult(player, dealer);
    }

    public boolean isGameEnd(Gamer player, Gamer dealer){
        if(player.getScore() > 21){
            System.out.println("Dealer Wins...");
            return true;
        }else if(dealer.getScore() > 21){
            System.out.println("Player Wins...");
            return true;
        }
        return false;
    }

    public void initGame(Deck deck, Gamer player, Gamer dealer){
        deck.deckInit();
        deck.shuffle();

        for(int i = 0; i < 2; i++){
            player.draw(deck);
            dealer.draw(deck);
        }
    }

    public void showCurrentState(Gamer player, Dealer dealer){
        System.out.println("---------- Jack's BlackJack Game ----------");
        dealer.showInitCards();
        player.showCards();
        System.out.println("-------------------------------------------");
    }

    public void selectHitStand(Scanner scanner, Deck deck, Gamer player, Dealer dealer){
        System.out.println("Hit or Stand? (H/S) :");
        String answer = scanner.next();

        if(answer.equals("h") || answer.equals("H")){
            player.draw(deck);
            if(!isGameEnd(player, dealer)){
                showCurrentState(player, dealer);
                selectHitStand(scanner, deck, player, dealer);
            }
        }else if(answer.equals("s") || answer.equals("S")){
            dealer.drawCardWhileEnd(deck);
            if(!isGameEnd(player,  dealer)){
                compareScore(player, dealer);
            }
        }
    }
    public void compareScore(Gamer player, Gamer dealer){
        if(player.getScore() > dealer.getScore()){
            System.out.println("Player Wins...");
        }else if(player.getScore() < dealer.getScore()){
            System.out.println("Dealer Wins...");
        }else{
            System.out.println("Draws...");
        }
    }
    public void showResult(Gamer player, Gamer dealer){
        System.out.println("---------- Jack's BlackJack Game ----------");
        dealer.showCards();
        player.showCards();
        System.out.println("-------------------------------------------");
    }

}
