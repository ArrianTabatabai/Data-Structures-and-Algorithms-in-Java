import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Q4_merge {
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

    public static void mergeSort(ArrayList<String> listOfWords) {
        if (listOfWords.size() <= 1) {
            return;
        }

        int mid = listOfWords.size() / 2;
        ArrayList<String> left = new ArrayList<>(listOfWords.subList(0, mid));
        ArrayList<String> right = new ArrayList<>(listOfWords.subList(mid, listOfWords.size()));

        mergeSort(left);
        mergeSort(right);

        int i = 0;
        int j = 0;
        int k = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i).compareToIgnoreCase(right.get(j)) < 0) {
                listOfWords.set(k, left.get(i));
                i++;
            } else {
                listOfWords.set(k, right.get(j));
                j++;
            }
            k++;
        }

        while (i < left.size()) {
            listOfWords.set(k, left.get(i));
            i++;
            k++;
        }

        while (j < right.size()) {
            listOfWords.set(k, right.get(j));
            j++;
            k++;
        }
    }

    public static void measurePerformance(ArrayList<String> words) {
    ArrayList<String> copy1 = new ArrayList<>(words.subList(0, Math.min(100, words.size())));
    ArrayList<String> copy2 = new ArrayList<>(words.subList(0, Math.min(200, words.size())));
    ArrayList<String> copy3 = new ArrayList<>(words.subList(0, Math.min(500, words.size())));

    // measure performance of merge sort for sorting first 100 words
    System.out.println("\nSorting first 100 words with merge sort");
    long startTime = System.nanoTime();
    mergeSort(copy1);
    long endTime = System.nanoTime();
    int moves = copy1.size() * (int) (Math.log(copy1.size()) / Math.log(2)); // Calculate the number of moves
    System.out.println("Time taken: " + (endTime - startTime) + " nanoseconds");
    System.out.println("Number of moves: " + moves);

    // measure performance of merge sort for sorting first 200 words
    System.out.println("\nSorting first 200 words with merge sort");
    startTime = System.nanoTime();
    mergeSort(copy2);
    endTime = System.nanoTime();
    moves = copy2.size() * (int) (Math.log(copy2.size()) / Math.log(2)); // Calculate the number of moves
    System.out.println("Time taken: " + (endTime - startTime) + " nanoseconds");
    System.out.println("Number of moves: " + moves);

    // measure performance of merge sort for sorting first 500 words
    System.out.println("\nSorting first 500 words with merge sort");
    startTime = System.nanoTime();
    mergeSort(copy3);
    endTime = System.nanoTime();
    moves = copy3.size() * (int) (Math.log(copy3.size()) / Math.log(2)); // Calculate the number of moves
    System.out.println("Time taken: " + (endTime - startTime) + " nanoseconds");
    System.out.println("Number of moves: " + moves);
}

    public static void main(String[] args) {
        // input file paths
        String inputFilePath = "input.txt";
        String stopwordsFilePath = "stopwords.txt";

        // delete stopwords from input file
        ArrayList<String> words = deleteStopwords(inputFilePath, stopwordsFilePath);

        // sort words using merge sort and measure performance
        mergeSort(words);
        measurePerformance(words);
    }
}
