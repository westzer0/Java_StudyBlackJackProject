public class Card {
    public int number;
    public String Mark;

    public Card(String inputMark, int inputNumber){
        Mark = inputMark;
        number = inputNumber;
    }

    public void showCard(){
        String[] numarr = {"0","A","2","3","4","5","6","7","8","9","10","J","Q","K"};

        System.out.print(Mark+"("+numarr[number]+")");
    }
}
