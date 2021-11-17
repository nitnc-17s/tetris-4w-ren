package jp.ac.nara_k.info.tetris_4w_ren.agent;

import org.junit.jupiter.api.Test;

import java.util.List;

class SimpleProfitSharingAgentTest {

    @Test
    void learn() {
        SimpleProfitSharingAgent agent = new SimpleProfitSharingAgent(2);
        agent.setCBid(0.0);
        agent.setTemperature(1);
        agent.learn(1000);
        agent.setCBid(0.1);
        System.err.println(average(agent.getRenResults()));
        System.err.println(max(agent.getRenResults()));
        agent.learn(1000000);

        agent.clearRenResults();
        for (int i = 0; i < 1000; i++) agent.run();
        agent.setTemperature(1e-5);
        System.err.println(average(agent.getRenResults()));
        System.err.println(max(agent.getRenResults()));
    }

    double average(List<Integer> results) {
        if (results.isEmpty()) {
            return 0;
        } else {
            return 1.0 * results.stream().reduce(Integer::sum).get()/results.size();
        }
    }

    int max(List<Integer> results) {
        if (results.isEmpty()) {
            return 0;
        } else {
            return results.stream().max(Integer::compare).get();
        }
    }
}