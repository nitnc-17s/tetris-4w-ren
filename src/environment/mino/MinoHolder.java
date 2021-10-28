package environment.mino;

import java.util.Arrays;

public class MinoHolder {
    private final Generator minoGenerator;
    // minoBuffer[0] is HoldMino
    // minoBuffer[1] is CurrentMino
    private final Mino[] minoBuffer;
    public MinoHolder(int nextNumber, Generator minoGenerator) {
        this.minoGenerator = minoGenerator;
        minoBuffer = new Mino[nextNumber+2];
        for (int i = 0; i < minoBuffer.length; i++) {
            minoBuffer[i] = minoGenerator.next();
        }
    }

    public Mino consume(boolean consumeCurrent) {
        if (consumeCurrent) {
            Mino tmp = minoBuffer[0];
            minoBuffer[0] = minoBuffer[1];
            minoBuffer[1] = tmp;
        }
        Mino ret = minoBuffer[1];
        System.arraycopy(minoBuffer, 2, minoBuffer, 1, minoBuffer.length - 2);
        minoBuffer[minoBuffer.length-1] = minoGenerator.next();
        return ret;
    }

    public Mino[] getNextMinos() {
        return Arrays.copyOfRange(minoBuffer, 2, minoBuffer.length);
    }

    @Override
    public String toString() {
        return "MinoHolder{" +
                "Hold=" + minoBuffer[0] +
                ", Current=" + minoBuffer[1] +
                ", Next=" + Arrays.toString(getNextMinos()) +
                ", minoGenerator=" + minoGenerator +
                '}';
    }
}
