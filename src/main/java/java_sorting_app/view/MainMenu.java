package java_sorting_app.view;

public class MainMenu implements Menu {
    private static String title = "Главное меню";
    private static StringBuilder menuItems;

    static {
        menuItems = new StringBuilder();
        menuItems.append("-----------------").append("\n");
        menuItems.append(title).append("\n");
        menuItems.append("-----------------").append("\n");
        menuItems.append("1 > Работа с автобусами").append("\n");
        menuItems.append("2 > Работа со студентами").append("\n");
        menuItems.append("3 > Работа с пользователями").append("\n");
        menuItems.append("0 > Выход").append("\n");
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
