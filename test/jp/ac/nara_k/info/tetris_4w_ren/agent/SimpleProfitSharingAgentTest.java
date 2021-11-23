package jp.ac.nara_k.info.tetris_4w_ren.agent;

import org.junit.jupiter.api.Test;

import java.util.List;

class SimpleProfitSharingAgentTest {

    @Test
    void learn() {
        SimpleProfitSharingAgent agent = new SimpleProfitSharingAgent(3, 0.5, 0xf948b319a72a4ae8L);

        // random run
        agent.setCBid(0.0);
        agent.setRecordRenResultsFlag(true);
        agent.run();
        System.err.println(average(agent.getRenResults()));
        System.err.println(max(agent.getRenResults()));
        agent.clearRenResults();

        // lets learn
        agent.setCBid(0.01);
        agent.setTemperature(1);
        agent.setRulesEffectLength(3);
        agent.setRecordRenResultsFlag(false);
        agent.learn(1_000_000);

        // final learning result
        agent.setCBid(0);
        agent.setTemperature(5e-3);
        agent.setRecordRenResultsFlag(true);
        agent.run();
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