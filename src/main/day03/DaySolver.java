package main.day03;

import lombok.val;
import main.AbstractSolver;
import utils.AoCFileReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DaySolver extends AbstractSolver {

  public DaySolver(String day) {
    super(day);
  }

  @Override
  public void solvePart1() {
    val input = AoCFileReader.readStringLineByLine(new File(this.inputFile1));
    int inputSizeHalf = input.size() / 2;
    int bits = input.get(0).length();
    val gamma = new StringBuilder();
    val power = new StringBuilder();
    for (int i = 0; i < bits; i++) {
      int valueCnt = 0;
      for (String s : input) {
        valueCnt += Integer.parseInt(String.valueOf(s.toCharArray()[i]));
      }
      gamma.append(valueCnt > inputSizeHalf ? "1" : "0");
      power.append(valueCnt > inputSizeHalf ? "0" : "1");
    }
    System.out.println(Integer.parseInt(power.toString(), 2) * Integer.parseInt(gamma.toString(), 2));
  }

  @Override
  public void solvePart2() {
    val input = AoCFileReader.readStringLineByLine(new File(this.inputFile2));
    var oxygenList = new ArrayList<>(input);
    var coTwoList = new ArrayList<>(input);
    int bits = input.get(0).length();
    while (oxygenList.size() > 1 && coTwoList.size() > 1) {
      for (int i = 0; i < bits; i++) {
        String oxygenBit = getMostCommonBit(oxygenList, i);
        String coTwoBit = getLeastCommonBit(coTwoList, i);
        oxygenList = (ArrayList<String>) reduceList(oxygenList, i, oxygenBit);
        coTwoList = (ArrayList<String>) reduceList(coTwoList, i, coTwoBit);
      }
    }
    System.out.println(Integer.parseInt(oxygenList.get(0), 2) * Integer.parseInt(coTwoList.get(0), 2));
  }

  private String getMostCommonBit(List<String> input, int position) {
    int zeroes = 0;
    int ones = 0;
    for (String s : input) {
      int pos = Integer.parseInt(String.valueOf(s.toCharArray()[position]));
      if (pos == 1) ones++;
      if (pos == 0) zeroes++;
    }
    return ones >= zeroes ? "1" : "0";
  }

  private String getLeastCommonBit(List<String> input, int position) {
    int zeroes = 0;
    int ones = 0;
    for (String s : input) {
      char pos = s.toCharArray()[position];
      if (pos == 1) ones++;
      if (pos == 0) zeroes++;
    }
    return zeroes > ones ? "1" : "0";
  }

  private List<String> reduceList(List<String> input, int position, String bitToCheck) {
    if (input.size() == 1) return input;
    return input.stream()
        .filter(entry -> String.valueOf(entry.toCharArray()[position]).equals(bitToCheck))
        .collect(Collectors.toList());
  }

}
