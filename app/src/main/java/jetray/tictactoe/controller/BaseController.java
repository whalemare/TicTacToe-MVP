package jetray.tictactoe.controller;


import jetray.tictactoe.utils.Invokable;

/**
 * @author Irina Ivanova
 * @since 2019
 */
public class BaseController<View> {

    private View view;

    public void onAttach(View view) {
        this.view = view;
    }

    public void isAttached(Invokable<View> invokable){
        if (view != null) {
            invokable.invoke(view);
        }
    }
}
