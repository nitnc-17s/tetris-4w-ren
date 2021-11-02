package jp.ac.nara_k.info.tetris_4w_ren.environment;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

class EnvironmentTest {

    @Test
    void getInitializedQList() {
        Environment  environment;
        environment = new Environment(0);
        double[][] qList = environment.getInitializedQList().clone();
    }

    @Test
    void stateSize() {
        Environment  environment;
        environment = new Environment(0);
        System.out.println(environment.stateSize());
        environment = new Environment(1);
        System.out.println(environment.stateSize());
        environment = new Environment(2);
        System.out.println(environment.stateSize());
        environment = new Environment(3);
        System.out.println(environment.stateSize());
    }

    @Test
    void state() {
    }

    @Test
    void action() {
    }

    @Test
    void qLearning() {
        final Random random = new Random();
        final int MAX_CYCLE = 1000000000;
        final int nextSize = 4;
        final double alpha = 0.8;
        final double gamma = 0.2;
        Environment environment = new Environment(nextSize);
        double[][] q = environment.getInitializedQList().clone();
        int max = 0;
        double average = 0;
        for (int cycle = 0; cycle < MAX_CYCLE; cycle++) {
            int ren = -1;
            environment = new Environment(nextSize);
            while (!environment.isFinalState()) {
                int state = environment.state();
                // int action = random.nextInt(environment.actionSize());
                int action = actionSelector(q[state], .5);

                double reward = environment.action(action);
                int next_state = environment.state();
                q[state][action] = alpha * (reward + gamma
                        * Arrays.stream(q[next_state]).max().orElse(0.0)
                        - q[state][action]);
                ren++;
            }
            max = Math.max(ren, max);
            average += 1.0 * ren / MAX_CYCLE;
        }
        System.out.println(max);
        System.out.println(average);

        max = 0;
        average = 0;
        for (int cycle = 0; cycle < 10; cycle++) {
            int ren = -1;
            environment = new Environment(nextSize);
            while (!environment.isFinalState()) {
                int state = environment.state();
                int action = actionSelector(q[state], 1e-4);
                environment.action(action);
                ren++;
            }
            System.out.printf("%d REN\n", ren);
            max = Math.max(ren, max);
            average += 1.0 * ren / 10;
        }
        System.out.println(max);
        System.out.println(average);

    }

    int actionSelector(double[] q_state, double epsilon) {
        Random random = new Random();
        int bound = q_state.length;

        if (random.nextDouble() < epsilon) {
            return random.nextInt(bound);
        } else {
            int ret = 0;
            double max = q_state[0];
            for (int i = 1; i < bound; i++) {
                if (q_state[i] > max) {
                    max = q_state[i];
                    ret = i;
                }
            }
            return ret;
        }
        // double[] soft_cul = new double[bound+1];
        // for (int i = 0; i < bound; i++) {
        //     soft_cul[i+1] = soft_cul[i] + Math.exp(q_state[i]/epsilon);
        // }
        // double sel = random.nextDouble() * soft_cul[bound];
        // for (int i = 0; i < bound; i++) {
        //     if (soft_cul[i+1] > sel) return i;
        // }
        // return bound-1;
    }

}