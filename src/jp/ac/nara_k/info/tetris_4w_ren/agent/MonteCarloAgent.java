package jp.ac.nara_k.info.tetris_4w_ren.agent;

import jp.ac.nara_k.info.tetris_4w_ren.environment.Environment;

import java.util.ArrayList;

public class MonteCarloAgent extends Agent{
    ArrayList<Integer> actions;
    ArrayList<Integer> states;
    int episodeCount;

    public MonteCarloAgent(int nextSize) {
        super(nextSize);
        episodeCount = 0;
    }

    @Override
    int selectAction(int state){
        int N = qTable[state].length;
        int act = this.randomGenerator.nextInt(N);
        actions.add(act);
        states.add(state);
        return act;
    }

    @Override
    public void doCycle() {
        Environment env = new Environment(this.nextSize);
        while(!env.isFinalState()){
            int act = selectAction(getState());
            env.action(act);
            this.episodeCount++;
        }

        for(int i = 0;i<=10;i++) {
            int N = episodeCount - i;
            this.qTable[states.get(N)][actions.get(N)] -= 10 - i;
            this.episodeCount = 0;
        }
    }
}
