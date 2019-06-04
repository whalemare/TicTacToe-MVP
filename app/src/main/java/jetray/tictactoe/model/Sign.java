package jetray.tictactoe.model;

/**
 * @author Anton Vlasov - whalemare
 * @since 2019
 */
public enum Sign {
    X {
        @Override
        public Sign another() {
            return O;
        }
    },
    O {
        @Override
        public Sign another() {
            return X;
        }
    };

    public abstract Sign another();
}
