package u1;

public class cipher {

    static final String alphabet = "ABCDEFGHIJKLMNOPQRTSUVWXYZ";

    public static void main(String[] args) throws Exception {
        String textToEncrypt = "hello world!";
        int shiftAmount = 3;

        String encryptedText = encrypt(textToEncrypt,shiftAmount);
        //System.out.println(encryptedText);

        encryptedText = encrypt2(textToEncrypt, shiftAmount);
        //System.out.println(encryptedText);
    }

    public static String encrypt(String text, int shiftAmount) {
        long startTime = System.nanoTime();
        text = text.toUpperCase();
        shiftAmount %= alphabet.length();

        StringBuilder retString = new StringBuilder();

        for(Character c : text.toCharArray()) {
            char newChar;

            if (alphabet.contains(c.toString())) {
                int pos = alphabet.indexOf(c) + shiftAmount;

                if (pos >= alphabet.length()) {
                    pos -= alphabet.length();
                } else if (pos < 0) {
                    pos += alphabet.length();
                }

                newChar = alphabet.charAt(pos);
            }
            else{newChar = c;}

            retString.append(newChar);
        }

        long stopTime = System.nanoTime();
        System.out.println(stopTime-startTime + "ns");
        return retString.toString();
    }


    public static String encrypt2(String text, int shiftAmount){
        long startTime = System.nanoTime();
        StringBuilder retString = new StringBuilder();

        for(Character c : text.toCharArray()){
            retString.append((char) (c + shiftAmount));
        }

        long stopTime = System.nanoTime();
        System.out.println(stopTime-startTime + "ns");
        return retString.toString();
    }
}
