package jp.ac.nara_k.info.tetris_4w_ren.agent;

import jp.ac.nara_k.info.tetris_4w_ren.environment.Environment;

import java.util.Random;

public abstract class Agent {
    // param
    int nextSize;

    Random randomGenerator;
    Environment environment;
    double[][] qTable;

    public Agent(int nextSize) {
        this(nextSize, new Random().nextLong());
    }

    public Agent(int nextSize, long seed) {
        this.nextSize = nextSize;

        randomGenerator = new Random(seed);
        initEnvironment();
        qTable = environment.getInitializedQList();
    }

    private void initEnvironment() {
        this.environment = new Environment(nextSize, randomGenerator.nextLong());
    }

    int getState() {
        return environment.state();
    }

    public void run() {
        doCycle();
        System.out.println(environment);
    }

    public void learn(long maxCycle) {
        for (long cycle = 1; cycle<= maxCycle; cycle++) {
            doCycle();
            initEnvironment();
        }
    }

    public abstract void doCycle();
    abstract int selectAction(int state);
}
