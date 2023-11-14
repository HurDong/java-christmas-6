package christmas;

import java.util.ArrayList;
import java.util.List;

import christmas.Controller.XmasController;
import christmas.Model.Menu;

public class Application {
    public static void main(String[] args) {
        XmasController xmasController = new XmasController();
        xmasController.init(menus());
    }

    private static List<Menu> menus() {
        List<Menu> menus = new ArrayList<>();
        menus.add(new Menu("애피타이저", "양송이수프", 6000));
        menus.add(new Menu("애피타이저", "타파스", 5500));
        menus.add(new Menu("애피타이저", "시저샐러드", 8000));
        menus.add(new Menu("메인", "티본스테이크", 55000));
        menus.add(new Menu("메인", "바비큐립", 54000));
        menus.add(new Menu("메인", "해산물파스타", 35000));
        menus.add(new Menu("메인", "크리스마스파스타", 25000));
        menus.add(new Menu("디저트", "초코케이크", 15000));
        menus.add(new Menu("디저트", "아이스크림", 5000));
        menus.add(new Menu("음료", "제로콜라", 3000));
        menus.add(new Menu("음료", "레드와인", 60000));
        menus.add(new Menu("음료", "샴페인", 25000));
        return menus;
    }

}
