package jp.ac.nara_k.info.tetris_4w_ren;

import jp.ac.nara_k.info.tetris_4w_ren.agent.*;

public class Main {
    public static void main(String[] args) {
        long seed = 0xf948b319a72a4ae8L;
        long printCycle = 100_000;
        long printTimes = 1000;

        System.out.println("==== Next 2 ====");
        System.out.println("== Q-Learning ==");
        new QAgent(2, 0.8, 0.2, 1e-4, seed).learn(printCycle, printTimes);
        System.out.println();

        System.out.println("== Sarsa ==");
        new QAgent(2, 0.8, 0.2, 1e-4, seed).learn(printCycle, printTimes);
        System.out.println();

        System.out.println("== Monte-Carlo ==");
        new MonteCarloAgent(2, seed).learn(printCycle, printTimes);
        System.out.println();

        System.out.println("== Simple Profit Sharing ==");
        new SimpleProfitSharingAgent(2, 0.5, seed).learn(printCycle, printTimes);
    }
}
