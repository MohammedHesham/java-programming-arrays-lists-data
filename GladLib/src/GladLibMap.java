import edu.duke.FileResource;
import edu.duke.URLResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    private Random myRandom;
    private List<String> categoriesUsed;
    private List<String> usedWords;

    public GladLibMap() {
        myMap = new HashMap<>();
        myRandom = new Random();
        categoriesUsed = new ArrayList<>();
        usedWords = new ArrayList<>();
    }


    private void initializeFromSource(String source) {

        String[] categories = {"adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit"};

        for (int i = 0; i < categories.length; i++) {
            ArrayList<String> words = readIt(source + "/" + categories[i] + ".txt");
            myMap.put(categories[i], words);
        }
    }


    private String fromTemplate(String source) {
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for (String word : resource.words()) {
                story = story + processWord(word) + " ";
            }
        } else {
            FileResource resource = new FileResource(source);
            for (String word : resource.words()) {
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private String processWord(String w) {
        int first = w.indexOf("<");
        int last = w.indexOf(">", first);
        if (first == -1 || last == -1) {
            return w;
        }
        String prefix = w.substring(0, first);
        String suffix = w.substring(last + 1);

        String sub;
        do {
            sub = getSubstitute(w.substring(first + 1, last));
        } while (usedWords.contains(sub));
        return prefix + sub + suffix;
    }


    private String getSubstitute(String label) {

        ArrayList<String> words = myMap.get(label);
        if (words == null || words.isEmpty()) {
            return "**UNKNOWN**";
        }
        if (!categoriesUsed.contains(label)) {
            categoriesUsed.add(label);
        }
        if (label.equals("number")) {
            return "" + myRandom.nextInt(50) + 5;
        }


        return randomFrom(words);
    }

    private String randomFrom(ArrayList<String> source) {
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private ArrayList<String> readIt(String source) {
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for (String line : resource.lines()) {
                list.add(line);
            }
        } else {
            FileResource resource = new FileResource(source);
            for (String line : resource.lines()) {
                list.add(line);
            }
        }
        return list;
    }

    public int totalWordsInMap() {
        int sum = 0;
        for (String category : myMap.keySet()) {
            sum += myMap.get(category).size();
        }

        return sum;
    }

    private int totalWordsConsidered() {
        int total = 0;
        for (String category : categoriesUsed) {
            total += myMap.get(category).size();
        }

        return total;
    }

}
