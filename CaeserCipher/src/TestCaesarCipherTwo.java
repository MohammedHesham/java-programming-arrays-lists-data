import edu.duke.FileResource;

public class TestCaesarCipherTwo {

    private int[] countLetters(String encrypted) {
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int i = 0; i < encrypted.length(); i++) {
            char c = Character.toLowerCase(encrypted.charAt(i));
            int index = alpha.indexOf(c);
            if (index != -1) {
                counts[index] += 1;
            }
        }
        return counts;
    }

    private int maxIndex(int[] freqs) {
        int max = -1;
        int maxIndex = 0;
        for (int i = 0; i < freqs.length; i++) {
            int value = freqs[i];
            if (max < value) {
                max = value;
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private String halfOfString(String message, int start) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            if (i % 2 == start) {
                builder.append(message.charAt(i));
            }
        }
        return builder.toString();
    }

    public void simpleTests() {
        CaesarCipherTwo caesarCipherTwo = new CaesarCipherTwo(17, 3);
        FileResource resource = new FileResource("smallHamlet.txt");
        String content = resource.asString();
        String encrypt = caesarCipherTwo.encrypt(content);
        System.out.println(encrypt);
        String decrypt = caesarCipherTwo.decrypt(encrypt);
        System.out.println(decrypt);

        System.out.println(breakCaesarCipher(encrypt));

    }

    public String breakCaesarCipher(String input) {
        String half1 = halfOfString(input, 0);
        String half2 = halfOfString(input, 1);

        int key1 = getKey(half1);
        int key2 = getKey(half2);

        System.out.println("key1=" + key1 + " &key2=" + key2);
        CaesarCipherTwo cipher = new CaesarCipherTwo(key1, key2);
        return cipher.encrypt(input);
    }

    private int getKey(String s) {
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return 26 - dkey;
    }
}
