package com.example.partefinalprojeto_eniac;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            Insets imeInsets = insets.getInsets(WindowInsetsCompat.Type.ime());
            int bottomPadding = Math.max(imeInsets.bottom, systemBars.bottom + dpToPx(25));
            v.setPadding(systemBars.left + dpToPx(25), systemBars.top + dpToPx(25), systemBars.right + dpToPx(25), bottomPadding);
            return insets;
        });

        ImageView setaVoltar = findViewById(R.id.setaVoltar);
        setaVoltar.setOnClickListener(v -> getOnBackPressedDispatcher().onBackPressed());

        ScrollView rolarteclado = findViewById(R.id.rolarteclado);
        Button btEntrega = findViewById(R.id.btEntrega);
        EditText editRua = findViewById(R.id.editRua);
        EditText editNUM = findViewById(R.id.editNUM);
        EditText editCEP = findViewById(R.id.editCEP);

        btEntrega.setOnClickListener(v -> {

            String rua = editRua.getText().toString();
            String numero = editNUM.getText().toString();
            String cep = editCEP.getText().toString();

            Intent intent = new Intent(this, MainActivity2.class);

            intent.putExtra("rua", rua);
            intent.putExtra("numero", numero);
            intent.putExtra("cep", cep);

            startActivity(intent);
        });


        //Pra a tela rolar pra cima só
        View.OnFocusChangeListener scrollToView = (v, hasFocus) -> {
            if (hasFocus) {
                rolarteclado.postDelayed(() -> {
                    int[] location = new int[2];
                    v.getLocationOnScreen(location);

                    int[] scrollLocation = new int[2];
                    rolarteclado.getLocationOnScreen(scrollLocation);

                    int viewTopRelativeToScroll = location[1] - scrollLocation[1] + rolarteclado.getScrollY();

                    rolarteclado.smoothScrollTo(0, viewTopRelativeToScroll - 50);
                }, 400);
            }
        };
        editRua.setOnFocusChangeListener(scrollToView);
        editNUM.setOnFocusChangeListener(scrollToView);
        editCEP.setOnFocusChangeListener(scrollToView);


    }

    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }
}