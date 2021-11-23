package jp.ac.nara_k.info.tetris_4w_ren.agent;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class MonteCarloAgentTest {
    ArrayList<Integer> ranRen = new ArrayList<>();
    ArrayList<Integer> aftRen = new ArrayList<>();
    @Test
    public void run() {
        MonteCarloAgent agent = new MonteCarloAgent(3);
        agent.doCycleRand();
        this.ranRen.add(agent.episodeCount-1);
    }

    @Test
    public void learning() {
        for (int i = 0; i < 100; i++) {
            this.run();
        }
        int ranSum = 0;
        for (int i = 0; i < 100; i++) {
            ranSum += this.ranRen.get(i);
        }
        System.out.println((double) ranSum/100);


        MonteCarloAgent agent = new MonteCarloAgent(3);
        agent.learn(10000, 100);


        for (int i = 0; i < 100; i++) {
            this.runTest();
        }
        int aftSum = 0;
        for (int i = 0; i < 100; i++) {
            aftSum += this.aftRen.get(i);
        }
        System.out.println((double) aftSum/100);
    }

    @Test
    public void runTest(){
        MonteCarloAgent agent = new MonteCarloAgent(3);
        agent.doCycleTest();
        this.aftRen.add(agent.episodeCount-1);
    }
}
