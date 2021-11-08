import java.io.*;
import java.lang.StringBuilder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class cracker {

    /*
    Cracker Section
    --------------------------------------------------------
    * This method derives the assumption that the spacebar is
    * the most used character, according to:
    * 
    --------------------------------------------------------
    */ 
    public static void crackcipher( String encryptedText ){

        //map frequencies of each character ocurrence in given plaintext
        Map<Character,Integer> frequencies = new HashMap<>();
        for (char c : encryptedText.toCharArray())
            frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);

        //get character with highest value
        Entry<Character,Integer> maxEntry = null;
        for(Entry<Character,Integer> entry : frequencies.entrySet()){
            if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                maxEntry = entry;
            }
        }

        //Assuming the max frequency character is the space.
        char maxFreq;
        int probableShiftValue;

        maxFreq = maxEntry.getKey();
        System.out.println( (int)maxFreq );
        probableShiftValue = (int)maxFreq - 32;

        System.out.println("Your key might be: " + probableShiftValue);
        System.out.println("Your deciphered text might be: " + decipher( encryptedText, probableShiftValue) );
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
                if ( (currentChar - key) < 32 ){
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


    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
        System.out.print( "Text or process from a text file? Enter 0 if text, or 1 if file.\n");
        
        int inputTF = Integer.parseInt( br.readLine() );
    
        if (inputTF == 1){
            
            File plaintextFile;
            JFileChooser chooseFile = new JFileChooser( FileSystemView.getFileSystemView().getHomeDirectory() );
            int returnValue = chooseFile.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION){
                plaintextFile = chooseFile.getSelectedFile();

                try ( BufferedReader userFile = new BufferedReader( new FileReader( plaintextFile ))) {

                    StringBuilder textfromFile = new StringBuilder();
                    String line = userFile.readLine();

                    //This assumes that the provided text file is the same as the sample test data given.
                    //Therefore, the text file is read from 2nd line onwards, ignoring key.
                    while ( (line = userFile.readLine()) != null ){
                        textfromFile.append(line);
                        textfromFile.append(System.lineSeparator());
                        line = userFile.readLine();
                    }

                    String completeText = textfromFile.toString();
                    System.out.print("Your ciphertext is:" + completeText);
                    crackcipher(completeText);
                }
            }
            
        } else {
            System.out.print( "Ciphertext:" );
            String text = br.readLine();
            crackcipher(text);
        }
    }
    
}
