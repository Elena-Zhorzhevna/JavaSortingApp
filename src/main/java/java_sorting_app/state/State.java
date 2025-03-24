package java_sorting_app.state;

import java_sorting_app.handlers.Handler;


public class State {

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