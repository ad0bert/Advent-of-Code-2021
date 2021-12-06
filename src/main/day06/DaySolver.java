package main.day06;

import lombok.val;
import main.AbstractSolver;
import utils.AoCFileReader;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class DaySolver extends AbstractSolver {

  public DaySolver(String day) {
    super(day);
  }

  @Override
  public void solvePart1() {
    val allFish = AoCFileReader.readIntegerLine(new File(this.inputFile1));
    int days = 80;

    for (int i = 0; i < days; i++) {
      val newFish = new ArrayList<Integer>();
      for (int j = 0; j < allFish.size(); j++) {
        if (allFish.get(j) == 0) {
          allFish.set(j, 6);
          newFish.add(8);
        } else {
          allFish.set(j, allFish.get(j) - 1);
        }
      }
      allFish.addAll(newFish);
    }

    System.out.println(allFish.size());
  }

  @Override
  public void solvePart2() {
    val initFish = AoCFileReader.readIntegerLine(new File(this.inputFile2));
    int days = 256;

    val allFish = new HashMap<Integer, Long>();
    for (int i = 0; i < 9; i++) {
      allFish.put(i, 0L);
    }
    initFish.forEach(fish -> allFish.put(fish, allFish.get(fish) + 1));

    for (int i = 0; i < days; i++) {
      val newGeneration = new HashMap<Integer, Long>();
      for (int j = 0; j < 9; j++) {
        newGeneration.put(j, 0L);
      }
      allFish.forEach((key, value) -> {
        if (key == 0) {
          newGeneration.put(6, newGeneration.get(6) + value);
          newGeneration.put(8, newGeneration.get(8) + value);
        } else {
          newGeneration.put(key - 1, newGeneration.get(key - 1) + value);
        }
      });
      allFish.clear();
      allFish.putAll(newGeneration);
    }

    val res = allFish.values().stream().mapToLong(Long::longValue).sum();

    System.out.println(res);
  }

}
