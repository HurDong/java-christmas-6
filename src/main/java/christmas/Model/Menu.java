package christmas.Model;

public class Menu {
    private String menuType;
    private String menuName;
    private int price;

    public Menu(String menuType, String menuName, int price) {
        this.menuType = menuType;
        this.menuName = menuName;
        this.price = price;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
