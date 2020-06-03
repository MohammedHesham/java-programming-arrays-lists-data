public class CaesarBreaker {
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

    public String decrypt(String encrypted) {
        CaesarCipher cs = new CaesarCipher();
        int key = getKey(encrypted);
        return cs.encrypt(encrypted, key);
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

    public String decryptTwoKeys(String encrypted) {

        String half1 = halfOfString(encrypted, 0);
        String half2 = halfOfString(encrypted, 1);

        int key1 = getKey(half1);
        int key2 = getKey(half2);

        System.out.println("key1=" + key1 + " &key2=" + key2);
        CaesarCipher cipher = new CaesarCipher();
        return cipher.encryptTwoKeys(encrypted, key1, key2);
    }

    public void testDecryptTwoKeys() {
        CaesarCipher cipher = new CaesarCipher();
        String encryptTwoKeys = cipher.encryptTwoKeys("side by side and having the same distance continuously between them.", 15, 21);
        System.out.println(decryptTwoKeys("Top ncmy qkff vi vguv vbg ycpx"));

    }


    public void testDecrypt() {
        CaesarCipher cs = new CaesarCipher();
        String encrypt = cs.encrypt("side by side and having the same distance continuously between them.", 9);
        System.out.println(encrypt);
        String decrypt = decrypt(encrypt);
        System.out.println(decrypt);
    }

    public void testhalfOfString() {
        System.out.println(halfOfString("Qbkm Zgis", 1));
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
