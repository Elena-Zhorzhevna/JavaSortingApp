package java_sorting_app.handlers;

import java_sorting_app.view.UserMenu;

public class UserHandler extends Handler {
    public UserHandler(Handler handler) {
        super(handler, new UserMenu());
        menuMap.put(1, this);
        menuMap.put(2, this);
        menuMap.put(3, this);
        menuMap.put(4, this);
    }

    @Override
    protected void handle(int numberMenu) {
        //pass
    }
}
