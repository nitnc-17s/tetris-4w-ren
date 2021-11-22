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
    void callToString() {
        System.out.println(new Environment(0, 0xda0bea0232bb06ebL));
        System.out.println();
        System.out.println(new Environment(4, 0xda0bea0232bb06ebL));
    }

    @Test
    void state() {
    }

    @Test
    void action() {
    }
}
