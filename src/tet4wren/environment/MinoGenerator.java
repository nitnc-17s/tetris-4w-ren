package tet4wren.environment;

public abstract class MinoGenerator {
    /**
     * 破壊的な命令です。
     * 次のミノを出力します。
     * @return 未知である次のミノ
     */
    abstract public Mino next();
}
