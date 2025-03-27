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

    public Handler process(String numberMenu) {
        try {
            int menuNum = Integer.parseInt(numberMenu);
            if (!menuController.containsItem(menuNum)) {
                System.out.println("Menu doesn't exist");
                return this;
            }
            IProcessor<Handler> handler = menuController.getHandler(menuNum);
            return handler.process();
        }
        catch (NumberFormatException e) {
            return this;
        }
    }

}