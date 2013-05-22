package com.eugene.petrenko.android.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static android.view.View.OnClickListener;
import static com.eugene.petrenko.android.calculator.Op.*;
import static com.eugene.petrenko.android.calculator.R.id.*;

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

    findViewById(R.id.calculator_button_ac).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        model.update(0);
      }
    });

    findViewById(calculator_button_equal).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        model.pop();
      }
    });

    findViewById(calculator_button_0).setOnClickListener(model.forDigit(0));
    findViewById(calculator_button_1).setOnClickListener(model.forDigit(1));
    findViewById(calculator_button_2).setOnClickListener(model.forDigit(2));
    findViewById(calculator_button_3).setOnClickListener(model.forDigit(3));
    findViewById(calculator_button_4).setOnClickListener(model.forDigit(4));
    findViewById(calculator_button_5).setOnClickListener(model.forDigit(5));
    findViewById(calculator_button_6).setOnClickListener(model.forDigit(6));
    findViewById(calculator_button_7).setOnClickListener(model.forDigit(7));
    findViewById(calculator_button_8).setOnClickListener(model.forDigit(8));
    findViewById(calculator_button_9).setOnClickListener(model.forDigit(9));

    findViewById(calculator_button_plus).setOnClickListener(model.forOp(PLUS));
    findViewById(calculator_button_div).setOnClickListener(model.forOp(DIV));
    findViewById(calculator_button_mul).setOnClickListener(model.forOp(MUL));
    findViewById(calculator_button_minus).setOnClickListener(model.forOp(MINUS));
  }
}
