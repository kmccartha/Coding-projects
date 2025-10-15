
/*
    CS282-1913 – Spring 2024
    Lab 5 - DMV Line 

    Kiame McCartha

    4/3/2024

    Node class 

*/
class Node
{
    //Data
    public String person;
        
    //Next reference
    public Node tail;
        
    public Node(String p)
    {
        person = p;
        tail = null;
    }
        
}