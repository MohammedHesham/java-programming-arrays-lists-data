import edu.duke.FileResource;

public class WordLengths {

    public void countWordLengths(FileResource fileResource, int[] counts) {
        for (String word : fileResource.words()) {
            int length = word.length();
            if (!Character.isLetter(word.charAt(0)))
                length -= 1;
            if (!Character.isLetter(word.charAt(word.length() - 1)))
                length -= 1;

            if (length <= 0)
                continue;

            counts[length] += 1;
        }

    }

    public int indexOfMax(int[] values) {
        int max = -1;
        int idx = 0;
        for (int i = 0, valuesLength = values.length; i < valuesLength; i++) {
            int value = values[i];
            if (max < value) {
                max = value;
                idx = i;
            }
        }
        return idx;

    }

    public void testCountWordLengths() {
        int[] counts = new int[31];
        FileResource fileResource = new FileResource("manyWords.txt");
        countWordLengths(fileResource, counts);

        for (int count : counts) {
            System.out.print(count + ", ");
        }
        System.out.println();

        System.out.println(indexOfMax(counts));
    }
}
