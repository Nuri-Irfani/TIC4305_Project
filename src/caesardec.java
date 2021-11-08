import java.io.*;
import java.lang.StringBuilder;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class caesardec {

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

                    //Assuming the sample text data is the format for testing with text files,
                    //we assume the key is provided in the first line of the text:
                    int keyfromFile = Integer.valueOf( line ); //<-- get an int of the first line. This is our key.
                    System.out.println("Your key is: " + keyfromFile);

                    
                    while ( (line = userFile.readLine()) != null ){ //<-- this gives us the second line onwards
                        textfromFile.append(line);
                        textfromFile.append(System.lineSeparator());
                        line = userFile.readLine();
                    }

                    String completeText = textfromFile.toString();
                    System.out.print("Your ciphertext is:" + completeText);
                    System.out.println("Your deciphered text is: " + decipher(completeText, keyfromFile));
                }
            }
            
        } else {
            System.out.print( "Key:" );
            int key = Integer.parseInt( br.readLine() );
            System.out.print( "Ciphertext:" );
            String text = br.readLine();
            System.out.println( decipher( text, key ) );
        }
    }
    
}
