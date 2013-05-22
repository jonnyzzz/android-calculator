package com.eugene.petrenko.android.calculator;

/**
 * Created 22.05.13 21:27
 *
 * @author Eugene Petrenko (eugene.petrenko@jetbrains.com)
 */
public enum Op {
  PLUS {
    @Override
    public double apply(double a, double b) {
      return a + b;
    }
  },
  MINUS {
    @Override
    public double apply(double a, double b) {
      return a - b;
    }
  },
  DIV {
    @Override
    public double apply(double a, double b) {
      return a / b;
    }
  },
  MUL {
    @Override
    public double apply(double a, double b) {
      return a * b;
    }
  },;

  public abstract double apply(double a, double b);
}
