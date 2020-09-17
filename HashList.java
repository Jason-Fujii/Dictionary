/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmingmethodology.Assignment2;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
/**
 *
 * @author jasonfujii
 */
public class HashList {
    private Hashtable<String, ArrayList<Entry>> table;
    private final int MAX_VAL = 100;
    
    public HashList()
    {
        table = new Hashtable<String, ArrayList<Entry>>(MAX_VAL);
    }
    
    /*
    add() takes an Entry object and inserts it into our HashList. 
    Check if the word is already a key in the hashtable
        if yes, add the rest of the entry to the ArrayList
        if no, add the key, then create an ArrayList
    */
    public void add(Entry entry)
    {
        String key = entry.getWord();
        if(table.containsKey(key))
        {
            table.get(key).add(entry);
        }
        else
        {
            ArrayList<Entry> list = new ArrayList<Entry>();
            table.put(key, list);
            table.get(key).add(entry);
        }
    }
    
    /*
    remove(key) removes all entries at the specified key
    */
    public void remove(String key)
    {
        if(!table.isEmpty())
        {
            if(table.containsKey(key))
            {
                table.remove(key);
                if(!table.containsKey(key))
                    System.out.println("The key was successfully removed");
                else
                    remove(key);
            }
        }
        else
            System.out.println("The dictionary is empty...");
    }
    /*
    remove() removes an Entry object from the Hashlist
    */
    public void remove(Entry entry)
    {
        String key = entry.getWord();
        if(!table.isEmpty())
        {
            if(table.containsKey(key))
            {
                if(table.get(key).contains(entry))
                {
                    int index = table.get(key).indexOf(entry);
                    table.get(key).remove(index);
                    if(table.get(key).contains(entry))
                        remove(entry);
                    else
                        System.out.println("Entry succesfully removed");
                }
            }
            else
                System.out.println("| \n<NOT FOUND> To be considered for the next release.\n|");
        }
        else
            System.out.println("The dictionary is empty...");
    }
    
    /*
    Search(key) is used when the user only inputs the word
    it searches table for the chosen key, then prints everything in that key's 
    ArrayList.
    If the key cannot be found, return a String such as: 
    <NOT FOUND> To be considered for the next release. 
    */
    public void Search(String key)
    {
        printKey(key);
    }
    
    /*
    Search(key, pos) is used when the user inputs 2 Strings
    it searches the table for the chosen key, then only prints anything that has
    the specified part of speech (pos)
    */
    public void Search(String key, String pos)
    {
        
    }
    
    /*
    Search(key, pos, dist) is used when the user inputs a key, a part of speech (pos),
    and the qualifier "distinct"
    it searches the table for the chosen key, finds the values with the specified
    pos, then skips over any repeat values
    */
    public void Search(String key, String pos, String dist)
    {
        
    }
    
    /*
    Search(key, pos, dist, rev) is used when the user inputs 4 values: a key,
    a part of speech (pos), the qualifier "distinct," and the qualifier "reverse."
    it searches the table for the chosen key, finds the values with the specified
    pos, skips over and repeat values, and prints them in reverse order
    */
    public void Search(String key, String pos, String dist, String rev)
    {
        
    }
    
    //-------------------------------printAll doesn't work-------------------------------------------------------------
    /*
    Prints out the entirety of the table in the format:
    [word] [pos] : [desc]
    */
    public void printAll()
    {
        if(table.isEmpty())
            System.out.println("The dictionary is empty...");
        else
        {
            Set<String> keys = table.keySet();
            for(String k: keys)
            {
                for(int i = 0; i < table.get(k).size(); i++)
                {
                    System.out.println(table.get(k).get(i).toString());
                }
            }
        }
        
    }
    
    /*
    Prints out the arrayList at the value Key
    */
    public void printKey(String key)
    {
        if(table.isEmpty())
        {
            System.out.println("The dictionary is empty...");
        }
        else
        {
            if(table.containsKey(key))
            {
                for(int i = 0; i < table.get(key).size(); i++)
                {
                    System.out.println(table.get(key).get(i).toString());
                }
            }
            else
                System.out.println("| \n<NOT FOUND> To be considered for the next release.\n|");
        }
    }
    
    /*
    Prints out the values at the key "key" with the chosen part of speech
    */
    public void printPOS(String key, String pos)
    {
        if(table.isEmpty())
            System.out.println("The dictionary is empty...");
        else
        {
            if(table.containsKey(key))
            {
                int count = 0;
                for(int i = 0; i < table.get(key).size(); i++)
                {
                    if(table.get(key).get(i).getPOS().equals(pos))
                    {
                        System.out.println(table.get(key).get(i).toString());
                        count++;
                    }
                }
                if(count == 0)
                    System.out.println("| \n<NOT FOUND> To be considered for the next release.\n|");
            }
            else
                System.out.println("| \n<NOT FOUND> To be considered for the next release.\n|");
        }
    }
    
    /*
    qualifiedPrint(key, dist, rev) is a printing method that accounts for the 
    qualifiers distinct and reverse. If distinct is true, qualifiedPrint makes sure
    that no repeats entries are printed. If rev is true, the list will print 
    in reverse.
    */
    public void qualifiedPrint(String key, String pos, boolean dist, boolean rev)
    {
        if(!table.isEmpty())
        {
            if(table.containsKey(key))
            {
                //dist & rev are *BOTH* true
                //flip the list first, then do distinct
                //Make a set (no repeat entries), iterate through the ArrayList backwards, then print the set. Then delete the set
                if(dist && rev)
                {
                    
                }
                //only dist is true
                //do distinct
                //Make a set (no repeat entries), then print out the elements Then delete the set
                else if(dist)
                {
                    Set<Entry> entries = new HashSet<Entry>();
                    
                }
                //only rev is true
                //reverse the list
                //iterate through the arraylist backwards
                else if(rev)
                {
                    
                }
                //both are false
                //call print
                else
                {
                    printPOS(key, pos);
                }
            }
            else
                System.out.println("| \n<NOT FOUND> To be considered for the next release.\n|");
        }
        else
            System.out.println("The dictionary is empty...");
    }
}


/*
Hashtable methods to remember:
    - contains(Object val): returns true if some key matches val
    - containsKey(Object key): returns true if the specific key is in the hashtable
    - get(Object key): returns the object that is in the key value
        In our case, it'll be an ArrayList.
    - equals(object o): returns true if the object o is equal to the object value in the
        hashtable (according to the hashtable equals method)
    - isEmpty: returns true if the hashtable is empty
    - put(K key, V value): Maps the specified key to thespecified value in the hashtable
    - keys(): returns an enum of the keys in the hashtable
    - elements(): returns an enum of the elements in the hashtable
    - remove(Object key): Removes the key and its value from the hashtable
    - size(): returns the number of keys in the hashtable

Arraylist methods to remember
    - add(E e): appends the specified element to the end of the list
    - clear(): removes all of the elements from this list
    - contains(Object o): Returns true if this list contains the specified element
    - get(int index): returns the element at the specified position in this list
    - indexOf(Object o): returns the index of the first occurrence of the specified
      element in this list, or -1 if the list doesn't contain the element
    - isEmpty(): returns true if the list contains no elements
    - remove(int index): Removes the element at the specified position and returns that object
    - remove(Object o): Removes the first occurrence of the specified element from this list, if it's present
    - set(int index, E element): Replaces the element at the specified position in this list with the specified element
    - size(): returns the number of elements in the list
    - Collections.sort(ArrayList arr): sorts arr
    - 
*/
