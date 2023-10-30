package com.cscorner.calculatrice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    double firstNum = 0;
    String operation = "";
    boolean newInput = true;
    TextView screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screen = findViewById(R.id.screen);

        ArrayList<Button> numberButtons = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            int resID = getResources().getIdentifier("num" + i, "id", getPackageName());
            Button button = findViewById(resID);
            numberButtons.add(button);
        }

        for (Button button : numberButtons) {
            button.setOnClickListener(view -> {
                if (newInput) {
                    screen.setText(button.getText());
                    newInput = false;
                } else {
                    screen.append(button.getText());
                }
            });
        }

        Button clearButton = findViewById(R.id.ac);
        clearButton.setOnClickListener(view -> {
            screen.setText("0");
            firstNum = 0;
            operation = "";
            newInput = true;
        });

        Button deleteButton = findViewById(R.id.del);
        deleteButton.setOnClickListener(view -> {
            String currentText = screen.getText().toString();
            if (currentText.length() > 1) {
                screen.setText(currentText.substring(0, currentText.length() - 1));
            } else {
                screen.setText("0");
            }
        });

        Button dotButton = findViewById(R.id.point);
        dotButton.setOnClickListener(view -> {
            if (!screen.getText().toString().contains(".")) {
                screen.append(".");
            }
        });

        Button[] operatorButtons = {
                findViewById(R.id.plus),
                findViewById(R.id.min),
                findViewById(R.id.times),
                findViewById(R.id.div)
        };

        for (Button button : operatorButtons) {
            button.setOnClickListener(view -> {
                firstNum = Double.parseDouble(screen.getText().toString());
                operation = button.getText().toString();
                newInput = true;
            });
        }

        Button equalButton = findViewById(R.id.equal);
        equalButton.setOnClickListener(view -> {
            double secondNum = Double.parseDouble(screen.getText().toString());
            double result = 0;

            if (operation.equals("+")) {
                result = firstNum + secondNum;
            } else if (operation.equals("-")) {
                result = firstNum - secondNum;
            } else if (operation.equals("X")) {
                result = firstNum * secondNum;
            } else if (operation.equals("/")) {
                if (secondNum != 0) {
                    result = firstNum / secondNum;
                } else {
                    screen.setText("Erreur : Division par zéro");
                    return;
                }
            }

            screen.setText(String.valueOf(result));
            firstNum = result;
            newInput = true;
        });
    }
}
