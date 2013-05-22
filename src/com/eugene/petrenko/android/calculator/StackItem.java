package com.eugene.petrenko.android.calculator;

/**
 * Created 22.05.13 21:27
 *
 * @author Eugene Petrenko (eugene.petrenko@jetbrains.com)
 */
public class StackItem {
  private final Op myOp;
  private final double myValue;

  public StackItem(Op op, double value) {
    myOp = op;
    myValue = value;
  }

  public double apply(double v) {
    return myOp.apply(myValue, v);
  }
}
