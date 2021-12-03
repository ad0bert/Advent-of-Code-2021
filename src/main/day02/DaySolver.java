package main.day02;

import lombok.Data;
import lombok.val;
import main.AbstractSolver;
import utils.AoCFileReader;

import java.io.File;

public class DaySolver extends AbstractSolver {

  public DaySolver(String day) {
    super(day);
  }

  private final static String FORWARD = "forward";
  private final static String DOWN = "down";
  private final static String UP = "up";

  @Override
  public void solvePart1() {
    val input = AoCFileReader.readStringLineByLine(new File(this.inputFile1));
    val subMarine = new SubMarineV1();
    input.forEach(entry -> {
      val command = entry.split(" ");
      subMarine.move(command[0], Integer.parseInt(command[1]));
    });
    System.out.println(subMarine.getDepth() * subMarine.getHorizontal());
  }

  @Override
  public void solvePart2() {
    val input = AoCFileReader.readStringLineByLine(new File(this.inputFile2));
    val subMarine = new SubMarineV2();
    input.forEach(entry -> {
      val command = entry.split(" ");
      subMarine.move(command[0], Integer.parseInt(command[1]));
    });
    System.out.println(subMarine.getDepth() * subMarine.getHorizontal());
  }

  @Data
  private static class SubMarineV1 {
    private int depth = 0;
    private int horizontal = 0;

    public void move(String direction, int amount) {
      switch (direction) {
        case FORWARD -> this.horizontal += amount;
        case DOWN -> this.depth += amount;
        case UP -> this.depth -= amount;
      }
    }

  }

  @Data
  private static class SubMarineV2 {
    private int depth = 0;
    private int horizontal = 0;
    private int aim = 0;

    public void move(String direction, int amount) {
      switch (direction) {
        case FORWARD -> {
          this.horizontal += amount;
          this.depth += (this.aim * amount);
        }
        case DOWN -> {
          this.aim += amount;
        }
        case UP -> {
          this.aim -= amount;
        }
      }
    }

  }

}
