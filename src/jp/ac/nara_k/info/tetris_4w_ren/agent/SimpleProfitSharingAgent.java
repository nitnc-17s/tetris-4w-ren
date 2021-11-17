package jp.ac.nara_k.info.tetris_4w_ren.agent;

import jp.ac.nara_k.info.tetris_4w_ren.environment.Environment;

import java.util.ArrayDeque;
import java.util.Queue;

public class SimpleProfitSharingAgent extends Agent {

    private double cBid;
    /**
     * Temperature for SoftmaxSelection
     */
    private double temperature;
    private final Queue<StateActionTuple> rules;

    public SimpleProfitSharingAgent(int nextSize) {
        super(nextSize);
        this.cBid = 0.2;
        this.temperature = 1.0;
        this.rules = new ArrayDeque<>();
    }

    @Override
    public void doCycle() {
        this.environment = new Environment(this.nextSize);
        while (!environment.isFinalState()) {
            int state = this.environment.state();
            int action = this.selectAction(state);
            this.rules.add(new StateActionTuple(state, action));
            if (rules.size() > 6) {
                rules.poll();
            }
            double reward = this.environment.action(action);
            if (reward < 0) {
                for( StateActionTuple stateActionTuple : this.rules) {
                    int s = stateActionTuple.state();
                    int a = stateActionTuple.action();
                    this.qTable[s][a] = this.qTable[s][a] + this.cBid * ( reward - this.qTable[s][a] );
                }
                this.rules.clear();
            }
        }
    }

    public void setCBid(double cBid) {
        this.cBid = cBid;
    }

    public double getCBid() {
        return cBid;
    }

    public void setTemperature(double temperature) {
        if (temperature == 0) {
            throw new ArithmeticException();
        }
        this.temperature = temperature;
    }

    public double getTemperature() {
        return temperature;
    }

    @Override
    int selectAction(int state) {
        return softmaxSelectAction(state);
    }

    private int softmaxSelectAction(int state) {
        int actionLength = this.qTable[state].length;
        double[] softmaxAccumulations = new double[actionLength+1];
        for (int i = 0; i < actionLength; i++) {
            softmaxAccumulations[i+1] = softmaxAccumulations[i] + Math.exp(qTable[state][i] / this.temperature);
        }
        double selection = this.randomGenerator.nextDouble() * softmaxAccumulations[actionLength];
        for (int i = 0; i < actionLength; i++) {
            if (selection < softmaxAccumulations[i+1]) return i;
        }
        return actionLength;
    }
}

record StateActionTuple(int state, int action) {}

