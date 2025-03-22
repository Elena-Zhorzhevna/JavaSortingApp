package java_sorting_app.view;

public class StudentMenu implements Menu {
    private static String title = "Меню работы со студентами";
    private static StringBuilder menuItems;

    static {
        menuItems = new StringBuilder();
        menuItems.append("-----------------").append("\n");
        menuItems.append(title).append("\n");
        menuItems.append("-----------------").append("\n");
        menuItems.append("1 > Добавить студента").append("\n");
        menuItems.append("2 > Удалить студента").append("\n");
        menuItems.append("3 > Найти студента").append("\n");
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
