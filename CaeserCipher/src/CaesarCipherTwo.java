public class CaesarCipherTwo {
    private final int key2;
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int key1;

    public CaesarCipherTwo(int key1, int key2) {
        this.key1 = key1;
        this.key2 = key2;
        this.alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        this.shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        this.shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
    }

    public String encrypt(String input) {
        StringBuilder stringBuilder = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
            int idx = alphabet.indexOf(stringBuilder.charAt(i));
            if (idx != -1) {
                if (i % 2 == 0)
                    stringBuilder.setCharAt(i, shiftedAlphabet1.charAt(idx));
                else
                    stringBuilder.setCharAt(i, shiftedAlphabet2.charAt(idx));
            }

            idx = alphabet.toLowerCase().indexOf(stringBuilder.charAt(i));
            if (idx != -1) {
                if (i % 2 == 0)
                    stringBuilder.setCharAt(i, shiftedAlphabet1.toLowerCase().charAt(idx));
                else
                    stringBuilder.setCharAt(i, shiftedAlphabet2.toLowerCase().charAt(idx));
            }
        }
        return stringBuilder.toString();
    }

    public String decrypt(String input) {
        int key1 = 26 - this.key1;
        int key2 = 26 - this.key2;
        CaesarCipherTwo caeserCipher = new CaesarCipherTwo(key1, key2);
        return caeserCipher.encrypt(input);
    }
}
