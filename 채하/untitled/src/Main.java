import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	Game g = new Game();
    	boolean loop = true;
    	while(loop) {
    		
    		g.start();
    		System.out.print("Try Arain? (Y/N):");
        	if(sc.next().equals("N")) {
        		loop = false;
        	}
    	}
    	
    	
    }
}

