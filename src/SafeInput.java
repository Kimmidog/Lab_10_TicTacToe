import java.util.Scanner;

public class SafeInput {
    public static int getRangedInt(Scanner console, String prompt, int low, int high) {
        int input;
        do {
            System.out.print(prompt);
            while (!console.hasNextInt()) {
                console.next();
                System.out.print(prompt);
            }
            input = console.nextInt();
        } while (input < low || input > high);
        return input;
    }

    public static boolean getYNConfirm(Scanner console, String prompt) {
        String response;
        do {
            System.out.print(prompt);
            response = console.next().trim().toLowerCase();
        } while (!response.equals("y") && !response.equals("n"));
        return response.equals("y");
    }
}
