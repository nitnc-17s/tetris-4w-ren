package jp.ac.nara_k.info.tetris_4w_ren.environment;

public enum BlockState {
    // see octal as binary
    B0 (0 , 07777), // un-chainable Blocks
    B1 (1 , 00007),
    B2 (2 , 00013),
    B3 (3 , 00023),
    B4 (4 , 00031),
    B5 (5 , 00032),
    B6 (6 , 00103),
    B7 (7 , 00111),
    B8 (8 , 00112),
    B9 (9 , 00221),
    B10(10, 01003),
    B11(11, 01011),
    B12(12, 02011),
    B13(13, 01021),
    B14(14, 01012),
    B15(15, 01101),
    B16(16, 01102),
    B17(17, 01201),
    B18(18, 02101),
    B19(19, 01110),
    B20(20, 01220),
    B21(21, 02110),
    B22(22, 01300),
    B23(23, 02300),
    B24(24, 03001),
    B25(25, 03010),
    B26(26, 03100),
    B27(27, 03200),
    B28(28, 07000),
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
    private final int block;

    BlockState(int id, int block) {
        this.id = id;
        this.block = block;
    }

    public BlockState nextState(TetriminoPlacement placement) {
        int state = this.getId();
        int place = placement.getId();
        return blockStateTransition[state][place];
    }

    public static int stateSize() {
        return values().length;
    }

    public String toBlockAscii() {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            int index = 11 - (i % 4) * 3 - i / 4;
            ret.append(((block >> index) & 1) == 1 ? "#" : ".");
            if (i%4 == 3) ret.append("\n");
        }
        return ret.toString();
    }

    public int getId() {
        return id;
    }
}
