package Utilities;

import java.util.Scanner;

public class Utilities {
    Scanner scanner = new Scanner(System.in);
    public String getUserInput() {
        return scanner.nextLine();
    }

    public void print(String text) {
        System.out.println(text);
    }

}
