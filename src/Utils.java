import java.util.Random;

public class Utils {

    public static String generateString(int length) {
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sb.append((char) (rnd.nextInt(123 - 28) + 48));
        return sb.toString();
    }

    public static void log(String message) {
        System.out.println(message);
    }
}
