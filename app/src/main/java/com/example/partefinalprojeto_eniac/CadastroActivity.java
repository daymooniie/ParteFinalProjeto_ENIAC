package com.example.partefinalprojeto_eniac;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CadastroActivity extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtEmail;
    private EditText edtTelefone;
    private EditText edtEndereco;
    private EditText edtSenha;
    private EditText edtConfirmarSenha;
    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edtNome = findViewById(R.id.edtNome);
        edtEmail = findViewById(R.id.edtEmail);
        edtTelefone = findViewById(R.id.edtTelefone);
        edtEndereco = findViewById(R.id.edtEndereco);
        edtSenha = findViewById(R.id.edtSenha);
        edtConfirmarSenha = findViewById(R.id.edtConfirmarSenha);
        btnCadastrar = findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tentarCadastrar();
            }
        });
    }

    private void tentarCadastrar() {
        String nome = edtNome.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String telefone = edtTelefone.getText().toString().trim();
        String endereco = edtEndereco.getText().toString().trim();
        String senha = edtSenha.getText().toString();
        String confirmarSenha = edtConfirmarSenha.getText().toString();

        if (!validarCampos(nome, email, telefone, endereco, senha, confirmarSenha)) {
            return;
        }

        PreferenciasUsuario.salvarUsuario(this, nome, email, telefone, endereco, senha);

        Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show();

        finish();
    }

    private boolean validarCampos(String nome, String email, String telefone,
                                  String endereco, String senha, String confirmarSenha) {
        boolean valido = true;

        if (TextUtils.isEmpty(nome)) {
            edtNome.setError("Informe seu nome completo");
            valido = false;
        }

        if (TextUtils.isEmpty(email)) {
            edtEmail.setError("Informe seu e-mail");
            valido = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.setError("Informe um e-mail válido");
            valido = false;
        }

        if (TextUtils.isEmpty(telefone)) {
            edtTelefone.setError("Informe seu telefone");
            valido = false;
        }

        if (TextUtils.isEmpty(endereco)) {
            edtEndereco.setError("Informe seu endereço");
            valido = false;
        }

        if (TextUtils.isEmpty(senha)) {
            edtSenha.setError("Informe uma senha");
            valido = false;
        }

        if (TextUtils.isEmpty(confirmarSenha)) {
            edtConfirmarSenha.setError("Confirme sua senha");
            valido = false;
        } else if (!confirmarSenha.equals(senha)) {
            edtConfirmarSenha.setError("As senhas não coincidem");
            valido = false;
        }

        return valido;
    }
}
