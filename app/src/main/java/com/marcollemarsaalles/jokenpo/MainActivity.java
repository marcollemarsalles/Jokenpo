package com.marcollemarsaalles.jokenpo;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayerVitoria;
    private MediaPlayer mediaPlayerDerrota;
    private MediaPlayer mediaPlayerEmpate;



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

        mediaPlayerVitoria = MediaPlayer.create(this, R.raw.vitoria);
        mediaPlayerDerrota = MediaPlayer.create(this, R.raw.derrota);
        mediaPlayerEmpate = MediaPlayer.create(this, R.raw.empate);

    }

    public void executarSomVitoria(View view) {
        mediaPlayerVitoria.start();
    }

    public void executarSomDerrota(View view) {
        mediaPlayerDerrota.start();
    }

    public void executarSomEmpate(View view) {
        mediaPlayerEmpate.start();
    }

    public void selecionadoPedra(View view) {
        this.opcaoSelecionada("pedra");
        ImageView vImagemResultUsuario = findViewById(R.id.img_usuario);
        vImagemResultUsuario.setImageResource(R.mipmap.pedra);
    }

    public void selecionadoPapel(View view) {
        this.opcaoSelecionada("papel");
        ImageView vImagemResultUsuario = findViewById(R.id.img_usuario);
        vImagemResultUsuario.setImageResource(R.mipmap.papel);
    }

    public void selecionadoTesoura(View view) {
        this.opcaoSelecionada("tesoura");
        ImageView vImagemResultUsuario = findViewById(R.id.img_usuario);
        vImagemResultUsuario.setImageResource(R.mipmap.tesoura);
    }

    public void opcaoSelecionada(String escolhaUsuario) {

        ImageView vImagemResultado = findViewById(R.id.img_padrao);
        ImageView vImagemResultBoot = findViewById(R.id.img_boot);

        TextView vTextoResultado = findViewById(R.id.txt_Resultado);
        TextView vQtdVitorias = findViewById(R.id.txt_cont_Vitoria);
        TextView vQtdDerrotas = findViewById(R.id.txt_cont_Derrotas);
        TextView vQtdEmpates = findViewById(R.id.txt_cont_Empates);

        int vNumero = new Random().nextInt(3);
        String[] vOpcoes = {"pedra", "papel", "tesoura"};
        String vEscolhaBot = vOpcoes[vNumero];

        switch (vEscolhaBot) {
            case "pedra":
                vImagemResultBoot.setImageResource(R.mipmap.pedra);
                break;
            case "papel":
                vImagemResultBoot.setImageResource(R.mipmap.papel);
                break;
            case "tesoura":
                vImagemResultBoot.setImageResource(R.mipmap.tesoura);
                break;
        }

        String vQtdVitoriasStr = vQtdVitorias.getText().toString();
        String vQtdDerrotasStr = vQtdDerrotas.getText().toString();
        String vQtdEmpatesStr = vQtdEmpates.getText().toString();

        int vVitorias = Integer.parseInt(vQtdVitoriasStr);
        String vVitorasStr;
        int vDerrotas = Integer.parseInt(vQtdDerrotasStr);
        String vDerrotasStr;
        int vEmpates = Integer.parseInt(vQtdEmpatesStr);
        String vEmpatesStr;

        if (vEscolhaBot.equals("pedra") && escolhaUsuario.equals("tesoura") ||
                vEscolhaBot.equals("papel") && escolhaUsuario.equals("pedra") ||
                vEscolhaBot.equals("tesoura") && escolhaUsuario.equals("papel")) {

            executarSomDerrota(vTextoResultado);
            vTextoResultado.setText("Você perdeu! Tente novamente :(");
            vImagemResultado.setImageResource(R.mipmap.derrota);
            vDerrotas++;
            vDerrotasStr = Integer.toString(vDerrotas);
            vQtdDerrotas.setText(vDerrotasStr);
            System.out.println("D" + vDerrotas);
        } else if (escolhaUsuario.equals("pedra") && vEscolhaBot.equals("tesoura") ||
                escolhaUsuario.equals("papel") && vEscolhaBot.equals("pedra") ||
                escolhaUsuario.equals("tesoura") && vEscolhaBot.equals("papel")) {

            executarSomVitoria(vTextoResultado);
            vTextoResultado.setText("Parabéns! Você ganhou :)");
            vImagemResultado.setImageResource(R.mipmap.vitoria);
            vVitorias++;
            vVitorasStr = Integer.toString(vVitorias);
            vQtdVitorias.setText(vVitorasStr);

        } else {
            executarSomEmpate(vTextoResultado);
            vTextoResultado.setText("Empate! Tente novamente :P");
            vImagemResultado.setImageResource(R.mipmap.empate);
            vEmpates++;
            vEmpatesStr = Integer.toString(vEmpates);
            vQtdEmpates.setText(vEmpatesStr);
        }

    }

}