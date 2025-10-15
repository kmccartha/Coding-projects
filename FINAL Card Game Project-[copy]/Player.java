import java.util.*;
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{
    private String name;
    private ArrayList<Card> hand = new ArrayList<>();
    Scanner keyboard = new Scanner(System.in);

    public Player(String name) 
    {
        this.name = name;
    }
    public String getName() 
    {
        return name;
    }
    public Card draw(Deck deck) 
    {
        Card card = deck.draw();
        if (card != null) 
        {
            hand.add(card);
        }
        return card;
    }
    public boolean isEmpty() 
    {
        return hand.isEmpty();
    }
    public ArrayList<Card> getHand()
    {
        return hand;
    }
    public void playerTurn(Player opponent, Deck deck)
    {
        System.out.println(getName() +"'s turn\n");
        System.out.println("Your cards");
        System.out.println("-----------");
        for(int i = 0; i < hand.size(); i++)//displays cards
        {
            System.out.println(hand.get(i).toString());
        }
        System.out.println();
        System.out.println("Request a Card Rank");
        String requestedCard = keyboard.nextLine();
                    //makes sure that user only requests card that's from their deck
        boolean contains = false;
        while(contains == false)
        {
            for(int i = 0; i < hand.size(); i++)
            {
                if(hand.get(i).getRank().equals(requestedCard))
                {
                    contains = true;        
                }
            }
            if(contains == false)
            {
                System.out.println("You can only request a card rank from your deck\n");
                System.out.println("Request a Card Rank");
                requestedCard = keyboard.nextLine();
            }
        }
                    
        boolean cardTransfer = false;
        for(int i = 0; i < opponent.getHand().size(); i++)
        {
            if(opponent.getHand().get(i).getRank().equals(requestedCard))//if opponent has requested card, he gives it to you
            {
                hand.add(opponent.getHand().get(i));
                System.out.println(opponent.getName() +" gave you a "+ opponent.getHand().get(i).toString());
                opponent.getHand().remove(i);
                cardTransfer = true;
                i--;
            }
        }
        if(cardTransfer == false)//if opponent doesn't have requested card, he tells you to go fish
        {
            System.out.println("Go Fish!!!");
            draw(deck);
            System.out.println("You have just drawn from the deck");
        }
        System.out.println();
    }
    public void computerTurn(Player opponent, Deck deck)
    {
        System.out.println(getName() +"'s turn\n");
        Random rand = new Random();
        int randomIndex = rand.nextInt(hand.size()); // Generate a random index
        String requestedCard = hand.get(randomIndex).getRank();
                    
        boolean cardTransfer = false;
        for(int i = 0; i < opponent.getHand().size(); i++)
        {
            if(opponent.getHand().get(i).getRank().equals(requestedCard))//if opponent has requested card, he gives it to you
            {
                hand.add(opponent.getHand().get(i));
                System.out.println("You gave you "+ getName() +" a "+ opponent.getHand().get(i).toString());
                opponent.getHand().remove(i);
                cardTransfer = true;
                i--;
            }
        }
        if(cardTransfer == false)//if opponent doesn't have requested card, he tells you to go fish
        {
            draw(deck);
            System.out.println(getName() +" just drew from the deck");
        }
        System.out.println();        
    }
    public void differentGame(Player opponent, Deck deck, Player pile, int round)
    {
        if(!pile.isEmpty())
        {
            String lastCardAdded = pile.getHand().get(pile.getHand().size() - 1).getRank();
            System.out.println("BS? type y for yes, or n for no");
            String ch = keyboard.nextLine();
            if(ch.equals("y"))
            {
                if(!lastCardAdded.equals(deck.getRanks()[round]))
                {
                    for(int i = 0; i < pile.getHand().size(); i++)
                    {
                        opponent.getHand().add(pile.getHand().get(i));    
                    }
                    pile.getHand().clear();
                }
                else
                {
                    System.out.println("You were wrong");
                    for(int i = 0; i < hand.size(); i++)
                    {
                        hand.add(pile.getHand().get(i));    
                    }
                    pile.getHand().clear();
                }
            }
        }
        System.out.println();
        
        System.out.println(getName() +"'s turn\n");
        System.out.println("Your cards");
        System.out.println("-----------");
        for(int i = 0; i < hand.size(); i++)//displays cards
        {
            System.out.println(hand.get(i).toString());
        }
        System.out.println();
        System.out.println("Card Needed: "+ deck.getRanks()[round]);
        System.out.println("Choose a rank From Your Deck");
        String chosenCard = keyboard.nextLine();
        
        boolean contains = false;
        while(contains == false)
        {
            for(int i = 0; i < hand.size(); i++)
            {
                if(hand.get(i).toString().equals(chosenCard))
                {
                    contains = true;
                    pile.getHand().add(hand.get(i));
                    hand.remove(i);
                }
            }
            if(contains == false)
            {
                System.out.println("You can only choose a card rank from your deck\n");
                System.out.println("Choose a Card");
                chosenCard = keyboard.nextLine();
            }
        }
        
    }
    public int numOfSetsOfFour(Player player1)
    {
        ArrayList<String> countOfFours = new ArrayList();
        for (int i = 0; i < hand.size(); i++) 
        {
            String element = hand.get(i).getRank();
            int count = 0;
                    // Count occurrences of the element
            for (int j = 0; j < hand.size(); j++) 
            {
                if (hand.get(j).getRank().equals(element)) 
                {
                    count++;
                }
            }
                    // Check if it appears exactly 4 times
            if (count == 4) 
            {
                        // Ensure it's not counted again
                countOfFours.add(element);
                        // Remove all occurrences of this element from the list to avoid recounting
                for (int j = 0; j < hand.size(); j++) 
                {
                    if (hand.get(j).equals(element)) 
                    {
                        hand.remove(j);
                        j--; // Adjust index after removal
                    }
                }
            }
        }
        return countOfFours.size();    
    }
    public void rules()
    {
        System.out.println("Each player starts with 5 random cards. Each player then takes turns asking \n"+
                            "the opponent for a specific card rank. If the person being asked has the card \n"+
                            "rank requested by the opponnent in their deck, he then must give that card to \n"+
                            "the opponent. If the person being asked does not have the card rank requested \n"+
                            "by the opponnent in their deck, he then must tell his opponent to ''GO FISH!'' \n"+
                            "which means to draw a card from the deck. After the deck is empty, or if either \n"+
                            "player has no more cards in their deck, whichever player has the most sets of 4 \n"+
                            "of the same rank in their deck wins the game.\n");
        
    }
}