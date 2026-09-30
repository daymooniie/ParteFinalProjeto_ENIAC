package com.example.partefinalprojeto_eniac;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.Button;
import android.widget.ImageView;
import android.animation.ObjectAnimator;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left + dpToPx(25), systemBars.top + dpToPx(25), systemBars.right + dpToPx(25), systemBars.bottom + dpToPx(25));
            return insets;
        });

        ImageView setaVoltar = findViewById(R.id.setaVoltar);
        setaVoltar.setOnClickListener(v -> getOnBackPressedDispatcher().onBackPressed());

        //Continuar com as informações aqui pq não fiz um banco de dados real ops ksjdksjd
        String rua = getIntent().getStringExtra("rua");
        String numero = getIntent().getStringExtra("numero");
        String cep = getIntent().getStringExtra("cep");
        String tipoEntrega = getIntent().getStringExtra("tipoEntrega");

        RadioGroup radGroupPagamento = findViewById(R.id.radGroupPagamento);
        Button btPagamento = findViewById(R.id.btPagamento);

        btPagamento.setOnClickListener(v -> {
            int idSelecionado = radGroupPagamento.getCheckedRadioButtonId();

            if (idSelecionado == -1) {
                Toast.makeText(this, "Selecione uma forma de pagamento", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton radioSelecionado = findViewById(idSelecionado);
            String tipoPagamento = radioSelecionado.getText().toString();

            Intent intent = new Intent(this, MainActivity4.class);
            intent.putExtra("rua", rua);
            intent.putExtra("numero", numero);
            intent.putExtra("cep", cep);
            intent.putExtra("tipoEntrega", tipoEntrega);
            intent.putExtra("tipoPagamento", tipoPagamento);
            startActivity(intent);
        });

        ImageView galao = findViewById(R.id.galao);

        ObjectAnimator wobble = ObjectAnimator.ofFloat(galao, "rotation", -8f, 8f);
        wobble.setDuration(300);
        wobble.setRepeatCount(5);
        wobble.setRepeatMode(ObjectAnimator.REVERSE);
        wobble.start();
    }

    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }
}