package java_sorting_app.controllers;

import java_sorting_app.menu.MenuController;


public abstract class Controller {

    protected Controller parentController;
    protected MenuController menuController;
    
    public Controller(String title, Controller parentController) {
        menuController = new MenuController(title);
        this.parentController = parentController;
    }

    public Controller getHandler(){
        return this;
    }
    
    public String getMenu(){
        return menuController.buildMenu();
    }

    public Controller process(String numberMenu) {
        try {
            int menuNum = Integer.parseInt(numberMenu);
            if (!menuController.containsItem(menuNum)) {
                System.out.println("Введенный пункт меню не существует!");
                return this;
            }
            IHandler<Controller> handler = menuController.getHandler(menuNum);
            return handler.process();
        }
        catch (NumberFormatException e) {
            return this;
        }
    }

}