package org.example;

import java.util.ArrayList;
import java.util.List;

public class Player implements Gamer{

    private List<Card> cards = new ArrayList<>();
    private int score = 0;
    private int getMinusA = 0;

    @Override
    public void draw(Deck deck){
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
            }
        }
    }

    public int getScore() {
        return score;
    }

    @Override
    public void showCards(){
        System.out.print("Player: ");
        for(Card card : cards){
            System.out.print(card.getDenomination().getSymbol() + "(" + card.getPattern().getSymbol() + ")  ");
        }
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
}
