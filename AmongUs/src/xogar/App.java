package xogar;

import java.util.Scanner;
import menu.Menu;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu("Menu Principal");

        while (true) {
            menu.display();
            System.out.println("Por favor, elige una opción:");
            int userInput = scanner.nextInt();

            if (userInput >= 0 && userInput < menu.getMenuSize()) {
                menu.handleUserInput(userInput);
            } else {
                System.out.println("Opción no válida. Por favor, intenta de nuevo.");
            }
        }
    }
}