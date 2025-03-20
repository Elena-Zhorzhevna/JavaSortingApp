package java_sorting_app.handlers;

import java_sorting_app.model.Model;
import java_sorting_app.view.Menu;

public abstract class Handler {

    protected static Model model;

    protected Handler parentHandler;

    protected Menu menu;

    public Handler(Handler parentHandler, Menu menu) {
        this.parentHandler = parentHandler;
        this.menu = menu;
    }

    public static void setModel(Model model) {
        Handler.model = model;
    }

    public String getMenu(){
        return menu.getMenuItems();
    }

    public abstract Handler process(int numberMenu);

    public String getPWD(){
        if(parentHandler != null){
            return parentHandler.getPWD() + "/" + menu.getMenuTitle();
        }
        return "/" + menu.getMenuTitle();
    }
}
