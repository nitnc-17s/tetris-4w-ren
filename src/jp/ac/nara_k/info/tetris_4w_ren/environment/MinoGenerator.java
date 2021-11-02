package jp.ac.nara_k.info.tetris_4w_ren.environment;

public abstract class MinoGenerator {
    /**
     * 破壊的な命令です。
     * 次のミノを出力します。
     * @return 未知である次のミノ
     */
    abstract public Mino next();
}
