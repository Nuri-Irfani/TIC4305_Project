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

            if (currentChar > 32){
                currentChar -= key;
                //if the shifted character value < 32, simply transfer the backflow difference to 127
                if (currentChar < 32){
                    int temp = 32 - currentChar; 
                    currentChar = 127-temp;
                    decText.append( (char)currentChar );
                } else {
                    decText.append( (char)currentChar );
                }
            //but if the character value is exactly 32, the calculation is straightforward.
            //we also want to have the newline character, if there is any, untouched
            } else {
                if (currentChar == 32){  //if current character is found to be a space, then simply subtract from 127
                    currentChar = 127 - key; //<-127 because there is still a character after 126 to walk past
                    decText.append( (char)currentChar );
                }
                if (currentChar == 10){ //<- this is a newline character. it gets ignored by society.
                    decText.append( (char)currentChar );
                }
            }
        }
        return decText; 
    }

    /*
    -------------------------------------------------------- 
    * JFileChooser used as a means to process text files.
    * That is an option, user can opt to type into terminal instead
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
                    System.out.print("Your ciphertext is:\n" + completeText + "\n");
                    System.out.println("Your deciphered text is:\n" + decipher(completeText, keyfromFile));
                }
            }
            
        } else {
            System.out.print( "Key:" );
            int key = Integer.parseInt( br.readLine() );
            System.out.print( "Ciphertext: " );
            String text = br.readLine();
            System.out.println( decipher( text, key ) );
        }
    }
    
}
