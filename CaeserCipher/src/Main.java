import edu.duke.FileResource;

public class Main {

    public static void main(String[] args) {
//      TestCaesarCipher testCaesarCipher = new TestCaesarCipher();
//      testCaesarCipher.simpleTests();
//
        TestCaesarCipherTwo testCaesarCipherTwo = new TestCaesarCipherTwo();
//      testCaesarCipherTwo.simpleTests();


        System.out.println(testCaesarCipherTwo.breakCaesarCipher("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!"));
        CaeserCipher caeserCipher = new CaeserCipher(15);
        System.out.println(caeserCipher.encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?"));

        CaesarCipherTwo caesarCipherTwo = new CaesarCipherTwo(21, 8);
        System.out.println(caesarCipherTwo.encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?"));

        caesarCipherTwo = new CaesarCipherTwo(14, 24);
        System.out.println(caesarCipherTwo.decrypt("Hfs cpwewloj loks cd Hoto kyg Cyy"));


        FileResource fileResource = new FileResource("mysteryTwoKeysQuiz.txt");
        String content = fileResource.asString();
        System.out.println(testCaesarCipherTwo.breakCaesarCipher(content));
    }
}
