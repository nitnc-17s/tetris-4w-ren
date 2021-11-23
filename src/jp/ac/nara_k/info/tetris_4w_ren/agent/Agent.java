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

    public int run() {
        doCycle();
        return -1;
    }

    public void learn(long printCycle, long printTimes, int sampleTimes) {
        int sum = 0;
        for (int i = 0; i < sampleTimes; i++) {
            sum += run();
            initEnvironment();
        }
        double avg = (double) sum / sampleTimes;
        System.out.println("index\tREN");
        System.out.println("0\t" + avg);

        for (long count = 1; count <= printTimes; count++) {
            for (long cycle = 1; cycle <= printCycle; cycle++) {
                doCycle();
                initEnvironment();
            }

            sum = 0;
            for (int i = 0; i < sampleTimes; i++) {
                sum += run();
                initEnvironment();
            }
            avg = (double) sum / sampleTimes;
            System.out.println(count * printCycle + "\t" + avg);
        }
    }

    public abstract void doCycle();
    abstract int selectAction(int state);
}
