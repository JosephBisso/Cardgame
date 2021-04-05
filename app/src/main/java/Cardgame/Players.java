package Cardgame;

public interface Players {
    Karte[] play(Karte[] toPlayCards);
    boolean hasCard();
    void pick(int numberOfCards);
}
