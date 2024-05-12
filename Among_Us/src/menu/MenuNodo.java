package menu;

import java.util.ArrayList;
import java.util.List;

// Definición da clase MenuNodo que implementa a interface MenuComposite
public class MenuNodo implements MenuComposite{

    private String name;
    private List<MenuComposite> subMenu;

    public MenuNodo(String name) {
        this.name = name;
        this.subMenu = new ArrayList<>();
    }

    // Método para engadir un submenú á lista
    public void add(MenuComposite menuCompo) {
        subMenu.add(menuCompo);
    }

    // Método para obter a lista de submenús
    public List<MenuComposite> getSubMenu() {
        return subMenu;
    }

    @Override
    public String getName() {
        return name;
    }

    // Método sobreescrito para amosar o menú e os seus submenús
     @Override
    public void display(String prefix) {
        System.out.println(prefix + getName());
        for (MenuComposite menuCompo : subMenu) {
            menuCompo.display(prefix + "\t");
        }
    }


}
