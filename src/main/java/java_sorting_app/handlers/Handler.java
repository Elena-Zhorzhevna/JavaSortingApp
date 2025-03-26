package java_sorting_app.handlers;
import java_sorting_app.dao.DAOModel;
import java_sorting_app.menu.MenuController;
import java_sorting_app.model.Bus;


public abstract class Handler {

    protected Handler parentHandler;
    protected MenuController menuController;
    
    public Handler(String title, Handler parentHandler) {
        menuController = new MenuController(title);
        this.parentHandler = parentHandler;
    }

    protected DAOModel getDAOModel(){
        if(parentHandler != null){
            return parentHandler.getDAOModel();
        }
        return null;
    }

    public Handler getHandler(){
        return this;
    }
    
    public String getMenu(){
        return menuController.buildMenu();
    }

    public Handler process(int numberMenu) {
        if (!menuController.containsItem(numberMenu)) {
            System.out.println("Menu doesn't exist");
            return this;
        }
        IProcessor<Handler> handler = menuController.getHandler(numberMenu);
        return handler.process();
    }

}