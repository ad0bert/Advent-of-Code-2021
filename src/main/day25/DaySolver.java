package main.day25;

import main.AbstractSolver;
import utils.AoCFileReader;

import java.io.File;
import java.util.List;

public class DaySolver extends AbstractSolver {

    public DaySolver(String day) {
        super(day);
    }

    @Override
    public void solvePart1() {
        List<List<String>> input = AoCFileReader.readListOfCharList(new File(this.inputFile1));
        System.out.println();
    }

    @Override
    public void solvePart2() {
        List<List<String>> input = AoCFileReader.readListOfCharList(new File(this.inputFile2));
        System.out.println();
    }

}
