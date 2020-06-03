import edu.duke.FileResource;

public class TestCaesarCipher {

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

    public String breakCaesarCipher(String encrypted) {
        int key = getKey(encrypted);
        CaeserCipher cs = new CaeserCipher(key);
        return cs.encrypt(encrypted);
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

    public void simpleTests() {
        FileResource resource = new FileResource("smallHamlet.txt");
        String content = resource.asString();

        CaeserCipher cipher = new CaeserCipher(18);
        String encrypt = cipher.encrypt(content);
        System.out.println("Enctypted string :" + encrypt);
        String decrypt = breakCaesarCipher(encrypt);
        System.out.println("Decrypted string :" + decrypt);


    }
}
