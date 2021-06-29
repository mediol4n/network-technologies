import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.CRC32;

public abstract class Code {

    static String readFromFile(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    static String calculateCRC(String message) {
        CRC32 crc = new CRC32();
        crc.update(message.getBytes());
        //System.out.println("CRC32: " + crc.getValue());
        long toWriteLong = crc.getValue();
        return String.format("%32s", Long.toBinaryString(toWriteLong)).replace(' ', '0');
    }
    
}
