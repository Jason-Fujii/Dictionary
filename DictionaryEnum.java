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

/**
 *
 * @author jasonfujii
 */

public enum DictionaryEnum{
    
    //Define the enum entries here
    Arrow("Arrow", "noun", "Here is one arrow: <IMG> -=>> </IMG>"),
    Book("Book", "noun", "A set of pages"),
    Book2("Book", "noun", "A written work published in printed or electronic form"),
    Book3("Book", "verb", "To arrange for someone to have a seat on a plane"),
    Book4("Book", "verb", "To arrange something on a particular date."),
    Distinct("Distinct", "adjective", "Familiar. Worked in Java."),
    Distinct2("Distinct", "adjective", "Unique. No duplicates. Clearly different or of a different kind."),
    Distinct3("Distinct", "adverb", "Uniquely. Written \"distinctly\"."),
    Distinct4("Distinct", "noun", "A keyword in this assignment."),
    Distinct7("Distinct", "noun", "A keyword in this assignment."),
    Distinct8("Distinct", "noun", "A keyword in this assignment."),
    Distinct5("Distinct", "noun", "An advanced search option."),
    Distinct6("Distinct", "noun", "Distinct is a parameter in this assignment"),
    Placeholder("Placeholder", "adjective", "To be updated..."),
    Placeholder2("Placeholder", "adjective", "To be updated..."),
    Placeholder3("Placeholder", "adverb", "To be updated..."),
    Placeholder4("Placeholder", "conjunction", "To be updated..."),
    Placeholder5("Placeholder", "interjection", "To be updated..."),
    Placeholder6("Placeholder", "noun", "To be updated..."),
    Placeholder7("Placeholder", "noun", "To be updated..."),
    Placeholder8("Placeholder", "noun", "To be updated..."),
    Placeholder9("Placeholder", "preposition", "To be updated..."),
    Placeholder10("Placeholder", "pronoun", "To be updated..."),
    Placeholder11("Placeholder", "verb", "To be updated..."),
    Reverse("Reverse","adjective","On back side."),
    Reverse2("Reverse","adjective","Opposite to usual or previous arrangement"),
    Reverse3("Reverse","noun","A dictionary program's parameter."),
    Reverse4("Reverse","noun","Change to opposite direction."),
    Reverse5("Reverse","noun","The opposite."),
    Reverse6("Reverse","noun","To be updated..."),
    Reverse7("Reverse","noun","To be updated..."),
    Reverse8("Reverse","noun","To be updated..."),
    Reverse9("Reverse","noun","To be updated..."),
    Reverse10("Reverse","verb","Change something to opposite."),
    Reverse11("Reverse","verb","Go back"),
    Reverse12("Reverse","verb","Revoke ruling."),
    Reverse13("Reverse","verb","To be updated..."),
    Reverse14("Reverse","verb","To be updated..."),
    Reverse15("Reverse","verb","Turn something inside out."),
    Dinosaur("Dinosaur", "noun", "A fossil reptile of the Mesozoic era, in many species"
            + " reaching an enormous size."),
    Dinosaur2("Dinosaur", "noun", "A person or thing that is outdated or has become"
            + " obsolete because of failure to adapt to changing circumstances."),
    Cartoon("Cartoon", "noun", "A simple drawing showing the features of its subjects"
            + " in a humorously exaggerated way, especially in a satirical one in a newspaper"
            + " or magazine"),
    Cartoon2("Cartoon", "verb", "Make a drawing of something or someone in a simplified "
            + "or exaggerated way."),
    Rotund("Rotund","adjective","Plump"),
    Gelatin("Gelatin","noun","A virtually colorless and tasteless water-soluble protein"
            + " prepared from collagen and used in food preparation as the basis of "
            + "jellies, in photographic processes, and in glue."),
    Engineer("Engineer","noun","A person who designs, builds, or maintains engines,"
            + " machines, or public works."),
    Engineer2("Engineer", "verb", "Design and build (a machine or structure)"),
    Everything("Everything", "pronoun", "All things; all the things of a group or class"),
    Hashtable("Hashtable", "noun", "A data structure that can map keys to values"),
    Detector("Detector","noun","A device or instrument designed to detect the presence of"
            + "a particular object or substance and to emit a signal in response"),
    Tone("Tone","noun","A musical or vocal sound with reference to its pitch, quality, and strength"),
    Tone4("Tone","noun", "The general character or attitude of a place, piece of writing, situation, etc."),
    Tone2("Tone","verb","Give greater strength or firmness to (the body or a part of it)"),
    Tone3("Tone", "verb", "Harmonize with something in terms of color"),
    Bathroom("Bathroom","noun", "A room containing a toilet and sink and typically also a "
            + "bathtub or shower."),
    Write("Write", "Verb", "Mark (letters, words, or other symbols) on a surface, typically"
            + "paper, with a pen, pencil, or similar"),
    Write2("Write", "Verb", "Compose, write, and send (a letter) to someone"),
    Affair("Affair","noun","An event or sequence of events of a specified kindor that has previously"
            + "been referred to"),
    Chimpanzee("Chimpanzee", "noun", "A great ape with large ears, mainly black coloration,"
            + "and lighter skin on the face, native to the forests of western and "
            + "central Africa. "),
    Coding("Coding","noun", "The process of assigning a code to something for the purposes "
            + "of classification or identification"),
    Coding2("Coding", "noun", "The process of coding genetically for an amino acid, protein, or characteristic.");
    //61/61
    
    
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
    
    public String enumToEntry()
    {
        return this.word +" "+this.partOfSpeech+" "+this.definition;
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
