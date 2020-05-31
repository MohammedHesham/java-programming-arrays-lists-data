public class WordPlay {

    /**
     * This method returns true if ch is a vowel (one of 'a', 'e', 'i', 'o', or 'u' or the uppercase versions) and false otherwise
     *
     * @param ch
     * @return
     */
    public boolean isVowel(Character ch) {
        String vowels = "aeiou";
        return vowels.indexOf(Character.toLowerCase(ch)) != -1;
    }


    /**
     * This method should return a String that is the string phrase but with the Char ch (upper- or lowercase) replaced by
     * ‘*’ if it is in an odd number location in the string (e.g. the first character has an odd number location but an even index, it is at index 0), or
     * ‘+’ if it is in an even number location in the string (e.g. the second character has an even number location but an odd index, it is at index 1).
     *
     * @param phrase
     * @param c
     * @return
     */
    public String emphasize(String phrase, char c) {
        StringBuilder stringBuilder = new StringBuilder(phrase);
        for (int i = 0; i < phrase.length(); i++) {
            if (stringBuilder.charAt(i) == c) {
                if (i % 2 == 0)//odd index
                {
                    stringBuilder.setCharAt(i, '*');
                } else {
                    stringBuilder.setCharAt(i, '+');
                }
            }
        }
        return stringBuilder.toString();
    }

    public void testIsVowel() {
        System.out.println(isVowel('F'));
        System.out.println(isVowel('a'));
    }

    /**
     * This method should return a String that is the string phrase with all the vowels (uppercase or lowercase) replaced by ch
     *
     * @param phrase
     * @param ch
     * @return
     */
    public String replaceVowels(String phrase, char ch) {
        StringBuilder stringBuilder = new StringBuilder(phrase);

        for (int i = 0; i < phrase.length(); i++) {
            if (isVowel(stringBuilder.charAt(i))) {
                stringBuilder.setCharAt(i, ch);
            }
        }
        return stringBuilder.toString();
    }

    public void testReplaceVowels() {
        System.out.println(replaceVowels("Hello World", '*'));
        System.out.println(replaceVowels("AAABBBEEE", '*'));
    }

    public void testEmphasize() {
        System.out.println(emphasize("dna ctgaaactga", 'a'));
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
    }
}
