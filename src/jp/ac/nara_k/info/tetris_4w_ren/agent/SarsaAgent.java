package jp.ac.nara_k.info.tetris_4w_ren.agent;

import java.util.Random;

public class SarsaAgent extends Agent {
    private final double learningRate; //α
    private final double discountFactor; //γ
    private final double epsilon; //ε

    public SarsaAgent(int nextSize, double learningRate, double discountFactor, double epsilon) {
        this(nextSize, learningRate, discountFactor, epsilon, new Random().nextLong());
    }

    public SarsaAgent(int nextSize, double learningRate, double discountFactor, double epsilon, long seed) {
        super(nextSize, seed);
        this.learningRate = learningRate;
        this.discountFactor = discountFactor;
        this.epsilon = epsilon;
    }

    @Override
    public void doCycle() {
        int s = getState();
        int a = selectAction(s);

        while (!environment.isFinalState()) {
            double r = environment.action(a);

            int nextS = getState();
            int nextA = selectAction(nextS);

            qTable[s][a] = qTable[s][a] + learningRate * (r + discountFactor * qTable[nextS][nextA] - qTable[s][a]);

            s = nextS;
            a = nextA;
        }
    }

    @Override
    int selectAction(int state) {
        int action = -1;

        double rand = randomGenerator.nextDouble();

        if (rand > epsilon) { // greedy
            double maxR = Double.MIN_VALUE;
            for (int i=0; i<qTable[state].length; i++) {
                if (qTable[state][i] > maxR) {
                    action = i;
                }
            }
        } else { // random
            action = randomGenerator.nextInt(qTable[state].length);
        }

        return action;
    }
}
