import java.util.Scanner;

public class Main {
    public static boolean isY(String input) {
        return input.equalsIgnoreCase("Y");
    }
    public static boolean isN(String input) {
        return input.equalsIgnoreCase("N");
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("게임 시작 하시겠습니까? (Y / N) : ");
            String input = scanner.next();
            if (isY(input)) {
                Game game = new Game();
                game.init();
            } else if (isN(input)) {
                break;
            } else {
                System.out.println("잘못입력하셨습니다.");
            }
        }
    }
}
