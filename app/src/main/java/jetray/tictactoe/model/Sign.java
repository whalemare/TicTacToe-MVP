package jetray.tictactoe.model;

/**
 * @author Irina Ivanova
 * @since 2019
 */
public enum Sign {
    O {
        @Override
        public Sign another() {
            return X;
        }
    },
    X {
        @Override
        public Sign another() {
            return O;
        }
    };

    public abstract Sign another();
}
