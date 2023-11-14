package christmas.View;

import java.util.List;

import christmas.Model.Order;

public class OutputView {

    public static void getDate() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
    }

    public static void getOrder() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
    }

    public static void printEvent(int date) {
        System.out.println("12월 " + date + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");
    }

    public static void printException(String message) {
        System.out.println(message);
    }

    public static void printOrders(List<Order> orders) {
        System.out.println("<주문 메뉴>");
        for (Order order : orders) {
            System.out.println(order.getOrderName() + " " + order.getQuantity() + "개");
        }
        System.out.println();
    }

    public static void printAmountBeforeDiscount(int amount) {
        System.out.println("<할인 전 총주문 금액>");
        String formattedAmount = String.format("%,d", amount);
        System.out.println(formattedAmount + "원\n");
    }

    public static void printExtra(int amountBeforeDiscount) {
        System.out.println("<증정 메뉴>");
        if (amountBeforeDiscount >= 120000) {
            System.out.println("샴페인 1개\n");
            return;
        }
        System.out.println("없음\n");
    }

    public static void printDiscount(int dday, int weekday, int weekend, int special, int extra, int discounts) {
        System.out.println("<혜택 내역>");
        if (dday > 0)
            printDday(dday);
        if (weekday > 0)
            printWeekday(weekday);
        if (weekend > 0)
            printWeekend(weekend);
        if (special > 0)
            printSpecial(special);
        if (extra > 0)
            printExtraDiscount(extra);
        if (discounts == 0)
            System.out.println("없음");
        System.out.println();
    }

    private static void printWeekday(int weekday) {
        String formattedString = String.format("%,d", weekday);
        System.out.println("평일 할인: -" + formattedString + "원");
    }

    private static void printExtraDiscount(int extra) {
        String formattedString = String.format("%,d", extra);
        System.out.println("증정 이벤트: -" + formattedString + "원");
    }

    private static void printSpecial(int special) {
        String formattedString = String.format("%,d", special);
        System.out.println("특별 할인: -" + formattedString + "원");
    }

    private static void printWeekend(int weekend) {
        String formattedString = String.format("%,d", weekend);
        System.out.println("주말 할인: -" + formattedString + "원");
    }

    private static void printDday(int dday) {
        String formattedString = String.format("%,d", dday);
        System.out.println("크리스마스 디데이 할인: -" + formattedString + "원");
    }

    public static void printAllDiscounts(int discounts) {
        System.out.println("<총혜택 금액>");
        if (discounts == 0) {
            System.out.println("0원\n");
            return;
        }
        String formattedDiscounts = String.format("%,d", discounts);
        System.out.println("-" + formattedDiscounts + "원\n");
    }

    public static void printBadge(String calculateBadge) {
        System.out.println("<12월 이벤트 배지>");
        if (calculateBadge != null) {
            System.out.println(calculateBadge);
            return;
        }
        System.out.println("없음");
    }

    public static void printAmountAfterDiscount(int amount) {
        System.out.println("<할인 후 예상 결제 금액>");
        String formattedAmount = String.format("%,d", amount);
        System.out.println(formattedAmount + "원\n");
    }

}
