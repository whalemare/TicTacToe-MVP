package jetray.tictactoe;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import jetray.tictactoe.controller.Invokable;

/**
 * @author Anton Vlasov - whalemare
 * @since 2019
 */
public class Utils {

    public static TextWatcher onTextChanges(EditText editText, Invokable<String> invokable) {
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                invokable.invoke(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        editText.addTextChangedListener(watcher);

        return watcher;
    }
}
