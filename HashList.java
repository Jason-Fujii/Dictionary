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
    iterates through the enum, turns every element into an Entry object, and adds 
    that Entry object to the hashList.
    */
    public void addEnum()
    {
        for(DictionaryEnum vals: DictionaryEnum.values())
        {
            Entry entry = new Entry(vals);
            add(entry);
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
            System.out.println("\tThe dictionary is empty...");
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
            System.out.println("\tThe dictionary is empty...");
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
        System.out.println("\t|");
        printKey(key);
        System.out.println("\t|");
    }
    
    /*
    Search(key, pos) is used when the user inputs 2 Strings
    it searches the table for the chosen key, then only prints anything that has
    the specified part of speech (pos)
    */
    public void Search(String key, String pos)
    {
        System.out.println("\t|");
        printPOS(key, pos);
        System.out.println("\t|");
    }
    
    /*
    Search(key, pos, dist) is used when the user inputs a key, a part of speech (pos),
    and the qualifier "distinct"
    it searches the table for the chosen key, finds the values with the specified
    pos, then skips over any repeat values
    */
    public void Search(String key, String pos, boolean dist ,boolean rev)
    {
        if(pos.isEmpty() && dist == false && rev == false)
        {
            Search(key);
        }
        else if(dist == false && rev == false)
        {
            Search(key, pos);
        }
        else if(pos.isEmpty() && dist == false)
        {
            //reverse
            qualifiedPrint(key, "", false, true);
        }
        else if(pos.isEmpty() && rev == false)
        {
            //dist
            qualifiedPrint(key, "", true, false);
        }
        else if(dist == false)
        {
            //pos && rev
            qualifiedPrint(key, pos, false, true);
        }
        else if(rev == false)
        {
            //pos && dist
            qualifiedPrint(key, pos, true, false);
        }
        else
        {
            //pos, dist, && rev
            qualifiedPrint(key, pos, true, true);
        }
    }
    
    /*
    Prints out the entirety of the table in the format:
    [word] [pos] : [desc]
    */
    public void printAll()
    {
        if(table.isEmpty())
            System.out.println("\tThe dictionary is empty...");
        else
        {
            Set<String> keys = table.keySet();
            for(String k: keys)
            {
                for(int i = 0; i < table.get(k).size(); i++)
                {
                    System.out.println("\t" + table.get(k).get(i).toString());
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
            System.out.println("\tThe dictionary is empty...");
        }
        else
        {
            if(table.containsKey(key))
            {
                for(int i = 0; i < table.get(key).size(); i++)
                {
                    System.out.println("\t" + table.get(key).get(i).toString());
                }
            }
            else
                System.out.println("\t| \n\t<NOT FOUND> To be considered for the next release.\n\t|");
        }
    }
    
    /*
    Prints out the values at the key "key" with the chosen part of speech
    */
    public void printPOS(String key, String pos)
    {
        if(table.isEmpty())
            System.out.println("\tThe dictionary is empty...");
        else
        {
            if(table.containsKey(key))
            {
                int count = 0;
                for(int i = 0; i < table.get(key).size(); i++)
                {
                    if(table.get(key).get(i).getPOS().equals(pos))
                    {
                        System.out.println("\t" + table.get(key).get(i).toString());
                        count++;
                    }
                }
                if(count == 0)
                    System.out.println("\t| \n<NOT FOUND> To be considered for the next release.\n\t|");
            }
            else
                System.out.println("\t| \n<NOT FOUND> To be considered for the next release.\n\t|");
        }
    }
    
    public void printDistinct(ArrayList<Entry> list, String pos)
    {
        if(!pos.isEmpty())
        {
            int count = 0;
            for(Entry e: list)
            {
                if(e.getPOS().equals(pos))
                {
                    System.out.println("\t" + e.toString());
                    count++;
                }
            }
            if(count == 0)
                System.out.println("\t<NOT FOUND> To be considered for the next release.\n\t|");
        }
        else
        {
            for(Entry e: list)
            {
                System.out.println("\t" + e.toString());
            }
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
                if(!pos.isEmpty())
                {
                    //dist & rev are *BOTH* true
                    //flip the list first, then do distinct
                    //Make a set (no repeat entries), iterate through the ArrayList backwards, then print the set. Then delete the set
                    if(dist && rev)
                    {
                        ArrayList<Entry> distinct = distinctify(key);
                        System.out.println("\t|");
                        reversePrint(distinct, pos);
                        System.out.println("\t|");
                    }
                    //only dist is true
                    //do distinct
                    //Make a set (no repeat entries), then print out the elements Then delete the set
                    else if(dist)
                    {
                        ArrayList<Entry> distinct = distinctify(key);
                        System.out.println("\t|");
                        printDistinct(distinct, pos);
                        System.out.println("\t|");
                    }
                    //only rev is true
                    //reverse the list
                    //iterate through the arraylist backwards
                    else if(rev)
                    {
                        System.out.println("\t|");
                        reversePrint(table.get(key), pos);
                        System.out.println("\t|");
                    }
                    //both are false
                    //call print
                    else
                    {
                        System.out.println("\t|");
                        printPOS(key, pos);
                        System.out.println("\t|");
                    }
                }
                else
                {
                    //dist & rev are *BOTH* true
                    //flip the list first, then do distinct
                    //Make a set (no repeat entries), iterate through the ArrayList backwards, then print the set. Then delete the set
                    if(dist && rev)
                    {
                        ArrayList<Entry> distinct = distinctify(key);
                        System.out.println("\t|");
                        reversePrint(distinct, "");
                        System.out.println("\t|");
                    }
                    //only dist is true
                    //do distinct
                    //Make a set (no repeat entries), then print out the elements Then delete the set
                    else if(dist)
                    {
                        ArrayList<Entry> distinct = distinctify(key);
                        System.out.println("\t|");
                        printDistinct(distinct, "");
                        System.out.println("\t|");
                    }
                    //only rev is true
                    //reverse the list
                    //iterate through the arraylist backwards
                    else if(rev)
                    {
                        System.out.println("\t|");
                        reversePrint(table.get(key), "");
                        System.out.println("\t|");
                    }
                    //both are false
                    //call print
                    else
                    {
                        System.out.println("\t|");
                        printPOS(key, "");
                        System.out.println("\t|");
                    }
                }
            }
            else
                System.out.println("\t| \n\t<NOT FOUND> To be considered for the next release.\n\t|");
        }
        else
            System.out.println("\tThe dictionary is empty...");
    }
    
    public ArrayList<Entry> distinctify(String key)
    {
        
        ArrayList<Entry> entries = new ArrayList<Entry>();
        if(table.containsKey(key))
            {
            entries.add(table.get(key).get(0));
            for(int i = 0; i < table.get(key).size(); i++)
            {
                boolean dist = true;
                for(int set = 0; set < entries.size(); set++)
                {
                    if(table.get(key).get(i).compareTo(entries.get(set)))
                        dist = false;
                }
                if(dist)
                    entries.add(table.get(key).get(i));
            }
        }
        return entries;
    }
    
    public void reversePrint(ArrayList<Entry> list, String pos)
    {
        if(pos.isEmpty())
        {
            for(int i = list.size() - 1; i >= 0; i--)
            {
                System.out.println("\t" + list.get(i).toString());
            }
        }
        else
        {
            int count = 0;
            for(int i = list.size() - 1; i >= 0; i--)
            {
                if(list.get(i).getPOS().equals(pos))
                {
                    System.out.println("\t" + list.get(i).toString());
                    count++;
                }
            }
            if(count == 0)
                System.out.println("\t<NOT FOUND> To be considered for the next release. Thank you.");
        }
    }
}

//Entry.compareTo(Entry e);
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
