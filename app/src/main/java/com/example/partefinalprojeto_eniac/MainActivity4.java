package com.example.partefinalprojeto_eniac;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.widget.Button;




public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left + dpToPx(25), systemBars.top + dpToPx(25), systemBars.right + dpToPx(25), systemBars.bottom + dpToPx(25));
            return insets;
        });

        ImageView setaVoltar = findViewById(R.id.setaVoltar);
        setaVoltar.setOnClickListener(v -> getOnBackPressedDispatcher().onBackPressed());

        String rua = getIntent().getStringExtra("rua");
        String numero = getIntent().getStringExtra("numero");
        String cep = getIntent().getStringExtra("cep");
        TextView txtConfirmEndereco = findViewById(R.id.txtConfirmEndereco);
        txtConfirmEndereco.setText("Endereço: " + rua + ", " + numero + " - CEP: " + cep);

        String tipoEntrega = getIntent().getStringExtra("tipoEntrega");

        ImageView imgTipoEntrega = findViewById(R.id.imgTipoEntrega);
        TextView txtPuxarCard = findViewById(R.id.txtPuxarCard);

        if ("Retirada".equals(tipoEntrega)) {
            imgTipoEntrega.setImageResource(R.drawable.caixa);
            txtPuxarCard.setText("Retirada");
            txtConfirmEndereco.setText("Rua Força Pública, 89 - CEP 07012030");
        } else {
            imgTipoEntrega.setImageResource(R.drawable.finalizer);
            txtPuxarCard.setText("Entrega");
        }

        String tipoPagamento = getIntent().getStringExtra("tipoPagamento");

        ImageView imgTipoPagamento = findViewById(R.id.imgTipoPagamento);
        TextView txtConfirmForma = findViewById(R.id.txtConfirmForma);

        if ("Cartão".equals(tipoPagamento)) {
            imgTipoPagamento.setImageResource(R.drawable.cartao);
            txtConfirmForma.setText("Cartão");
        } else if ("Dinheiro".equals(tipoPagamento)) {
            imgTipoPagamento.setImageResource(R.drawable.dinheiro);
            txtConfirmForma.setText("Dinheiro");
        } else if ("Pix".equals(tipoPagamento)) {
            imgTipoPagamento.setImageResource(R.drawable.pix);
            txtConfirmForma.setText("Pix");
        }

        Button btFinal = findViewById(R.id.btFinal);

        ObjectAnimator escalaX = ObjectAnimator.ofFloat(btFinal, "scaleX", 1f, 0.96f);
        ObjectAnimator escalaY = ObjectAnimator.ofFloat(btFinal, "scaleY", 1f, 0.96f);

        escalaX.setRepeatCount(ValueAnimator.INFINITE);
        escalaY.setRepeatCount(ValueAnimator.INFINITE);
        escalaX.setRepeatMode(ValueAnimator.REVERSE);
        escalaY.setRepeatMode(ValueAnimator.REVERSE);

        AnimatorSet pulso = new AnimatorSet();
        pulso.playTogether(escalaX, escalaY);
        pulso.setDuration(650);
        pulso.start();

        btFinal.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity5.class);
            intent.putExtra("rua", rua);
            intent.putExtra("numero", numero);
            intent.putExtra("cep", cep);
            intent.putExtra("tipoEntrega", tipoEntrega);
            intent.putExtra("tipoPagamento", tipoPagamento);
            startActivity(intent);
        });


    }
    
    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }
}