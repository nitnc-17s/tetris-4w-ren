package jp.ac.nara_k.info.tetris_4w_ren.agent;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.ArrayList;
import java.util.List;

public class SimpleProfitSharingAgent extends Agent {

    /**
     * Temperature for SoftmaxSelection
     */
    private double temperature = 1.0;
    /**
     * Random Probability, epsilon, for EpsilonGreedySelection
     */
    private double epsilon = 0.5;
    private double cBid = 0.2;
    private final Queue<StateActionTuple> rules = new ArrayDeque<>();
    private int rulesEffectLength = 2;

    private final List<Integer> renResults = new ArrayList<>();

    public SimpleProfitSharingAgent(int nextSize) {
        super(nextSize);
    }

    public SimpleProfitSharingAgent(int nextSize, long seed) {
        super(nextSize, seed);
    }

    public SimpleProfitSharingAgent(int nexstSize, double epsilon, long seed) {
        super(nexstSize, seed);
        this.epsilon = epsilon;
    }

    @Override
    public void doCycle() {
        while (!environment.isFinalState()) {
            int state = getState();
            int action = selectAction(state);
            rules.add(new StateActionTuple(state, action));
            if (rules.size() > rulesEffectLength) {
                rules.poll();
            }
            double reward = environment.action(action);
            if (reward < 0) {
                for (StateActionTuple stateActionTuple : rules) {
                    int s = stateActionTuple.state();
                    int a = stateActionTuple.action();
                    qTable[s][a] = qTable[s][a] + cBid * ( reward - qTable[s][a] );
                }
                rules.clear();
            }
        }
    }

    @Override
    public int run() {
        double tmpTemperature = getTemperature();
        setTemperature(5e-3);
        double tmpEpsilon = getEpsilon();
        setEpsilon(0.0);

        int ren = -1;
        while (!environment.isFinalState()) {
            // System.err.println(environment);
            ren++;
            int state = getState();
            int action = selectAction(state);
            environment.action(action);
        }
        renResults.add(ren);

        setEpsilon(tmpEpsilon);
        setTemperature(tmpTemperature);

        return ren;
    }

    public void setCBid(double cBid) {
        this.cBid = cBid;
    }

    public double getCBid() {
        return cBid;
    }

    public void setRulesEffectLength(int rulesEffectLength) {
        this.rulesEffectLength = rulesEffectLength;
    }

    public void setTemperature(double temperature) {
        if (temperature == 0) {
            throw new ArithmeticException("Absolute 0 temperature will invoke div by zero.");
        }
        this.temperature = temperature;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getEpsilon() {
        return epsilon;
    }

    public void setEpsilon(double epsilon) {
        this.epsilon = epsilon;
    }

    public void clearRenResults() {
        renResults.clear();
    }

    public List<Integer> getRenResults() {
        return renResults;
    }

    @Override
    int selectAction(int state) {
        return epsilonGreedySelectAction(state);
        // return softmaxSelectAction(state);
    }

    private int epsilonGreedySelectAction(int state) {
        double selection = randomGenerator.nextDouble();
        if (selection > epsilon) {
            return greedySelectAction(state);
        } else {
            int actionLength = qTable[state].length;
            return randomGenerator.nextInt(actionLength);
        }
    }

    private int greedySelectAction(int state) {
        int actionLength = qTable[state].length;
        if (actionLength == 0) return -1;
        int maxIndex = 0;
        double maxQValue = qTable[state][0];
        for (int i = 1; i < actionLength; i++) {
            if (qTable[state][i] > maxQValue) {
                maxIndex = i;
                maxQValue = qTable[state][i];
            }
        }
        return maxIndex;
    }

    private int softmaxSelectAction(int state) {
        int actionLength = qTable[state].length;
        double[] softmaxAccumulations = new double[actionLength+1];
        for (int i = 0; i < actionLength; i++) {
            softmaxAccumulations[i+1] = softmaxAccumulations[i] + Math.exp(qTable[state][i] / temperature);
        }
        double selection = randomGenerator.nextDouble() * softmaxAccumulations[actionLength];
        for (int i = 0; i < actionLength; i++) {
            if (selection < softmaxAccumulations[i+1]) return i;
        }
        return actionLength - 1;
    }
}

record StateActionTuple(int state, int action) {}
