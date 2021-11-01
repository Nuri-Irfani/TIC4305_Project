
import java.io.*;
import java.lang.StringBuilder;


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

            if( Character.isUpperCase(element)){
                //if uppercase, subtract 65 ('A') to get range between 0-25 for modulo-ing
                int currentChar = (int)element - 'A';
                //shift by key value
                currentChar += key;
                //perform modulo
                currentChar = Math.floorMod(currentChar, 26);
                //re-add 65
                currentChar += 'A';
                encText.append( (char)currentChar );
            } else if ( Character.isLowerCase(element) ) {
                //if lowercase, subtract 97 ('a') to get range 0-25
                int currentChar = (int)element - 'a';
                currentChar += key;
                currentChar = Math.floorMod(currentChar, 26);
                currentChar += 'a';
                encText.append( (char)currentChar );
            } else {
                //if non-alphabetical, just shift by key
                int currentChar = (int)element;
                currentChar += key;
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

            if( Character.isUpperCase(element)){
                int currentChar = (int)element - 'A';
                currentChar -= key;
                currentChar = Math.floorMod(currentChar, 26);
                currentChar += 'A';
                decText.append( (char)currentChar );
            } else if( Character.isLowerCase(element) ) {
                int currentChar = (int)element - 'a';
                currentChar -= key;
                currentChar = Math.floorMod(currentChar, 26);
                currentChar += 'a';
                decText.append( (char)currentChar );
            } else {
                int currentChar = (int)element;
                currentChar -= key;
                decText.append( (char)currentChar );
            }
        }
        return decText;
    }


    /*
    Cracker
    --------------------------------------------------------
    * Method: Character counter
    * Count how many times an alphabet appear in the string
    --------------------------------------------------------
    */ 
    public static int[] countChar(String textEncrypted) {
        //Only want alphabetic characters
        textEncrypted = textEncrypted.replaceAll("[^a-zA-Z]", "").toUpperCase();
        int[] counts = new int[26];
        for (char c : textEncrypted.toCharArray())
            counts[c - 'A']++;
        return counts;
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
    }
}
