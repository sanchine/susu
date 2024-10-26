import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {
  public static void main(String [] args) throws IOException {

    // READING FROM FILE

    int bufferSize = 2048;
    char[] buffer = new char[bufferSize];

    try (FileReader reader = new FileReader("notes.txt")) {
      int c;

      while ((c = reader.read(buffer)) > 0) {
        if (c < bufferSize) {
          buffer = Arrays.copyOf(buffer, c);
        }
        // System.out.print(buffer);
      }

    }
    catch (IOException ex) {
      System.out.println(ex.getMessage());
    }

    String textFromFile = String.valueOf(buffer);
    
    // COUNTING OF EACH OF WORDS

    String[] words = textFromFile
          .toLowerCase()
          .split("\\s*(\\s|,|!|\\.)\\s*");
    
    Map<String, Integer> wordsCount = new HashMap<String, Integer>();

    for (String word : words){
      if (!wordsCount.containsKey(word)) wordsCount.put(word, 1);
      else wordsCount.put(word, wordsCount.get(word) + 1);
    }

    List<Map.Entry<String, Integer>> entries = new ArrayList<>(wordsCount.entrySet());
    entries.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

    for (int i = 0; i < Math.min(20, entries.size()); i++) {
        Map.Entry<String, Integer> entry = entries.get(i);
        System.out.println(entry.getKey() + ": " + entry.getValue());
    }

  }
}
