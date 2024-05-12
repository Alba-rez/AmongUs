package xogar;

import java.util.Scanner;
import menu.Menu;
import menu.SingletonScanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = SingletonScanner.getInstance();
        Menu menu = new Menu("Menu Principal");
        String ultimaOpcion = "";

        while (true) {
            if (!ultimaOpcion.equals("Xogar")) {
                menu.display();
                System.out.println("Por favor, elixa unha opción:");
            }
            int entradaUsu = sc.nextInt();
            sc.nextLine();


            if (entradaUsu >= 0 && entradaUsu < menu.getMenuSize()) {
                ultimaOpcion = menu.getMenuOptionName(entradaUsu);
                menu.manexarEntradaUsuario(entradaUsu);
            } else {
                System.out.println("Opción non válida. Por favor, intenta de novo.");
            }
        }
    }
}
