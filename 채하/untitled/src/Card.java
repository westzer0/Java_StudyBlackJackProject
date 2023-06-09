public class Card {
    public int Number;
    public String Mark;

    public Card(String inputMark, int inputNumber){
        Mark = inputMark;
        Number = inputNumber;
    }

    public void Show(){
        String[] Numarr = {"0","A","2","3","4","5","6","7","8","9","10","J","Q","K"};

        System.out.print(Mark+"("+Numarr[Number]+")");
    }
}
