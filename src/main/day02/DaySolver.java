package main.day02;

import lombok.val;
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
    val input = AoCFileReader.readMultipleLines(new File(this.inputFile1));
  }

  @Override
  public void solvePart2() {
    val input = AoCFileReader.readMultipleLines(new File(this.inputFile2));
  }

}
