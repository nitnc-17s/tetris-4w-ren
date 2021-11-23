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

    public void learn(long printCycle, long printTimes) {
        System.out.println("index, REN");
        System.out.println("0, " + run() + "");

        for (long count = 1; count <= printTimes; count++) {
            for (long cycle = 1; cycle <= printCycle; cycle++) {
                doCycle();
                initEnvironment();
            }

            System.out.println(count * printCycle + ", " + run() + "");
        }
    }

    public abstract void doCycle();
    abstract int selectAction(int state);
}
