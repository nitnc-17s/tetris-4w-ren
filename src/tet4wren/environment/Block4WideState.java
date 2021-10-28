package tet4wren.environment;

public enum Block4WideState {
    // see comment's octal as binary
    Bx (0) , // un-chainable Blocks
    B1 (1) , // 0007
    B2 (2) , // 0013
    B3 (3) , // 0023
    B4 (4) , // 0031
    B5 (5) , // 0032
    B6 (6) , // 0103
    B7 (7) , // 0111
    B8 (8) , // 0112
    B9 (9) , // 0221
    B10(10), // 1002
    B11(11), // 1011
    B12(12), // 2011
    B13(13), // 1021
    B14(14), // 1012
    B15(15), // 1101
    B16(16), // 1102
    B17(17), // 1201
    B18(18), // 2101
    B19(19), // 1110
    B20(20), // 1220
    B21(21), // 2110
    B22(22), // 1300
    B23(23), // 2300
    B24(24), // 3001
    B25(25), // 3010
    B26(26), // 3100
    B27(27), // 3200
    B28(28), // 7000
    ;

    private final int id;
    Block4WideState(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
}
