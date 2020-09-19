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
        while(!quit)
        {
            System.out.print("Search [" + searchNum + "]:");
            String input = sc.nextLine();
            checkInput(input);
            searchNum++;
        }
    }
    
    public void checkInput(String userInput)
    {
        //Don't do toUpper or toLower to test if you actually need to do it
        //if there are issues, check here first
        String[] input = userInput.split(" ");
        if(input.length > 4)
            System.out.println("\t|\n\t<This dictionary accepts input of up to 4 words.>\n"
                    + "\t<Any words beyond that have been ignored.>\n\t|");
        //check if String is empty
        if(input[0].isEmpty() || input[0].charAt(0) == '!')
            printAdmin(input[0]);
        else
        {
            input[0] = input[0].toLowerCase();
            input[0] = input[0].substring(0, 1).toUpperCase() + input[0].substring(1);
            
            //check if there's a distinct in 2 or 3
            if(input.length == 1)
            {
                list.Search(input[0]);
            }
            if(input.length == 2)
            {
                if(checkDistinct(input[1]))
                    //send it to a search that has a word and a qualifier
                    list.Search(input[0], "", true, false);
                else if(checkReverse(input[1]))
                    list.Search(input[0], "", false, true);
                else if(checkPOS(input[1]))
                    list.Search(input[0], input[1]);
                else
                {
                    System.out.println("\t|\n\t<The entered 2nd parameter \"" 
                        +input[1]+"\" is NOT a part of speech.>");
                    System.out.println("\t<The entered 2nd parameter \"" 
                        +input[1]+"\" is NOT 'distinct.'>");
                    System.out.println("\t<The entered 2nd parameter \"" 
                        +input[1]+"\" is NOT 'reverse.'>");
                    System.out.println("\t<The entered 2nd parameter \"" 
                        +input[1]+"\" was disregarded.>");
                    System.out.println("\t<The 2nd parameter should be a part of speech"
                            + "or 'distinct' or 'reverse'>\n\t|");
                    list.Search(input[0]);
                }
            }
            else if(input.length == 3)
            {
                //check if distinct or reverse is in 2 or 3
                if(checkDistinct(input[1]))
                {
                    if(checkReverse(input[2]))
                        list.Search(input[0], "", true, true);
                    else if(checkPOS(input[2]))
                    {
                        System.out.println("\t|\n\t<The part of speech entered as the"
                                + " 3rd parameter has been disregarded.>");
                        System.out.println("\t<Parts of speech must be entered as the "
                                + "2nd parameter>\n\t|");
                        list.Search(input[0], "", true, false);
                    }
                    else
                    {
                        System.out.println("\t|\n\t<The entered 3rd parameter \'" 
                            +input[2]+"\" is NOT a part of speech.>");
                        System.out.println("\t<The entered 3rd parameter \'" 
                            +input[2]+"\" is NOT 'distinct.'>");
                        System.out.println("\t<The entered 3rd parameter \'" 
                            +input[2]+"\" is NOT 'reverse.'>");
                        System.out.println("\t<The entered 3rd parameter \'" 
                            +input[2]+"\" was disregarded.>");
                        System.out.println("\t<The 3rd parameter should be a part of speech"
                                + "or 'distinct' or 'reverse'>\n\t|");
                        list.Search(input[0]);
                    }
                }
                else if(checkReverse(input[1]))
                {
                    if(checkPOS(input[2]))
                    {
                        System.out.println("\t|\n\t<The part of speech entered as the"
                                + " 3rd parameter has been disregarded.>");
                        System.out.println("\t<Parts of speech must be entered as the "
                                + "2nd parameter>\n\t|");
                        list.Search(input[0], "", false, true);
                    }
                    else if(checkDistinct(input[2]))
                    {
                        System.out.println("\t|\n\t<'Distinct' has been entered after 'reverse.'>");
                        System.out.println("\t<'Distinct' has been disregarded.\n\t|>");
                        list.Search(input[0], "", false, true);
                    }
                    else
                    {
                        System.out.println("\t|\n\t<The entered 3rd parameter \'" 
                            +input[2]+"\" is NOT a part of speech.>");
                        System.out.println("\t<The entered 3rd parameter \'" 
                            +input[2]+"\" is NOT 'distinct.'>");
                        System.out.println("\t<The entered 3rd parameter \'" 
                            +input[2]+"\" is NOT 'reverse.'>");
                        System.out.println("\t<The entered 3rd parameter \'" 
                            +input[2]+"\" was disregarded.>");
                        System.out.println("\t<The 3rd parameter should be a part of speech"
                                + "or 'distinct' or 'reverse'>\n\t|");
                        list.Search(input[0], "", false, true);
                    }
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
                        System.out.println("\t|\n\t<The entered 3rd parameter \'" 
                            +input[2]+"\" is NOT a part of speech.>");
                        System.out.println("\t<The entered 3rd parameter \'" 
                            +input[2]+"\" is NOT 'distinct.'>");
                        System.out.println("\t<The entered 3rd parameter \'" 
                            +input[2]+"\" is NOT 'reverse.'>");
                        System.out.println("\t<The entered 3rd parameter \'" 
                            +input[2]+"\" was disregarded.>");
                        System.out.println("\t<The 3rd parameter should be 'distinct'"
                                + " or 'reverse'>\n\t|");
                        list.Search(input[0],input[1]);
                    }
                }
                else
                {
                    //not a valid entry
                    if(checkDistinct(input[2]))
                    {
                        System.out.println("\t|\n\t<The entered 2nd parameter \'" 
                            +input[1]+"\" is NOT a part of speech.>");
                        System.out.println("\t<The entered 2nd parameter \'" 
                            +input[1]+"\" is NOT 'distinct.'>");
                        System.out.println("\t<The entered 2nd parameter \'" 
                            +input[1]+"\" is NOT 'reverse.'>");
                        System.out.println("\t<The entered 2nd parameter \'" 
                            +input[1]+"\" was disregarded.>");
                        System.out.println("\t<The 2nd parameter should be a part of speech, "
                                + "'distinct,' or 'reverse'>\n\t|");
                        list.Search(input[0], "", true, false);
                    }
                    else if(checkReverse(input[2]))
                    {
                        System.out.println("\t|\n\t<The entered 2nd parameter \'" 
                            +input[1]+"\" is NOT a part of speech.>");
                        System.out.println("\t<The entered 2nd parameter \'" 
                            +input[1]+"\" is NOT 'distinct.'>");
                        System.out.println("\t<The entered 2nd parameter \'" 
                            +input[1]+"\" is NOT 'reverse.'>");
                        System.out.println("\t<The entered 2nd parameter \'" 
                            +input[1]+"\" was disregarded.>");
                        System.out.println("\t<The 2nd parameter should be a part of speech, "
                                + "'distinct,' or 'reverse'>\n\t|");
                        list.Search(input[0], "", false, true);
                    }
                    else
                    {
                        System.out.println("\t|\n\t<The entered 2nd parameter \'" 
                            +input[1]+"\" is NOT a part of speech.>");
                        System.out.println("\t<The entered 2nd parameter \'" 
                            +input[1]+"\" is NOT 'distinct.'>");
                        System.out.println("\t<The entered 2nd parameter \'" 
                            +input[1]+"\" is NOT 'reverse.'>");
                        System.out.println("\t<The entered 2nd parameter \'" 
                            +input[1]+"\" was disregarded.>");
                        System.out.println("\t<The 2nd parameter should be a part of speech, "
                                + "'distinct,' or 'reverse'>\n\t|");
                        System.out.println("\t|\n\t<The entered 3rd parameter \'" 
                            +input[2]+"\" is NOT a part of speech.>");
                        System.out.println("\t<The entered 3rd parameter \'" 
                            +input[2]+"\" is NOT 'distinct.'>");
                        System.out.println("\t<The entered 3rd parameter \'" 
                            +input[2]+"\" is NOT 'reverse.'>");
                        System.out.println("\t<The entered 3rd parameter \'" 
                            +input[2]+"\" was disregarded.>");
                        System.out.println("\t<The 3rd parameter should be a part of speech"
                                + "or 'distinct' or 'reverse'>\n\t|");
                        list.Search(input[0]);
                    }
                }
            }
            else if(input.length >= 4)
            {
                //check input[1]
                //check if distinct is in 2 or 3
                //check if reverse is in 2, 3, or 4
                if(checkDistinct(input[1]))
                {
                    if(checkReverse(input[2]))
                    {
                        //Search(input[0], "distinct", "reverse");
                        System.out.println("\t|\n\t<'Reverse' should be the last command>");
                        System.out.println("\t<The entered 4th parameter '"+input[3]+"' was"
                                + "disregarded.>\n\t|");
                        list.Search(input[0], "", true, true);
                    }
                    else if(checkPOS(input[2]))
                    {
                        System.out.println("\t|\n\t<The part of speech entered as the"
                                + " 3rd parameter has been disregarded.>");
                        System.out.println("\t<Parts of speech must be entered as the "
                                + "2nd parameter>");
                        System.out.println("\t<'Reverse' should be the last command>");
                        System.out.println("\t<The entered 4th parameter '"+input[3]+"' was"
                                + "disregarded.>\n\t|");
                        list.Search(input[0], "", true, false);
                    }
                    else
                    {
                        System.out.println("\t|\n\t<The entered 3rd parameter \'" 
                            +input[2]+"\" is NOT a part of speech.>");
                        System.out.println("\t<The entered 3rd parameter \'" 
                            +input[2]+"\" is NOT 'distinct.'>");
                        System.out.println("\t<The entered 3rd parameter \'" 
                            +input[2]+"\" is NOT 'reverse.'>");
                        System.out.println("\t<The entered 3rd parameter \'" 
                            +input[2]+"\" was disregarded.>");
                        System.out.println("\t<The 3rd parameter should be a part of speech"
                                + "or 'distinct' or 'reverse'>\n\t|");
                        System.out.println("\t|\n\t<The entered 4th parameter \'" 
                            +input[3]+"\" is NOT a part of speech.>");
                        System.out.println("\t<The entered 4th parameter \'" 
                            +input[3]+"\" is NOT 'distinct.'>");
                        System.out.println("\t<The entered 4th parameter \'" 
                            +input[3]+"\" is NOT 'reverse.'>");
                        System.out.println("\t<The entered 4th parameter \'" 
                            +input[3]+"\" was disregarded.>");
                        System.out.println("\t<The 4th parameter should be 'reverse'>\n\t|");
                        list.Search(input[0], "", true, false);
                    }
                }
                else if(checkReverse(input[1]))
                {
                    if(checkPOS(input[2]))
                    {
                        System.out.println("\t|\n\t<The part of speech entered as the"
                                + " 3rd parameter has been disregarded.>");
                        System.out.println("\t<Parts of speech must be entered as the "
                                + "2nd parameter>\n\t|");
                        System.out.println("\t<'Reverse should be the last qualifier included.'>");
                        System.out.println("\t<The 4th parameter '"+input[3]+"' has been disregarded.>\n\t|");
                        list.Search(input[0], "", true, false);
                    }
                    else if(checkDistinct(input[2]))
                    {
                        System.out.println("\t|\n\t<'Distinct' has been entered after 'reverse.'>");
                        System.out.println("\t<'Distinct' has been disregarded.\n\t|>");
                        System.out.println("\t<'Reverse should be the last qualifier included.'>");
                        System.out.println("\t<The 4th parameter '"+input[3]+"' has been disregarded.>\n\t|");
                        list.Search(input[0], "", false, true);
                    }
                    else
                    {
                        System.out.println("\t|\n\t<The entered 3rd parameter \'" 
                            +input[2]+"\" is NOT a part of speech.>");
                        System.out.println("\t<The entered 3rd parameter \'" 
                            +input[2]+"\" is NOT 'distinct.'>");
                        System.out.println("\t<The entered 3rd parameter \'" 
                            +input[2]+"\" is NOT 'reverse.'>");
                        System.out.println("\t<The entered 3rd parameter \'" 
                            +input[2]+"\" was disregarded.>");
                        System.out.println("\t<The 3rd parameter should be a part of speech"
                                + "or 'distinct' or 'reverse'>\n\t|");
                        System.out.println("\t|\n\t<The entered 4th parameter \'" 
                            +input[3]+"\" is NOT a part of speech.>");
                        System.out.println("\t<The entered 4th parameter \'" 
                            +input[3]+"\" is NOT 'distinct.'>");
                        System.out.println("\t<The entered 4th parameter \'" 
                            +input[3]+"\" is NOT 'reverse.'>");
                        System.out.println("\t<The entered 4th parameter \'" 
                            +input[3]+"\" was disregarded.>");
                        System.out.println("\t<The 4th parameter should be 'reverse'>\n\t|");
                        list.Search(input[0], "", false, true);
                    }
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
                            System.out.println("\t|\n\t<The entered 4th parameter is NOT 'reverse.'>");
                            System.out.println("\t<The entered 4th parameter '"+input[3]+"' has been disregarded.>\n\t|");
                            list.Search(input[0], input[1], true, false);
                        }
                    }
                    else if(checkReverse(input[2]))
                    {
                        //Search(input[0],input[1],"reverse");
                        System.out.println("\t|\n\t<'Reverse' should be the last command>");
                        System.out.println("\t<The entered 4th parameter '"+input[3]+"' was "
                                + "disregarded.>\n\t|");
                        list.Search(input[0], input[1], false, true);
                    }
                    else
                    {
                        System.out.println("\t|\n\t<The entered 3rd parameter \'" 
                            +input[2]+"\" is NOT a part of speech.>");
                        System.out.println("\t<The entered 3rd parameter \'" 
                            +input[2]+"\" is NOT 'distinct.'>");
                        System.out.println("\t<The entered 3rd parameter \'" 
                            +input[2]+"\" is NOT 'reverse.'>");
                        System.out.println("\t<The entered 3rd parameter \'" 
                            +input[2]+"\" was disregarded.>");
                        System.out.println("\t<The 3rd parameter should be a part of speech"
                                + "or 'distinct' or 'reverse'>\n\t|");
                        if(checkReverse(input[3]))
                        {
                            list.Search(input[0], input[1], false, true);
                            //Search(input[0[, input[1], "reverse");
                        }
                        else
                        {
                            System.out.println("\t|\n\t<The entered 4th parameter \'" 
                                +input[3]+"\" is NOT a part of speech.>");
                            System.out.println("\t<The entered 4th parameter \'" 
                                +input[3]+"\" is NOT 'distinct.'>");
                            System.out.println("\t<The entered 4th parameter \'" 
                                +input[3]+"\" is NOT 'reverse.'>");
                            System.out.println("\t<The entered 4th parameter \'" 
                                +input[3]+"\" was disregarded.>");
                            System.out.println("\t<The 4th parameter should be a part of speech"
                                    + "or 'distinct' or 'reverse'>\n\t|");
                            list.Search(input[0], input[1],false, false);
                        }
                    }
                }
                else
                {
                    //input[1] is bad
                    if(checkDistinct(input[2]))
                    {
                        if(checkReverse(input[3]))
                        {
                            System.out.println("\t|\n\t<The entered 2nd parameter \'" 
                                +input[1]+"\" is NOT a part of speech.>");
                            System.out.println("\t<The entered 2nd parameter \'" 
                                +input[1]+"\" is NOT 'distinct.'>");
                            System.out.println("\t<The entered 2nd parameter \'" 
                                +input[1]+"\" is NOT 'reverse.'>");
                            System.out.println("\t<The entered 2nd parameter \'" 
                                +input[1]+"\" was disregarded.>");
                            System.out.println("\t<The 2nd parameter should be a part of speech, "
                                    + "'distinct,' or 'reverse'>\n\t|");
                            list.Search(input[0], "", true, true);
                        }
                    }
                    else if(checkReverse(input[2]))
                    {
                        System.out.println("\t|\n\t<The entered 2nd parameter \'" 
                            +input[1]+"\" is NOT a part of speech.>");
                        System.out.println("\t<The entered 2nd parameter \'" 
                            +input[1]+"\" is NOT 'distinct.'>");
                        System.out.println("\t<The entered 2nd parameter \'" 
                            +input[1]+"\" is NOT 'reverse.'>");
                        System.out.println("\t<The entered 2nd parameter \'" 
                            +input[1]+"\" was disregarded.>");
                        System.out.println("\t<The 2nd parameter should be a part of speech, "
                                + "'distinct,' or 'reverse'>\n\t|");
                        System.out.println("\t|\n\t<'Reverse' should be the last command>");
                        System.out.println("\t<The entered 4th parameter '"+input[3]+"' was "
                                + "disregarded.>\n\t|");
                        list.Search(input[0], "", false, true);
                    }
                    else
                    {
                        if(checkDistinct(input[3]))
                        {
                            System.out.println("\t|\n\t<The entered 2nd parameter \'" 
                            +input[1]+"\" is NOT a part of speech.>");
                            System.out.println("\t<The entered 2nd parameter \'" 
                                +input[1]+"\" is NOT 'distinct.'>");
                            System.out.println("\t<The entered 2nd parameter \'" 
                                +input[1]+"\" is NOT 'reverse.'>");
                            System.out.println("\t<The entered 2nd parameter \'" 
                                +input[1]+"\" was disregarded.>");
                            System.out.println("\t<The 2nd parameter should be a part of speech, "
                                    + "'distinct,' or 'reverse'>\n\t|");
                            System.out.println("\t|\n\t<The entered 3rd parameter \'" 
                                +input[2]+"\" is NOT a part of speech.>");
                            System.out.println("\t<The entered 3rd parameter \'" 
                                +input[2]+"\" is NOT 'distinct.'>");
                            System.out.println("\t<The entered 3rd parameter \'" 
                                +input[2]+"\" is NOT 'reverse.'>");
                            System.out.println("\t<The entered 3rd parameter \'" 
                                +input[2]+"\" was disregarded.>");
                            System.out.println("\t<The 3rd parameter should be a part of speech"
                                    + "or 'distinct' or 'reverse'>\n\t|");
                            list.Search(input[0], "", true, false);
                        }
                        else if(checkReverse(input[3]))
                        {
                            System.out.println("\t|\n\t<The entered 2nd parameter \'" 
                            +input[1]+"\" is NOT a part of speech.>");
                            System.out.println("\t<The entered 2nd parameter \'" 
                                +input[1]+"\" is NOT 'distinct.'>");
                            System.out.println("\t<The entered 2nd parameter \'" 
                                +input[1]+"\" is NOT 'reverse.'>");
                            System.out.println("\t<The entered 2nd parameter \'" 
                                +input[1]+"\" was disregarded.>");
                            System.out.println("\t<The 2nd parameter should be a part of speech, "
                                    + "'distinct,' or 'reverse'>\n\t|");
                            System.out.println("\t|\n\t<The entered 3rd parameter \'" 
                                +input[2]+"\" is NOT a part of speech.>");
                            System.out.println("\t<The entered 3rd parameter \'" 
                                +input[2]+"\" is NOT 'distinct.'>");
                            System.out.println("\t<The entered 3rd parameter \'" 
                                +input[2]+"\" is NOT 'reverse.'>");
                            System.out.println("\t<The entered 3rd parameter \'" 
                                +input[2]+"\" was disregarded.>");
                            System.out.println("\t<The 3rd parameter should be a part of speech"
                                    + "or 'distinct' or 'reverse'>\n\t|");
                            list.Search(input[0], "", false, true);
                        }
                        else
                        {
                            System.out.println("\t|\n\t<The entered 2nd parameter \'" 
                            +input[1]+"\" is NOT a part of speech.>");
                            System.out.println("\t<The entered 2nd parameter \'" 
                                +input[1]+"\" is NOT 'distinct.'>");
                            System.out.println("\t<The entered 2nd parameter \'" 
                                +input[1]+"\" is NOT 'reverse.'>");
                            System.out.println("\t<The entered 2nd parameter \'" 
                                +input[1]+"\" was disregarded.>");
                            System.out.println("\t<The 2nd parameter should be a part of speech, "
                                    + "'distinct,' or 'reverse'>\n\t|");
                            System.out.println("\t|\n\t<The entered 3rd parameter \'" 
                                +input[2]+"\" is NOT a part of speech.>");
                            System.out.println("\t<The entered 3rd parameter \'" 
                                +input[2]+"\" is NOT 'distinct.'>");
                            System.out.println("\t<The entered 3rd parameter \'" 
                                +input[2]+"\" is NOT 'reverse.'>");
                            System.out.println("\t<The entered 3rd parameter \'" 
                                +input[2]+"\" was disregarded.>");
                            System.out.println("\t<The 3rd parameter should be a part of speech"
                                    + "or 'distinct' or 'reverse'>\n\t|");
                            System.out.println("\t|\n\t<The entered 4th parameter \'" 
                                +input[3]+"\" is NOT a part of speech.>");
                            System.out.println("\t<The entered 4th parameter \'" 
                                +input[3]+"\" is NOT 'distinct.'>");
                            System.out.println("\t<The entered 4th parameter \'" 
                                +input[3]+"\" is NOT 'reverse.'>");
                            System.out.println("\t<The entered 4th parameter \'" 
                                +input[3]+"\" was disregarded.>");
                            System.out.println("\t<The 4th parameter should be a part of speech"
                                    + "or 'distinct' or 'reverse'>\n\t|");
                            list.Search(input[0]);
                        }
                    }
                }
            }
            //check if there's a reverse in 2, 3, or 4
            //send the first word to the dictionary
        }    
            
    }
    
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
    }
    
    public boolean checkPOS(String str)
    {
        String s = str.toLowerCase();
        return (s.equals("noun") || s.equals("adjective") || s.equals("adverb") 
                || s.equals("conjunction") || s.equals("interjection") 
                || s.equals("preposition") || s.equals("pronoun") || s.equals("verb"));
            
    }
    
    public boolean checkDistinct(String str)
    {
        String s = str.toLowerCase();
        return s.equals("distinct");
    }
    
    public boolean checkReverse(String str)
    {
        String s = str.toLowerCase();
        return s.equals("reverse");
    }
}
