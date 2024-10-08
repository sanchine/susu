import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadHelper {
    public static String readTextFromFileToString(String filePath) throws IOException {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath))).toLowerCase();
        } catch (IOException ex) {
            return "IOException: " + ex;
        }
    }
}
