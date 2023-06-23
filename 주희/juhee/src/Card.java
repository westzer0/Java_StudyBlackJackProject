class Card {
    private String shape; // 카드 모양
    private String number; // 카드 숫자
    private int value; // 카드 점수

    public Card(String suit, String rank, int value) {
        this.shape = suit;
        this.number = rank;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return number + "(" + shape + ")";
    }
}