package tet4wren.environment;

import java.util.Random;

public class SevenBag extends Generator {
    private final Random random;
    private int displacement;
    private final Tetrimino[] buffer = new Tetrimino[7];

    public SevenBag(long seed) {
        displacement = 0;
        random = new Random(seed);
        gen_bag();
    }

    @Override
    public Tetrimino next() {
        Tetrimino next_mino = buffer[displacement++];
        if ( displacement >= 7 ) {
            displacement = 0;
            gen_bag();
        }
        return next_mino;
    }

    /**
     * 後続のバッグを生成します。もともとの後続のバッグは破壊されます。
     */
    private void gen_bag() {
        Tetrimino[] next_bag = {
                Tetrimino.I, Tetrimino.O, Tetrimino.T, Tetrimino.L, Tetrimino.J, Tetrimino.S, Tetrimino.Z,
        };
        // Fisher-Yates Shuffle を使用して、バッグ内テトリミノ順列をシャッフル
        for ( int i = next_bag.length - 1; i > 0; i-- ) {
            int index = random.nextInt(i+1);
            Tetrimino tmp = next_bag[index];
            next_bag[index] = next_bag[i];
            next_bag[i] = tmp;
        }
        System.arraycopy(next_bag, 0, buffer, 0, 7);
    }

    @Override
    public String toString() {
        return "SevenBag";
    }
}
