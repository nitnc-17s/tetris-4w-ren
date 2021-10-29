package tet4wren.environment;

public enum Tetrimino implements Mino {
    I(0), O(1), T(2), L(3), J(4), S(5), Z(6),;

    private final int id;
    Tetrimino(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public static int size() {
        return 7;
    }

}
