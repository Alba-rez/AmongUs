package menu;

import java.util.ArrayList;
import java.util.List;

public class MenuNodo implements MenuComposite{

    private String name;
    private List<MenuComposite> subMenu;

    public MenuNodo(String name) {
        this.name = name;
        this.subMenu = new ArrayList<>();
    }

    public void add(MenuComposite menuCompo) {
        subMenu.add(menuCompo);
    }

    public List<MenuComposite> getSubMenu() {
        return subMenu;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void display(String prefix) {
        System.out.println(prefix + getName());
        for (MenuComposite menuCompo : subMenu) {
            menuCompo.display(prefix + "\t");
        }
    }


}
