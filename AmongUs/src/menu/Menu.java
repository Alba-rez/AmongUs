package menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import xogo.Xogo;

public class Menu {
    private List<MenuComposite> mainMenu;
    private Scanner scanner;
    private Xogo xogo;

    public Menu(String name) {
        this.mainMenu = new ArrayList<>();
        this.scanner = SingletonScanner.getInstance();
        this.xogo = new Xogo();

        // Crear menú principal
        MenuNodo configuracion = new MenuNodo("Configuracion");
        MenuComposite xogar = new MenuHoja("Xogar");
        MenuComposite sair = new MenuHoja("Sair");

        // Crear submenús de configuracion
        MenuNodo tarefas = new MenuNodo("Tarefas");
        tarefas.add(new MenuHoja("Engadir tarefa"));
        tarefas.add(new MenuHoja("Borrar tarefa"));
        tarefas.add(new MenuHoja("Ver tarefas"));
        tarefas.add(new MenuHoja("Sair"));

        MenuNodo xogadores = new MenuNodo("Xogadores");
        xogadores.add(new MenuHoja("Engadir xogador"));
        xogadores.add(new MenuHoja("Borrar xogador"));
        xogadores.add(new MenuHoja("Ver xogadores"));
        xogadores.add(new MenuHoja("Sair"));

        configuracion.add(tarefas);
        configuracion.add(xogadores);
        configuracion.add(new MenuHoja("Configurar tempo máximo de resposta"));
        configuracion.add(new MenuHoja("Sair"));

        // Agregar opciones al menú principal
        mainMenu.add(configuracion);
        mainMenu.add(xogar);
        mainMenu.add(sair);
    }

    public void display() {
        for (int i = 0; i < mainMenu.size(); i++) {
            System.out.println(i + ". " + mainMenu.get(i).getName());
        }
    }

    public int getMenuSize() {
        return mainMenu.size();
    }

    public void handleUserInput(int userInput) {
        MenuComposite chosenOption = mainMenu.get(userInput);
        if (chosenOption instanceof MenuHoja) {
            switch (chosenOption.getName()) {
                case "Xogar":
                    // Aquí puedes llamar al método para iniciar el juego
                    break;
                case "Sair":
                    System.out.println("Saliendo...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intenta de nuevo.");
            }
        } else if (chosenOption instanceof MenuNodo) {
            handleSubMenuInput((MenuNodo) chosenOption, "\t");
        }
    }

    private void handleSubMenuInput(MenuNodo menuNod, String prefix) {
        while (true) {
            for (int i = 0; i < menuNod.getSubMenu().size(); i++) {
                System.out.println(prefix + i + ". " + menuNod.getSubMenu().get(i).getName());
            }
            System.out.println(prefix + "Por favor, elixe unha opción:");
            int userInput = scanner.nextInt();

            if (userInput >= 0 && userInput < menuNod.getSubMenu().size()) {
                MenuComposite chosenOption = menuNod.getSubMenu().get(userInput);
                if (chosenOption instanceof MenuHoja) {
                    switch (chosenOption.getName()) {
                        case "Engadir tarefa":
                            xogo.engadirTarefa();
                            break;
                        case "Borrar tarefa":
                            xogo.borrarTarefa();
                            break;
                        case "Ver tarefas":
                            xogo.verTarefas();
                            break;
                        case "Sair":
                            return; // Vuelve al menú principal
                        case "Engadir xogador":
                            xogo.engadirXogador();
                            break;
                        case "Borrar xogador":
                            xogo.borrarXogador();
                            break;
                        case "Ver xogadores":
                            xogo.verXogadores();
                            break;
                        case "Configurar tempo máximo de resposta":
                            xogo.configurarTempo();
                            break;
                        // Agrega aquí los casos para las otras opciones de submenú
                    }
                } else if (chosenOption instanceof MenuComposite) {
                    handleSubMenuInput((MenuNodo) chosenOption, prefix + "\t");
                }
            } else {
                System.out.println(prefix + "Opción no válida. Por favor, intenta de nuevo.");
            }
        }
    }
}