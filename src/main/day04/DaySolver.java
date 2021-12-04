package main.day04;

import lombok.val;
import main.AbstractSolver;
import utils.AoCFileReader;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DaySolver extends AbstractSolver {

  public DaySolver(String day) {
    super(day);
  }

  @Override
  public void solvePart1() {
    val input = AoCFileReader.readOneLine(new File(this.inputFile1));
    val bingoNumbers = Arrays.stream(input.split(",")).map(Integer::parseInt).collect(Collectors.toList());
    val boards = AoCFileReader.readBingoBoard(new File(this.inputFile1));
    List<List<Map.Entry<Integer, Boolean>>> winningBoard = null;
    int currentNumber = 0;
    for (Integer bingoNumber : bingoNumbers) {
      currentNumber = bingoNumber;
      markBoards(boards, currentNumber);
      winningBoard = getWinningBoard(boards);
      if (winningBoard != null) break;
    }
    assert winningBoard != null;
    val winningNumber = calculateBoardResult(winningBoard, currentNumber);
    assert winningNumber.equals(21607);
    System.out.println(winningNumber);
  }

  @Override
  public void solvePart2() {
    val input = AoCFileReader.readOneLine(new File(this.inputFile2));
    val bingoNumbers = Arrays.stream(input.split(",")).map(Integer::parseInt).collect(Collectors.toList());
    val boards = AoCFileReader.readBingoBoard(new File(this.inputFile2));
    List<List<Map.Entry<Integer, Boolean>>> winningBoard = null;
    int currentNumber = 0;
    for (Integer bingoNumber : bingoNumbers) {
      currentNumber = bingoNumber;
      markBoards(boards, currentNumber);
      winningBoard = removeAllWinningBoards(boards);
      if (boards.size() == 0) break;
    }
    assert winningBoard != null;
    val winningNumber = calculateBoardResult(winningBoard, currentNumber);
    assert winningNumber.equals(19012);
    System.out.println(winningNumber);
  }

  private List<List<Map.Entry<Integer, Boolean>>> removeAllWinningBoards(
      List<List<List<Map.Entry<Integer, Boolean>>>> boards
  ) {
    List<List<Map.Entry<Integer, Boolean>>> winningBoard = null;
    List<List<Map.Entry<Integer, Boolean>>> lastOne;
    do {
      lastOne = winningBoard;
      winningBoard = getWinningBoard(boards);
      boards.remove(winningBoard);
    } while (winningBoard != null);
    return lastOne;
  }

  private void markBoards(List<List<List<Map.Entry<Integer, Boolean>>>> boards, int toMark) {
    boards.forEach(board -> markBoard(board, toMark));
  }

  private void markBoard(List<List<Map.Entry<Integer, Boolean>>> board, int toMark) {
    board.forEach(line -> markBoardLine(line, toMark));
  }

  private void markBoardLine(List<Map.Entry<Integer, Boolean>> boardLine, int toMark) {
    boardLine.forEach(entry -> {
      if (entry.getKey().equals(toMark)) entry.setValue(true);
    });
  }

  private List<List<Map.Entry<Integer, Boolean>>> getWinningBoard(
      List<List<List<Map.Entry<Integer, Boolean>>>> boards
  ) {
    for (List<List<Map.Entry<Integer, Boolean>>> board : boards) {
      if (checkBoard(board)) return board;
    }
    return null;
  }

  private boolean checkBoard(List<List<Map.Entry<Integer, Boolean>>> board) {
    boolean hasBingoLine = board.stream().anyMatch(this::checkBoardLine);
    if (hasBingoLine) return true;
    return checkBoardCols(board);
  }

  private boolean checkBoardLine(List<Map.Entry<Integer, Boolean>> boardLine) {
    for (Map.Entry<Integer, Boolean> integerBooleanEntry : boardLine) {
      if (!integerBooleanEntry.getValue()) return false;
    }
    return true;
  }

  private boolean checkBoardCols(List<List<Map.Entry<Integer, Boolean>>> board) {
    for (int i = 0; i < board.get(0).size(); i++) {
      if (checkBoardCol(board, i)) return true;
    }
    return false;
  }

  private boolean checkBoardCol(List<List<Map.Entry<Integer, Boolean>>> board, int col) {
    val boardLine = board.stream().map(line -> line.get(col)).collect(Collectors.toList());
    return checkBoardLine(boardLine);
  }

  private Integer calculateBoardResult(List<List<Map.Entry<Integer, Boolean>>> board, Integer winningNumber) {
    return board.stream()
        .map(this::calculateBoardLineResult)
        .mapToInt(Integer::intValue)
        .sum() * winningNumber;
  }

  private Integer calculateBoardLineResult(List<Map.Entry<Integer, Boolean>> boardLine) {
    return boardLine.stream()
        .filter(entry -> !entry.getValue())
        .map(Map.Entry::getKey)
        .mapToInt(Integer::intValue)
        .sum();
  }

}
