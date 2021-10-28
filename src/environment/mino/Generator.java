package environment.mino;

public abstract class Generator {
    /**
     * 破壊的な命令です。
     * 次のミノを出力します。
     * @return 未知である次のミノ
     */
    abstract public Mino next();
}
