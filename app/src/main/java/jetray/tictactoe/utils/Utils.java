package jetray.tictactoe.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.List;

/**
 * @author Irina Ivanova
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

    public static <Item> int indexOf(List<Item> list, Searchable<Item> searchable) {
        for (int i = 0; i < list.size(); i++) {
            if (searchable.search(list.get(i))) {
                return i;
            }
        }
        return -1;
    }

    public interface Searchable<Item> {
        boolean search(Item item);
    }

}
