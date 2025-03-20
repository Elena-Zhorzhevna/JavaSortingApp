package java_sorting_app.handlers;

import java_sorting_app.view.UserMenu;

public class UserHandler extends Handler {
    public UserHandler(Handler handler) {
        super(handler, new UserMenu());
    }

    @Override
    public Handler process(int numberMenu) {
        return null;
    }
}
