package com.example.partefinalprojeto_eniac;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import android.graphics.Typeface;
import android.text.style.StyleSpan;
import android.widget.ImageView;
import android.widget.Toast;

//Pro WhatsApp yaay
import android.net.Uri;



public class MainActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main5);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left + dpToPx(25), systemBars.top + dpToPx(25), systemBars.right + dpToPx(25), systemBars.bottom + dpToPx(25));
            return insets;
        });

        String rua = getIntent().getStringExtra("rua");
        String numero = getIntent().getStringExtra("numero");
        String cep = getIntent().getStringExtra("cep");
        String tipoEntrega = getIntent().getStringExtra("tipoEntrega");
        String tipoPagamento = getIntent().getStringExtra("tipoPagamento");



        ImageView setaVoltar = findViewById(R.id.setaVoltar);
        setaVoltar.setOnClickListener(v -> getOnBackPressedDispatcher().onBackPressed());

        TextView txtAvisoWhatsapp = findViewById(R.id.txtAvisoWhatsapp);
        SpannableString texto = new SpannableString(txtAvisoWhatsapp.getText());

        String palavra = "Whatsapp";
        int inicio = texto.toString().indexOf(palavra);

        if (inicio >= 0) {
            int fim = inicio + palavra.length();

            texto.setSpan(
                    new ForegroundColorSpan(ContextCompat.getColor(this, R.color.blue)),
                    inicio, fim,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            );

            texto.setSpan(
                    new StyleSpan(Typeface.BOLD),
                    inicio, fim,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            );
        }

        txtAvisoWhatsapp.setText(texto);

        // Aqui é pro WhatsApp também ,que emoção
        Button whatsapp = findViewById(R.id.whatsapp);
        whatsapp.setOnClickListener(v -> {
            String endereco;

            if ("Retirada".equals(tipoEntrega)) {
                endereco = "Rua Força Pública, 89 - CEP 07012030";
            } else {
                endereco = rua + ", " + numero + " - CEP: " + cep;
            }

            String mensagem =
                    "Olá! Gostaria de finalizar meu pedido.\n\n" +
                            "Tipo de entrega: " + tipoEntrega + "\n" +
                            "Endereço: " + endereco + "\n" +
                            "Forma de pagamento: " + tipoPagamento;

            String numeroWhatsApp = "5511951084655";

            Uri link = Uri.parse("https://wa.me/" + numeroWhatsApp)
                    .buildUpon()
                    .appendQueryParameter("text", mensagem)
                    .build();

            Intent abrirWhatsApp = new Intent(Intent.ACTION_VIEW, link);
            try {
                startActivity(abrirWhatsApp);
            } catch (android.content.ActivityNotFoundException e) {
                Toast.makeText(this, "Não foi possível abrir o link do WhatsApp", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }
}