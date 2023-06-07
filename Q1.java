import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Q1 {
    public static ArrayList<String> deleteStopwords(String inputFilePath, String stopwordsFilePath) {
        ArrayList<String> result = new ArrayList<>();
        HashSet<String> stopwords = new HashSet<>();

        // read stop words from file and add them to a hash set for efficient lookup
        try (BufferedReader reader = new BufferedReader(new FileReader(stopwordsFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stopwords.add(line.toLowerCase()); // convert to lowercase for case-insensitivity
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // process input text file
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    // remove punctuation and convert to lowercase for case-insensitivity
                    String cleanedWord = word.replaceAll("[^a-zA-Z']", "").toLowerCase();
                    if (!stopwords.contains(cleanedWord)) {
                        result.add(word); // add original word to result if it's not a stopword
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void main(String[] args) {
        String inputFilePath = "Input.txt"; // update with your actual input file path
        String stopwordsFilePath = "stopwords.txt"; // update with your actual stopwords file path

        ArrayList<String> nonStopwords = deleteStopwords(inputFilePath, stopwordsFilePath);

        // print non-stop words
        System.out.println("Non-stop words:");
        for (String word : nonStopwords) {
            System.out.println(word);
        }
    }
}
