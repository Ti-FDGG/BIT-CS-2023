package homework;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;

//代表扑克牌
class Card {
    public static enum Face {
        Ace, Deuce, Three, Four, Five, Six,
        Seven, Eight, Nine, Ten, Jack, Queen, King
    };

    public static enum Suit {Clubs, Diamonds, Hearts, Spades};

    private final Face face;
    private final Suit suit;

    public Card(Face cardFace, Suit cardSuit) {
        face = cardFace;
        suit = cardSuit;
    }


    public Face getFace() {
        return face;
    }

    public Suit getSuit() {
        return suit;
    }

    public String toString() {
        return String.format("%s of %s", face, suit);
    }
}

public class DeckOfCards {
    private final List<Card> list;

    public DeckOfCards() {
        Card[] deck = new Card[52];
        int count = 0;

        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Face face : Card.Face.values()) {
                deck[count] = new Card(face, suit);
                ++count;
            }
        }
        list = Arrays.asList(deck);
        //洗牌（即打乱集合中元素的顺序）
        Collections.shuffle(list);
    }

    public void printCards() {
        // 按两列显示52张牌
        for (int i = 0; i < list.size(); i++)
            System.out.printf("%-19s%s", list.get(i),
                    ((i + 1) % 4 == 0) ? "\n" : "");
    }

    public static void main(String[] args) {
        DeckOfCards cards = new DeckOfCards();
        cards.printCards();
    }
}
