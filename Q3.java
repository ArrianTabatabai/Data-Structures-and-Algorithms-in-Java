import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Q3 {

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

//took pseudocode for 'merge sort' algorithm from learning central materials in week 6
//then just converted it into actual Java, and implemented it into my code as shown below
//accessed 22-04-2023
//https://learningcentral.cf.ac.uk/webapps/blackboard/execute/content/file?cmd=view&content_id=_7067978_1&course_id=_412459_1
    public static void mergeSort(ArrayList<String> listOfWords) {
        if (listOfWords.size() <= 1) {
            return;
        }

        int mid = listOfWords.size() / 2;
        ArrayList<String> left = new ArrayList<>(listOfWords.subList(0, mid));
        ArrayList<String> right = new ArrayList<>(listOfWords.subList(mid, listOfWords.size()));

        mergeSort(left);
        mergeSort(right);

        int i = 0; // index for left list
        int j = 0; // index for right list
        int k = 0; // index for merged list

        // merge the sorted left and right lists
        while (i < left.size() && j < right.size()) {
            if (left.get(i).compareTo(right.get(j)) <= 0) {
                listOfWords.set(k++, left.get(i++));
            } else {
                listOfWords.set(k++, right.get(j++));
            }
        }

        // copy any remaining elements from left list
        while (i < left.size()) {
            listOfWords.set(k++, left.get(i++));
        }

        // copy any remaining elements from right list
        while (j < right.size()) {
            listOfWords.set(k++, right.get(j++));
        }
    }
//end of 'merge sort' algorithm implementation

    public static void main(String[] args) {
        String inputFilePath = "Input.txt"; // update with your actual input file path
        String stopwordsFilePath = "stopwords.txt"; // update with your actual stopwords file path

        ArrayList<String> nonStopwords = deleteStopwords(inputFilePath, stopwordsFilePath);

        mergeSort(nonStopwords); // sort the words using merge sort

        // print non-stop words after sorting
        System.out.println("Non-stop words after sorting:");
        for (String word : nonStopwords) {
            System.out.println(word);
        }
    }
}
