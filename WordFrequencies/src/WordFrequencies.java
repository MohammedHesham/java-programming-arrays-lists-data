import edu.duke.FileResource;

import java.util.ArrayList;

public class WordFrequencies {

    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;


    public WordFrequencies() {
        myFreqs = new ArrayList<>();
        myWords = new ArrayList<>();
    }

    public void findUnique() {
        myWords.clear();
        myFreqs.clear();
        FileResource fileResource = new FileResource("errors.txt");
        for (String word : fileResource.words()) {
            word = word.toLowerCase();
            int index = myWords.indexOf(word);
            if (index == -1) {
                myWords.add(word);
                myFreqs.add(1);
            } else {
                Integer val = myFreqs.get(index);
                myFreqs.set(index, val + 1);
            }
        }

    }

    public void tester() {
        findUnique();
        System.out.println("Number of unique words = " + myWords.size());
        for (int i = 0; i < myWords.size(); i++) {
            String myWord = myWords.get(i);
            System.out.println(myWord);
        }

        int indexOfMax = findIndexOfMax();
        System.out.println("Index of maximum: " + indexOfMax);

        if (indexOfMax < 0)
            return;
        System.out.print("The word that occurs most often and its count are: " + myWords.get(indexOfMax) + " " + myFreqs.get(indexOfMax));
    }

    private int findIndexOfMax() {
        int max = -1;
        int index = -1;
        for (int i = 0; i < myFreqs.size(); i++) {
            Integer freq = myFreqs.get(i);
            if (freq > max) {
                max = freq;
                index = i;
            }
        }
        return index;

    }
}
