package java_sorting_app.handlers;

import java_sorting_app.model.Model;
import java_sorting_app.menu.MenuController;

public abstract class Handler {

    protected static Model model;

    protected MenuController menuController;

    public Handler(String title) {
        menuController = new MenuController(title);
    }

    public static void setModel(Model model) {
        Handler.model = model;
    }

    public String getMenu(){
        return menuController.buildMenu();
    }

    public Handler process(int numberMenu) {
        Handler handler = getItemHandler(numberMenu);

        if (handler == this) {
            handle(numberMenu);
        }

        return handler;
    }

    protected abstract void handle(int numberMenu);

    protected Handler getItemHandler(int numberMenu) {
        if (!menuController.containsItem(numberMenu)) {
            return this;
        }
        return menuController.getHandler(numberMenu);
    }

    public String getPWD(){
        Handler parentHandler = menuController.getHandler(0);
        if(parentHandler != null){
            return parentHandler.getPWD() + "/" + menuController.getTitle();
        }
        return "/" + menuController.getTitle();
    }
}
