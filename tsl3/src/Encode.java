import java.io.IOException;
import java.io.PrintWriter;

public class Encode extends Code {

    private final static String signCode = "01111110";

    static void encode(String fileToRead, String fileToWrite) throws IOException {
        String message = readFromFile(fileToRead);
        String crc = calculateCRC(message);
        String messageWithCRC = message + crc;
        messageWithCRC = stretch(messageWithCRC);
        PrintWriter writer = new PrintWriter(fileToWrite);
        writer.println(signCode + messageWithCRC + signCode);
        writer.close();
    }

    private static String stretch(String message) {
        StringBuilder builder = new StringBuilder(message);
        int counter = 0;
        for (int i = 0; i < builder.length(); i++) {
            Character character = builder.charAt(i);
            if (character.equals('1') && counter < 5) {
                counter++;
                if (counter == 5) {
                    builder.insert(i + 1, '0');
                    counter = 0;
                }
            } else {
                counter = 0;
            }
        }
        return builder.toString();
    }
    
}
