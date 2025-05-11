package com.example.licznik;

import android.os.Bundle;
import android.app.AlertDialog;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int _counter = 0;
    private TextView counterText;
    private Button incrementButton, decrementButton, resetButton, setCounterButton, setCounterDialogButton;
    private EditText setCounterInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterText = findViewById(R.id.counterText);
        incrementButton = findViewById(R.id.incrementButton);
        decrementButton = findViewById(R.id.decrementButton);
        resetButton = findViewById(R.id.resetButton);
        setCounterButton = findViewById(R.id.setCounterButton);
        setCounterInput = findViewById(R.id.setCounterInput);


        setCounterDialogButton = findViewById(R.id.setCounterButtonDialog);

        updateCounterDisplay();

        incrementButton.setOnClickListener(v -> {
            _counter++;
            updateCounterDisplay();
        });

        decrementButton.setOnClickListener(v -> {
            _counter--;
            updateCounterDisplay();
        });

        resetButton.setOnClickListener(v -> {
            _counter = 0;
            updateCounterDisplay();
        });

        setCounterButton.setOnClickListener(v -> {
            String inputText = setCounterInput.getText().toString();
            if (!inputText.isEmpty()) {
                try {
                    _counter = Integer.parseInt(inputText);
                    updateCounterDisplay();
                    setCounterInput.setText("");
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Wprowadź poprawną liczbę", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Pole nie może być puste", Toast.LENGTH_SHORT).show();
            }
        });

        setCounterDialogButton.setOnClickListener(v -> showInputDialog());
    }

    private void updateCounterDisplay() {
        counterText.setText(String.valueOf(_counter));
    }

    private void showInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ustaw wartość licznika:");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);
        builder.setView(input);

        builder.setPositiveButton("OK", (dialog, which) -> {

        _counter = Integer.parseInt(input.getText().toString());
        updateCounterDisplay();

        });

        builder.setNegativeButton("Anuluj", (dialog, which) -> dialog.cancel());

        builder.show();
    }
}