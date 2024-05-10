package menu;

class MenuHoja implements MenuComposite{
    private String name;

    public MenuHoja(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void display(String prefix) {
        System.out.println(prefix + getName());
    }
}


