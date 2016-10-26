import java.util.*;
import java.io.*;
import java.util.regex.*;

/**
 * Info-I202 Lab 5
 * Lab5.java
 * This program retrieves a text file containing a list of words. These words are stored in an array containing linked lists. Another text file - a book, for 
 * example - is also retrieved. As it is being read, only words are retrieved and stored within an Array List of a String type. It also puts the words into a
 * lower case format. Once all the words from the text file is stored within the array list, each word within the array list is compared to the dictionary to 
 * check to see whether or not it is correctly spelled or not. Each time a correctly spelled word is found, a counter is increased by one. This is true for 
 * words that are not correctly spelled, except another counter is increased instead. Each word usually takes multiple comparisons to the dictionary to find 
 * whether or not it is correctly spelled or not. Each such comparison is also kept track of using a counter. At the end of the program, the numbers of these
 * counters are displayed. 
 * 
 * @author Michael Popplewell
 */

public class ProgrammingAssignment4 {
    public static void main(String[] args) {
        
        //counters
        long wordsFound = 0, wordsNotFound = 0, compsFound = 0, compsNotFound = 0;
        
        
        /**
         * Reads text file containing the words for the dictionary and stores the words within an array of MyLinkedList[] objects. 
         */
        
        File f = new File("random_dictionary.txt");
        MyLinkedList[] dictionary = new MyLinkedList[26];
        
        for(int i = 0; i <= 25; i++)
            dictionary[i] = new MyLinkedList<>();

        try {
            Scanner input = new Scanner(f);
            while (input.hasNext()){
                String value = input.next();
                value = value.toLowerCase();
                dictionary[value.charAt(0) - 97].add(value);
            } // while
            input.close();
        } // try
        
        catch (IOException e){
            System.out.println("Error reading file.");
        } // catch
        
        /**
          * Reads the text to be analyzed by the dictionary. Trims the words to a basic form using the 'delims' string, delimiters. I learned the concept from 
          * the following link: 
          * http://pages.cs.wisc.edu/~hasti/cs302/examples/Parsing/parseString.html
          * This section of code also checks to make sure that each 'word' isn't of void value and that it has a length more than 0 before adding it to
          * the List object (list).
          * 
         */
        
        File g = new File("oliver.txt");
        List<String> list = new ArrayList<String>();
        
        try {
            Scanner input = new Scanner(g);
            
            String delims = "[ /,.!?*0123456789#--\";:]+";
            while (input.hasNext()){
                String value = input.next();
                String[] words = value.split(delims);
                for (int i = 0; i < words.length; i++){
                    if (words[i] != null && words[i].length() > 0)
                        list.add(words[i].toLowerCase());
                } // for
            } // while
            input.close();
        } // try
        catch(IOException e) {
            System.out.println("Error reading file.");
        } // catch
        
        /**
         * This is where the magic happens. Once the words have been properly organized, the code moves to this section where the List object (list) is converted
         * to an array (arrayWords). This array containing the words to be analyzed is then searched through in a linear fashion using a 'for' loop. Nested
         * within this 'for' loop is an 'if' statement to check to see if the first character of each word is a part of the alphabet. Once that check is complete,
         * another if statement tests whether the word is contained within 'dictionary' - the array that contains the Linked Lists containing words. If this is 
         * true, 'wordsFound' and 'commpsFound' is incremented. If not, 'wordsNotFound' and compsNotFound' is incremented. 
         * 
         * Once every word has been analyzed, the counters are outputted. 
         */
        
        String[] arrayWords = list.toArray(new String[list.size()]);
        int[] count = {0};
        
        for (int i = 0; i < arrayWords.length; i++){
            if (arrayWords[i].charAt(0) - 97 > 0 && arrayWords[i].charAt(0) - 97 <= 26) {
                if (dictionary[arrayWords[i].charAt(0) - 97].contains(arrayWords[i], count)) {
                    wordsFound++;
                    compsFound += count[0];
                }
                else 
                    wordsNotFound++;
                    compsNotFound += count[0];
                
            } // if
        } // for
        
        long averageWordsFound = compsFound / wordsFound;
        long averageWordsNotFound = compsNotFound / wordsNotFound;
        
        System.out.println("Words Found: " + wordsFound);
        System.out.println("Words Not Found: " + wordsNotFound);
        System.out.println("Average Comparisons of Words Found " + averageWordsFound);
        System.out.println("Average Comparisons of Words Not Found " + averageWordsNotFound);
    } // main
} // class
