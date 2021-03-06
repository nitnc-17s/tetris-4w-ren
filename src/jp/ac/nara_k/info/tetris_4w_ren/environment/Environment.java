package jp.ac.nara_k.info.tetris_4w_ren.environment;

import java.util.ArrayList;
import java.util.Random;

public class Environment {
    private final MinoHolder minoHolder;
    private BlockState blockState;
    private final ArrayList<TetriminoPlacement> placementsList;
    private final ArrayList<BlockState> nextBlockStateList;
    private int currentPlacementBound;

    public Environment(int nextNumber, long seed) {
        this.minoHolder = new MinoHolder(nextNumber, new SevenBag(seed));
        this.blockState = BlockState.B3;
        this.placementsList = new ArrayList<>();
        this.nextBlockStateList = new ArrayList<>();
        updateNextStates();
    }

    public Environment(int nextSize) {
        this(nextSize, new Random().nextLong());
    }

    private Environment(MinoHolder minoHolder, BlockState blockState) {
        this.minoHolder = minoHolder;
        this.blockState = blockState;
        this.placementsList = new ArrayList<>();
        this.nextBlockStateList = new ArrayList<>();
        updateNextStates();
    }

    public int stateSize() {
        return minoHolder.stateSize() * BlockState.stateSize();
    }

    public int state() {
        return minoHolder.toState() * BlockState.stateSize() + blockState.getId();
    }

    private void updateNextStates() {
        placementsList.clear();
        nextBlockStateList.clear();
        currentPlacementBound = 0;
        for (TetriminoPlacement placement : TetriminoPlacement.placementsFromTetrimino(minoHolder.getCurrentMino())) {
            BlockState next = blockState.nextState(placement);
            if ( next != null ) {
                placementsList.add(placement);
                nextBlockStateList.add(next);
                currentPlacementBound++;
            }
        }
        for (TetriminoPlacement placement : TetriminoPlacement.placementsFromTetrimino(minoHolder.getHoldMino())) {
            BlockState next = blockState.nextState(placement);
            if ( next != null ) {
                placementsList.add(placement);
                nextBlockStateList.add(next);
            }
        }
    }

    public double action(int actionIndex) {
        boolean useCurrent = actionIndex < currentPlacementBound;
        Tetrimino mino = minoHolder.consume(useCurrent);
        blockState = nextBlockStateList.get(actionIndex);
        updateNextStates();
        return isFinalState() ? -1 : 1;
    }

    public int actionSize() {
        return placementsList.size();
    }

    public boolean isFinalState() {
        return nextBlockStateList.isEmpty();
    }

    public double[][] getInitializedQList() {
        double[][] qList = new double[stateSize()][];
        MinoHolder[] allMinoHolders = MinoHolder.allStates(minoHolder.getNextMinos().length);
        BlockState[] allBlockStates = BlockState.values();
        for (int state = 0; state < stateSize(); state++) {
            int minoHolderState = state / BlockState.stateSize();
            int blockState = state % BlockState.stateSize();
            MinoHolder sampleMinoHolder = allMinoHolders[minoHolderState];
            BlockState sampleBlockState = allBlockStates[blockState];
            Environment sampleEnvironment = new Environment(sampleMinoHolder, sampleBlockState);
            int actionLength = sampleEnvironment.nextBlockStateList.size();
            qList[state] = new double[actionLength];
        }
        return qList;
    }

    public BlockState getBlockState() {
        return blockState;
    }

    @Override
    public String toString() {
        return "Environment state\n" +
            minoHolder + "\n" +
            blockState.toBlockAscii();
    }
}
