import java.io.IOException;
import java.io.PrintWriter;

public class Decode extends Code {

    
    private final static String errMsg = "Error! Damaged package!";

    static void decode(String fileToRead, String fileToWrite) throws IOException {
        String code = readFromFile(fileToRead);
        String messageWithCRC = removeSignCode(code);
        messageWithCRC = unstrech(messageWithCRC);
        String message = removeCRC(messageWithCRC);
        String crc = removeMessage(messageWithCRC);
        PrintWriter writer = new PrintWriter(fileToWrite);

        if (!checkCRC(crc, message)) {
            System.out.println(errMsg);
            writer.write(errMsg);
            writer.close();
            return;
        }

        writer.println(message);
        writer.close();
    }

    private static boolean checkCRC(String crc, String message) {
        String currCRC = calculateCRC(message);
        return currCRC.equals(crc);
    }

    private static String removeMessage(String messageWithCRC) {
        return messageWithCRC.substring(messageWithCRC.length() - 32);
    }

    private static String removeCRC(String messageWithCRC) {
        return messageWithCRC.substring(0, messageWithCRC.length() - 32);
    }

    private static String unstrech(String msg) {
        StringBuilder builder = new StringBuilder(msg);
        int counter = 0;
        for (int i = 0; i < builder.length(); i++) {
            Character character = builder.charAt(i);
            if (character.equals('1') && counter < 5) {
                counter++;
                if (counter == 5) {
                    builder.deleteCharAt(i+1);
                    counter = 0;
                }   
            } else {
                counter = 0;
            }
        }
        return builder.toString();
    }

    private static String removeSignCode(String code) {
        String msg = "";
        StringBuilder builder = new StringBuilder(msg);
        boolean readMessage = false;
        int counter = 0;
        for (int i = 0; i < code.length(); i++) {
            Character character = code.charAt(i);
            if (readMessage) {
                builder.append(code.charAt(i));
            }

            if (character.equals('1') && counter < 6) {
                counter++;
                if (counter == 6) {
                    readMessage = !readMessage;
                    counter = 0;
                }
            } else {
                counter = 0;
            }
        }

        msg = builder.toString();

        //usunac 0 z poczatku oraz 0111111 z konca
        return msg.substring(1, builder.length() - 7);
    }
    
}
