package com.example.partefinalprojeto_eniac;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenciasUsuario {

    private static final String PREFS_NOME = "LLAguasPrefs";

    private static final String CHAVE_NOME = "usuario_nome";
    private static final String CHAVE_EMAIL = "usuario_email";
    private static final String CHAVE_TELEFONE = "usuario_telefone";
    private static final String CHAVE_ENDERECO = "usuario_endereco";
    private static final String CHAVE_SENHA = "usuario_senha";
    private static final String CHAVE_CADASTRADO = "usuario_cadastrado";

    public static void salvarUsuario(Context context, String nome, String email,
                                     String telefone, String endereco, String senha) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NOME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(CHAVE_NOME, nome);
        editor.putString(CHAVE_EMAIL, email);
        editor.putString(CHAVE_TELEFONE, telefone);
        editor.putString(CHAVE_ENDERECO, endereco);
        editor.putString(CHAVE_SENHA, senha);
        editor.putBoolean(CHAVE_CADASTRADO, true);
        editor.apply();
    }

    public static boolean validarLogin(Context context, String email, String senha) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NOME, Context.MODE_PRIVATE);
        String emailSalvo = prefs.getString(CHAVE_EMAIL, null);
        String senhaSalva = prefs.getString(CHAVE_SENHA, null);

        if (emailSalvo == null || senhaSalva == null) {
            return false;
        }

        return emailSalvo.trim().equalsIgnoreCase(email.trim()) && senhaSalva.equals(senha);
    }

    public static boolean existeUsuarioCadastrado(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NOME, Context.MODE_PRIVATE);
        return prefs.getBoolean(CHAVE_CADASTRADO, false);
    }

    public static String obterNomeUsuario(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NOME, Context.MODE_PRIVATE);
        return prefs.getString(CHAVE_NOME, "");
    }
}
