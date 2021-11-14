import java.io.*;
import java.lang.StringBuilder;

public class caesarenc {

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
            //If character is newline, do nothing
            if (currentChar == 10){
                encText.append( (char)currentChar );
            } else {
                currentChar += key;
                //If character value exceeds 126 after shift, transfer overflow to 32
                if (currentChar > 126){
                    int temp = currentChar - 127;//<- 127 because there is still a character after 126
                    currentChar = temp + 32;
                    encText.append( (char)currentChar );
                } else {
                    encText.append( (char)currentChar );
                }
            }
        }
        return encText;
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
            
                //Assuming the sample text data is the format for testing with text files,
                //it is assumed the key is provided in the first line of the text:
                int keyfromFile = Integer.valueOf( userFile.readLine() ); //<-- get an int of the first line. This is the key.
                System.out.println("Your key is: " + keyfromFile);

                String line = userFile.readLine();//<-- this gives the second line onwards
                while (line != null ){ 
                    textfromFile.append(line);
                    textfromFile.append(System.lineSeparator());
                    line = userFile.readLine();
                }


                String completeText = textfromFile.toString();
                System.out.print("Your plaintext is:\n" + completeText);

                StringBuilder cipherText = encipher( completeText, keyfromFile);
                System.out.println("Your cipher is:\n" + cipherText);
            }
            
        } else {
            System.out.print( "Key:" );
            int key = Integer.parseInt( br.readLine() );
            System.out.print( "Text:" );
            String text = br.readLine();
            StringBuilder encText = encipher( text, key );
            System.out.println("Your cipher is: " + encText);
        }
    }
    
}
