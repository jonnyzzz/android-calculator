package com.eugene.petrenko.android.calculator;

import android.view.View;
import android.widget.TextView;
import org.jetbrains.annotations.NotNull;

import java.util.Stack;

/**
 * Created 22.05.13 21:27
 *
 * @author Eugene Petrenko (eugene.petrenko@jetbrains.com)
 */
public class CalculatorModel {
  private Stack<StackItem> myStack = new Stack<StackItem>();

  private final TextView myView;
  private double myValue = 0;

  public CalculatorModel(@NotNull final TextView view) {
    myView = view;
  }

  public double getValue() {
    return myValue;
  }

  public void update(double v) {
    myStack.clear();
    myValue = v;
    myView.setText("" + myValue);
  }

  @NotNull
  public View.OnClickListener forDigit(final int d) {
    return new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        update(getValue() * 10 + d);
      }
    };
  }

  @NotNull
  public View.OnClickListener forOp(@NotNull final Op op) {
    return new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        pop();
        push(op);
      }
    };
  }

  public void push(@NotNull final Op op) {
    myStack.push(new StackItem(op, myValue));
    update(0);
  }

  public void pop() {
    if (myStack.isEmpty()) return;
    update(myStack.pop().apply(myValue));
  }
}
