package es.sgie.back.util;

import java.security.SecureRandom;

/**
 * String util class
 */
public final class StringUtils {

    // String for passwords
    private final static String PAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz!$%&/()=,;.+-#*";

    // String for users
    private final static String USR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * Private constructor for utility class
     */
    private StringUtils() {

    }

    /**
     * Get random password for disabled users
     */
    public static final String getDisabledPassword() {
        return StringUtils.generateRandomString(30, StringUtils.PAS);
    }

    /**
     * Get random name for PIC to new users
     */
    public static final String getInitialPIC() {
        return StringUtils.generateRandomString(3, StringUtils.USR) + "-" +
                StringUtils.generateRandomString(3, StringUtils.USR) + "-" +
                StringUtils.generateRandomString(3, StringUtils.USR);
    }

    /**
     * Base util to generate random strings
     */
    private static String generateRandomString(int length, String chars) {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }

        return sb.toString();
    }

}
