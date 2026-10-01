package com.example.partefinalprojeto_eniac;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtSenha;
    private Button btnEntrar;
    private TextView txtCriarConta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        btnEntrar = findViewById(R.id.btnEntrar);
        txtCriarConta = findViewById(R.id.txtCriarConta);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tentarLogin();
            }
        });

        txtCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, CadastroActivity.class));
            }
        });
    }

    private void tentarLogin() {
        String email = edtEmail.getText().toString().trim();
        String senha = edtSenha.getText().toString();

        if (!validarCampos(email, senha)) {
            return;
        }

        boolean autenticado = PreferenciasUsuario.validarLogin(this, email, senha);

        if (autenticado) {
            startActivity(new Intent(LoginActivity.this, UsuarioMainActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Erro de autenticação: e-mail ou senha inválidos", Toast.LENGTH_LONG).show();
        }
    }

    private boolean validarCampos(String email, String senha) {
        boolean valido = true;

        if (TextUtils.isEmpty(email)) {
            edtEmail.setError("Informe seu e-mail ou usuário");
            valido = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.setError("Informe um e-mail válido");
            valido = false;
        }

        if (TextUtils.isEmpty(senha)) {
            edtSenha.setError("Informe sua senha");
            valido = false;
        }

        return valido;
    }
}
