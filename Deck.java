import java.util.*;
/**
 * Write a description of class Deck here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Deck
{
    private ArrayList<Card> cards = new ArrayList<>();

    public Deck() 
    {
        String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
        String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };
        for(int i = 0; i < suits.length; i++) 
        {
            for(int x = 0; x < ranks.length; x++) 
            {
                cards.add(new Card(suits[i], ranks[x]));
            }
        }
    }
    public ArrayList <Card> getCard()
    {
        return cards;
    }
    public String[] getRanks()
    {
        String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };
        return ranks;
    }
    public void shuffle() 
    {
        Collections.shuffle(cards);
    }
    public boolean isEmpty() 
    {
        return cards.isEmpty();
    }
    public Card draw() 
    {
        if (!isEmpty()) 
        {
            return cards.remove(0);
        }
        return null;
    }
}

