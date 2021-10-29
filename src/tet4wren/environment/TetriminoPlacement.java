package tet4wren.environment;

public enum TetriminoPlacement {
    I1(0),
    I2(1),
    O1(2),
    T1(3),
    T2(4),
    T3(5),
    T4(6),
    L1(7),
    L2(8),
    L3(9),
    L4(10),
    J1(11),
    J2(12),
    J3(13),
    J4(14),
    S1(15),
    S2(16),
    Z1(17),
    Z2(18),
    ;

    private final int id;
    TetriminoPlacement(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static TetriminoPlacement[] placementsFromTetrimino(Tetrimino tetrimino) {
        switch (tetrimino) {
            case I -> { return new TetriminoPlacement[] {I1, I2}; }
            case O -> { return new TetriminoPlacement[] {O1}; }
            case T -> { return new TetriminoPlacement[] {T1, T2, T3, T4}; }
            case L -> { return new TetriminoPlacement[] {L1, L2, L3, L4}; }
            case J -> { return new TetriminoPlacement[] {J1, J2, J3, J4}; }
            case S -> { return new TetriminoPlacement[] {S1, S2}; }
            case Z -> { return new TetriminoPlacement[] {Z1, Z2}; }
        }
        return null;
    }

}
