package java_sorting_app.handlers;
import java_sorting_app.model.Model;
import java_sorting_app.view.Menu;

import java.util.HashMap;
import java.util.Map;


public abstract class Handler {

    protected Map<Integer, Handler> menuMap;

    protected static Model model;

    protected Menu menu;

    public Handler(Handler parentHandler, Menu menu) {
        this.menu = menu;
        menuMap = new HashMap<Integer, Handler>();
        menuMap.put(0, parentHandler);
    }

    public static void setModel(Model model) {
        Handler.model = model;
    }

    public String getMenu() {
        return menu.getMenuItems();
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
        if (!menuMap.containsKey(numberMenu)) {
            return this;
        }
        return menuMap.get(numberMenu);
    }

    public String getPWD() {
        Handler parentHandler = menuMap.get(0);
        if (parentHandler != null) {
            return parentHandler.getPWD() + "/" + menu.getMenuTitle();
        }
        return "/" + menu.getMenuTitle();
    }
}