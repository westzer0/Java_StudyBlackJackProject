import java.util.Scanner;


public class Game {
	public static Deck GameDeck;
	public static Hand Player;
	public static Hand Dealer;
	
    public static void start() {
    	Scanner sc = new Scanner(System.in);
    	GameDeck = new Deck();
        GameDeck.Shuffle();
        Hand Player = new Hand();
        Hand Dealer = new Hand();
        Player.Draw(GameDeck, 2);
        Dealer.Draw(GameDeck, 2);

        while(true) {
            boolean Hitbool = true;
            if(Player.Sum()> 21) {
            	System.out.println("Dealer Win...");
            	PrintGame(Player, Dealer, true);
            	break;
            }
            
            PrintGame(Player, Dealer, true);
            
            System.out.print("Hit or Stand? (H/S):");
            
            String hit = sc.next();
            if (hit.equals("H")){
            	Player.Draw(GameDeck, 1);
            } else if (hit.equals("S")) {
            	PrintGame(Player, Dealer, false);
            	//딜러가 17이 넘을때까지 드로우
            	while(Dealer.Sum()< 17) {
            		Dealer.Draw(GameDeck, 1);
            		PrintGame(Player, Dealer, false);
            	}
            	//승패 결정
            	if(Dealer.Sum()> 21 || (21 - Dealer.Sum()) > (21 - Player.Sum())) {
            		System.out.println("Player Win...");
            		PrintGame(Player, Dealer, false);
            	}else {
            		System.out.println("Dealer Win...");
            		PrintGame(Player, Dealer, false);
            	}
            	
                break;
	            }
	        }
    	}
        
       public static void PrintGame(Hand Player, Hand Dealer, boolean Dhide) {
    	   System.out.println("---------------------Jack's BlackJack Game---------------------");
           System.out.print("# Dealer: ");
           if(Dhide) {
        	   Dealer.HandDeck.get(0).Show();
        	   System.out.println(" XX");
           }
           else {
        	   for (Card cd : Dealer.HandDeck) {
                   cd.Show();
               }
        	   System.out.println();
           }
           
           System.out.print("# Player: ");
           for (Card cd : Player.HandDeck) {
               cd.Show();
           }
           System.out.println();
           System.out.println("---------------------------------------------------------------");
           
    	   
       }

}

