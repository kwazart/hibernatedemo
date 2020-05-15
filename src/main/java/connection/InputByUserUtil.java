package connection;

import java.util.Scanner;

public class InputByUserUtil {
    public static String inputData() {
        return new Scanner(System.in).nextLine();
    }

    public static long inputLong() {
        System.out.print("Input index number: ");
        while (true) {
            String s = inputData();
            if (isDigit(s)) {
                return Long.parseLong(s);
            }
        }
    }

    public static boolean isDigit (String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isNotFound(Object obj) {
        if (obj == null) {
            System.out.println("Object is not found.");
            return false;
        }
        return true;
    }
}