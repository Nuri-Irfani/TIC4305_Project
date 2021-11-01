package main;
import java.io.*;
import java.lang.StringBuilder;



class Caesar {
    private static StringBuilder encipher( String text, int key ){
        
        StringBuilder encText = new StringBuilder();

        for( char element:text.toCharArray() ){

            //leave non-alphabetic characters as is
            if( !Character.isAlphabetic(element) ){
                encText.append(element);
            }
            else{

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
                } else {
                    //if lowercase, subtract 97 ('a') to get range 0-25
                    int currentChar = (int)element - 'a';
                    currentChar += key;
                    currentChar = Math.floorMod(currentChar, 26);
                    currentChar += 'a';
                    encText.append( (char)currentChar );
                }
            }
        }

        return encText;
    }

    private static StringBuilder decipher( String encText, int key ){

        StringBuilder decText = new StringBuilder();
        for( char element:encText.toCharArray() ){
            if( !Character.isAlphabetic( element )){
                decText.append(element);
            }
            else{

                if( Character.isUpperCase(element)){
                    int currentChar = (int)element - 'A';
                    currentChar -= key;
                    currentChar = Math.floorMod(currentChar, 26);
                    currentChar += 'A';
                    decText.append( (char)currentChar );
                } else {
                    int currentChar = (int)element - 'a';
                    currentChar -= key;
                    currentChar = Math.floorMod(currentChar, 26);
                    currentChar += 'a';
                    decText.append( (char)currentChar );
                }
            }
        }
        return decText;
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
