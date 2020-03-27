package in.bsnl.mobile.app.ws.shared.utils;

import java.util.Random;

public abstract class RandomAlertId {
    public static String generate(int size) {
        final String ALPHANUMERICSTRING = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb  = new StringBuilder(size);
        Random random = new Random();
        for(int i = 0; i< size; i++) {
            sb.append(ALPHANUMERICSTRING.charAt(random.nextInt(ALPHANUMERICSTRING.length())));
        }
        return sb.toString();
    }

}
