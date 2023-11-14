package christmas.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import christmas.Exception.Exception;
import christmas.Model.Menu;
import christmas.Model.Order;
import christmas.View.InputView;
import christmas.View.OutputView;

public class XmasController {
    public void init(List<Menu> menus) {
        int date = getDate();
        List<Order> orders = null;
        while (orders == null) {
            try {
                String order = getOrder(menus);
                orders = convertOrder(order, menus);
            } catch (IllegalArgumentException e) {
                OutputView.printException(e.getMessage());
            }
        }
        OutputView.printEvent(date);
        OutputView.printOrders(orders);
        int amountBeforeDiscount = getAmountBeforeDiscount(menus, orders);
        OutputView.printAmountBeforeDiscount(amountBeforeDiscount);
        getExtraItem(amountBeforeDiscount);
        orderDiscount(orders, menus, amountBeforeDiscount, date);
    }

    private void orderDiscount(List<Order> orders, List<Menu> menus, int amountBeforeDiscount, int date) {
        int dday = 0, weekday = 0, weekend = 0, special = 0, extra = 0;
        if (amountBeforeDiscount >= 10000) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2023, Calendar.DECEMBER, date); // 2023 12년
            dday = calculateDdayDiscount(date);
            weekday = calculateWeekdayDiscount(calendar, orders, menus);
            weekend = calculateWeekendDiscount(calendar, orders, menus);
            special = calculateSpecialDiscount(date);
            extra = calcualateExtralDiscount(amountBeforeDiscount);
        }
        int discounts = dday + weekday + weekend + special + extra;
        OutputView.printDiscount(dday, weekday, weekend, special, extra, discounts);
        displayDiscountResults(amountBeforeDiscount, discounts, extra);
    }

    private void displayDiscountResults(int amountBeforeDiscount, int discounts, int extra) {
        OutputView.printAllDiscounts(discounts);
        int sum = amountBeforeDiscount - discounts + extra;
        OutputView.printAmountAfterDiscount(sum);
        OutputView.printBadge(calculateBadge(discounts));
    }

    private String calculateBadge(int discounts) {
        if (discounts >= 20000) {
            return "산타";
        }
        if (discounts >= 10000) {
            return "트리";
        }
        if (discounts >= 5000) {
            return "별";
        }
        return null;
    }

    private int calcualateExtralDiscount(int amountBeforeDiscount) {
        if (amountBeforeDiscount >= 120000) {
            return 25000;
        }
        return 0;
    }

    private int calculateSpecialDiscount(int date) {
        Set<Integer> specialDays = new HashSet<>(Arrays.asList(3, 10, 17, 24, 25, 31));
        if (specialDays.contains(date)) {
            return 1000;
        }
        return 0;
    }

    private int calculateWeekendDiscount(Calendar calendar, List<Order> orders, List<Menu> menus) {
        if (!isWeekend(calendar.get(Calendar.DAY_OF_WEEK))) {
            return 0;
        }
        return calculateDiscountForOrderType(orders, menus, "메인");
    }

    private boolean isWeekend(int date) {
        return date == Calendar.FRIDAY || date == Calendar.SATURDAY;
    }

    private int calculateWeekdayDiscount(Calendar calendar, List<Order> orders, List<Menu> menus) {
        if (!isWeekday(calendar.get(Calendar.DAY_OF_WEEK))) {
            return 0;
        }
        return calculateDiscountForOrderType(orders, menus, "디저트");
    }

    private int calculateDiscountForOrderType(List<Order> orders, List<Menu> menus, String menuType) {
        int discount = 0;
        for (Order order : orders) {
            discount += calculateDiscountForSingleOrder(order, menus, menuType);
        }
        return discount;
    }

    private int calculateDiscountForSingleOrder(Order order, List<Menu> menus, String menuType) {
        for (Menu menu : menus) {
            if (order.getOrderName().equals(menu.getMenuName()) && menu.getMenuType().equals(menuType)) {
                return 2023 * order.getQuantity();
            }
        }
        return 0;
    }

    private boolean isWeekday(int date) {
        return date >= Calendar.SUNDAY && date <= Calendar.THURSDAY;
    }

    private int calculateDdayDiscount(int date) {
        if (date < 1 || date > 25) {
            return 0;
        }
        return 1000 + (date - 1) * 100;
    }

    private void getExtraItem(int amountBeforeDiscount) {
        OutputView.printExtra(amountBeforeDiscount);
    }

    private int getAmountBeforeDiscount(List<Menu> menus, List<Order> orders) {
        int amount = 0;
        for (Order order : orders) {
            amount += calculatePrice(order.getOrderName(), order.getQuantity(), menus);
        }
        return amount;
    }

    private int calculatePrice(String orderName, int quantity, List<Menu> menus) {
        for (Menu menu : menus) {
            if (orderName.equals(menu.getMenuName())) {
                return menu.getPrice() * quantity;
            }
        }
        return 0;
    }

    private int getDate() {
        OutputView.getDate();
        int date;
        try {
            String input = InputView.readDate();
            Exception.dateValidate(input);
            date = Integer.parseInt(input);
            return date;
        } catch (IllegalArgumentException e) {
            OutputView.printException(e.getMessage());
            getDate();
        }
        return -1;
    }

    private String getOrder(List<Menu> menus) {
        OutputView.getOrder();
        String order;
        order = InputView.readOrder();
        Exception.orderValidate(order, menus);
        return order;
    }

    private List<Order> convertOrder(String order, List<Menu> menus) {
        List<Order> orders = new ArrayList<>();
        boolean onlyBeverage = true;
        String[] items = order.split(",");
        for (String item : items) {
            String[] parts = item.trim().split("-");
            String orderName = parts[0].trim();
            int quantity = Integer.parseInt(parts[1].trim());
            orders.add(new Order(orderName, quantity));
            if (!isBeverage(orderName, menus))
                onlyBeverage = false;
        }
        if (onlyBeverage)
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문 형식입니다. 다시 입력해 주세요.");
        return orders;
    }

    private boolean isBeverage(String orderName, List<Menu> menus) {
        for (Menu menu : menus) {
            if (orderName.equals(menu.getMenuName()) && "음료".equals(menu.getMenuType())) {
                return true;
            }
        }
        return false;
    }

}
