package es.sgie.back.util;

import java.security.SecureRandom;

public final class UtilTest {

    private UtilTest() {}

    /**
     * Random Utils
     */
    public static final String getRandomValue(int length, boolean numeric) {
        String LETTER = "abcdefghijklmnopqrstuvwxyz";
        String NUMBER = "0123456789";

        String chars = null;
        if (numeric) {
            chars = NUMBER;
        } else {
            chars = LETTER;
        }

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }

        return sb.toString();
    }
}
