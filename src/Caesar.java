import java.io.*;
import java.lang.StringBuilder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;


class Caesar {

    /*
    --------------------------------------------------------
    * Encryption: 
    * Shifts each character forward by provided number (key)
    --------------------------------------------------------
    */ 
    private static StringBuilder encipher( String text, int key ){
        
        StringBuilder encText = new StringBuilder();

        for( char element:text.toCharArray() ){

            int currentChar = (int)element;
                currentChar += key;
                //if current character > 127, get overflow value and add to 32 ('SPACE')
                if (currentChar > 127){
                    int temp = currentChar - 127;
                    temp += 32;
                    currentChar = temp;
                    encText.append( (char)currentChar);
                } else {
                    encText.append( (char)currentChar);
                }
        }

        return encText;
    }


    /*
    --------------------------------------------------------
    * Decryption: 
    * Shifts each character backwards by provided number (key)
    --------------------------------------------------------
    */
    private static StringBuilder decipher( String encText, int key ){

        StringBuilder decText = new StringBuilder();
        for( char element:encText.toCharArray() ){

            int currentChar = (int)element;
                //check if current character is less than 32 after shift
                if ( (currentChar - key) < 32 ){
                //if so, do the steps to the encryption overflow in reverse:
                //find difference by subtracting 32, add to 127 and subtract by key value
                    int temp = currentChar - 32;
                    temp += 127;
                    currentChar = temp - 91;
                    decText.append( (char)currentChar );
                } else {
                    currentChar -= key;
                    decText.append( (char)currentChar );
                }
        }
        return decText;
    }


    /*
    Cracker Section
    --------------------------------------------------------
    * This method derives the assumption that the spacebar is
    * the most used character, according to:
    * 
    --------------------------------------------------------
    */ 
    public static void checkFrequency( String encryptedText ){

        //map frequencies of each character ocurrence in given plaintext
        Map<Character,Integer> frequencies = new HashMap<>();
        for (char c : encryptedText.toCharArray())
            frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
        //System.out.println( frequencies );

        //get character with highest value
        Entry<Character,Integer> maxEntry = null;
        for(Entry<Character,Integer> entry : frequencies.entrySet()){
            if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                maxEntry = entry;
            }
        }
        //System.out.println( maxEntry.getKey() );

        //Assuming the max frequency character is the space.
        char maxFreq;
        int estimatedShift = 0;
        //int shift2 = 0;

        maxFreq = maxEntry.getKey();
        estimatedShift = ( (int)maxFreq - 32 ) + 30;
        decipher(encryptedText, estimatedShift);

        /*
        Pattern pat1 = Pattern.compile( ".*" + "\\" + maxEntry.getKey() + "(.)" + "\\" + maxEntry.getKey() + ".*" );
        Matcher matcher = pat1.matcher( encryptedText );
        if ( matcher.find()){
            ai = (matcher.group(1)).charAt(0);
            shift2 = ( (int)ai - 32 ) + 30;
            decipher(encryptedText, shift2);
            //System.out.println(shift);
            //System.out.println(matcher.group(1));
        } else {
            System.out.println("no");
        }
        */
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
        System.out.print( "Key:" );
        int key = Integer.parseInt( br.readLine() );
        System.out.print( "Text:" );
        String text = br.readLine();
        StringBuilder encText = encipher( text, key );
        System.out.println(encText);
        System.out.println( decipher( encText.toString(), key ) );
        checkFrequency( encText.toString() );
    }
}
