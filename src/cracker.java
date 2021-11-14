import java.io.*;
import java.lang.StringBuilder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class cracker {

    /*
    Cracker Section
    --------------------------------------------------------
    * We assume, for sentences and phrases, that SPACE is
    * the most used character, according to several sources:
    * https://github.com/piersy/ascii-char-frequency-english
    * http://www.fitaly.com/board/domper3/posts/136.html
    * http://millikeys.sourceforge.net/freqanalysis.html
    * Since SPACE seems to be consistently the most frequent ascii character in the english language,
    * this cracker will apply such an assumption to its calculations.
    --------------------------------------------------------
    */ 

    public static void bruteForce( String encryptedText ) {
        //96, as we are operating on ascii 32 to 127
        // shift of 0 or 96 would result in no change
        for (int shift = 1; shift < 96; shift++){
            System.out.println("shift: " + shift + "\n" + decipher(encryptedText, shift) + "\n");
        }
    }

    public static void crackcipher( String encryptedText ){

        //map frequencies of each character ocurrence in given plaintext
        Map<Character,Integer> frequencies = new HashMap<>();
        for (char c : encryptedText.toCharArray()){
            frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
        }
        //System.out.println(frequencies);

        //get character with highest value
        Entry<Character,Integer> maxEntry = null;
        for(Entry<Character,Integer> entry : frequencies.entrySet()){
            if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                maxEntry = entry;
            }
        }
        //Assuming the max frequency character is the space...
        char maxFreq;
        int probableShiftValue;

        maxFreq = maxEntry.getKey();
        //System.out.println( (int)maxFreq );
        probableShiftValue = (int)maxFreq - 32;

        System.out.println("Your key might be: " + probableShiftValue);
        System.out.println("Your deciphered text might be:\n" + decipher( encryptedText, probableShiftValue) );
    }

    /*
    --------------------------------------------------------
    * Decryption (Code Reuse. Check caesardec.java for comments) 
    * Shifts each character backwards by provided number (key)
    --------------------------------------------------------
    */
    private static StringBuilder decipher( String encText, int key ){

        StringBuilder decText = new StringBuilder();
        for( char element:encText.toCharArray() ){

            int currentChar = (int)element;
            
            if (currentChar > 32){
                currentChar -= key;
                if (currentChar < 32){
                    int temp = 32 - currentChar; 
                    currentChar = 127-temp;
                    decText.append( (char)currentChar );
                } else {
                    decText.append( (char)currentChar );
                }
            } else {
                if (currentChar == 32){
                    currentChar = 127 - key;
                    decText.append( (char)currentChar );
                }
                if (currentChar == 10){
                    decText.append( (char)currentChar );
                }
            }
        }
        return decText;
    }

    /*
    -------------------------------------------------------- 
    * JFileChooser used as a means to process text files.
    * That is an option, user can opt to type into terminal
    * With the file chooser method, it is assumed that the text file
    * is like the sample test data provided.
    --------------------------------------------------------
    */
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
        System.out.print( "Text or process from a text file? Enter 0 if text, or 1 if file.\n");
        
        int inputTF = Integer.parseInt( br.readLine() );
    
        if (inputTF == 1){
            
            System.out.println("Enter filepath: ( .txt files only)\n");
            File plaintextFile = new File( br.readLine() );

            try ( BufferedReader userFile = new BufferedReader( new FileReader( plaintextFile ))) {

                StringBuilder textfromFile = new StringBuilder();
                String line; 
                //Assuming the sample text data is the format for testing with text files,
                //it is assumed the key is provided in the first line of the text:
                int keyfromFile = Integer.valueOf( userFile.readLine() ); //<-- get an int of the first line. This is the key.
                System.out.println("Your key is: " + keyfromFile);
                line = userFile.readLine();//<-- this gives the second line onwards
                while (line != null ){
                    textfromFile.append(line);
                    textfromFile.append(System.lineSeparator());
                    line = userFile.readLine();
                }

                String completeText = textfromFile.toString();
                System.out.print("Your ciphertext is:\n" + completeText);
                crackcipher(completeText);
                System.out.print("\nDid I get it right? Enter yes or no. \nIf no, I will attempt to brute force.");
                String rightwrong = br.readLine();
                if (rightwrong.equals("yes")){
                    System.out.println("Full marks? Hehe :P");
                } else {
                    bruteForce(completeText);
                }
            }
            
        } else {
            System.out.print( "Ciphertext:" );
            String text = br.readLine();
            crackcipher(text);
            System.out.println("\nDid I get it right? Enter [yes/no].\n");
            String rightwrong = br.readLine();
            if (rightwrong.equals("yes")){
                System.out.println("Full marks? Hehe :P");
            } else {
                System.out.println("Attempting brute force...");
                bruteForce(text);
                System.out.println("Brute force complete.");
            }
        }
    }
    
}
