package java_sorting_app.state;

import java_sorting_app.handlers.Handler;
import java_sorting_app.model.Model;

public class State {
    private static Model model;

    static {
        model = new Model();
        Handler.setModel(model);
    }

    public State(Handler handler) {
        this.handler = handler;
    }

    private Handler handler;

    public String getMenu(){
        return handler.getMenu();
    }

    public boolean handle(int menuItem){
        handler = handler.process(menuItem);
        if(handler == null) {
            return false;
        }
        return true;
    }

}
