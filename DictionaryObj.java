/*
Jason Fujii
CSC-340.05 TOE
Professor Ta
*/

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmingmethodology.Assignment2;
import java.util.Scanner;
/**
 *
 * @author jasonfujii
 */
public class DictionaryObj {
    public HashList list;
    private boolean quit = false;
    private String[] lastInput = {"one", "two", "three", "four"};
    public DictionaryObj()
    {
       list = new HashList();
       list.addEnum();
    }
    
    
    /*
    The main function that facilitates interaction between the dictionary and the 
    user
    */
    public void userInterface()
    {
        Scanner sc = new Scanner(System.in);
        int searchNum = 1;
        System.out.println("Enter '!help' to learn how to format your search");
        System.out.println("Enter '!q' to quit the program");
        System.out.println("Enter '!list' to see a list of searchable entries");
        while(!quit)
        {
            System.out.print("Search [" + searchNum + "]:");
            String input = sc.nextLine();
            checkInput(input);
            searchNum++;
        }
    }
    
    /*
    input: String userInput - the user's query
    Takes the string, splits it into an array and performs operations on those words
    */
    public void checkInput(String userInput)
    {
        //Don't do toUpper or toLower to test if you actually need to do it
        //if there are issues, check here first
        String[] input = userInput.split(" ");
        
            input[0] = input[0].toLowerCase();
            input[0] = input[0].substring(0, 1).toUpperCase() + input[0].substring(1);
        if(input.length > 4)
            System.out.println("\t|\n\t<This dictionary accepts input of up to 4 words.>\n"
                    + "\t<Any words beyond that have been ignored.>\n\t|");
        //check if String is empty
        if(input[0].isEmpty() || input[0].charAt(0) == '!')
            printAdmin(input[0]);
        else if(checkSameInput(input))
            printAdmin("");
        else
        {
            //check if there's a distinct in 2 or 3
            if(input.length == 1)
            {
                list.Search(input[0]);
            }
            if(input.length == 2)
            {
                input[1] = input[1].toLowerCase();
                //Word [distinct]
                if(checkDistinct(input[1]))
                    list.Search(input[0], "", true, false);
                //word [reverse]
                else if(checkReverse(input[1]))
                    list.Search(input[0], "", false, true);
                //word [pos]
                else if(checkPOS(input[1]))
                    list.Search(input[0], input[1]);
                //word [other]
                else
                {
                    printErrors(2, input[1], checkPOS(input[1]), checkDistinct(input[1]), checkReverse(input[1]));
                    list.Search(input[0]);
                }
            }
            else if(input.length == 3)
            {
                input[1] = input[1].toLowerCase();
                //check if distinct or reverse is in 2 or 3
                if(checkDistinct(input[1]))
                {
                    if(checkReverse(input[2]))
                        list.Search(input[0], "", true, true);
                    else
                    {
                        printErrors(3, input[2], checkPOS(input[2]), checkDistinct(input[2]), checkReverse(input[2]));
                        list.Search(input[0], "", true, false);
                    }
                }
                else if(checkReverse(input[1]))
                {
                    System.out.println("\t|\n\t<'Reverse' should be the last value input.>\n\t|");
                    list.Search(input[0], "", false, true);
                }
                else if(checkPOS(input[1]))
                {
                    //check if distinct or reverse is in 3
                    if(checkDistinct(input[2]))
                        list.Search(input[0], input[1], true, false);
                    else if(checkReverse(input[2]))
                        list.Search(input[0], input[1], false, true);
                        //Search(input[0],input[1],"reverse");
                    else
                    {
                        printErrors(3, input[2], checkPOS(input[2]), checkDistinct(input[2]), checkReverse(input[2]));
                        list.Search(input[0],input[1]);
                    }
                }
                else
                {
                    printErrors(2, input[1], checkPOS(input[1]), checkDistinct(input[1]), checkReverse(input[1]));
                    if(checkDistinct(input[2]))
                    {
                        
                        list.Search(input[0], "", true, false);
                    }
                    else if(checkReverse(input[2]))
                    {
                        list.Search(input[0], "", false, true);
                    }
                    else if(checkPOS(input[2]))
                    {
                        list.Search(input[0], input[2]);
                    }
                    else
                    {
                        printErrors(3, input[2], checkPOS(input[2]), checkDistinct(input[2]), checkReverse(input[2]));
                        list.Search(input[0]);
                    }
                }
            }
            else if(input.length >= 4)
            {
                input[1] = input[1].toLowerCase();
                //check input[1]
                //check if distinct is in 2 or 3
                //check if reverse is in 2, 3, or 4
                if(checkDistinct(input[1]))
                {
                    if(checkReverse(input[2]))
                    {
                        //Search(input[0], "distinct", "reverse");
                        System.out.println("\t<The entered 4th parameter '"+input[3]+"' was"
                               + "disregarded.>\n\t|");
                        list.Search(input[0], "", true, true);
                    }
                    else
                    {
                        printErrors(3, input[2], checkPOS(input[2]), checkDistinct(input[2]), checkReverse(input[2]));
                        if(checkReverse(input[3]))
                        {
                            list.Search(input[0], "", true, true);
                        }
                        else
                        {
                            printErrors(4, input[3], checkPOS(input[3]), checkDistinct(input[3]), checkReverse(input[3]));
                        list.Search(input[0], "", true, false);
                        }
                    }
                }
                else if(checkReverse(input[1]))
                {
                    System.out.println("\t|\n\t<'Reverse' should be the last qualifier included.>\n\t|");
                    list.Search(input[0], "", false, true);
                }
                else if(checkPOS(input[1]))
                {
                    //check if distinct or reverse is in 3
                    if(checkDistinct(input[2]))
                    {
                        if(checkReverse(input[3]))
                            list.Search(input[0], input[1], true, true);
                        else
                        {
                            System.out.println("\t|\n\t<The entered 4th parameter should be 'reverse.'>");
                            printErrors(4, input[3], checkPOS(input[3]), checkDistinct(input[3]), checkReverse(input[3]));
//                            System.out.println("\t<The entered 4th parameter '"+input[3]+"' has been disregarded.>\n\t|");
                            list.Search(input[0], input[1], true, false);
                        }
                    }
                    else if(checkReverse(input[2]))
                    {
                        //Search(input[0],input[1],"reverse");
                        System.out.println("\t|\n\t<'Reverse' should be the last command>");
                        list.Search(input[0], input[1], false, true);
                    }
                    else
                    {
                        printErrors(3, input[2], checkPOS(input[2]), checkDistinct(input[2]), checkReverse(input[2]));
                        if(checkReverse(input[3]))
                        {
                            list.Search(input[0], input[1], false, true);
                            //Search(input[0[, input[1], "reverse");
                        }
                        else if(checkDistinct(input[3]))
                        {
                            list.Search(input[0], input[1], true, false);
                        }
                        else
                        {
                            printErrors(4, input[3], checkPOS(input[3]), checkDistinct(input[3]), checkReverse(input[3]));
                            list.Search(input[0], input[1],false, false);
                        }
                    }
                }
                else
                {
                    //input[1] is bad
                    printErrors(2, input[1], checkPOS(input[1]), checkDistinct(input[1]), checkReverse(input[1]));
                    if(checkDistinct(input[2]))
                    {
                        if(checkReverse(input[3]))
                        {
                            list.Search(input[0], "", true, true);
                        }
                        else
                        {
                            printErrors(4, input[3], checkPOS(input[3]), checkDistinct(input[3]), checkReverse(input[3]));
                            list.Search(input[0], "", true, false);
                        }
                    }
                    else if(checkReverse(input[2]))
                    {
                        System.out.println("\t|\n\t<'Reverse' should be the last command>\n\t|");
                        list.Search(input[0], "", false, true);
                    }
                    else if(checkPOS(input[2]))
                    {
                        if(checkDistinct(input[3]))
                        {
                            list.Search(input[0], input[2], true, false);
                        }
                        else if(checkReverse(input[3]))
                        {
                            list.Search(input[0], input[2], false, true);
                        }
                        else
                        {
                            printErrors(4, input[3], checkPOS(input[3]), checkDistinct(input[3]), checkReverse(input[3]));
                            list.Search(input[0], input[2]);
                        }
                    }
                    else
                    {
                        printErrors(3, input[2], checkPOS(input[2]), checkDistinct(input[2]), checkReverse(input[2]));
                        if(checkDistinct(input[3]))
                        {
                            list.Search(input[0], "", true, false);
                        }
                        else if(checkReverse(input[3]))
                        {
                            list.Search(input[0], "", false, true);
                        }
                        else if(checkPOS(input[3]))
                        {
                            list.Search(input[0], input[3]);
                        }
                        else
                        {
                            printErrors(4, input[3], checkPOS(input[3]), checkDistinct(input[3]), checkReverse(input[3]));
                            list.Search(input[0]);
                        }
                    }
                }
            }
        }    
            
    }
    
    
    /*
    Prints out the administrative replies
    */
    public void printAdmin(String input)
    {
        //input == "" or "help"
        if(input.isEmpty() || input.equals("!help"))
        {
            System.out.println("\t|\n\tPARAMETER HOW-TO, please enter:");
            System.out.println("\t1. A search key -then 2. An optional part of speech -then"
                    + "\n\t3. An optional 'distinct' -then 4. An optional 'reverse'\n\t|");
        }
        //input == "q"
        if(input.equals("!q") ||input.equals("!quit"))
        {
            quit = true;
            System.out.println("-----THANK YOU-----");
        }
        //input == "!list"
        if(input.equals("!list"))
        {
            list.printAllKeys();
        }
    }
    
    /*
    returns true if str is a valid part of speech
    */
    public boolean checkPOS(String str)
    {
        String s = str.toLowerCase();
        return (s.equals("noun") || s.equals("adjective") || s.equals("adverb") 
                || s.equals("conjunction") || s.equals("interjection") 
                || s.equals("preposition") || s.equals("pronoun") || s.equals("verb"));
            
    }
    
    /*
    returns true if str is "distinct"
    */
    public boolean checkDistinct(String str)
    {
        String s = str.toLowerCase();
        return s.equals("distinct");
    }
    
    /*
    returns true if str is "reverse"
    */
    public boolean checkReverse(String str)
    {
        String s = str.toLowerCase();
        return s.equals("reverse");
    }
    
    /*
    printErrors() takes and int of either 2, 3, or 4 and uses that to know which 
    part of the input is erroneous.
    String "in" is the input that the user entered and will print out so that they
    know exactly what word was wrong
    Booleans pos, dist, and rev all refer to their respective values part of speech,
    distinct, and reverse.
    */
    private void printErrors(int place, String in, boolean pos, boolean dist, boolean rev)
    {
        //if input is valid, then you can't print anything...
        String pl;
        switch(place)
        {
            case 2:
            {
                pl = "2nd";
                break;
            }
            case 3:
            {
                pl = "3rd";
                break;
            }
            case 4:
            {
                pl = "4th";
                break;
            }
            default:
                pl = "2nd";
        }
        System.out.println("\t|");
        //not a part of speech
        if(!pos)
            System.out.println("\t<The entered " +pl+ " parameter \"" 
                        +in+"\" is NOT a part of speech.>");
        //not "distinct"
        if(!dist)
            System.out.println("\t<The entered " +pl+ " parameter \"" 
                        +in+"\" is NOT 'distinct.'>");
        //not "reverse"
        if(!rev)
            System.out.println("\t<The entered " +pl+ " parameter \"" 
                        +in+"\" is NOT 'reverse.'>");
        //none of them
        if(!pos && !dist && !rev)
            System.out.println("\t<The "+pl+" parameter should be a part of speech," +
                                    "'distinct,' or 'reverse'>");
        
        //Always print
        System.out.println("\t<The entered " +pl+ " parameter \"" 
                        +in+"\" was disregarded.>");
        System.out.println("\t|");
    }    
    
    /*
    checkSameInput() returns true if the user input the same search as their 
    previous search. 
    Returns false if the search inputs are different
    */
    private boolean checkSameInput(String[] currInput)
    {
        int min;
        if(currInput.length < lastInput.length)
            min = currInput.length;
        else
            min = lastInput.length;
        
        int numSame = 0;
        for(int i = 0; i < min; i++)
        {
            if(lastInput[i].equals(currInput[i]))
                numSame++;
        }
        
        
        
        if(numSame == currInput.length)
        {
            //Changes the global variable to what the newest search is
            lastInput = currInput;
            return true;
        }
        else
        {
            //Changes the global variable to what the newest search is
            lastInput = currInput;
            return false;
        }
    }
}
