import java.io.*;
import java.lang.StringBuilder;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class crackertemp {

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

    /*
     * Frequencies of ascii characters in the english written language
     * I chose to use Piersy's data, as it is based on Reuters-21578 corpus,
     * which is a widely used test collection for text categorization research.
     * Piersy's github: https://github.com/piersy/ascii-char-frequency-english
     * Reuters-21578: http://www.daviddlewis.com/resources/testcollections/reuters21578/
     * Not all of the data are included, as non-printable ascii characters are also
     * included in Piersy's calculations/code, and those are not used in this project
     */
    public static void piersy(){
        HashMap<Integer,Double> piersyFrequencies = new HashMap<Integer,Double>();
        piersyFrequencies.put(32,0.168);
        piersyFrequencies.put(101,0.086);
        piersyFrequencies.put(116,0.063);
        piersyFrequencies.put(97,0.061);
        piersyFrequencies.put(110,0.055);
        piersyFrequencies.put(105,0.055);
        piersyFrequencies.put(111,0.054);
        piersyFrequencies.put(115,0.052);
        piersyFrequencies.put(114,0.052);
        piersyFrequencies.put(108,0.032);
        piersyFrequencies.put(100,0.032);
        piersyFrequencies.put(104,0.026);
        piersyFrequencies.put(99,0.025);
        piersyFrequencies.put(117,0.019);
        piersyFrequencies.put(109,0.018);
        piersyFrequencies.put(112,0.017);
        piersyFrequencies.put(102,0.015);
        piersyFrequencies.put(103,0.013);
        piersyFrequencies.put(46,0.011);
        piersyFrequencies.put(121,0.011);
        piersyFrequencies.put(98,0.010);
        piersyFrequencies.put(119,0.010);
        piersyFrequencies.put(44,0.009);
        piersyFrequencies.put(118,0.008);
        piersyFrequencies.put(48,0.006);
        piersyFrequencies.put(107,0.005);
        piersyFrequencies.put(49,0.005);
        piersyFrequencies.put(83,0.003);
        piersyFrequencies.put(84,0.003);
        piersyFrequencies.put(67,0.003);
        piersyFrequencies.put(50,0.003);
        piersyFrequencies.put(56,0.003);
        piersyFrequencies.put(53,0.003);
        piersyFrequencies.put(65,0.002);
        piersyFrequencies.put(57,0.002);
        piersyFrequencies.put(120,0.002);
        piersyFrequencies.put(51,0.002);
        piersyFrequencies.put(73,0.002);
        piersyFrequencies.put(45,0.002);
        piersyFrequencies.put(54,0.002);
        piersyFrequencies.put(52,0.002);
        piersyFrequencies.put(55,0.002);
        piersyFrequencies.put(77,0.002);
        piersyFrequencies.put(66,0.002);
        piersyFrequencies.put(34,0.002);
        piersyFrequencies.put(39,0.002);
        piersyFrequencies.put(80,0.001);
        piersyFrequencies.put(69,0.001);
        piersyFrequencies.put(78,0.001);
        piersyFrequencies.put(70,0.001);
        piersyFrequencies.put(82,0.001);
        piersyFrequencies.put(68,0.001);
        piersyFrequencies.put(85,0.001);
        piersyFrequencies.put(113,0.001);
        piersyFrequencies.put(76,0.001);
        piersyFrequencies.put(71,0.001);
        piersyFrequencies.put(74,0.001);
        piersyFrequencies.put(72,0.001);
        piersyFrequencies.put(79,0.001);
        piersyFrequencies.put(87,0.001);
        piersyFrequencies.put(106,0.001);
        piersyFrequencies.put(122,0.001);
        piersyFrequencies.put(47,0.001);
        piersyFrequencies.put(60,0.001);
        piersyFrequencies.put(62,0.001);
        piersyFrequencies.put(75,0.001);
        piersyFrequencies.put(41,0.001);
        piersyFrequencies.put(40,0.001);
        piersyFrequencies.put(86,0.001);
        piersyFrequencies.put(89,0.001);
        piersyFrequencies.put(58,0.001);
        piersyFrequencies.put(81,0.001);
        piersyFrequencies.put(90,8.620);
        piersyFrequencies.put(88,6.573);
        piersyFrequencies.put(59,7.416);
        piersyFrequencies.put(63,4.627);
        piersyFrequencies.put(127,3.106);
        piersyFrequencies.put(94,2.218);
        piersyFrequencies.put(38,2.028);
        piersyFrequencies.put(43,1.521);
        piersyFrequencies.put(91,6.972);
        piersyFrequencies.put(93,6.338);
        piersyFrequencies.put(36,5.071);
        piersyFrequencies.put(33,5.071);
        piersyFrequencies.put(42,4.437);
        piersyFrequencies.put(61,2.535);
        piersyFrequencies.put(126,1.901);
        piersyFrequencies.put(95,1.268);
        piersyFrequencies.put(123,6.338);
        piersyFrequencies.put(64,6.338);
    }

    
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

    public static void bruteForceAttack( String encText ) {
        //96, as we are operating on ascii 32 to 127
        // shift of 0 or 96 would result in no change
        for (int shift = 1; shift < 96; shift++){
            System.out.println("shift: " + shift + ", " + decipher(encText, shift));
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
            
            File plaintextFile;

            //Basic call for the JFileChooser constructor.
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
