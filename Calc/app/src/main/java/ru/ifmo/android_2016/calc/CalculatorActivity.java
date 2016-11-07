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
    String expression = "", lastNumber = "0", oldOperation = "+", resultOperation, lastButton = "", result = "";

    static final String FIRSTPESULT = "firstResult";
    static final String SECONDRESULT = "secondResult";
    static final String LASTBUTTON = "lastButton";
    static final String EXPRESSION = "expression";
    static final String ISPOINT = "isPoint";
    static final String LASTNUMBER = "lastNumber";
    static final String OLDOPERATION = "oldOperation";
    static final String RESULOPERATION = "resultOperation";
    static final String RESULT = "result";






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calculator);

        resultView = (TextView) findViewById(R.id.result);
        expressionView = (TextView) findViewById(R.id.expr);
        if (savedInstanceState != null) {
            firstResult = savedInstanceState.getDouble(FIRSTPESULT);
            secondResult = savedInstanceState.getDouble(SECONDRESULT);
            lastButton = savedInstanceState.getString(LASTBUTTON);
            expression = savedInstanceState.getString(EXPRESSION);
            oldOperation = savedInstanceState.getString(OLDOPERATION);
            resultOperation = savedInstanceState.getString(RESULOPERATION);
            isPoint = savedInstanceState.getBoolean(ISPOINT);
            lastNumber = savedInstanceState.getString(LASTNUMBER);
            result = savedInstanceState.getString(RESULT);

        }
        resultView.setText(result);
        expressionView.setText(expression);


    }

    public void updateView() {
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
        result = expression;
        updateView();
        lastButton = "=";

    }

    public void clear(){
        expression = "";
        firstResult = 0;
        secondResult = 0;
        isPoint = false;
        oldOperation = "+";
        lastNumber = "0";
        lastButton = "c";
        resultOperation = "";
        resultView.setText("");
        result = "";
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

    @Override
    public void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        saveInstanceState.putString(LASTNUMBER, lastNumber);
        saveInstanceState.putDouble(SECONDRESULT, secondResult);
        saveInstanceState.putBoolean(ISPOINT, isPoint);
        saveInstanceState.putDouble(FIRSTPESULT, firstResult);
        saveInstanceState.putString(LASTBUTTON, lastButton);
        saveInstanceState.putString(OLDOPERATION, oldOperation);
        saveInstanceState.putString(RESULOPERATION, resultOperation);
        saveInstanceState.putString(EXPRESSION, expression);
        saveInstanceState.putString(RESULT, result);

    }

}
