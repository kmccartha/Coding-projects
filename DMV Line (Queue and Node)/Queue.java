
/*
    CS282-1913 – Spring 2024
    Lab 5 - DMV Line 

    Kiame McCartha

    4/3/2024

    Queue class 

*/
public class Queue 
{
    Node Rear;
    Node Front;
    int Size;
    
    public Queue()
    {
        Rear=null;
        Front=null;
    }
    
    //isEmpty
    public boolean isEmpty()
    {
        if(Front==null)
            return true;
        
        return false;
    }
    
    //enqueue
    public  void enqueue(String p)
    {
        Node  temp = new  Node(p);
        
        if(isEmpty())
        {
            Front=temp;
            Rear=temp;
        }
        
        Rear.tail=temp;
        Rear=temp;
        Size++;

    }
    
    //dequeue
    public String dequeue()
    {
        if(isEmpty())
            return "empty!";
        
        String temp=Front.person;
        
        Front=Front.tail;
        
        if(Front==null)
            Rear=null;
        
        Size--;
        return temp;
    }
    
    //first
    public String first()
    {
        return Front.person;
    }
    
    //size
    public int size()
    {
        return Size;
    }

}