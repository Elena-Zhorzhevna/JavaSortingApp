package java_sorting_app.menu;

import java_sorting_app.handlers.Handler;
import java_sorting_app.util.CustomArrayList;

public class MenuController {
    String title;
    CustomArrayList<Integer> numbersItemMenu;
    CustomArrayList<String> labelItemsMenu;
    CustomArrayList<Handler> handlers;

    public MenuController(String title) {
        this.title = title;
        numbersItemMenu = new CustomArrayList<>();
        labelItemsMenu = new CustomArrayList<>();
        handlers = new CustomArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void addItem(Integer numberItem, String labelItem, Handler handler) {
        numbersItemMenu.add(numberItem);
        labelItemsMenu.add(labelItem);
        handlers.add(handler);
    }

    public String buildMenu() {
        StringBuilder menuItems = new StringBuilder();
        menuItems.append("-----------------").append("\n");
        menuItems.append(title).append("\n");
        menuItems.append("-----------------").append("\n");
        for (int i = 0; i < numbersItemMenu.size(); i++) {
            menuItems.append(numbersItemMenu.get(i)).append(" > ");
            menuItems.append(labelItemsMenu.get(i)).append("\n");
        }
        menuItems.append("? > ");

        return menuItems.toString();
    }

    public Handler getHandler(int index) {
        for(int i = 0; i < numbersItemMenu.size(); i++) {
            if (numbersItemMenu.get(i) == index) {
                return handlers.get(i);
            }
        }
        return null;
    }

    public boolean containsItem(int numberMenu) {
        for(int i = 0; i < numbersItemMenu.size(); i++) {
            if(numbersItemMenu.get(i) == numberMenu) {
                return true;
            }
        }
        return false;
    }

}
