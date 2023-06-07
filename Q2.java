import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Q2 {
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

//took pseudocode for 'insertion sort' algorithm from learning central materials in week 5
//then just converted it into actual Java, and implemented it into my code as shown below
//accessed 22-04-2023
//https://learningcentral.cf.ac.uk/webapps/blackboard/execute/content/file?cmd=view&content_id=_7060228_1&course_id=_412459_1
    public static void insertionSort(ArrayList<String> listofWords) {
        for (int i = 1; i < listofWords.size(); i++) {
            String item = listofWords.get(i);
            int j = i - 1;
            while (j >= 0 && listofWords.get(j).compareToIgnoreCase(item) > 0) {
                listofWords.set(j + 1, listofWords.get(j));
                j = j - 1;
            }
            listofWords.set(j + 1, item);
        }
    }
//end of 'insertion sort' algorithm implementation

    public static void main(String[] args) {
        String inputFilePath = "Input.txt"; // update with your actual input file path
        String stopwordsFilePath = "stopwords.txt"; // update with your actual stopwords file path

        ArrayList<String> nonStopwords = deleteStopwords(inputFilePath, stopwordsFilePath);

        // sort non-stop words using insertion sort
        insertionSort(nonStopwords);

        // print sorted non-stop words
        System.out.println("Sorted non-stop words:");
        for (String word : nonStopwords) {
            System.out.println(word);
        }
    }
}
