package jp.ac.nara_k.info.tetris_4w_ren.agent;

import jp.ac.nara_k.info.tetris_4w_ren.environment.Environment;

import java.util.Random;

public abstract class Agent {
    // param
    int episodeCount;
    int nextSize;

    Random randomGenerator;
    Environment environment;
    double[][] qTable;

    public Agent(int episodeCount, int nextSize) {
        this(episodeCount, nextSize, new Random().nextLong());
    }

    public Agent(int episodeCount, int nextSize, long seed) {
        this.episodeCount = episodeCount;
        this.nextSize = nextSize;

        this.randomGenerator = new Random(seed);
        this.environment = new Environment(nextSize, seed);
        this.qTable = environment.getInitializedQList();
    }

    int getState() {
        return environment.state();
    }

    public abstract void run();
    abstract int selectAction(double[][] QTable, int state);
}
