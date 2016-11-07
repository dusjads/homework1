package ru.ifmo.android_2016.calc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public final class CalculatorActivity extends Activity {
    double firstResult = 0, secondResult = 0;
    TextView resultView, expressionView;
    boolean isPoint = false;
    String expression = "", lastNumber = "0", oldOperation = "+", resultOperation, lastButton = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calculator);

        resultView = (TextView) findViewById(R.id.result);
        expressionView = (TextView) findViewById(R.id.expr);
        expressionView.setText(expression);
        resultView.setText("");
    }

    public void updateView() {
        //resultView.setText(String.format("%6d", result));
        expressionView.setText(expression);
    }

    public void onClickDigit(View view) {
        if (lastButton.equals("="))
            clear();
        String digit = ((Button) view).getText().toString();
        if (!expression.equals("0"))
            expression += digit;
        else
            expression = digit;
        lastNumber += digit;
        updateView();
        lastButton = "d";
    }

    public void onClickOperation(View view) {
        if (!lastButton.equals("d") && !lastButton.equals("=") && !lastButton.equals("p"))
            return;
        String operation = ((Button) view).getText().toString();
        expression += operation;
        if (operation.equals("+") || operation.equals("-")) {
            if (secondResult != 0) {
                switch (oldOperation) {
                    case "*":
                        secondResult *= Double.parseDouble(lastNumber);
                        break;
                    case "/":
                        secondResult /= Double.parseDouble(lastNumber);
                        break;
                }
                switch (resultOperation) {
                    case "+":
                        firstResult += secondResult;
                        break;
                    case "-":
                        firstResult -= secondResult;
                        break;
                }
                secondResult = 0;
            } else {
                switch (oldOperation) {
                    case "*":
                        firstResult *= Double.parseDouble(lastNumber);
                        break;
                    case "/":
                        firstResult /= Double.parseDouble(lastNumber);
                        break;
                    case "+":
                        firstResult += Double.parseDouble(lastNumber);
                        break;
                    case "-":
                        firstResult -= Double.parseDouble(lastNumber);
                        break;
                }
            }
        }
        else{
            if (secondResult != 0){
                switch (oldOperation) {
                    case "*":
                        secondResult *= Double.parseDouble(lastNumber);
                        break;
                    case "/":
                        secondResult /= Double.parseDouble(lastNumber);
                        break;
                }
            }
            else{
                secondResult = Double.parseDouble(lastNumber);
                resultOperation = oldOperation;
            }
        }
        oldOperation = operation;
        lastNumber = "0";
        updateView();
        isPoint = false;
        lastButton = "o";
    }

    public void onClickEqv(View view) {
        if (lastButton.equals("="))
            return;

        if (secondResult != 0) {
            switch (oldOperation) {
                case "*":
                    secondResult *= Double.parseDouble(lastNumber);
                    break;
                case "/":
                    secondResult /= Double.parseDouble(lastNumber);
                    break;
            }
            switch (resultOperation) {
                case "+":
                    firstResult += secondResult;
                    break;
                case "-":
                    firstResult -= secondResult;
                    break;
            }
            secondResult = 0;
        } else {
            switch (oldOperation) {
                case "*":
                    firstResult *= Double.parseDouble(lastNumber);
                    break;
                case "/":
                    firstResult /= Double.parseDouble(lastNumber);
                    break;
                case "+":
                    firstResult += Double.parseDouble(lastNumber);
                    break;
                case "-":
                    firstResult -= Double.parseDouble(lastNumber);
                    break;
            }
        }

        expression = String.valueOf(firstResult);
        lastNumber = "0";
        oldOperation = "+";
        resultView.setText(expression);
        updateView();
        lastButton = "=";

    }

    public void clear(){
        expression = "";
        firstResult = 0;
        secondResult = 0;
        isPoint = false;
        oldOperation = "+";
        resultOperation = "";
        resultView.setText("");
        expressionView.setText(expression);
    }

    public void onClickClear(View view) {
        clear();
        lastButton = "c";

    }

    public void onClickPoint(View view) {
        if (lastButton.equals("="))
            clear();
        if (isPoint)
            return;
        expression += ".";
        lastNumber += ".";
        lastButton = "p";
        isPoint = true;
        updateView();
    }


}
