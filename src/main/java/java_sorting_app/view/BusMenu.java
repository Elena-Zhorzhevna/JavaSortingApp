package java_sorting_app.view;

public class BusMenu implements Menu {
    private static String title = "Меню работы с автобусами";
    private static StringBuilder menuItems;

    static {
        menuItems = new StringBuilder();
        menuItems.append("-----------------").append("\n");
        menuItems.append(title).append("\n");
        menuItems.append("-----------------").append("\n");
        menuItems.append("1 > Добавить автобус").append("\n");
        menuItems.append("2 > Удалить автобус").append("\n");
        menuItems.append("3 > Найти автобус").append("\n");
        menuItems.append("4 > PWD").append("\n");
        menuItems.append("0 > Назад").append("\n");
        menuItems.append("? > ");
    }

    @Override
    public String getMenuItems() {
        return menuItems.toString();
    }

    @Override
    public String getMenuTitle() {
        return title;
    }
}
