import edu.duke.FileResource;

public class CaesarCipher {

    public String encrypt(String input, int key) {
        String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseAlphabets = alphabets.toLowerCase();

        String shifted = alphabets.substring(key) + alphabets.substring(0, key);
        String loweCaseShifted = shifted.toLowerCase();

        StringBuilder stringBuilder = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
            int idx = alphabets.indexOf(stringBuilder.charAt(i));
            if (idx != -1) {
                stringBuilder.setCharAt(i, shifted.charAt(idx));
            }
            idx = lowerCaseAlphabets.indexOf(stringBuilder.charAt(i));
            if (idx != -1) {
                stringBuilder.setCharAt(i, loweCaseShifted.charAt(idx));
            }
        }
        return stringBuilder.toString();
    }





    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder result = new StringBuilder(input);

        String encrypt1 = encrypt(input, key1);
        String encrypt2 = encrypt(input, key2);

        for (int i = 0; i < input.length(); i++) {
            if (i % 2 == 0) {
                result.setCharAt(i, encrypt1.charAt(i));
            } else {
                result.setCharAt(i, encrypt2.charAt(i));

            }
        }
        return result.toString();
    }

    public void testEncrypt() {
        System.out.println(encrypt("FIRST LEGION ATTACK EAST FLANK!", 23));
        System.out.println(encrypt("First Legion!", 17));
        System.out.println(encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15));

        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, 23);
        System.out.println("key is " + 23 + "\n" + encrypted);
    }

    public void testEncryptTwoKeys() {
        System.out.println(encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15, 21));
    }
}
