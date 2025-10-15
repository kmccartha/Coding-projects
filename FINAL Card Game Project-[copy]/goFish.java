import java.util.*;
/**
 * Write a description of class GoFish here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class goFish
{
    public static void main(String[] args)
    {
        Deck deck = new Deck();
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        Player computer = new Player("computer");
        
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;
        while(running == true)
        {
            System.out.println("GO FISH");
            System.out.println("--------");
            System.out.println("1. Start New Game");
            System.out.println("2. play against computer");
            System.out.println("3. Rules");
            System.out.println("4. Test menu");
            System.out.println("5. Exit Game");
            System.out.println("6. different Game");
            
            System.out.println();
            
            int choice = keyboard.nextInt();
            keyboard.nextLine();
            switch (choice) 
            {
                case 1:
                    deck.shuffle();
                    //deals 5 cards from deck to each player
                    for (int i = 0; i < 5; i++) 
                    {
                        player1.draw(deck);
                        player2.draw(deck);
                    }
                    while (!deck.isEmpty() && !player1.getHand().isEmpty() && !player2.getHand().isEmpty())
                    {
                        player1.playerTurn(player2, deck);
                        if(!deck.isEmpty() && !player1.getHand().isEmpty() && !player2.getHand().isEmpty())
                        {
                            player2.playerTurn(player1, deck);    
                        }
                    }
                    System.out.println();
                    System.out.println("Game Over!!!");
                    System.out.println();
                    declareWinner(player1, player2);
                    break;
                case 2:
                    deck.shuffle();
                    //deals 5 cards from deck to each player
                    for (int i = 0; i < 5; i++) 
                    {
                        player1.draw(deck);
                        computer.draw(deck);
                    }
                    while (!deck.isEmpty() && !player1.getHand().isEmpty() && !computer.getHand().isEmpty())
                    {
                        player1.playerTurn(computer, deck);
                        if(!deck.isEmpty() && !player1.getHand().isEmpty() && !computer.getHand().isEmpty())
                        {
                            computer.computerTurn(player1, deck);    
                        }
                    }
                    System.out.println();
                    System.out.println("Game Over!!!");
                    System.out.println();
                    declareWinner(player1, computer);
                    break;
                case 3: 
                    Player player = new Player("Player");
                    player.rules();
                    break;
                case 4: 
                    boolean running2 = true;
                    while(running2 == true)
                    {
                        System.out.println("Test Menu");
                        System.out.println("-------------");
                        System.out.println("1. Generate Deck");
                        System.out.println("2. Shuffle Deck");
                        System.out.println("3. See Top Card of Deck");
                        System.out.println("4. Exit");
                        System.out.println();
                                    
                        int choice2 = keyboard.nextInt();
                        keyboard.nextLine();
                        switch(choice2)
                        {
                            case 1:
                                for(int i = 0; i < deck.getCard().size(); i++)
                                {
                                    System.out.println(deck.getCard().get(i));
                                }
                                System.out.println();
                                System.out.println("Deck Generated");
                                System.out.println();
                                break;
                            case 2:
                                deck.shuffle();
                                for(int i = 0; i < deck.getCard().size(); i++)
                                {
                                    System.out.println(deck.getCard().get(i));
                                }
                                System.out.println();
                                System.out.println("Deck Shuffled");
                                System.out.println();
                                break;
                            case 3:
                                deck.shuffle();
                                Player testPlayer = new Player("Test Player");
                                System.out.println(testPlayer.draw(deck).toString());
                                System.out.println();
                                break;
                            case 4:
                                running2 = false;
                                break;
                            case 5:
                                System.out.println("Invalid choice. Please enter a valid menu number.");
                        }
                    }
                    break;
                case 5:
                    running = false;
                    break;
                case 6:
                    deck.shuffle();
                    Player pile = new Player("pile");
                    int i = 12;
                    //deals 5 cards from deck to each player
                    for (int x = 0; x < 5; x++) 
                    {
                        player1.draw(deck);
                        player2.draw(deck);
                    }
                    while(!deck.isEmpty())
                    {
                        player1.differentGame(player2, deck, pile, i);
                        if(i != 12)
                        {
                            i++;
                        }
                        else
                        {
                            i = 0;
                        }
                        player2.differentGame(player1, deck, pile, i);
                        if(i != 12)
                        {
                            i++;
                        }
                        else
                        {
                            i = 0;
                        }
                    }
                default:
                    System.out.println("Invalid choice. Please enter a valid menu number.\n");
            }
        }
    }
    public static void declareWinner(Player player1, Player player2)
    {
        int player1SetsOf4 = player1.numOfSetsOfFour(player1);
        int player2SetsOf4 = player2.numOfSetsOfFour(player2);
        if(player1SetsOf4 > player2SetsOf4)
        {
            System.out.println("Player 1 wins!!!");    
        }
        else if(player1SetsOf4 < player2SetsOf4)
        {
            System.out.println("Player 2 wins!!!");    
        }
        else
        {
            System.out.println("It's a Tie!!!");    
        }    
    }
}
