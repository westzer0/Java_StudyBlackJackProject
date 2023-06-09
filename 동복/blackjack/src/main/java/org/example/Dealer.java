package org.example;

import java.util.ArrayList;
import java.util.List;

public class Dealer implements Gamer{

    private List<Card> cards = new ArrayList<>();
    private int score = 0;
    private int getMinusA = 0;

    public int getScore() {
        return score;
    }

    @Override
    public void draw(Deck deck) {
        Card card = deck.draw();
        cards.add(card);
        plusScore(card.getDenomination().getScore());
    }

    @Override
    public void plusScore(int score){
        this.score += score;
        if(this.score > 21){
            if(getANumber() > getMinusA){
                this.score -= 10;
                getMinusA++;
            }
        }

    }

    @Override
    public void showCards(){
        System.out.print("Dealer: ");
        for(Card card : cards) {
            System.out.print(card.getDenomination().getSymbol() + "(" + card.getPattern().getSymbol() + ")  ");
        }
        System.out.println();
    }

    public void showInitCards(){
        System.out.print("Dealer: ");
        System.out.print(cards.get(0).getDenomination().getSymbol() + "(" + cards.get(0).getPattern().getSymbol() + ")  XX");
        System.out.println();
    }

    @Override
    public int getANumber(){

        int number = 0;

        for(Card card : cards){
            if(card.getDenomination().getSymbol() == "A"){
                number++;
            }
        }
        return number;
    }

    public void drawCardWhileEnd(Deck deck){
        while(this.score < 17){
            draw(deck);
        }
    }
}
