package com.example.kalkulatornapiwku;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private EditText etTip;
    private TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etTip = findViewById(R.id.etTip);
        tvResult = findViewById(R.id.tvResult);
        Button btn10Percent = findViewById(R.id.btn10Percent);
        Button btn15Percent = findViewById(R.id.btn15Percent);
        Button btn20Percent = findViewById(R.id.btn20Percent);


        View.OnClickListener sharedListener = v -> {
            if (v.getId() == R.id.btn10Percent) {
                calculate(10);
            } else {
                calculate(15);
            }
        };


        btn10Percent.setOnClickListener(sharedListener);
        btn15Percent.setOnClickListener(sharedListener);
        btn20Percent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                calculate(20);
            }
        });
    }
    private void calculate(int percent){


       double amount = Double.parseDouble(etTip.getText().toString());
       if (amount <= 0) {
           Toast.makeText(this, getText(R.string.error_wrong_amount), Toast.LENGTH_SHORT).show();
       }
       double finalAmount = (amount + (percent/100.00)*amount);
        if (Math.rint(finalAmount) == finalAmount)
        {
            int finalAmountInt = (int) finalAmount;
            tvResult.setText(String.format(getString(R.string.tv_result), String.valueOf(finalAmountInt)));
            return;
        }

        tvResult.setText(String.format(getString(R.string.tv_result), String.valueOf(Math.round(finalAmount * 100.00)/100.00)));
    }
}