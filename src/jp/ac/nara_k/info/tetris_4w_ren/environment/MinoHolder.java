package jp.ac.nara_k.info.tetris_4w_ren.environment;

import java.util.Arrays;

public class MinoHolder {
    private final MinoGenerator minoGenerator;
    // minoBuffer[0] is HoldMino
    // minoBuffer[1] is CurrentMino
    private final Mino[] minoBuffer;
    public MinoHolder(int nextNumber, MinoGenerator minoGenerator) {
        this.minoGenerator = minoGenerator;
        minoBuffer = new Mino[nextNumber+2];
        for (int i = 0; i < minoBuffer.length; i++) {
            minoBuffer[i] = minoGenerator.next();
        }
    }

    public Mino consume(boolean consumeCurrent) {
        if (!consumeCurrent) {
            Mino tmp = minoBuffer[0];
            minoBuffer[0] = minoBuffer[1];
            minoBuffer[1] = tmp;
        }
        Mino ret = minoBuffer[1];
        System.arraycopy(minoBuffer, 2, minoBuffer, 1, minoBuffer.length - 2);
        minoBuffer[minoBuffer.length-1] = minoGenerator.next();
        return ret;
    }

    public Mino getHoldMino() {
        return minoBuffer[0];
    }

    public Mino getCurrentMino() {
        return minoBuffer[1];
    }

    public Mino[] getNextMinos() {
        return Arrays.copyOfRange(minoBuffer, 2, minoBuffer.length);
    }

    @Override
    public String toString() {
        return "MinoHolder{" +
                "Hold=" + getHoldMino() +
                ", Current=" + getCurrentMino() +
                ", Next=" + Arrays.toString(getNextMinos()) +
                ", minoGenerator=" + minoGenerator +
                '}';
    }

    public int stateSize() {
        int base = 1;
        for (Mino ignored : minoBuffer) {
            base *= Tetrimino.size();
        }
        return base;
    }

    public int toState() {
        int index = 0;
        int base = 1;
        for (Mino mino : minoBuffer) {
            if (!(mino instanceof Tetrimino)) {
                return -1;
            }
            index += base * mino.getId();
            base *= Tetrimino.size();
        }
        return index;
    }

    public static MinoHolder[] allStates(int nextNumber) {
        // nextが全部 同じ種類とかになる パターンが出るけど 知ったこっちゃない
        MinoHolder sampleMinoHolder = new MinoHolder(nextNumber, new SevenBag(0));
        int length = sampleMinoHolder.stateSize();
        MinoHolder[] allStates = new MinoHolder[length];
        for (int i = 0; i < length; i++) {
            final MinoHolder minoHolder = new MinoHolder(nextNumber, new SevenBag(0));
            int tetriminoIndex = i;
            for (int j = 0; j < minoHolder.minoBuffer.length; j++) {
                minoHolder.minoBuffer[j] = Tetrimino.values()[tetriminoIndex%Tetrimino.size()];
                tetriminoIndex /= Tetrimino.size();
            }
            allStates[i] = minoHolder;
        }
        return allStates;
    }

}
