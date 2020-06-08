import java.io.File;
import java.util.*;

import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder content = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            content.append(message.charAt(i));
        }
        //REPLACE WITH YOUR CODE
        return content.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        CaesarCracker caesarCracker = new CaesarCracker(mostCommon);
        for (int i = 0; i < klength; i++) {
            String slice = sliceString(encrypted, i, klength);
            int key1 = caesarCracker.getKey(slice);
            key[i] = key1;
        }
        return key;
    }

    public void breakVigenere() {
        FileResource fileResource = new FileResource();

        DirectoryResource directoryResource = new DirectoryResource();
        HashMap<String, HashSet<String>> languages = new HashMap<>();

        for (File file : directoryResource.selectedFiles()) {

            HashSet<String> dicWords = readDictionary(new FileResource(file));
            languages.put(file.getName(), dicWords);
        }


        String decrypt = breakForAllLangs(fileResource.asString(), languages);
        System.out.println(decrypt);
    }

    public HashSet<String> readDictionary(FileResource fileResource) {
        HashSet<String> words = new HashSet<>();
        for (String word : fileResource.words()) {
            words.add(word.toLowerCase());
        }
        return words;

    }

    public int countWords(String message, HashSet<String> dic) {
        String[] words = message.split("\\W+");

        int realWords = 0;
        for (String word : words) {
            if (dic.contains(word.toLowerCase())) {
                realWords++;
            }
        }
        return realWords;

    }

    public String breakForLanguage(String enc, HashSet<String> dic) {
        int maxWords = 0;
        String decryptedMsg = null;
        int keyLength = 0;
        int[] keys = new int[0];
        for (int i = 1; i <= 100; i++) {
            keys = tryKeyLength(enc, i, mostCommonCharIn(dic));
            VigenereCipher vigenereCipher = new VigenereCipher(keys);
            String decrypt = vigenereCipher.decrypt(enc);
            int realWords = countWords(decrypt, dic);

            if (realWords > maxWords) {
                maxWords = realWords;
                keyLength = i;
                decryptedMsg = decrypt;
            }
        }
        System.out.println(keyLength);
        return decryptedMsg;
    }

    public char mostCommonCharIn(HashSet<String> dic) {
        String alphabets = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (String word : dic) {
            for (int i = 0; i < word.length(); i++) {
                char c = word.toLowerCase().charAt(i);
                int index = alphabets.indexOf(c);
                if (index != -1) {
                    counts[index]++;
                }
            }
        }

        int maxCount = 0;
        int maxIndex = -1;
        for (int i = 0; i < counts.length; i++) {
            int count = counts[i];
            if (maxCount < count) {
                maxCount = count;
                maxIndex = i;
            }
        }

        return alphabets.charAt(maxIndex);
    }

    public String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        int maxWords = 0;
        String decryptedMsg = null;
        String decryptedLanguage = null;
        for (String language : languages.keySet()) {
            HashSet<String> words = languages.get(language);
            String decrypted = breakForLanguage(encrypted, words);
            int countWords = countWords(decrypted, words);

            if (countWords > maxWords) {
                maxWords = countWords;
                decryptedLanguage = language;
                decryptedMsg = decrypted;
            }
        }

        System.out.println("Decrypted lang:" + decryptedLanguage);
        return decryptedMsg;
    }
}
