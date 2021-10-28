import tet4wren.environment.*;

import java.util.ArrayList;

public class EnvironmentTest {
    public static void main(String[] args) {
        for (long seed = 0; seed < 1024; seed++) {
            SevenBag sevenBag = new SevenBag(seed);
            for (int cycle = 0; cycle < 256; cycle++) {
                ArrayList<Tetrimino> tetriminos = new ArrayList<>();
                for (int i = 0; i < 7; i++) {
                    Tetrimino next = sevenBag.next();
                    assert !tetriminos.contains(next);
                    tetriminos.add(next);
                }
                tetriminos.clear();
            }
        }
    }
}
