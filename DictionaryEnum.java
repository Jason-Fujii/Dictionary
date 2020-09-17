/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmingmethodology.Assignment2;

/**
 *
 * @author jasonfujii
 */

public enum DictionaryEnum{
    
    //Define the enum entries here
    Arrow("Arrow", "noun", "Here is one arrow"),
    Book("Book", "noun", "A set of pages"),
    Book2("Book", "noun", "A written work published in printed or electronic form"),
    Book3("Book", "verb", "To arrange for someone to have a seat on a plane"),
    Book4("Book", "verb", "To arrange something on a particular date."),
    Distinct("Distinct", "adjective", "Familiar. Worked in Java."),
    Distinct2("Distinct", "adjective", "Unique. No duplicates. Clearly different or of a different kind."),
    Distinct3("Distinct", "adverb", "Uniquely. Written \"distinctly\"."),
    Distinct4("Distinct", "noun", "A keyword in this assignment."),
    Distinct5("Distinct", "noun", "An advanced search option."),
    Distinct6("Distinct", "noun", "Distinct is a parameter in this assignment"),
    Placeholder("Placeholder", "noun", "To be updated..."),
    Reverse();
    
    //enum declaration/initialization
    private String word;
    private String partOfSpeech;
    private String definition;
    
    //Constructors
    private DictionaryEnum()
    {
        word = "Placeholder";
        partOfSpeech = randPOS();
        definition = "To be updated...";
    }
    
    private DictionaryEnum(String w, String p, String d)
    {
        word = w;
        partOfSpeech = p;
        definition = d;
    }
    
    //accessors
    public String getWord()
    {
        return word;
    }
    
    public String getPOS()
    {
        return partOfSpeech;
    }
    
    public String getDef()
    {
        return definition;
    }
    
    public void setWord(String w)
    {
        word = w;
    }
    
    public void setPOS(String p)
    {
        partOfSpeech = p;
    }
    
    public void setDef(String d)
    {
        definition = d;
    }
    
    @Override
    public String toString()
    {
        return this.word + " [" + this.partOfSpeech + "] : " + this.definition;
    }
   
    //An enum to help method randPOS()
    enum pos {NOUN, ADJ, ADV, CONJ, INTER, PREP, PRO, VERB};
    //          0    1    2    3      4     5     6    7
    
    //randPOS() uses the above enum to return a part of speech at random
    private String randPOS()
    {
        int rand = (int)Math.random()*8;
        switch(rand)
        {
            case 0: 
                return "noun";
            case 1:
                return "adjective";
            case 2:
                return "adverb";
            case 3:
                return "conjunction";
            case 4: 
                return "interjunction";
            case 5:
                return "preposition";
            case 6:
                return "pronoun";
            case 7:
                return "verb";
        }
        return "pronoun";
    }
}
