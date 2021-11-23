package jp.ac.nara_k.info.tetris_4w_ren.agent;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class MonteCarloAgentTest {
    @Test
    public void run() {
        Agent agent = new MonteCarloAgent(2, 0xf948b319a72a4ae8L);
        agent.run();
    }

    @Test
    public void learn() {
        Agent agent = new MonteCarloAgent(2, 0xf948b319a72a4ae8L);
        agent.learn(10000, 100);
    }

    /*
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
            agent.doCycleTest();
        }
        int aftSum = 0;
        for (int i = 0; i < 100; i++) {
            aftSum += this.aftRen.get(i);
        }
        System.out.println((double) aftSum/100);
    }
    */
}
