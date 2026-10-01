# LL Águas para Android Studio

Projeto Android nativo com uma tela WebView. A interface e a lógica do app ficam em `app/src/main/assets/web/` (HTML, CSS, JavaScript e imagens); a ponte Android está em `app/src/main/java/br/com/llaguas/app/MainActivity.java`.

## Abrir e gerar o APK

1. Extraia este projeto e abra a pasta `android` no Android Studio.
2. Aguarde a sincronização do Gradle. Na primeira abertura, o Android Studio pode baixar o Gradle e componentes do SDK.
3. Para criar um APK de depuração, selecione **Build > Build Bundle(s) / APK(s) > Build APK(s)**.
4. O APK ficará em `app/build/outputs/apk/debug/app-debug.apk`.

O app aceita Android 7.0 (API 24) ou mais recente. A versão de depuração serve para instalação e avaliação; publicar na Play Store exige uma assinatura de lançamento própria.
