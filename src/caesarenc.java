import java.io.*;
import java.lang.StringBuilder;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

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
                    int keyfromFile = Integer.valueOf( line );
                    System.out.println("Your key is: " + keyfromFile);

                    while ( (line = userFile.readLine()) != null ){
                        textfromFile.append(line);
                        textfromFile.append(System.lineSeparator());
                        line = userFile.readLine();
                    }

                    String completeText = textfromFile.toString();
                    System.out.print("Your plaintext is:" + completeText);

                    StringBuilder cipherText = encipher( completeText, keyfromFile);
                    System.out.println("Your cipher is: " + cipherText);
                }
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
