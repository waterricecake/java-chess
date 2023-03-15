package chess;

public enum Rank {
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1)
    ;

    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public int calculateDistance(Rank rank){
        return Math.abs(this.value - rank.value);
    }
}
