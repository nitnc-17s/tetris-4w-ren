package jp.ac.nara_k.info.tetris_4w_ren.agent;

import jp.ac.nara_k.info.tetris_4w_ren.environment.Environment;

import java.util.Random;

public abstract class Agent {
    // param
    int maxCycle;
    int nextSize;

    Random randomGenerator;
    Environment environment;
    double[][] qTable;

    public Agent(int maxCycle, int nextSize) {
        this(maxCycle, nextSize, new Random().nextLong());
    }

    public Agent(int maxCycle, int nextSize, long seed) {
        this.maxCycle = maxCycle;
        this.nextSize = nextSize;

        this.randomGenerator = new Random(seed);
        this.environment = new Environment(nextSize, seed);
        this.qTable = environment.getInitializedQList();
    }

    int getState() {
        return environment.state();
    }

    public void run() {
        for (int cycle = 1; cycle<= maxCycle; cycle++) {
            doCycle();
        }
    }

    public abstract void doCycle();
    abstract int selectAction(int state);
}
