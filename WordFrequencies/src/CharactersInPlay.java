import edu.duke.FileResource;

import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList<String> characterNames;
    private ArrayList<Integer> freqs;

    public CharactersInPlay() {
        characterNames = new ArrayList<>();
        freqs = new ArrayList<>();
    }

    private void update(String person) {

        int index = characterNames.indexOf(person);
        if (index == -1) {
            characterNames.add(person);
            freqs.add(1);
        } else {
            Integer val = freqs.get(index);
            freqs.set(index, val + 1);
        }
    }

    public void findAllCharacters() {

        characterNames.clear();
        freqs.clear();

        FileResource resource = new FileResource("likeit.txt");
        for (String line : resource.lines()) {
            if (line.isEmpty())
                continue;

            int periodIndex = line.indexOf('.');
            if (periodIndex == -1)
                continue;

            String characterName = line.substring(0, periodIndex);
            update(characterName.trim() );
        }

    }

    private void charactersWithNumParts(int num1 ,int num2)
    {
        for (int i = 0; i < freqs.size(); i++) {
            Integer freq = freqs.get(i);
            if (freq >= num1 && freq < num2) {
                System.out.println(characterNames.get(i));
            }
        }


    }

    public void tester() {
        findAllCharacters();
        for (int i = 0; i < characterNames.size(); i++) {
            String characterName = characterNames.get(i);
            Integer freq = freqs.get(i);
            System.out.println(characterName + " " + freq);
        }

        charactersWithNumParts(10,15);

    }
}
