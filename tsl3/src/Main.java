import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        final String z = "data/input.txt";
        final String w = "data/output.txt";
        final String zc = "data/inputFromOutput.txt";


        try {
            Encode.encode(z, w);
            Decode.decode(w, zc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

}
