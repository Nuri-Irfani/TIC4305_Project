import java.util.Arrays;

public class test {


    public static void testrun() {
        char comma = ',';
        double COMMA = (double)comma;
        System.out.println(COMMA);
    }

    public static int[] countCharacters(String message) {
        message = message.replaceAll("[^a-zA-Z]", "").toUpperCase();
        int[] counts = new int[26];
        for (char c : message.toCharArray())
            counts[c - 'A']++;
        System.out.println(Arrays.toString(counts));
        return counts;
    }
    public static void main(String[] args) throws Exception {
        //testrun();
        String message = new String("ThisIsATest");
        countCharacters(message);
    }
    
}
