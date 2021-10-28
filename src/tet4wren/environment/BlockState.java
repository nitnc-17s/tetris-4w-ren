package tet4wren.environment;

public enum BlockState {
    // see comment's octal as binary
    B0 (0) , // un-chainable Blocks
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

    private static final BlockState[][] blockStateTransition = new BlockState[][] {
            {B0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
            {B1, null, null, B6, null, B6, null, B2, null, B10, null, B10, null, B2, null, null, null, null, null},
            {B2, null, B15, B0, null, B7, null, B4, B24, B11, null, B12, null, null, B0, B7, null, null, null},
            {B3, null, B15, B7, B24, null, B0, null, null, null, null, B11, null, null, null, B9, B18, B7, B17},
            {B4, null, B19, null, null, null, null, null, B25, null, null, null, null, null, B0, null, null, null, null},
            {B5, null, B19, null, B25, null, B0, null, null, null, null, null, null, null, null, B0, B21, null, B0},
            {B6, null, null, B0, null, null, null, B0, null, B15, null, B18, null, B7, null, null, null, null, null},
            {B7, B28, null, null, B26, null, null, null, null, B19, null, null, B27, null, null, null, null, null, B22},
            {B8, null, null, null, null, null, null, B0, null, B19, null, B21, null, null, null, null, null, null, null},
            {B9, null, null, null, null, null, null, null, null, null, null, B19, null, null, null, null, null, null, null},
            {B10, null, B7, B17, null, B15, null, B13, B0, null, null, B24, null, B11, null, null, null, B15, null},
            {B11, B0, null, null, B0, B19, B22, null, null, B7, B23, null, null, B0, null, null, B26, null, B0},
            {B12, null, null, B4, null, null, null, B2, null, B7, null, B0, null, null, null, B19, null, null, null},
            {B13, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, B19, null},
            {B14, null, null, B0, null, B19, null, B0, null, null, null, B25, null, null, null, null, null, null, null},
            {B15, B0, null, null, B4, B7, B0, null, null, B0, null, null, B5, B19, null, null, B0, null, B2},
            {B16, null, null, B22, null, null, null, B0, null, null, null, B26, null, B19, null, null, null, B7, null},
            {B17, null, null, null, null, null, null, null, null, null, null, null, null, null, null, B7, null, null, null},
            {B18, null, null, B0, null, B7, null, B6, null, null, null, B0, null, null, null, null, null, null, null},
            {B19, B1, null, null, null, null, B2, null, null, null, B3, null, null, B7, null, null, B4, null, null},
            {B20, null, null, null, null, null, null, B7, null, null, null, null, null, null, null, null, null, null, null},
            {B21, null, null, null, null, null, null, B8, null, null, null, B0, null, B7, null, null, null, null, null},
            {B22, null, B7, null, null, null, null, null, B0, null, null, null, null, null, B0, null, null, null, null},
            {B23, null, B7, null, B0, null, B6, null, null, null, null, null, null, null, null, B0, null, B0, B8},
            {B24, null, B19, B13, null, B11, null, B10, B22, B15, null, B17, null, null, B0, B11, null, null, null},
            {B25, null, null, B0, null, null, null, B14, null, B19, null, B0, null, B11, null, null, null, null, null},
            {B26, null, B11, B0, null, B19, null, B16, B0, null, null, B22, null, B15, B10, null, null, B19, null},
            {B27, null, B11, B19, B0, null, B10, B15, null, null, null, null, null, null, null, B19, B13, B20, B14},
            {B28, null, null, B25, null, B25, null, B26, null, B24, null, B24, null, B26, null, null, null, null, null},
    };

    private final int id;
    BlockState(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public BlockState nextState(TetriminoPlacement placement) {
        int state = this.getId();
        int place = placement.getId();
        return blockStateTransition[state][place];
    }

}
