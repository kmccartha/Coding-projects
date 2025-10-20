
/**
 * Write a description of class Card here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Card
{
    private String suit;
    private String rank;

    public Card(String suit, String rank) 
    {
        this.suit = suit;
        this.rank = rank;
    }
    public String getSuit() 
    {
        return suit;
    }
    public String getRank() 
    {
        return rank;
    }
    @Override
    public String toString() 
    {
        return rank + " of " + suit;
    }
}