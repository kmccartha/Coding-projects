import java.util.Scanner;
import java.util.*;
import java.io.*;
/*
    CS282-1913 – Spring 2024
    Lab 3 Cipher 

    Kiame McCartha

    3/11/2024

    program takes a key word, a variation on the Caesar Cipher. The keyword will fill the first part of the shifted array. 
    The remaining letters will fill the end of the array. Program then pops up menu for user to choose wether to encrypt message, 
    or decrypt message 

*/
public class CaesarCipher
{
    public static void main(String[] args)
    {
        String message;
        String keyword;
        String inputFileName;
        String outputFileName;
        
        Scanner keyboard = new Scanner(System.in);
        
        System.out.println("1. Enter Key word");
        keyword = keyboard.nextLine().toUpperCase();
        Keyword(keyword); //creates shifted alphabet based on keyword
        
        
        //menu
        boolean running = true;
        while (running) 
        {
            System.out.println("1. Encrypt message");
            System.out.println("2. Decrypt message");
            System.out.println("3.create encrypted file");
            System.out.println("4.decrypt file");
            System.out.println("5. Exit");
            
            int choice = keyboard.nextInt();
            keyboard.nextLine();
            switch (choice) 
            {
                case 1: //encrypts message
                    System.out.println("Enter message to encrypt");
                    message = keyboard.nextLine().toUpperCase();
                    Encrypt(message, Keyword(keyword));
                    break;
                case 2: //decrypts message
                    System.out.println("Enter message to decrypt");
                    message = keyboard.nextLine().toUpperCase();
                    Decrypt(message, Keyword(keyword));
                    break;
                case 3: //encrypts message to file
                    System.out.println("Enter message to encrypt");
                    message = keyboard.nextLine().toUpperCase();
                    try
                    {
                        System.out.println("Enter the path where output.txt should be saved (ie c:/tmp/output.txt)");
                        outputFileName = keyboard.nextLine();
                        PrintWriter outStream = new PrintWriter(outputFileName);
                        
                        EncryptToFile(Encrypt(message, Keyword(keyword)), outputFileName);
                    }
                    catch(IOException e)
                    {
                        System.out.println(e.getMessage());
                    }        
                    break;
                case 4: //decrypts encrypted file          
                    System.out.println("Enter the path to the decrypted file (ie c:/tmp/decryptedMessage.txt)");
                    inputFileName = keyboard.nextLine();
                    File file = new File(inputFileName);
                        
                    DecryptEncryptedFile(inputFileName, Keyword(keyword));
                    while (!file.exists())
                    {
                        System.out.println("Enter the path to the decrypted file (ie c:/tmp/decryptedMessage.txt)");
                        inputFileName = keyboard.nextLine();
                        file = new File(inputFileName);
                            
                        DecryptEncryptedFile(inputFileName, Keyword(keyword));
                    }
                    break;
                case 5:
                    running = false; // Exit the loop
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid menu number.");
            }
        }
    }
    public static char[] Keyword(String keyword)
    {
        //creates shifted alphabet by moving all letters in key word to the front of the alphabet in the order entered by user
        
        boolean contains;
        
        // array to store the shifted alphabets
        char[] shiftedAlphabet = new char[26];
        
        int index = 0;
        //fills the first part of the array with unique characters from the keyword
        for (int i = 0; i < keyword.length(); i++) 
        {
            contains = false;
            char ch = keyword.charAt(i);
            for (int x = 0; x < shiftedAlphabet.length; x++) 
            {
                if (shiftedAlphabet[x] == ch)
                {
                    contains = true;  
                }
            } 
            if (contains == false)
            shiftedAlphabet[index++] = ch;
        }
                    
        //fills the remaining part of the array with the remaining characters
        for (char ch = 'A'; ch <= 'Z'; ch++) 
        {
            contains = false;
            for (int i = 0; i < shiftedAlphabet.length; i++) 
            {
                if (shiftedAlphabet[i] == ch)
                {
                    contains = true;  
                }
            } 
            if (contains == false)
                shiftedAlphabet[index++] = ch;
        }
        return shiftedAlphabet;
    }
    public static String Encrypt(String message, char[] shiftedArray)
    {
        String output = "";
        
        //array to store alphabet in normal order
        char[] regAlphabet = new char[26];
                    
        //fills regAlphabet[] array with letters from alphabet in normal order (from A to Z)
        int index = 0;
        for (char ch = 'A'; ch <= 'Z'; ch++) 
        {
            regAlphabet[index++] = ch;
        }
        
        //encrypts message
        for (int i = 0; i < message.length(); i++) 
        {
            char ch = message.charAt(i);
            if (Character.isLetter(ch)) 
            {
                for (int x = 0; x < regAlphabet.length; x++)
                {
                    if (ch == regAlphabet[x])
                    {
                        System.out.print(shiftedArray[x]);
                        output += shiftedArray[x];
                    }    
                }
            }
            else 
            {
                System.out.print(ch); 
                output += ch;
            }
        }
        System.out.println(); 
        return output;
    }
    public static String Decrypt(String message, char[] shiftedArray)
    {
        String output = "";

        //array to store alphabet in normal order
        char[] regAlphabet = new char[26];
                    
        //fills regAlphabet[] array with letters from alphabet in normal order (from A to Z)
        int index = 0;
        for (char ch = 'A'; ch <= 'Z'; ch++) 
        {
            regAlphabet[index++] = ch;
        }
                    
        //decrypts message
        for (int i = 0; i < message.length(); i++)
        {
            char ch = message.charAt(i);
            if (Character.isLetter(ch))
            {
                for (int x = 0; x < shiftedArray.length; x++)
                {
                    if (ch == shiftedArray[x])
                    {
                        System.out.print(regAlphabet[x]);
                        output += regAlphabet[x];
                    }
                }    
            }
            else 
            {
                System.out.print(ch); // Print non-alphabetic characters as they are
            }
        }
        System.out.println();
        return output;
    }
    public static void EncryptToFile(String message, String outputFileName)
    {
        //encrypts message to file
        try
        {
            FileWriter fw = new FileWriter(outputFileName, true);
            PrintWriter outStream = new PrintWriter(fw);
            outStream.println(message);
            outStream.close();
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public static void DecryptEncryptedFile(String inputFileName, char[] shiftedArray)
    {
        //decrypts encrypted file 
        try
        { 
            File file = new File(inputFileName);
            Scanner inputStream = new Scanner(file);
            
            String input = inputStream.nextLine();
            Decrypt(input, shiftedArray);
            
            inputStream.close(); 
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}