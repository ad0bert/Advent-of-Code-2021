package main.day01;

import lombok.val;
import main.AbstractSolver;
import utils.AoCFileReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DaySolver extends AbstractSolver {

  public DaySolver(String day) {
    super(day);
  }

  @Override
  public void solvePart1() {
    val input = AoCFileReader.readIntegerLineByLine(new File(this.inputFile1));
    System.out.println(getIncreaseCnt(input));
  }

  @Override
  public void solvePart2() {
    val input = AoCFileReader.readIntegerLineByLine(new File(this.inputFile2));
    val windows = new ArrayList<Integer>();
    for (int i = 0; i < input.size() - 2; i++) {
      windows.add(input.get(i) + input.get(i + 1) + input.get(i + 2));
    }
    System.out.println(getIncreaseCnt(windows));
  }

  private int getIncreaseCnt(List<Integer> toCheck) {
    int incCnt = 0;
    for (int i = 1; i < toCheck.size(); i++) {
      if (toCheck.get(i) > toCheck.get(i - 1)) {
        incCnt++;
      }
    }
    return incCnt;
  }

}
