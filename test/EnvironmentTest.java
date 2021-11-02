import jp.ac.nara_k.info.tetris_4w_ren.environment.*;

import java.util.ArrayList;

public class EnvironmentTest {
    public static void main(String[] args) {
        for (long seed = 0; seed < 1024; seed++) {
            SevenBag sevenBag = new SevenBag(seed);
            for (int cycle = 0; cycle < 256; cycle++) {
                ArrayList<Tetrimino> tetriminos = new ArrayList<>();
                for (int i = 0; i < 7; i++) {
                    Tetrimino next = sevenBag.next();
                    assert !tetriminos.contains(next);
                    assert next != null;
                    tetriminos.add(next);
                }
                tetriminos.clear();
            }
        }
        testMinoHolder();
    }
    public static void testMinoHolder() {
        MinoHolder minoHolder = new MinoHolder(7, new SevenBag(0));
        for ( int i = 0; i < 100; i++ ) {
            System.out.print(minoHolder);
            Mino consume = minoHolder.consume(i%5!=1);
            System.out.println(" => Use: " + consume);
        }
    }
}
