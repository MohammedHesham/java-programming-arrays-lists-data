import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WordsInFiles {

    private HashMap<String, ArrayList<String>> wordsMap;

    public WordsInFiles() {
        wordsMap = new HashMap<>();
    }

    private void addWordsFromFile(File f) {
        FileResource fileResource = new FileResource(f);
        for (String word : fileResource.words()) {
//            word = word.toLowerCase();
            ArrayList<String> filenames = wordsMap.get(word);
            if (filenames == null) {
                filenames = new ArrayList<>();
                filenames.add(f.getName());
                wordsMap.put(word, filenames);
            } else {

                if (!filenames.contains(f.getName())) {
                    filenames.add(f.getName());

                }
            }
        }

    }


    public void buildWordFileMap() {
        wordsMap.clear();
        DirectoryResource directoryResource = new DirectoryResource();
        for (File file : directoryResource.selectedFiles()) {
            addWordsFromFile(file);
        }

    }

    public int maxNumber() {
        int max = -1;
        for (String word : wordsMap.keySet()) {
            ArrayList<String> filenames = wordsMap.get(word);
            if (filenames.size() > max) {
                max = filenames.size();
            }
        }

        return max;
    }

    public List<String> wordsInNumFiles(int number) {
        List<String> wordsList = new ArrayList<>();
        for (String word : wordsMap.keySet()) {
            ArrayList<String> filenames = wordsMap.get(word);
            if (filenames.size() == number)
                wordsList.add(word);
        }
        return wordsList;

    }

    public void printFilesIn(String word) {
        ArrayList<String> filenames = wordsMap.get(word);
        for (String filename : filenames) {
            System.out.println(filename);
        }

    }

    public void tester() {
        buildWordFileMap();
        System.out.println(wordsInNumFiles(7).size());

//        printFilesIn("tree");
    }
}
