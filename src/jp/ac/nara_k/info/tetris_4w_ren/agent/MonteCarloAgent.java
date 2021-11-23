package jp.ac.nara_k.info.tetris_4w_ren.agent;

import jp.ac.nara_k.info.tetris_4w_ren.environment.Environment;

import java.util.ArrayList;

import  java.lang.Integer;

public class MonteCarloAgent extends Agent{
    ArrayList<Integer> actions = new ArrayList<>();
    ArrayList<Integer> states = new ArrayList<>();
    int episodeCount;
    ArrayList<Integer> ren = new ArrayList<>();



    public ArrayList<Integer> getRen() {
        return ren;
    }

    public void setRen(ArrayList<Integer> ren) {
        this.ren = ren;
    }



    public MonteCarloAgent(int nextSize) {
        super(nextSize);
        episodeCount = 0;
    }

    int selectAction(int state){
        int N = qTable[state].length;
        int act = this.randomGenerator.nextInt(N);
        actions.add(act);
        states.add(state);
        return act;
    }

    public void doCycle() {
        while(!environment.isFinalState()){
            int act = selectAction(getState());
            environment.action(act);
            this.episodeCount++;
        }

        int N =episodeCount;

        for(int i = 0;i<N;i++) {
            if(N - i < 3 ) {
                this.qTable[states.get(i)][actions.get(i)] -= 2;
            }else {
                this.qTable[states.get(i)][actions.get(i)] += 1;
            }
            ren.add(N);
            this.episodeCount = 0;

        }
    }
    public void doCycleRand() {
        while(!environment.isFinalState()){
            int act = selectAction(getState());
            environment.action(act);
            this.episodeCount++;
        }
    }

    public void doCycleTest(){
        while(!environment.isFinalState()){
            int act = selectActionTest(getState());
            environment.action(act);
            this.episodeCount++;
        }

    }



    public int selectActionTest(int state){
        int N = qTable[state].length;
        int act=0;
        for (int i=0;i<N;i++){
            for (int j=i;j<N;j++){
                if(this.qTable[state][j] > this.qTable[state][act]){
                    act=j;
                }
            }
        }
        return act;
    }
}