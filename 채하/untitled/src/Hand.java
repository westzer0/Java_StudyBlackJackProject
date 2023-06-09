import java.util.ArrayList;
import java.util.Iterator;

public class Hand {
    public ArrayList<Card> HandDeck;
    public Hand(){
    	HandDeck = new ArrayList<Card>();
    }
    public int Sum(){
    	int answer = 0;
    	for (Card card : HandDeck) {
			if (card.Number > 10) {
				answer += 10;
			}else{
				answer += card.Number;
			}

		}
    	
    	return answer;
    }
    public void Draw(Deck d,int num) {
    	for (int i = 0;i < num; i++) {
    		this.HandDeck.add(d.Open());
		}
    	
    }
}
