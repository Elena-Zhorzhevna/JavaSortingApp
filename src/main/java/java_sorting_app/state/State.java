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

    public boolean handle(String menuItem){
        int menuNum = -1;
        try {
            menuNum = Integer.parseInt(menuItem);
            handler = handler.process(menuNum);
            if(handler == null) {
                return false;
            }
            return true;
        }
        catch (NumberFormatException e) {
            return true;
        }
    }
}