import edu.duke.FileResource;

import java.util.HashMap;

public class CodonCount {
    private HashMap<String, Integer> condonsMap;

    public CodonCount() {
        condonsMap = new HashMap<>();
    }

    public void buildCodonMap(int start, String dna) {
        if (start < 0 || dna == null || dna.isEmpty() || start + 3 > (dna.length() - start))
            return;
        condonsMap.clear();


        String codon = dna.substring(start, start + 3);
        while (true) {
            Integer count = condonsMap.get(codon);
            if (count == null) {
                condonsMap.put(codon, 1);
            } else {
                condonsMap.put(codon, condonsMap.get(codon) + 1);
            }
            start += 3;
            if (start + 3 >= dna.length()) {
                break;
            }
            codon = dna.substring(start, start + 3);
        }
    }

    public String getMostCommonCodon() {
        int maxCount = 0;
        String mostCommonCodon = null;
        for (String codon : condonsMap.keySet()) {
            Integer count = condonsMap.get(codon);
            if (count > maxCount) {
                maxCount = count;
                mostCommonCodon = codon;
            }
        }

        return mostCommonCodon;
    }

    public void printCodonCounts(int start, int end) {
        for (String codon : condonsMap.keySet()) {
            Integer count = condonsMap.get(codon);
            if (count >= start && count <= end) {
                System.out.println(codon + " " + count);
            }
        }

    }

    public void tester() {
        FileResource fileResource = new FileResource("dnaMystery2.txt");
        String content = fileResource.asString().toUpperCase();

        for (int i = 0; i <= 2; i++) {

            buildCodonMap(i, content);

            System.out.println("Reading frame starting with " + i + " results in " + condonsMap.size() + " unique codons");
            String mostCommonCodon = getMostCommonCodon();
            System.out.println("and most common codon is " + mostCommonCodon + " with count " + condonsMap.get(mostCommonCodon));

            System.out.println("Counts of codons between 1 and 5 inclusive are:");
            printCodonCounts(7,7);
        }

    }

}
