import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Q4_insertion {
    public static ArrayList<String> deleteStopwords(String inputFilePath, String stopwordsFilePath) {
      ArrayList<String> listofWords = new ArrayList<>();
      ArrayList<String> stopwords = new ArrayList<>();
      HashSet<String> stopwordsSet = new HashSet<>();

      // read input file and populate listofWords
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split(" ");
                for (String word : words) {
                    listofWords.add(word);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // read stopwords file and populate stopwordsSet
        try (BufferedReader br = new BufferedReader(new FileReader(stopwordsFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split(" ");
                for (String word : words) {
                    stopwordsSet.add(word);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // remove stopwords from listofWords
        for (String word : listofWords) {
            if (!stopwordsSet.contains(word)) {
                stopwords.add(word);
            }
        }

        return stopwords;
    }

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

    public static void measureInsertionSortPerformance(ArrayList<String> listofWords, int[] wordCounts) {
        long startTime;
        long endTime;

        for (int i = 0; i < wordCounts.length; i++) {
            if (wordCounts[i] > listofWords.size()) {
                System.out.println("Error: Word count " + wordCounts[i] + " exceeds the size of the array.");
                continue;
            }
            ArrayList<String> sublist = new ArrayList<>(listofWords.subList(0, wordCounts[i]));

            startTime = System.nanoTime();
            insertionSort(sublist);
            endTime = System.nanoTime();

            int totalMoves = (wordCounts[i] * (wordCounts[i] - 1)) / 2; // calculate total moves for insertion sort
            long elapsedTime = endTime - startTime;
            System.out.println("Time taken to sort " + wordCounts[i] + " words: " + elapsedTime + " nanoseconds");
            System.out.println("Total moves while sorting " + wordCounts[i] + " words: " + totalMoves);
        }
    }

    public static void main(String[] args) {
        String inputFilePath = "Input.txt"; // update with your actual input file path
        String stopwordsFilePath = "stopwords.txt"; // update with your actual stopwords file path

        ArrayList<String> nonStopwords = deleteStopwords(inputFilePath, stopwordsFilePath);

        // sort non-stop words using insertion sort and measure performance
        int[] wordCounts = {100, 200, 500};
        measureInsertionSortPerformance(nonStopwords, wordCounts);
    }
}
