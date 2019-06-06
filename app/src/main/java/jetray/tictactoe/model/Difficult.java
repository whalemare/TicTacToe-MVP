package jetray.tictactoe.model;

/**
 * @author Irina Ivanova
 * @since 2019
 */
public enum Difficult {
    EASY("Легко"),
    MEDIUM("Средняя"),
    HARD("Сложно"),
    IMPOSSIBLE("Невозможная");

    public final String title;

    Difficult(String title) {
        this.title = title;
    }
}
