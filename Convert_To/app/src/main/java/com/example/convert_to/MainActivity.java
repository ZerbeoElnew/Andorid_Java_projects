package com.example.convert_to;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            EditText num1 = findViewById(R.id.Number);
            Button btn = findViewById(R.id.Convert);
            TextView sum = findViewById(R.id.sum);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   String number= num1.getText().toString();
                   double number_n= Double.parseDouble(number);
                   double result = cmToInches(number_n);
                   sum.setText(""+result);

                }
            });
            return insets;
        });
    }
    private double cmToInches(double cm) {
        return cm * 0.393701;
    }
}