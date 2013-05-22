package com.eugene.petrenko.android.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.jetbrains.annotations.NotNull;

import java.util.Stack;

public class JetCalc extends Activity {
  /**
   * Called when the activity is first created.
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    final TextView value = (TextView) findViewById(R.id.calculator_value);
    final CalculatorModel model = new CalculatorModel(value);


    findViewById(R.id.calculator_button_ac).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        model.update(0);
      }
    });

    findViewById(R.id.calculator_button_0).setOnClickListener(model.forDigit(0));
    findViewById(R.id.calculator_button_1).setOnClickListener(model.forDigit(1));
    findViewById(R.id.calculator_button_2).setOnClickListener(model.forDigit(2));
    findViewById(R.id.calculator_button_3).setOnClickListener(model.forDigit(3));
    findViewById(R.id.calculator_button_4).setOnClickListener(model.forDigit(4));
    findViewById(R.id.calculator_button_5).setOnClickListener(model.forDigit(5));
    findViewById(R.id.calculator_button_6).setOnClickListener(model.forDigit(6));
    findViewById(R.id.calculator_button_7).setOnClickListener(model.forDigit(7));
    findViewById(R.id.calculator_button_8).setOnClickListener(model.forDigit(8));
    findViewById(R.id.calculator_button_9).setOnClickListener(model.forDigit(9));

    findViewById(R.id.calculator_button_plus).setOnClickListener(model.forOp(Op.PLUS));
    findViewById(R.id.calculator_button_div).setOnClickListener(model.forOp(Op.DIV));
    findViewById(R.id.calculator_button_mul).setOnClickListener(model.forOp(Op.MUL));
    findViewById(R.id.calculator_button_minus).setOnClickListener(model.forOp(Op.MINUS));

    findViewById(R.id.calculator_button_equal).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        model.pop();
      }
    });

  }

  private static enum Op {
    PLUS {
      @Override
      public double apply(double a, double b) {
        return a+b;
      }
    },
    MINUS {
      @Override
      public double apply(double a, double b) {
        return a-b;
      }
    },
    DIV {
      @Override
      public double apply(double a, double b) {
        return a/b;
      }
    },
    MUL {
      @Override
      public double apply(double a, double b) {
        return a*b;
      }
    },
    ;

    public abstract double apply(double a, double b);
  }

  private static class StackItem {
    private final Op myOp;
    private final double myValue;

    private StackItem(Op op, double value) {
      myOp = op;
      myValue = value;
    }

    public double apply(double v) {
      return myOp.apply(myValue, v);
    }
  }


  private static class CalculatorModel {
    private Stack<StackItem> myStack = new Stack<StackItem>();

    private final TextView myView;
    private double myValue = 0;

    private CalculatorModel(TextView view) {
      myView = view;
    }

    public double getValue() {
      return myValue;
    }

    private void update(double v) {
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
}
