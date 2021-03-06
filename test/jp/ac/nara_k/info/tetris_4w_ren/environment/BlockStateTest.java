package jp.ac.nara_k.info.tetris_4w_ren.environment;

import org.junit.jupiter.api.Test;

class BlockStateTest {

    @Test
    void nextState() {
        for (long seed = 0; seed < 4; seed++) {
            MinoHolder minoHolder = new MinoHolder(3, new SevenBag(seed));
            BlockState blockState = BlockState.B3;
            int renCount = -2;
            while (blockState != null) {
                Tetrimino current = minoHolder.getCurrentMino();
                Tetrimino hold = minoHolder.getHoldMino();
                BlockState next_state = null;
                boolean consumeCurrent = true;
                for (TetriminoPlacement placement : TetriminoPlacement.placementsFromTetrimino(current)) {
                    BlockState next = blockState.nextState(placement);
                    if ( next != null ) {
                        if ( next_state == null || next_state.getId() == 0 ) {
                            next_state = next;
                        }
                    }
                }
                for (TetriminoPlacement placement : TetriminoPlacement.placementsFromTetrimino(hold)) {
                    BlockState next = blockState.nextState(placement);
                    if ( next != null ) {
                        if ( next_state == null || next_state.getId() == 0 ) {
                            next_state = next;
                            consumeCurrent = false;
                        }
                    }
                }
                System.out.println(minoHolder);
                Tetrimino mino = minoHolder.consume(consumeCurrent);
                System.out.print(blockState.toBlockAscii());
                System.out.println("vvv USAGE: " + mino + " vvv");
                blockState = next_state;
                renCount++;
            }
            System.out.println("End: " + renCount + "REN\n");
        }
    }
}
