package com.example.partefinalprojeto_eniac;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.RadioButton;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left + dpToPx(25), systemBars.top + dpToPx(25), systemBars.right + dpToPx(25), systemBars.bottom + dpToPx(25));
            return insets;
        });

        ImageView setaVoltar = findViewById(R.id.setaVoltar);
        setaVoltar.setOnClickListener(v -> getOnBackPressedDispatcher().onBackPressed());


        RadioButton radioRetirada = findViewById(R.id.radioRetirada);
        radioRetirada.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity2.class);
            intent.putExtra("tipoEntrega", "Retirada");
            startActivity(intent);
        });

        RadioButton radioEntrega = findViewById(R.id.radioEntrega);
        radioEntrega.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("tipoEntrega", "Entrega");
            startActivity(intent);
        });
    }

    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }
}