import java.util.Scanner;
/*
    CS282-1913 – Spring 2024
    Lab 5 - DMV Line 

    Kiame McCartha

    4/3/2024

    DMV waiting list program that adds a person, checks who's next, and assists the next person by 
    removing their name from the list

*/
public class DMVLine
{
    public static void main(String[] args)
    {
        String name;
        Queue queue = new Queue();
        
        Scanner keyboard = new Scanner(System.in);
        //menu
        boolean running = true;
        while (running) 
        {
            System.out.println("DMV Waiting List");
            System.out.println("----------------------");
            System.out.println("1.Add a person");
            System.out.println("2.check who is next");
            System.out.println("3.Assist next person (and remove from list)");
            System.out.println("4.exit");
            System.out.println();
            
            int choice = keyboard.nextInt();
            keyboard.nextLine();
            switch (choice) 
            {
                case 1://adds person
                    System.out.println("Enter Name");
                    name = keyboard.nextLine();
                    queue.enqueue(name);
                    System.out.println("You have been entered");
                    System.out.println();
                    break;
                case 2://checks who's next in line
                    if(!queue.isEmpty())
                    {
                        System.out.println("Next Person: "+ queue.first());
                        System.out.println();
                    }
                    else
                    {
                        System.out.println("List is Empty");
                        System.out.println();
                    }
                    break;
                case 3://removes next person from list
                    queue.dequeue();
                    System.out.println("Next person has been assisted");
                    System.out.println();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid menu number.");
            }
        }
    }
}
