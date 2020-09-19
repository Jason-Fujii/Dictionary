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
enum pos {NOUN, ADJ, ADV, CONJ, INTER, PREP, PRO, VERB};
/*
This class is essentially a toned down version of DictionaryEnum. DictionaryEnum
is where I will change all of the entries, and Entry is what I will turn the Enum 
values to input them into the interactive dictionary in HashList.
*/
public class Entry {
    private String word;
    private String partOfSpeech;
    private String definition;
    
    public Entry()
    {
        word = "Placeholder";
        partOfSpeech = randPOS();
        definition = "To be updated...";
    }
    public Entry(DictionaryEnum DEnum)
    {
        word = DEnum.getWord();
        partOfSpeech = DEnum.getPOS();
        definition = DEnum.getDef();
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
    
    @Override
    public String toString()
    {
        return word + " [" + partOfSpeech + "] : " + definition;
    }
    
    public boolean compareTo(Entry e)
    {
        return this.word.equals(e.getWord()) && this.definition.equals(e.definition) && this.partOfSpeech.equals(e.partOfSpeech);
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
