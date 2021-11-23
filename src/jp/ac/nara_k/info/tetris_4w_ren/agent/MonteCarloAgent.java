package jp.ac.nara_k.info.tetris_4w_ren.agent;

import java.util.ArrayList;

import  java.lang.Integer;

public class MonteCarloAgent extends Agent{
    ArrayList<Integer> ren = new ArrayList<>();

    public ArrayList<Integer> getRen() {
        return ren;
    }

    public void setRen(ArrayList<Integer> ren) {
        this.ren = ren;
    }

    public MonteCarloAgent(int nextSize) {
        super(nextSize);
    }

    public MonteCarloAgent(int nextSize, long seed) {
        super(nextSize, seed);
    }

    @Override
    public int run() {
        int ren = -1;

        while (!environment.isFinalState()) {
            int state = getState();
            int act = selectAction(state);
            environment.action(act);
            ren++;
        }

        return ren;
    }

    @Override
    public void doCycle() {
        ArrayList<Integer> actions = new ArrayList<>();
        ArrayList<Integer> states = new ArrayList<>();
        int episodeCount = 0;

        while (!environment.isFinalState()) {
            int state = getState();
            int act = selectRandomAction(state);

            states.add(state);
            actions.add(act);

            environment.action(act);
            episodeCount++;
        }

        for (int i = 0; i < episodeCount; i++) {
            if (episodeCount - i < 3) {
                this.qTable[states.get(i)][actions.get(i)] -= 2;
            }else {
                this.qTable[states.get(i)][actions.get(i)] += 1;
            }
            ren.add(episodeCount);
        }
    }


    int selectRandomAction(int state){
        int N = qTable[state].length;
        return this.randomGenerator.nextInt(N);
    }

    @Override
    public int selectAction(int state){
        int N = qTable[state].length;
        int act=0;
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                if (this.qTable[state][j] > this.qTable[state][act]) {
                    act=j;
                }
            }
        }
        return act;
    }
}
