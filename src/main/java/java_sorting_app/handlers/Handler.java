package java_sorting_app.handlers;
import java_sorting_app.dao.DAOModel;
import java_sorting_app.menu.MenuController;
import java_sorting_app.model.Bus;


public abstract class Handler<T> {

    protected Handler<T> parentHandler;
    protected MenuController menuController;
    
    public Handler(String title, Handler<T> parentHandler) {
        menuController = new MenuController(title);
        this.parentHandler = parentHandler;
    }

    protected DAOModel<T> getDAOModel(){
        if(parentHandler != null){
            return parentHandler.getDAOModel();
        }
        return null;
    }
    
    public String getMenu(){
        return menuController.buildMenu();
    }

    public Handler<T> process(int numberMenu) {
        Handler handler = getItemHandler(numberMenu);

        if (handler == this) {
            handle(numberMenu);
        }

        return handler;
    }

    protected abstract void handle(int numberMenu);

    protected Handler<T> getItemHandler(int numberMenu) {
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