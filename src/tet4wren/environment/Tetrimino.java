package tet4wren.environment;

public enum Tetrimino implements Mino {
    I, O, T, J, L, S, Z,;

    @Override
    public int number() {
        switch (this) {
            case I -> { return 0; }
            case O -> { return 1; }
            case T -> { return 2; }
            case J -> { return 3; }
            case L -> { return 4; }
            case S -> { return 5; }
            case Z -> { return 6; }
            default -> { return -1; }
        }
    }

    @Override
    public int size() {
        return 7;
    }

}
