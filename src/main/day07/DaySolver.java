package main.day07;

import lombok.val;
import main.AbstractSolver;
import utils.AoCFileReader;

import java.io.File;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;

public class DaySolver extends AbstractSolver {

  public DaySolver(String day) {
    super(day);
  }

  @Override
  public void solvePart1() {
    val input = AoCFileReader.readIntegerLine(new File(this.inputFile1));
    input.sort(Integer::compareTo);

    var min = input.stream().mapToInt(Integer::intValue).min().orElse(-1);
    var max = input.stream().mapToInt(Integer::intValue).max().orElse(-1);
    int res = 0;
    do {
      val finalMin = min;
      val finalMax = max;
      val minEntries = input.stream().filter(entry -> entry.equals(finalMin)).toList().size();
      val maxEntries = input.stream().filter(entry -> entry.equals(finalMax)).toList().size();
      val toCheck = minEntries < maxEntries ? min : max;
      val toModify = minEntries < maxEntries ? 1 : -1;
      if (minEntries < maxEntries) input.sort(Integer::compareTo);
      else input.sort(Collections.reverseOrder());
      for (int i = 0; i < input.size(); i++) {
        if (input.get(i) == toCheck) {
          input.set(i, input.get(i) + toModify);
          res++;
        } else break;
      }
      min = input.stream().mapToInt(Integer::intValue).min().orElse(-1);
      max = input.stream().mapToInt(Integer::intValue).max().orElse(-1);
    } while (min != max);

    System.out.println(res);
  }

  @Override
  public void solvePart2() {
    val input1 = AoCFileReader.readIntegerLine(new File(this.inputFile2));
    val input2 = input1.stream().map(entry -> new AbstractMap.SimpleEntry<>(entry, 1)).toList();
    var input = new ArrayList<>(input2);
    input.sort(java.util.Map.Entry.comparingByKey());

    var min = input.stream().mapToInt(AbstractMap.SimpleEntry::getKey).min().orElse(-1);
    var max = input.stream().mapToInt(AbstractMap.SimpleEntry::getKey).max().orElse(-1);
    do {
      val finalMin = min;
      val finalMax = max;
      val minEntries = input.stream().filter(entry -> entry.getKey().equals(finalMin)).mapToInt(AbstractMap.SimpleEntry::getValue).sum();
      val maxEntries = input.stream().filter(entry -> entry.getKey().equals(finalMax)).mapToInt(AbstractMap.SimpleEntry::getValue).sum();
      val toCheck = minEntries < maxEntries ? min : max;
      val toModify = minEntries < maxEntries ? 1 : -1;
      if (minEntries < maxEntries) input.sort(java.util.Map.Entry.comparingByKey());
      else input.sort((o1, o2) -> o2.getKey().compareTo(o1.getKey()));
      for (int i = 0; i < input.size(); i++) {
        if (input.get(i).getKey() == toCheck)
          input.set(i, new AbstractMap.SimpleEntry<>(input.get(i).getKey() + toModify, input.get(i).getValue() + 1));
        else break;
      }
      min = input.stream().mapToInt(AbstractMap.SimpleEntry::getKey).min().orElse(-1);
      max = input.stream().mapToInt(AbstractMap.SimpleEntry::getKey).max().orElse(-1);
    } while (min != max);

    int sum = 0;
    for (val integerIntegerSimpleEntry : input) {
      for (int j = 1; j < integerIntegerSimpleEntry.getValue(); j++) {
        sum += j;
      }
    }

    System.out.println(sum);
  }

}
