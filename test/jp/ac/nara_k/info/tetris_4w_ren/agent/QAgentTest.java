package jp.ac.nara_k.info.tetris_4w_ren.agent;

import org.junit.jupiter.api.Test;

public class QAgentTest {
    @Test
    public void run() {
        Agent agent = new QAgent(2, 0.8, 0.2, 1e-4, 0xf948b319a72a4ae8L);
        agent.run();
    }

    @Test
    public void learn() {
        Agent agent = new QAgent(2, 0.8, 0.2, 1e-4, 0xf948b319a72a4ae8L);
        agent.learn(10000, 100, 1);
    }
}
