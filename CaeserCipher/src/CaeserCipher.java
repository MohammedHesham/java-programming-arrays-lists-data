public class CaeserCipher {

    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;

    public CaeserCipher(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        mainKey = key;
    }

    public String encrypt(String input) {
        StringBuilder stringBuilder = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
            int idx = alphabet.indexOf(stringBuilder.charAt(i));
            if (idx != -1) {
                stringBuilder.setCharAt(i, shiftedAlphabet.charAt(idx));
            }
            idx = alphabet.toLowerCase().indexOf(stringBuilder.charAt(i));
            if (idx != -1) {
                stringBuilder.setCharAt(i, shiftedAlphabet.toLowerCase().charAt(idx));
            }
        }
        return stringBuilder.toString();
    }

    public String decrypt(String input) {
        int key = 26 - mainKey;
        CaeserCipher caeserCipher = new CaeserCipher(key);
        return caeserCipher.encrypt(input);
    }
}
