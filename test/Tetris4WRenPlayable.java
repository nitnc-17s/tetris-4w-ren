import jp.ac.nara_k.info.tetris_4w_ren.environment.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Tetris4WRenPlayable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose your seed:");
        long seed = scanner.nextLong();
        MinoHolder minoHolder = new MinoHolder(3, new SevenBag(seed));
        BlockState blockState = BlockState.B3;
        int renCount = -1;

        while (true) {
            Tetrimino current = minoHolder.getCurrentMino();
            Tetrimino hold = minoHolder.getHoldMino();
            ArrayList<BlockState> nextStates = new ArrayList<>();
            boolean consumeCurrent = true;
            int currentBound = 0;
            for (TetriminoPlacement placement : TetriminoPlacement.placementsFromTetrimino(current)) {
                BlockState next = blockState.nextState(placement);
                if ( next != null ) {
                    nextStates.add(next);
                    currentBound++;
                }
            }
            for (TetriminoPlacement placement : TetriminoPlacement.placementsFromTetrimino(hold)) {
                BlockState next = blockState.nextState(placement);
                if ( next != null ) {
                    nextStates.add(next);
                }
            }
            if (nextStates.isEmpty()) {
                break;
            }

            System.out.println(minoHolder);
            System.out.println("Current Block State:");
            System.out.println(blockState.toBlockAscii());
            for (int i = 0; i < nextStates.size(); i++) {
                System.out.println("" + i + " (" + (i < currentBound ? current : hold) + ") :");
                System.out.println(nextStates.get(i).toBlockAscii());
            }

            BlockState nextState = null;
            while (nextState == null) {
                System.out.print("Choose next State:");
                int index = scanner.nextInt();
                if (index < nextStates.size()) {
                    nextState = nextStates.get(index);
                    consumeCurrent = index < currentBound;
                }
            }

            Tetrimino mino = minoHolder.consume(consumeCurrent);
            System.out.println("vvv USAGE: " + mino + " vvv");
            blockState = nextState;
            renCount++;
        }

        System.out.println("End: " + renCount + "REN\n");
    }
}
