package christmas.Exception;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import christmas.Model.Menu;

public class Exception {

    public static void dateValidate(String input) {
        int date;
        try {
            date = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요");
        }
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public static void orderValidate(String order, List<Menu> menus) {
        Set<String> validMenuItems = menus.stream().map(Menu::getMenuName).collect(Collectors.toSet()); // MenuName만추출하여저장
        Set<String> orderedItems = new HashSet<>(); // 중복 확인을 위해 선언
        int totalQuantity = 0;
        String[] items = order.split(",");
        for (String item : items) {
            String[] parts = parseOrderItem(item);
            String menuItem = parts[0];
            int quantity = Integer.parseInt(parts[1]);
            validateMenuItem(menuItem, validMenuItems);
            checkForDuplicateItems(menuItem, orderedItems);
            totalQuantity += quantity;
        }
        checkTotalQuantity(totalQuantity);
    }

    private static String[] parseOrderItem(String item) {
        String[] parts = item.trim().split("-");
        if (parts.length != 2 || !isNumeric(parts[1].trim())) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문 형식입니다. 다시 입력해 주세요.");
        }
        return parts;
    }

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static void validateMenuItem(String menuItem, Set<String> validMenuItems) {
        if (!validMenuItems.contains(menuItem)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 메뉴 항목입니다. 다시 입력해 주세요.");
        }
    }

    private static void checkForDuplicateItems(String menuItem, Set<String> orderedItems) {
        if (!orderedItems.add(menuItem)) {
            throw new IllegalArgumentException("[ERROR] 중복된 메뉴 항목입니다. 다시 입력해 주세요.");
        }
    }

    private static void checkTotalQuantity(int totalQuantity) {
        if (totalQuantity > 20) {
            throw new IllegalArgumentException("[ERROR] 주문 가능한 총 메뉴 항목 수는 20개를 초과할 수 없습니다.");
        }
    }

}
