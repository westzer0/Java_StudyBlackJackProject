import java.util.Stack;
import java.util.Collections;

public class Deck {
    static String Clover = "(♣)";
    static String Dia = "(♦)";
    static String Heart = "(♥)";
    static String Spade = "(♠)";

    private Stack<String> cards;

    public Deck(){
        cards = new Stack<>();
        init();
    }

    public void init(){
        String[] suits = {Clover, Dia, Heart, Spade};
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        for(String suit : suits){
            for(String rank : ranks){
                cards.push(rank  + suit);
            }
        }
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public void showCards() {
        System.out.println("Deck Cards ");
        for (String card : cards) {
            System.out.println(card);
        }
    }

    public void size(){
        System.out.println(cards.size());
    }

    public String pop(){

        return cards.pop();
    }
}
