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

}
