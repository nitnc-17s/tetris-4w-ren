package jp.ac.nara_k.info.tetris_4w_ren.agent;
import jp.ac.nara_k.info.tetris_4w_ren.environment.Environment;

import java.util.Random;
public class QAgent extends Agent{
    private final double ALPHA;
    private final double GAMMA;
    private final double EPSILON;

    public QAgent(int nextSize, double alpha, double gamma, double epsilon) {
        this(nextSize, alpha, gamma, epsilon, new Random().nextLong());
    }

    public QAgent(int nextSize, double alpha, double gamma, double epsilon, long seed) {
        super(nextSize, seed);
        this.ALPHA = alpha;
        this.GAMMA = gamma;
        this.EPSILON = epsilon;
    }

    @Override
    public int run(){
        int ren = -1;
        while(!environment.isFinalState()){
            ren++;
            int state = getState();
            int action = getMaxIndex(qTable[state]);
            environment.action(action);
        }
        return ren;
    }

    @Override
    public void doCycle(){
        int ren = -1;
        while(!environment.isFinalState()){
            ren++;
            int state = getState();
            int action = this.selectAction(state);
            double r = environment.action(action);
            int newState = getState();
            qTable[state][action] = qTable[state][action] + ALPHA * (r + GAMMA * getMax(qTable[newState]) - qTable[state][action]);
        }
        //System.out.println(ren);
    }

    @Override
    int selectAction(int state){
        Random rand = new Random();
        int actionLength = this.qTable[state].length;
        if(rand.nextDouble() < this.EPSILON){ //ランダム
            return rand.nextInt(qTable[state].length);
        }else{ //greedy
            return getMaxIndex(qTable[state]);
        }
    }

    private double getMax(double[] arr){
        double max = 0;
        for(int i = 0; i < arr.length; i++){
            if(max < arr[i]){
                max = arr[i];
            }
        }
        return max;
    }

    private int getMaxIndex(double[] table){
        Random rand = new Random();
        int maxIndex = 0;
        int maxCount = 1;
        for(int i = 0; i < table.length; i++){
            if(table[maxIndex] < table[i]){
                maxIndex = i;
                maxCount = 1;
            }
            else if(table[maxIndex] == table[i]){
                maxCount++;
            }
        }
        if(maxCount > 1){
            return rand.nextInt(table.length);
        }
        return maxIndex;
    }

    public void check(long cycle){
        Random rand = new Random();
        long sumRen = 0;
        for(long l = 1; l <= cycle; l++){
            int ren = -1;
            while(!environment.isFinalState()){
                ren++;
                int state = getState();
                int action = getMaxIndex(this.qTable[state]);
                //int action = rand.nextInt(qTable[state].length);
                double r = environment.action(action);
            }
            this.environment = new Environment(nextSize, randomGenerator.nextLong());
            System.out.println(ren);
            sumRen += ren;
        }
        System.out.print("Result: ");
        System.out.println(sumRen / cycle);
        //System.out.println(Arrays.toString(qTable));
    }
}

