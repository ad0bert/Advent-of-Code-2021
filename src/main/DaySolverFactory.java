package main;

import lombok.SneakyThrows;
import lombok.val;

public class DaySolverFactory {

  @SneakyThrows
  public static AbstractSolver getSolver(int i) {
    val day = String.format("%02d", i);
    val clazz = Class.forName("main.day" + day + ".DaySolver");
    val ctor = clazz.getConstructor(String.class);
    return (AbstractSolver) ctor.newInstance(new Object[]{day});
  }

}
