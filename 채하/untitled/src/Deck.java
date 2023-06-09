import java.util.ArrayList;
import java.util.Random;

public class Deck {
    public ArrayList<Card> CardDeck;

    public Deck(){
    	CardDeck = new ArrayList<Card>();
        String[] Mark = {"♠","♣","♥","◆"};
        for (String M : Mark){
            for(int j = 1; 13 > j; j++){
                CardDeck.add(new Card(M,j));
          }
         }
    }
    
    public Card Open(){
        Card Opencard = CardDeck.get(0);
        CardDeck.remove(0);
        return Opencard;
    }

    public void Shuffle(){
        ArrayList<Card> ShffleDeck = new ArrayList<Card>();
        Random random = new Random();
        for (int i = 0; CardDeck.size() > i; i++){
            int index = random.nextInt(CardDeck.size()-1);
            ShffleDeck.add(CardDeck.get(index));
            CardDeck.remove(index);
        }
        CardDeck = ShffleDeck;
    }
}
