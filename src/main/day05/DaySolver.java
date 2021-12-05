package main.day05;

import lombok.val;
import main.AbstractSolver;
import utils.AoCFileReader;

import java.io.File;

public class DaySolver extends AbstractSolver {

  public DaySolver(String day) {
    super(day);
  }

  @Override
  public void solvePart1() {
    val input = AoCFileReader.readStringLineByLine(new File(this.inputFile1));
    val matrix = new int[1000][1000];
    for (String s : input) {
      val leftRight = s.split(" -> ");
      val left = leftRight[0];
      val right = leftRight[1];
      val leftXY = left.split(",");
      val rightXY = right.split(",");
      val leftX = Integer.parseInt(leftXY[0]);
      val leftY = Integer.parseInt(leftXY[1]);
      val rightX = Integer.parseInt(rightXY[0]);
      val rightY = Integer.parseInt(rightXY[1]);
      fillMatrix1(matrix, leftX, leftY, rightX, rightY);
    }
    System.out.println(countMatrix(matrix));
  }

  @Override
  public void solvePart2() {
    val input = AoCFileReader.readStringLineByLine(new File(this.inputFile2));
    val matrix = new int[1000][1000];
    for (String s : input) {
      val leftRight = s.split(" -> ");
      val left = leftRight[0];
      val right = leftRight[1];
      val leftXY = left.split(",");
      val rightXY = right.split(",");
      val leftX = Integer.parseInt(leftXY[0]);
      val leftY = Integer.parseInt(leftXY[1]);
      val rightX = Integer.parseInt(rightXY[0]);
      val rightY = Integer.parseInt(rightXY[1]);
      fillMatrix2(matrix, leftX, leftY, rightX, rightY);
    }
    System.out.println(countMatrix(matrix));
  }

  private void fillMatrix1(int[][] matrix, int leftX, int leftY, int rightX, int rightY) {
    if (leftX == rightX) {
      if (leftY > rightY) {
        for (int i = rightY; i <= leftY; i++) {
          matrix[i][leftX]++;
        }
      } else {
        for (int i = leftY; i <= rightY; i++) {
          matrix[i][leftX]++;
        }
      }
    }
    if (leftY == rightY) {
      if (leftX > rightX) {
        for (int i = rightX; i <= leftX; i++) {
          matrix[leftY][i]++;
        }
      } else {
        for (int i = leftX; i <= rightX; i++) {
          matrix[leftY][i]++;
        }
      }
    }
  }

  private void fillMatrix2(int[][] matrix, int leftX, int leftY, int rightX, int rightY) {
    if (leftX == rightX || leftY == rightY) fillMatrix1(matrix, leftX, leftY, rightX, rightY);
    else {
      int dist = Math.abs(leftX - rightX);
      if (leftX > rightX) {
        if (leftY > rightY) {
          for (int i = 0; i <= dist; i++) {
            matrix[rightY++][rightX++]++;
          }
        } else {
          for (int i = 0; i <= dist; i++) {
            matrix[leftY++][leftX--]++;
          }
        }
      } else {
        if (leftY > rightY) {
          for (int i = 0; i <= dist; i++) {
            matrix[rightY++][rightX--]++;
          }
        } else {
          for (int i = 0; i <= dist; i++) {
            matrix[leftY++][leftX++]++;
          }
        }
      }
    }

  }

  private int countMatrix(int[][] matrix) {
    int res = 0;
    for (int[] ints : matrix) {
      for (int anInt : ints) {
        if (anInt > 1) res++;
      }
    }
    return res;
  }

}
