package utils;

import lombok.SneakyThrows;
import lombok.val;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class AoCFileReader {

  @SneakyThrows
  public static List<Integer> readIntegerLineByLine(File f) {
    val res = new ArrayList<Integer>();
    val br = new BufferedReader(new FileReader(f));
    String line;
    while ((line = br.readLine()) != null) {
      res.add(Integer.parseInt(line));
    }
    return res;
  }

  @SneakyThrows
  public static List<Long> readLongLineByLine(File f) {
    val res = new ArrayList<Long>();
    val br = new BufferedReader(new FileReader(f));
    String line;
    while ((line = br.readLine()) != null) {
      res.add(Long.parseLong(line));
    }
    return res;
  }

  @SneakyThrows
  public static List<Integer> readIntegerLine(File f) {
    val br = new BufferedReader(new FileReader(f));
    val line = br.readLine().split(",");
    return Arrays.stream(line).map(Integer::parseInt).collect(Collectors.toList());
  }

  @SneakyThrows
  public static List<Long> readLongLine(File f) {
    val br = new BufferedReader(new FileReader(f));
    val line = br.readLine().split(",");
    return Arrays.stream(line).map(Long::parseLong).collect(Collectors.toList());
  }

  @SneakyThrows
  public static List<List<String>> readListOfCharList(File f) {
    val res = new ArrayList<List<String>>();
    val br = new BufferedReader(new FileReader(f));
    String line;
    while ((line = br.readLine()) != null) {
      val charLine = new ArrayList<String>();
      for (char c : line.toCharArray()) {
        charLine.add(String.valueOf(c));
      }
      res.add(charLine);
    }
    return res;
  }

  @SneakyThrows
  public static String readOneLine(File f) {
    return new BufferedReader(new FileReader(f)).readLine();
  }

  @SneakyThrows
  public static List<String> readMultipleLines(File f) {
    val lines = new ArrayList<String>();
    val br = new BufferedReader(new FileReader(f));
    String line;
    while ((line = br.readLine()) != null) {
      lines.add(line);
    }
    return lines;
  }

  @SneakyThrows
  public static Map<String, Character> readStringMap(File f) {
    val res = new HashMap<String, Character>();
    val br = new BufferedReader(new FileReader(f));
    String line;
    while ((line = br.readLine()) != null) {
      res.put(line.split(" => ")[0], line.split(" => ")[1].charAt(0));
    }
    return res;
  }

}
