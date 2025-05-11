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
    private Button incrementButton, decrementButton, resetButton, setCounterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterText = findViewById(R.id.counterText);
        incrementButton = findViewById(R.id.incrementButton);
        decrementButton = findViewById(R.id.decrementButton);
        resetButton = findViewById(R.id.resetButton);
        setCounterButton = findViewById(R.id.setCounterButton);

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

        setCounterButton.setOnClickListener(v -> showInputDialog());
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
            try {
                _counter = Integer.parseInt(input.getText().toString());
                updateCounterDisplay();
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Nieprawidłowa liczba!", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Anuluj", (dialog, which) -> dialog.cancel());

        builder.show();
    }
}